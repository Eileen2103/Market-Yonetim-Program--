package şubeObjects;

import java.io.Serializable;

public abstract class Şube implements Serializable{

	private static final long serialVersionUID = 1L;
	protected String postaKodu;
	protected String açıkAdres;
	protected String ilçe;
	protected String storeName;

	public Şube(String postaKodu, String açıkAdres, String ilçe) {
		this.postaKodu = postaKodu;
		this.açıkAdres = açıkAdres;
		this.ilçe = ilçe;

	}

	abstract String createStoreName();

	public String getPostaKodu() {
		return postaKodu;
	}

	public void setPostaKodu(String postaKodu) {
		this.postaKodu = postaKodu;
	}

	public String getAçıkAdres() {
		return açıkAdres;
	}

	public void setAçıkAdres(String açıkAdres) {
		this.açıkAdres = açıkAdres;
	}

	public String getİlçe() {
		return ilçe;
	}

	public void setİlçe(String ilçe) {
		this.ilçe = ilçe;
	}

}
