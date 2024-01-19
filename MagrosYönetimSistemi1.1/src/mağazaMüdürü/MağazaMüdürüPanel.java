package mağazaMüdürü;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import GUISettings.ColourSettings;
import loginPages.GirisEkranı;

import javax.swing.ImageIcon;

public class MağazaMüdürüPanel {

	private JFrame frame;
	private static JPanel bodyPanel;
	private JPanel panel;
	private JLabel userNameLabel;
	private JLabel kontorlPaneliLabel;
	private JPanel subPanel1;
	private JLabel personelLabel;
	private JLabel genelStokLabel;

	private static String nameSurname;
	private JLabel backLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MağazaMüdürüPanel window = new MağazaMüdürüPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MağazaMüdürüPanel() {

		initialize();
		userNameLabel.setText(nameSurname);

		genelStokLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				genelStokLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				ColourSettings.setForegoundColor(genelStokLabel, Color.white);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setNewPanel(new MMStokPanel());
				kontorlPaneliLabel.setText("Kontrol Paneli | Genel Stok");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ColourSettings.setOriginalForegroudnColor(genelStokLabel);
			}
		});

		personelLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				personelLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				ColourSettings.setForegoundColor(personelLabel, Color.white);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setNewPanel(new MMPersonelPanel());
				kontorlPaneliLabel.setText("Kontrol Paneli | Personel");
			}

			@Override
			public void mouseExited(MouseEvent e) {

				ColourSettings.setOriginalForegroudnColor(personelLabel);
			}
		});
		backLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Çıkış yapmak istediğinize emin misiniz?");
				if (a == JOptionPane.YES_OPTION) {
					getFrame().setVisible(false);

					GirisEkranı yeniGiris2 = new GirisEkranı();
					yeniGiris2.getFrame().setVisible(true);

				}
			}
		});

	}

	private static void setNewPanel(Component component) {
		bodyPanel.removeAll();
		bodyPanel.add(component);
		bodyPanel.repaint();
		bodyPanel.revalidate();

	}

	private void initialize() {

		frame = new JFrame();
		panel = new JPanel();
		userNameLabel = new JLabel("");
		userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		kontorlPaneliLabel = new JLabel("Kontrol Paneli");
		subPanel1 = new JPanel();
		personelLabel = new JLabel("Personel");
		genelStokLabel = new JLabel("Genel Stok");
		bodyPanel = new JPanel();

		frame.setBounds(100, 100, 1110, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Mağaza Müdürü Kontrol Paneli");

		panel.setLayout(null);
		panel.setBackground(SystemColor.controlDkShadow);
		panel.setBounds(0, 0, 1094, 63);
		frame.getContentPane().add(panel);

		userNameLabel.setFont(new Font("Arial Nova", Font.BOLD, 15));
		userNameLabel.setBounds(741, 21, 240, 29);
		panel.add(userNameLabel);

		kontorlPaneliLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		kontorlPaneliLabel.setBounds(21, 9, 262, 53);
		panel.add(kontorlPaneliLabel);

		backLabel = new JLabel("");

		backLabel.setIcon(new ImageIcon(MağazaMüdürüPanel.class.getResource("/icons/Back Arrow.png")));
		backLabel.setBounds(1001, 7, 50, 53);
		panel.add(backLabel);

		subPanel1.setLayout(null);
		subPanel1.setBackground(SystemColor.controlDkShadow);
		subPanel1.setBounds(0, 62, 241, 699);
		frame.getContentPane().add(subPanel1);

		personelLabel.setHorizontalAlignment(SwingConstants.LEFT);
		personelLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		personelLabel.setBounds(10, 27, 202, 45);
		subPanel1.add(personelLabel);

		genelStokLabel.setHorizontalAlignment(SwingConstants.LEFT);
		genelStokLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		genelStokLabel.setBounds(10, 82, 202, 45);
		subPanel1.add(genelStokLabel);

		bodyPanel.setLayout(null);
		bodyPanel.setBounds(238, 63, 856, 698);
		frame.getContentPane().add(bodyPanel);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public String getNameSurname() {
		return nameSurname;
	}

	public static void setNameSurname(String newNameSurname) {
		nameSurname = newNameSurname;
	}
}
