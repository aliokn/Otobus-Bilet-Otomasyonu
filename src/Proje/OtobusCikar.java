package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class OtobusCikar extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OtobusCikar frame = new OtobusCikar();
					frame.setVisible(true);
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
	public OtobusCikar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				
				
				
String sorgu="SELECT * FROM otobus";
				
				
				try {
				DefaultTableModel model=new DefaultTableModel();
				Statement st=Baglanti().createStatement();
				ResultSet rs=st.executeQuery(sorgu);
				model.addColumn("Plaka");
				model.addColumn("Ad Soyad");
				model.addColumn("Sicil No");
				model.addColumn("Koltuk No");
			
				
				
				while(rs.next()) {
					model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
				
					
				}
			
				
				table.setModel(model);
				}
				catch(Exception hata) {
					 hata.printStackTrace();
				}
				
				
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 530, 363);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 189, 514, 135);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table=(JTable)e.getSource();
				int row=table.getSelectedRow();
				textField.setText(table.getModel().getValueAt(row, 0).toString());
				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Sil");
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String s="Delete from otobus where Plaka=?";
					PreparedStatement st=Baglanti().prepareStatement(s);
					st.setString(1, textField.getText());
					st.executeUpdate();
					st.close();
					
				}
				catch(Exception ex) {ex.printStackTrace();}
				JOptionPane.showMessageDialog(null,"Silme Başarılı!");
				String sorgu="SELECT * FROM otobus";
				
				
				try {
				DefaultTableModel model=new DefaultTableModel();
				Statement st=Baglanti().createStatement();
				ResultSet rs=st.executeQuery(sorgu);
				model.addColumn("Plaka");
				model.addColumn("Ad Soyad");
				model.addColumn("Sicil No");
				model.addColumn("Koltuk No");
			
				
				
				while(rs.next()) {
					model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
				
					
				}
			
				
				table.setModel(model);
				}
				catch(Exception hata) {
					 hata.printStackTrace();
				}
				
				textField.setText(null);
				
				
			}
		});
		btnNewButton.setBounds(175, 155, 89, 23);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(175, 46, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Otob\u00FCs Plakas\u0131");
		lblNewLabel.setBounds(79, 49, 86, 14);
		contentPane.add(lblNewLabel);
	}
}
