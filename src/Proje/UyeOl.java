package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class UyeOl extends JFrame {

	private JPanel contentPane;
	private JTextField Tc;
	private JTextField Ad;
//ÜYE OL İNSERT ET
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeOl frame = new UyeOl();
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
	public UyeOl() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 382, 320);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Tc = new JTextField();
		Tc.setBounds(224, 88, 112, 20);
		contentPane.add(Tc);
		Tc.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("TC(Şifreniz Olacaktır)");
		lblNewLabel.setBounds(10, 91, 122, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad(Kullanıcı Adınız Olacaktır)");
		lblNewLabel_1.setBounds(10, 116, 215, 14);
		contentPane.add(lblNewLabel_1);
		
		Ad = new JTextField();
		Ad.setBounds(224, 113, 112, 20);
		contentPane.add(Ad);
		Ad.setColumns(10);
		
		JButton btnNewButton = new JButton("Üye Ol");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s="Insert into uye(TC,Ad,Onay) values (?,?,?)";
				try{
					PreparedStatement st=Baglanti().prepareStatement(s);
					st.setString(1, Tc.getText());
					st.setString(2, Ad.getText());
					st.setString(3, "Onaylanmadi");
					st.execute();
					st.close();
					
					JOptionPane.showMessageDialog(null,"Kayıt Başarılı! Lütfen Üyeliğinizin Onaylanmasını Bekleyin");
					Tc.setText(null);
					Ad.setText(null);
				}
				catch(Exception ex) {ex.printStackTrace();}
				
			}
		});
		btnNewButton.setBounds(161, 178, 89, 23);
		contentPane.add(btnNewButton);
	}
}
