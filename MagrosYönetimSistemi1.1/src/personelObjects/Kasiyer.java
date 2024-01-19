package personelObjects;

import java.io.Serializable;

public class Kasiyer extends Personel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String kasiyerKod;
	private static int mevcutKasiyerSayısı = 0;

	public Kasiyer(String name, String surName, String departman, String storeName, String telNO, String tcNO) {
		super(name, surName, departman, storeName, telNO, tcNO);
		this.kasiyerKod = createPersonelKod();
		mevcutKasiyerSayısı = mevcutKasiyerSayısı + 1;

	}

	@Override
	String createPersonelKod() {

		int number = mevcutKasiyerSayısı;
		String storeName = getStoreName();
		String name = getName();
		kasiyerKod = storeName + name + number;

		return kasiyerKod;
	}

	public String getKasiyerKod() {
		return kasiyerKod;
	}

	public void setKasiyerKod(String kasiyerKod) {
		this.kasiyerKod = kasiyerKod;
	}
}
