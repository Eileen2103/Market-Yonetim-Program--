package personelObjects;

import java.io.Serializable;

public class MarketGörevlisi extends Personel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String marketGörevlisiKod;
	private String password;
	private static int mevcutmarketGörevlisiSayısı = 0;

	public MarketGörevlisi(String name, String surName, String departman, String storeName, String telNO, String tcNO) {
		super(name, surName, departman, storeName, telNO, tcNO);
		this.marketGörevlisiKod = createPersonelKod();
		this.password = createPassword();
		mevcutmarketGörevlisiSayısı = mevcutmarketGörevlisiSayısı + 1;
	}

	@Override
	String createPersonelKod() {
		int number = mevcutmarketGörevlisiSayısı;
		String storeName = getStoreName();
		String name = getName();

		marketGörevlisiKod = storeName + name + number;
		return marketGörevlisiKod;
	}

	public String createPassword() {
		String name = getName();
		int number = mevcutmarketGörevlisiSayısı;
		String password = name + "MG" + number + number;
		return password;
	}

	public String getMarketGörevlisiKod() {
		return marketGörevlisiKod;
	}

	public void setMarketGörevlisiKod(String marketGörevlisiKod) {
		this.marketGörevlisiKod = marketGörevlisiKod;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
