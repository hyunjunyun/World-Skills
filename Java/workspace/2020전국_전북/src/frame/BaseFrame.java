package frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class BaseFrame extends JFrame {
	BaseFrame(int w, int h, String title) {
		setSize(w, h);
		setTitle(title);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public static void errorMessage(String text) {
		JOptionPane.showMessageDialog(null, text, "메시지", JOptionPane.ERROR_MESSAGE);
	}

	public static void informationMessage(String text) {
		JOptionPane.showMessageDialog(null, text, "메시지", JOptionPane.INFORMATION_MESSAGE);
	}

	public static JLabel createLabel(JLabel label, Font font) {
		label.setFont(font);
		return label;
	}

	public static <T extends JComponent> T createComponent(T comp, int x, int y, int width, int height) {
		comp.setBounds(x, y, width, height);
		return comp;
	}

	public static <T extends JComponent> T createComponent(T comp, int width, int height) {
		comp.setPreferredSize(new Dimension(width, height));
		return comp;
	}

	public static JButton createButton(String text, ActionListener e) {
		JButton button = new JButton(text);
		button.addActionListener(e);
		return button;
	}

	public void openDialog(JDialog dialog) {
		dialog.setVisible(true);
	}

	public static ImageIcon getImage(int width, int height, String path) {
		return new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(path).getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
}
