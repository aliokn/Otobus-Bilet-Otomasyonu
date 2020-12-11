package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UyeKoltukAl extends JFrame {

	private JPanel contentPane;
	private JTextField Sfr;
	private JTextField Otbs;
	private JTextField Gzrgh;
	private JTextField Trh;
	private JTextField Saat;
	private JLabel lblNewLabel_3;
	private JTable table;
	private JScrollPane scrollPane;
	int a;
	private JButton btnNewButton;
	private JTextField Tutar;
	private JLabel lblNewLabel_4;
	private JLabel TC;
	private JLabel AD;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeKoltukAl frame = new UyeKoltukAl();
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
	public UyeKoltukAl() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				try{
					String s1="Select KoltukNo,Durumu from koltukno where OtobusAdi='"+Otbs.getText()+"'AND SeferNo='"+Sfr.getText()+"'";
					PreparedStatement st=Baglanti().prepareStatement(s1);
					ResultSet rs=st.executeQuery(s1);
					DefaultTableModel model=new DefaultTableModel();
					model.addColumn("KoltukNo");
					model.addColumn("Durum");
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString(1),rs.getString(2)});
					
						
					}
					table.setModel(model);
					
				}
				catch(Exception hata) {
					hata.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 749, 432);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Sfr = new JTextField();
		Sfr.setBounds(131, 57, 137, 20);
		contentPane.add(Sfr);
		Sfr.setColumns(10);
		
		Otbs = new JTextField();
		Otbs.setColumns(10);
		Otbs.setBounds(131, 88, 137, 20);
		contentPane.add(Otbs);
		
		Gzrgh = new JTextField();
		Gzrgh.setColumns(10);
		Gzrgh.setBounds(131, 118, 137, 20);
		contentPane.add(Gzrgh);
		
		Trh = new JTextField();
		Trh.setColumns(10);
		Trh.setBounds(131, 147, 137, 20);
		contentPane.add(Trh);
		
		Saat = new JTextField();
		Saat.setColumns(10);
		Saat.setBounds(131, 178, 137, 20);
		contentPane.add(Saat);
		
		JLabel lblNewLabel = new JLabel("Sefer No");
		lblNewLabel.setBounds(58, 60, 63, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Otob\u00FCs");
		lblNewLabel_1.setBounds(58, 91, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("G\u00FCzergah");
		lblNewLabel_2.setBounds(58, 121, 63, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tarih");
		lblNewLabel_2_1.setBounds(58, 150, 63, 14);
		contentPane.add(lblNewLabel_2_1);
		
		lblNewLabel_3 = new JLabel("Saat");
		lblNewLabel_3.setBounds(58, 181, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 280, 733, 113);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionBackground(new Color(144, 238, 144));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTable table = (JTable)e.getSource();
				int row = table.getSelectedRow();
				int column = table.getSelectedColumn();
				DefaultTableModel model=(DefaultTableModel)table.getModel();
				a=(Integer.parseInt(model.getValueAt(row, 0).toString()));
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Bilet Al");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String s="Insert into bilet(SeferNo,AD,KoltukNo,Tarih,Saat,Plaka,Guzergah,TC,Fiyat) values(?,?,?,?,?,?,?,?,?)";
					PreparedStatement st=Baglanti().prepareStatement(s);
					st.setString(1, Sfr.getText());
					st.setString(2, AD.getText());
					st.setInt(3, a);
					st.setDate(4, Date.valueOf(Trh.getText()));
					st.setTime(5, Time.valueOf(Saat.getText()));
					st.setString(6, Otbs.getText());
					st.setString(7, Gzrgh.getText());
					st.setString(8, TC.getText());
					st.setFloat(9, Float.valueOf(Tutar.getText()));
					st.execute();
					st.close();
					}
					catch(Exception ex) {ex.printStackTrace();}
					try {
						String s1="Update koltukno  set Durumu=? where KoltukNo=?";
						PreparedStatement st=Baglanti().prepareStatement(s1);
						st.setString(1,"Dolu");
						st.setInt(2, a);
						st.executeUpdate();
						st.close();
					
				}
					catch(Exception hata) {hata.printStackTrace();}
					
					try{
						String s1="Select KoltukNo,Durumu from koltukno where OtobusAdi='"+Otbs.getText()+"'AND SeferNo='"+Sfr.getText()+"'";
						PreparedStatement st=Baglanti().prepareStatement(s1);
						ResultSet rs=st.executeQuery(s1);
						DefaultTableModel model=new DefaultTableModel();
						model.addColumn("KoltukNo");
						model.addColumn("Durum");
						while(rs.next()) {
							model.addRow(new Object[] {rs.getString(1),rs.getString(2)});
							
							
						}
						table.setModel(model);
						
					}
					catch(Exception hata) {
						hata.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null,"Kayıt Başarılı!");
				
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(375, 246, 89, 23);
		contentPane.add(btnNewButton);
		
		Tutar = new JTextField();
		Tutar.setBounds(131, 209, 137, 20);
		contentPane.add(Tutar);
		Tutar.setColumns(10);
		
		lblNewLabel_4 = new JLabel("Tutar");
		lblNewLabel_4.setBounds(58, 212, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		TC = new JLabel("asd");
		TC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		TC.setHorizontalAlignment(SwingConstants.CENTER);
		TC.setBounds(546, 24, 137, 50);
		contentPane.add(TC);
		
		AD = new JLabel("New label");
		AD.setHorizontalAlignment(SwingConstants.CENTER);
		AD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		AD.setBounds(546, 118, 137, 46);
		contentPane.add(AD);
		
		lblNewLabel_5 = new JLabel("TC");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(426, 44, 72, 14);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Ad Soyad");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(426, 136, 72, 14);
		contentPane.add(lblNewLabel_6);
		
		btnNewButton_1 = new JButton("Bilet Sil");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String s="Delete from bilet where TC=? AND SeferNo=? AND KoltukNo=?";
					PreparedStatement st=Baglanti().prepareStatement(s);
					st.setString(1, TC.getText());
					st.setString(2, Sfr.getText());
					st.setInt(3, a);
					st.executeUpdate();
					st.close();
					
				}
				catch(Exception ex) {ex.printStackTrace();}
				
				try {
					String s1="Update koltukno  set Durumu=? where KoltukNo=?";
					PreparedStatement st=Baglanti().prepareStatement(s1);
					st.setString(1,"Boş");
					st.setInt(2, a);
					st.executeUpdate();
					st.close();
				
			}
				catch(Exception hata) {hata.printStackTrace();}
				
				try{
					String s1="Select KoltukNo,Durumu from koltukno where OtobusAdi='"+Otbs.getText()+"'AND SeferNo='"+Sfr.getText()+"'";
					PreparedStatement st=Baglanti().prepareStatement(s1);
					ResultSet rs=st.executeQuery(s1);
					DefaultTableModel model=new DefaultTableModel();
					model.addColumn("KoltukNo");
					model.addColumn("Durum");
					while(rs.next()) {
						model.addRow(new Object[] {rs.getString(1),rs.getString(2)});
						
						
					}
					table.setModel(model);
					
				}
				catch(Exception hata) {
					hata.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"Silme Başarılı!");
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton_1.setBounds(68, 246, 89, 23);
		contentPane.add(btnNewButton_1);
	}
	public void veri(String a,String b,String c,String d,String e,String f,String g,String h) {
		Sfr.setText(a);
		Otbs.setText(b);
		Gzrgh.setText(c);
		Trh.setText(d);
		Saat.setText(e);
		Tutar.setText(f);
		TC.setText(g);
		AD.setText(h);
	}

}
