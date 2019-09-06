package zf.platform.insprint.pages;

import framework.RestApiUtility;
import io.restassured.response.Response;

public class CPF2826Page extends RestApiUtility {
	public void AddSupportForChangeClientContextExtractor()
	{
		Response ClientContextExtractorHierarchyResponse=GetServices("ClientContextExtractorHierarchy");
		Response ClientContextExtractorUserResponse=GetServices("ClientContextExtractorUser");

		if(ClientContextExtractorHierarchyResponse.getStatusCode()==200 && ClientContextExtractorUserResponse.getStatusCode()==200)
		{
			info("The ClientContextExtractor Hierarchy of Client Response is :"+ClientContextExtractorHierarchyResponse.getBody().asString());
			testPassed("The ClientContextExtractor User of Client Response is :"+ClientContextExtractorUserResponse.getBody().asString());
		}
		else
		{
			info("The ClientContextExtractor Hierarchy of Client Response is :"+ClientContextExtractorHierarchyResponse.getBody().asString());
			testFailed("The ClientContextExtractor User of Client Response is :"+ClientContextExtractorUserResponse.getBody().asString());
		}
	}
}