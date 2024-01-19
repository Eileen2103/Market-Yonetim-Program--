package loginPages;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import GUISettings.TextSettings;
import dataControls.InfoControllerInterface;
import personel.PAktüelÜrünlerPanel;
import personel.PStokPanel;
import personel.PersonelPanel;
import personelObjects.MarketGörevlisi;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PersonelGirisi implements InfoControllerInterface {

	private JFrame frame;
	private JTextField kullanıcıAdıtextField;
	private JPasswordField passwordField;
	private JButton girisYapButton;
	private JLabel backLabel;
	private String kullanıcıAdıText = "Kullanıcı Adı";
	private String şifreText = "Şifre";
	private File file;
	boolean append;
	private ObjectInputStream in;
	private FileInputStream fin;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonelGirisi window = new PersonelGirisi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PersonelGirisi() {
		initialize();

		try {
			fin = new FileInputStream("LoginVerify.txt");
			in = new ObjectInputStream(fin);
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		girisYapButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (checkIfInfoIsValid()) {
					Object temp;
					try {
						while ((temp = in.readObject()) != null) {

							if (temp instanceof MarketGörevlisi) {
								System.out.println("girdi1");
								if (((MarketGörevlisi) temp).getMarketGörevlisiKod()
										.equals(kullanıcıAdıtextField.getText().trim())
										&& ((MarketGörevlisi) temp).getPassword()
												.equals(passwordField.getText().trim())) {
									System.out.println("girdi2");

									String storeName = ((MarketGörevlisi) temp).getStoreName();
									PAktüelÜrünlerPanel.setStoreName(storeName); // giving the storename to the panels
																					// to
																					// get the right files
									PStokPanel.setStoreName(storeName);

									getFrame().setVisible(false);
									PersonelPanel newPanel = new PersonelPanel();
									newPanel.getFrame().setVisible(true);
								}
							}

						}
					} catch (EOFException e2) {

						System.out.println("Tüm kayıtlar Listelendi...");
					} catch (ClassNotFoundException | IOException e1) {

						e1.printStackTrace();
					}

				} else {
					System.out.println("Lütfen bilgilerinizi kontrol edin");
				}

			}
		});

		// Button settings
		girisYapButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				girisYapButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});

		backLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				getFrame().setVisible(false);
				GirisEkranı yeniGiris = new GirisEkranı();
				yeniGiris.getFrame().setVisible(true);
			}
		});

		// Text focus gained and lost settings
		kullanıcıAdıtextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TextSettings.textFocusGained(kullanıcıAdıtextField, kullanıcıAdıText);
			}

			@Override
			public void focusLost(FocusEvent e) {
				TextSettings.textFocusLost(kullanıcıAdıtextField);
			}
		});

		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				TextSettings.textFocusGained(passwordField, şifreText);
			}

			@Override
			public void focusLost(FocusEvent e) {
				TextSettings.textFocusLost(passwordField);
			}
		});

	}

	@Override
	public boolean checkIfInfoIsValid() {
		if (kullanıcıAdıtextField.getText().isEmpty() || passwordField.getText().isBlank()) {
			return false;
		} else {
			return true;
		}
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		kullanıcıAdıtextField = new JTextField();
		passwordField = new JPasswordField();
		girisYapButton = new JButton("Giriş Yap");

		backLabel = new JLabel("");

		frame.getContentPane().setBackground(new Color(255, 128, 64));
		frame.setBounds(100, 100, 330, 290);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		kullanıcıAdıtextField.setForeground(new Color(192, 192, 192));
		kullanıcıAdıtextField.setBounds(68, 41, 187, 40);
		kullanıcıAdıtextField.setColumns(10);
		frame.getContentPane().add(kullanıcıAdıtextField);
		kullanıcıAdıtextField.setText(kullanıcıAdıText);

		passwordField.setForeground(new Color(192, 192, 192));
		passwordField.setBounds(68, 112, 187, 40);
		frame.getContentPane().add(passwordField);
		passwordField.setText(şifreText);

		girisYapButton.setBackground(new Color(255, 255, 255));
		girisYapButton.setFont(new Font("Arial Nova", Font.BOLD, 12));
		girisYapButton.setBounds(119, 185, 85, 27);
		frame.getContentPane().add(girisYapButton);

		backLabel.setIcon(new ImageIcon(PersonelGirisi.class.getResource("/Icons/Left.png")));
		backLabel.setBounds(10, 10, 37, 20);
		frame.getContentPane().add(backLabel);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
