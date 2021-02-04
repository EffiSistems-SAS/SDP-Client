package Connection;


/**
 * @author migue
 * @version 1.0
 * @created 03-feb.-2021 21:24:55
 */
public class Dao {

	public ProccesData m_ProccesData;

	public Dao(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param tabla
	 * @param data
	 */
	public static Promise create(String tabla, Object[] data){
		return null;
	}

	/**
	 * 
	 * @param tabla
	 * @param id
	 */
	public static Promise get(String tabla, String id){
		return null;
	}

	/**
	 * 
	 * @param table
	 */
	public static Promise getAll(String table){
		return null;
	}

	/**
	 * 
	 * @param tabla
	 * @param cuerpo
	 * @param id
	 */
	public static Promise edit(String tabla, Object cuerpo, String id){
		return null;
	}

	/**
	 * 
	 * @param tabla
	 * @param id
	 */
	public static Promise delete(String tabla, String id){
		return null;
	}

	/**
	 * 
	 * @param request
	 */
	public static Promise sendRequest(String request){
		return null;
	}
}//end Dao