import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class ToolsInv {

	private JFrame frame;
	private JTextField textFieldType;
	private JTextField textFieldAmount;
	private JTextField textFieldPrice;
	private JComboBox comboboxTools;
	private JLabel lblInvID;
	private JLabel lblType;
	private JLabel lblAmount;
	private JLabel lblPrice;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnLogout;
	private JScrollPane scrollPaneTools;
	private JTable tableInv;
	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToolsInv window = new ToolsInv();
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
	private JButton btnBack;
	private JLabel lblToolsInventory;
	private JTextField textFieldInvID;
	
	
	
	private void fillComboBox() {		
		try{
		String query = "select * from DiffTools";
		PreparedStatement pts = connection.prepareStatement(query);
		ResultSet rs = pts.executeQuery();
		
		while(rs.next()){
			comboboxTools.addItem(rs.getString("ID"));
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
			String query = "select * from DiffTools";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableInv.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
			pst.close();
			rs.close();
			
		}catch(Exception io){
			io.printStackTrace();
		}
		
		
	}
	public ToolsInv() {
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
		frame.getContentPane().addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent e) {
				System.out.println("poop");
				comboboxTools.setAutoscrolls(true);
			}
		});
		frame.setBounds(100, 100, 754, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboboxTools = new JComboBox();
		comboboxTools.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER){
					btnSave.doClick();
					
				}
			}
		});
		comboboxTools.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = " select * from DiffTools where ID=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, (String)comboboxTools.getSelectedItem());
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
		comboboxTools.setBounds(57, 96, 189, 36);
		frame.getContentPane().add(comboboxTools);
		
		lblInvID = new JLabel("InvID:");
		lblInvID.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInvID.setBounds(57, 66, 78, 36);
		frame.getContentPane().add(lblInvID);
		
		lblType = new JLabel("Type:");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblType.setBounds(57, 134, 78, 36);
		frame.getContentPane().add(lblType);
		
		lblAmount = new JLabel("Quantity:");
		lblAmount.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAmount.setBounds(57, 183, 78, 36);
		frame.getContentPane().add(lblAmount);
		
		lblPrice = new JLabel("Location:");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPrice.setBounds(57, 237, 78, 27);
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
		textFieldType.setBounds(57, 161, 116, 22);
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
		textFieldAmount.setBounds(57, 210, 116, 22);
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
		textFieldPrice.setBounds(57, 259, 116, 22);
		frame.getContentPane().add(textFieldPrice);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "insert into DiffTools (Type,Quantity,Location) values (?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.setString(1, textFieldType.getText());
					pst.setString(2, textFieldAmount.getText());
					pst.setString(3, textFieldPrice.getText());
					pst.execute();
					
				
					JOptionPane.showMessageDialog(null, "Saved");
					
					pst.close();
				
					if(btnSave.isEnabled()){
						comboboxTools.removeAllItems();
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
		btnSave.setBounds(37, 319, 136, 47);
		frame.getContentPane().add(btnSave);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{	
					String query = "Update DiffTools set ID = '"+textFieldInvID.getText() + "', Type = '"+textFieldType.getText() + "', Quantity = '"+textFieldAmount.getText()+"', Location = '"+textFieldPrice.getText()+"' where ID = '"+textFieldInvID.getText() + "' ";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Updated");
					//btnLoad.doClick();
					pst.close();
				
				
					if(btnUpdate.isEnabled()){
						comboboxTools.removeAllItems();
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
		btnUpdate.setBounds(37, 393, 136, 47);
		frame.getContentPane().add(btnUpdate);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String query = "delete from DiffTools where ID = '"+textFieldInvID.getToolTipText()+"' ";
					PreparedStatement pst = connection.prepareStatement(query);
					
					pst.execute();
					
					JOptionPane.showMessageDialog(null, "Deleted");
			
					
					if(btnDelete.isEnabled()){
						comboboxTools.removeAllItems();
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
		btnDelete.setBounds(37, 474, 136, 47);
		frame.getContentPane().add(btnDelete);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginScr sC = new LoginScr();
				sC.setVisible(true);
				
			
			}
		});
		btnLogout.setBounds(646, 13, 78, 27);
		frame.getContentPane().add(btnLogout);
		
		JScrollPane scrollPaneTools = new JScrollPane();
		scrollPaneTools.setBounds(274, 53, 369, 475);
		frame.getContentPane().add(scrollPaneTools);
	
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
				String query = " select * from DiffTools where ID='"+Name_+"' ";
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
	scrollPaneTools.setViewportView(tableInv);
	
	btnBack = new JButton("Back");
	btnBack.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			Optionpage op = new Optionpage();
			op.setVisible(true);
		}
	});
	btnBack.setBounds(556, 14, 78, 27);
	frame.getContentPane().add(btnBack);
	
	lblToolsInventory = new JLabel("Tools Inventory:");
	lblToolsInventory.setFont(new Font("Tahoma", Font.PLAIN, 20));
	lblToolsInventory.setBounds(33, 13, 229, 47);
	frame.getContentPane().add(lblToolsInventory);
	
	textFieldInvID = new JTextField();
	textFieldInvID.setColumns(10);
	textFieldInvID.setBounds(57, 103, 60, 22);
	frame.getContentPane().add(textFieldInvID);
}

	public void setVisible(boolean b) {
		frame.setVisible(true);
	}
}
