package personelObjects;

import java.io.Serializable;

public class GenelMüdür extends Personel implements Serializable{

	private static final long serialVersionUID = 1L;
	private String genelMüdürüKod;
	private String password;
	
	
	public GenelMüdür(String name, String surName, String departman, String storeName, String telNO, String tcNO) {
		super(name, surName, departman, storeName, telNO, tcNO);
		this.genelMüdürüKod = createPersonelKod();
		this.password = createPassword();

	}

	public String createPassword() {

		String name = getName();
		String password = name + "16128211822%M";
		return password;
	}

	@Override
	String createPersonelKod() {

		String name = getName();
		String genelMüdürüKod = name + "GMİstanbul"; // Genel Müdür İstanbul
		return genelMüdürüKod;
	}

	public String getGenelMüdürüKod() {
		return genelMüdürüKod;
	}

	public void setGenelMüdürüKod(String genelMüdürüKod) {
		this.genelMüdürüKod = genelMüdürüKod;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
