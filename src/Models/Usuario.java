package Models;

import Connection.ConexionServer;

/**
 * @author migue
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class Usuario {

	private String id;
	private String correo;
	private String contrase�a;
	private String nombre;
	public ConexionServer m_ConexionServer;

	public Usuario(){

	}

	public void finalize() throws Throwable {

	}
	public int iniciarSesi�n(){
		return 0;
	}
}//end Usuario