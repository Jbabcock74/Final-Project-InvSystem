import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import net.proteanit.sql.DbUtils;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;

public class Optionpage {

	private JFrame frame;
	private JPopupMenu popupMenu;
	private JButton btnEdit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Optionpage window = new Optionpage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private void refreshLumb(){
		try{
			String query = "select * from LumbInv";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableLumb.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
			pst.close();
			rs.close();
			
		}catch(Exception io){
			io.printStackTrace();
		}
		
	}
	private void refreshAppliances(){
		try{
			String query = "select * from DiffAppl";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableAppliances.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
			pst.close();
			rs.close();
			
		}catch(Exception io){
			io.printStackTrace();
		}
		
	}
	private void refreshTools(){
		try{
			String query = "select * from DiffTools";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableTools.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
			pst.close();
			rs.close();
			
		}catch(Exception io){
			io.printStackTrace();
		}
		
	}
	private void refreshScrews(){
		try{
			String query = "select * from DiffScrews";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableScrews.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
			pst.close();
			rs.close();
			
		}catch(Exception io){
			io.printStackTrace();
		}
		
	}
	private void refreshNails(){
		try{
			String query = "select * from DiffNails";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			tableNails.setModel(DbUtils.resultSetToTableModel(rs));
			
			
			
			pst.close();
			rs.close();
			
		}catch(Exception io){
			io.printStackTrace();
		}
		
	}
	
		Connection connection = null;
		private JTable tableLumb;
		private JTable tableTools;
		private JTable tableAppliances;
		private JTable tableNails;
		private JTable tableScrews;
		private JScrollPane scrollPane_1;
		private JScrollPane scrollPane;
		private JTabbedPane tabbedPane;
		private JButton btnLogout;
	/**
	 * Create the application.
	 */
	public Optionpage() {
		initialize();
		connection = sqliteConnection.dbConnector();
		refreshLumb();
		refreshAppliances();
		refreshTools();
		refreshScrews();
		refreshNails();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 856, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
		    public void stateChanged(ChangeEvent e) {
		        System.out.println("Tab index: " + tabbedPane.getSelectedIndex());
		    }
		});
		tabbedPane.addMouseListener(new MouseAdapter() {
			@Override
				 public void mouseClicked(MouseEvent e) {
		                if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
		                	if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1) {
		                        popupMenu.show(e.getComponent(),e.getX(),e.getY());
		                        }
		                }
		            }
			
		});
		
		popupMenu = new JPopupMenu();
		popupMenu.setLocation(154, 69);
		addPopup(frame.getContentPane(), popupMenu);
		btnEdit = new JButton("Edit");
		popupMenu.add(btnEdit);
		tabbedPane.setBounds(214, 69, 612, 452);
		frame.getContentPane().add(tabbedPane);
		btnEdit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
		 if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()).equals("Lumber")){
						frame.setVisible(false);
						InvScr iI = new InvScr();
						iI.setVisible(true);
		
					}
				 else if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()).equals("Tools")){
					frame.setVisible(false);
					ToolsInv tI = new ToolsInv();
					tI.setVisible(true);
	
				}
				 else if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()).equals("Appliances")){
					frame.setVisible(false);
					AppliancesInv aI = new AppliancesInv();
					aI.setVisible(true);
				}
				 else if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()).equals("Nails")){
						frame.setVisible(false);
						NailsInv aI = new NailsInv();
						aI.setVisible(true);
					}
				 else if (tabbedPane.getTitleAt(tabbedPane.getSelectedIndex()).equals("Screws")){
						frame.setVisible(false);
						ScrewsInv sI = new ScrewsInv();
						sI.setVisible(true);
					}
			}
		});
	
			
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Lumber", null, scrollPane, "");
		
		tableLumb = new JTable();
		scrollPane.setViewportView(tableLumb);
		
		
		scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("Tools", null, scrollPane_1, null);
	
		
		tableTools = new JTable();
		scrollPane_1.setViewportView(tableTools);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("Appliances", null, scrollPane_2, null);
		
		tableAppliances = new JTable();
		scrollPane_2.setViewportView(tableAppliances);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		tabbedPane.addTab("Nails", null, scrollPane_3, null);
		
		tableNails = new JTable();
		scrollPane_3.setViewportView(tableNails);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		tabbedPane.addTab("Screws", null, scrollPane_4, null);
		
		tableScrews = new JTable();
		scrollPane_4.setViewportView(tableScrews);
		
		JLabel lblBabcockBrothersInventory = new JLabel("Babcock Brothers Inventory:");
		lblBabcockBrothersInventory.setFont(new Font("Segoe UI Historic", Font.PLAIN, 28));
		lblBabcockBrothersInventory.setBounds(33, 13, 578, 43);
		frame.getContentPane().add(lblBabcockBrothersInventory);
		
		JButton btnEditLumber = new JButton("<html> Edit Lumber <br /> Inventory:</html>");
		btnEditLumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				InvScr sC = new InvScr();
				sC.setVisible(true);
			}
		});
		btnEditLumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditLumber.setHorizontalAlignment(SwingConstants.LEFT);
		btnEditLumber.setBounds(48, 91, 122, 50);
		frame.getContentPane().add(btnEditLumber);
		
		JButton btnEditToolsInventory = new JButton("<html> Edit Tools <br /> Inventory:</html>");
		btnEditToolsInventory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditToolsInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ToolsInv tI = new ToolsInv();
				tI.setVisible(true);
			}
		});
		btnEditToolsInventory.setBounds(48, 165, 122, 43);
		frame.getContentPane().add(btnEditToolsInventory);
		
		JButton btnEditAppliancesInventory = new JButton("<html> Edit Appliances <br /> Inventory:</html>");
		btnEditAppliancesInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				AppliancesInv aI = new AppliancesInv();
				aI.setVisible(true);
			}
		});
		btnEditAppliancesInventory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditAppliancesInventory.setBounds(48, 232, 122, 43);
		frame.getContentPane().add(btnEditAppliancesInventory);
		
		JButton btnEditScrewsInventory = new JButton("<html> Edit Screws <br /> Inventory:</html>");
		btnEditScrewsInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				ScrewsInv nI = new ScrewsInv();
				nI.setVisible(true);
			}
		});
		btnEditScrewsInventory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditScrewsInventory.setBounds(48, 350, 122, 43);
		frame.getContentPane().add(btnEditScrewsInventory);
		
		JButton btnEditNailsInventory = new JButton("<html> Edit Nails <br /> Inventory:</html>");
		btnEditNailsInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				NailsInv nI = new NailsInv();
				nI.setVisible(true);
			}
		});
		btnEditNailsInventory.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditNailsInventory.setBounds(48, 288, 122, 43);
		frame.getContentPane().add(btnEditNailsInventory);
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginScr ls = new LoginScr();
				ls.setVisible(true);
			}
		});
		btnLogout.setHorizontalAlignment(SwingConstants.LEFT);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogout.setBounds(741, 13, 85, 31);
		frame.getContentPane().add(btnLogout);
	
	}
	public void setVisible(boolean b) {
		frame.setVisible(true);
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
	}
}
