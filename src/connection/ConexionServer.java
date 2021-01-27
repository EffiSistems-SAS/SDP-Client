package connection;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ConexionServer {
    
    private HttpClient htppClient;
    private HttpGet get;
    private HttpPost post;
    private HttpPut put;
    private HttpResponse response;
    private String resource, base_url;
    
    private static ConexionServer instance;
    
    private ConexionServer(){
        htppClient = HttpClients.createDefault();
        get = null;
        post = null;
        base_url = "";
    
    }
    
    public static ConexionServer getInstance() {
        if (instance == null) {
            instance = new ConexionServer();
        }
        return instance;
    }
    
    public String GET(String ruta) {

        get = new HttpGet(base_url + ruta);
        try {
            response = this.htppClient.execute(get);
            resource = EntityUtils.toString(this.response.getEntity());

        } catch (Exception e) {
        }
        return resource;

    }

    public void POST(String ruta, String data) {
        try {
            URI final_url = new URIBuilder(base_url + ruta).build();
            post = new HttpPost(final_url);
            post.setHeader("content-type", "application/json");
            StringEntity final_data = new StringEntity(data);
            post.setEntity(final_data);
            response = htppClient.execute(post);
            resource = EntityUtils.toString(response.getEntity());
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void POSTFile(String ruta,File file){
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            FileBody body = new FileBody(file);
            builder.addPart("file", body);
            URI final_url = new URIBuilder(base_url + ruta).build();
            post = new HttpPost(final_url);
            //post.setHeader("content-type", "multipart/form-data");
            HttpEntity reqEntity = builder.build();
            post.setEntity(reqEntity);
            response = htppClient.execute(post);
            resource = EntityUtils.toString(response.getEntity());
        } catch (URISyntaxException ex) {
           
        } catch (IOException ex) {
            
        }
       

    }

    public void PUT(String ruta, String data) {
        try {
            put = new HttpPut(base_url + ruta);
            put.setHeader("content-type", "application/json");
            StringEntity final_data = new StringEntity(data);
            put.setEntity(final_data);
            response = htppClient.execute(put);
            resource = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
        }
    }
    
}
