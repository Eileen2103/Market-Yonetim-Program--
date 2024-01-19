package loginPages;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import GUISettings.ColourSettings;

public class GirisEkranı {

	private JFrame frame;
	private JLabel migrosLabel;
	private JLabel kullanıcıgirisiseçinLabel;
	private JButton personelButton;
	private JButton mağazaMüdürüButton;
	private JButton genelMüdürButton;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GirisEkranı window = new GirisEkranı();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public GirisEkranı() {
		initialize();
		// Button Color and Cursor settings
				personelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						personelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						ColourSettings.setBackgroundColor(personelButton, Color.white);
						ColourSettings.setForegoundColor(personelButton, Color.orange);

					}

					@Override
					public void mouseExited(MouseEvent e) {
						ColourSettings.setOriginalBackgroundColor(personelButton);
						ColourSettings.setOriginalForegroudnColor(personelButton);
					}
				});

				mağazaMüdürüButton.addMouseListener(new MouseAdapter() {
					@Override

					public void mouseEntered(MouseEvent e) {
						mağazaMüdürüButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						ColourSettings.setBackgroundColor(mağazaMüdürüButton, Color.white);
						ColourSettings.setForegoundColor(mağazaMüdürüButton, Color.orange);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						ColourSettings.setOriginalBackgroundColor(mağazaMüdürüButton);
						ColourSettings.setOriginalForegroudnColor(mağazaMüdürüButton);
					}
				});
				genelMüdürButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						genelMüdürButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						ColourSettings.setBackgroundColor(genelMüdürButton, Color.white);
						ColourSettings.setForegoundColor(genelMüdürButton, Color.orange);
					}

					@Override
					public void mouseExited(MouseEvent e) {
						ColourSettings.setOriginalBackgroundColor(genelMüdürButton);
						ColourSettings.setOriginalForegroudnColor(genelMüdürButton);
					}
				});

				// Action settings
				mağazaMüdürüButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getFrame().setVisible(false);
						MağazaMüdürüGirisi yeniGiris = new MağazaMüdürüGirisi();
						yeniGiris.getFrame().setVisible(true);
					}
				});

				genelMüdürButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getFrame().setVisible(false);
						GenelMüdürGirisi yeniGiris = new GenelMüdürGirisi();
						yeniGiris.getFrame().setVisible(true);
					}
				});
				personelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						getFrame().setVisible(false);
						PersonelGirisi yeniGiris = new PersonelGirisi();
						yeniGiris.getFrame().setVisible(true);

					}
				});

			}

		
		
	

	
	private void initialize() {
		
		setFrame(new JFrame());
		migrosLabel = new JLabel("Magros Mağazaları Yönetim Paneli");
		migrosLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kullanıcıgirisiseçinLabel = new JLabel("Kullanıcı girişi seçiniz");
		personelButton = new JButton("Personel Girişi");
		mağazaMüdürüButton = new JButton("Mağaza Müdürü Girişi");
		genelMüdürButton = new JButton("Genel Müdür Girişi");

		getFrame().setBounds(100, 100, 450, 300);
		

		
		getFrame().getContentPane().setBackground(new Color(255, 128, 64));
		getFrame().setBackground(new Color(255, 128, 64));
		getFrame().setBounds(100, 100, 609, 430);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);

		migrosLabel.setBackground(new Color(255, 128, 0));
		migrosLabel.setFont(new Font("Bahnschrift", Font.BOLD, 32));
		migrosLabel.setBounds(28, 37, 531, 115);
		getFrame().getContentPane().add(migrosLabel);

		kullanıcıgirisiseçinLabel.setFont(new Font("Arial Nova", Font.BOLD, 24));
		kullanıcıgirisiseçinLabel.setBounds(175, 211, 266, 48);
		getFrame().getContentPane().add(kullanıcıgirisiseçinLabel);

		personelButton.setBackground(new Color(255, 255, 255));
		personelButton.setFont(new Font("Arial Nova", Font.BOLD, 12));
		personelButton.setBounds(28, 269, 164, 48);
		getFrame().getContentPane().add(personelButton);

		mağazaMüdürüButton.setBackground(new Color(255, 255, 255));
		mağazaMüdürüButton.setFont(new Font("Arial Nova", Font.BOLD, 12));
		mağazaMüdürüButton.setBounds(211, 269, 164, 49);
		getFrame().getContentPane().add(mağazaMüdürüButton);

		genelMüdürButton.setBackground(new Color(255, 255, 255));
		genelMüdürButton.setFont(new Font("Arial Nova", Font.BOLD, 12));
		genelMüdürButton.setBounds(395, 269, 164, 48);
		getFrame().getContentPane().add(genelMüdürButton);
	}
	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}
