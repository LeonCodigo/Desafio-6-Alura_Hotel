package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.swing.JOptionPane;

import modelo.Reserva;

public class ReservaDAO 
{
   private Connection coneccion; 
   private String ultimoQueryBusqueda ="SELECT * FROM reservas;";
	
	public ReservaDAO(Connection coneccion)
	{
		this.coneccion =coneccion;
	}
	
	public void guardar(Reserva reserva)                                                                    /*1*/
	{
		String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_pago)"     
				+ "VALUES (?,?,?,?)";
		try (PreparedStatement preState = coneccion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))   /*2*/
		{
			preState.setDate(1, reserva.getFechaEn());                                                        /*3*/
			preState.setDate(2, reserva.getFechaSal());
			preState.setString(3, reserva.getValor());
			preState.setString(4, reserva.getFormaPago());
			
			preState.executeUpdate();																			/*4*/
			
			try (ResultSet rst = preState.getGeneratedKeys())                                                  /*5*/
			{
				while (rst.next())
				{
					reserva.setId(rst.getInt(1));
				}
			}
			
			
		} 
		catch (SQLException e)
		{
		   throw new RuntimeException(e);
		}
		
	}
	
	public List<Reserva> leer(String pBuscado)
	{
		List<Reserva> reservasCargadas = new ArrayList<>();
		
		String queryString = "SELECT * FROM reservas;";
		
		if(!pBuscado.equals(""))
		{
			queryString = "SELECT * FROM reservas WHERE id= '"+ pBuscado.toString()  +"' OR forma_pago = '"+ pBuscado.toString() + "' OR valor = '"+ pBuscado.toString() +"'";
		    //+" OR fecha_entrada="+ pBuscado + " or fecha_salida="+ pBuscado +" or valor="+ pBuscado
		
		}
		
		ultimoQueryBusqueda = queryString;
		
		System.out.println("ReservaDAO query ejecutado: " + queryString);
		
		
		try
		{
			Statement statement = coneccion.createStatement();
			boolean resultadoQuery = statement.execute(queryString);
			
			if(resultadoQuery)
			{
			   ResultSet resultSet = statement.getResultSet();	
			   
			   while(resultSet.next())
				{
				   reservasCargadas.add(  new Reserva( resultSet.getInt("id"), resultSet.getDate("fecha_entrada"), resultSet.getDate("fecha_salida"), resultSet.getString("valor"), resultSet.getString("forma_pago") ) );
				}			
			}		
			
			
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		
		
		return reservasCargadas;
	}

	public int eliminar(String id)
	{
		int res = 0;
		try 
		{
			Statement statement = coneccion.createStatement();
			statement.execute("DELETE FROM reservas WHERE id = " + id);
			res = statement.getUpdateCount();									/*6*/
			System.out.println("ReservaDAO query ejecutado: " +"DELETE FROM reservas WHERE id = " + id);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
			throw new RuntimeException(e);
		}
		
		return res;
	}

	public int modificar(String id, String col, String valor)
	{
		String query = "UPDATE reservas SET " + col +" = "+ valor +" WHERE id =" + id;
		int res=0;
		try
		{
			System.out.println("ReservaDAO query ejecutado: " +query);
			Statement statement = coneccion.createStatement();
			statement.execute(query);
			res = statement.getUpdateCount();
			
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
			throw new RuntimeException();			
		}
		
		
		return res;
	}
	
	public String get_ultimoQueryBusqueda()
	{
		return this.ultimoQueryBusqueda;
	}
	
}






/* Clase ReservaDAO --> Se encarga de mantener la connecion con la base de datos, ademas contiene las reglas de negocio
 * contiene los metodos CRUD. Es la clase que se va a instanciar en otra para poder usar los metodos que modifiquen la base
 * de datos. Para esto necesita tambien tener una variable coneccion que la recibe desde la clase que la instancie. 
 * 
 * 1 --> Metodo guardar, creamos el String con el Query, en lugar de los valores usamos un " ? " -> por cada variable.
 * esto se hace usando PreparedStatemen, que justamente prepara el Query. 
 * 
 * 2 --> Statement.RETURN_GENERATED_KEYS => devuelve la clave auto generada, para usarla en el momento de guardar los datos
 * y ademas le enviamos el Query en forma de texto 
 * 
 * 3 --> Una vez que tenemos el Query y el dato auto generado del ID, podemos setear los datos que obtenemos como parametro
 * en el mentodo en reseserva
 * 
 * 4 --> Pedimos que se actualize con los datos de PreparedStatemen, la base de datos. 
 * 
 * 5 -->>  ResultSet -> Crea una tabla de los resultados de la insercion, que fue ejecutada con preState.executeUpdate();
 *         ResultSet recibe la clave auto generada por la base de datos a travez del metodo ->  getGeneratedKeys() 
 *         del prepared statemen, una vez obtenidas las claves auto generadas. Hacemos el proceso inverso, le pasamos a la 
 *         reserva, el id que obtubimos en el resultset
 * 
 *  6 -->> devuelve cuantas filas fueron modificadas. 
 * 
 * 
 * 
 * 
 */