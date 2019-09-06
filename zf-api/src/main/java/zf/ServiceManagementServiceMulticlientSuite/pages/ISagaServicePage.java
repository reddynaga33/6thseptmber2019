package zf.ServiceManagementServiceMulticlientSuite.pages;

import java.util.HashMap;

import framework.RestApiUtility;
import io.restassured.response.Response;
import zf.api.pages.ServiceDescriptorPage;

public class ISagaServicePage extends RestApiUtility{
	public Response createPropertyForCUSTrue=null;
	public Response updatePropertyForCUSTrue=null;
	String SagaStatusWithoutAppid;
	ServiceDescriptorPage SD =new ServiceDescriptorPage();
	
	public void VerifyTheSagaEntityForSDWithCorrectSagaId() {
		Response serviceDescriptor = SD.getServiceDescriptor();
		SagaStatusWithoutAppid = SD.assignServiceDescriptorToCLientWithoutAppId();
		Response SagaResponse=SD.getSagaStatusCLientWithoutAppId(SagaStatusWithoutAppid);

	}
}