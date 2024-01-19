package GUISettings;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class TextSettings {

	private static String originalText;
	private static Color originalColor;

	// A method to remove the previous text when the focus is gained
	public static void textFocusGained(JTextField textField, String original) {
		originalText = original;

		if (textField.getText().trim().equals(original)) {
			textField.setText("");

		}
	}

//A method to put back the original text when the focus is lost
	public static void textFocusLost(JTextField textField) {
		originalColor = textField.getForeground();
		if (textField.getText().trim().equals("")) {
			textField.setText(originalText);
			textField.setForeground(originalColor);
		}
	}

	// A method to check if the given textfield is filled.(for only adding a new
	// store)
	public static boolean ifTextFieldIsFilled(JComboBox şubeTürüComboBox, JComboBox ilçeComboBox, JTextField postaKodu,
			JTextField adres) {
		if ((postaKodu.getText().equals("") && postaKodu.isEnabled())
				|| (adres.getText().equals("") && adres.isEnabled()) || şubeTürüComboBox.getSelectedItem() == null
				|| ilçeComboBox.getSelectedItem() == null) {
			return false;

		}

		return true;
	}

   //A method to prevent user to type letters
	public static void setOnlyNumber(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
	}

	// A method to prevent user to type numbers
	public static void setOnlyLetter(JTextField textField) {
		textField.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isAlphabetic(c)) {
					e.consume();
				}
			}
		});
	}
	
}
