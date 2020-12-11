package Proje;
import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JOptionPane;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;

public class Otobus {

	private JFrame frame;
	private JTextField AdSoyad;
	private JTextField Plaka;
	private JTextField SicilNo;
	private JTextField KoltukNO;
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Otobus window = new Otobus();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/odev1","root","");
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Create the application.
	 */
	public Otobus() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(UIManager.getColor("Tree.dropLineColor"));
		frame.getContentPane().setBackground(new Color(124, 252, 0));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			}
		});
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Otobüs Kayıt");
		AdSoyad = new JTextField();
		AdSoyad.setBounds(338, 93, 86, 20);
		frame.getContentPane().add(AdSoyad);
		AdSoyad.setColumns(10);
		
		Plaka = new JTextField();
		Plaka.setBounds(338, 55, 86, 20);
		frame.getContentPane().add(Plaka);
		Plaka.setColumns(10);
		
		SicilNo = new JTextField();
		SicilNo.setBounds(338, 124, 86, 20);
		frame.getContentPane().add(SicilNo);
		SicilNo.setColumns(10);
		
		KoltukNO = new JTextField();
		KoltukNO.setBounds(338, 155, 86, 20);
		frame.getContentPane().add(KoltukNO);
		KoltukNO.setColumns(10);
		
		JLabel Ekle = new JLabel("OTOBÜS KAYIT");
		Ekle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Ekle.setHorizontalAlignment(SwingConstants.CENTER);
		Ekle.setVerticalAlignment(SwingConstants.TOP);
		Ekle.setBounds(79, 11, 244, 33);
		frame.getContentPane().add(Ekle);
		
		JLabel lblNewLabel = new JLabel("Plaka");
		lblNewLabel.setBounds(250, 58, 78, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblAdSoyad = new JLabel("Ad Soyad");
		lblAdSoyad.setBounds(250, 96, 86, 14);
		frame.getContentPane().add(lblAdSoyad);
		
		JLabel lblSicilNo = new JLabel("Sicil No");
		lblSicilNo.setBounds(250, 127, 96, 14);
		frame.getContentPane().add(lblSicilNo);
		
		JLabel lblKoltukSays = new JLabel("Koltuk Say\u0131s\u0131");
		lblKoltukSays.setBounds(250, 158, 114, 14);
		frame.getContentPane().add(lblKoltukSays);
		
		JButton btnNewButton = new JButton("Kaydet");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				try {
				
				
				
				
				String sorgu="INSERT INTO otobus(Plaka,AdSoyad,SicilNo,KoltukNo) values(?,?,?,?)";
				
				
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/odev1","root","");
					PreparedStatement st=conn.prepareStatement(sorgu);
					st.setString(1,Plaka.getText());
					st.setString(2,AdSoyad.getText());
					st.setString(3,SicilNo.getText());
					st.setString(4,KoltukNO.getText());
					st.execute();
					st.close();
					JOptionPane.showMessageDialog(null,"Kayıt Başarılı!");
					Plaka.setText(null);
					AdSoyad.setText(null);
					SicilNo.setText(null);
					KoltukNO.setText(null);
				}
				catch(SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(frame,ex.getMessage());
				}
				
			}
			
		});
		btnNewButton.setBounds(41, 158, 89, 23);
		frame.getContentPane().add(btnNewButton);
	
		
		
		
		
		
		
		
		
		
		
	}

	
}
