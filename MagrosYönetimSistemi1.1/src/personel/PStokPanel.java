package personel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import FileControls.AppendableObjectOutputStream2;
import GUISettings.TextSettings;
import stokOperations.StokObject;

import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class PStokPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField stokKodutextField;
	private JTable table;
	private JLabel stokKoduJLabel;
	private JButton sorgulaButton;
	private JSeparator separator1;
	private JLabel stokEkleLabel;
	private JLabel stokKoduLabel2;
	private JLabel ekleStokMiktarıLabel;
	private JTextField stokKodutextField2;
	private JTextField ekleStokMiktarıtextField;
	private JButton ekleButton;
	private JLabel stokÇıkarLabel;
	private JLabel stokKoduLabel3;
	private JLabel çıkarStokMiktarıLabel;
	private JTextField stokKodutextField3;
	private JTextField çıkarStokMiktarıtextField;
	private JButton çıkarButton;
	private JSeparator separator;
	private static String storeName;
	private String fileName;
	private FileOutputStream f;
	private AppendableObjectOutputStream2 out;
	private File file;
	boolean append;
	private ObjectInputStream in;
	private FileInputStream fin;
	private JScrollPane scrollPane;

	public PStokPanel() {

		setPanel();
		fileName = storeName + "StockInfo.txt";

		file = new File(fileName);
		append = file.exists();

		ekleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);
				try {
					f = new FileOutputStream(file, append);
					out = new AppendableObjectOutputStream2(f, append);
					System.out.println("burda1");
				} catch (FileNotFoundException e2) {

					e2.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				try {
					fin = new FileInputStream(fileName);
					in = new ObjectInputStream(fin);
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				StokObject object;
				try {
					while ((object = (StokObject) in.readObject()) != null) {
						System.out.println("burda2");
						if (((StokObject) object).getStokKod().equals(stokKodutextField2.getText().trim())) {
							int plusAmmount = Integer.valueOf(ekleStokMiktarıtextField.getText());
							int ammount = Integer.valueOf(((StokObject) object).getStokAmmount()) + plusAmmount;
							((StokObject) object).setStokAmmount(String.valueOf(ammount));

							JOptionPane.showMessageDialog(ekleButton, "Stok bilgisi güncellendi.Yeni stok miktarI: "
									+ Integer.valueOf(((StokObject) object).getStokAmmount()));
						}

					}
				} catch (FileNotFoundException e3) {
					System.out.println("nesne eklenemedi");
					e3.printStackTrace();
				} catch (EOFException e2) {
					System.out.println("Tüm kayıtlar Listelendi...");
				} catch (ClassNotFoundException | IOException e1) {

					e1.printStackTrace();
				}

			}
		});
		çıkarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.setRowCount(0);

				try {
					f = new FileOutputStream(file, append);
					out = new AppendableObjectOutputStream2(f, append);
				} catch (FileNotFoundException e2) {

					e2.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				try {
					fin = new FileInputStream(fileName);
					in = new ObjectInputStream(fin);
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}
				StokObject object;

				try {
					while ((object = (StokObject) in.readObject()) != null) {
						if (((StokObject) object).getStokKod().equals(stokKodutextField3.getText().trim())) {
							int minusAmmount = Integer.valueOf(çıkarStokMiktarıtextField.getText());
							int ammount = Integer.valueOf(((StokObject) object).getStokAmmount()) - minusAmmount;

							((StokObject) object).setStokAmmount(String.valueOf(ammount));
							JOptionPane.showMessageDialog(ekleButton, "Stok bilgisi güncellendi.Yeni stok miktarI: "
									+ Integer.valueOf(((StokObject) object).getStokAmmount()));
						}

					}
				} catch (EOFException e2) {
					System.out.println("Tüm kayıtlar Listelendi...");
				} catch (ClassNotFoundException | IOException e1) {

					e1.printStackTrace();
				}

			}
		});
		sorgulaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					fin = new FileInputStream(fileName);
					in = new ObjectInputStream(fin);
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (IOException e1) {

					e1.printStackTrace();
				}

				DefaultTableModel model = (DefaultTableModel) table.getModel();
				StokObject object;
				try {
					while ((object = (StokObject) in.readObject()) != null) {
						if (((StokObject) object).getStokKod().equals(stokKodutextField.getText().trim())) {
							String stokKod = ((StokObject) object).getStokKod();
							String stokName1 = ((StokObject) object).getStokName1();
							String stokName2 = ((StokObject) object).getStokName2();
							String ammount = String.valueOf(((StokObject) object).getStokAmmount());
							String data[] = { stokKod, stokName1, stokName2, ammount };
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
		setBounds(0, 0, 857, 698);
		setLayout(null);

		stokKoduJLabel = new JLabel("Stok kodu:");
		stokKoduJLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		stokKoduJLabel.setBounds(10, 18, 102, 14);
		add(stokKoduJLabel);

		stokKodutextField = new JTextField();
		stokKodutextField.setColumns(10);
		stokKodutextField.setBounds(107, 18, 134, 20);
		add(stokKodutextField);

		sorgulaButton = new JButton("Sorgula");

		sorgulaButton.setFont(new Font("Arial", Font.BOLD, 12));
		sorgulaButton.setBackground(Color.WHITE);
		sorgulaButton.setBounds(259, 10, 85, 34);
		add(sorgulaButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 56, 737, 101);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Stok Kodu", "Stok Adı 1", "Stok Adı 2", "Stok Miktarı" }));
		scrollPane.setViewportView(table);

		separator1 = new JSeparator();
		separator1.setBounds(29, 201, 793, 2);
		add(separator1);

		stokEkleLabel = new JLabel("Stok Ekle");
		stokEkleLabel.setFont(new Font("Arial", Font.BOLD, 21));
		stokEkleLabel.setBounds(30, 242, 171, 34);
		add(stokEkleLabel);

		stokKoduLabel2 = new JLabel("Stok kodu");
		stokKoduLabel2.setFont(new Font("Arial", Font.PLAIN, 19));
		stokKoduLabel2.setBounds(20, 299, 119, 20);
		add(stokKoduLabel2);

		ekleStokMiktarıLabel = new JLabel("Eklenecek Stok Miktarı");
		ekleStokMiktarıLabel.setFont(new Font("Arial", Font.PLAIN, 19));
		ekleStokMiktarıLabel.setBounds(20, 368, 218, 25);
		add(ekleStokMiktarıLabel);

		stokKodutextField2 = new JTextField();
		stokKodutextField2.setColumns(10);
		stokKodutextField2.setBounds(250, 299, 130, 23);
		add(stokKodutextField2);

		ekleStokMiktarıtextField = new JTextField();
		TextSettings.setOnlyNumber(ekleStokMiktarıtextField);
		ekleStokMiktarıtextField.setColumns(10);
		ekleStokMiktarıtextField.setBounds(250, 370, 130, 23);
		add(ekleStokMiktarıtextField);

		ekleButton = new JButton("Ekle");

		ekleButton.setFont(new Font("Arial", Font.BOLD, 14));
		ekleButton.setBounds(274, 421, 85, 28);
		add(ekleButton);

		stokÇıkarLabel = new JLabel("Stok Çıkar");
		stokÇıkarLabel.setFont(new Font("Arial", Font.BOLD, 21));
		stokÇıkarLabel.setBounds(453, 242, 171, 34);
		add(stokÇıkarLabel);

		stokKoduLabel3 = new JLabel("Stok kodu:");
		stokKoduLabel3.setFont(new Font("Arial", Font.PLAIN, 19));
		stokKoduLabel3.setBounds(453, 299, 119, 20);
		add(stokKoduLabel3);

		çıkarStokMiktarıLabel = new JLabel("Çıkarılacak Stok Miktarı");
		çıkarStokMiktarıLabel.setFont(new Font("Arial", Font.PLAIN, 19));
		çıkarStokMiktarıLabel.setBounds(453, 368, 218, 25);
		add(çıkarStokMiktarıLabel);

		stokKodutextField3 = new JTextField();
		stokKodutextField3.setColumns(10);
		stokKodutextField3.setBounds(671, 296, 130, 23);
		add(stokKodutextField3);

		çıkarStokMiktarıtextField = new JTextField();
		TextSettings.setOnlyNumber(çıkarStokMiktarıtextField);
		çıkarStokMiktarıtextField.setColumns(10);
		çıkarStokMiktarıtextField.setBounds(671, 368, 130, 23);
		add(çıkarStokMiktarıtextField);

		çıkarButton = new JButton("Çıkar");

		çıkarButton.setFont(new Font("Arial", Font.BOLD, 14));
		çıkarButton.setBounds(697, 421, 85, 28);
		add(çıkarButton);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(408, 254, 2, 336);
		add(separator);

	}

	public static String getStoreName() {
		return storeName;
	}

	public static void setStoreName(String newStoreName) {
		storeName = newStoreName;
	}
}
