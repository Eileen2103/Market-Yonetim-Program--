package stokOperations;

import java.io.Serializable;

public class StokObject implements Serializable {

	private static final long serialVersionUID = 1L;
	public String stokKod;
	public String stokName1;
	public String stokName2;
	public String stokAmmount;

	public StokObject(String stokKod, String stokName1, String stokName2, String stokAmmount) {
		this.stokKod = stokKod;
		this.stokName1 = stokName1;
		this.stokName2 = stokName2;
		this.stokAmmount = stokAmmount;

	}

	public String getStokKod() {
		return stokKod;
	}

	public void setStokKod(String stokKod) {
		this.stokKod = stokKod;
	}

	public String getStokName1() {
		return stokName1;
	}

	public void setStokName1(String stokName1) {
		this.stokName1 = stokName1;
	}

	public String getStokName2() {
		return stokName2;
	}

	public void setStokName2(String stokName2) {
		this.stokName2 = stokName2;
	}

	public String getStokAmmount() {
		return stokAmmount;
	}

	public void setStokAmmount(String stokAmmount) {
		this.stokAmmount = stokAmmount;
	}
}
