package frame;

import java.awt.GridLayout;

public class MainFrame extends BaseFrame{
	public MainFrame() {
		super(300, 300, "����");
		setLayout(new GridLayout(0,1));
		
		add(createButton("������ ����", e-> openFrame(new ReservationFrame())));
		add(createButton("������ ���", e-> openFrame(new ReservationCancelFrame())));
		add(createButton("�α׾ƿ�", e-> openFrame(new LoginFrame())));
		add(createButton("����", e-> dispose()));
	}
	
	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}
}
