package personelObjects;

import java.io.Serializable;

public class TemizlikGörevlisi extends Personel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String temizlikGörevlisiKod;

	private static int mevcutGörevliSayısı = 0;

	public TemizlikGörevlisi(String name, String surName, String departman, String storeName, String telNO,
			String tcNO) {
		super(name, surName, departman, storeName, telNO, tcNO);
		temizlikGörevlisiKod = createPersonelKod();
		mevcutGörevliSayısı = mevcutGörevliSayısı + 1;
	}

	@Override
	String createPersonelKod() {
		String name = getName();
		String storeName = getStoreName();
		int number = mevcutGörevliSayısı;

		temizlikGörevlisiKod = storeName + name + number;
		return temizlikGörevlisiKod;
	}

	public String getTemizlikGörevlisiKod() {
		return temizlikGörevlisiKod;
	}

	public void setTemizlikGörevlisiKod(String temizlikGörevlisiKod) {
		this.temizlikGörevlisiKod = temizlikGörevlisiKod;
	}
}
