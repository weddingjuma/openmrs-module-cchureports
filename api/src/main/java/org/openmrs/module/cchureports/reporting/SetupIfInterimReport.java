/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.cchureports.reporting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openmrs.Concept;
import org.openmrs.PersonAttributeType;
import org.openmrs.VisitType;
import org.openmrs.api.context.Context;
import org.openmrs.module.cchureports.util.Indicators;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.CompositionCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.GenderCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.PersonAttributeCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.VisitCohortDefinition;
import org.openmrs.module.reporting.dataset.definition.CohortIndicatorDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.evaluation.parameter.ParameterizableUtil;
import org.openmrs.module.reporting.indicator.CohortIndicator;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;

/**
 * @author Bailly RURANGIRWA
 */
public class SetupIfInterimReport {
	
	private List<VisitType> allVisitTypes;
	
	private PersonAttributeType registrationDiagnosis;
	
	private Concept hydrocephalusHC;
	
	public void setup() throws Exception {
		
		setUpProperties();
		
		ReportDefinition rd = createReportDefinition();
		ReportDesign design = Helper.createRowPerPatientXlsOverviewReportDesign(rd, "IfInterimReport.xls", "IfInterimReport.xls_", null);
		Properties props = new Properties();
		props.put("repeatingSections", "sheet:1,dataset:If Interim Report Data Set");
		props.put("sortWeight", "5000");
		design.setProperties(props);
		Helper.saveReportDesign(design);
		
	}
	
	public void delete() {
		ReportService rs = Context.getService(ReportService.class);
		for (ReportDesign rd : rs.getAllReportDesigns(false)) {
			if ("IfInterimReport.xls_".equals(rd.getName())) {
				rs.purgeReportDesign(rd);
			}
		}
		Helper.purgeReportDefinition("If Interim Report");
	}
	
	private ReportDefinition createReportDefinition() {
		
		ReportDefinition rd = new ReportDefinition();
		rd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		rd.addParameter(new Parameter("endDate", "End Date", Date.class));
		rd.setName("If Interim Report");
		rd.addDataSetDefinition(createBaseDataSet(), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}"));
		Helper.saveReportDefinition(rd);
		return rd;
	}
	
	private CohortIndicatorDataSetDefinition createBaseDataSet() {
		CohortIndicatorDataSetDefinition dsd = new CohortIndicatorDataSetDefinition();
		dsd.setName("If Interim Report Data Set");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		createIndicators(dsd);
		return dsd;
	}
	
	private void createIndicators(CohortIndicatorDataSetDefinition dsd) {
		
		VisitCohortDefinition patientsWithAnyVisitBetweenDates = new VisitCohortDefinition();
		patientsWithAnyVisitBetweenDates.addParameter(new Parameter("createdOnOrAfter", "Created on or After", Date.class));
		patientsWithAnyVisitBetweenDates.addParameter(new Parameter("createdOnOrBefore", "Created on or before", Date.class));
		patientsWithAnyVisitBetweenDates.setVisitTypeList(allVisitTypes);
		
		GenderCohortDefinition males = new GenderCohortDefinition();
		males.setName("male Patients");
		males.setMaleIncluded(true);
		males.setFemaleIncluded(false);
		
		GenderCohortDefinition females = new GenderCohortDefinition();
		females.setName("female Patients");
		females.setMaleIncluded(false);
		females.setFemaleIncluded(true);
		
		PersonAttributeCohortDefinition hydrocephalusPatientCohortDefinition = new PersonAttributeCohortDefinition();
		hydrocephalusPatientCohortDefinition.setAttributeType(registrationDiagnosis);
		List<Concept> hydrocephalusHCConcepts = new ArrayList<Concept>();
		hydrocephalusHCConcepts.add(hydrocephalusHC);
		hydrocephalusPatientCohortDefinition.setValueConcepts(hydrocephalusHCConcepts);
		
		CompositionCohortDefinition hydrocephalusPatientsWithAnyVisitsBetweenDates = new CompositionCohortDefinition();
		hydrocephalusPatientsWithAnyVisitsBetweenDates.setName("hydrocephalusPatientsWithAnyVisitsBetweenDates");
		hydrocephalusPatientsWithAnyVisitsBetweenDates.addParameter(new Parameter("createdOnOrAfter", "Created on or after", Date.class));
		hydrocephalusPatientsWithAnyVisitsBetweenDates.addParameter(new Parameter("createdOnOrBefore", "Created on or before", Date.class));
		hydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAnyVisitBetweenDates, ParameterizableUtil.createParameterMappings("createdOnOrAfter=${createdOnOrAfter},createdOnOrBefore=${createdOnOrBefore}")));
		
		hydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("2", new Mapped<CohortDefinition>(hydrocephalusPatientCohortDefinition, null));
		hydrocephalusPatientsWithAnyVisitsBetweenDates.setCompositionString("1 and 2");
		
		CohortIndicator hydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator = Indicators.newCohortIndicator("hydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator", hydrocephalusPatientsWithAnyVisitsBetweenDates, ParameterizableUtil.createParameterMappings("createdOnOrAfter=${startDate},createdOnOrBefore=${endDate}"));
		
		dsd.addColumn("hydrocephalusPatients", "hydrocephalusHC", new Mapped<CohortIndicator>(hydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")), "");
		
		CompositionCohortDefinition maleHydrocephalusPatientsWithAnyVisitsBetweenDates = new CompositionCohortDefinition();
		maleHydrocephalusPatientsWithAnyVisitsBetweenDates.setName("maleHydrocephalusPatientsWithAnyVisitsBetweenDates");
		maleHydrocephalusPatientsWithAnyVisitsBetweenDates.addParameter(new Parameter("createdOnOrAfter", "Created on or after", Date.class));
		maleHydrocephalusPatientsWithAnyVisitsBetweenDates.addParameter(new Parameter("createdOnOrBefore", "Created on or before", Date.class));
		maleHydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAnyVisitBetweenDates, ParameterizableUtil.createParameterMappings("createdOnOrAfter=${createdOnOrAfter},createdOnOrBefore=${createdOnOrBefore}")));
		
		maleHydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("2", new Mapped<CohortDefinition>(hydrocephalusPatientCohortDefinition, null));
		maleHydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
		maleHydrocephalusPatientsWithAnyVisitsBetweenDates.setCompositionString("1 and 2 and 3");
		
		CohortIndicator maleHydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator = Indicators.newCohortIndicator("maleHydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator", maleHydrocephalusPatientsWithAnyVisitsBetweenDates, ParameterizableUtil.createParameterMappings("createdOnOrAfter=${startDate},createdOnOrBefore=${endDate}"));
		
		dsd.addColumn("maleHydrocephalusPatients", "maleHydrocephalusHC", new Mapped<CohortIndicator>(maleHydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")), "");
		
		CompositionCohortDefinition femaleHydrocephalusPatientsWithAnyVisitsBetweenDates = new CompositionCohortDefinition();
		femaleHydrocephalusPatientsWithAnyVisitsBetweenDates.setName("femaleHydrocephalusPatientsWithAnyVisitsBetweenDates");
		femaleHydrocephalusPatientsWithAnyVisitsBetweenDates.addParameter(new Parameter("createdOnOrAfter", "Created on or after", Date.class));
		femaleHydrocephalusPatientsWithAnyVisitsBetweenDates.addParameter(new Parameter("createdOnOrBefore", "Created on or before", Date.class));
		femaleHydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAnyVisitBetweenDates, ParameterizableUtil.createParameterMappings("createdOnOrAfter=${createdOnOrAfter},createdOnOrBefore=${createdOnOrBefore}")));
		
		femaleHydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("2", new Mapped<CohortDefinition>(hydrocephalusPatientCohortDefinition, null));
		femaleHydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("3", new Mapped<CohortDefinition>(females, null));
		femaleHydrocephalusPatientsWithAnyVisitsBetweenDates.setCompositionString("1 and 2 and 3");
		
		CohortIndicator femaleHydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator = Indicators.newCohortIndicator("femaleHydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator", femaleHydrocephalusPatientsWithAnyVisitsBetweenDates, ParameterizableUtil.createParameterMappings("createdOnOrAfter=${startDate},createdOnOrBefore=${endDate}"));
		
		dsd.addColumn("femaleHydrocephalusPatients", "femaleHydrocephalusHC", new Mapped<CohortIndicator>(femaleHydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")), "");
		
		CompositionCohortDefinition unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates = new CompositionCohortDefinition();
		unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates.setName("unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates");
		unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates.addParameter(new Parameter("createdOnOrAfter", "Created on or after", Date.class));
		unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates.addParameter(new Parameter("createdOnOrBefore", "Created on or before", Date.class));
		unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("1", new Mapped<CohortDefinition>(patientsWithAnyVisitBetweenDates, ParameterizableUtil.createParameterMappings("createdOnOrAfter=${createdOnOrAfter},createdOnOrBefore=${createdOnOrBefore}")));
		
		unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("2", new Mapped<CohortDefinition>(hydrocephalusPatientCohortDefinition, null));
		unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("3", new Mapped<CohortDefinition>(males, null));
		unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates.getSearches().put("4", new Mapped<CohortDefinition>(females, null));
		unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates.setCompositionString("1 and 2 and ( not 3) and ( not 4)");
		
		CohortIndicator unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator = Indicators.newCohortIndicator("unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator", unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDates, ParameterizableUtil.createParameterMappings("createdOnOrAfter=${startDate},createdOnOrBefore=${endDate}"));
		
		dsd.addColumn("unknownGenderHydrocephalusPatients", "unknownGenderHydrocephalusPatients", new Mapped<CohortIndicator>(unknownGenderHydrocephalusPatientsWithAnyVisitsBetweenDatesIndicator, ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}")), "");
		
	}
	
	private void setUpProperties() {
		allVisitTypes = Context.getVisitService().getAllVisitTypes(false);
		
		registrationDiagnosis = Context.getPersonService().getPersonAttributeTypeByUuid("537f22bb-8b4e-4d51-9f54-d3b315a1a2d2");
		hydrocephalusHC = Context.getConceptService().getConceptByUuid("4cfe7a53-e23d-48fd-9e4d-63b30639c5a7");
		
	}
	
}
