package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DN extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DN frame = new DN();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static Connection Baglanti() throws Exception{
		try {
			Connection baglanti=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/odev","root","");
			return baglanti;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	String a;
	String b;
	private JTextField Tc;
	
	/**
	 * Create the frame.
	 */
	public DN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
			nf.setVisible(true);}
				else {JOptionPane.showMessageDialog(null,"Üyelik Onaylanmadi!");}
				}
				else {JOptionPane.showMessageDialog(null,"Böyle bir üyelik yok!");}
				Tc.setText(null);}
				catch(Exception ex) {ex.printStackTrace();}
				
				//ESAS MEVZU BURDAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(148, 124, 89, 23);
		contentPane.add(btnNewButton);
		
		Tc = new JTextField();
		Tc.setBounds(148, 79, 86, 20);
		contentPane.add(Tc);
		Tc.setColumns(10);
	}
}
