package GUISettings;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;

public class ColourSettings {

	private static Color originalBackgroundColor;
	private static Color originalForegroundColor;

	
	//Color settings for buttons
	public static void setBackgroundColor(JButton button, Color color) {
		originalBackgroundColor = button.getBackground();
		button.setBackground(color);

	}

	public static void setForegoundColor(JButton button, Color color) {
		originalForegroundColor = button.getForeground();
		button.setForeground(color);
	}

	public static void setOriginalBackgroundColor(JButton button) {
		button.setBackground(originalBackgroundColor);
	}

	public static void setOriginalForegroudnColor(JButton button) {
		button.setForeground(originalForegroundColor);
	}

//Color settings for labels
	public static void setBackgroundColor(JLabel label, Color color) {
		originalBackgroundColor = label.getBackground();
		label.setBackground(color);

	}

	public static void setForegoundColor(JLabel label, Color color) {
		originalForegroundColor = label.getForeground();
		label.setForeground(color);
	}

	public static void setOriginalBackgroundColor(JLabel label) {
		label.setBackground(originalBackgroundColor);
	}

	public static void setOriginalForegroudnColor(JLabel label) {
		label.setForeground(originalForegroundColor);
	}

}
