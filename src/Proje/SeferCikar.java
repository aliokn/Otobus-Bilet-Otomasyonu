package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class SeferCikar extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField Sefer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SeferCikar frame = new SeferCikar();
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
	public SeferCikar() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
	String sorgu="SELECT * FROM sefer";
				
				
				try {
				DefaultTableModel model=new DefaultTableModel();
				Statement st=Baglanti().createStatement();
				ResultSet rs=st.executeQuery(sorgu);
				model.addColumn("Sefer Numarası");
				model.addColumn("Otobüs");
				model.addColumn("Tarih");
				model.addColumn("Güzergah");
				model.addColumn("Sefer Saati");
				model.addColumn("Bilet Fiyatı");
				
				while(rs.next()) {
					model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getFloat(6)});
				
					
				}
			
				
				table.setModel(model);
				}
				catch(Exception hata) {
					 hata.printStackTrace();
				}
				
				
				
				
				
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 918, 451);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 274, 902, 138);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JTable table=(JTable)e.getSource();
				int row=table.getSelectedRow();
				Sefer.setText(table.getValueAt(row, 0).toString());
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		Sefer = new JTextField();
		Sefer.setBounds(321, 59, 86, 20);
		contentPane.add(Sefer);
		Sefer.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sefer Numaras\u0131");
		lblNewLabel.setBounds(197, 62, 114, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Sil");
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String s="Delete from sefer where SeferNo=?";
					PreparedStatement st=Baglanti().prepareStatement(s);
					st.setString(1, Sefer.getText());
					st.executeUpdate();
					st.close();
					
				}
				catch(Exception ex) {ex.printStackTrace();}
				try {
					String s1="Delete from koltukno where SeferNo=?";
					PreparedStatement st=Baglanti().prepareStatement(s1);
					st.setString(1, Sefer.getText());
					st.executeUpdate();
					st.close();
				}
				catch(Exception ex) {ex.printStackTrace();}
				JOptionPane.showMessageDialog(null,"Silme Başarılı!");
				String sorgu="SELECT * FROM sefer";
				
				
				try {
				DefaultTableModel model=new DefaultTableModel();
				Statement st=Baglanti().createStatement();
				ResultSet rs=st.executeQuery(sorgu);
				model.addColumn("Sefer Numarası");
				model.addColumn("Otobüs");
				model.addColumn("Tarih");
				model.addColumn("Güzergah");
				model.addColumn("Sefer Saati");
				model.addColumn("Bilet Fiyatı");
				
				while(rs.next()) {
					model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getFloat(6)});
				
					
				}
			
				
				table.setModel(model);
				}
				catch(Exception hata) {
					 hata.printStackTrace();
				}
				Sefer.setText(null);
				
			}
		});
		btnNewButton.setBounds(349, 242, 89, 23);
		contentPane.add(btnNewButton);
	}
}
