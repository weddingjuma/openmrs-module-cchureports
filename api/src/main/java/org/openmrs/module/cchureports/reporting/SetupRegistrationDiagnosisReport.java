package org.openmrs.module.cchureports.reporting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openmrs.Concept;
import org.openmrs.PersonAttributeType;
import org.openmrs.PersonName;
import org.openmrs.api.context.Context;
import org.openmrs.module.cchureports.reporting.library.BasePatientDataLibrary;
import org.openmrs.module.cchureports.util.MetadataLookup;
import org.openmrs.module.reporting.cohort.definition.PersonAttributeCohortDefinition;
import org.openmrs.module.reporting.common.SortCriteria;
import org.openmrs.module.reporting.common.SortCriteria.SortDirection;
import org.openmrs.module.reporting.data.converter.PropertyConverter;
import org.openmrs.module.reporting.data.patient.definition.PatientIdentifierDataDefinition;
import org.openmrs.module.reporting.data.patient.library.BuiltInPatientDataLibrary;
import org.openmrs.module.reporting.data.person.definition.PreferredNameDataDefinition;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;
import org.springframework.stereotype.Component;

@Component
public class SetupRegistrationDiagnosisReport {
	
	// @Autowired
	private BuiltInPatientDataLibrary builtInPatientData = new BuiltInPatientDataLibrary();
	
	// @Autowired
	private BasePatientDataLibrary basePatientData = new BasePatientDataLibrary();
	
	private PersonAttributeType careTaker1;
	
	private PersonAttributeType careTaker2;
	
	private PersonAttributeType phoneContact;
	
	private PersonAttributeType registrationDiagnosis = null;
	
	public void setup() throws Exception {
		
		setupProperties();
		
		ReportDefinition rd = createReportDefinition();
		
		ReportDesign design = Helper.createRowPerPatientXlsOverviewReportDesign(rd, "registrationdiagnosisreport.xls", "registrationdiagnosisreport.xls_", null);
		
		Properties props = new Properties();
		props.put("repeatingSections", "sheet:1,row:9,dataset:dataSet");
		props.put("sortWeight", "5000");
		design.setProperties(props);
		
		Helper.saveReportDesign(design);
	}
	
	public void delete() {
		ReportService rs = Context.getService(ReportService.class);
		for (ReportDesign rd : rs.getAllReportDesigns(false)) {
			if ("registrationdiagnosisreport.xls_".equals(rd.getName())) {
				rs.purgeReportDesign(rd);
			}
		}
		Helper.purgeReportDefinition("Registration Diagnosis Report");
	}
	
	private ReportDefinition createReportDefinition() {
		
		ReportDefinition reportDefinition = new ReportDefinition();
		reportDefinition.setName("Registration Diagnosis Report");
		
		reportDefinition.addParameter(new Parameter("valueConcepts", "Diagnosis List", Concept.class, List.class, null));
		
		createDataSetDefinition(reportDefinition);
		
		Helper.saveReportDefinition(reportDefinition);
		
		return reportDefinition;
	}
	
	private void createDataSetDefinition(ReportDefinition reportDefinition) {
		
		PatientDataSetDefinition dataSetDefinition = new PatientDataSetDefinition();
		dataSetDefinition.setName("dataSet");
		dataSetDefinition.setParameters(getParameters());
		
		SortCriteria sortCriteria = new SortCriteria();
		sortCriteria.addSortElement("Current Age (mth)", SortDirection.ASC);
		dataSetDefinition.setSortCriteria(sortCriteria);
		
		PersonAttributeCohortDefinition pacd = new PersonAttributeCohortDefinition();
		pacd.setAttributeType(new PersonAttributeType(registrationDiagnosis.getId()));
		pacd.addParameter(new Parameter("valueConcepts", "Diagnosis List", Concept.class, List.class, null));
		
		dataSetDefinition.addRowFilter(pacd, "valueConcepts=${valueConcepts}");
		
		PatientIdentifierDataDefinition i = new PatientIdentifierDataDefinition();
		i.addType(Context.getPatientService().getPatientIdentifierType(3));
		dataSetDefinition.addColumn("IP", i, (String) null);
		
		PreferredNameDataDefinition d = new PreferredNameDataDefinition();
		dataSetDefinition.addColumn("givenName", d, new HashMap<String, Object>(), new PropertyConverter(PersonName.class, "givenName"));
		dataSetDefinition.addColumn("familyName", d, new HashMap<String, Object>(), new PropertyConverter(PersonName.class, "familyName"));
		dataSetDefinition.addColumn("Current Age (yr)", basePatientData.getAgeAtEndInYears(), new HashMap<String, Object>());
		dataSetDefinition.addColumn("Current Age (mth)", basePatientData.getAgeAtEndInMonths(), new HashMap<String, Object>());
		dataSetDefinition.addColumn("M/F", builtInPatientData.getGender(), new HashMap<String, Object>());
		
		dataSetDefinition.addColumn("Registration Diagnosis", basePatientData.getPersonAttribute(registrationDiagnosis), new HashMap<String, Object>());
		
		dataSetDefinition.addColumn("District", basePatientData.getDistrict(), new HashMap<String, Object>());
		dataSetDefinition.addColumn("CareTaker1", basePatientData.getPersonAttribute(careTaker1), new HashMap<String, Object>());
		dataSetDefinition.addColumn("CareTaker2", basePatientData.getPersonAttribute(careTaker2), new HashMap<String, Object>());
		dataSetDefinition.addColumn("Phone Contact", basePatientData.getPersonAttribute(phoneContact), new HashMap<String, Object>());
		
		Map<String, Object> mappings = new HashMap<String, Object>();
		mappings.put("valueConcepts", "${valueConcepts}");
		
		reportDefinition.addDataSetDefinition("dataSet", dataSetDefinition, mappings);
	}
	
	private void setupProperties() {
		registrationDiagnosis = MetadataLookup.getPersonAttributeType("537f22bb-8b4e-4d51-9f54-d3b315a1a2d2");
		careTaker1 = MetadataLookup.getPersonAttributeType("eca08628-b7f5-41f7-b50e-1409f8974fc0");
		careTaker2 = MetadataLookup.getPersonAttributeType("5bb3308e-6372-415b-b5ca-9fe7238fd0b6");
		phoneContact = MetadataLookup.getPersonAttributeType("e6e8fcb0-739c-496e-b295-b017f6f1fb84");
	}
	
	public List<Parameter> getParameters() {
		List<Parameter> l = new ArrayList<Parameter>();
		l.add(new Parameter("valueConcepts", "Diagnosis List", Concept.class, List.class, null));
		return l;
	}
	
}
