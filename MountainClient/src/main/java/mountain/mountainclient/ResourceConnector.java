package mountain.mountainclient;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.ws.rs.core.MediaType;


/**
 *
 */
public class ResourceConnector {
    
    // Create the client
    private final Client client = Client.create();
    
    // Base operation every operation starts at
    WebResource baseResource = client.resource("http://localhost:8080/webapi/mountainresource/");
    
    // POST operation with query parameters     
    public String postIt(String path, String[] params) {
        
        // Add the specified path parameter to the base resource path
        WebResource webTarget = baseResource.path(path);
        
        // Add query paramaters to the URL
        for (int i = 0; i < params.length; i++) {
            if (i < params.length - 1) {
                webTarget = webTarget.queryParam(params[i], params[i + 1]);
            }
        }
                
        ClientResponse response = webTarget.type(MediaType.TEXT_PLAIN)
                                           .post(ClientResponse.class);
        
        // If error code returned
        if (response.getStatus() > 204) {
            System.out.println("POST Failed with code: " + response.getStatus());
            
        }
        
        return response.getEntity(String.class);
    }
    
    
    // GET operation with query parameters    
    public String getIt(String path, String[] params) {
        
        
        // Add the specified path parameter to the base resource path
        WebResource webTarget = baseResource.path(path);
        
        // Add query paramaters to the URL
        for (int i = 0; i < params.length; i++) {
            if (i < params.length - 1) {
                webTarget = webTarget.queryParam(params[i], params[i + 1]);
            }
        }
                
        ClientResponse response = webTarget.type(MediaType.TEXT_PLAIN)
                                           .get(ClientResponse.class);
        
        // If an OK status is not returned
        if (response.getStatus() != 200) {
            System.out.println("GET Failed with code: " + response.getStatus());
        }
        
        return response.getEntity(String.class);
    }
    
    
    // PUT operation with query parameters    
    public String putIt(String path, String[] params) {
        

        // Add the specified path parameter to the base resource path
        WebResource webTarget = baseResource.path(path);
        
        // Add query paramaters to the URL
        for (int i = 0; i < params.length; i++) {
            if (i < params.length - 1) {
                webTarget = webTarget.queryParam(params[i], params[i + 1]);
            }
        }
                
        ClientResponse response = webTarget.type(MediaType.TEXT_PLAIN)
                                           .put(ClientResponse.class);
        
        // If an error code is returned
        if (response.getStatus() > 204) {
            System.out.println("PUT Failed with code: " + response.getStatus());
        }
        
        return response.getEntity(String.class);
    }
    
    
    // DELETE operation with query parameters    
    public String deleteIt(String path, String[] params) {
        
        // Add the specified path parameter to the base resource path
        WebResource webTarget = baseResource.path(path);
        
        // Add query paramaters to the URL
        for (int i = 0; i < params.length; i++) {
            if (i < params.length - 1) {
                webTarget = webTarget.queryParam(params[i], params[i + 1]);
            }
        }
        
        ClientResponse response = webTarget.type(MediaType.TEXT_PLAIN)
                                           .delete(ClientResponse.class);
        
        // If an error code is returned
        if (response.getStatus() > 204) {
            System.out.println("DELETE Failed with code: " + response.getStatus());
        }
        
        return response.getEntity(String.class);
    }
    
}
