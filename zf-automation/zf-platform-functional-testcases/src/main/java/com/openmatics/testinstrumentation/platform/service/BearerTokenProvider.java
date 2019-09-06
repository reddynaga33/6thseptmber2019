package com.openmatics.testinstrumentation.platform.service;

import com.microsoft.aad.adal4j.*;

import javax.naming.ServiceUnavailableException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BearerTokenProvider {

    private String tokenServiceUriTemplate = "https://login.microsoftonline.com/{tenant}/oauth2/authorize/";
    private AuthenticationResult token = null;
    private IServiceConfiguration serviceProperty;


    public BearerTokenProvider(IServiceConfiguration serviceProperty)
    {
        this.serviceProperty= serviceProperty;
    }


    public AuthenticationResult getToken() throws java.net.MalformedURLException, ServiceUnavailableException {
        if (token == null) {
            token = callForToken();
        }
        else{
            // check if token is valid or not be before exporation if not return ii
            // if yes call for new
            // compare now UTC ? date wit date expiration token
        }
        return token;
    }

    private AuthenticationResult callForToken() throws ServiceUnavailableException{
        AuthenticationContext context;
        AuthenticationResult result = null;
        ExecutorService service = null;
        String tokenServiceUri = tokenServiceUriTemplate.replace("{tenant}", getAddTenant(serviceProperty));
        System.out.println("tokenServiceUri: " + tokenServiceUri);
        try
        {
            service = Executors.newFixedThreadPool(1);
            context = new AuthenticationContext(tokenServiceUri, true, service);
            ClientCredential cliCred = new ClientCredential(serviceProperty.getApplicationClientId(), serviceProperty.getApplicationClientSecret());
            Future<AuthenticationResult> future = context.acquireToken(serviceProperty.getApplicationIdUri(), cliCred, null);
            result = future.get();
        }
        catch (Exception e){
            //Log.logger.error(e);
            //throw e;
            System.out.println(e.getMessage());
        }
        finally {
            service.shutdown();
        }
        if (result == null) {
            System.out.println("!!!!!!!!!!!!!!!!!! Authentication result is null. !!!!!!!!!!!!!!!!");
            throw new ServiceUnavailableException("Authentication result is null.");
        }
        return result;
    }

    /**
     * If exists tenant directly deffined in the service get it else get tenant from environment
     * @param config
     * @return
     */
    private String getAddTenant(IServiceConfiguration config){
        String tenant = config.getTenant();
        if (tenant == null ) tenant = config.getEnvConf().getAADTenant();
        return tenant;
    }
}
