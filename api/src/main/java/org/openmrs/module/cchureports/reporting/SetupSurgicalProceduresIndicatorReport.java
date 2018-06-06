/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.cchureports.reporting;

import java.util.Date;
import java.util.Properties;

import org.openmrs.Concept;
import org.openmrs.api.context.Context;
import org.openmrs.module.cchureports.util.GlobalPropertiesManagement;
import org.openmrs.module.cchureports.util.MetadataLookup;
import org.openmrs.module.reporting.cohort.definition.BaseObsCohortDefinition.TimeModifier;
import org.openmrs.module.reporting.cohort.definition.CodedObsCohortDefinition;
import org.openmrs.module.reporting.dataset.definition.SimpleIndicatorDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.evaluation.parameter.ParameterizableUtil;
import org.openmrs.module.reporting.indicator.QueryCountIndicator;
import org.openmrs.module.reporting.query.obs.definition.SqlObsQuery;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.openmrs.module.reporting.report.service.ReportService;

public class SetupSurgicalProceduresIndicatorReport {
	
	GlobalPropertiesManagement gp = new GlobalPropertiesManagement();
	
	// properties
	
	private Concept SURGICAL_PROCEDURE;
	
	private Concept ASPIRATION_IRRIGATION;
	
	private Concept BONE_SHAVING;
	
	private Concept BURR_HOLE_RESERVOIR_PLACEMENT;
	
	private Concept BURR_HOLE_S;
	
	private Concept CHEST_TUBE_DRAINAGE_PLEURAL_EFFUSION;
	
	private Concept CIRCUMCISION;
	
	private Concept CLEFT_LIP_AND_PALATE_REPAIR;
	
	private Concept CLEFT_LIP_REPAIR;
	
	private Concept CLEFT_PALATE_REPAIR;
	
	private Concept CPC;
	
	private Concept CPC_RESERVOIR_PLACEMENT;
	
	private Concept CPC_VPS;
	
	private Concept CRANIECTOMY;
	
	private Concept CRANIOFACIAL_RECONSTRUCTION;
	
	private Concept CRANIOPLASTY;
	
	private Concept CRANIOTOMY_ANEURYSM;
	
	private Concept CRANIOTOMY_DEPRESSED_SKULL_FRACTURE;
	
	private Concept CRANIOTOMY_ECR;
	
	private Concept CRANIOTOMY_EDH;
	
	private Concept CRANIOTOMY_EPILEPSY;
	
	private Concept CRANIOTOMY_GROWING_FRACTURE;
	
	private Concept CRANIOTOMY_OTHER;
	
	private Concept CRANIOTOMY_SDH;
	
	private Concept CRANIOTOMY_ACF_REPAIR;
	
	private Concept CRANIOTOMY_HAEMATOMA;
	
	private Concept CRANIOTOMY_SUBDURAL_EMPYEMA;
	
	private Concept CRANIOTOMY_TUMOR;
	
	private Concept CSF_LEAK_REPAIR;
	
	private Concept CYSTO_PERITONEAL_SHUNT;
	
	private Concept DEBRID_CLOSURE;
	
	private Concept DEBRIDEMENT;
	
	private Concept DECOMPRESSIVE_HEMICRANIECTOMY;
	
	private Concept DISARTICULATION;
	
	private Concept DISKECTOMY;
	
	private Concept DURA_EXPANSION;
	
	private Concept DURAPLASTY;
	
	private Concept EC_REPAIR_OCCIPITAL;
	
	private Concept EC_VERTEX;
	
	private Concept ECR_FRONTAL_NASAL;
	
	private Concept ECR_FRONTAL_TEMPORAL;
	
	private Concept ECR_OCCIPITAL;
	
	private Concept ECR_PARIETAL;
	
	private Concept ENCEPHALOCELE_EXCISION;
	
	private Concept ENDOSCOPIC_TRANS_SPHENOIDAL_SURGERY_ETSS;
	
	private Concept ETV;
	
	private Concept ETV_PLUS_BIOPSY;
	
	private Concept ETV_REDO;
	
	private Concept ETV_REDO_VPS_PLACEMENT;
	
	private Concept ETV_AQUEDUCTOPLASTY;
	
	private Concept ETV_CPC;
	
	private Concept ETV_CPC_REDO;
	
	private Concept ETV_CPC_RESERVOIR_PLACEMENT;
	
	private Concept ETV_CPC_VPS_PLACEMENT;
	
	private Concept ETV_RESERVOIR_PLACEMENT;
	
	private Concept ETV_RESERVOIR_REMOVAL;
	
	private Concept ETV_SEPTOSTOMY;
	
	private Concept ETV_VPS_PLACEMENT;
	
	private Concept ETV_VPS_REMOVAL;
	
	private Concept EUA_BIOPSY;
	
	private Concept EVD_PLACEMENT;
	
	private Concept EXCISION;
	
	private Concept EXCISION_SCALP_MASS;
	
	private Concept EXCISION_SINUS_TRACT_SPINAL;
	
	private Concept EXCISION_SINUS_TRACT_CERVICAL;
	
	private Concept EXCISION_SKULL_MASS;
	
	private Concept EXCISION_SPINAL_MASS;
	
	private Concept EXCISIONAL_BIOPSY;
	
	private Concept EXPLORATION_SCALP_MASS;
	
	private Concept EXTERNAL_DRAINAGE_SYSTEM;
	
	private Concept HERNIOTOMY;
	
	private Concept ICP_MEASUREMENT;
	
	private Concept IMPLANT_REMOVAL;
	
	private Concept INCISION_AND_DRAINAGE;
	
	private Concept INCISIONAL_BIOPSY_SCALP_MASS;
	
	private Concept INCISIONAL_BIOPSY_TUMOR;
	
	private Concept INTERBODY_FUSSION;
	
	private Concept LAMINECTOMIES_SBO;
	
	private Concept LAMINECTOMIES_CERVICAL;
	
	private Concept LAMINECTOMIES_CHIARI_DECOMPRESSION;
	
	private Concept LAMINECTOMIES_LUMBAR;
	
	private Concept LAMINECTOMIES_SACRAL;
	
	private Concept LAMINECTOMIES_THORACIC;
	
	private Concept LAMINECTOMIES_THORACO_LUMBAR;
	
	private Concept LAMINECTOMIES_TUMOR;
	
	private Concept LAMINECTOMY;
	
	private Concept LAMINECTOMY_PLUS_FUSSION;
	
	private Concept LAMINOTOMY;
	
	private Concept LUMBOPERITONEAL_SHUNT;
	
	private Concept LYMPHNODE_BIOPSY;
	
	private Concept MENINGOCELE_REPAIR;
	
	private Concept MM_CLOSURE;
	
	private Concept MMC_CLOSURE;
	
	private Concept MMC_KYPHECTOMY;
	
	private Concept PDR;
	
	private Concept RE_DO_CRANIOTOMY_TUMOR_RESECTION;
	
	private Concept RESERVOIR_SUBDURAL;
	
	private Concept RESERVOIR_PLACEMENT;
	
	private Concept RESERVOIR_PLACEMENT_VPS_REMOVAL;
	
	private Concept RESERVOIR_REMOVAL;
	
	private Concept RESERVOIR_REMOVAL_REPLACEMENT;
	
	private Concept RESERVOIR_REVISION;
	
	private Concept ROD_REMOVAL;
	
	private Concept SD_P_SHUNT_PLACEMENT;
	
	private Concept SD_P_SHUNT_REMOVAL_ETV;
	
	private Concept SD_P_SHUNT_REMOVAL_ETV_CPC;
	
	private Concept SD_P_SHUNT_REMOVAL_VPS_PLACEMENT;
	
	private Concept SD_P_SHUNT_REVISION;
	
	private Concept SEPARATION_OF_CO_JOINT_TWINS;
	
	private Concept SKIN_GRAFT;
	
	private Concept SPINAL_CORD_UNTETHERING_MM;
	
	private Concept SPINAL_CORD_UNTETHERING_SBO;
	
	private Concept SPINAL_FUSION;
	
	private Concept SPINE_STABILISATION;
	
	private Concept SUTURE_REMOVAL;
	
	private Concept TARSORRHAPHY;
	
	private Concept TRACHEOSTOMY;
	
	private Concept TUMOR_BIOPSY;
	
	private Concept VENTRICULO_ATRIAL_SHUNT_PLACEMENT;
	
	private Concept VENTRICULO_PLEURAL_SHUNT_PLACEMENT;
	
	private Concept VENTRICULOSCOPY_AQUEDUCTOPLASTY;
	
	private Concept VENTRICULOSCOPY_CYST;
	
	private Concept VENTRICULOSCOPY_FENESTRATION_OF_MEMBRANES;
	
	private Concept VENTRICULOSCOPY_RESERVOIR_PLACEMENT;
	
	private Concept VENTRICULOSCOPY_SEPTOSTOMY;
	
	private Concept VENTRICULOSCOPY_TUMOR;
	
	private Concept VENTRICULOSCOPY_VENT_CATH_REMOVAL;
	
	private Concept VENTRICULOSCOPY_VPS_PLACEMENT;
	
	private Concept VENTRICULOSCOPY_VPS_REVISION;
	
	private Concept VPS_EXTERNALIZATION;
	
	private Concept VPS_INTERNALIZATION;
	
	private Concept VPS_PLACEMENT;
	
	private Concept VPS_PLACEMENT_RESERVOIR_REMOVAL;
	
	private Concept VPS_REMOVAL;
	
	private Concept VPS_REMOVAL_RESERVOIR_PLACEMENT;
	
	private Concept VPS_REPLACEMENT;
	
	private Concept VPS_REPLACEMENT_RESERVOIR_REMOVAL;
	
	private Concept VPS_REVISION;
	
	private Concept VPS_TIE_OFF;
	
	private Concept VSSS_PLACEMENT;
	
	private Concept VSSS_REMOVAL_VPS_PLACEMENT;
	
	private Concept VSSS_REVISION;
	
	private Concept WOUND_CLOSURE;
	
	private Concept WOUND_REVISION_MM;
	
	private Concept WOUND_REVISION_OTHER;
	
	public void setup() throws Exception {
		
		setUpProperties();
		
		//Report set-up
		ReportDefinition reportDefinition = new ReportDefinition();
		reportDefinition.setName("Surgical Procedures Indicator Report");
		reportDefinition.addParameter(new Parameter("startDate", "Start Date", Date.class));
		reportDefinition.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		reportDefinition.addDataSetDefinition(createObsDataSet(), ParameterizableUtil.createParameterMappings("startDate=${startDate},endDate=${endDate}"));
		
		CodedObsCohortDefinition patientsWithSurgicalProcedureObservation = new CodedObsCohortDefinition();
		patientsWithSurgicalProcedureObservation.setTimeModifier(TimeModifier.ANY);
		patientsWithSurgicalProcedureObservation.setQuestion(SURGICAL_PROCEDURE);
		patientsWithSurgicalProcedureObservation.addParameter(new Parameter("onOrAfter", "On or After", Date.class));
		patientsWithSurgicalProcedureObservation.addParameter(new Parameter("onOrBefore", "On or Before", Date.class));
		
		reportDefinition.setBaseCohortDefinition(patientsWithSurgicalProcedureObservation, ParameterizableUtil.createParameterMappings("onOrAfter=${startDate},onOrBefore=${endDate}"));
		
		Helper.saveReportDefinition(reportDefinition);
		
		ReportDesign monthlyDesign = Helper.createRowPerPatientXlsOverviewReportDesign(reportDefinition, "Surgical_Procedures_Indicator_Report.xls", "Surgical Procedures Indicator Report", null);
		Properties monthlyProps = new Properties();
		monthlyProps.put("repeatingSections", "sheet:1,dataset:Obs Data Set");
		monthlyProps.put("sortWeight", "5000");
		monthlyDesign.setProperties(monthlyProps);
		Helper.saveReportDesign(monthlyDesign);
		
	}
	
	public void delete() {
		ReportService rs = Context.getService(ReportService.class);
		for (ReportDesign rd : rs.getAllReportDesigns(false)) {
			if ("Hypertension Monthly Indicator Report (Excel)".equals(rd.getName())) {
				rs.purgeReportDesign(rd);
			}
		}
		Helper.purgeReportDefinition("Surgical Procedures Indicator Report");
		
	}
	
	// Create Obs Data set
	
	private SimpleIndicatorDataSetDefinition createObsDataSet() {
		SimpleIndicatorDataSetDefinition dsd = new SimpleIndicatorDataSetDefinition();
		dsd.setName("Obs Data Set");
		dsd.addParameter(new Parameter("startDate", "Start Date", Date.class));
		dsd.addParameter(new Parameter("endDate", "End Date", Date.class));
		
		createIndicators(dsd);
		return dsd;
	}
	
	private void createIndicators(SimpleIndicatorDataSetDefinition dsd) {
		
		dsd.addColumn("1", "Total # of " + ASPIRATION_IRRIGATION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ASPIRATION_IRRIGATION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("2", "Total # of " + BONE_SHAVING.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(BONE_SHAVING), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("3", "Total # of " + BURR_HOLE_RESERVOIR_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(BURR_HOLE_RESERVOIR_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("4", "Total # of " + BURR_HOLE_S.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(BURR_HOLE_S), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("5", "Total # of " + CHEST_TUBE_DRAINAGE_PLEURAL_EFFUSION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CHEST_TUBE_DRAINAGE_PLEURAL_EFFUSION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("6", "Total # of " + CIRCUMCISION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CIRCUMCISION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("7", "Total # of " + CLEFT_LIP_AND_PALATE_REPAIR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CLEFT_LIP_AND_PALATE_REPAIR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("8", "Total # of " + CLEFT_LIP_REPAIR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CLEFT_LIP_REPAIR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("9", "Total # of " + CLEFT_PALATE_REPAIR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CLEFT_PALATE_REPAIR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("10", "Total # of " + CPC.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CPC), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("11", "Total # of " + CPC_RESERVOIR_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CPC_RESERVOIR_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("12", "Total # of " + CPC_VPS.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CPC_VPS), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("13", "Total # of " + CRANIECTOMY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIECTOMY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("14", "Total # of " + CRANIOFACIAL_RECONSTRUCTION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOFACIAL_RECONSTRUCTION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("15", "Total # of " + CRANIOPLASTY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOPLASTY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("16", "Total # of " + CRANIOTOMY_ANEURYSM.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_ANEURYSM), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("17", "Total # of " + CRANIOTOMY_DEPRESSED_SKULL_FRACTURE.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_DEPRESSED_SKULL_FRACTURE), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("18", "Total # of " + CRANIOTOMY_ECR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_ECR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("19", "Total # of " + CRANIOTOMY_EDH.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_EDH), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("20", "Total # of " + CRANIOTOMY_EPILEPSY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_EPILEPSY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("21", "Total # of " + CRANIOTOMY_GROWING_FRACTURE.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_GROWING_FRACTURE), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("22", "Total # of " + CRANIOTOMY_OTHER.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_OTHER), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("23", "Total # of " + CRANIOTOMY_SDH.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_SDH), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("24", "Total # of " + CRANIOTOMY_ACF_REPAIR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_ACF_REPAIR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("25", "Total # of " + CRANIOTOMY_HAEMATOMA.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_HAEMATOMA), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("26", "Total # of " + CRANIOTOMY_SUBDURAL_EMPYEMA.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_SUBDURAL_EMPYEMA), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("27", "Total # of " + CRANIOTOMY_TUMOR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CRANIOTOMY_TUMOR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("28", "Total # of " + CSF_LEAK_REPAIR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CSF_LEAK_REPAIR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("29", "Total # of " + CYSTO_PERITONEAL_SHUNT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(CYSTO_PERITONEAL_SHUNT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("30", "Total # of " + DEBRID_CLOSURE.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(DEBRID_CLOSURE), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("31", "Total # of " + DEBRIDEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(DEBRIDEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("32", "Total # of " + DECOMPRESSIVE_HEMICRANIECTOMY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(DECOMPRESSIVE_HEMICRANIECTOMY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("33", "Total # of " + DISARTICULATION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(DISARTICULATION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("34", "Total # of " + DISKECTOMY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(DISKECTOMY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("35", "Total # of " + DURA_EXPANSION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(DURA_EXPANSION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("36", "Total # of " + DURAPLASTY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(DURAPLASTY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("37", "Total # of " + EC_REPAIR_OCCIPITAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EC_REPAIR_OCCIPITAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("38", "Total # of " + EC_VERTEX.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EC_VERTEX), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("39", "Total # of " + ECR_FRONTAL_NASAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ECR_FRONTAL_NASAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("40", "Total # of " + ECR_FRONTAL_TEMPORAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ECR_FRONTAL_TEMPORAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("41", "Total # of " + ECR_OCCIPITAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ECR_OCCIPITAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("42", "Total # of " + ECR_PARIETAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ECR_PARIETAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("43", "Total # of " + ENCEPHALOCELE_EXCISION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ENCEPHALOCELE_EXCISION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("44", "Total # of " + ENDOSCOPIC_TRANS_SPHENOIDAL_SURGERY_ETSS.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ENDOSCOPIC_TRANS_SPHENOIDAL_SURGERY_ETSS), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("45", "Total # of " + ETV.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("46", "Total # of " + ETV_PLUS_BIOPSY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_PLUS_BIOPSY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("47", "Total # of " + ETV_REDO.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_REDO), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("48", "Total # of " + ETV_REDO_VPS_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_REDO_VPS_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("49", "Total # of " + ETV_AQUEDUCTOPLASTY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_AQUEDUCTOPLASTY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("50", "Total # of " + ETV_CPC.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_CPC), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("51", "Total # of " + ETV_CPC_REDO.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_CPC_REDO), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("52", "Total # of " + ETV_CPC_RESERVOIR_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_CPC_RESERVOIR_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("53", "Total # of " + ETV_CPC_VPS_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_CPC_VPS_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("54", "Total # of " + ETV_RESERVOIR_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_RESERVOIR_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("55", "Total # of " + ETV_RESERVOIR_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_RESERVOIR_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("56", "Total # of " + ETV_SEPTOSTOMY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_SEPTOSTOMY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("57", "Total # of " + ETV_VPS_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_VPS_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("58", "Total # of " + ETV_VPS_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ETV_VPS_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("59", "Total # of " + EUA_BIOPSY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EUA_BIOPSY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("60", "Total # of " + EVD_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EVD_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("61", "Total # of " + EXCISION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EXCISION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("62", "Total # of " + EXCISION_SCALP_MASS.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EXCISION_SCALP_MASS), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("63", "Total # of " + EXCISION_SINUS_TRACT_SPINAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EXCISION_SINUS_TRACT_SPINAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("64", "Total # of " + EXCISION_SINUS_TRACT_CERVICAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EXCISION_SINUS_TRACT_CERVICAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("65", "Total # of " + EXCISION_SKULL_MASS.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EXCISION_SKULL_MASS), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("66", "Total # of " + EXCISION_SPINAL_MASS.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EXCISION_SPINAL_MASS), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("67", "Total # of " + EXCISIONAL_BIOPSY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EXCISIONAL_BIOPSY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("68", "Total # of " + EXPLORATION_SCALP_MASS.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EXPLORATION_SCALP_MASS), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("69", "Total # of " + EXTERNAL_DRAINAGE_SYSTEM.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(EXTERNAL_DRAINAGE_SYSTEM), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("70", "Total # of " + HERNIOTOMY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(HERNIOTOMY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("71", "Total # of " + ICP_MEASUREMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ICP_MEASUREMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("72", "Total # of " + IMPLANT_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(IMPLANT_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("73", "Total # of " + INCISION_AND_DRAINAGE.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(INCISION_AND_DRAINAGE), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("74", "Total # of " + INCISIONAL_BIOPSY_SCALP_MASS.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(INCISIONAL_BIOPSY_SCALP_MASS), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("75", "Total # of " + INCISIONAL_BIOPSY_TUMOR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(INCISIONAL_BIOPSY_TUMOR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("76", "Total # of " + INTERBODY_FUSSION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(INTERBODY_FUSSION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("77", "Total # of " + LAMINECTOMIES_SBO.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINECTOMIES_SBO), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("78", "Total # of " + LAMINECTOMIES_CERVICAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINECTOMIES_CERVICAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("79", "Total # of " + LAMINECTOMIES_CHIARI_DECOMPRESSION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINECTOMIES_CHIARI_DECOMPRESSION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("80", "Total # of " + LAMINECTOMIES_LUMBAR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINECTOMIES_LUMBAR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("81", "Total # of " + LAMINECTOMIES_SACRAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINECTOMIES_SACRAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("82", "Total # of " + LAMINECTOMIES_THORACIC.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINECTOMIES_THORACIC), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("83", "Total # of " + LAMINECTOMIES_THORACO_LUMBAR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINECTOMIES_THORACO_LUMBAR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("84", "Total # of " + LAMINECTOMIES_TUMOR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINECTOMIES_TUMOR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("85", "Total # of " + LAMINECTOMY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINECTOMY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("86", "Total # of " + LAMINECTOMY_PLUS_FUSSION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINECTOMY_PLUS_FUSSION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("87", "Total # of " + LAMINOTOMY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LAMINOTOMY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("88", "Total # of " + LUMBOPERITONEAL_SHUNT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LUMBOPERITONEAL_SHUNT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("89", "Total # of " + LYMPHNODE_BIOPSY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(LYMPHNODE_BIOPSY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("90", "Total # of " + MENINGOCELE_REPAIR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(MENINGOCELE_REPAIR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("91", "Total # of " + MM_CLOSURE.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(MM_CLOSURE), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("92", "Total # of " + MMC_CLOSURE.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(MMC_CLOSURE), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("93", "Total # of " + MMC_KYPHECTOMY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(MMC_KYPHECTOMY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("94", "Total # of " + PDR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(PDR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("95", "Total # of " + RE_DO_CRANIOTOMY_TUMOR_RESECTION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(RE_DO_CRANIOTOMY_TUMOR_RESECTION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("96", "Total # of " + RESERVOIR_SUBDURAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(RESERVOIR_SUBDURAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("97", "Total # of " + RESERVOIR_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(RESERVOIR_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("98", "Total # of " + RESERVOIR_PLACEMENT_VPS_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(RESERVOIR_PLACEMENT_VPS_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("99", "Total # of " + RESERVOIR_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(RESERVOIR_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("100", "Total # of " + RESERVOIR_REMOVAL_REPLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(RESERVOIR_REMOVAL_REPLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("101", "Total # of " + RESERVOIR_REVISION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(RESERVOIR_REVISION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("102", "Total # of " + ROD_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(ROD_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("103", "Total # of " + SD_P_SHUNT_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SD_P_SHUNT_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("104", "Total # of " + SD_P_SHUNT_REMOVAL_ETV.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SD_P_SHUNT_REMOVAL_ETV), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("105", "Total # of " + SD_P_SHUNT_REMOVAL_ETV_CPC.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SD_P_SHUNT_REMOVAL_ETV_CPC), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("106", "Total # of " + SD_P_SHUNT_REMOVAL_VPS_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SD_P_SHUNT_REMOVAL_VPS_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("107", "Total # of " + SD_P_SHUNT_REVISION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SD_P_SHUNT_REVISION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("108", "Total # of " + SEPARATION_OF_CO_JOINT_TWINS.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SEPARATION_OF_CO_JOINT_TWINS), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("109", "Total # of " + SKIN_GRAFT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SKIN_GRAFT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("110", "Total # of " + SPINAL_CORD_UNTETHERING_MM.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SPINAL_CORD_UNTETHERING_MM), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("111", "Total # of " + SPINAL_CORD_UNTETHERING_SBO.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SPINAL_CORD_UNTETHERING_SBO), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("112", "Total # of " + SPINAL_FUSION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SPINAL_FUSION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("113", "Total # of " + SPINE_STABILISATION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SPINE_STABILISATION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("114", "Total # of " + SUTURE_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(SUTURE_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("115", "Total # of " + TARSORRHAPHY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(TARSORRHAPHY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("116", "Total # of " + TRACHEOSTOMY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(TRACHEOSTOMY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("117", "Total # of " + TUMOR_BIOPSY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(TUMOR_BIOPSY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("118", "Total # of " + VENTRICULO_ATRIAL_SHUNT_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULO_ATRIAL_SHUNT_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("119", "Total # of " + VENTRICULO_PLEURAL_SHUNT_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULO_PLEURAL_SHUNT_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("120", "Total # of " + VENTRICULOSCOPY_AQUEDUCTOPLASTY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULOSCOPY_AQUEDUCTOPLASTY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("121", "Total # of " + VENTRICULOSCOPY_CYST.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULOSCOPY_CYST), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("122", "Total # of " + VENTRICULOSCOPY_FENESTRATION_OF_MEMBRANES.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULOSCOPY_FENESTRATION_OF_MEMBRANES), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("123", "Total # of " + VENTRICULOSCOPY_RESERVOIR_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULOSCOPY_RESERVOIR_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("124", "Total # of " + VENTRICULOSCOPY_SEPTOSTOMY.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULOSCOPY_SEPTOSTOMY), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("125", "Total # of " + VENTRICULOSCOPY_TUMOR.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULOSCOPY_TUMOR), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("126", "Total # of " + VENTRICULOSCOPY_VENT_CATH_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULOSCOPY_VENT_CATH_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("127", "Total # of " + VENTRICULOSCOPY_VPS_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULOSCOPY_VPS_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("128", "Total # of " + VENTRICULOSCOPY_VPS_REVISION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VENTRICULOSCOPY_VPS_REVISION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("129", "Total # of " + VPS_EXTERNALIZATION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VPS_EXTERNALIZATION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("130", "Total # of " + VPS_INTERNALIZATION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VPS_INTERNALIZATION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("131", "Total # of " + VPS_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VPS_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("132", "Total # of " + VPS_PLACEMENT_RESERVOIR_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VPS_PLACEMENT_RESERVOIR_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("133", "Total # of " + VPS_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VPS_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("134", "Total # of " + VPS_REMOVAL_RESERVOIR_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VPS_REMOVAL_RESERVOIR_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("135", "Total # of " + VPS_REPLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VPS_REPLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("136", "Total # of " + VPS_REPLACEMENT_RESERVOIR_REMOVAL.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VPS_REPLACEMENT_RESERVOIR_REMOVAL), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("137", "Total # of " + VPS_REVISION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VPS_REVISION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("138", "Total # of " + VPS_TIE_OFF.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VPS_TIE_OFF), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("139", "Total # of " + VSSS_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VSSS_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("140", "Total # of " + VSSS_REMOVAL_VPS_PLACEMENT.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VSSS_REMOVAL_VPS_PLACEMENT), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("141", "Total # of " + VSSS_REVISION.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(VSSS_REVISION), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("142", "Total # of " + WOUND_CLOSURE.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(WOUND_CLOSURE), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("143", "Total # of " + WOUND_REVISION_MM.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(WOUND_REVISION_MM), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		dsd.addColumn("144", "Total # of " + WOUND_REVISION_OTHER.getDisplayString() + " Procedures", new Mapped<QueryCountIndicator>(addIndicator(WOUND_REVISION_OTHER), ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		
	}
	
	private QueryCountIndicator addIndicator(Concept concept) {
		
		SqlObsQuery etvcpcObs = new SqlObsQuery();
		etvcpcObs.setQuery("select obs_id from obs where value_coded =" + concept.getConceptId() + " and obs_datetime>= :startDate and obs_datetime<= :endDate and voided=0");
		etvcpcObs.setName("etvcpcObs" + concept.getDisplayString());
		etvcpcObs.addParameter(new Parameter("startDate", "startDate", Date.class));
		etvcpcObs.addParameter(new Parameter("endDate", "endDate", Date.class));
		
		QueryCountIndicator ci = new QueryCountIndicator();
		ci.addParameter(new Parameter("startDate", "Start date", Date.class));
		ci.addParameter(new Parameter("endDate", "End date", Date.class));
		ci.setQuery(new Mapped<SqlObsQuery>(etvcpcObs, ParameterizableUtil.createParameterMappings("endDate=${endDate},startDate=${startDate}")));
		
		return ci;
	};
	
	private void setUpProperties() {
		SURGICAL_PROCEDURE = MetadataLookup.getConcept("638b0acc-7c7d-4c2d-9685-341ba937070f");
		ASPIRATION_IRRIGATION = MetadataLookup.getConcept("3d4f8ffb-ac3c-4917-965d-2dd7ca4b4e32");
		BONE_SHAVING = MetadataLookup.getConcept("2b918818-19e6-44f7-8081-418dfe0813d4");
		BURR_HOLE_RESERVOIR_PLACEMENT = MetadataLookup.getConcept("6a3bf9bd-ebed-44e2-b3c0-05830221952b");
		BURR_HOLE_S = MetadataLookup.getConcept("b3352194-e7b0-4e55-b64c-cdf9e7660f37");
		CHEST_TUBE_DRAINAGE_PLEURAL_EFFUSION = MetadataLookup.getConcept("6f6c103d-9e02-4e01-b1fb-401e9b4f4414");
		CIRCUMCISION = MetadataLookup.getConcept("51ced7d0-9059-4872-a494-9a334d0ad658");
		CLEFT_LIP_AND_PALATE_REPAIR = MetadataLookup.getConcept("c570c268-a8e0-477b-8da0-fea08872a954");
		CLEFT_LIP_REPAIR = MetadataLookup.getConcept("a3e17286-65e1-4ab6-acf7-d6596b290899");
		CLEFT_PALATE_REPAIR = MetadataLookup.getConcept("cf02b467-9cff-4347-a5b2-21d708bd47da");
		CPC = MetadataLookup.getConcept("2610cda0-3a3b-473e-aca2-1bb9501cf331");
		CPC_RESERVOIR_PLACEMENT = MetadataLookup.getConcept("7191915e-08c4-46ad-a15a-82ed8b15b30d");
		CPC_VPS = MetadataLookup.getConcept("563846e6-9fd0-4894-927f-33c88b1f5195");
		CRANIECTOMY = MetadataLookup.getConcept("dd3a1b87-4d93-4c5a-828d-dbe94d879dae");
		CRANIOFACIAL_RECONSTRUCTION = MetadataLookup.getConcept("0e55ebab-f8d0-4159-a901-63254302297e");
		CRANIOPLASTY = MetadataLookup.getConcept("83677896-98d6-4044-9565-bdee63eed12b");
		CRANIOTOMY_ANEURYSM = MetadataLookup.getConcept("5ac4c73e-5f16-4967-9e45-8cbd1993f869");
		CRANIOTOMY_DEPRESSED_SKULL_FRACTURE = MetadataLookup.getConcept("f7446189-0e72-43ee-97b2-e14e308193a8");
		CRANIOTOMY_ECR = MetadataLookup.getConcept("ad7cb829-54bf-4548-8de8-c2f6666b10bd");
		CRANIOTOMY_EDH = MetadataLookup.getConcept("294a90ba-9159-4533-ad4a-37fe26dc5616");
		CRANIOTOMY_EPILEPSY = MetadataLookup.getConcept("91058739-f6c1-4f36-bfb1-886b2321d151");
		CRANIOTOMY_GROWING_FRACTURE = MetadataLookup.getConcept("65716368-7723-4390-bea0-61b785270fbd");
		CRANIOTOMY_OTHER = MetadataLookup.getConcept("d72b1a42-7b44-4fdb-92a8-8a42743f1e4f");
		CRANIOTOMY_SDH = MetadataLookup.getConcept("0016a399-564b-4903-b9cd-e9ed8a4aa881");
		CRANIOTOMY_ACF_REPAIR = MetadataLookup.getConcept("643360c4-24cf-4871-a6d0-1fe457c6a697");
		CRANIOTOMY_HAEMATOMA = MetadataLookup.getConcept("4d6aff35-3612-45a9-8908-e70cd86e10ba");
		CRANIOTOMY_SUBDURAL_EMPYEMA = MetadataLookup.getConcept("e72af1eb-da24-4a26-b52c-eca72c4a04c9");
		CRANIOTOMY_TUMOR = MetadataLookup.getConcept("e7299850-1acc-4d8a-b656-272fbdf0eae2");
		CSF_LEAK_REPAIR = MetadataLookup.getConcept("259f387b-98a6-4d90-9231-d66fbb052a91");
		CYSTO_PERITONEAL_SHUNT = MetadataLookup.getConcept("7566e858-8854-4e3c-8d73-8d99e8ffc264");
		DEBRID_CLOSURE = MetadataLookup.getConcept("356fb4c8-f381-4b70-80bf-3820b071b38e");
		DEBRIDEMENT = MetadataLookup.getConcept("d20e9838-a433-4316-b273-9bab153211cc");
		DECOMPRESSIVE_HEMICRANIECTOMY = MetadataLookup.getConcept("a3b9878f-7c45-480f-9589-39a6e54b4f0d");
		DISARTICULATION = MetadataLookup.getConcept("ff43cfd6-554c-47d8-b1b1-25ac7b58b04a");
		DISKECTOMY = MetadataLookup.getConcept("810c1e58-095f-4e3d-ba3d-fe776039b626");
		DURA_EXPANSION = MetadataLookup.getConcept("bd51b567-ca66-4a1a-96a3-f6ecd0a2a359");
		DURAPLASTY = MetadataLookup.getConcept("d919091f-9938-4eba-adb9-dad79c1e9cc5");
		EC_REPAIR_OCCIPITAL = MetadataLookup.getConcept("0c127820-f0c7-46a7-b0a1-044a7ab7fcd9");
		EC_VERTEX = MetadataLookup.getConcept("fd8184ef-347a-4875-b7c7-1a7535bfcb7e");
		ECR_FRONTAL_NASAL = MetadataLookup.getConcept("008b5dab-b129-432c-95e7-477caea42005");
		ECR_FRONTAL_TEMPORAL = MetadataLookup.getConcept("f89c6eb0-11ce-4a6e-91cb-8dffec665293");
		ECR_OCCIPITAL = MetadataLookup.getConcept("8a0ef88a-43f3-49e9-8c20-413817314e2e");
		ECR_PARIETAL = MetadataLookup.getConcept("3eb300d2-5c99-4986-8c8c-91746b06df6b");
		ENCEPHALOCELE_EXCISION = MetadataLookup.getConcept("1d3a534f-63ea-45b9-866f-3f6073ebe9b8");
		ENDOSCOPIC_TRANS_SPHENOIDAL_SURGERY_ETSS = MetadataLookup.getConcept("ed6308e5-3b6c-4bcd-a450-4c09d99d3f6c");
		ETV = MetadataLookup.getConcept("344e082a-35f3-494f-9dc6-e78e0cfaff69");
		ETV_PLUS_BIOPSY = MetadataLookup.getConcept("73e9d4ea-ffd8-4165-9f24-2461404b8b81");
		ETV_REDO = MetadataLookup.getConcept("93c7d750-ed53-43cf-a909-358ef0a7e014");
		ETV_REDO_VPS_PLACEMENT = MetadataLookup.getConcept("c6ef3740-6d0a-437a-b54e-22ef39c16a03");
		ETV_AQUEDUCTOPLASTY = MetadataLookup.getConcept("8e575512-ca01-4402-b5c5-171bb9f17162");
		ETV_CPC = MetadataLookup.getConcept("56bf4721-db60-4564-8f5e-a63923b2aabc");
		ETV_CPC_REDO = MetadataLookup.getConcept("034e1063-903c-49fb-8cdc-afcbe0ce8c92");
		ETV_CPC_RESERVOIR_PLACEMENT = MetadataLookup.getConcept("502adffd-b794-43a9-9c19-7459556e87e4");
		ETV_CPC_VPS_PLACEMENT = MetadataLookup.getConcept("3219a6be-7049-435b-a9cf-9231a88ac50d");
		ETV_RESERVOIR_PLACEMENT = MetadataLookup.getConcept("2e08528f-324c-4f6c-a5d6-c6089493a85e");
		ETV_RESERVOIR_REMOVAL = MetadataLookup.getConcept("a61692f9-8370-4293-ba7f-daf396397bc5");
		ETV_SEPTOSTOMY = MetadataLookup.getConcept("85bdfb8e-02de-4e50-ade7-66bd17809856");
		ETV_VPS_PLACEMENT = MetadataLookup.getConcept("3ec00dd6-a864-41dc-84fd-68ed86f1958e");
		ETV_VPS_REMOVAL = MetadataLookup.getConcept("90bd8c52-77cb-4358-beb6-b8b3e27aa35a");
		EUA_BIOPSY = MetadataLookup.getConcept("0e098d61-e26e-4f80-b50d-691572da9d37");
		EVD_PLACEMENT = MetadataLookup.getConcept("46e1f39e-2b43-4f1c-b611-b96eb9d5a0ef");
		EXCISION = MetadataLookup.getConcept("c64b4815-dd15-4262-92e3-4bd530f8ff0a");
		EXCISION_SCALP_MASS = MetadataLookup.getConcept("12672458-b99e-4648-b003-98693a539649");
		EXCISION_SINUS_TRACT_SPINAL = MetadataLookup.getConcept("f565b732-3937-44b2-ae72-91a6a084a8ad");
		EXCISION_SINUS_TRACT_CERVICAL = MetadataLookup.getConcept("4eb337e9-35fd-45df-beca-1ac7ff59b4fb");
		EXCISION_SKULL_MASS = MetadataLookup.getConcept("6da0bf2f-a1c9-4459-99e5-f21e42c4f335");
		EXCISION_SPINAL_MASS = MetadataLookup.getConcept("538b64fb-bedd-4de8-bc94-7af12e92cb11");
		EXCISIONAL_BIOPSY = MetadataLookup.getConcept("55c42d17-f3f7-4d4b-9964-548df3a70908");
		EXPLORATION_SCALP_MASS = MetadataLookup.getConcept("5664a2d2-bc39-4807-8ab6-9f5c6eeaa6fd");
		EXTERNAL_DRAINAGE_SYSTEM = MetadataLookup.getConcept("270e1ad2-3c7a-4e90-8eb3-91b719680115");
		HERNIOTOMY = MetadataLookup.getConcept("ade45a0b-e88a-492b-a3b3-8a92d069f13c");
		ICP_MEASUREMENT = MetadataLookup.getConcept("6d88b480-7dd7-4c4a-89b6-8f23ab5b9a57");
		IMPLANT_REMOVAL = MetadataLookup.getConcept("1c577947-68a0-446f-b31a-ebcef6cca882");
		INCISION_AND_DRAINAGE = MetadataLookup.getConcept("7d256365-7ced-46cf-96f2-c0bc977f2d23");
		INCISIONAL_BIOPSY_SCALP_MASS = MetadataLookup.getConcept("3ee9ba2e-3641-4b2d-848a-d0b86e52cab7");
		INCISIONAL_BIOPSY_TUMOR = MetadataLookup.getConcept("8b1f2e55-889f-4ad1-8a32-cabdc0dd18b2");
		INTERBODY_FUSSION = MetadataLookup.getConcept("863279e1-735a-45ab-8a29-4936aa57045b");
		LAMINECTOMIES_SBO = MetadataLookup.getConcept("15135a54-8aff-4c8f-9360-a0ef07b95bb7");
		LAMINECTOMIES_CERVICAL = MetadataLookup.getConcept("17a3a462-ccaf-4ea0-adb4-6cadbc6562db");
		LAMINECTOMIES_CHIARI_DECOMPRESSION = MetadataLookup.getConcept("b2ef33d4-050b-4b69-8b2e-4f3d3c4c5a17");
		LAMINECTOMIES_LUMBAR = MetadataLookup.getConcept("1a8df11e-b0d0-45ea-9d4a-76f1b78094bd");
		LAMINECTOMIES_SACRAL = MetadataLookup.getConcept("610c498b-1305-407c-baca-74c11ce92bb8");
		LAMINECTOMIES_THORACIC = MetadataLookup.getConcept("8f099e83-e45f-4941-af32-5b000ba5fa8f");
		LAMINECTOMIES_THORACO_LUMBAR = MetadataLookup.getConcept("c0174d9b-2eb4-4a04-b939-3ddb4c7ef9a0");
		LAMINECTOMIES_TUMOR = MetadataLookup.getConcept("2332d1c3-db80-4779-84f1-dd636f0cfa67");
		LAMINECTOMY = MetadataLookup.getConcept("9c16cbca-5d82-4986-8a5f-87f0cac5be7c");
		LAMINECTOMY_PLUS_FUSSION = MetadataLookup.getConcept("59285b41-8267-4995-98d8-79531b6f0b02");
		LAMINOTOMY = MetadataLookup.getConcept("db1d82f3-5980-48e2-bb7b-dd8712bd6325");
		LUMBOPERITONEAL_SHUNT = MetadataLookup.getConcept("8d5e68d9-78dd-4ca7-9283-d994d2e5c14a");
		LYMPHNODE_BIOPSY = MetadataLookup.getConcept("385c4200-e3ac-4d1e-88e4-165393c21fff");
		MENINGOCELE_REPAIR = MetadataLookup.getConcept("17548fda-d5e3-40b7-9aea-9f33e3f74ac4");
		MM_CLOSURE = MetadataLookup.getConcept("029c62a2-454a-4116-a912-cb6d76e7a1c5");
		MMC_CLOSURE = MetadataLookup.getConcept("3325a85f-c281-41dc-a02c-3984854c368f");
		MMC_KYPHECTOMY = MetadataLookup.getConcept("97128253-d4f3-4976-a116-c91b248a8b01");
		PDR = MetadataLookup.getConcept("a059d22b-082f-4f46-afcb-1f7684cc6a0d");
		RE_DO_CRANIOTOMY_TUMOR_RESECTION = MetadataLookup.getConcept("b469a37f-76e0-49cf-a59d-2f681dbf168a");
		RESERVOIR_SUBDURAL = MetadataLookup.getConcept("d3695f82-1cbe-4ab8-a936-b7ecb8ed95d1");
		RESERVOIR_PLACEMENT = MetadataLookup.getConcept("744acd39-7d82-4ebb-be11-21a501deea91");
		RESERVOIR_PLACEMENT_VPS_REMOVAL = MetadataLookup.getConcept("a33b2a07-2156-44dd-a49f-ab860bde7f32");
		RESERVOIR_REMOVAL = MetadataLookup.getConcept("c0ebed61-e4d3-49cd-afee-b5ed539d019e");
		RESERVOIR_REMOVAL_REPLACEMENT = MetadataLookup.getConcept("817e86df-2385-4a6b-9de5-15a55e09e196");
		RESERVOIR_REVISION = MetadataLookup.getConcept("ca44b261-4a1c-4ed8-907c-57c490dba11b");
		ROD_REMOVAL = MetadataLookup.getConcept("c5da19b6-85cc-4ebb-b294-663f18838dde");
		SD_P_SHUNT_PLACEMENT = MetadataLookup.getConcept("2f903455-94c9-416c-9c3d-25620e43f4a7");
		SD_P_SHUNT_REMOVAL_ETV = MetadataLookup.getConcept("3abc5068-9fbb-4db2-9f42-1d50dc4083b3");
		SD_P_SHUNT_REMOVAL_ETV_CPC = MetadataLookup.getConcept("75d72fee-7338-4205-9646-b81976b3bca0");
		SD_P_SHUNT_REMOVAL_VPS_PLACEMENT = MetadataLookup.getConcept("d9ef6321-6167-41ae-b2ce-e269a6955bb8");
		SD_P_SHUNT_REVISION = MetadataLookup.getConcept("1bb70fbd-80c3-44d9-9618-cf2b21641ea2");
		SEPARATION_OF_CO_JOINT_TWINS = MetadataLookup.getConcept("6337e0a2-d2e9-4c5e-becd-388d4ad8fd89");
		SKIN_GRAFT = MetadataLookup.getConcept("7404bb0d-15a5-4685-97dc-b0c996114956");
		SPINAL_CORD_UNTETHERING_MM = MetadataLookup.getConcept("bf53bd0e-6bc5-49ce-8197-39543834bc55");
		SPINAL_CORD_UNTETHERING_SBO = MetadataLookup.getConcept("c2933d98-b7bb-403e-b058-396e910c923e");
		SPINAL_FUSION = MetadataLookup.getConcept("b767add2-997c-4203-b030-31e83ef629ad");
		SPINE_STABILISATION = MetadataLookup.getConcept("ca14abcb-462a-4435-837f-39417f2accf2");
		SUTURE_REMOVAL = MetadataLookup.getConcept("eceecc0f-b5da-428e-972c-ef1968915626");
		TARSORRHAPHY = MetadataLookup.getConcept("8ca48539-9c84-4298-8fdc-3f00acc3d108");
		TRACHEOSTOMY = MetadataLookup.getConcept("203bb1f3-8a42-48ee-ac8a-e237b7b1eb47");
		TUMOR_BIOPSY = MetadataLookup.getConcept("fa3f3db5-0456-46ea-bb70-d9d131466df2");
		VENTRICULO_ATRIAL_SHUNT_PLACEMENT = MetadataLookup.getConcept("ae98ec24-33b4-4968-89d8-d22a2415cd85");
		VENTRICULO_PLEURAL_SHUNT_PLACEMENT = MetadataLookup.getConcept("b40b2879-ed7c-4c81-b0ad-855690121f7e");
		VENTRICULOSCOPY_AQUEDUCTOPLASTY = MetadataLookup.getConcept("d033b943-2b90-42a4-b300-7cfe7c3b2182");
		VENTRICULOSCOPY_CYST = MetadataLookup.getConcept("c5abcffe-1072-442f-bf8a-90a964e45024");
		VENTRICULOSCOPY_FENESTRATION_OF_MEMBRANES = MetadataLookup.getConcept("241eac29-28c1-479a-82fe-02a37c2db107");
		VENTRICULOSCOPY_RESERVOIR_PLACEMENT = MetadataLookup.getConcept("02d1caa6-a87d-4513-b9ba-a1266bdf4a4e");
		VENTRICULOSCOPY_SEPTOSTOMY = MetadataLookup.getConcept("19d21020-5016-4464-b3b8-7dcaf3b3329b");
		VENTRICULOSCOPY_TUMOR = MetadataLookup.getConcept("3801a383-c0f2-4cd6-a4aa-8b25d53adc25");
		VENTRICULOSCOPY_VENT_CATH_REMOVAL = MetadataLookup.getConcept("383ab55d-d903-41c7-a816-710958c29925");
		VENTRICULOSCOPY_VPS_PLACEMENT = MetadataLookup.getConcept("fa658180-924f-434d-ad68-531af8c6afee");
		VENTRICULOSCOPY_VPS_REVISION = MetadataLookup.getConcept("f4304e90-c73e-47fc-ac8e-5b9bc03c186e");
		VPS_EXTERNALIZATION = MetadataLookup.getConcept("70d04fe8-63b3-4607-a4f8-ad7017985938");
		VPS_INTERNALIZATION = MetadataLookup.getConcept("bdebbd11-e7c2-47d3-b7ef-7b3268c87698");
		VPS_PLACEMENT = MetadataLookup.getConcept("3a277d17-ff63-414b-b49d-b47eccf00199");
		VPS_PLACEMENT_RESERVOIR_REMOVAL = MetadataLookup.getConcept("49201967-feab-4ad4-aabd-571616ef806a");
		VPS_REMOVAL = MetadataLookup.getConcept("cfc62089-4984-44db-87f7-f7d298c9ee20");
		VPS_REMOVAL_RESERVOIR_PLACEMENT = MetadataLookup.getConcept("fb0f67d7-873f-4346-b3f0-1edaa8c098ec");
		VPS_REPLACEMENT = MetadataLookup.getConcept("5cbeb6f8-3348-4851-9682-69c061673465");
		VPS_REPLACEMENT_RESERVOIR_REMOVAL = MetadataLookup.getConcept("ec771563-8830-4900-a49d-c1015f4b18f9");
		VPS_REVISION = MetadataLookup.getConcept("60754167-0527-4e67-aa3b-5de0dfb400a0");
		VPS_TIE_OFF = MetadataLookup.getConcept("a831e09e-83f6-4810-9cb8-5b6e2a26a725");
		VSSS_PLACEMENT = MetadataLookup.getConcept("6859ef95-ec54-4ce1-8e39-eb431df474b6");
		VSSS_REMOVAL_VPS_PLACEMENT = MetadataLookup.getConcept("e9965143-b4c8-452c-b821-3c28d6fb8446");
		VSSS_REVISION = MetadataLookup.getConcept("293e8f23-3d68-460b-a725-616cb31b16f4");
		WOUND_CLOSURE = MetadataLookup.getConcept("7834d3fe-2ea6-49e7-a20a-4ab30b9f668f");
		WOUND_REVISION_MM = MetadataLookup.getConcept("8994c20e-db9a-4dd8-a7ad-ee85d55b3988");
		WOUND_REVISION_OTHER = MetadataLookup.getConcept("5486d031-4a34-4357-bde1-96cfe86c5111");
		
	}
}
