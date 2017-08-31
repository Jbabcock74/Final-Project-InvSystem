import java.awt.EventQueue;


import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginScr {

	public JFrame frame;
	public static LoginScr sc;
	public static Optionpage scr;
//	private boolean inventoryNotCreated = true;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sc = new LoginScr();
					sc.setVisible(true);
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
	private JTextField textFieldUsrn;
	private JPasswordField passwordField;
	private JLabel label;
	public LoginScr() {
		
		initialize();
		connection =sqliteConnection.dbConnector();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 507, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(79, 67, 66, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(79, 119, 66, 16);
		frame.getContentPane().add(lblPassword);
		
		textFieldUsrn = new JTextField();
		textFieldUsrn.setBounds(157, 64, 116, 22);
		frame.getContentPane().add(textFieldUsrn);
		textFieldUsrn.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "select * from Lumber where username=? and password=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUsrn.getText());
					pst.setString(2, passwordField.getText());
					
					ResultSet rs = pst.executeQuery();
					int count = 0;
					while(rs.next()){
						count++;
					}
					if(count == 1){
						JOptionPane.showMessageDialog(null, "Username & Password Correct");
						 frame.setVisible(false);
						 	scr = new Optionpage();
							scr.setVisible(true);
					
					}else if(count > 1){
						JOptionPane.showMessageDialog(null, "Duplicate Username & Password Correct");
						}else{
							JOptionPane.showMessageDialog(null, "Username & Password is not correct");
					}
					rs.close();
					pst.close();
				}catch(Exception io){
					JOptionPane.showMessageDialog(null, io);
					}
				}
		});
		btnLogin.setBounds(207, 151, 66, 25);
		frame.getContentPane().add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER){
					try{
						String query = "select * from Lumber where username=? and password=?";
						PreparedStatement pst = connection.prepareStatement(query);
						pst.setString(1, textFieldUsrn.getText());
						pst.setString(2, passwordField.getText());
						
						ResultSet rs = pst.executeQuery();
						int count = 0;
						while(rs.next()){
							count++;
						}
						if(count == 1){
							JOptionPane.showMessageDialog(null, "Username & Password Correct");
							 frame.setVisible(false);
							 	scr = new Optionpage();
								scr.setVisible(true);
						
						}else if(count > 1){
							JOptionPane.showMessageDialog(null, "Duplicate Username & Password Correct");
							}else{
								JOptionPane.showMessageDialog(null, "Username & Password is not correct");
						}
						rs.close();
						pst.close();
					}catch(Exception io){
						JOptionPane.showMessageDialog(null, io);
						}
					}
			}
		});
		passwordField.setBounds(157, 116, 116, 22);
		frame.getContentPane().add(passwordField);
		
		label = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/Inventory-maintenance-icon.png")).getImage();
		label.setIcon(new ImageIcon(img));
		label.setBounds(298, -17, 157, 224);
		frame.getContentPane().add(label);
		
		JButton buttonCreateLog = new JButton("Create Login");
		buttonCreateLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			 NewUser nu = new NewUser();
				nu.setVisible(true);
			}
		});
		buttonCreateLog.setHorizontalAlignment(SwingConstants.LEFT);
		buttonCreateLog.setVerticalAlignment(SwingConstants.TOP);
		buttonCreateLog.setFont(new Font("Tahoma", Font.PLAIN, 11));
		buttonCreateLog.setBounds(79, 151, 99, 25);
		frame.getContentPane().add(buttonCreateLog);
	}

	public void setVisible(boolean b) {
		frame.setVisible(true);
		
	}
}
