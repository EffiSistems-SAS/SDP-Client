package Connection;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpDelete;
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

    private org.apache.http.client.HttpClient client;
    private HttpGet get;
    private HttpPost post;
    private HttpPut put;
    private HttpDelete delete;
    private org.apache.http.HttpResponse response;
    private String resource, base_url;
    private static ConexionServer instanceConnection;

    private ConexionServer() { //Constructor público
        client = HttpClients.createDefault();
        get = null;
        post = null;
        base_url = "https://sdp-solution.herokuapp.com/";
    }

    public org.apache.http.HttpResponse getResponse() {
        return response;
    }

    public static ConexionServer getConexionServer() { //Singleton
        if (instanceConnection == null) {
            instanceConnection = new ConexionServer();
        }
        return instanceConnection;
    }

    public String GET(String ruta) { //Ruta get
        get = new HttpGet(base_url + ruta);
        try {
            response = this.client.execute(get);
            resource = EntityUtils.toString(this.response.getEntity());
            return resource;
        } catch (Exception e) {
        }
        if (this.response == null) {
        }
        return null;
    }

    public InputStream GETFILE(String ruta) { //Ruta get para obtener un archivo
        get = new HttpGet(base_url + ruta);
        try {
            response = this.client.execute(get);
            return this.response.getEntity().getContent();
        } catch (Exception e) {
        }
        return null;
    }

    public int POST(String ruta, String datos) {
        try {
            URI final_url = new URIBuilder(base_url + ruta).build();
            post = new HttpPost(final_url);
            post.setHeader("content-type", "application/json");
            StringEntity final_data = new StringEntity(datos);
            post.setEntity(final_data);
            response = client.execute(post);
            resource = EntityUtils.toString(response.getEntity());
            return this.response.getStatusLine().getStatusCode();
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
            return 402;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }

    public int POSTFILE(String ruta, File archivo) { //Ruta post para subir un archivo
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            FileBody body = new FileBody(archivo);
            builder.addPart("file", body);
            URI final_url = new URIBuilder(base_url + ruta).build();
            post = new HttpPost(final_url);
            HttpEntity reqEntity = builder.build();
            post.setEntity(reqEntity);
            response = client.execute(post);
            resource = EntityUtils.toString(response.getEntity());
            return this.response.getStatusLine().getStatusCode();
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
            return 402;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return 500;
        }
    }

    public int DELETE(String ruta) { //Ruta delete para borrar un empleado
        try {
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            URI final_url = new URIBuilder(base_url + ruta).build();
            delete = new HttpDelete(final_url);
            response = client.execute(delete);
            resource = EntityUtils.toString(response.getEntity());
            return this.response.getStatusLine().getStatusCode();
        } catch (URISyntaxException ex) {
            System.out.println(ex.getMessage());
            return 402;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return 500;
        }
    }

    public int PUT(String ruta, String datos) { //Ruta put para editar
        try {
            put = new HttpPut(base_url + ruta);
            put.setHeader("content-type", "application/json");
            StringEntity final_data = new StringEntity(datos);
            put.setEntity(final_data);
            response = client.execute(put);
            resource = EntityUtils.toString(response.getEntity());
            return this.response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 500;
        }
    }
}
