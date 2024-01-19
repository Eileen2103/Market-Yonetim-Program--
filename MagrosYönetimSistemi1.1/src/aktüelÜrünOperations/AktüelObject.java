package aktüelÜrünOperations;

import java.io.Serializable;

public class AktüelObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String ammount;
	private String beginDate;
	private String endDate;

	public AktüelObject(String name, String ammount, String beginDate, String endDate) {
		this.ammount = ammount;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.name = name;
	}

	public String getName() {
		return name;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmmount() {
		return ammount;
	}

	public void setAmmount(String ammount) {
		this.ammount = ammount;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}
