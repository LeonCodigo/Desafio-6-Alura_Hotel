package views;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Reserva;
import controller.ReservasController;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	 String ultimoBuscado = "";
	
	private ReservasController reservaController = new ReservasController() ;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Busqueda() 
	{
		
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		
		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setForeground(new Color(12, 138, 199));
		lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
		lblNewLabel_4.setBounds(331, 62, 300, 42);
		contentPane.add(lblNewLabel_4);
		
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setBackground(new Color(12, 138, 199));
		panel.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);

		
		
		
		tbReservas = new JTable();
		tbReservas.setShowHorizontalLines(false);
		tbReservas.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Dialog", Font.BOLD, 14));
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("id") ;
		modelo.addColumn("fecha_entrada");
		modelo.addColumn("fecha_salida");
		modelo.addColumn("valor");
		modelo.addColumn("forma_pago");
		
		modelo.addRow(new Object[] { "      N° Reserva ", "      Fecha ingreso " , "      Fecha egreso " , "                Valor💲 " , " Forma de Pago 💳"});
		modelo.addRow(new Object[] { "                 ⬇", "                 ⬇" , "                 ⬇" , "        "+"           ⬇" , "                ⬇"});
		 
		 
		
		
		tbHuespedes = new JTable();
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		
		modeloH.addRow(new Object[] { " N° Huesped ", " Nombre " , " Apellido " , " Fecha nacimiento " , " Nacionalidad " , " Telefono ", " N° de Reserva " });
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		
		JPanel header = new JPanel();
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			     
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		
		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(12, 138, 199));
				labelAtras.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				 btnAtras.setBackground(Color.white);
			     labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		header.add(btnAtras);
		
		labelAtras = new JLabel("<");
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
				 btnexit.setBackground(Color.white);
			     labelExit.setForeground(Color.black);
			}
		});
		btnexit.setLayout(null);
		btnexit.setBackground(Color.WHITE);
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.BLACK);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(12, 138, 199));
		separator_1_2.setBackground(new Color(12, 138, 199));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
		
        
			@Override
			public void mouseClicked(MouseEvent e)
			{
				
				leer(txtBuscar.getText());
				ultimoBuscado=txtBuscar.getText();
				
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(12, 138, 199));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		
		JLabel lblBuscar = new JLabel("BUSCAR");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
		
		JPanel btnEditar = new JPanel();
		btnEditar.setLayout(null);
		btnEditar.setBackground(new Color(12, 138, 199));
		btnEditar.setBounds(635, 508, 122, 35);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditar);
		
		JLabel lblEditar = new JLabel("EDITAR");
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEditar.setBounds(0, 0, 122, 35);
		btnEditar.add(lblEditar);
		
		// E D I T A R
		
		lblEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(tbReservas.getSelectedRow()>1 && tbReservas.getSelectedColumn()>2)
				{
				
				Object objeto_DatoDeFila =modelo.getValueAt( tbReservas.getSelectedRow(), 0 );			    
				if(0==JOptionPane.showConfirmDialog(tbReservas, " ¿Desea modificar el registro con N° de reserva: " + objeto_DatoDeFila +" ?"))
				{
				  cartel("Se selecciono la columna: "+ modelo.getColumnName(tbReservas.getSelectedColumn()));
				String col = modelo.getColumnName(tbReservas.getSelectedColumn());
				String valor = modificar_nuevoValor(tbReservas.getSelectedColumn());
				 modificar(objeto_DatoDeFila.toString(),col,valor);
				}
				}
				else
				{
					cartel("Solo puede modificar el ( Valor 💲 ) y ( forma de pago 💳 ) de la reserva, seleccione la columna deseada ");
				}
				
				
			}

			

			
		});
		
		// F I N   E D I T A R
		
		
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(12, 138, 199));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		
		JLabel lblEliminar = new JLabel("ELIMINAR");
		
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
		
		lblEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(tbReservas.getSelectedRow()>1 && tbReservas.getSelectedColumn()==0)
				{
				
				Object objeto_DatoDeFila =modelo.getValueAt( tbReservas.getSelectedRow(), tbReservas.getSelectedColumn() );			    
				if(0==JOptionPane.showConfirmDialog(tbReservas, " ¿Desea eliminar el registro con N° de reserva: " +objeto_DatoDeFila +" ?"))
				{
				eliminar(objeto_DatoDeFila.toString());
				
				}
				}
				else
				{
					cartel("Debe seleccionar N° DE RESERVA dentro del cuadro ");
				}
			
			}
		});
		
	}
	
	
	   public void leer(String pBuscado)
	   {
		   List<Reserva> reservas = reservaController.leer(pBuscado);		   
		   
		    borraFilas();
			 

		reservas.forEach(re ->  modelo.addRow(new Object[] {"                  "+re.getId().toString(),"      "+re.getFechaEn(),""
				+ "      "+ re.getFechaSal(),"      "+ re.getValor()  ,"   "+ re.getFormaPago()}   ) );

		
	   }
	   
	   public void eliminar(String id)
	   {
		int eliminados = reservaController.eliminar(id);
		   cartel("Se eliminaron " +eliminados+" registros." );
		   leer(ultimoBuscado);
	   }
	   
	   private void modificar(String id, String col, String valor) 
	    {
			
			int modificados = reservaController.modificar(id,col,valor);
			cartel("Se modificaron " +modificados+" registros." );
			 leer(ultimoBuscado);
		}
	   
	   public void cartel(String mensaje)
	   {
		   JOptionPane.showMessageDialog(contentPane, mensaje);
		   
	   }
	   
	   public void borraFilas()
	   {
		   for(int i=modelo.getRowCount(); i>1; i--)
		   {
			   if(i>2) { modelo.removeRow(i-1);}			     
		   }
	   }
	   
	   public String modificar_nuevoValor(Integer col_seleccionada)
		{
			String res="";
			
			if(col_seleccionada == 3)
			{
				res= JOptionPane.showInputDialog("Ingrese nuevo valor 💲: ");
			}
			if(col_seleccionada == 4)
			{
				Object opciones[] = {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo"};
				int opcion= JOptionPane.showOptionDialog(contentPane, "Seleccione nueva forma de pago", "Formas de pago", 0, 1, null, opciones, opciones[2]);
				
				if(opcion==0) {res = "'"+opciones[0].toString()+"'"; }
				else if(opcion==1) {res ="'"+ opciones[1].toString()+"'"; }
				else {res ="'"+opciones[2].toString()+"'";}
			}
			
			return res;
		}
	   
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}