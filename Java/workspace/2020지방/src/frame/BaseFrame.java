package frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultTreeCellEditor.DefaultTextField;

public class BaseFrame extends JFrame {
	static int userNo;
	static String userName;

	static DefaultTableCellRenderer crend = new DefaultTableCellRenderer();
	
	static {
		userName = "�ڰ���";
		userNo = 2;
		crend.setHorizontalAlignment(0);
	}
	
	public BaseFrame(int w, int h, String t, int n) {
		setTitle(t);
		setSize(w,h);
		setDefaultCloseOperation(n);
		setLocationRelativeTo(null);
	}

	public static void eMsg(String t) {
		JOptionPane.showMessageDialog(null, t, "�޽���", JOptionPane.ERROR_MESSAGE);
	}

	public static void iMsg(String t) {
		JOptionPane.showMessageDialog(null, t, "�޽���", JOptionPane.INFORMATION_MESSAGE);
	}

	public static JLabel setlabel(JLabel lb, Font font) {
		lb.setFont(font);
		return lb;
	}//���̺��� ��Ʈ�� ���ؼ� ��ȯ

	public static <T extends JComponent> T setComp(T comp, int x, int y, int w, int h) {
		comp.setBounds(x, y, w, h);
		return comp;
	}//������Ʈ�� ��ġ�� ���ؼ� ��ȯ

	public static <T extends JComponent> T setComp(T comp, int w, int h) {
		comp.setPreferredSize(new Dimension(w, h));
		return comp;
	}// ������Ʈ�� ũ�⸸ ���ؼ� ��ȯ

	public static JButton setBtn(String t, ActionListener e) {
		var btn = new JButton(t);
		btn.addActionListener(e);
		return btn;
	}//��ư�� �ؽ�Ʈ�� ������ �߰�

	public static JButton setBtnM(String t, ActionListener e,Color c) {
		var btn = new JButton(t);
		btn.addActionListener(e);
		btn.setForeground(c);
		btn.setMargin(new Insets(0, 0, 0, 0));
		return btn;
	}

	public static ImageIcon getImage(String path, int w, int h) {
		return new ImageIcon(Toolkit.getDefaultToolkit().getImage(path).getScaledInstance(w, h, Image.SCALE_SMOOTH));
	}

	public void openFrame(JFrame frame) {
		dispose();
		frame.setVisible(true);
	}
	
	public Integer toInt(String t) {
		return Integer.parseInt(t);
	}
}
