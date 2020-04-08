package es.studium.Practica4DIProgGestion;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaArticulosAltas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDescripAlta;
	private JTextField txtPrecioAlta;
	private JTextField txtCantAlta;

	// Objetos que vamos a controlar
	DlgArticMensajeAlta objDlgArticMensajeAlta = new DlgArticMensajeAlta();
	DlgArticMensajeErrorAlta objDlgArticMensajeErrorAlta = new DlgArticMensajeErrorAlta();
	Modelo modelo = new Modelo();

	public VistaArticulosAltas() {
		setTitle("Altas_Art\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 413, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDescripAlta = new JLabel("Descripci\u00F3n");
		lblDescripAlta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescripAlta.setBounds(10, 11, 100, 25);
		contentPane.add(lblDescripAlta);

		JLabel lblPrecioAlta = new JLabel("Precio");
		lblPrecioAlta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrecioAlta.setBounds(10, 43, 100, 19);
		contentPane.add(lblPrecioAlta);

		JLabel lblCantAlta = new JLabel("Cantidad");
		lblCantAlta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantAlta.setBounds(10, 73, 100, 25);
		contentPane.add(lblCantAlta);

		txtDescripAlta = new JTextField();
		txtDescripAlta.setBounds(103, 15, 281, 20);
		contentPane.add(txtDescripAlta);
		txtDescripAlta.setColumns(10);

		txtPrecioAlta = new JTextField();
		txtPrecioAlta.setBounds(103, 42, 86, 20);
		contentPane.add(txtPrecioAlta);
		txtPrecioAlta.setColumns(10);

		txtCantAlta = new JTextField();
		txtCantAlta.setBounds(103, 73, 86, 20);
		contentPane.add(txtCantAlta);
		txtCantAlta.setColumns(10);

		JButton btnAceptarAlta = new JButton("ACEPTAR");
		btnAceptarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// String para guardar los datos de los Txt
				String descripcion = txtDescripAlta.getText();
				String precio = txtPrecioAlta.getText();
				String cantidad = txtCantAlta.getText();

				// Conectar con la BD desde el Modelo
				Connection conn = modelo.conectar();

				// Sentencia para INSERTAR los datos en la tabla artículos
				String sentenciaSQL = "INSERT INTO articulos(descripcionArticulo, precioArticulo, cantidadArticulo) VALUES ('"+descripcion+"', '"+precio+"', '"+cantidad+"' )";   
				Statement statement = null;
				try {
					statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
					statement.executeUpdate(sentenciaSQL);
					// Abre el Diálogo Mensaje_Alta
					objDlgArticMensajeAlta.setVisible(true);
				}catch(SQLException ex) 
				{
					ex.printStackTrace();
					// En caso de Error muestra el Diálogo Mensaje Error_Alta
					objDlgArticMensajeErrorAlta.setVisible(true);
				}
			}
		});
		btnAceptarAlta.setBounds(21, 114, 89, 23);
		contentPane.add(btnAceptarAlta);

		JButton btnLimpiarAlta = new JButton("LIMPIAR");
		btnLimpiarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDescripAlta.selectAll();
				txtDescripAlta.setText("");
				txtPrecioAlta.selectAll();
				txtPrecioAlta.setText("");
				txtCantAlta.selectAll();
				txtCantAlta.setText("");
			}
		});
		btnLimpiarAlta.setBounds(151, 114, 89, 23);
		contentPane.add(btnLimpiarAlta);

		JButton btnVolverAlta = new JButton("VOLVER");
		btnVolverAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolverAlta.setBounds(281, 114, 89, 23);
		contentPane.add(btnVolverAlta);
	}

}