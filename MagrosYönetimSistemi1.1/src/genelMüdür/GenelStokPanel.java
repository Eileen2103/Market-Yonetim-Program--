package genelMüdür;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import stokOperations.StokObject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GenelStokPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable stokJTable;
	private JScrollPane scrollPane;
	private JTextField mağazaKodutextField2;
	private JTextField stokKodutextField;
	private JTable table2;
	private JLabel mağazaKoduLabel;
	private JButton sorgulaButton1;
	private JLabel mağazaKoduLabel2;
	private JLabel stokKoduLabel;
	private JButton sorgulaButton2;
	boolean append;
	private ObjectInputStream in;
	private FileInputStream fin;
	private JTextField mağazaKodutextField1;
	private JScrollPane scrollPane2;

	public GenelStokPanel() {
		setPanel();
		

		// Listing all the stock datas of desired store.
		sorgulaButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) stokJTable.getModel();
				model.setRowCount(0);
				String storeName = mağazaKodutextField1.getText().trim();
				String fileName = storeName + "StockInfo.txt";
				fileName = fileName.trim();

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
						String stokKod = object.getStokKod();
						String stokName1 = object.getStokName1();
						String stokName2 = object.getStokName2();
						String stokAmmount = String.valueOf(object.getStokAmmount());
						String[] data = { stokKod, stokName1, stokName2, stokAmmount };
						model.addRow(data);
					}
				} catch (EOFException e2) {
					System.out.println("Tüm kayıtlar Listelendi...");
				} catch (ClassNotFoundException | IOException e1) {

					e1.printStackTrace();
				}

			}
		});
		// listing the info of the given stock code of a selected store
		sorgulaButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) table2.getModel();
				model.setRowCount(0);
				String storeName = mağazaKodutextField2.getText().trim();
				String fileName = storeName + "StockInfo.txt";
				fileName = fileName.trim();

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

						if (((StokObject) object).getStokKod().equals(stokKodutextField.getText().trim())) {
							String stokKod = object.getStokKod();
							String stokName1 = object.getStokName1();
							String stokName2 = object.getStokName2();
							String stokAmmount = String.valueOf(object.getStokAmmount());
							String[] data = { stokKod, stokName1, stokName2, stokAmmount };
							model.addRow(data);

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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 73, 753, 289);
		add(scrollPane);

		stokJTable = new JTable();
		stokJTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Stok Kodu", "Stok Adı 1", "Stok Adı 2", "Stok Miktarı" }));
		scrollPane.setViewportView(stokJTable);

		mağazaKoduLabel = new JLabel("Mağaza kodu:");
		mağazaKoduLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		mağazaKoduLabel.setBounds(44, 28, 113, 24);
		add(mağazaKoduLabel);

		sorgulaButton1 = new JButton("Stok Sorgula");

		sorgulaButton1.setFont(new Font("Arial", Font.BOLD, 12));
		sorgulaButton1.setBackground(Color.WHITE);
		sorgulaButton1.setBounds(374, 30, 168, 34);
		add(sorgulaButton1);

		mağazaKoduLabel2 = new JLabel("Mağaza kodu:");
		mağazaKoduLabel2.setFont(new Font("Arial", Font.PLAIN, 18));
		mağazaKoduLabel2.setBounds(44, 404, 134, 24);
		add(mağazaKoduLabel2);

		stokKoduLabel = new JLabel("Stok kodu:");
		stokKoduLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		stokKoduLabel.setBounds(44, 469, 105, 24);
		add(stokKoduLabel);

		mağazaKodutextField2 = new JTextField();
		mağazaKodutextField2.setColumns(10);
		mağazaKodutextField2.setBounds(188, 409, 178, 28);
		add(mağazaKodutextField2);

		stokKodutextField = new JTextField();
		stokKodutextField.setColumns(10);
		stokKodutextField.setBounds(188, 474, 178, 28);
		add(stokKodutextField);

		sorgulaButton2 = new JButton("Stok Sorgula");

		sorgulaButton2.setFont(new Font("Arial", Font.BOLD, 12));
		sorgulaButton2.setBackground(Color.WHITE);
		sorgulaButton2.setBounds(200, 537, 157, 34);
		add(sorgulaButton2);
		
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(40, 589, 743, 68);
		add(scrollPane2);

		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Stok Kodu", "Stok Adı 1", "Stok Adı 2", "Stok Miktarı"
			}
		));
		scrollPane2.setViewportView(table2);
		
		mağazaKodutextField1 = new JTextField();
		mağazaKodutextField1.setColumns(10);
		mağazaKodutextField1.setBounds(167, 28, 178, 28);
		add(mağazaKodutextField1);
	}
}
