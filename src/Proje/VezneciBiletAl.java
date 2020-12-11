package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class VezneciBiletAl extends JFrame {

	private JPanel contentPane;
	private JTextField Sefer;
	private JLabel lblOtobs;
	private JTextField Otbos;
	private JTextField Gzrgh;
	private JLabel lblNewLabel;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField TC;
	private JTextField Ad;
	int a;
	private JTextField Tarih;
	private JTextField Saat;
	private JButton btnNewButton_1;
	private JTextField Tutar;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VezneciBiletAl frame = new VezneciBiletAl();
					frame.setVisible(true);
					frame.setTitle("Vezneci Bilet Ekranı");
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
	public VezneciBiletAl() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {	
				try{
				String s1="Select KoltukNo,Durumu from koltukno where OtobusAdi='"+Otbos.getText()+"'AND SeferNo='"+Sefer.getText()+"'";
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
			@Override
			public void windowClosing(WindowEvent e) {
			
			}
		});
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 753, 481);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Sfr = new JLabel("Sefer No");
		Sfr.setHorizontalAlignment(SwingConstants.CENTER);
		Sfr.setBounds(10, 43, 46, 14);
		contentPane.add(Sfr);
		
		Sefer = new JTextField();
		Sefer.setBounds(110, 40, 158, 20);
		contentPane.add(Sefer);
		Sefer.setColumns(10);
		
		lblOtobs = new JLabel("Otob\u00FCs");
		lblOtobs.setHorizontalAlignment(SwingConstants.CENTER);
		lblOtobs.setBounds(10, 74, 46, 14);
		contentPane.add(lblOtobs);
		
		Otbos = new JTextField();
		Otbos.setBounds(110, 71, 158, 20);
		contentPane.add(Otbos);
		Otbos.setColumns(10);
		
		Gzrgh = new JTextField();
		Gzrgh.setBounds(110, 102, 158, 20);
		contentPane.add(Gzrgh);
		Gzrgh.setColumns(10);
		
		lblNewLabel = new JLabel("G\u00FCzergah");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 105, 46, 14);
		contentPane.add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		scrollPane.setBounds(0, 293, 737, 149);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionBackground(new Color(0, 255, 0));
		table.setSelectionForeground(new Color(34, 139, 34));
		table.setGridColor(UIManager.getColor("Tree.dropLineColor"));
		table.setBackground(UIManager.getColor("ToolBar.floatingBackground"));
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
		
		TC = new JTextField();
		TC.setBounds(641, 40, 86, 20);
		contentPane.add(TC);
		TC.setColumns(10);
		
		Ad = new JTextField();
		Ad.setBounds(641, 71, 86, 20);
		contentPane.add(Ad);
		Ad.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("TC");
		lblNewLabel_1.setBounds(565, 43, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("AD SOYAD");
		lblNewLabel_2.setBounds(565, 74, 61, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Bilet Al");
		btnNewButton.setBackground(new Color(152, 251, 152));
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				String s="Insert into bilet(SeferNo,AD,KoltukNo,Tarih,Saat,Plaka,Guzergah,TC,Fiyat) values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement st=Baglanti().prepareStatement(s);
				st.setString(1, Sefer.getText());
				st.setString(2, Ad.getText());
				st.setInt(3, a);
				st.setDate(4, Date.valueOf(Tarih.getText()));
				st.setTime(5, Time.valueOf(Saat.getText()));
				st.setString(6, Otbos.getText());
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
					String s1="Select KoltukNo,Durumu from koltukno where OtobusAdi='"+Otbos.getText()+"'AND SeferNo='"+Sefer.getText()+"'";
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
				TC.setText(null);
				Ad.setText(null);
				
				
		}});
		btnNewButton.setBounds(641, 259, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel label1 = new JLabel("Tarih");
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setBounds(10, 130, 37, 20);
		contentPane.add(label1);
		
		JLabel label1_1 = new JLabel("Saat");
		label1_1.setHorizontalAlignment(SwingConstants.CENTER);
		label1_1.setBounds(10, 161, 37, 20);
		contentPane.add(label1_1);
		
		Tarih = new JTextField();
		Tarih.setColumns(10);
		Tarih.setBounds(110, 133, 158, 20);
		contentPane.add(Tarih);
		
		Saat = new JTextField();
		Saat.setColumns(10);
		Saat.setBounds(110, 161, 158, 20);
		contentPane.add(Saat);
		
		btnNewButton_1 = new JButton("Bilet İptal");
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String s="Delete from bilet where TC=? AND KoltukNo=?";
					PreparedStatement st=Baglanti().prepareStatement(s);
					st.setString(1, TC.getText());
					st.setInt(2, a);
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
					String s1="Select KoltukNo,Durumu from koltukno where OtobusAdi='"+Otbos.getText()+"'AND SeferNo='"+Sefer.getText()+"'";
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
				TC.setText(null);
				Ad.setText(null);
			}
		});
		btnNewButton_1.setBounds(10, 259, 89, 23);
		contentPane.add(btnNewButton_1);
		
		Tutar = new JTextField();
		Tutar.setBounds(641, 102, 86, 20);
		contentPane.add(Tutar);
		Tutar.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Tutar");
		lblNewLabel_3.setBounds(565, 105, 55, 14);
		contentPane.add(lblNewLabel_3);
	}
	public void Otbus(String Otbs,String x) {
		Otbos.setText(Otbs);
		Tutar.setText(x);
	}
	public void Seferr(String sfer) {
		Sefer.setText(sfer);
	}
	public void Guzergah(String x) {
		Gzrgh.setText(x);
	}
	public void Trh(String x) {
		Tarih.setText(x);
	}
	public void Sat(String x) {
		Saat.setText(x);
	}
}
