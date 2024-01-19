
/*import FileControls.AddUserVerificationData;
  import personelObjects.GenelMüdür;
 * */

import loginPages.GirisEkranı;

public class Main {

	public static void main(String[] args) {

		/*
		 * GenelMüdür müdür = new GenelMüdür("Aylin", "Türkyılmaz", "Genel Müdür",
		 * "İstanbul Şubeler", "000000000","11111111111");
		 * AddUserVerificationData.addUser(müdür);
		 * 
		 */
		GirisEkranı newGiris = new GirisEkranı();
		newGiris.getFrame().setVisible(true);

	}
}
