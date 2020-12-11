package Proje;

import java.awt.EventQueue;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.Cursor;
import java.awt.SystemColor;

public class Vezneci {

	private JFrame frame;
	private JTable table;
	private JButton btnBltAl;
	private JTextField Sfr;
	private JTextField Otbs;
	private JTextField Trh;
	private JTextField Saat;
	private JTextField Gzrgh;
	private JLabel lblSaat_2;
	private JLabel lblSaat_1;
	private JToggleButton a4;
	private JScrollPane scrollPane;
	private JTextField Tutar;
	private JLabel lblNewLabel_1;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vezneci window = new Vezneci();
					window.frame.setVisible(true);
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
	 * Create the application.
	 */
	public Vezneci() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 224, 208));
		frame.setBounds(100, 100, 801, 547);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Vezneci Ekranı");
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 358, 785, 150);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setSelectionForeground(SystemColor.desktop);
		table.setSelectionBackground(new Color(144, 238, 144));
		scrollPane.setViewportView(table);
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
		table.setBackground(Color.PINK);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JButton btnSfrGrntle = new JButton("Sefer G\u00F6r\u00FCnt\u00FCle");
		btnSfrGrntle.setBackground(new Color(135, 206, 235));
		btnSfrGrntle.setBorder(null);
		btnSfrGrntle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
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
		btnSfrGrntle.setBounds(10, 324, 138, 23);
		frame.getContentPane().add(btnSfrGrntle);
		
		btnBltAl = new JButton("Bilet Al");
		btnBltAl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBltAl.setBackground(new Color(144, 238, 144));
		btnBltAl.setBorder(null);
		btnBltAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			VezneciBiletAl nf=new VezneciBiletAl();
			nf.Otbus(Otbs.getText(),Tutar.getText());
			nf.Seferr(Sfr.getText());
			nf.Guzergah(Gzrgh.getText());
			nf.Trh(Trh.getText());
			nf.Sat(Saat.getText());
			nf.setTitle("Vezneci Bilet Ekranı");
			nf.setVisible(true);
			}
		});
		btnBltAl.setBounds(494, 324, 89, 23);
		frame.getContentPane().add(btnBltAl);
		
		Sfr = new JTextField();
		Sfr.setBounds(85, 37, 138, 20);
		frame.getContentPane().add(Sfr);
		Sfr.setColumns(10);
		
		Otbs = new JTextField();
		Otbs.setColumns(10);
		Otbs.setBounds(85, 69, 138, 20);
		frame.getContentPane().add(Otbs);
		
		Trh = new JTextField();
		Trh.setColumns(10);
		Trh.setBounds(85, 100, 138, 20);
		frame.getContentPane().add(Trh);
		
		JLabel lblNewLabel = new JLabel("Sefer Adı");
		lblNewLabel.setBounds(10, 40, 65, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblOtobs = new JLabel("Otobüs ");
		lblOtobs.setBounds(10, 72, 65, 14);
		frame.getContentPane().add(lblOtobs);
		
		JLabel lblSaat = new JLabel("Tarih");
		lblSaat.setBounds(10, 100, 75, 14);
		frame.getContentPane().add(lblSaat);
		
		Saat = new JTextField();
		Saat.setColumns(10);
		Saat.setBounds(85, 130, 138, 20);
		frame.getContentPane().add(Saat);
		
		Gzrgh = new JTextField();
		Gzrgh.setColumns(10);
		Gzrgh.setBounds(85, 161, 138, 20);
		frame.getContentPane().add(Gzrgh);
		
		lblSaat_2 = new JLabel("Saat");
		lblSaat_2.setBounds(10, 133, 65, 14);
		frame.getContentPane().add(lblSaat_2);
		
		lblSaat_1 = new JLabel("Güzergah");
		lblSaat_1.setBounds(10, 164, 65, 14);
		frame.getContentPane().add(lblSaat_1);
		
		Tutar = new JTextField();
		Tutar.setBounds(85, 192, 138, 20);
		frame.getContentPane().add(Tutar);
		Tutar.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Bilet Fiyatı");
		lblNewLabel_1.setBounds(10, 195, 65, 14);
		frame.getContentPane().add(lblNewLabel_1);
	
		/**/
	}
}
