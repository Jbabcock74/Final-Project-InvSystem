import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class InvScr {

	private JFrame frame;
	private JTextField textFieldType;
	private JTextField textFieldAmount;
	private JTextField textFieldPrice;
	private JTable tableInv;
	private JButton btnDelete;
	private JButton buttonLogout;
	private JPanel contentPane;
	private JTable table;
	private JComboBox comboboxLumb;
	private JLabel lblTypeOfLumber;
	private JLabel lblAmountOfLumber;
	private JLabel lblPrice;
	private JButton btnSave;
	private JLabel labelInvID;
	private JButton btnUpdate;
	private JButton btnBack;
	public static InvScr scr;
	public static LoginScr sc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InvScr window = new InvScr();
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
	private JTextField textFieldInvID;
	public void fillComboBox(){
		try{
			String query = "select * from LumbInv";
			PreparedStatement pts = connection.prepareStatement(query);
			ResultSet rs = pts.executeQuery();
			
			while(rs.next()){
				comboboxLumb.addItem(rs.getString("ID"));
			}
			
				//combobox.setModel((ComboBoxModel) DbUtils.resultSetToTableModel(rs));
		
			pts.close();
			rs.close();
			
		}catch(Exception io){
			io.printStackTrace();
		}
		refreshLabel();
	}
	private void refreshLabel(){
		try{
			String query = "select * from LumbInv";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableInv.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
			pst.close();
			rs.close();
			
		}catch(Exception io){
			io.printStackTrace();
		}
		
		
		
	}
	public InvScr() {
		initialize();
		connection = sqliteConnection.dbConnector();
		refreshLabel();
		fillComboBox();
	
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER){
					btnSave.doClick();
					
				}
			}
		});
		frame.setBounds(100, 100, 673, 671);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboboxLumb = new JComboBox();
		comboboxLumb.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER){
					btnSave.doClick();
					
				}
			}
		});
		comboboxLumb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = " select * from LumbInv where ID=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String)comboboxLumb.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()){
				//	textFieldInvID.setText(rs.getString("ID"));
					textFieldType.setText(rs.getString("Name"));
					textFieldAmount.setText(rs.getString("Quantity"));
					textFieldPrice.setText(rs.getString("Price"));
					}
					
					rs.close();		
					pst.close();
					
			
				
				}catch(Exception io){
					io.printStackTrace();
				}
				
				refreshLabel();
			}
		});
		comboboxLumb.setBounds(42, 91, 116, 36);
		frame.getContentPane().add(comboboxLumb);
		
		JLabel labelInvID = new JLabel("InvID:");
		labelInvID.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelInvID.setBounds(42, 56, 78, 36);
		frame.getContentPane().add(labelInvID);
		
		JLabel lblTypeOfLumb = new JLabel("Type:");
		lblTypeOfLumb.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTypeOfLumb.setBounds(38, 134, 78, 36);
		frame.getContentPane().add(lblTypeOfLumb);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantity.setBounds(38, 183, 78, 36);
		frame.getContentPane().add(lblQuantity);
		
		JLabel lblPrice = new JLabel("Price:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrice.setBounds(38, 237, 78, 27);
		frame.getContentPane().add(lblPrice);
		
		textFieldType = new JTextField();
		textFieldType.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER){
					btnSave.doClick();
					
				}
			}
		});
		textFieldType.setColumns(10);
		textFieldType.setBounds(38, 161, 116, 22);
		frame.getContentPane().add(textFieldType);
		
		textFieldAmount = new JTextField();
		textFieldAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER){
					btnSave.doClick();
					
				}
			}
		});
		textFieldAmount.setColumns(10);
		textFieldAmount.setBounds(38, 210, 116, 22);
		frame.getContentPane().add(textFieldAmount);
		
		textFieldPrice = new JTextField();
		textFieldPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER){
					btnSave.doClick();
					
				}
			}
		});
		textFieldPrice.setColumns(10);
		textFieldPrice.setBounds(38, 259, 116, 22);
		frame.getContentPane().add(textFieldPrice);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "insert into LumbInv (Name,Quantity,Price) values (?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					//pst.setString(1, textFieldInvID.getText());
					pst.setString(1, textFieldType.getText());
					pst.setString(2, textFieldAmount.getText());
					pst.setString(3, textFieldPrice.getText());
					pst.execute();
					
				
					JOptionPane.showMessageDialog(null, "Saved");
					//btnLoad.doClick();
					pst.close();
				
					if(btnSave.isEnabled()){
						comboboxLumb.removeAllItems();
						fillComboBox();
					}
				
					
				}catch(Exception io){
					io.printStackTrace();
				}
				
				refreshLabel();
			}
		});
			
		btnSave.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnSave.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(169, 169, 169), new Color(169, 169, 169), new Color(0, 0, 0), new Color(0, 0, 0)));
		btnSave.setBounds(18, 319, 136, 47);
		frame.getContentPane().add(btnSave);
		
		 btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
			try{	
			String query = "Update LumbInv set ID = '"+textFieldInvID.getText()+"', Name = '"+textFieldType.getText() + "', Quantity = '"+textFieldAmount.getText()+"', Price = '"+textFieldPrice.getText()+"' where ID = '"+textFieldInvID.getText()+"' ";
			PreparedStatement pst = connection.prepareStatement(query);
			pst.execute();
			
			JOptionPane.showMessageDialog(null, "Updated");
			//btnLoad.doClick();
			pst.close();
		
		
			if(btnUpdate.isEnabled()){
				comboboxLumb.removeAllItems();
				fillComboBox();
			}
		}
		catch(Exception io){
			io.printStackTrace();
		}
		
	
		refreshLabel();
		
		
		}
			
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnUpdate.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(169, 169, 169), new Color(169, 169, 169), new Color(0, 0, 0), new Color(0, 0, 0)));
		btnUpdate.setBounds(18, 393, 136, 47);
		frame.getContentPane().add(btnUpdate);
		
	 btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "delete from LumbInv where ID = '"+comboboxLumb.getToolTipText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Deleted");
				//	btnLoad.doClick();
					
					if(btnDelete.isEnabled()){
						comboboxLumb.removeAllItems();
						fillComboBox();
					}
					pst.close();
				}catch(Exception io){
					io.printStackTrace();
				}
				
			
				refreshLabel();
				
			
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDelete.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(169, 169, 169), new Color(169, 169, 169), new Color(0, 0, 0), new Color(0, 0, 0)));
		btnDelete.setBounds(18, 474, 136, 47);
		frame.getContentPane().add(btnDelete);
		
		JButton button_4 = new JButton("Logout");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginScr sc = new LoginScr();
				sc.setVisible(true);
			}
		});
		button_4.setBounds(565, 13, 78, 27);
		frame.getContentPane().add(button_4);
		
		JScrollPane scrollPaneLumb = new JScrollPane();
		scrollPaneLumb.setBounds(255, 53, 369, 475);
		frame.getContentPane().add(scrollPaneLumb);
		
		tableInv = new JTable();
		tableInv.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER){
					btnSave.doClick();
					
				}
			}
		});
		tableInv.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					int row = tableInv.getSelectedRow();
					String Name_ =(tableInv.getModel().getValueAt(row, 0)).toString();
					String query = " select * from LumbInv where ID='"+Name_+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
				
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()){
					textFieldInvID.setText(rs.getString("ID"));
					textFieldType.setText(rs.getString("Name"));
					textFieldAmount.setText(rs.getString("Quantity"));
					textFieldPrice.setText(rs.getString("Price"));
						
					}
					
							
					pst.close();
					rs.close();
					
				
					
				}catch(Exception io){
					io.printStackTrace();
				}
				
				
				
				
			}
			
		});
		scrollPaneLumb.setViewportView(tableInv);
	
	btnBack = new JButton("Back");
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			Optionpage op = new Optionpage();
			op.setVisible(true);
		}
	});
	btnBack.setBounds(475, 13, 78, 27);
	frame.getContentPane().add(btnBack);
	
	JLabel lblLumbInv = new JLabel("Lumber Inventory:");
	lblLumbInv.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblLumbInv.setBounds(18, 16, 209, 27);
	frame.getContentPane().add(lblLumbInv);
	
	textFieldInvID = new JTextField();
	textFieldInvID.setColumns(10);
	textFieldInvID.setBounds(42, 98, 60, 22);
	frame.getContentPane().add(textFieldInvID);
	}
	public void setVisible(boolean b) {
			frame.setVisible(true);
			
		
		
	}
}
