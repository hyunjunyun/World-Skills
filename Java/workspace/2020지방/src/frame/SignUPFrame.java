package frame;

import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SignUPFrame extends BaseFrame {

	JTextField nf = setComp(new JTextField(), 200, 25);
	JTextField idf = setComp(new JTextField(), 200, 25);
	JTextField pwf = setComp(new JTextField(), 200, 25);

	JComboBox<Integer> cby = new JComboBox<Integer>();
	JComboBox<Integer> cbm = new JComboBox<Integer>();
	JComboBox<Integer> cbd = new JComboBox<Integer>();

	public SignUPFrame() {
		super(300, 230, "ȸ������", 2);
		setLayout(new FlowLayout());
		add(setComp(new JLabel("�̸�", 4), 50, 35));
		add(nf);
		add(setComp(new JLabel("���̵�", 4), 50, 35));
		add(idf);
		add(setComp(new JLabel("��й�ȣ", 4), 50, 35));
		add(pwf);

		cby.addActionListener(this::changeDate);
		cbm.addActionListener(this::changeDate);

		Calendar cal = Calendar.getInstance();

		cby.addItem(null);
		for (int i = 1940; i <= cal.get(Calendar.YEAR); i++) {
			cby.addItem(i);
		}

		cbm.addItem(null);
		for (int i = 1; i <= 12; i++) {
			cbm.addItem(i);
		}

		add(new JLabel("�������"));
		add(cby);
		add(new JLabel("��"));
		add(cbm);
		add(new JLabel("��"));
		add(cbd);
		add(new JLabel("��"));

		add(setBtn("���� �Ϸ�", e -> clickSubmit()));
		add(setBtn("���", e -> openFrame(new LoginFrame())));
	}

	public void changeDate(ActionEvent e) {
		cbd.removeAllItems();
		if (cby.getSelectedItem() != null && cbm.getSelectedItem() != null) {
			Calendar cal = Calendar.getInstance();

			cal.set((int) cby.getSelectedItem(), (int) cbm.getSelectedItem() - 1, 1);

			for (int i = 1; i <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
				cbd.addItem(i);
			}
		}
	}

	public void clickSubmit() {
		String n = nf.getText();
		String id = idf.getText();
		String pw = pwf.getText();

		if (n.isEmpty() || id.isEmpty() || pw.isEmpty() || cbd.getSelectedItem() == null) {
			eMsg("������ �׸��� �ֽ��ϴ�.");
			return;
		}

		if (!Pattern.compile("(?=.*[A-z])(?=.*[^A-z\\d])(?=.*\\d).{6,}$").matcher(pw).find()) { // Ư������
			eMsg("��й�ȣ ������ ��ġ���� �ʽ��ϴ�.");
			return;
		}

		try (var pst = CM.con.prepareStatement("select * from patient where p_id = ?;")) {
			pst.setObject(1, id);
			var rs = pst.executeQuery();
			if (rs.next()) {
				eMsg("���̵� �ߺ��Ǿ����ϴ�.");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try (var pst = CM.con.prepareStatement("insert into patient values(0,?,?,?,?)")) {
			pst.setObject(1, n);
			pst.setObject(2, id);
			pst.setObject(3, pw);
			pst.setObject(4,
					String.format("%d-%02d-%02d", cby.getSelectedItem(), cbm.getSelectedItem(), cbd.getSelectedItem()));
			pst.execute();
			iMsg("������ �Ϸ�Ǿ����ϴ�.");
			openFrame(new LoginFrame());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
