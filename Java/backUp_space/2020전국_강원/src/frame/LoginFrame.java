package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFrame extends BaseFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField tfId = createComponenet(new JTextField(), 200, 30);
	JPasswordField tfPw = createComponenet(new JPasswordField(), 200, 30);

	public LoginFrame() {
		super(300, 210, "�α���");
		add(createComponenet(createLabel(new JLabel("�α���", 0), new Font("����", 1, 24)), 200, 40), BorderLayout.NORTH);

		JPanel centerpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		JPanel southpanel = new JPanel();

		centerpanel.add(createComponenet(new JLabel("ID:"), 55, 30));
		centerpanel.add(tfId);
		centerpanel.add(createComponenet(new JLabel("PW:"), 55, 30));
		centerpanel.add(tfPw);

		southpanel.add(createButton("�α���", e -> clickLogin()));
		southpanel.add(createButton("ȸ������", e -> openFrame(new SignUpFrame())));
		add(centerpanel);
		add(southpanel, BorderLayout.SOUTH);
	}

	@SuppressWarnings("deprecation")
	public void clickLogin() {
		if (tfId.getText().isEmpty() || tfPw.getText().isEmpty()) {
			errorMessage("ID �Ǵ� Pw�� �Է����ּ���.");
		}

		try (PreparedStatement pst = connection.prepareStatement("select * from USER where id=? and pw=?")) {
			pst.setObject(1, tfId.getText());
			pst.setObject(2, tfPw.getText());
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				informationMessage(rs.getString(4) + "�� ȯ���մϴ�.");
				openFrame(new MainFrame());
			}else {
				errorMessage("ID �Ǵ� PW�� Ȯ�����ּ���.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new LoginFrame().setVisible(true);
	}
}
