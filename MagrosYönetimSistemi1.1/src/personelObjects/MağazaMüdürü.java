package personelObjects;

import java.io.Serializable;
import java.util.Random;

public class MağazaMüdürü extends Personel implements Serializable {

	private static final long serialVersionUID = 1L;
	private String mağazaMüdürüKod;
	private String password;

	public MağazaMüdürü(String name, String surName, String departman, String storeName, String telNO, String tcNO) {
		super(name, surName, departman, storeName, telNO, tcNO);
		this.mağazaMüdürüKod = createPersonelKod();
		this.password = createPassword();

	}

	@Override
	String createPersonelKod() {

		String storeName = getStoreName();
		String name = getName();
		mağazaMüdürüKod = storeName + name + "MM";

		return mağazaMüdürüKod;
	}

	public String createPassword() {
		Random rand = new Random();
		String name = getName();
		int rndNum = rand.nextInt(1000);
		String password = name + String.valueOf(rndNum);
		return password;
	}

	public String getMağazaMüdürüKod() {
		return mağazaMüdürüKod;
	}

	public void setMağazaMüdürüKod(String mağazaMüdürüKod) {
		this.mağazaMüdürüKod = mağazaMüdürüKod;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
