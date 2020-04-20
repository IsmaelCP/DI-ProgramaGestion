package es.studium.Practica4DIProgGestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Crea la vista Men� Principal
 * @author Ismael
 */
public class VistaMPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar mbarMenu = new JMenuBar();
	private JMenu mnArticulos = new JMenu("Art\u00EDculos");
	private JMenuItem mniAltasArticulos = new JMenuItem("Altas");
	private JMenuItem mniBajasArticulos = new JMenuItem("Bajas");
	private JMenuItem mniModifArticulos = new JMenuItem("Modificaciones");
	private JMenuItem mniConsultasArticulos = new JMenuItem("Consultas");
	private JMenu mnTickets = new JMenu("Tickets");
	private JMenuItem mniAltasTickets = new JMenuItem("Altas");
	private JMenuItem mniConsultasTickets = new JMenuItem("Consultas");
	private JMenu mnImprimir = new JMenu("iReports");
	private JMenuItem mniArticulos = new JMenuItem("Informe Art\u00EDculos");
	private JMenuItem mniTickets = new JMenuItem("Informe Tickets");
	
	/**
	 * Declara los objetos que controla
	 * DlgMPrincipalSalir
	 * AyudaMPrincipal
	 * VistaArticulosAltas
	 * VistaArticulosBajas
	 * VistaArticulosModif1
	 * VistaArticulosConsultas
	 * DlgTicketsAltaFecha
	 * VistaTicketsConsultas
	 * DlgConsultaTickets
	 * InformeArticulos
	 * Modelo
	 */
	DlgMPrincipalSalir objDlgMPrincSalir = new DlgMPrincipalSalir();
	AyudaMPrincipal objAyudaMPrinc = new AyudaMPrincipal();
	VistaArticulosAltas objVArticulosAltas = new VistaArticulosAltas();
	VistaArticulosBajas objVArticulosBajas = new VistaArticulosBajas();
	VistaArticulosModif1 objVArticulosModif1 = new VistaArticulosModif1();
	VistaArticulosConsultas objVArticulosConsultas = new VistaArticulosConsultas();
	DlgTicketsAltaFecha objDlgTicketsAltaFecha = new DlgTicketsAltaFecha();
	VistaTicketsConsultas objVTicketsConsultas = new VistaTicketsConsultas();
	DlgConsultaTickets objDlgConsultaTickets = new DlgConsultaTickets();
	InformeArticulos informeArtic = new InformeArticulos();
	Modelo modelo = new Modelo();

	/**
	 * Constructor sin par�metros 
	 */
	public VistaMPrincipal() {
		setTitle("Men\u00FA Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 348, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		mbarMenu.setBounds(0, 0, 156, 21);
		contentPane.add(mbarMenu);

		mbarMenu.add(mnArticulos);
		// Men� Item Altas Art�culos
		mniAltasArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objVArticulosAltas.setVisible(true);
			}
		});
		mnArticulos.add(mniAltasArticulos);

		// Men� Item Bajas Art�culos
		mniBajasArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objVArticulosBajas.setVisible(true);
			}
		});
		mnArticulos.add(mniBajasArticulos);

		// Men� Item Modificaciones Art�culos
		mniModifArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objVArticulosModif1.setVisible(true);
			}
		});
		mnArticulos.add(mniModifArticulos);

		// Men� Item Consultas Art�culos
		mniConsultasArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objVArticulosConsultas.actualizar();
				objVArticulosConsultas.setVisible(true);
			}
		});
		mnArticulos.add(mniConsultasArticulos);

		mbarMenu.add(mnTickets);
		// Men� Item Altas Tickets
		mniAltasTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objDlgTicketsAltaFecha.setVisible(true);
			}
		});
		mnTickets.add(mniAltasTickets);

		// Men� Item Consultas Tickets
		mniConsultasTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objVTicketsConsultas.setVisible(true);
			}
		});
		mnTickets.add(mniConsultasTickets);
		
		mbarMenu.add(mnImprimir);	
		// Men� Item iReport - Art�culos
		mniArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				informeArtic.infoArtic();
			}
		});
		mnImprimir.add(mniArticulos);
		
		// Men� Item iReport - Tickets
		mniTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objDlgConsultaTickets.setVisible(true);
			}
		});
		mnImprimir.add(mniTickets);

		// Bot�n Salir del Men� Principal
		JButton btnSalirMPrinc = new JButton("SALIR");
		btnSalirMPrinc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objDlgMPrincSalir.setVisible(true);
			}
		});
		btnSalirMPrinc.setBounds(49, 132, 89, 23);
		contentPane.add(btnSalirMPrinc);

		// Bot�n Ayuda del Men� Principal
		JButton btnAyudaMPrinc = new JButton("AYUDA");
		btnAyudaMPrinc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				objAyudaMPrinc.setVisible(true);
			}
		});
		btnAyudaMPrinc.setBounds(204, 132, 89, 23);
		contentPane.add(btnAyudaMPrinc);
	}
}
