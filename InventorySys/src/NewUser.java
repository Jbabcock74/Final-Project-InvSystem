import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class NewUser {

	private JFrame frame;
	private JTextField textFieldUname;
	private JTextField passwordField1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser window = new NewUser();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection connection = null;
	private JTextField passwordField;
	public NewUser() {
		initialize();
		connection =sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 397, 382);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCreateNewUsr = new JLabel("Create Username & Password:");
		lblCreateNewUsr.setBounds(48, 26, 186, 27);
		frame.getContentPane().add(lblCreateNewUsr);
		
		JLabel lblNewUsr = new JLabel("Desired Username:");
		lblNewUsr.setBounds(47, 101, 116, 27);
		frame.getContentPane().add(lblNewUsr);
		
		JLabel lblRedesiredPassword = new JLabel("Redesired Password:");
		lblRedesiredPassword.setBounds(30, 170, 134, 27);
		frame.getContentPane().add(lblRedesiredPassword);
		
		textFieldUname = new JTextField();
		textFieldUname.setColumns(10);
		textFieldUname.setBounds(161, 103, 116, 22);
		frame.getContentPane().add(textFieldUname);
		
		passwordField1 = new JTextField();
		passwordField1.setColumns(10);
		passwordField1.setBounds(161, 172, 116, 22);
		frame.getContentPane().add(passwordField1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginScr ls = new LoginScr();
				ls.setVisible(true);
			}
		});
		btnBack.setBounds(54, 253, 59, 32);
		frame.getContentPane().add(btnBack);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					
				//	if(passwordField.getText() == passwordField1.getText()){
					String query = "insert into Lumber (username,password) values (?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUname.getText());
					pst.setString(2, passwordField.getText());
					
					pst.execute();
					
					
					JOptionPane.showMessageDialog(null, "Saved");
					pst.close();
					
				}catch(Exception io){
					io.printStackTrace();
				}
			}
		});
		btnCreate.setBounds(161, 253, 116, 32);
		frame.getContentPane().add(btnCreate);
		
		JLabel lblNewPw = new JLabel("Desired Password:");
		lblNewPw.setBounds(47, 130, 116, 27);
		frame.getContentPane().add(lblNewPw);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(161, 132, 116, 22);
		frame.getContentPane().add(passwordField);
	}

	public void setVisible(boolean b) {
	frame.setVisible(true);
		
	}
}
