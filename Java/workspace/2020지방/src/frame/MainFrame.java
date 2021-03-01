package frame;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;

public class MainFrame extends BaseFrame {
	public MainFrame() {
		super(300, 400, "", 2);

		setLayout(new GridLayout(0, 1));
		add(setlabel(new JLabel(userName + "ȯ��", 0), new Font("����", 1, 25)));
		add(setBtn("���Ό��", e -> openFrame(new ReservationFrame())));
		add(setBtn("�������û", e -> compare()));
		add(setBtn("���Ό����Ȳ", e -> openFrame(new ReservationStatusFrame())));
		add(setBtn("������� �м�", e -> openFrame(new AnalysisFrame())));
		add(setBtn("����", e -> openFrame(new LoginFrame())));
	}

	public void compare() {
		try (var pst = CM.con.prepareStatement("select * from hospitalization where p_no = ? order by h_no desc limit 1;")) {
			pst.setObject(1, userNo);
			var rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getString(6).length() == 0) {
					openFrame(new BillFrame());
				} else {
					openFrame(new AdmissionFrame());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
