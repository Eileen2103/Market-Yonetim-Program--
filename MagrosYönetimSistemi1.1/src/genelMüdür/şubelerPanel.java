package genelMüdür;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;

import GUISettings.TextSettings;
import dataControls.InfoControllerInterface;
import şubeObjects.M2Şube;
import şubeObjects.M3Şube;
import şubeObjects.M5Şube;
import şubeObjects.MJetŞube;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import FileControls.AppendableObjectOutputStream2;

public class şubelerPanel extends JPanel implements Serializable, InfoControllerInterface {

	private static final long serialVersionUID = 1L;
	private JTable şubeBilgisitable;
	private JTextField postaKoduTextField;
	private JTextField açıkAdresTextField;
	private JLabel şubeBilgisiListeleJLabel;
	private JLabel şubeKoduJLabel1;
	private JButton listeleButton;
	private JLabel yeniŞubeEkleJLabel;
	private JLabel şubeTürüJLabel;
	private JLabel açıkAdresJLabel;
	private JLabel ilçeJLabeli;
	private JLabel postaKoduJlabel;
	private static JComboBox şubeTürüComboBox;
	private static JComboBox ilçeComboBox;
	private JButton ekleButton;
	private static int selectedPanel;
	private String storeName;
	private JScrollPane scrollPane;

	private FileOutputStream fout;
	private AppendableObjectOutputStream2 out;
	private File file;
	boolean append;
	private ObjectInputStream in;
	private FileInputStream fin;
	private JTextField mağazaKodutextField;

	public şubelerPanel() {
		setPanel();

		// Mouse settings
		listeleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				listeleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		
		//Listing the info of a store
		listeleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) şubeBilgisitable.getModel();
				model.setRowCount(0);
				String tempStoreKod = mağazaKodutextField.getText().trim();

				try {
					fin = new FileInputStream("şubeler.txt");
					in = new ObjectInputStream(fin);
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				Object temp;
				try {
					while ((temp = in.readObject()) != null) {

						if (temp instanceof M5Şube) {
							if (((M5Şube) temp).getStoreName().equals(tempStoreKod)) {

								String mağazaTürü = ((M5Şube) temp).getStoreTürü();
								System.out.println(mağazaTürü);
								String ilçe = ((M5Şube) temp).getİlçe();
								System.out.println(ilçe);
								String postaKodu = ((M5Şube) temp).getPostaKodu();
								String açıkAdres = ((M5Şube) temp).getAçıkAdres();
								String[] data = { mağazaTürü, ilçe, açıkAdres, postaKodu };
								model.addRow(data);
							}

						}
						if (temp instanceof M3Şube) {
							if (((M3Şube) temp).getStoreName().equals(tempStoreKod)) {

								String mağazaTürü = ((M3Şube) temp).getStoreTürü();
								System.out.println(mağazaTürü);
								String ilçe = ((M3Şube) temp).getİlçe();
								System.out.println(ilçe);
								String postaKodu = ((M3Şube) temp).getPostaKodu();
								String açıkAdres = ((M3Şube) temp).getAçıkAdres();
								String[] data = { mağazaTürü, ilçe, açıkAdres, postaKodu };
								model.addRow(data);
							}

						}
						if (temp instanceof M2Şube) {
							if (((M2Şube) temp).getStoreName().equals(tempStoreKod)) {

								String mağazaTürü = ((M2Şube) temp).getStoreTürü();
								System.out.println(mağazaTürü);
								String ilçe = ((M2Şube) temp).getİlçe();
								System.out.println(ilçe);
								String postaKodu = ((M2Şube) temp).getPostaKodu();
								String açıkAdres = ((M2Şube) temp).getAçıkAdres();
								String[] data = { mağazaTürü, ilçe, açıkAdres, postaKodu };
								model.addRow(data);
							}

						}
						if (temp instanceof MJetŞube) {
							if (((MJetŞube) temp).getStoreName().equals(tempStoreKod)) {

								String mağazaTürü = ((MJetŞube) temp).getStoreTürü();
								System.out.println(mağazaTürü);
								String ilçe = ((MJetŞube) temp).getİlçe();
								System.out.println(ilçe);
								String postaKodu = ((MJetŞube) temp).getPostaKodu();
								String açıkAdres = ((MJetŞube) temp).getAçıkAdres();
								String[] data = { mağazaTürü, ilçe, açıkAdres, postaKodu };
								model.addRow(data);
							}
						}

					}

				} catch (EOFException e2) {
					System.out.println("Tüm kayıtlar Listelendi...");
				} catch (ClassNotFoundException | IOException e1) {

					e1.printStackTrace();
				}

			}
		});

		
		//Adding new store 
		ekleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ekleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				createNewStore();

			}
		});

	}

	public void createNewStore() {
		try {
			if (checkIfInfoIsValid()) {
				file = new File("şubeler.txt"); //  txt file for all the stores in the directory
				append = file.exists();
				String şubeTürü = (String) şubeTürüComboBox.getSelectedItem();

				try {
					fout = new FileOutputStream(file, append);
					out = new AppendableObjectOutputStream2(fout, append);
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				switch (şubeTürü) {
				case "M5":
					M5Şube yeniM5Şube = new M5Şube(String.valueOf(postaKoduTextField.getText().trim()),
							String.valueOf(açıkAdresTextField.getText().trim()),
							(String) ilçeComboBox.getSelectedItem());
					storeName=yeniM5Şube.getStoreName();
					out.writeObject(yeniM5Şube);
					out.flush();
					break;
				case "M3":
					M3Şube yeniM3Şube = new M3Şube(String.valueOf(postaKoduTextField.getText().trim()),
							String.valueOf(açıkAdresTextField.getText().trim()),
							(String) ilçeComboBox.getSelectedItem());
					storeName=yeniM3Şube.getStoreName();
					out.writeObject(yeniM3Şube);
					out.flush();
					break;
				case "M2":
					M2Şube yeniM2Şube = new M2Şube(String.valueOf(postaKoduTextField.getText().trim()),
							String.valueOf(açıkAdresTextField.getText().trim()),
							(String) ilçeComboBox.getSelectedItem());
					storeName=yeniM2Şube.getStoreName();
					out.writeObject(yeniM2Şube);
					out.flush();
					break;
				case "MJet":
					MJetŞube yeniMJetŞube = new MJetŞube(String.valueOf(postaKoduTextField.getText().trim()),
							String.valueOf(açıkAdresTextField.getText().trim()),
							(String) ilçeComboBox.getSelectedItem());
					storeName=yeniMJetŞube.getStoreName();
					out.writeObject(yeniMJetŞube);
					out.flush();
					break;
				default:
					System.out.println("An error occured");
					break;
				}

				int result = JOptionPane.showConfirmDialog(null,"Mağaza Kodu:"+storeName+
						" İşlem başarılı.Yeni şube verisi eklemek ister misiniz?", "", JOptionPane.OK_CANCEL_OPTION);

				if (result == JOptionPane.OK_OPTION) {
					String[] options = { "Personel Paneli", "Genel Stok Paneli" };
					selectedPanel = JOptionPane.showOptionDialog(null, "Gitmek istediğiniz paneli seçin",
							"Panel Seçimi", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);
					GenelMüdürPanel.setSelectedPanel();

				} else if (result == JOptionPane.CANCEL_OPTION) {

				}

			} else {
				JOptionPane.showConfirmDialog(null, "Lütfen bilgileri doğru girip girmediğinizi kontrol edin.", "",
						JOptionPane.OK_OPTION);

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An error occurred.");
		}

	}

	@Override
	public boolean checkIfInfoIsValid() {
		if (TextSettings.ifTextFieldIsFilled(şubeTürüComboBox, ilçeComboBox, postaKoduTextField, açıkAdresTextField)) {
			return true;
		} else {
			return false;
		}

	}

	public void setPanel() {
		açıkAdresTextField = new JTextField();
		postaKoduTextField = new JTextField();
		yeniŞubeEkleJLabel = new JLabel("Yeni Şube Ekle");
		şubeTürüJLabel = new JLabel("Şube Türü");
		açıkAdresJLabel = new JLabel("Şube Açık Adresi");
		ilçeJLabeli = new JLabel("İlçe");
		postaKoduJlabel = new JLabel("Posta Kodu");
		şubeBilgisiListeleJLabel = new JLabel("Şube Bilgisi Listele");
		şubeKoduJLabel1 = new JLabel("Şube Kodu");
		ekleButton = new JButton("Ekle");

		listeleButton = new JButton("Listele");
		listeleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		şubeTürüComboBox = new JComboBox();
		ilçeComboBox = new JComboBox();

		setBackground(new Color(240, 240, 240));
		setBounds(0, 0, 857, 698);
		setLayout(null);

		şubeBilgisiListeleJLabel.setFont(new Font("Arial", Font.BOLD, 20));
		şubeBilgisiListeleJLabel.setBounds(38, 25, 244, 31);
		add(şubeBilgisiListeleJLabel);

		şubeKoduJLabel1.setFont(new Font("Arial", Font.PLAIN, 14));
		şubeKoduJLabel1.setBounds(27, 66, 71, 25);
		add(şubeKoduJLabel1);

		listeleButton.setFont(new Font("Arial", Font.BOLD, 15));
		listeleButton.setBounds(300, 63, 104, 30);
		listeleButton.setBackground(new Color(255, 255, 255));
		add(listeleButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 124, 837, 67);
		add(scrollPane);
		şubeBilgisitable = new JTable();
		şubeBilgisitable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Mağaza Türü", "İlçe", "Açık Adres", "Posta Kodu" }));
		scrollPane.setViewportView(şubeBilgisitable);

		yeniŞubeEkleJLabel.setFont(new Font("Arial", Font.BOLD, 20));
		yeniŞubeEkleJLabel.setBounds(136, 238, 210, 21);
		add(yeniŞubeEkleJLabel);

		şubeTürüJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		şubeTürüJLabel.setBounds(170, 311, 118, 29);
		add(şubeTürüJLabel);

		açıkAdresJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		açıkAdresJLabel.setBounds(170, 458, 118, 29);
		add(açıkAdresJLabel);

		ilçeJLabeli.setFont(new Font("Arial", Font.PLAIN, 15));
		ilçeJLabeli.setBounds(170, 360, 118, 29);
		add(ilçeJLabeli);

		postaKoduJlabel.setFont(new Font("Arial", Font.PLAIN, 15));
		postaKoduJlabel.setBounds(170, 409, 118, 29);
		add(postaKoduJlabel);

		şubeTürüComboBox.setBounds(300, 312, 59, 29);
		şubeTürüComboBox.setModel(new DefaultComboBoxModel(new String[] { "M5", "M3", "M2", "MJet" }));
		şubeTürüComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(şubeTürüComboBox);

		ilçeComboBox.setBounds(300, 361, 138, 29);
		ilçeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ilçeComboBox.setModel(new DefaultComboBoxModel(new String[] { "Adalar", "Arnavutköy", "Ataşehir", "Avcılar",
				"Bağcılar", "Bahçelievler", "Bakırköy", "Başakşehir", "Bayrampaşa", "Beşiktaş", "Beykoz", "Beylikdüzü",
				"Beyoğlu", "Büyükçekmece", "Çatalca", "Çekmeköy", "Esenler", "Esenyurt", "Eyüp", "Fatih",
				"Gaziosmanpaşa", "Güngören", "Kadıköy", "Kâğıthane", "Kartal", "Küçükçekmece", "Maltepe", "Pendik",
				"Sancaktepe", "Sarıyer", "Silivri", "Sultanbeyli", "Sultangazi", "Şile", "Şişli", "Tuzla", "Ümraniye",
				"Üsküdar", "Zeytinburnu" }));
		add(ilçeComboBox);

		postaKoduTextField.setBounds(300, 410, 92, 29);
		add(postaKoduTextField);
		postaKoduTextField.setColumns(10);
		TextSettings.setOnlyNumber(postaKoduTextField);

		açıkAdresTextField.setBounds(300, 469, 217, 82);
		açıkAdresTextField.setHorizontalAlignment(JTextField.LEFT);
		add(açıkAdresTextField);
		açıkAdresTextField.setColumns(10);

		ekleButton.setFont(new Font("Arial", Font.BOLD, 15));
		ekleButton.setBounds(370, 572, 85, 31);
		ekleButton.setBackground(new Color(255, 255, 255));
		add(ekleButton);

		mağazaKodutextField = new JTextField();
		mağazaKodutextField.setColumns(10);
		mağazaKodutextField.setBounds(108, 66, 178, 28);
		add(mağazaKodutextField);

	}

	public JComboBox getŞubeTürüComboBox() {
		return şubeTürüComboBox;

	}

	public JComboBox getIlçeComboBox() {
		return ilçeComboBox;
	};

	public static int getSelectedPanel() {
		return selectedPanel;
	}

	public void setSelectedPanel(int selectedPanel) {
		this.selectedPanel = selectedPanel;
	}

}
