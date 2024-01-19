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
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import GUISettings.TextSettings;
import dataControls.InfoControllerInterface;
import genelMüdür.GenelMüdürPanel;
import personelObjects.GenelMüdür;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenelMüdürGirisi implements InfoControllerInterface {

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
					GenelMüdürGirisi window = new GenelMüdürGirisi();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GenelMüdürGirisi() {
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
						if ((temp = in.readObject()) != null) {
							System.out.println("girdi1");
							if (temp instanceof GenelMüdür) {
								if (((GenelMüdür) temp).getGenelMüdürüKod()
										.equals(kullanıcıAdıtextField.getText().trim())
										&& ((GenelMüdür) temp).getPassword().equals(passwordField.getText().trim())) {
									System.out.println("giriş başarılı");
									System.out.println("sisteme girildi");
									getFrame().setVisible(false);
									GenelMüdürPanel newPanel = new GenelMüdürPanel();
									newPanel.getFrame().setVisible(true);

								}
							}

						}
					} catch (EOFException e2) {
						System.out.println("Tüm kayıtlar Listelendi...");
					} catch (ClassNotFoundException | IOException e1) {
						JOptionPane.showConfirmDialog(null, "Lütfen bilgilerinizi kontrol edin");

						e1.printStackTrace();
					}

				} else {
					System.out.println("Lütfen bilgilerinizi kontrol edin");
				}

			}
		});

		// text focus settings
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

		// Button and label settings
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
			public void mousePressed(MouseEvent e) {
				getFrame().setVisible(false);
				GirisEkranı yeniGiris = new GirisEkranı();
				yeniGiris.getFrame().setVisible(true);
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

		frame = new JFrame();
		girisYapButton = new JButton("Giriş Yap");

		backLabel = new JLabel("");
		passwordField = new JPasswordField();
		kullanıcıAdıtextField = new JTextField();

		frame.getContentPane().setBackground(new Color(255, 128, 64));
		frame.setBounds(100, 100, 330, 290);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		kullanıcıAdıtextField.setText("Kullanıcı Adı");
		kullanıcıAdıtextField.setForeground(Color.LIGHT_GRAY);
		kullanıcıAdıtextField.setColumns(10);
		kullanıcıAdıtextField.setBounds(68, 41, 187, 40);
		kullanıcıAdıtextField.setText(kullanıcıAdıText);
		frame.getContentPane().add(kullanıcıAdıtextField);

		passwordField.setText("Şifre");
		passwordField.setForeground(Color.LIGHT_GRAY);
		passwordField.setBounds(68, 112, 187, 40);
		passwordField.setText(şifreText);
		frame.getContentPane().add(passwordField);

		girisYapButton.setFont(new Font("Arial Nova", Font.BOLD, 12));
		girisYapButton.setBackground(new Color(255, 255, 255));
		girisYapButton.setBounds(119, 185, 85, 27);
		frame.getContentPane().add(girisYapButton);

		backLabel.setIcon(new ImageIcon(GenelMüdürGirisi.class.getResource("/Icons/Left.png")));
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
