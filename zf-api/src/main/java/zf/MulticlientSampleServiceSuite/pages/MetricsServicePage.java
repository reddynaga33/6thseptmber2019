package zf.MulticlientSampleServiceSuite.pages;

import framework.RestApiUtility;
import zf.platform.insprint.pages.CPF3049Page;

public class MetricsServicePage extends RestApiUtility{

	CPF3049Page CPF3049pageObject=new CPF3049Page();
	public void VerifyUuserIsAbleToSeeTheMetricsForPrometheus() {
	
		CPF3049pageObject.PrometheusMetrics();
		CPF3049pageObject.HealthCheck();
		CPF3049pageObject.PrometheusMetrics();
	}
	
	
}
