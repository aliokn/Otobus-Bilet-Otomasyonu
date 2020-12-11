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
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;

public class UyeBiletAl extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField Otbs;
	private JTextField Sfr;
	private JTextField Trh;
	private JTextField Saat;
	private JTextField Gzrgh;
	private JLabel TC;
	private JLabel Ad;
	private JTextField Tutar;
	private JLabel lblNewLabel_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeBiletAl frame = new UyeBiletAl();
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
public void ver(String x,String y) {
	
	TC.setText(x);
	Ad.setText(y);
	
}



	/**
	 * Create the frame.
	 */
	public UyeBiletAl() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 475);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 268, 788, 168);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionBackground(new Color(144, 238, 144));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				JTable table=(JTable)e.getSource();
				int row=table.getSelectedRow();
				Sfr.setText(table.getModel().getValueAt(row, 0).toString());
				Otbs.setText(table.getModel().getValueAt(row, 1).toString());	
				Trh.setText(table.getModel().getValueAt(row, 2).toString());	
				Saat.setText(table.getModel().getValueAt(row, 4).toString());		
				Gzrgh.setText(table.getModel().getValueAt(row, 3).toString());
				Tutar.setText(table.getModel().getValueAt(row, 5).toString());
				try{
					String s1="Select Durumu from koltukno where OtobusAdi='"+Otbs.getText()+"'AND SeferNo='"+Sfr.getText()+"'";
					PreparedStatement st=Baglanti().prepareStatement(s1);
					ResultSet rs=st.executeQuery(s1);
				
				
					
				}
				catch(Exception hata) {
					hata.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		Otbs = new JTextField();
		Otbs.setBounds(98, 87, 118, 20);
		contentPane.add(Otbs);
		Otbs.setColumns(10);
		
		Sfr = new JTextField();
		Sfr.setBounds(98, 56, 118, 20);
		contentPane.add(Sfr);
		Sfr.setColumns(10);
		
		Trh = new JTextField();
		Trh.setBounds(98, 118, 118, 20);
		contentPane.add(Trh);
		Trh.setColumns(10);
		
		Saat = new JTextField();
		Saat.setBounds(98, 149, 118, 20);
		contentPane.add(Saat);
		Saat.setColumns(10);
		
		Gzrgh = new JTextField();
		Gzrgh.setBounds(98, 180, 118, 20);
		contentPane.add(Gzrgh);
		Gzrgh.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sefer Ad\u0131");
		lblNewLabel.setBounds(10, 59, 58, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Otob\u00FCs");
		lblNewLabel_1.setBounds(10, 90, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tarih");
		lblNewLabel_2.setBounds(10, 121, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Saat");
		lblNewLabel_3.setBounds(10, 152, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("G\u00FCzergah");
		lblNewLabel_4.setBounds(10, 183, 69, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Bilet Al");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UyeKoltukAl nf=new UyeKoltukAl();
				nf.veri(Sfr.getText(), Otbs.getText(), Gzrgh.getText(), Trh.getText(), Saat.getText(),Tutar.getText(),TC.getText(),Ad.getText());
				nf.setTitle("Üye Müşteri Bilet Ekranı");
				nf.setVisible(true);
			}
		});
		btnNewButton.setBounds(287, 234, 89, 23);
		contentPane.add(btnNewButton);
		
		TC = new JLabel("New label");
		TC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		TC.setHorizontalAlignment(SwingConstants.CENTER);
		TC.setBounds(582, 48, 154, 36);
		contentPane.add(TC);
		
		Ad = new JLabel("New label");
		Ad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Ad.setHorizontalAlignment(SwingConstants.CENTER);
		Ad.setBounds(582, 118, 154, 36);
		contentPane.add(Ad);
		
		Tutar = new JTextField();
		Tutar.setBounds(98, 211, 118, 20);
		contentPane.add(Tutar);
		Tutar.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Ücret");
		lblNewLabel_5.setBounds(10, 214, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		
	}
}
