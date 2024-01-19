package personel;

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
import java.io.Serializable;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import FileControls.AppendableObjectOutputStream2;
import GUISettings.TextSettings;
import aktüelÜrünOperations.AktüelObject;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PAktüelÜrünlerPanel extends JPanel implements Serializable {

	private static final long serialVersionUID = 1L;
	private JTable aktüelÜrünlerTable;

	private JLabel aktüelÜrünlerLabel;
	private JTextField ürünAdıtextField;
	private JTextField ürünSayısıtextField;
	private JTextField başTarihitextField;
	private JTextField bitTarihitextField;
	private JScrollPane scrollPane;
	private JLabel ürünEkleLabel;
	private JLabel ürünAdıLabel;
	private JLabel ürünSayısıLabel;
	private JLabel başTarihiLabel;
	private JLabel bitTarihiLabel;
	private JButton ürünEkleButton;
	private JButton güncelleButton;
	private static String storeName;
	private String fileName;
	private FileOutputStream f;
	private AppendableObjectOutputStream2 out;
	private File file;
	boolean append;
	private ObjectInputStream in;
	private FileInputStream fin;

	public PAktüelÜrünlerPanel() {
		setPanel();

		System.out.println(storeName);
		fileName = storeName + "AktüelInfo.txt";

		file = new File(fileName);
		append = file.exists();

		ürünEkleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					f = new FileOutputStream(file, append);
					out = new AppendableObjectOutputStream2(f, append);
				} catch (FileNotFoundException e2) {

					e2.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				String name = ürünAdıtextField.getText().trim();
				String ammount = ürünSayısıtextField.getText().trim();
				String beginDate = başTarihitextField.getText().trim();
				String endDate = bitTarihitextField.getText().trim();
				AktüelObject yeniÜrün = new AktüelObject(name, ammount, beginDate, endDate);
				try {
					out.writeObject(yeniÜrün);
					out.flush();
					out.close();
					System.out.println("Eklendi");
				} catch (IOException e1) {

					e1.printStackTrace();
				}

			}
		});

		güncelleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) aktüelÜrünlerTable.getModel();
				model.setRowCount(0);
				try {
					fin = new FileInputStream(fileName);
					in = new ObjectInputStream(fin);

				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				AktüelObject object;
				try {
					while ((object = (AktüelObject) in.readObject()) != null) {
						String name = ((AktüelObject) object).getName();
						String ammount = ((AktüelObject) object).getAmmount();
						String başlangıçTarihi = ((AktüelObject) object).getBeginDate();
						String bitişTarihi = ((AktüelObject) object).getEndDate();
						String[] data = { name, ammount, başlangıçTarihi, bitişTarihi };
						model.addRow(data);
						revalidate();
						repaint();

					}
					in.close();
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
		System.out.println("frame worked");

		aktüelÜrünlerLabel = new JLabel("Aktüel Ürünler ");
		aktüelÜrünlerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		aktüelÜrünlerLabel.setBounds(22, 23, 164, 24);
		add(aktüelÜrünlerLabel);
		System.out.println("label worked");

		scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 57, 782, 306);
		add(scrollPane);

		aktüelÜrünlerTable = new JTable();
		aktüelÜrünlerTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Ürün Adı", "Ürün Sayısı", "Başlangıç Tarihi", "Bitiş Tarihi" }));
		scrollPane.setViewportView(aktüelÜrünlerTable);

		ürünEkleLabel = new JLabel("Ürün Ekle");
		ürünEkleLabel.setFont(new Font("Arial", Font.BOLD, 20));
		ürünEkleLabel.setBounds(45, 400, 164, 24);
		add(ürünEkleLabel);

		ürünAdıLabel = new JLabel("Ürün Adı");
		ürünAdıLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		ürünAdıLabel.setBounds(57, 450, 107, 26);
		add(ürünAdıLabel);

		ürünSayısıLabel = new JLabel("Ürün Sayısı");
		ürünSayısıLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		ürünSayısıLabel.setBounds(57, 500, 107, 26);
		add(ürünSayısıLabel);

		başTarihiLabel = new JLabel("Başlangıç Tarihi");
		başTarihiLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		başTarihiLabel.setBounds(57, 550, 122, 26);
		add(başTarihiLabel);

		bitTarihiLabel = new JLabel("Bitiş Tarihi");
		bitTarihiLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		bitTarihiLabel.setBounds(57, 600, 107, 26);
		add(bitTarihiLabel);

		ürünAdıtextField = new JTextField();
		ürünAdıtextField.setColumns(10);
		ürünAdıtextField.setBounds(189, 430, 253, 40);
		add(ürünAdıtextField);

		ürünSayısıtextField = new JTextField();
		TextSettings.setOnlyNumber(ürünSayısıtextField);
		ürünSayısıtextField.setColumns(10);
		ürünSayısıtextField.setBounds(189, 500, 154, 25);
		add(ürünSayısıtextField);

		başTarihitextField = new JTextField();
		başTarihitextField.setColumns(10);
		başTarihitextField.setBounds(189, 550, 154, 25);
		add(başTarihitextField);

		bitTarihitextField = new JTextField();
		bitTarihitextField.setColumns(10);
		bitTarihitextField.setBounds(189, 600, 154, 25);
		add(bitTarihitextField);

		ürünEkleButton = new JButton("Ürün Ekle");
		ürünEkleButton.setBackground(new Color(255, 255, 255));

		ürünEkleButton.setFont(new Font("Arial", Font.BOLD, 16));
		ürünEkleButton.setBounds(421, 600, 164, 26);
		add(ürünEkleButton);

		güncelleButton = new JButton("Tabloyu Güncelle");
		güncelleButton.setBackground(new Color(255, 255, 255));

		güncelleButton.setFont(new Font("Arial", Font.BOLD, 14));
		güncelleButton.setBounds(625, 373, 178, 40);
		add(güncelleButton);
		System.out.println("table worked");

	}

	public static String getStoreName() {
		return storeName;
	}

	public static void setStoreName(String newStoreName) {
		storeName = newStoreName;
	}
}
