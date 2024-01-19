package mağazaMüdürü;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import personelObjects.Kasiyer;
import personelObjects.MarketGörevlisi;
import personelObjects.TemizlikGörevlisi;

public class MMPersonelPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel personelListesiLabel;
	private JScrollPane scrollPane;
	private static String storeName;
	private String fileName;
	boolean append;
	private ObjectInputStream in;
	private FileInputStream fin;
	private File file;

	public MMPersonelPanel() {

		setPanel();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		fileName = storeName + "Personel.txt";
		file = new File(fileName);
		try {
			fin = new FileInputStream(fileName);
			in = new ObjectInputStream(fin);
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		Object temp;
		try {
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
		} catch (ClassNotFoundException | IOException e) {

			e.printStackTrace();
		}

	}

	public void setPanel() {
		setBackground(new Color(240, 240, 240));
		setBounds(0, 0, 857, 698);
		setLayout(null);

		personelListesiLabel = new JLabel("Personel Listesi");
		personelListesiLabel.setFont(new Font("Arial", Font.BOLD, 25));
		personelListesiLabel.setBounds(21, 23, 240, 33);
		add(personelListesiLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 66, 802, 472);
		add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Ad", "Soyad", "Departman", "Tel No", "TC No" }));
		scrollPane.setViewportView(table);

	}

	public static String getStoreName() {
		return storeName;
	}

	public static void setStoreName(String newStoreName) {
		storeName = newStoreName;
	}
}
