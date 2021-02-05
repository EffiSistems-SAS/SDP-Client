package Connection;

import java.io.File;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

/**
 * @author migue
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class ConexionServer {

    private HttpClient httpClient;
    private HttpPost post;
    private HttpGet get;
    private HttpResponse response;
    private String baseUrl;
    private HttpPut put;
    private static ConexionServer instanceConnection;

    public void finalize() throws Throwable {

    }

    private ConexionServer() {

    }

    /**
     *
     * @param ruta
     * @param datos
     */
    public void POST(String ruta, String datos) {

    }

    /**
     *
     * @param ruta
     */
    public String GET(String ruta) {
        return "";
    }

    public static ConexionServer getConexionServer() {
        return null;
    }

    /**
     *
     * @param ruta
     * @param archivo
     */
    public void POST(String ruta, File archivo) {

    }

    /**
     *
     * @param ruta
     * @param data
     */
    public void PUT(String ruta, String data) {

    }
}//end ConexionServer
