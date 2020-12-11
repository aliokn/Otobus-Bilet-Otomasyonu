package Proje;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.sql.*;
import com.mysql.jdbc.PreparedStatement;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JMonthChooser;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;

public class EtiketEkle {

	private JFrame frame;
	private JTextField Sfr;
	private JTextField Otbs;
	private JTextField Gzrgh;
	private JTextField Saat;
	private JTextField Tutar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EtiketEkle window = new EtiketEkle();
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
	public EtiketEkle() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(60, 179, 113));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Sefer Kayıt");
		Sfr = new JTextField();
		Sfr.setBounds(134, 24, 86, 20);
		frame.getContentPane().add(Sfr);
		Sfr.setColumns(10);
		
		Otbs = new JTextField();
		Otbs.setBounds(134, 55, 86, 20);
		frame.getContentPane().add(Otbs);
		Otbs.setColumns(10);
		
		JDateChooser Trh = new JDateChooser();
		Trh.setBounds(134, 86, 95, 20);
		frame.getContentPane().add(Trh);
		
		Gzrgh = new JTextField();
		Gzrgh.setBounds(134, 117, 86, 20);
		frame.getContentPane().add(Gzrgh);
		Gzrgh.setColumns(10);
		
		JButton btnNewButton = new JButton("Kay\u0131t Et");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				String sorgu ="Insert into sefer(SeferNO,Otobus,SeferTarihi,SeferGuzergahi,SeferSaati,Tutar) values(?,?,?,?,?,?)";
				try {
					PreparedStatement st=(PreparedStatement) Baglanti().prepareStatement(sorgu);
					st.setString(1,Sfr.getText());
					st.setString(2,Otbs.getText());
					st.setDate(3,new java.sql.Date(Trh.getDate().getTime()));
					st.setString(4,Gzrgh.getText());
					st.setString(5, Saat.getText());
					st.setFloat(6,Float.valueOf(Tutar.getText()));
					st.execute();
					st.close();
					JOptionPane.showMessageDialog(frame,"Kayıt Başarılı!");
				}
				catch(Exception ex) {ex.printStackTrace();
				
				}
				for(int i=1;i<31;i++) {
					try {
						String s1="Insert Into koltukno(OtobusAdi,KoltukNo,Durumu,SeferNo) values(?,?,?,?)";
						
						PreparedStatement st=(PreparedStatement) Baglanti().prepareStatement(s1);
						st.setString(1,Otbs.getText());
						st.setInt(2, i);
						st.setString(3,"Boş");
						st.setString(4, Sfr.getText());
						st.execute();
						st.close();
						
					}
					catch(SQLException ex) {ex.printStackTrace();
					JOptionPane.showMessageDialog(frame,ex.getMessage());} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				Sfr.setText(null);
				Otbs.setText(null);
				Trh.setDate(null);
				Gzrgh.setText(null);
				Saat.setText(null);
				Tutar.setText(null);
			}
		});
		btnNewButton.setBounds(106, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		Saat = new JTextField();
		Saat.setBounds(134, 148, 86, 20);
		frame.getContentPane().add(Saat);
		Saat.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sefer Numaras\u0131");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(20, 27, 95, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Otob\u00FCs Plakas\u0131");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(20, 58, 95, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sefer Tarihi");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(10, 92, 95, 14);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Sefer G\u00FCzergah\u0131");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setBounds(10, 120, 114, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Sefer Saati");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setBounds(10, 151, 95, 14);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		Tutar = new JTextField();
		Tutar.setBounds(134, 179, 86, 20);
		frame.getContentPane().add(Tutar);
		Tutar.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ücret");
		lblNewLabel_2.setBounds(32, 182, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
	}
}
