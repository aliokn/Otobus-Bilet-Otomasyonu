package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class UyeGirisi extends JFrame {
	public static Connection baglanti;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField Tc;

	ResultSet rs=null;
	PreparedStatement pst =null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeGirisi frame = new UyeGirisi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
String a;
String b;
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
	public UyeGirisi() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 483, 343);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kullan\u0131c\u0131 Ad\u0131");
		lblNewLabel.setBounds(37, 65, 100, 14);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(201, 62, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Giri\u015F Yap");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					String s="Select TC,AD,Onay from uye where TC='"+Tc.getText()+"'";
					PreparedStatement st=Baglanti().prepareStatement(s);
					st.execute();
					ResultSet rs=st.executeQuery(s);
					if(rs.next()) {if(rs.getString(3).equals("Onaylandi")) {
				
					
					a=String.valueOf(rs.getString(1).toString());
					b=String.valueOf(rs.getString(2).toString());
										
				
				UyeBiletAl nf=new UyeBiletAl();
				nf.ver(a, b);
				nf.setVisible(true);
				nf.setTitle("Üye Sefer Görüntüleme Ekranı");}
					else {JOptionPane.showMessageDialog(null,"Üyelik Onaylanmadi!");}
					}
					else {JOptionPane.showMessageDialog(null,"Böyle bir üyelik yok!");}
					Tc.setText(null);}
					catch(Exception ex) {ex.printStackTrace();}
				
				
			}
		});
		btnNewButton.setBounds(156, 188, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel textSifre1 = new JLabel("\u015Eifre");
		textSifre1.setBounds(37, 120, 67, 14);
		contentPane.add(textSifre1);
		
		Tc = new JTextField();
		Tc.setBounds(201, 117, 86, 20);
		contentPane.add(Tc);
		Tc.setColumns(10);
	}

}
