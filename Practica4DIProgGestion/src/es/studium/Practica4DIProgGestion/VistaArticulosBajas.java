package es.studium.Practica4DIProgGestion;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VistaArticulosBajas extends JFrame implements WindowListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;
	Choice chcArticuloBajas;

	// Objetos que vamos a controlar
	DlgArticMensajeBaja objDlgArticMensajeBaja = new DlgArticMensajeBaja();
	DlgArticMensajeErrorBaja objDlgArticMensajeErrorBaja = new DlgArticMensajeErrorBaja();
	Modelo modelo = new Modelo();

	// Diálogo Confirmar_Baja Articulo
	Dialog dlgConfBaja = new Dialog(this, "Confirmar Baja", true);
	Label lblConfBaja = new Label("Confirmar la baja del artículo");
	Button btnConfBajaSi = new Button("SI");
	Button btnConfBajaNo = new Button("NO");

	public VistaArticulosBajas() {
		setTitle("Baja_Art\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 367, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Definir el Diálogo Confirmar Baja Artículo
		dlgConfBaja.setLayout(new FlowLayout());
		dlgConfBaja.add(lblConfBaja);
		dlgConfBaja.add(btnConfBajaSi);
		dlgConfBaja.add(btnConfBajaNo);
		dlgConfBaja.setSize(200, 150);
		dlgConfBaja.setLocationRelativeTo(null);
		dlgConfBaja.addWindowListener(this);
		btnConfBajaSi.addActionListener(this);
		btnConfBajaNo.addActionListener(this);

		JLabel lblNewLabel = new JLabel("Escoge un Art\u00EDculo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 23, 147, 23);
		contentPane.add(lblNewLabel);

		chcArticuloBajas = new Choice();
		chcArticuloBajas.setBounds(10, 46, 169, 20);
		contentPane.add(chcArticuloBajas);
		// Mostrar los artículos en el choice chcArticuloBajas conectando con la BD
		ResultSet articulos = null;
		Connection conArticulo = null;
		conArticulo = modelo.conectar();
		articulos = modelo.consultaArticulos(conArticulo);
		try {
			String id;
			String descripcion;
			while(articulos.next()) {
				id = articulos.getInt("idArticulos")+"";
				descripcion = articulos.getString("descripcionArticulo");
				chcArticuloBajas.add(id+" - "+descripcion);
			}
			modelo.desconectar(conArticulo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		// Botón Volver. Acción --> vuelve al Menú Principal
		JButton btnVolverBajas = new JButton("VOLVER");
		btnVolverBajas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolverBajas.setBounds(252, 46, 89, 23);
		contentPane.add(btnVolverBajas);

		// Botón Eliminar. Acción --> abre el Diálogo Confirmar_Baja 
		JButton btnEliminarBajas = new JButton("ELIMINAR");
		btnEliminarBajas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dlgConfBaja.setVisible(true);
			}
		});
		btnEliminarBajas.setBounds(252, 126, 89, 23);
		contentPane.add(btnEliminarBajas);

	}

	// Listener
	public void actionPerformed(ActionEvent e) {

		// Acción del botón btnSi (SI) del diálogo Confirmar_Baja - Realiza la baja del artículo y abre el diálogo Mensaje_Baja
		if(btnConfBajaSi.equals(e.getSource()))
		{
			try
			{
				// String para guardar lo que ha elegido el usuario en el choice
				String articuloElegido = chcArticuloBajas.getSelectedItem();
				String[] arrayCadena = articuloElegido.split(" - ");
				Connection conn = modelo.conectar();
				// return que recoje la respuesta de ok o error
				int exito = modelo.eliminarArticulo(conn, Integer.parseInt(arrayCadena[0]));
				if(exito==0)
				{
					// Cierra el diálogo Confirmar_Baja
					dlgConfBaja.setVisible(false);
					// Abre el diálogo Mensaje Baja
					objDlgArticMensajeBaja.setVisible(true);
				}
				else
				{
					// Abre el diálogo Error Baja
					objDlgArticMensajeErrorBaja.setVisible(true);
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}

		// Acción del botón btnNo (NO) del diálogo Confirmar_Baja - vuelve a la ventana Artículos_Bajas
		if(btnConfBajaNo.equals(e.getSource()))
		{
			try
			{
				dlgConfBaja.setVisible(false);
				this.setVisible(true);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}

	}

	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0) {}
	public void windowClosing(WindowEvent arg0) {
		// Cierra el diálogo Confirmar_Baja
		if(dlgConfBaja.isActive())
		{
			dlgConfBaja.setVisible(false);
		}
	}
	
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

}
