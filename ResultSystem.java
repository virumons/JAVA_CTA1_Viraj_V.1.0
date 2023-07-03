
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/*

The Student Grading System computes the Result => Grade of Students, total marks and Grade
Version V.1.0
Author: Viraj Kulkarni

 */

//custom exception for IA errors 
class InternalInputsException extends Exception {
	public String toString() {
		return "Error";
	}
}

//GUI 
class Forms extends JFrame implements ActionListener {
	JFrame f;
	JButton b;
	Container contentPane;
	JPanel p, p1;
	JLabel l, l1, l2, l3, l4, l5, lr1;
	JTextField t2, t1, t3, t4, t5;

	Forms(String title) {
		super(title);

		JLabel n = new JLabel("Student Grading System");
		n.setFont(new Font("inter", Font.BOLD, 30));

		b = new JButton("Submit");
		b.setBounds(180, 80, 20, 20);
		b.addActionListener(this);

		l1 = new JLabel("IA - 1  ");
		t1 = new JTextField(10);
		l1.setForeground(Color.white);
		l1.setFont(new Font("inter", Font.BOLD, 15));

		l2 = new JLabel("IA - 2 ");
		l2.setBounds(100, 100, 0, 0);
		t2 = new JTextField(10);
		l2.setForeground(Color.white);
		l2.setFont(new Font("inter", Font.BOLD, 15));

		l3 = new JLabel("IA - 3  ");
		t3 = new JTextField(10);
		l3.setForeground(Color.white);
		l3.setFont(new Font("inter", Font.BOLD, 15));

		l4 = new JLabel("CTA ");
		t4 = new JTextField(10);
		l4.setForeground(Color.white);
		l4.setFont(new Font("inter", Font.BOLD, 15));

		l5 = new JLabel("External SEE ");
		t5 = new JTextField(10);
		l5.setForeground(Color.white);
		l5.setFont(new Font("inter", Font.BOLD, 15));

		p = new JPanel();
		p1 = new JPanel();

		p1.add(n);
		p.add(l1);
		p.add(t1);

		p.add(l2);
		p.add(t2);

		p.add(l3);
		p.add(t3);

		p.add(l4);
		p.add(t4);

		p.add(l5);
		p.add(t5);

		p.add(b);
		// p.setLayout(new GridLayout(6, 2, 10, 10));
		p.setBackground(Color.DARK_GRAY);
		l = new JLabel();
		l.setFont(new Font("inter", Font.BOLD, 30));

		lr1 = new JLabel();
		lr1.setFont(new Font("inter", Font.BOLD, 30));

		p.setPreferredSize(new Dimension(500, 500));

		contentPane = this.getContentPane();
		contentPane.add(p1, BorderLayout.NORTH);
		contentPane.add(p, BorderLayout.CENTER);
		contentPane.add(l, BorderLayout.WEST);
		contentPane.add(lr1, BorderLayout.SOUTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		double IA1 = Double.parseDouble(t1.getText());
		double IA2 = Double.parseDouble(t2.getText());
		double IA3 = Double.parseDouble(t3.getText());
		double CTA = Double.parseDouble(t4.getText());
		double SEE = Double.parseDouble(t5.getText());
		// String St = Double.toString(SEE);
		int i;
		double CIE, Tol;
		int IA[] = { (int) IA1, (int) IA2, (int) IA3 }; // IA 1,2,,3 => array IA[]

		// checking for IA marks between 0 - 20
		for (i = 0; i < 3; i++) {

			if (IA[i] <= 20 && IA[i] >= 0) {
				// Empty
			} // if
			else {
				try {

					throw new InternalInputsException();
				} catch (InternalInputsException iie) {

					l.setText("Marks between 0 to 20");
				} // catch
			} // else

		} // for
			// Checking for the CTA between 0-10 and SEE between 0-100
		if (CTA <= 10 && CTA >= 0) {
			// Empty
		} // if
		else {
			try {
				throw new InternalInputsException();
			} catch (InternalInputsException iie) {

				l.setText("CTA between 0 to 10");

			} // catch
		} // else
		if (SEE <= 100 && SEE >= 0) {
			// Empty
		} // if
		else {
			try {
				throw new InternalInputsException();
			} catch (InternalInputsException iie) {

				l.setText("External SEE between 0 to 100");

			} // catch
		} // else

		// if(St.equalsIgnoreCase("AB")) {
		// l.setText("Absent");
		// }

		// Calculation for CIE
		if (IA[0] >= IA[1] && IA[2] >= IA[1]) {
			CIE = IA[0] + IA[2] + CTA;
		} else if (IA[0] >= IA[2] && IA[1] >= IA[2]) {
			CIE = IA[0] + IA[1] + CTA;
		} else {
			CIE = IA[1] + IA[2] + CTA;
		}
		// CIE check For CIE if less then 20
		if (CIE < 20) {
			l.setText("Student is detained ! " + CIE);
		}

		// Updating the SEE if 38,39 =>> 40||41
		else if (SEE == 38 || SEE == 39) {
			SEE = SEE + 2;
			// empty
		}
		// Student Failed if less then 38
		else if (SEE < 38) {
			l.setText("Student Failed Grade F ! " + SEE);
		}
		// Calculation for Total marks and printing the Grade according
		Tol = CIE + SEE / 2;

		int roundedTol = (int) Math.round(Tol);
		String grade;

		switch (roundedTol / 10) {
		case 10:
		case 9:
			grade = "S";
			break;
		case 8:
			grade = "A";
			break;
		case 7:
			grade = "B";
			break;
		case 6:
			grade = "C";
			break;
		case 5:
			grade = "D";
			break;
		case 4:
			grade = "E";
			break;
		default:
			grade = "F";
			break;
		}

		lr1.setText("Grade : " + grade + " Total=> " + roundedTol);

	}// method
}// class

public class ResultSystem {

	public static void main(String[] args) {

		JFrame f = new Forms("Student Grading System");

		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(900, 500);
		f.setVisible(true);

	}

}
