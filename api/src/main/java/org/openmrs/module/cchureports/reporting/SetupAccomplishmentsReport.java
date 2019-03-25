package org.openmrs.module.cchureports.reporting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.cchureports.dataset.definition.EncounterAndObsDataSetDefinition2;
import org.openmrs.module.cchureports.reporting.library.BasePatientDataLibrary;
import org.openmrs.module.reporting.common.ObjectUtil;
import org.openmrs.module.reporting.data.encounter.library.BuiltInEncounterDataLibrary;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.query.encounter.definition.MappedParametersEncounterQuery;
import org.openmrs.module.reporting.query.encounter.definition.SqlEncounterQuery;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;

public class SetupAccomplishmentsReport {
	
	BuiltInEncounterDataLibrary encounterData = new BuiltInEncounterDataLibrary();
	
	private BasePatientDataLibrary basePatientData = new BasePatientDataLibrary();
	
	protected final static Log log = LogFactory.getLog(SetupAccomplishmentsReport.class);
	
	public void setup() throws Exception {
		
		ReportDefinition rd = createReportDefinition();
		
		ReportDesign design = Helper.createRowPerPatientXlsOverviewReportDesign(rd, "surgeonCasesReport.xls", "surgeonCasesReport.xls_", null);
		
		Properties props = new Properties();
		props.put("repeatingSections", "sheet:1,row:9,dataset:dataSet");
		props.put("sortWeight", "5000");
		design.setProperties(props);
		
		Helper.saveReportDesign(design);
	}
	
	public void delete() {
		ReportService rs = Context.getService(ReportService.class);
		for (ReportDesign rd : rs.getAllReportDesigns(false)) {
			if ("surgeonCasesReport.xls_".equals(rd.getName())) {
				rs.purgeReportDesign(rd);
			}
		}
		Helper.purgeReportDefinition("Surgeon Cases Report");
	}
	
	private ReportDefinition createReportDefinition() {
		
		ReportDefinition reportDefinition = new ReportDefinition();
		reportDefinition.setName("Surgeon Cases Report");
		reportDefinition.addParameter(new Parameter("startDate", "From Date", Date.class));
		reportDefinition.addParameter(new Parameter("endDate", "To Date", Date.class));
		
		Parameter form = new Parameter("doctor", "Doctor", Concept.class);
		
		form.setRequired(false);
		
		reportDefinition.addParameter(form);
		
		createDataSetDefinition(reportDefinition);
		
		Helper.saveReportDefinition(reportDefinition);
		
		return reportDefinition;
	}
	
	private void createDataSetDefinition(ReportDefinition reportDefinition) {
		
		EncounterAndObsDataSetDefinition2 dsd = new EncounterAndObsDataSetDefinition2();
		dsd.setName("dataSet");
		dsd.setParameters(getParameters());
		
		SqlEncounterQuery rowFilter = new SqlEncounterQuery();
		rowFilter.addParameter(new Parameter("onOrAfter", "On Or After", Date.class));
		rowFilter.addParameter(new Parameter("onOrBefore", "On Or Before", Date.class));
		
		Parameter doctor = new Parameter("doctor", "Doctor", Concept.class);
		doctor.setRequired(false);
		
		rowFilter.addParameter(doctor);
		
		rowFilter.setQuery("select encounter_id from obs where value_coded=:doctor and obs_datetime >=:onOrAfter and   obs_datetime <=:onOrBefore and voided=0 ");
		
		MappedParametersEncounterQuery q = new MappedParametersEncounterQuery(rowFilter, ObjectUtil.toMap("onOrAfter=startDate,onOrBefore=endDate,doctor=doctor"));
		dsd.addRowFilter(Mapped.mapStraightThrough(q));
		
		PatientIdentifierDataDefinition i = new PatientIdentifierDataDefinition();
		i.addType(Context.getPatientService().getPatientIdentifierType(3));
		dsd.addColumn("IP", i, (String) null);
		
		dsd.addColumn("familyName", basePatientData.getPreferredFamilyName(), "");
		dsd.addColumn("givenName", basePatientData.getPreferredGivenName(), "");
		
		reportDefinition.addDataSetDefinition("dataSet", Mapped.mapStraightThrough(dsd));
		
	}
	
	public List<Parameter> getParameters() {
		List<Parameter> l = new ArrayList<Parameter>();
		l.add(new Parameter("startDate", "From Date", Date.class));
		l.add(new Parameter("endDate", "To Date", Date.class));
		
		Parameter doctor = new Parameter("doctor", "Doctor", Concept.class);
		doctor.setRequired(true);
		l.add(doctor);
		return l;
	}
	
}
