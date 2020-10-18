package frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReservationFrame extends BaseFrame {

	JComboBox<String> cbStartStation = new JComboBox<String>();
	JComboBox<String> cbArriveStation = new JComboBox<String>();

	JComboBox<Integer>[] cbPeople_info = new JComboBox[3];

	JTextField tfDate = createComponenet(new JTextField(), 140, 30);
	static int width = 600, height = 330;

	public ReservationFrame() {
		super(width, height, "������ ����");
		setLayout(new FlowLayout());
		
		JPanel wrappanel = createComponenet(new JPanel(new BorderLayout()), width, height);
		JPanel northpanel = createComponenet(new JPanel(new FlowLayout(FlowLayout.LEFT,0,10)), width,height-160);
		JPanel centerpanel = createComponenet(new JPanel(new FlowLayout(FlowLayout.LEFT)), width, 60);
		JPanel center_inner = createComponenet(new JPanel(new GridLayout(2,1,10,0)), width-100, 60);
		
		wrappanel.setBorder(BorderFactory.createEmptyBorder(10,20,20,10));
		
		northpanel.add(createComponenet(new JLabel("��߿�"), 60,20));
		northpanel.add(createComponenet(cbStartStation, 200, 30));
		northpanel.add(createComponenet(new JLabel("������",0), 60,50));
		northpanel.add(createComponenet(cbArriveStation, 200, 30));
		northpanel.add(createComponenet(new JLabel("��¥"), 60,50));
		northpanel.add(tfDate);
		tfDate.setEnabled(false);
		northpanel.add(createButton("�޷�", e->openFrame(new CalendarFrame())));
		
		centerpanel.add(createComponenet(new JLabel("�ο�����"), 60, 20));
		
		center_inner.add(createComponenet(new JLabel("� (�� 13�� �̻�)",0), 140, 20));
		center_inner.add(createComponenet(new JLabel("��� (�� 6~12��)",0), 140, 20));
		center_inner.add(createComponenet(new JLabel("��� (�� 65�� �̻�)",0), 140, 20));
		
		for (int i = 0; i < cbPeople_info.length; i++) {
			center_inner.add(createComponenet(cbPeople_info[i] = new JComboBox<Integer>(), 140, 30));
		}
		
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j < 3; j++) {
				cbPeople_info[j].addItem(i);
			}
		}
		
		centerpanel.add(center_inner);
		wrappanel.add(northpanel,BorderLayout.NORTH);
		wrappanel.add(centerpanel);
		centerpanel.add(createComponenet(createButton("��ȸ", e->clickInquiry()), width, 30),BorderLayout.SOUTH);
		add(wrappanel);
	}

	public void clickInquiry() {
		
	}
	
	public void setStationData() {
		
	}

	public static void main(String[] args) {
		new ReservationFrame().setVisible(true);
	}
}
