package personelObjects;

import java.io.Serializable;

public abstract class Personel implements Serializable {

	private static final long serialVersionUID = 1L;
	protected String name;
	protected String surname;
	protected String departman;
	protected String telNo;
	protected String tcNO;
	protected String storeName;

	public Personel(String name, String surname, String departman, String storeName, String telNO, String tcNO) {
		this.name = name;
		this.surname = surname;
		this.departman = departman;
		this.telNo = telNO;
		this.tcNO = tcNO;
		this.storeName = storeName;
	}

	abstract String createPersonelKod();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDepartman() {
		return departman;
	}

	public void setDepartman(String departman) {
		this.departman = departman;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getTcNO() {
		return tcNO;
	}

	public void setTcNO(String tcNO) {
		this.tcNO = tcNO;
	}
}
