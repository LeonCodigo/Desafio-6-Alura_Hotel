package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion
{
	
	public static void main(String[] args) throws SQLException
	{
		ConnectionFactory connectionFactory = new ConnectionFactory();       /*1*/
	    
	    Connection conexion = connectionFactory.recuperarConexion();         /*2*/
	    
	    
	 
	    
	    conexion.close();
	    
	 
	}
	
    
    
    
}



/* 1 --> instanciamos objeto de la clase ConnectionFactory que crea y devuelve una conneccion con el metodo recuperarConexion()        
 * 
 * 2 -> Recuperamos la conexion para poder usarla en ensta clase y la guardamos en un objeto de tipo Connection.
 * 
 * 
 * 
 * 
 * 
 * 
 * *
 */