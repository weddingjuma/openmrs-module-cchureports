package org.openmrs.module.cchureports.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.cchureports.reporting.SetupFollowUpReport;
import org.openmrs.module.cchureports.reporting.SetupIfInterimReport;
import org.openmrs.module.cchureports.reporting.SetupLateVisitandLTFUReport;
import org.openmrs.module.cchureports.reporting.SetupORLogBookReport;
import org.openmrs.module.cchureports.reporting.SetupPlasticSurgeryLogBookReport;
import org.openmrs.module.cchureports.reporting.SetupSummaryValuesReport;
import org.openmrs.module.cchureports.reporting.SetupSurgicalProceduresIndicatorReport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CCHUSetupReportsFormController {
	
	public Log log = LogFactory.getLog(getClass());
	
	@RequestMapping(value = "/module/cchureports/cchureports", method = RequestMethod.GET)
	public void manage() {
	}
	
	@RequestMapping("/module/cchureports/register_ORLogBook")
	public ModelAndView registerORLogBook() throws Exception {
		new SetupORLogBookReport().setup();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/remove_ORLogBook")
	public ModelAndView removeORLogBook() throws Exception {
		new SetupORLogBookReport().delete();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/register_PSLoggBook")
	public ModelAndView registerPSLoggBook() throws Exception {
		new SetupPlasticSurgeryLogBookReport().setup();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/remove_PSLoggBook")
	public ModelAndView removePSLoggBook() throws Exception {
		new SetupPlasticSurgeryLogBookReport().delete();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/register_SVReport")
	public ModelAndView registerSVReport() throws Exception {
		new SetupSummaryValuesReport().setup();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/remove_SVReport")
	public ModelAndView removeSVReport() throws Exception {
		new SetupSummaryValuesReport().delete();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/register_followupsReport")
	public ModelAndView registerfollowupsReport() throws Exception {
		new SetupFollowUpReport().setup();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/remove_followupsReport")
	public ModelAndView removefollowupsReport() throws Exception {
		new SetupFollowUpReport().delete();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/register_lateVisitandLTFUReport")
	public ModelAndView registerLateVisitandLTFUReport() throws Exception {
		new SetupLateVisitandLTFUReport().setup();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/remove_lateVisitandLTFUReport")
	public ModelAndView removeLateVisitandLTFUReport() throws Exception {
		new SetupLateVisitandLTFUReport().delete();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/register_surgicalProceduresReport")
	public ModelAndView registerSurgicalProceduresReport() throws Exception {
		new SetupSurgicalProceduresIndicatorReport().setup();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/remove_surgicalProceduresReport")
	public ModelAndView removeSurgicalProceduresReport() throws Exception {
		new SetupSurgicalProceduresIndicatorReport().delete();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/register_ifInterimReport")
	public ModelAndView registerIfInterimReport() throws Exception {
		new SetupIfInterimReport().setup();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/remove_ifInterimReport")
	public ModelAndView removeIfInterimReport() throws Exception {
		new SetupIfInterimReport().delete();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
}
