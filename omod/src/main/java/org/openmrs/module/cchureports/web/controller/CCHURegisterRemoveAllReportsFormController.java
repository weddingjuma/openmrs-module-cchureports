package org.openmrs.module.cchureports.web.controller;

import org.openmrs.module.cchureports.util.CleanReportingTablesAndRegisterAllReports;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class CCHURegisterRemoveAllReportsFormController {
	
	@RequestMapping("/module/cchureports/register_allReports")
	public ModelAndView registerAllReports() throws Exception {
		CleanReportingTablesAndRegisterAllReports.registerReports();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
	@RequestMapping("/module/cchureports/remove_allReports")
	public ModelAndView removeAllReports() throws Exception {
		CleanReportingTablesAndRegisterAllReports.cleanTables();
		return new ModelAndView(new RedirectView("cchureports.form"));
	}
	
}
