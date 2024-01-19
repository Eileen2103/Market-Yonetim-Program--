package genelMüdür;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JComboBox;
import personelObjects.Kasiyer;
import personelObjects.MarketGörevlisi;
import personelObjects.MağazaMüdürü;
import personelObjects.TemizlikGörevlisi;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import FileControls.AddUserVerificationData;
import FileControls.AppendableObjectOutputStream2;
import GUISettings.TextSettings;

public class PersonelOperationsPanel extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;
	private JTable personellerTable;
	private JTextField adTextField;
	private JTextField soyadTextField;
	private JTextField tcTextField;
	private JTextField telNoTextField;
	private JLabel şubeKoduLabel;
	private JButton listeleButton;
	private JLabel personelEkleLabel;
	private JLabel adLabel;
	private JLabel soyadLabel;
	private JLabel tcLabel;
	private JLabel departmanLabel;
	private JLabel telNoLabel;
	private JComboBox departmanComboBox;
	private JButton personelEkleButton;
	private JLabel mağazaLabel;
	private String storeName;
	private String personelKod;
	private JScrollPane scrollPane;

	private FileOutputStream f;
	private AppendableObjectOutputStream2 out;
	private ObjectInputStream in;
	private FileInputStream fin;
	private File file;
	boolean append;
	private JTextField mağazaKodutextField;
	private JTextField mağazaKodutextField2;

	public PersonelOperationsPanel() {
		setPanel();
		

		// Adding new personel to the system
		personelEkleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				storeName = mağazaKodutextField.getText().trim();
				String fileName = storeName + "Personel.txt";
				String departman = (String) departmanComboBox.getSelectedItem();

				file = new File(fileName);
				append = file.exists();
				try {
					f = new FileOutputStream(file, append);
					out = new AppendableObjectOutputStream2(f, append);
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e2) {

					e2.printStackTrace();
				}

				switch (departman) {
				case "Kasiyer":
					try {
						Kasiyer yeniKasiyer = new Kasiyer(adTextField.getText(), soyadTextField.getText(),
								(String) departmanComboBox.getSelectedItem(), mağazaKodutextField.getText().trim(),
								String.valueOf(telNoTextField.getText()), String.valueOf(tcTextField.getText()));
						personelKod = yeniKasiyer.getKasiyerKod();
						out.writeObject(yeniKasiyer);
						out.flush();
						System.out.println("Succesfull");

					} catch (IOException e1) {

						e1.printStackTrace();
					}

					break;
				case "Market Görevlisi":
					try {
						MarketGörevlisi yeniMarketGörevlisi = new MarketGörevlisi(adTextField.getText(),
								soyadTextField.getText(), (String) departmanComboBox.getSelectedItem(),
								mağazaKodutextField.getText().trim(), String.valueOf(telNoTextField.getText()),
								String.valueOf(tcTextField.getText()));
						personelKod = yeniMarketGörevlisi.getMarketGörevlisiKod();
						out.writeObject(yeniMarketGörevlisi);
						out.flush();
						AddUserVerificationData.addUser(yeniMarketGörevlisi); // Adding new user verification data to
																				// the file
						System.out.println("kullanıcı adı" + yeniMarketGörevlisi.getMarketGörevlisiKod());
						System.out.println("şifre" + yeniMarketGörevlisi.getPassword());
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					break;
				case "Temizlik Görevlisi":
					try {
						TemizlikGörevlisi yeniTemizlikGörevlisi = new TemizlikGörevlisi(adTextField.getText(),
								soyadTextField.getText(), (String) departmanComboBox.getSelectedItem(),
								mağazaKodutextField.getText().trim(), String.valueOf(telNoTextField.getText()),
								String.valueOf(tcTextField.getText()));
						personelKod = yeniTemizlikGörevlisi.getTemizlikGörevlisiKod();
						out.writeObject(yeniTemizlikGörevlisi);
						out.flush();
						System.out.println("Succesfull");
					} catch (IOException e1) {

						e1.printStackTrace();
					}
					break;
				case "Mağaza Müdürü":
					try {
						MağazaMüdürü yeniMağazaMüdürü = new MağazaMüdürü(adTextField.getText(),
								soyadTextField.getText(), (String) departmanComboBox.getSelectedItem(),
								 mağazaKodutextField.getText().trim(), String.valueOf(telNoTextField.getText()),
								String.valueOf(tcTextField.getText()));
						personelKod = yeniMağazaMüdürü.getMağazaMüdürüKod();
						out.writeObject(yeniMağazaMüdürü);
						out.flush();
						AddUserVerificationData.addUser(yeniMağazaMüdürü); // Adding new user verification data to the
																			// file
						System.out.println("Succesfull");
						System.out.println("kullanıcı adı" + yeniMağazaMüdürü.getMağazaMüdürüKod());
						System.out.println("şifre" + yeniMağazaMüdürü.getPassword());

					} catch (IOException e1) {

						e1.printStackTrace();
					}

					break;
				default:
					System.out.println("An error occurred.");
					break;
				}

			}
		});

		// write all the employees to the personellerTable
		listeleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) personellerTable.getModel();
				model.setRowCount(0);
				String tempStoreKod = mağazaKodutextField2.getText().trim();
				String fileName = tempStoreKod + "Personel.txt";
				fileName = fileName.trim();
				try {
					fin = new FileInputStream(fileName);
					in = new ObjectInputStream(fin);
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				try {
					Object temp;
					while ((temp = in.readObject()) != null) {
						if (temp instanceof Kasiyer) {

							String name = ((Kasiyer) temp).getName();
							String surname = ((Kasiyer) temp).getSurname();
							String departman = ((Kasiyer) temp).getDepartman();
							String storeName = ((Kasiyer) temp).getStoreName();
							String telNo = ((Kasiyer) temp).getTelNo();
							String tcNo = ((Kasiyer) temp).getTcNO();

							String[] data = { name, surname, departman, storeName, telNo, tcNo };
							model.addRow(data);

						} else if (temp instanceof MarketGörevlisi) {
							String name = ((MarketGörevlisi) temp).getName();
							String surname = ((MarketGörevlisi) temp).getSurname();
							String departman = ((MarketGörevlisi) temp).getDepartman();
							String storeName = ((MarketGörevlisi) temp).getStoreName();
							String telNo = ((MarketGörevlisi) temp).getTelNo();
							String tcNo = ((MarketGörevlisi) temp).getTcNO();

							String[] data = { name, surname, departman, storeName, telNo, tcNo };
							model.addRow(data);

						} else if (temp instanceof MağazaMüdürü) {

							String name = ((MağazaMüdürü) temp).getName();
							String surname = ((MağazaMüdürü) temp).getSurname();
							String departman = ((MağazaMüdürü) temp).getDepartman();
							String storeName = ((MağazaMüdürü) temp).getStoreName();
							String telNo = ((MağazaMüdürü) temp).getTelNo();
							String tcNo = ((MağazaMüdürü) temp).getTcNO();

							String[] data = { name, surname, departman, storeName, telNo, tcNo };
							model.addRow(data);
							;

						} else if (temp instanceof TemizlikGörevlisi) {
							String name = ((TemizlikGörevlisi) temp).getName();
							String surname = ((TemizlikGörevlisi) temp).getSurname();
							String departman = ((TemizlikGörevlisi) temp).getDepartman();
							String storeName = ((TemizlikGörevlisi) temp).getStoreName();
							String telNo = ((TemizlikGörevlisi) temp).getTelNo();
							String tcNo = ((TemizlikGörevlisi) temp).getTcNO();

							String[] data = { name, surname, departman, storeName, telNo, tcNo };
							model.addRow(data);
							;

						}

					}

				} catch (EOFException e2) {
					System.out.println("Tüm kayıtlar Listelendi...");
				} catch (ClassNotFoundException | IOException e1) {

					e1.printStackTrace();

				}

			}
		});

	}

	public void setPanel() {
		setBackground(new Color(240, 240, 240));
		setBounds(0, 0, 857, 698);
		setLayout(null);

		şubeKoduLabel = new JLabel("Şube Kodu");
		şubeKoduLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		şubeKoduLabel.setBounds(10, 13, 102, 27);
		add(şubeKoduLabel);

		listeleButton = new JButton("Personelleri Listele");
		listeleButton.setBackground(new Color(255, 255, 255));
		listeleButton.setFont(new Font("Arial", Font.BOLD, 14));
		listeleButton.setBounds(370, 10, 174, 36);
		add(listeleButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 685, 135);
		add(scrollPane);

		personellerTable = new JTable();
		personellerTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Name", "Surname", "Departman", "Mağaza Adı", "Tel no", "TC No" }));
		scrollPane.setViewportView(personellerTable);

		personelEkleLabel = new JLabel("Personel Ekle");
		personelEkleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		personelEkleLabel.setBounds(149, 223, 153, 25);
		add(personelEkleLabel);

		adLabel = new JLabel("Adı");
		adLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		adLabel.setBounds(170, 270, 45, 25);
		add(adLabel);

		soyadLabel = new JLabel("Soyadı");
		soyadLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		soyadLabel.setBounds(170, 310, 71, 21);
		add(soyadLabel);

		tcLabel = new JLabel("TC Kimlik No");
		tcLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		tcLabel.setBounds(170, 350, 102, 21);
		add(tcLabel);

		departmanLabel = new JLabel("Departman");
		departmanLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		departmanLabel.setBounds(170, 430, 85, 21);
		add(departmanLabel);

		telNoLabel = new JLabel("Telefon No");
		telNoLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		telNoLabel.setBounds(170, 390, 95, 21);
		add(telNoLabel);

		adTextField = new JTextField();
		TextSettings.setOnlyLetter(adTextField);
		adTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		adTextField.setBounds(300, 270, 134, 21);
		add(adTextField);
		adTextField.setColumns(10);

		soyadTextField = new JTextField();
		TextSettings.setOnlyLetter(soyadTextField);
		soyadTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		soyadTextField.setColumns(10);
		soyadTextField.setBounds(300, 310, 134, 21);
		add(soyadTextField);

		tcTextField = new JTextField();
		TextSettings.setOnlyNumber(tcTextField);
		tcTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		tcTextField.setColumns(10);
		tcTextField.setBounds(300, 350, 134, 21);
		add(tcTextField);

		telNoTextField = new JTextField();
		TextSettings.setOnlyNumber(telNoTextField);
		telNoTextField.setFont(new Font("Arial", Font.PLAIN, 12));
		telNoTextField.setColumns(10);
		telNoTextField.setBounds(300, 390, 134, 21);
		add(telNoTextField);

		departmanComboBox = new JComboBox();
		departmanComboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Kasiyer", "Market Görevlisi", "Mağaza Müdürü", "Temizlik Görevlisi" }));
		departmanComboBox.setBounds(300, 430, 166, 25);
		add(departmanComboBox);

		personelEkleButton = new JButton("Personel Ekle");
		personelEkleButton.setBackground(new Color(255, 255, 255));

		personelEkleButton.setFont(new Font("Arial", Font.BOLD, 14));
		personelEkleButton.setBounds(241, 534, 153, 27);
		add(personelEkleButton);

		mağazaLabel = new JLabel("Mağaza ");
		mağazaLabel.setFont(new Font("Arial", Font.PLAIN, 17));
		mağazaLabel.setBounds(170, 470, 85, 21);
		add(mağazaLabel);

		mağazaKodutextField = new JTextField();
		mağazaKodutextField.setFont(new Font("Arial", Font.PLAIN, 12));
		mağazaKodutextField.setColumns(10);
		mağazaKodutextField.setBounds(300, 470, 166, 27);
		add(mağazaKodutextField);

		mağazaKodutextField2 = new JTextField();
		mağazaKodutextField2.setFont(new Font("Arial", Font.PLAIN, 12));
		mağazaKodutextField2.setColumns(10);
		mağazaKodutextField2.setBounds(123, 13, 214, 27);
		add(mağazaKodutextField2);
	}
}
