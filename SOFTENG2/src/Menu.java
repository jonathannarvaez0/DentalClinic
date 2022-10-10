import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;

public class Menu extends JFrame {
	private Image img_logo = new ImageIcon ( Main.class.getResource ( "imgs/logo.png" ) ).getImage ().getScaledInstance ( 120, 120, Image.SCALE_SMOOTH );
	private Image img_dashboard = new ImageIcon ( Main.class.getResource ( "imgs/dashboard.png" ) ).getImage ().getScaledInstance ( 40,50, Image.SCALE_SMOOTH );
	private Image img_appointment = new ImageIcon (Main.class.getResource( "imgs/appointment.png" ) ).getImage ().getScaledInstance ( 40,50, Image.SCALE_SMOOTH );
	private Image img_records = new ImageIcon ( Main.class.getResource ( "imgs/records.png" ) ).getImage().getScaledInstance ( 40, 50, Image.SCALE_SMOOTH );
	private Image img_dentists = new ImageIcon ( Main.class.getResource ( "imgs/dentist.png" ) ).getImage ().getScaledInstance( 40, 50, Image.SCALE_SMOOTH );
	private Image img_services = new ImageIcon ( Main.class.getResource ( "imgs/services.png" ) ).getImage().getScaledInstance ( 40, 50, Image.SCALE_SMOOTH );
	private Image img_logout = new ImageIcon ( Main.class.getResource ( "imgs/logout.png" ) ).getImage().getScaledInstance( 40, 50, Image.SCALE_SMOOTH );
	private JPanel contentPane;
	JLayeredPane layeredPane = new JLayeredPane();
	private Dashboard dashboard;
	private Records records;
	private Doctors doctors;
	private Services services;
	private Appointments appointments;
	
	public Menu () {
		components();
	}
		
	public void components () {
		
		//MAIN WINDOW
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setBounds ( 100, 100, 800, 500 );
		setUndecorated ( true );
		contentPane = new JPanel ();
		contentPane.setBackground ( new Color ( 47, 79, 79 ) );
		contentPane.setBorder ( new LineBorder ( new Color ( 0, 0, 128 ), 2 ) );
		setContentPane ( contentPane );
		contentPane.setLayout ( null );
		
		dashboard = new Dashboard();
		records = new Records();
		doctors = new Doctors();
		services = new Services();
		//appointments = new Appointments();
		
		//MENU PANEL
		JPanel panelMenu = new JPanel ();
		panelMenu.setBackground ( new Color ( 0, 128, 128 ) );
		panelMenu.setBounds ( 0, 0, 260, 500 );
		contentPane.add ( panelMenu );
		panelMenu.setLayout ( null );
		
		//LOGO IMAGE ICON
		JLabel lblIcon = new JLabel ( "" );
		lblIcon.setHorizontalAlignment ( SwingConstants.CENTER );
		lblIcon.setBounds( 10, 11, 230, 140 );
		panelMenu.add ( lblIcon );
		lblIcon.setIcon ( new ImageIcon ( img_logo ) );
		
		//DASHBOARD PANEL
		JPanel panelDashboard = new JPanel ( );
		panelDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dashboard = new Dashboard();
				switchPanel(dashboard);
				}
			}
		);
		panelDashboard.addMouseListener(new PanelButtonMouseAdapter(panelDashboard));
		panelDashboard.setBackground ( new Color ( 0, 128, 128 ) );
		panelDashboard.setBounds ( 0, 163, 260, 50 );
		panelMenu.add ( panelDashboard );
		panelDashboard.setLayout ( null );
		
		//DASHBOARD LABEL
		JLabel lblNewLabel = new JLabel ( "Dashboard" );
		lblNewLabel.setForeground ( new Color ( 255, 255, 255 ) );
		lblNewLabel.setBackground ( new Color ( 255, 255, 255 ) );
		lblNewLabel.setFont ( new Font ( "Dialog", Font.BOLD, 14 ) );
		lblNewLabel.setBounds ( 70, 8, 95, 28 );
		panelDashboard.add ( lblNewLabel );
		
		//DASHBOARD ICON IMAGE
		JLabel lblDashboardIcon = new JLabel ( "" );
		lblDashboardIcon.setBounds ( 20, -1, 40, 51 );
		panelDashboard.add ( lblDashboardIcon );
		lblDashboardIcon.setIcon ( new ImageIcon ( img_dashboard ) );
		
		//APPOINTMENTS PANEL
		JPanel panelAppointments = new JPanel ();
		panelAppointments.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				appointments = new Appointments();
				switchPanel(appointments);
				}
			}
		);
		panelAppointments.addMouseListener(new PanelButtonMouseAdapter(panelAppointments));
		panelAppointments.setBackground ( new Color ( 0, 128, 128 ) );
		panelAppointments.setBounds ( 0, 213, 260, 50 );
		panelMenu.add ( panelAppointments );
		panelAppointments.setLayout ( null );
		
		//APPOINTMENTS LABEL
		JLabel labelAppointment = new JLabel ( "Appointment" );
		labelAppointment.setForeground ( Color.WHITE );
		labelAppointment.setFont ( new Font ( "Dialog", Font.BOLD, 14 ) );
		labelAppointment.setBackground ( Color.WHITE );
		labelAppointment.setBounds ( 70, 11, 95, 28 );
		panelAppointments.add ( labelAppointment );
		
		//APPOINTMENTS ICON IMAGE
		JLabel lblAppointmentIcon = new JLabel ( "" );
		lblAppointmentIcon.setBounds ( 20, 0, 40, 50 );
		panelAppointments.add ( lblAppointmentIcon );
		lblAppointmentIcon.setIcon ( new ImageIcon(img_appointment ) );
		
		//RECORDS PANEL
		JPanel panelRecords = new JPanel ();
		panelRecords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				records = new Records();
				switchPanel(records);
				}
			}
		);
		panelRecords.addMouseListener(new PanelButtonMouseAdapter(panelRecords));
		panelRecords.setBackground ( new Color (0, 128, 128 ) );
		panelRecords.setBounds ( 0, 263, 260, 50 );
		panelMenu.add ( panelRecords );
		panelRecords.setLayout ( null );
		
		//RECORDS LABEL
		JLabel lblRecords = new JLabel ( "Records" );
		lblRecords.setForeground  ( Color.WHITE );
		lblRecords.setFont ( new Font ( "Dialog", Font.BOLD, 14 ) );
		lblRecords.setBackground ( Color.WHITE );
		lblRecords.setBounds ( 70, 11, 80, 28 );
		panelRecords.add ( lblRecords );
		
		//RECORDS ICON IMAGE
		JLabel lblRecordsIcon = new JLabel ( "" ) ;
		lblRecordsIcon.setBounds ( 20, 0, 40, 50 );
		panelRecords.add ( lblRecordsIcon );
		lblRecordsIcon.setIcon ( new ImageIcon ( img_records ) );
		
		//DOCTORS PANEL
		JPanel panelDoctors = new JPanel ();
		panelDoctors.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanel(doctors);
				}
			}
		);
		panelDoctors.addMouseListener(new PanelButtonMouseAdapter(panelDoctors));
		panelDoctors.setBackground ( new Color ( 0, 128, 128 ) );
		panelDoctors.setBounds ( 0, 313, 260, 50 );
		panelMenu.add ( panelDoctors );
		panelDoctors.setLayout ( null );
		
		//DOCTORS LABEL
		JLabel lblDentists = new JLabel ( "Dentists" );
		lblDentists.setForeground ( Color.WHITE );
		lblDentists.setFont (new Font ( "Dialog", Font.BOLD, 14 ) );
		lblDentists.setBackground ( Color.WHITE );
		lblDentists.setBounds ( 70, 11, 80, 28 );
		panelDoctors.add ( lblDentists );
		
		//DOCTORS IMAGE ICON
		JLabel lblDentistsIcon = new JLabel ( "" );
		lblDentistsIcon.setBounds ( 20, 0, 40, 50 );
		panelDoctors.add ( lblDentistsIcon );
		lblDentistsIcon.setIcon ( new ImageIcon ( img_dentists ) );

		//SERVICES PANEL
		JPanel panelServices = new JPanel ();
		panelServices.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				switchPanel(services);
				}
			}
		);
		panelServices.addMouseListener(new PanelButtonMouseAdapter(panelServices));
		panelServices.setBackground ( new Color ( 0, 128, 128 ) );
		panelServices.setBounds ( 0, 360, 260, 50 );
		panelMenu.add ( panelServices );
		panelServices.setLayout ( null );
		
		//SERVICES LABEL
		JLabel lblServices = new JLabel ( "Services" );
		lblServices.setForeground ( Color.WHITE );
		lblServices.setFont ( new Font ( "Dialog", Font.BOLD, 14 ) );
		lblServices.setBackground ( Color.WHITE );
		lblServices.setBounds( 70, 11, 80, 28 );
		panelServices.add ( lblServices );
		
		//SERVICES IMAGE ICON
		JLabel lblServicesIcon = new JLabel ( "" ) ;
		lblServicesIcon.setBounds ( 20, 0, 40, 50 );
		panelServices.add ( lblServicesIcon );
		lblServicesIcon.setIcon ( new ImageIcon ( img_services ) );
		
		//LOGOUT PANEL
		JPanel panelLogout = new JPanel ();
		panelLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( JOptionPane.showConfirmDialog ( null, "Are you sure you want to Logout?", "Logout" , JOptionPane.YES_NO_OPTION ) == 0 ) {
					Menu.this.dispose ();
					Main m = new Main();
					m.main(null);
				}
				}
			}
		);
		panelLogout.addMouseListener(new PanelButtonMouseAdapter(panelLogout));
		
		panelLogout.setBackground ( new Color (0, 128, 128 ) );
		panelLogout.setBounds ( 0, 410, 260, 50 );
		panelMenu.add ( panelLogout );
		panelLogout.setLayout ( null );
		
		//LOGOUT LABEL
		JLabel lblLogout = new JLabel ( "Logout" );
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ( JOptionPane.showConfirmDialog ( null, "Are you sure you want to Logout?", "Logout" , JOptionPane.YES_NO_OPTION ) == 0 ) {
					Menu.this.dispose ();
					Main m = new Main();
					m.main(null);
			}
			}}
		);
		lblLogout.setToolTipText ( "" );
		lblLogout.setForeground ( Color.WHITE );
		lblLogout.setFont ( new Font ( "Dialog", Font.BOLD, 14 ) );
		lblLogout.setBackground ( Color.WHITE );
		lblLogout.setBounds ( 70, 11, 80, 28 );
		panelLogout.add ( lblLogout );
		
		//LOGOUT IMAGE ICON
		JLabel lblLogoutIcon = new JLabel ( "" );
		lblLogoutIcon.setBounds ( 20, 0, 40, 50 );
		panelLogout.add ( lblLogoutIcon );
		lblLogoutIcon.setIcon ( new ImageIcon(img_logout ) );
		
		//MULTIPLE JPANELS ON RIGHT SIDE
		
		layeredPane.setBounds(270, 11, 520, 478);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(dashboard, "name_1636146812200");
		
	}
	
	public void switchPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
		
	}//
	
	//MOUSE LISTENERS FOR LEFT SIDE
	
	private class PanelButtonMouseAdapter extends MouseAdapter{
		JPanel panel;
		public PanelButtonMouseAdapter(JPanel panel) {
		this.panel = panel;
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			panel.setBackground ( new Color ( 112, 128, 144 ) );
		}
		@Override
		public void mouseExited(MouseEvent e) {
			panel.setBackground ( new Color ( 0, 128, 128 ) );
		}
		@Override
		public void mousePressed(MouseEvent e) {
			panel.setBackground ( new Color ( 112, 128, 144 ) );
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			panel.setBackground ( new Color ( 112, 128, 144 ) );
		}
		@Override
		public void mouseClicked(MouseEvent e) {
		}
	}//private class
	
}//menu.java
