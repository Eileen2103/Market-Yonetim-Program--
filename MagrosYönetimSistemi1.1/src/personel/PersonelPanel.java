package personel;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
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

public class PersonelPanel {

	private JFrame frame;
	private static JPanel bodyPanel;;
	private JPanel panel;
	private JLabel kontorlPaneliLabel;
	private JPanel subPanel1;
	private JLabel aktüelLabel;
	private JLabel stokLabel;
	private JLabel backLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonelPanel window = new PersonelPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PersonelPanel() {
		initialize();
		
		aktüelLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				aktüelLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				ColourSettings.setForegoundColor(aktüelLabel, Color.white);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setNewPanel(new PAktüelÜrünlerPanel());
				kontorlPaneliLabel.setText("Personel Kontrol Paneli | Aktüel Ürünler ");

			}

			@Override
			public void mouseExited(MouseEvent e) {

				ColourSettings.setOriginalForegroudnColor(aktüelLabel);
			}
		});
		stokLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				stokLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				ColourSettings.setForegoundColor(stokLabel, Color.white);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setNewPanel(new PStokPanel());
				kontorlPaneliLabel.setText("Personel Kontrol Paneli | Stok ");
				System.out.println("stok label action worked");
				System.out.println("1");
				System.out.println("2");
			}

			@Override
			public void mouseExited(MouseEvent e) {
				ColourSettings.setOriginalForegroudnColor(stokLabel);
			}
		});
		backLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = JOptionPane.showConfirmDialog(null, "Çıkış yapmak istediğinize emin misiniz?");
				if (a == JOptionPane.YES_OPTION) {
					getFrame().setVisible(false);

					GirisEkranı yeniGiris2 = new GirisEkranı();
					yeniGiris2.getFrame().setVisible(true);

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

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
		frame.setBounds(100, 100, 1110, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(SystemColor.controlDkShadow);
		panel.setBounds(0, 0, 1094, 63);
		frame.getContentPane().add(panel);

		subPanel1 = new JPanel();
		subPanel1.setLayout(null);
		subPanel1.setBackground(SystemColor.controlDkShadow);
		subPanel1.setBounds(0, 62, 241, 699);
		frame.getContentPane().add(subPanel1);

		kontorlPaneliLabel = new JLabel("Personel Kontrol Paneli");
		kontorlPaneliLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		kontorlPaneliLabel.setBounds(21, 9, 366, 53);
		panel.add(kontorlPaneliLabel);

		backLabel = new JLabel("");

		backLabel.setIcon(new ImageIcon(PersonelPanel.class.getResource("/icons/Back Arrow.png")));
		backLabel.setBounds(994, 7, 50, 53);
		panel.add(backLabel);

		aktüelLabel = new JLabel("Aktüel Ürünler");
		aktüelLabel.setHorizontalAlignment(SwingConstants.LEFT);
		aktüelLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		aktüelLabel.setBounds(10, 86, 202, 45);
		subPanel1.add(aktüelLabel);

		stokLabel = new JLabel("Stok Bilgisi");
		stokLabel.setHorizontalAlignment(SwingConstants.LEFT);
		stokLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		stokLabel.setBounds(10, 31, 202, 45);
		subPanel1.add(stokLabel);

		bodyPanel = new JPanel();
		bodyPanel.setBounds(238, 63, 856, 698);
		frame.getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

}