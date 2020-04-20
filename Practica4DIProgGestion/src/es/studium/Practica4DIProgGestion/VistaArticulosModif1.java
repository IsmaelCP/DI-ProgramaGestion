package es.studium.Practica4DIProgGestion;

import java.awt.Choice;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Crea la vista Modificación_Artículos
 * @author Ismael
 */
public class VistaArticulosModif1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Declara los objetos que controla
	 * VistaArticulosModif2
	 * Modelo
	 */
	VistaArticulosModif2 objVistaArticulosModif2 = new VistaArticulosModif2();
	Modelo modelo = new Modelo();

	/**
	 * Constructor sin parámetros 
	 */
	public VistaArticulosModif1() {
		setTitle("Modificaci\u00F3n_Art\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 362, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblArticuloModif1 = new JLabel("Escoge un Art\u00EDculo");
		lblArticuloModif1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblArticuloModif1.setBounds(10, 27, 147, 23);
		contentPane.add(lblArticuloModif1);

		Choice chcArticuloModif1 = new Choice();
		chcArticuloModif1.setBounds(10, 50, 169, 20);
		contentPane.add(chcArticuloModif1);
		// Mostrar los artículos en el choice chcArticuloBajas conectando con la BD
		ResultSet articulos = null;
		Connection conArticulo = null;
		conArticulo = modelo.conectar();
		articulos = modelo.consultaArticulos(conArticulo);
		try {
			String id;
			String descripcion;
			String precio;
			String cantidad;
			while(articulos.next()) {
				id = articulos.getInt("idArticulos")+"";
				descripcion = articulos.getString("descripcionArticulo");
				precio = articulos.getDouble("precioArticulo")+"";
				cantidad = articulos.getInt("cantidadArticulo")+"";
				chcArticuloModif1.add(id+" - "+descripcion+" - "+precio+" - "+cantidad);
			}
			modelo.desconectar(conArticulo);
		}catch(SQLException e) {
			e.printStackTrace();
		}

		JButton btnVolverModif1 = new JButton("VOLVER");
		btnVolverModif1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolverModif1.setBounds(248, 36, 89, 23);
		contentPane.add(btnVolverModif1);

		JButton btnAceptarModif1 = new JButton("ACEPTAR");
		btnAceptarModif1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Rellenar los TextField con los datos del artículo elegido en los choices
				try {
					String elementoElegido = chcArticuloModif1.getSelectedItem();
					String[] arrayCadena = elementoElegido.split(" - ");

					int id = Integer.parseInt(arrayCadena[0]);
					String descripcion = arrayCadena[1]; 
					double precio = Double.parseDouble(arrayCadena[2]);
					int cantidad = Integer.parseInt(arrayCadena[3]);

					// Rellenar los TextField de la VistaArticulosModif2
					objVistaArticulosModif2.idArticulosModif1.setText(id+"");
					objVistaArticulosModif2.getTxtDescripModif2().setText(descripcion);
					objVistaArticulosModif2.getTxtPrecioModif2().setText(precio+"");
					objVistaArticulosModif2.getTxtCantModif2().setText(cantidad+"");

					// Abre la Vista Modificar_Artículos
					objVistaArticulosModif2.setVisible(true);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnAceptarModif1.setBounds(248, 112, 89, 23);
		contentPane.add(btnAceptarModif1);
	}
}
