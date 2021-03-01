package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BillFrame extends BaseFrame {

	JTextField[] tf = new JTextField[7];
	boolean check = false;
	Date startDate;
	Date endDate;
	int result=0;
	
	public BillFrame() {
		super(260, 290, "���", 2);
		var cp = new JPanel();
		var sp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		String[] list = "ȯ�ڹ�ȣ,ȯ�ڸ�,ȣ��,�Կ���¥,�����¥,�Ļ� Ƚ��,�ѱݾ�".split(",");
		for (int i = 0; i < list.length; i++) {
			cp.add(setComp(new JLabel(list[i], 0), 100, 20));
			cp.add(setComp(tf[i] = new JTextField(), 130, 20));
		}

		tf[0].setText(userNo + "");
		tf[1].setText(userName);

		try (var pst = CM.con.prepareStatement(
				"select *,curdate() from hospitalization as h inner join sickroom as s on h.s_no = s.s_no where p_no = ? order by h_no desc;")) {
			pst.setObject(1, userNo);
			var rs = pst.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
				tf[2].setText(rs.getString(11));
				tf[3].setText(rs.getString(5));
				tf[4].setText(rs.getString(13));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		tf[5].addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				getAmount();
			}
		});
		for (int i = 0; i < list.length; i++) {
			if (i == 5) {
				continue;
			}
			tf[i].setEnabled(false);
		}

		sp.add(setBtn("���", e -> out()));
		sp.add(setBtn("�ݱ�", e -> openFrame(new MainFrame())));

		add(setlabel(new JLabel("��꼭", 0), new Font("����", 1, 30)), BorderLayout.NORTH);
		add(cp, BorderLayout.CENTER);
		add(sp, BorderLayout.SOUTH);
	}

	public void out() {
		String t = tf[5].getText();
		if (t.isEmpty()) {
			eMsg("�Ļ� Ƚ���� �Է����ּ���.");
			return;
		}if (check == false) {
			eMsg("���ڷθ� �Է����ּ���.");
			return;
		}if (toInt(t) > 21) {
			eMsg("21���� �ִ��Դϴ�.");
			return;
		}if (startDate.toString().equals(endDate.toString())) {
			eMsg("���� ����� �Ұ��մϴ�.");
			return;
		}
		
		try (var pst = CM.con.prepareStatement("update hospitalization set h_fday = curdate() where h_no = ?")){
			pst.setObject(1, result);
			pst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		iMsg("����� �Ϸ�Ǿ����ϴ�.");
		openFrame(new MainFrame());
	}


	public void getAmount() {
		if (tf[5].getText().isEmpty()) {
			return;
		}
		int amount = 0;
		long diffDay = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			startDate = sdf.parse(tf[3].getText());
			endDate = sdf.parse(tf[4].getText());
			diffDay = (startDate.getTime() - endDate.getTime()) / (24 * 60 * 60 * 1000);
			amount = (int) (diffDay * (350000 - (50000 * (toInt(tf[2].getText()) / 100)))
					+ (toInt(tf[5].getText()) * 5000));
		} catch (NumberFormatException e) {
			check = false;
			return;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		check = true;
		tf[6].setText(amount + "");
	}
}
