package es.studium.Practica4DIProgGestion;

import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaTicketsAlta extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final AbstractButton txtAgregarTicktes = null;
	private JPanel contentPane;
	private JTextField txtCantTickets;

	// Objetos que vamos a controlar
	DlgTicketsMensajeAlta objDlgTicketsMensajeAlta = new DlgTicketsMensajeAlta();
	DlgTicketsMensajeErrorAlta objDlgTicketsMensajeErrorAlta = new DlgTicketsMensajeErrorAlta();
	Modelo modelo = new Modelo();
	private JTextField txtTotalTck;
	double total = 0;

	public VistaTicketsAlta() {
		setTitle("Alta_Tickets");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 531, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSeleccionarArt = new JLabel("Selecciona los art\u00EDculos");
		lblSeleccionarArt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSeleccionarArt.setBounds(10, 11, 287, 20);
		contentPane.add(lblSeleccionarArt);

		JLabel lblArticulosTickets = new JLabel("Art\u00EDculos");
		lblArticulosTickets.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblArticulosTickets.setBounds(10, 42, 70, 25);
		contentPane.add(lblArticulosTickets);

		// Choice para elegir los artículos
		Choice chcArticulosTickets = new Choice();
		chcArticulosTickets.setBounds(82, 42, 215, 20);
		contentPane.add(chcArticulosTickets);
		// Mostrar los artículos en el choice chcArticuloBajas conectando con la BD
		ResultSet articulos = null;
		Connection conArticulo = null;
		conArticulo = modelo.conectar();
		articulos = modelo.consultaArticulos(conArticulo);
		try {
			int id;
			String descripcion;
			String importe;
			while(articulos.next()) {
				id = articulos.getInt("idArticulos");
				descripcion = articulos.getString("descripcionArticulo");
				importe = articulos.getDouble("precioArticulo")+"";
				chcArticulosTickets.add(id + " - " + descripcion + " - " + importe);
			}
			modelo.desconectar(conArticulo);
		}catch(SQLException e) {
			e.printStackTrace();
		}

		JLabel lblCantTickets = new JLabel("Cantidad");
		lblCantTickets.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantTickets.setBounds(10, 77, 62, 20);
		contentPane.add(lblCantTickets);

		txtCantTickets = new JTextField();
		txtCantTickets.setColumns(10);
		txtCantTickets.setBounds(82, 79, 43, 20);
		contentPane.add(txtCantTickets);

		JTextArea txtAgregarTickets = new JTextArea("Artículo (id-Descripción-Precio) / Cantidad (ud.) / Subtotal (€)",5,100);
		txtAgregarTickets.setBounds(102, 119, 381, 125);
		contentPane.add(txtAgregarTickets);

		// Botón Agregar. Acción --> 
		// 1. Agrega el artículo, la cantidad y el subtotal en el txtAgregarTickets
		// 2. Sumar los subtotales y mostrarlos en el txtTotalTck
		// 3. Realizar un INSERT en la tabla "incluyen" --> idArticuloFK1, idTicketFK2 y Cantidad
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1. Agrega el artículo, la cantidad y el subtotal en el txtAgregarTickets
				// Obtener el importe del artículo
				//int importeArticulo = Integer.parseInt(txtImporteTickets.getText());

				// Obtener el artículo elegido en el chcArticulosTickets
				String artElegidoTck = chcArticulosTickets.getSelectedItem();

				// Guardar en una variable el precio del artículo elegido
				String[] arrayCadena = artElegidoTck.split(" - ");
				double precioArtTck = Double.parseDouble(arrayCadena[2]);

				// Obtener la cantidad de artículos del txtCantTickets
				double cantidad = Double.parseDouble(txtCantTickets.getText());
				int cantidadMostrar = (int)cantidad;

				// Obtener el subtotal
				double subtotal = cantidad * precioArtTck;
				DecimalFormat df = new DecimalFormat("#.##");

				// 2. Suma de los subtotales y los muestro en el txtTotalTck
				total = total + subtotal;
				txtTotalTck.setText(total+"€");

				// Mostrar en txtAgregarTickets el artículo, la cantidad y el subtotal
				txtAgregarTickets.setText(txtAgregarTickets.getText()+"\n"+artElegidoTck+ " / " + cantidadMostrar+ " / " + df.format(subtotal)+"€");

				// 3. Realizar un INSERT en la tabla "incluyen" --> idArticuloFK1, idTicketFK2 y Cantidad
				try
				{			
					// Obtener el id del artículo elegido en el chcArticulosTickets 
					int idArt = Integer.parseInt(arrayCadena[0]);

					// Obtener el numTicket creado
					int numTicket = 0;
					Connection con = null;
					con = modelo.conectar();
					numTicket = modelo.idTicket(con);

					// Realizar el INSERT en la tabla "incluyen"
					String sentenciaSQL = "INSERT INTO incluyen VALUES(null, '"+idArt+"', '"+numTicket+"', '"+cantidadMostrar+"' )";   
					Statement statement = null;
					try {
						statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
						statement.executeUpdate(sentenciaSQL);
					}catch(SQLException ex) 
					{
						ex.printStackTrace();
						// En caso de Error muestra el Diálogo Mensaje Error_Alta
						objDlgTicketsMensajeErrorAlta.setVisible(true);
					}

					modelo.desconectar(con);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();				
				}	 
			}
		});

		btnAgregar.setBounds(10, 120, 82, 23);
		contentPane.add(btnAgregar);


		// Botón Finalizar Ticket. Acción --> 
		// Agrega en la tabla "tickets" el importe total en la columna "importeTicket"  
		JButton btnAceptarAltaTickets = new JButton("Finalizar Ticket");
		btnAceptarAltaTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// UPDATE de tickets para meter lo que hay en el txtTotalTck
				try
				{				
					// Obtener el importe total del ticket
					String imp = txtTotalTck.getText();
					// Con la siguiente línea elimino el símbolo del €
					String cadena = imp.substring(0, imp.length()-1);
					double importeTotal = Double.parseDouble(cadena);
					

					// Obtener el numTicket creado
					int numTicket = 0;
					Connection con = null;
					con = modelo.conectar();
					numTicket = modelo.idTicket(con);
					
					// Realizar UPDATE desde el método modificarTickets en el Modelo
					modelo.modificarTickets(con, numTicket, importeTotal);
					modelo.desconectar(con);
					
					// Mensaje de Alta realiza correctamente
					objDlgTicketsMensajeAlta.setVisible(true);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();	
					// En caso de Error muestra el Diálogo Mensaje Error_Alta
					objDlgTicketsMensajeErrorAlta.setVisible(true);
				}	 
			}	
		});

		JLabel lblTotalTck = new JLabel("TOTAL");
		lblTotalTck.setBounds(364, 269, 53, 14);
		contentPane.add(lblTotalTck);

		txtTotalTck = new JTextField();
		txtTotalTck.setColumns(10);
		txtTotalTck.setBounds(413, 266, 70, 20);
		contentPane.add(txtTotalTck);
		btnAceptarAltaTickets.setBounds(45, 309, 123, 23);
		contentPane.add(btnAceptarAltaTickets);

		// Botón Limpiar. Acción --> Limpia el contenido de los TextField
		JButton btnLimpiarAltaTickets = new JButton("Limpiar");
		btnLimpiarAltaTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCantTickets.selectAll();
				txtCantTickets.setText("");
			}
		});
		btnLimpiarAltaTickets.setBounds(216, 309, 89, 23);
		contentPane.add(btnLimpiarAltaTickets);

		// Botón Cancelar Ticket. Acción --> 
		// 1. Elimina el registro de la tabla "incluyen" con el idTicketFK2 
		// 2. Elimina el último registro creado de la tabla "tickets" 
		// 3. Cierra la ventana Alta Tickets
		JButton btnVolverAltaTickets = new JButton("Cancelar Ticket");
		btnVolverAltaTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 1. Elimina el registro de la tabla "incluyen" con el idTicketFK2  
				try
				{
					// Obtener el numTicket creado
					int numTicket = 0;
					Connection con = null;
					con = modelo.conectar();
					numTicket = modelo.idTicket(con);
					
					// Realizar DELETE desde el método modificarTickets en el Modelo
					modelo.eliminarRegistroIncluyen(con, numTicket);
					modelo.desconectar(con);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();				
				}	

				// 2. Elimina el último registro creado de la tabla "tickets" 
				try
				{
					// Obtener el numTicket creado
					int numTicket = 0;
					Connection con = null;
					con = modelo.conectar();
					numTicket = modelo.idTicket(con);
					
					// Realizar DELETE desde el método modificarTickets en el Modelo
					modelo.eliminarRegistroTicket(con, numTicket);
					modelo.desconectar(con);
				}
				catch(Exception ex)
				{
					ex.printStackTrace();				
				}	
				
				// 3. Cierra la ventana VistaTicketsAlta
				dispose();
			}
		});
		btnVolverAltaTickets.setBounds(349, 309, 123, 23);
		contentPane.add(btnVolverAltaTickets);

	}
}
