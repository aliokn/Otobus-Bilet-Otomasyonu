package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GirisEkrani extends JFrame {
	public static Connection baglanti;
	private JPanel contentPane;
	private JTextField textAd;
	private JTextField textSifre;
	
	ResultSet rs=null;
	PreparedStatement pst =null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisEkrani frame = new GirisEkrani();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void baglantiAc() {
		
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			String bag ="jdbc:mysql://localhost:3306/odev1";
			String kul="root";
			String sifre="";
			
			baglanti = DriverManager.getConnection(bag,kul,sifre);
			System.out.println("Baglanti ok");
		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
		}
	}
	
	public static void baglantiKapat() {
		try {
			baglanti.close();
			System.out.println("Baglanti kapama ok");
		} catch (Exception e) {
			System.out.println(e.toString());
			// TODO: handle exception
		}
	}
	
	

	/**
	 * Create the frame.
	 */
	public GirisEkrani() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 472, 309);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ad");
		lblNewLabel.setBounds(37, 45, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSifre = new JLabel("\u015Eifre");
		lblSifre.setBounds(37, 94, 46, 14);
		contentPane.add(lblSifre);
		
		textAd = new JTextField();
		textAd.setBounds(119, 42, 86, 20);
		contentPane.add(textAd);
		textAd.setColumns(10);
		
		textSifre = new JTextField();
		textSifre.setBounds(119, 91, 86, 20);
		contentPane.add(textSifre);
		textSifre.setColumns(10);
		
		JButton btnNewButton = new JButton("Giri\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				baglantiAc();
				
				String sql="select * from girisekrani where KullaniciAdi=? and Sifre=?";
				try {
					pst =baglanti.prepareStatement(sql);
					pst.setString(1,textAd.getText());
					pst.setString(2, textSifre.getText());
					rs=pst.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "dogru girdiniz");
						String tip = rs.getString("KullaniciTipi");
						
						System.out.println(tip);
						int yeni1=Integer.parseInt(tip);
						if(yeni1==1) {
							SeferEkrani f4 =new SeferEkrani();
							f4.main(null);
						}
						if(yeni1==2) {
							Vezneci f5 =new Vezneci();
							f5.main(null);
							
						}
						rs.close();
						pst.close();
					}
					
					else {
						JOptionPane.showMessageDialog(null, "ok");
					}
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
					
					// TODO: handle exception
				}
				finally {
					try {
						rs.close();
						pst.close();
					}
					catch (Exception e) {
						// TODO: handle exception
					}
				}
				baglantiKapat();
			}
		});
		btnNewButton.setBounds(116, 153, 89, 23);
		contentPane.add(btnNewButton);
	}
}
