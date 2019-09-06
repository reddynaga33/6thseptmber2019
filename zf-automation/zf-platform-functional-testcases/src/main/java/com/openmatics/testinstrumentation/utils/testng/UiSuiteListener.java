package com.openmatics.testinstrumentation.utils.testng;

import com.openmatics.testinstrumentation.platform.IEnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.EnvironmentConfiguration;
import com.openmatics.testinstrumentation.platform.service.IServiceConfiguration;
import com.openmatics.testinstrumentation.platform.service.RestApiBase;
import com.openmatics.testinstrumentation.platform.service.ServiceConfiguration;
import com.openmatics.testinstrumentation.utils.net.HttpResponse;
import org.testng.ISuiteListener;
import org.testng.ISuite;

public class UiSuiteListener implements ISuiteListener
{

    String subscriptionId="f889f019-873b-4c24-a776-bf10fc81df76";
    String groupName="HCO-IoT-CDO-Dev";
    String vmName="AutomatedTest";
    String restAzureResourceMngmUrlTemplate="https://management.azure.com/subscriptions/%s/" +
            "resourceGroups/%s/" +
            "providers/Microsoft.Compute/virtualMachines/%s/" +
            "%s"
            + "?api-version=2018-06-01";

    @Override
    public void onStart(ISuite suite) {

        try
        {
            String envKey = System.getProperty("envKey");
            IEnvironmentConfiguration env = new EnvironmentConfiguration(envKey);
            IServiceConfiguration config = new ServiceConfiguration("cdo-dev-tool", env);
            String ulr = String.format(restAzureResourceMngmUrlTemplate, subscriptionId, groupName, vmName, "start");
            RestApiBase api = new RestApiBase(new java.net.URL(ulr), config);
            HttpResponse response = api.getPostResponse("", "");
            if (response.getStatus() != 202) {
                System.out.println("!!!!!! Bad response status when it try start VM with selenium server");
            }
        }
        catch(Exception ex){
            System.out.println("ERROR: !!!!! Failed start VM with selenium server");
        };
    }

    @Override
    public void onFinish(ISuite suite) {

        try
        {
            String envKey = System.getProperty("envKey");
            IEnvironmentConfiguration env = new EnvironmentConfiguration(envKey);
            IServiceConfiguration config = new ServiceConfiguration("cdo-dev-tool", env);
            String ulr = String.format(restAzureResourceMngmUrlTemplate, subscriptionId, groupName, vmName, "deallocate");
            RestApiBase api = new RestApiBase(new java.net.URL(ulr), config);
            HttpResponse response = api.getPostResponse("", "");
            if (response.getStatus() != 202) {
                System.out.println("!!!!!! Bad response status when it try deallocate VM with selenium server");
            }
        }
        catch(Exception ex)
        {
            //TODO sent email about error when try dealocate VM with selenium remote server
            System.out.println("ERROR: !!!!! Failed dealocate VM with selenium server");
        };

    }

}
