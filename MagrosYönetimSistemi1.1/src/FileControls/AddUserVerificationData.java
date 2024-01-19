package FileControls;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddUserVerificationData {

	private static FileOutputStream fout;
	private static AppendableObjectOutputStream2 out;
	private static File file;
	static boolean append;

	public static void addUser(Object obj) {
		file = new File("LoginVerify.txt"); // txt file for all the stores in the directory
		append = file.exists();

		try {
			fout = new FileOutputStream(file, append);
			out = new AppendableObjectOutputStream2(fout, append);
		} catch (FileNotFoundException e) {
			System.out.println("Dosya bulunamadı");
			e.printStackTrace();
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		try {
			out.writeObject(obj);
			out.flush();
		} catch (IOException e) {
			System.out.println("Obje eklenmedi.");
			e.printStackTrace();
		}

	}

}
