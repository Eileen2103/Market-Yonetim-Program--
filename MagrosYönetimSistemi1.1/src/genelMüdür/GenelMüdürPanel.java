package genelMüdür;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import GUISettings.ColourSettings;
import loginPages.GirisEkranı;

import java.awt.SystemColor;

public class GenelMüdürPanel {

	private JFrame frame;
	private JPanel panel;
	private static JPanel bodyPanel;
	private JLabel userNameLabel;
	private JPanel subPanel1;
	private JLabel şubelerLabel;
	private JLabel personelLabel;
	private JLabel genelStokLabel;
	private JLabel kontorlPaneliLabel;
	private JLabel backLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenelMüdürPanel window = new GenelMüdürPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GenelMüdürPanel() {

		initialize();

		// Setting new panel settings

		genelStokLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				genelStokLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				ColourSettings.setForegoundColor(genelStokLabel, Color.white);

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setNewPanel(new GenelStokPanel());
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
				setNewPanel(new PersonelOperationsPanel());
				kontorlPaneliLabel.setText("Kontrol Paneli | Personel");

			}

			@Override
			public void mouseExited(MouseEvent e) {
				ColourSettings.setOriginalForegroudnColor(personelLabel);
			}
		});

		// Hand cursor settings for labels
		şubelerLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				şubelerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				ColourSettings.setForegoundColor(şubelerLabel, Color.white);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setNewPanel(new şubelerPanel());
				kontorlPaneliLabel.setText("Kontrol Paneli | Şubeler");

			}

			@Override
			public void mouseExited(MouseEvent e) {

				ColourSettings.setOriginalForegroudnColor(şubelerLabel);
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

	public static void setSelectedPanel() {
		int resultPanel = şubelerPanel.getSelectedPanel();
		if (resultPanel == 0) {
			setNewPanel(new PersonelOperationsPanel());
		} else if (resultPanel == 1) {
			setNewPanel(new GenelStokPanel());
		}

	}

	private void initialize() {

		frame = new JFrame();
		panel = new JPanel();
		subPanel1 = new JPanel();
		bodyPanel = new JPanel();
		userNameLabel = new JLabel("Aylin Türkyılmaz");
		userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		kontorlPaneliLabel = new JLabel("Kontrol Paneli");
		personelLabel = new JLabel("Personel");
		şubelerLabel = new JLabel("Şubeler");
		genelStokLabel = new JLabel("Genel Stok");

		frame.setBounds(100, 100, 1110, 770);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setTitle("Genel Müdür Kontrol Paneli");

		panel.setBackground(SystemColor.controlDkShadow);
		panel.setBounds(0, 0, 1094, 63);
		panel.setLayout(null);
		frame.getContentPane().add(panel);

		subPanel1.setLayout(null);
		subPanel1.setBackground(SystemColor.controlDkShadow);
		subPanel1.setBounds(0, 62, 241, 699);
		frame.getContentPane().add(subPanel1);

		bodyPanel.setBounds(238, 63, 856, 698);
		bodyPanel.setLayout(null);
		frame.getContentPane().add(bodyPanel);

		şubelerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		şubelerLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		şubelerLabel.setBounds(10, 20, 202, 45);
		subPanel1.add(şubelerLabel);

		personelLabel.setHorizontalAlignment(SwingConstants.LEFT);
		personelLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		personelLabel.setBounds(10, 75, 202, 45);
		subPanel1.add(personelLabel);

		genelStokLabel.setHorizontalAlignment(SwingConstants.LEFT);
		genelStokLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		genelStokLabel.setBounds(10, 130, 202, 45);
		subPanel1.add(genelStokLabel);

		userNameLabel.setFont(new Font("Arial Nova", Font.BOLD, 15));
		userNameLabel.setBounds(817, 21, 184, 29);
		panel.add(userNameLabel);

		kontorlPaneliLabel.setIcon(new ImageIcon(GenelMüdürPanel.class.getResource("/icons/Circled M.orange.png")));
		kontorlPaneliLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		kontorlPaneliLabel.setBounds(21, 9, 299, 53);
		panel.add(kontorlPaneliLabel);

		backLabel = new JLabel("");
		backLabel.setIcon(new ImageIcon(GenelMüdürPanel.class.getResource("/icons/Back Arrow.png")));
		backLabel.setBounds(999, 6, 50, 56);
		panel.add(backLabel);

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
