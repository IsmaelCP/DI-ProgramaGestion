package es.studium.Practica4DIProgGestion;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaArticulosModif2 extends JFrame implements WindowListener, ActionListener{

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;

	JTextField txtDescripModif2;
	JTextField txtPrecioModif2;
	JTextField txtCantModif2;
	JLabel idArticulosModif1 = new JLabel("idArticulo");

	// Objetos que vamos a controlar
	DlgArticMensajeModif objDlgArticMensajeModif = new DlgArticMensajeModif();
	DlgArticMensajeErrorModif objDlgArticMensajeErrorModif = new DlgArticMensajeErrorModif();
	Modelo modelo = new Modelo();

	// Diálogo Confirmar_Modificaciones Articulo
	Dialog dlgConfModif = new Dialog(this, "Confirmar Baja", true);
	Label lblConfModif = new Label("Confirmar la modificaciones del artículo");
	Button btnConfModifSi = new Button("SI");
	Button btnConfModifNo = new Button("NO");

	public VistaArticulosModif2() {
		setTitle("Modificar_Art\u00EDculos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 403, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Definir el Diálogo Confirmar Modificaciones Artículo
		dlgConfModif.setLayout(new FlowLayout());
		dlgConfModif.add(lblConfModif);
		dlgConfModif.add(btnConfModifSi);
		dlgConfModif.add(btnConfModifNo);
		dlgConfModif.setSize(280, 150);
		dlgConfModif.setLocationRelativeTo(null);
		dlgConfModif.addWindowListener(this);
		btnConfModifSi.addActionListener(this);
		btnConfModifNo.addActionListener(this);

		JLabel lblDescripModif2 = new JLabel("Descripci\u00F3n");
		lblDescripModif2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescripModif2.setBounds(10, 22, 100, 25);
		contentPane.add(lblDescripModif2);

		JLabel lblPrecioModif2 = new JLabel("Precio");
		lblPrecioModif2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrecioModif2.setBounds(10, 54, 100, 19);
		contentPane.add(lblPrecioModif2);

		JLabel lblCantModif2 = new JLabel("Cantidad");
		lblCantModif2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantModif2.setBounds(10, 84, 100, 25);
		contentPane.add(lblCantModif2);

		setTxtDescripModif2(new JTextField());
		getTxtDescripModif2().setColumns(10);
		getTxtDescripModif2().setBounds(103, 26, 274, 20);
		contentPane.add(getTxtDescripModif2());

		setTxtPrecioModif2(new JTextField());
		getTxtPrecioModif2().setColumns(10);
		getTxtPrecioModif2().setBounds(103, 53, 86, 20);
		contentPane.add(getTxtPrecioModif2());

		setTxtCantModif2(new JTextField());
		getTxtCantModif2().setColumns(10);
		getTxtCantModif2().setBounds(103, 84, 86, 20);
		contentPane.add(getTxtCantModif2());

		// Botón Aceptar. Acción --> abre la ventana Confirmar_Modificaciones
		JButton btnAceptarModif2 = new JButton("ACEPTAR");
		btnAceptarModif2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dlgConfModif.setVisible(true);
			}
		});
		btnAceptarModif2.setBounds(21, 130, 89, 23);
		contentPane.add(btnAceptarModif2);

		// Botón Limpiar. Acción --> limpia los TextField de la ventana VistaArticulosModif2
		JButton btnLimpiarModif2 = new JButton("LIMPIAR");
		btnLimpiarModif2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTxtDescripModif2().selectAll();
				getTxtDescripModif2().setText("");
				getTxtPrecioModif2().selectAll();
				getTxtPrecioModif2().setText("");
				getTxtCantModif2().selectAll();
				getTxtCantModif2().setText("");

			}
		});
		btnLimpiarModif2.setBounds(145, 130, 89, 23);
		contentPane.add(btnLimpiarModif2);

		// Botón Volver. Acción --> cierra la ventana VistaArticulosModif2
		JButton btnVolverModif2 = new JButton("VOLVER");
		btnVolverModif2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnVolverModif2.setBounds(275, 130, 89, 23);
		contentPane.add(btnVolverModif2);
		idArticulosModif1.setVisible(false);
		idArticulosModif1.setEnabled(false);
		idArticulosModif1.setBounds(10, 0, 46, 14);
		contentPane.add(idArticulosModif1);
	}

	public JTextField getTxtDescripModif2() {
		return txtDescripModif2;
	}

	public void setTxtDescripModif2(JTextField txtDescripModif2) {
		this.txtDescripModif2 = txtDescripModif2;
	}

	public JTextField getTxtPrecioModif2() {
		return txtPrecioModif2;
	}

	public void setTxtPrecioModif2(JTextField txtPrecioModif2) {
		this.txtPrecioModif2 = txtPrecioModif2;
	}

	public JTextField getTxtCantModif2() {
		return txtCantModif2;
	}

	public void setTxtCantModif2(JTextField txtCantModif2) {
		this.txtCantModif2 = txtCantModif2;
	}

	// Listener
	public void actionPerformed(ActionEvent e) {

		// Acción del botón btnSi (SI) del diálogo Confirmar_Modificaciones - Realiza las modificaciones del artículo y abre el diálogo Mensaje_Modificaciones
		if(btnConfModifSi.equals(e.getSource()))
		{
			try
			{
				// String para guardar lo que ha elegido el usuario en el choice
				// String articuloElegido = chcArticuloBajas.getSelectedItem();
				// String[] arrayCadena = articuloElegido.split(" - ");
				String idArticuloElegido = idArticulosModif1.getText();
				String descripcionArticuloElegido = txtDescripModif2.getText();
				String precioArticuloElegido = txtPrecioModif2.getText();
				String cantidadArticuloElegido = txtCantModif2.getText();
				Connection conn = modelo.conectar();

				double precio = Double.parseDouble(precioArticuloElegido);
				modelo.modificarArticulo(conn, Integer.parseInt(idArticuloElegido), descripcionArticuloElegido, precio , Integer.parseInt(cantidadArticuloElegido));

				// Cierra el diálogo Confirmar_Baja
				dlgConfModif.setVisible(false);
				// Abre el diálogo Mensaje Baja
				objDlgArticMensajeModif.setVisible(true);
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				// Abre el diálogo Error Baja
				objDlgArticMensajeErrorModif.setVisible(true);
			}
		}

		// Acción del botón btnNo (NO) del diálogo Confirmar_Modificaciones Artículo - vuelve a la ventana Modificaciones_Artículos
		if(btnConfModifNo.equals(e.getSource()))
		{
			try
			{
				dlgConfModif.setVisible(false);
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
		// Cierra el diálogo Confirmar_Modificaciones
		if(dlgConfModif.isActive())
		{
			dlgConfModif.setVisible(false);
		}
	}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}
}
