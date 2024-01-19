package şubeObjects;

import java.io.Serializable;

public class M3Şube extends Şube implements Serializable {

	private static final long serialVersionUID = 1L;
	private String storeTürü;
	private static int mevcutŞubeSayısı = 0;

	public M3Şube(String postaKodu, String açıkAdres, String ilçe) {
		super(postaKodu, açıkAdres, ilçe);
		storeName = createStoreName();
		storeTürü = "3M Migros";
		mevcutŞubeSayısı = mevcutŞubeSayısı + 1;

	}

	@Override
	String createStoreName() {
		String name;
		String townName = getİlçe();
		int number = mevcutŞubeSayısı;

		name = "M3" + townName + number;

		return name;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getMevcutŞubeSayısı() {
		return mevcutŞubeSayısı;
	}

	public void setMevcutŞubeSayısı(int mevcutŞubeSayısı) {
		this.mevcutŞubeSayısı = mevcutŞubeSayısı;
	}
	public String getStoreTürü() {
		return storeTürü;
	}

	public void setStoreTürü(String storeTürü) {
		this.storeTürü = storeTürü;
	}
}
