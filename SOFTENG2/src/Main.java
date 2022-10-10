import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Main extends JFrame {
	
	private Image img_logo = new ImageIcon ( Main.class.getResource ( "imgs/logo.png" ) ).getImage ().getScaledInstance ( 90, 90, Image.SCALE_SMOOTH );
	private Image img_username = new ImageIcon ( Main.class.getResource ( "imgs/username.png" ) ).getImage ().getScaledInstance (40, 40, Image.SCALE_SMOOTH );
	private Image img_password = new ImageIcon ( Main.class.getResource ( "imgs/password.png") ).getImage ().getScaledInstance ( 40, 40, Image.SCALE_SMOOTH );
	private Image img_login = new ImageIcon ( Main.class.getResource ( "imgs/login.png" ) ).getImage ().getScaledInstance ( 51, 51, Image.SCALE_SMOOTH );
	private JPanel contentPane; 
	private JTextField textUsername;
	private JPasswordField txtPassword;
	private JLabel lblLoginMessage = new JLabel("");

	/**
	 * Launch the application.
	 */
	public static void main ( String[] args ) {
		EventQueue.invokeLater ( new Runnable () {
			public void run () {
				try {
					Main frame = new Main ();
					frame.setVisible ( true );
				} catch ( Exception e ) {
					e.printStackTrace ();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main () {
		
		//MAIN WINDOW
		setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
		setBounds ( 100, 100, 600, 400 );
		contentPane = new JPanel ();
		contentPane.setBackground ( new Color ( 0, 128, 128 ) );
		contentPane.setBorder ( new LineBorder ( new Color ( 0, 0, 128 ), 2) );
		setContentPane ( contentPane );
		contentPane.setLayout ( null );
		
		//MAIN LOGO
		JLabel lblIconLogo = new JLabel ( "" );
		lblIconLogo.setHorizontalAlignment ( SwingConstants.CENTER );
		lblIconLogo.setBounds ( 169, 40, 250, 95 );
		contentPane.add ( lblIconLogo );
		lblIconLogo.setIcon ( new ImageIcon ( img_logo ) );
		//CENTER AND NO STANDARD CLOSE, MAXIMIZE/MINIMIZE
		setUndecorated ( true );
		setLocationRelativeTo ( null );
		
		//USERNAME PANEL
		JPanel panel = new JPanel ();
		panel.setBackground ( new Color ( 255, 255, 255 ) );
		panel.setBounds ( 169, 146, 250, 40 );
		contentPane.add ( panel );
		panel.setLayout ( null );
		
		//USERNAME TXT FIELD
		textUsername = new JTextField ();
		textUsername.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained ( FocusEvent e ) {
				if ( textUsername.getText ().equals ( "Username" ) ) {
					textUsername.setText ( "" );
				}
				else {
					textUsername.selectAll ();
					
				}
			}
			@Override
			public void focusLost ( FocusEvent e ) {
				if ( textUsername.getText ().equals ("") ) {
					textUsername.setText ( "Username" );
				}
			}
		});
		textUsername.setBorder(null);
		textUsername.setText ( "Username" );
		textUsername.setBounds ( 10, 11, 170, 20 );
		panel.add ( textUsername );
		textUsername.setColumns ( 10 );
		
		//USERNAME IMAGE ICON
		JLabel lblUsernameImage = new JLabel ( "" );
		lblUsernameImage.setHorizontalAlignment ( SwingConstants.CENTER );
		lblUsernameImage.setBounds ( 210, 0, 40, 40 );
		panel.add ( lblUsernameImage );
		lblUsernameImage.setIcon (new ImageIcon ( img_username ) );
		
		//PASSWORD PANEL
		JPanel panel_1 = new JPanel ();
		panel_1.setBackground ( Color.WHITE );
		panel_1.setBounds( 169, 220, 250, 40 );
		contentPane.add ( panel_1 );
		panel_1.setLayout ( null );
		
		//PASSWORD TXT FIELD
		txtPassword = new JPasswordField ();
		txtPassword.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained ( FocusEvent e ) {
				if ( txtPassword.getText ().equals ( "Password" ) ) {
					txtPassword.setEchoChar ( '●' );
					txtPassword.setText ( "" );
				}
				else {
					txtPassword.selectAll ();
				}
			}
			@Override
			public void focusLost ( FocusEvent e ) {
				if ( txtPassword.getText ().equals ("") ) {
					txtPassword.setText ( "Password" );
					txtPassword.setEchoChar ( (char)0 );
				}
			}
		});
		txtPassword.setBorder(null);
		txtPassword.setEchoChar ( (char)0 );
		txtPassword.setText ( "Password" );
		txtPassword.setToolTipText ( "" );
		txtPassword.setBounds( 10, 11, 170, 20 );
		panel_1.add ( txtPassword );
		
		//PASSWORD IMAGE ICON
		JLabel lblPasswordImage = new JLabel ( "" );
		lblPasswordImage.setHorizontalAlignment ( SwingConstants.CENTER );
		lblPasswordImage.setBounds ( 210, 0, 40, 40 );
		panel_1.add ( lblPasswordImage );
		lblPasswordImage.setIcon ( new ImageIcon ( img_password ) );
		
		//LOGIN PANEL
		JPanel pnlBtnLogin = new JPanel ();
		pnlBtnLogin.addMouseListener ( new MouseAdapter () {
			@Override
			public void mouseClicked ( MouseEvent e ) {
				if ( textUsername.getText ().equals ( "admin" ) && txtPassword.getText().equals ( "admin" ) ) {
					//correct input
					System.out.println("nice");
					Main.this.dispose ();
					Menu m = new Menu ();
					m.components ();
					m.setVisible(true);
				}
				else if ( textUsername.getText ().isBlank() || txtPassword.getText().isBlank() ){
					lblLoginMessage.setText ( "Please Fill Out Fields" );
				}
				else {
					lblLoginMessage.setText ( "Incorrect Credentials" );
				}
			}
		});
		pnlBtnLogin.setBackground ( new Color ( 47, 79, 79 ) );
		pnlBtnLogin.setBounds ( 169, 295, 250, 53 );
		contentPane.add ( pnlBtnLogin );
		pnlBtnLogin.setLayout ( null );
		
		//LOGIN LABEL
		JLabel lblNewLabel = new JLabel ( "LOGIN" );
		lblNewLabel.setForeground ( new Color ( 255, 255, 255 ) );
		lblNewLabel.setFont ( new Font (  "Arial", Font.BOLD, 14 ) );
		lblNewLabel.setBounds ( 99, 11, 51, 31 );
		pnlBtnLogin.add ( lblNewLabel );
		
		//LOGIN IMAGE ICON
		JLabel lblLoginImage = new JLabel ( "" );
		lblLoginImage.setHorizontalAlignment ( SwingConstants.CENTER );
		lblLoginImage.setBounds ( 152, 0, 51, 51);
		pnlBtnLogin.add ( lblLoginImage );
		lblLoginImage.setIcon ( new ImageIcon ( img_login ) );
		
		//CLOSE LABEL
		JLabel lblX = new JLabel ( "X" );
		lblX.addMouseListener ( new MouseAdapter () {
			@Override
			public void mouseClicked ( MouseEvent e ) {
				if ( JOptionPane.showConfirmDialog ( null, "Are you sure you want to exit?", "Confirmation" , JOptionPane.YES_NO_OPTION ) == 0 ) {
					Main.this.dispose ();
				}
			}
			@Override
			public void mouseEntered ( MouseEvent e ) {
				lblX.setForeground ( Color.RED );
			}
			@Override
			public void mouseExited ( MouseEvent e ) {
				lblX.setForeground ( Color.WHITE );
			}
		});
		lblX.setForeground (new Color ( 255, 255, 255) );
		lblX.setFont ( new Font ( "Comic Sans MS", Font.BOLD, 14 ) );
		lblX.setHorizontalAlignment ( SwingConstants.CENTER );
		lblX.setBounds (580, 0, 20, 20);
		contentPane.add ( lblX );
		
		lblLoginMessage.setForeground(new Color(165, 42, 42));
		lblLoginMessage.setFont(new Font("Arial", Font.PLAIN, 13));
		lblLoginMessage.setBounds(169, 270, 250, 14);
		contentPane.add(lblLoginMessage);
		
	}
}
