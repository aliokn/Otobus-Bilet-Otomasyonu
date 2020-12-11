package Proje;

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
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

public class SeferEkrani {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					SeferEkrani window = new SeferEkrani();
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
	public SeferEkrani() {
		initialize();
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.info);
		frame.setBounds(100, 100, 699, 439);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Yönetici Ekranı");
		JLabel lblNewLabel = new JLabel("Y\u00D6NET\u0130C\u0130 EKRANI");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblNewLabel.setBounds(187, 5, 287, 35);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 247, 683, 152);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setBackground(Color.ORANGE);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		
		JButton btnOtbsEkle = new JButton("Otob\u00FCs Ekle");
		btnOtbsEkle.setBackground(new Color(0, 255, 0));
		btnOtbsEkle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Otobus f1=new Otobus();
				f1.main(null);
			}
		});
		btnOtbsEkle.setBounds(573, 60, 106, 23);
		frame.getContentPane().add(btnOtbsEkle);
		
		JButton btnOtobsSil = new JButton("Otob\u00FCs Sil");
		btnOtobsSil.setBackground(new Color(255, 0, 0));
		btnOtobsSil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OtobusCikar f1=new OtobusCikar();
				f1.setVisible(true);
				f1.setTitle("Otobüs Silme Ekranı");
				
				
				
			}
		});
		btnOtobsSil.setBounds(573, 94, 106, 23);
		frame.getContentPane().add(btnOtobsSil);
		
		JLabel lblNewLabel_1 = new JLabel("Otob\u00FCs \u0130\u015Flemleri");
		lblNewLabel_1.setBounds(573, 11, 110, 35);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnSfrEkrn = new JButton("Sefer Ekle");
		btnSfrEkrn.setBorder(null);
		btnSfrEkrn.setBackground(new Color(0, 255, 0));
		btnSfrEkrn.setBounds(10, 60, 166, 23);
		/*Sefer Ekleme Ekranını buraya koy*/btnSfrEkrn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			EtiketEkle f1=new EtiketEkle();
			f1.main(null);
			
			}
		});
		frame.getContentPane().add(btnSfrEkrn);
		
		JButton btnSfrGrntle = new JButton("Sefer G\u00F6r\u00FCnt\u00FCle");
		btnSfrGrntle.setBackground(new Color(72, 209, 204));
		btnSfrGrntle.setBorder(null);
		btnSfrGrntle.setBounds(10, 213, 166, 23);
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
				model.addColumn("Tutar");
				
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
		frame.getContentPane().add(btnSfrGrntle);
		
		JButton btnUylk = new JButton("\u00DCyelik Ekran\u0131");
		btnUylk.setBackground(new Color(147, 112, 219));
		btnUylk.setBorder(null);
		btnUylk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UyeYonetici nf=new UyeYonetici();
				nf.setVisible(true);
				nf.setTitle("Üyelik Onaylama Ekranı");
			}
		});
		btnUylk.setBounds(543, 213, 130, 23);
		frame.getContentPane().add(btnUylk);
		
		JButton btnNewButton = new JButton("Sefer Çıkar");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SeferCikar s=new SeferCikar();
			s.setVisible(true);
			s.setTitle("Sefer Silme Ekranı");
			}
		});
		btnNewButton.setBounds(10, 94, 166, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
