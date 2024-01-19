package mağazaMüdürü;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import FileControls.AppendableObjectOutputStream2;
import GUISettings.TextSettings;
import stokOperations.StokObject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MMStokPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField stokKodutextField;
	private JTable table;
	private JLabel stokKoduJLabel1;
	private JTextField stokKodutextField2;
	private JTextField stokAdıTextField;
	private JTextField stokAdı2TextField;
	private JTextField ekMiktarTextField;
	private JTextField stokKodutextField3;
	private JTextField ekMiktarTextField2;
	private JTextField stokKodutextField4;
	private JTextField çıkarMiktarTextField;
	private JButton sorgulaButton;
	private JLabel yeniÜrünStoğuEkleJLabel;
	private JLabel yüStokKoduJLabel;
	private JLabel yüStokAdıJLabel;
	private JLabel yüStokAdı2JLabel;
	private JLabel yüEklenecekStokJLabel;
	private JButton ekleButton1;
	private JLabel ürünStoğuEkleJlabel;
	private JLabel üsStokKoduJLabel;
	private JLabel üsEklenecekStokJLabel;
	private JButton ekleButton2;
	private JLabel stokÇıkarJLabel;
	private JLabel sçStokKoduJLabel;
	private JLabel sçEklenecekStokJLabel;
	private JButton ÇıkarButton1;
	private JSeparator separator2;
	private JSeparator separator1;
	private JScrollPane scrollPane;
	private static String storeName;
	private String fileName;
	private FileOutputStream f;
	private AppendableObjectOutputStream2 out;
	private File file;
	boolean append;
	private ObjectInputStream in;
	private FileInputStream fin;

	public MMStokPanel() {

		setPanel();

		fileName = storeName + "StockInfo.txt";
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

		try {
			fin = new FileInputStream(fileName);
			in = new ObjectInputStream(fin);
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		// Yeni ürün stoğu ekle
		ekleButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String stokKodu = stokKodutextField2.getText().trim();
				String stokAdı1 = stokAdıTextField.getText().trim();
				String stokAdı2 = stokAdı2TextField.getText().trim();
				String eklenecekMiktar = ekMiktarTextField.getText().trim();
				System.out.println(eklenecekMiktar);
				StokObject yeniStok = new StokObject(stokKodu, stokAdı1, stokAdı2, eklenecekMiktar);
				System.out.println(yeniStok.getStokAmmount());
				try {
					out.writeObject(yeniStok);
					out.flush();
					System.out.println("Stok eklendi");
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
		});

		// Ürün stoğu ekle
		ekleButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StokObject object;
				try {
					while ((object = (StokObject) in.readObject()) != null) {
						System.out.println("girdi");
						if (((StokObject) object).getStokKod().equals(stokKodutextField3.getText().trim())) {
							System.out.println("girdi2");
							int plusAmmount = Integer.valueOf(ekMiktarTextField2.getText().trim());
							int finalAmmount = Integer.valueOf(((StokObject) object).getStokAmmount()) + plusAmmount;
							System.out.println(finalAmmount);
							((StokObject) object).setStokAmmount(String.valueOf(finalAmmount));
						}

					}
				} catch (EOFException e2) {

					System.out.println("Tüm kayıtlar Listelendi...");
				} catch (ClassNotFoundException | IOException e1) {

					e1.printStackTrace();
				}

			}
		});
		//Ürün stoğu çıkar
		ÇıkarButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StokObject object;
				try {
					while ((object = (StokObject) in.readObject()) != null) {
						if (((StokObject) object).getStokKod().equals(stokKodutextField4.getText().trim())) {
							int minusAmmount = Integer.valueOf(çıkarMiktarTextField.getText().trim());
							int finalAmmount = Integer.valueOf(((StokObject) object).getStokAmmount()) - minusAmmount;
							((StokObject) object).setStokAmmount(String.valueOf(finalAmmount));
						}

					}
				} catch (EOFException e2) {

					System.out.println("Tüm kayıtlar Listelendi...");
				} catch (ClassNotFoundException | IOException e1) {

					e1.printStackTrace();
				}

			}
		});
    //Girilen stok kodunun bilgilerini yazdırma
		sorgulaButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				StokObject object;
				try {
					while ((object = (StokObject) in.readObject()) != null) {
						if (((StokObject) object).getStokKod().equals(stokKodutextField.getText().trim())) {
							String stokKodu = ((StokObject) object).getStokKod();
							String stokName1 = ((StokObject) object).getStokName1();
							String stokName2 = ((StokObject) object).getStokName2();
							String ammount = ((StokObject) object).getStokAmmount();
							System.out.println(ammount);
							String data[] = { stokKodu, stokName1, stokName2, ammount };
							model.addRow(data);
							revalidate();
							repaint();
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
		setBounds(0, 0, 840, 698);
		setLayout(null);

		stokKoduJLabel1 = new JLabel("Stok kodu:");
		stokKoduJLabel1.setFont(new Font("Arial", Font.PLAIN, 18));
		stokKoduJLabel1.setBounds(29, 28, 102, 14);
		add(stokKoduJLabel1);

		stokKodutextField = new JTextField();
		stokKodutextField.setColumns(10);
		stokKodutextField.setBounds(141, 28, 134, 20);
		add(stokKodutextField);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 66, 683, 101);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Stok Kodu", "Stok Adı 1", "Stok Adı 2", "Stok Miktarı" }));
		scrollPane.setViewportView(table);

		sorgulaButton = new JButton("Sorgula");

		sorgulaButton.setFont(new Font("Arial", Font.BOLD, 12));
		sorgulaButton.setBackground(Color.WHITE);
		sorgulaButton.setBounds(300, 20, 85, 34);
		add(sorgulaButton);

		yeniÜrünStoğuEkleJLabel = new JLabel("Yeni Ürün Stoğu Ekle");
		yeniÜrünStoğuEkleJLabel.setFont(new Font("Arial", Font.BOLD, 20));
		yeniÜrünStoğuEkleJLabel.setBounds(50, 254, 234, 34);
		add(yeniÜrünStoğuEkleJLabel);

		yüStokKoduJLabel = new JLabel("Stok Kodu");
		yüStokKoduJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		yüStokKoduJLabel.setBounds(90, 318, 74, 25);
		add(yüStokKoduJLabel);

		yüStokAdıJLabel = new JLabel("Stok Adı");
		yüStokAdıJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		yüStokAdıJLabel.setBounds(90, 352, 70, 25);
		add(yüStokAdıJLabel);

		yüStokAdı2JLabel = new JLabel("Stok adı 2");
		yüStokAdı2JLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		yüStokAdı2JLabel.setBounds(90, 386, 85, 25);
		add(yüStokAdı2JLabel);

		yüEklenecekStokJLabel = new JLabel("Eklenecek Stok Miktarı");
		yüEklenecekStokJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		yüEklenecekStokJLabel.setBounds(90, 420, 159, 25);
		add(yüEklenecekStokJLabel);

		stokKodutextField2 = new JTextField();
		stokKodutextField2.setColumns(10);
		stokKodutextField2.setBounds(250, 318, 116, 21);
		add(stokKodutextField2);

		stokAdıTextField = new JTextField();
		stokAdıTextField.setColumns(10);
		stokAdıTextField.setBounds(250, 354, 116, 21);
		add(stokAdıTextField);

		stokAdı2TextField = new JTextField();
		stokAdı2TextField.setColumns(10);
		stokAdı2TextField.setBounds(250, 388, 116, 21);
		add(stokAdı2TextField);

		ekMiktarTextField = new JTextField();
		TextSettings.setOnlyNumber(ekMiktarTextField);
		ekMiktarTextField.setColumns(10);
		ekMiktarTextField.setBounds(250, 422, 116, 21);
		add(ekMiktarTextField);

		ekleButton1 = new JButton("Ekle");

		ekleButton1.setFont(new Font("Arial", Font.BOLD, 13));
		ekleButton1.setBackground(Color.WHITE);
		ekleButton1.setBounds(270, 453, 74, 25);
		add(ekleButton1);

		ürünStoğuEkleJlabel = new JLabel("Ürün Stoğu Ekle");
		ürünStoğuEkleJlabel.setFont(new Font("Arial", Font.BOLD, 20));
		ürünStoğuEkleJlabel.setBounds(50, 490, 234, 34);
		add(ürünStoğuEkleJlabel);

		üsStokKoduJLabel = new JLabel("Stok Kodu");
		üsStokKoduJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		üsStokKoduJLabel.setBounds(90, 535, 74, 25);
		add(üsStokKoduJLabel);

		üsEklenecekStokJLabel = new JLabel("Eklenecek Stok Miktarı");
		üsEklenecekStokJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		üsEklenecekStokJLabel.setBounds(90, 585, 159, 25);
		add(üsEklenecekStokJLabel);

		stokKodutextField3 = new JTextField();
		stokKodutextField3.setColumns(10);
		stokKodutextField3.setBounds(250, 535, 116, 21);
		add(stokKodutextField3);

		ekMiktarTextField2 = new JTextField();
		TextSettings.setOnlyNumber(ekMiktarTextField2);
		ekMiktarTextField2.setColumns(10);
		ekMiktarTextField2.setBounds(250, 585, 116, 21);
		add(ekMiktarTextField2);

		ekleButton2 = new JButton("Ekle");

		ekleButton2.setFont(new Font("Arial", Font.BOLD, 13));
		ekleButton2.setBackground(Color.WHITE);
		ekleButton2.setBounds(270, 629, 74, 25);
		add(ekleButton2);

		stokÇıkarJLabel = new JLabel("Stok Çıkar");
		stokÇıkarJLabel.setFont(new Font("Arial", Font.BOLD, 20));
		stokÇıkarJLabel.setBounds(495, 352, 122, 34);
		add(stokÇıkarJLabel);

		sçStokKoduJLabel = new JLabel("Stok Kodu");
		sçStokKoduJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		sçStokKoduJLabel.setBounds(518, 411, 74, 25);
		add(sçStokKoduJLabel);

		sçEklenecekStokJLabel = new JLabel("Çıkarılacak Stok Miktarı");
		sçEklenecekStokJLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		sçEklenecekStokJLabel.setBounds(518, 451, 159, 25);
		add(sçEklenecekStokJLabel);

		stokKodutextField4 = new JTextField();
		stokKodutextField4.setColumns(10);
		stokKodutextField4.setBounds(700, 414, 116, 21);
		add(stokKodutextField4);

		çıkarMiktarTextField = new JTextField();
		TextSettings.setOnlyNumber(çıkarMiktarTextField);
		çıkarMiktarTextField.setColumns(10);
		çıkarMiktarTextField.setBounds(700, 454, 116, 21);
		add(çıkarMiktarTextField);

		ÇıkarButton1 = new JButton("Çıkar");

		ÇıkarButton1.setFont(new Font("Arial", Font.BOLD, 13));
		ÇıkarButton1.setBackground(Color.WHITE);
		ÇıkarButton1.setBounds(722, 499, 74, 25);
		add(ÇıkarButton1);

		separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(442, 265, 27, 357);
		add(separator2);

		separator1 = new JSeparator();
		separator1.setBounds(29, 211, 767, 2);
		add(separator1);

	}

	public static String getStoreName() {
		return storeName;
	}

	public static void setStoreName(String newStoreName) {
		storeName = newStoreName;
	}
}
