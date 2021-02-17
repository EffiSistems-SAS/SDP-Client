package Connection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
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

/**
 * @author migue
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class ConexionServer {

    private org.apache.http.client.HttpClient client;
    private HttpGet get;
    private HttpPost post;
    private HttpPut put;
    private org.apache.http.HttpResponse response;
    private String resource, base_url;
    private static ConexionServer instanceConnection;

    private ConexionServer() {
        client = HttpClients.createDefault();
        get = null;
        post = null;
        base_url = "http://localhost:3000";
    }

    /**
     *
     * @param ruta
     * @param datos
     */
    public void POST(String ruta, String datos) {
        try {
            URI final_url = new URIBuilder(base_url + ruta).build();
            post = new HttpPost(final_url);
            post.setHeader("content-type", "application/json");
            StringEntity final_data = new StringEntity(datos);
            post.setEntity(final_data);
            response = client.execute(post);
            resource = EntityUtils.toString(response.getEntity());
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     *
     * @param ruta
     */
    public InputStream GETFILE(String ruta) {
        get = new HttpGet(base_url + ruta);
        try {
            response = this.client.execute(get);
            //resource = EntityUtils.toString(this.response.getEntity());
            return this.response.getEntity().getContent();

        } catch (Exception e) {
        }
        return null;
    }
    
    public int GET(String ruta) {
        get = new HttpGet(base_url + ruta);
        try {
            response = this.client.execute(get);
            resource = EntityUtils.toString(this.response.getEntity());

        } catch (Exception e) {
        }
        return this.response.getStatusLine().getStatusCode();
    }

    public static ConexionServer getConexionServer() {
        if (instanceConnection == null) {
            instanceConnection = new ConexionServer();
        }
        return instanceConnection;
    }

    /**
     *
     * @param ruta
     * @param archivo
     */
    public void POST(String ruta, File archivo) {
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            FileBody body = new FileBody(archivo);
            builder.addPart("file", body);
            URI final_url = new URIBuilder(base_url + ruta).build();
            post = new HttpPost(final_url);
            //post.setHeader("content-type", "multipart/form-data");
            HttpEntity reqEntity = builder.build();
            post.setEntity(reqEntity);
            response = client.execute(post);
            resource = EntityUtils.toString(response.getEntity());
        } catch (URISyntaxException ex) {
        } catch (IOException ex) {
        }
    }

    /**
     *
     * @param ruta
     * @param data
     */
    public void PUT(String ruta, String datos) {
        try {
            put = new HttpPut(base_url + ruta);
            put.setHeader("content-type", "application/json");
            StringEntity final_data = new StringEntity(datos);
            put.setEntity(final_data);
            response = client.execute(put);
            resource = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
        }

    }
}//end ConexionServer
