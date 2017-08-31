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

public class AppliancesInv {

	private JFrame frame;
	private JTextField textFieldType;
	private JTextField textFieldAmount;
	private JTextField textFieldPrice;
	private JTextField textFieldInvID;
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
	private JButton btnLoad;
	private JLabel labelInvID;
	private JButton btnUpdate;
	private JButton btnBack;
	public static InvScr scr;
	public static LoginScr sc;
	private JLabel lblApplInv;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppliancesInv window = new AppliancesInv();
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
	
	public void fillComboBox(){
		try{
			String query = "select * from DiffAppl";
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
			String query = "select * from DiffAppl";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableInv.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
			pst.close();
			rs.close();
			
		}catch(Exception io){
			io.printStackTrace();
		}
		
		
		
	}
	public AppliancesInv() {
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
					String query = " select * from DiffAppl where ID=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String)comboboxLumb.getSelectedItem());
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()){
					textFieldInvID.setText(rs.getString("ID"));
					textFieldType.setText(rs.getString("Type"));
					textFieldAmount.setText(rs.getString("Quantity"));
					textFieldPrice.setText(rs.getString("Location"));
						
					}
					
					rs.close();		
					pst.close();
					
			
				
				}catch(Exception io){
					io.printStackTrace();
				}
				
				refreshLabel();
			}
		});
		comboboxLumb.setBounds(38, 93, 189, 36);
		frame.getContentPane().add(comboboxLumb);
		
		JLabel labelInvID = new JLabel("InvID:");
		labelInvID.setFont(new Font("Tahoma", Font.BOLD, 16));
		labelInvID.setBounds(38, 58, 78, 36);
		frame.getContentPane().add(labelInvID);
		
		JLabel lblTypeOfLumb = new JLabel("Type:");
		lblTypeOfLumb.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTypeOfLumb.setBounds(38, 134, 78, 36);
		frame.getContentPane().add(lblTypeOfLumb);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblQuantity.setBounds(38, 183, 78, 36);
		frame.getContentPane().add(lblQuantity);
		
		JLabel lblPrice = new JLabel("Location:");
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
					String query = "insert into DiffAppl (Type,Quantity,Location) values (?,?,?)";
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
			String query = "Update DiffAppl set ID = '"+textFieldInvID.getText()+"', Type = '"+textFieldType.getText() + "', Quantity = '"+textFieldAmount.getText()+"', Location = '"+textFieldPrice.getText()+"' where ID = '"+textFieldInvID.getText() + "' ";
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
					String query = "delete from DiffAppl where ID = '"+textFieldInvID.getText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Deleted");
					btnLoad.doClick();
					
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
		
		 btnLoad = new JButton("Load Inv");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "select * from DiffAppl";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					tableInv.setModel(DbUtils.resultSetToTableModel(rs));
				
				
					pst.close();
					rs.close();
					
				}catch(Exception io){
					io.printStackTrace();
				}
				
				
				
				
			}
		});
		btnLoad.setBounds(255, 13, 110, 27);
		frame.getContentPane().add(btnLoad);
		
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
					String query = " select * from DiffAppl where ID='"+Name_+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
				
					ResultSet rs = pst.executeQuery();
					
					while(rs.next()){
					textFieldInvID.setText(rs.getString("ID"));
					textFieldType.setText(rs.getString("Type"));
					textFieldAmount.setText(rs.getString("Quantity"));
					textFieldPrice.setText(rs.getString("Location"));
						
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
	
	lblApplInv = new JLabel("Appliances Inventory:");
	lblApplInv.setFont(new Font("Tahoma", Font.PLAIN, 18));
	lblApplInv.setBounds(12, 18, 209, 27);
	frame.getContentPane().add(lblApplInv);
	
	textFieldInvID = new JTextField();
	textFieldInvID.setColumns(10);
	textFieldInvID.setBounds(38, 100, 60, 22);
	frame.getContentPane().add(textFieldInvID);
	}
	public void setVisible(boolean b) {
			frame.setVisible(true);
			
		
		
	}
	
}
