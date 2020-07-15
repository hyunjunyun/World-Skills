package frame;

import java.util.Scanner;

public class CircleManager {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);

		System.out.print("������ �� >>");

		int radius = scan.nextInt();

		Circle c = new Circle();
		c.setRadius(radius);

		System.out.println("������ " + c.getRadius() + "�� ���� ���̴� " + c.Area() + "�̴�.");
	}
}

class Circle {
	private int radius;

	public Circle() {
	}

	public double Area() { // ���� ���� return (���� ���� = ������*������*3.14)
		return radius * radius * 3.14;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public int getRadius() {
		return radius;
	}
}