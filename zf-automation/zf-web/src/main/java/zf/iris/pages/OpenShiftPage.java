package zf.iris.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import framework.ElementManager;
import framework.JsonReader;
import net.minidev.json.JSONObject;

public class OpenShiftPage extends ElementManager {

	private static  By HOMEPAGE_PROJECT_FIRST_VALUE                    =By.xpath("//a[@class='project-title tile-target ng-binding']");
	private static  By PROJECT_APPLICATION_LK                          =By.xpath("(//span[text()='Applications'])[1]");
	private static  By PROJECT_DEPLOYMENTS_LK                          =By.xpath("//span[text()='Deployments']");
	private static  By DEPLOYMENTS_STATUS_DT                           =By.xpath("//td[@data-title='Status']");
	private static  By DEPLOYMENTS_NAME_DT                             =By.xpath("//td[@data-title='Status']/preceding-sibling::td[@data-title='Name']");
	private static  By PROJECT_RESOUECES_LK                            =By.xpath("(//span[text()='Resources'])[1]");
	private static  By PROJECT_QUOTA_LK                                =By.xpath("//span[text()='Quota']");
	private static  By PROJECT_QUOTACPU_IMG                            =By.xpath("(//*[@class=' c3-shapes c3-shapes-used c3-arcs c3-arcs-used'])[1]/*");
	private static  By PROJECT_QUOTAMEMORY_IMG                         =By.xpath("(//*[@class=' c3-shapes c3-shapes-used c3-arcs c3-arcs-used'])[2]/*");
	private static  By PROJECT_QUOTATERMINATINGCPU_IMG                 =By.xpath("(//*[@class=' c3-shapes c3-shapes-used c3-arcs c3-arcs-used'])[3]/*");
	private static  By PROJECT_QUOTATERMINATINGMEMORY_IMG              =By.xpath("(//*[@class=' c3-shapes c3-shapes-used c3-arcs c3-arcs-used'])[4]/*");



	public void ApplicationDeploymentStatus() {
		try {
			JSONObject createAssetObject = JsonReader.getJsonObject("C55138");
			elementClick(HOMEPAGE_PROJECT_FIRST_VALUE);
			sleep(2000);
			elementClick(PROJECT_APPLICATION_LK);
			sleep(2000);
			elementClick(PROJECT_DEPLOYMENTS_LK);
			sleep(30000);
			List<WebElement> deploymentStatus = elementListReturn(DEPLOYMENTS_STATUS_DT);
			List<WebElement> deploymentName = elementListReturn(DEPLOYMENTS_NAME_DT);

			for(int i=0;i<deploymentStatus.size();i++) {
				if(deploymentStatus.get(i).getText().equalsIgnoreCase(createAssetObject.getAsString("Crash"))){
					info("Application Name "+deploymentName.get(i).getText()+" Status : "+deploymentStatus.get(i).getText());
				}else {
					testPassed("Application Name "+deploymentName.get(i).getText()+" Status : "+deploymentStatus.get(i).getText());
				}
			}

		}catch(Exception e) {
			testFailed("An exception occured  and the error message is : "+e.getMessage());
		}
	}

	public void QuotaStatus() {
		try {
			JSONObject createAssetObject = JsonReader.getJsonObject("C55138");
			elementClick(HOMEPAGE_PROJECT_FIRST_VALUE);
			elementClick(PROJECT_RESOUECES_LK);
			sleep(2000);
			elementClick(PROJECT_QUOTA_LK);
			sleep(30000);
			String defaultCpuUsageValue = elementGetValue(PROJECT_QUOTACPU_IMG, createAssetObject.getAsString("Style"));
			String[] split = defaultCpuUsageValue.split(";");
			String Colourcode = split[0].replace("fill: ", "");
			if(Colourcode.equalsIgnoreCase(createAssetObject.getAsString("Colourcode"))){
				testPassed("default-compute-quota CPU usage is in limit ");
			}else {
				testFailed("default-compute-quota CPU usage is not in limit");
				takeScreenShot("default-compute-quota CPU usage is not in limit");
			}
			String defaultMemoryUsageValue = elementGetValue(PROJECT_QUOTAMEMORY_IMG, createAssetObject.getAsString("Style"));
			split = defaultMemoryUsageValue.split(";");
			Colourcode = split[0].replace("fill: ", "");
			if(Colourcode.equalsIgnoreCase(createAssetObject.getAsString("Colourcode"))){
				testPassed("default-compute-quota Memory usage is in limit ");
			}else {
				testFailed("default-compute-quota Memory usage is not in limit ");
				takeScreenShot("default-compute-quota Memory usage is in limit");
			}
			String defaultTerminatingCpuUsageValue = elementGetValue(PROJECT_QUOTATERMINATINGCPU_IMG, createAssetObject.getAsString("Style"));
			split = defaultTerminatingCpuUsageValue.split(";");
			Colourcode = split[0].replace("fill: ", "");
			if(Colourcode.equalsIgnoreCase(createAssetObject.getAsString("Colourcode"))){
				testPassed("default-compute-quota-terminating CPU usage is in limit");
			}else {
				testFailed("default-compute-quota-terminating CPU usage is not in limit");
				takeScreenShot("default-compute-quota-terminating CPU usage is not in limit");
			}
			String defaultTerminatingmemoryUsageValue = elementGetValue(PROJECT_QUOTATERMINATINGMEMORY_IMG, createAssetObject.getAsString("Style"));
			split = defaultTerminatingmemoryUsageValue.split(";");
			Colourcode = split[0].replace("fill: ", "");
			if(Colourcode.equalsIgnoreCase(createAssetObject.getAsString("Colourcode"))){
				testPassed("default-compute-quota-terminating Memory usage is in limit ");
			}else {
				testFailed("default-compute-quota-terminating Memory usage is in limit ");
				takeScreenShot("default-compute-quota-terminating Memory usage is not in limit");
			}

		}catch(Exception e) {
			testFailed("An exception occured  and the error message is : "+e.getMessage());
		}
	}




}
