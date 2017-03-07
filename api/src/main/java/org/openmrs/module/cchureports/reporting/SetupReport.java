package org.openmrs.module.cchureports.reporting;

/**
 * Report Setup / teardown interface
 */
public interface SetupReport {
	
	public void setup() throws Exception;
	
	public void delete();
}
