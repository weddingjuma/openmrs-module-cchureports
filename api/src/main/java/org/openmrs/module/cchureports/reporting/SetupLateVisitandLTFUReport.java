package org.openmrs.module.cchureports.reporting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openmrs.Concept;
import org.openmrs.EncounterType;
import org.openmrs.PersonAttributeType;
import org.openmrs.PersonName;
import org.openmrs.api.context.Context;
import org.openmrs.module.cchureports.reporting.library.BasePatientDataLibrary;
import org.openmrs.module.cchureports.reporting.library.DataFactory;
import org.openmrs.module.cchureports.util.Cohorts;
import org.openmrs.module.cchureports.util.MetadataLookup;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.common.SortCriteria.SortDirection;
import org.openmrs.module.reporting.data.converter.PropertyConverter;
import org.openmrs.module.reporting.data.patient.definition.PatientDataDefinition;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.patient.library.BuiltInPatientDataLibrary;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.evaluation.parameter.ParameterizableUtil;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;

public class SetupLateVisitandLTFUReport {
	
	//@Autowired
	private BuiltInPatientDataLibrary builtInPatientData = new BuiltInPatientDataLibrary();
	
	//@Autowired
	private BasePatientDataLibrary basePatientData = new BasePatientDataLibrary();
	
	private DataFactory df = new DataFactory();
	
	private PersonAttributeType careTaker1;
	
	private PersonAttributeType careTaker2;
	
	private PersonAttributeType phoneContact;
	
	private List<EncounterType> dischargeEncounterType = new ArrayList<EncounterType>();
	
	private Concept returnVisitDate = null;
	
	private Concept nextVisitSite = null;
	
	private PersonAttributeType registrationDiagnosis = null;
	
	public void setup() throws Exception {
		
		setupProperties();
		
		ReportDefinition rd = createReportDefinition();
		
		ReportDesign design = Helper.createRowPerPatientXlsOverviewReportDesign(rd, "lateVisitandLTFUReport.xls",
		    "lateVisitandLTFUReport.xls_", null);
		
		Properties props = new Properties();
		props.put("repeatingSections", "sheet:1,row:9,dataset:dataSet");
		props.put("sortWeight", "5000");
		design.setProperties(props);
		
		Helper.saveReportDesign(design);
	}
	
	public void delete() {
		ReportService rs = Context.getService(ReportService.class);
		for (ReportDesign rd : rs.getAllReportDesigns(false)) {
			if ("lateVisitandLTFUReport.xls_".equals(rd.getName())) {
				rs.purgeReportDesign(rd);
			}
		}
		Helper.purgeReportDefinition("Late Visit and Lost to Follow Up Report");
	}
	
	private ReportDefinition createReportDefinition() {
		
		ReportDefinition reportDefinition = new ReportDefinition();
		reportDefinition.setName("Late Visit and Lost to Follow Up Report");
		reportDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		createDataSetDefinition(reportDefinition);
		
		Helper.saveReportDefinition(reportDefinition);
		
		return reportDefinition;
	}
	
	private void createDataSetDefinition(ReportDefinition reportDefinition) {
		
		//Create the only dataset for our report
		PatientDataSetDefinition dataSetDefinition = new PatientDataSetDefinition();
		dataSetDefinition.setName("dataSet");
		dataSetDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		//Add a filter to the dataset to only query patients whose last next followup date is before endDate parameter
		CohortDefinition rowFilter = Cohorts.getPatientsWhoseObsValueDateIsBeforeEndDateAtLocation(returnVisitDate);
		dataSetDefinition.addRowFilter(Mapped.mapStraightThrough(rowFilter));
		
		//Sort the dataset rows by the "Days since last followup Date" column -Descending
		SortCriteria sortCriteria = new SortCriteria();
		sortCriteria.addSortElement("Days since last Followup Date", SortDirection.DESC);
		dataSetDefinition.setSortCriteria(sortCriteria);
		
		PatientIdentifierDataDefinition i = new PatientIdentifierDataDefinition();
		i.addType(Context.getPatientService().getPatientIdentifierType(3));
		dataSetDefinition.addColumn("IP", i, (String) null);
		
		PreferredNameDataDefinition d = new PreferredNameDataDefinition();
		dataSetDefinition.addColumn("givenName", d, new HashMap<String, Object>(), new PropertyConverter(PersonName.class,
		        "givenName"));
		dataSetDefinition.addColumn("familyName", d, new HashMap<String, Object>(), new PropertyConverter(PersonName.class,
		        "familyName"));
		dataSetDefinition.addColumn("Current Age (yr)", basePatientData.getAgeAtEndInYears(), new HashMap<String, Object>());
		dataSetDefinition.addColumn("Current Age (mth)", basePatientData.getAgeAtEndInMonths(),
		    new HashMap<String, Object>());
		dataSetDefinition.addColumn("M/F", builtInPatientData.getGender(), new HashMap<String, Object>());
		
		dataSetDefinition.addColumn("Registration Diagnosis", basePatientData.getPersonAttribute(registrationDiagnosis),
		    new HashMap<String, Object>());
		
		dataSetDefinition.addColumn("District", basePatientData.getDistrict(), new HashMap<String, Object>());
		dataSetDefinition.addColumn("CareTaker1", basePatientData.getPersonAttribute(careTaker1),
		    new HashMap<String, Object>());
		dataSetDefinition.addColumn("CareTaker2", basePatientData.getPersonAttribute(careTaker2),
		    new HashMap<String, Object>());
		dataSetDefinition.addColumn("Phone Contact", basePatientData.getPersonAttribute(phoneContact),
		    new HashMap<String, Object>());
		
		dataSetDefinition.addColumn("Appointment Site",
		    df.getMostRecentObsByEndDate(nextVisitSite, dischargeEncounterType, df.getObsValueCodedConverter()),
		    new HashMap<String, Object>());
		
		dataSetDefinition.addColumn("Appointment Date", basePatientData.getAppointmentDatesDuringPeriod(),
		    new HashMap<String, Object>());
		
		PatientDataDefinition daysSinceLastVisit = df.getDifferenceSinceLastObservation("lastFollowupDate", returnVisitDate);
		daysSinceLastVisit.addParameter(new Parameter("endDate", "endDate", Date.class));
		
		dataSetDefinition.addColumn("Days since last Followup Date", daysSinceLastVisit,
		    ParameterizableUtil.createParameterMappings("endDate=${endDate}"));
		
		Map<String, Object> mappings = new HashMap<String, Object>();
		mappings.put("endDate", "${endDate}");
		
		reportDefinition.addDataSetDefinition("dataSet", dataSetDefinition, mappings);
	}
	
	private void setupProperties() {
		returnVisitDate = MetadataLookup.getConcept("c38e5cc4-3f10-11e4-adec-0800271c1b75");
		nextVisitSite = MetadataLookup.getConcept("2d2185b1-3b81-42db-a551-1a5315b0a98a");
		registrationDiagnosis = MetadataLookup.getPersonAttributeType("537f22bb-8b4e-4d51-9f54-d3b315a1a2d2");
		careTaker1 = MetadataLookup.getPersonAttributeType("eca08628-b7f5-41f7-b50e-1409f8974fc0");
		careTaker2 = MetadataLookup.getPersonAttributeType("5bb3308e-6372-415b-b5ca-9fe7238fd0b6");
		phoneContact = MetadataLookup.getPersonAttributeType("e6e8fcb0-739c-496e-b295-b017f6f1fb84");
		dischargeEncounterType.add(MetadataLookup.getEncounterType("81dd3390-3f10-11e4-adec-0800271c1b75"));
		dischargeEncounterType.add(MetadataLookup.getEncounterType("81852aee-3f10-11e4-adec-0800271c1b75"));
	}
	
}
