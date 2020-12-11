package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class UyeYonetici extends JFrame {

	private JPanel contentPane;
	private JTable table;
	String a;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeYonetici frame = new UyeYonetici();
					frame.setVisible(true);
					frame.setTitle("Üyelik Onaylama Ekranı");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static Connection Baglanti() throws Exception{
		try {
			Connection baglanti=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/odev1","root","");
			return baglanti;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Create the frame.
	 */
	public UyeYonetici() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				
				try{
					String s1="Select TC,Ad,Onay from uye";
					PreparedStatement st=Baglanti().prepareStatement(s1);
					ResultSet rs=st.executeQuery(s1);
					DefaultTableModel model=new DefaultTableModel();
					model.addColumn("Tc");
					model.addColumn("Ad");
					model.addColumn("Onay");
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3)});
						
						
					}
					table.setModel(model);
					
				}
				catch(Exception hata) {
					hata.printStackTrace();
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 632, 392);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(119, 136, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 237, 616, 116);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(SystemColor.info);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JTable table = (JTable)e.getSource();
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				a=model.getValueAt(row, 0).toString();
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("\u00DCYE ONAYLAMA");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(171, 11, 248, 45);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("\u00DCyeli\u011Fi Onayla");
		btnNewButton.setBackground(new Color(0, 255, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String s1="Update uye set Onay=? where TC=?";
					PreparedStatement st=Baglanti().prepareStatement(s1);
					st.setString(1,"Onaylandi");
					st.setString(2,a);
					st.executeUpdate();
					st.close();
				
			}
				catch(Exception hata) {hata.printStackTrace();}
				JOptionPane.showMessageDialog(null,"Üyelik Onaylandı!");
				try{
					String s1="Select TC,Ad,Onay from uye";
					PreparedStatement st=Baglanti().prepareStatement(s1);
					ResultSet rs=st.executeQuery(s1);
					DefaultTableModel model=new DefaultTableModel();
					model.addColumn("Tc");
					model.addColumn("Ad");
					model.addColumn("Onay");
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3)});
						
						
					}
					table.setModel(model);
					
				}
				catch(Exception hata) {
					hata.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBounds(234, 206, 118, 23);
		contentPane.add(btnNewButton);
	}
}
