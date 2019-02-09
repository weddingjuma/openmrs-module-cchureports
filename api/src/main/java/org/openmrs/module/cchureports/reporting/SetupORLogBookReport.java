package org.openmrs.module.cchureports.reporting;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openmrs.PersonName;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.data.converter.PropertyConverter;
import org.openmrs.module.reporting.data.patient.definition.PatientIdDataDefinition;
import org.openmrs.module.reporting.data.person.definition.GenderDataDefinition;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;

/**
 * Created by openmrs on 11/28/16.
 */
public class SetupORLogBookReport implements SetupReport {
	
	public void setup() throws Exception {
		
		setupProperties();
		
		ReportDefinition rd = createReportDefinition();
		
		ReportDesign design = Helper.createRowPerPatientXlsOverviewReportDesign(rd, "ORLogBook.xls", "ORLogBook.xls_", null);
		
		Properties props = new Properties();
		props.put("repeatingSections", "sheet:1,row:14,dataset:dataSet");
		props.put("sortWeight", "5000");
		design.setProperties(props);
		
		Helper.saveReportDesign(design);
	}
	
	public void delete() {
		ReportService rs = Context.getService(ReportService.class);
		for (ReportDesign rd : rs.getAllReportDesigns(false)) {
			if ("ORLogBook.xls_".equals(rd.getName())) {
				rs.purgeReportDesign(rd);
			}
		}
		Helper.purgeReportDefinition("OR Log Book Report");
	}
	
	private ReportDefinition createReportDefinition() {
		
		ReportDefinition reportDefinition = new ReportDefinition();
		reportDefinition.setName("OR Log Book Report");
		
		reportDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		reportDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		createDataSetDefinition(reportDefinition);
		
		Helper.saveReportDefinition(reportDefinition);
		
		return reportDefinition;
	}
	
	private void createDataSetDefinition(ReportDefinition reportDefinition) {
		
		// Create new dataset definition
		PatientDataSetDefinition dataSetDefinition = new PatientDataSetDefinition();
		dataSetDefinition.setName("dataSet");
		
		dataSetDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dataSetDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		// Add filters
		/*
		 * dataSetDefinition.addRowFilter(
		 * Cohorts.createInProgramParameterizableByDate("In program",
		 * Context.getProgramWorkflowService().getProgram(1)), "onDate=${endDate}");
		 */
		
		// Add Columns
		
		Map<String, Object> mappings = new HashMap<String, Object>();
		
		dataSetDefinition.addColumn("EMR ID", new PatientIdDataDefinition(), (String) null);
		
		PreferredNameDataDefinition d = new PreferredNameDataDefinition();
		dataSetDefinition.addColumn("givenName", d, mappings, new PropertyConverter(PersonName.class, "givenName"));
		dataSetDefinition.addColumn("familyName", d, mappings, new PropertyConverter(PersonName.class, "familyName"));
		dataSetDefinition.addColumn("Sexe", new GenderDataDefinition(), (String) null);
		
		mappings.put("startDate", "${startDate}");
		mappings.put("endDate", "${endDate}");
		
		reportDefinition.addDataSetDefinition("dataSet", dataSetDefinition, mappings);
	}
	
	private void setupProperties() {
	}
	
}
