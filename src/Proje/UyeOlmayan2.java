package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;

public class UyeOlmayan2 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeOlmayan2 frame = new UyeOlmayan2();
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
	public UyeOlmayan2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 377);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 128, 114));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Admin ve Vezneci Giri\u015F");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				GirisEkrani f6 = new GirisEkrani();
				f6.setVisible(true);
				
				
			}
		});
		btnNewButton.setBounds(109, 121, 158, 23);
		contentPane.add(btnNewButton);
		
		JButton uyegirisbtn = new JButton("\u00DCye Giri\u015Fi");
		uyegirisbtn.setBorder(null);
		uyegirisbtn.setBackground(new Color(233, 150, 122));
		uyegirisbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UyeGirisi f9 =new UyeGirisi();
				f9.setVisible(true);
			}
		});
		uyegirisbtn.setBounds(109, 167, 158, 23);
		contentPane.add(uyegirisbtn);
		
		JButton uyeolmayangirisbtn = new JButton("\u00DCye Olmadan Devam Et");
		uyeolmayangirisbtn.setBorder(null);
		uyeolmayangirisbtn.setBackground(SystemColor.info);
		uyeolmayangirisbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UyeOlmayanSeferGoruntule f2 = new UyeOlmayanSeferGoruntule();
				f2.setVisible(true);	
				
			}
		});
		uyeolmayangirisbtn.setBounds(109, 201, 158, 23);
		contentPane.add(uyeolmayangirisbtn);
		
		JLabel lblNewLabel = new JLabel("OTOB\u00DCS OTOMASYONU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(87, 28, 225, 55);
		contentPane.add(lblNewLabel);
	}
}
