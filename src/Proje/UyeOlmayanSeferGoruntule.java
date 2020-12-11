package Proje;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class UyeOlmayanSeferGoruntule extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UyeOlmayanSeferGoruntule frame = new UyeOlmayanSeferGoruntule();
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
	public UyeOlmayanSeferGoruntule() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 469);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 264, 434, 166);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.PINK);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Sefer G\u00F6r\u00FCnt\u00FCle");
		btnNewButton.setBorder(null);
		btnNewButton.setBackground(new Color(224, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sorgu="SELECT * FROM sefer";


                try {
                DefaultTableModel model=new DefaultTableModel();
                Statement st=Baglanti().createStatement();
                ResultSet rs=st.executeQuery(sorgu);
                model.addColumn("Sefer Numarası");
                model.addColumn("Otobüs");
                model.addColumn("Tarih");
                model.addColumn("Sefer Saati");
                model.addColumn("Güzergah");
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
		btnNewButton.setBounds(65, 205, 114, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u00DCye Ol");
		btnNewButton_1.setBackground(new Color(60, 179, 113));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				UyeOl f1 = new UyeOl();
				f1.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(285, 205, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}
