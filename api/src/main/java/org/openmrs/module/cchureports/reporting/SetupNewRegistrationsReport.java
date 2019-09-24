package org.openmrs.module.cchureports.reporting;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openmrs.Patient;
import org.openmrs.PersonAttributeType;
import org.openmrs.PersonName;
import org.openmrs.api.context.Context;
import org.openmrs.module.cchureports.reporting.library.BasePatientDataLibrary;
import org.openmrs.module.cchureports.util.Cohorts;
import org.openmrs.module.cchureports.util.MetadataLookup;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.data.converter.PropertyConverter;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;
import org.springframework.stereotype.Component;

@Component
public class SetupNewRegistrationsReport {
	
	// @Autowired
	
	// @Autowired
	private BasePatientDataLibrary basePatientData = new BasePatientDataLibrary();
	
	private PersonAttributeType registrationDiagnosis = null;
	
	public void setup() throws Exception {
		
		setupProperties();
		
		ReportDefinition rd = createReportDefinition();
		
		ReportDesign design = Helper.createRowPerPatientXlsOverviewReportDesign(rd, "newRegistrations.xls", "newRegistrations.xls_", null);
		
		Properties props = new Properties();
		props.put("repeatingSections", "sheet:1,row:9,dataset:dataSet");
		props.put("sortWeight", "5000");
		design.setProperties(props);
		
		Helper.saveReportDesign(design);
	}
	
	public void delete() {
		ReportService rs = Context.getService(ReportService.class);
		for (ReportDesign rd : rs.getAllReportDesigns(false)) {
			if ("newRegistrations.xls_".equals(rd.getName())) {
				rs.purgeReportDesign(rd);
			}
		}
		Helper.purgeReportDefinition("New Registrations Report");
	}
	
	private ReportDefinition createReportDefinition() {
		
		ReportDefinition reportDefinition = new ReportDefinition();
		reportDefinition.setName("New Registrations Report");
		
		reportDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		reportDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		createDataSetDefinition(reportDefinition);
		
		Helper.saveReportDefinition(reportDefinition);
		
		return reportDefinition;
	}
	
	private void createDataSetDefinition(ReportDefinition reportDefinition) {
		
		PatientDataSetDefinition dataSetDefinition = new PatientDataSetDefinition();
		dataSetDefinition.setName("dataSet");
		dataSetDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dataSetDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		CohortDefinition rowFilter = Cohorts.newPatientsNotVoided();
		dataSetDefinition.addRowFilter(Mapped.mapStraightThrough(rowFilter));
		
		dataSetDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dataSetDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		PatientIdentifierDataDefinition i = new PatientIdentifierDataDefinition();
		i.addType(Context.getPatientService().getPatientIdentifierType(3));
		dataSetDefinition.addColumn("IP", i, (String) null);
		
		PreferredNameDataDefinition d = new PreferredNameDataDefinition();
		dataSetDefinition.addColumn("givenName", d, new HashMap<String, Object>(), new PropertyConverter(PersonName.class, "givenName"));
		dataSetDefinition.addColumn("familyName", d, new HashMap<String, Object>(), new PropertyConverter(PersonName.class, "familyName"));
		
		dataSetDefinition.addColumn("Registration Date", d, new HashMap<String, Object>(), new PropertyConverter(Patient.class, "dateCreated"));
		
		dataSetDefinition.addColumn("Registration Diagnosis", basePatientData.getPersonAttribute(registrationDiagnosis), new HashMap<String, Object>());
		
		dataSetDefinition.addColumn("District", basePatientData.getDistrict(), new HashMap<String, Object>());
		
		Map<String, Object> mappings = new HashMap<String, Object>();
		mappings.put("startDate", "${startDate}");
		mappings.put("endDate", "${endDate}");
		
		reportDefinition.addDataSetDefinition("dataSet", dataSetDefinition, mappings);
	}
	
	private void setupProperties() {
		registrationDiagnosis = MetadataLookup.getPersonAttributeType("537f22bb-8b4e-4d51-9f54-d3b315a1a2d2");
	}
	
}
