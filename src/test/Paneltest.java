package test;


	import java.awt.Color;
	import java.awt.Component;
	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JPanel;

	public class Paneltest extends JFrame implements ActionListener {
	 JPanel[] jpmain = new JPanel[2];

	 JPanel[] jp;
	 Component[] temp;
	 JLabel[] jl = new JLabel[5];
	 
	 JButton[] jbtn = new JButton[5];

	 public Paneltest() {
	  setLayout(new GridLayout(1, 2));

	  for (int i = 0; i < jpmain.length; i++) {
	   jpmain[i] = new JPanel();
	   jp = new JPanel[5];
	   jpmain[i].setLayout(null);

	   control();

	   for (int j = 0; j < jp.length; j++) {
	    jpmain[i].add(jp[j]);
	   }
	  }

	  for (int i = 0; i < jpmain.length; i++) {
	   getContentPane().add(jpmain[i]);
	  }
	  temp=jpmain[0].getComponents();
	  //System.out.println(jpmain[0].getComponents());
	  setSize(500, 500);
	  setVisible(true);

	 }

	 public void control() {
	  for (int i = 0; i < jp.length; i++) {
	   jp[i] = new JPanel();

	   jl[i] = new JLabel(i + " ��");
	   jbtn[i] = new JButton(i + " ��  ����");

	   jbtn[i].addActionListener(this);

	   jp[i].add(jl[i]);
	   jp[i].setName(""+i+"");
	   jp[i].add(jbtn[i]);

	   jp[i].setBounds(50, 70 * i, 200, 60);
	  }
	 }

	 public void actionPerformed(ActionEvent e) {
	  
	 if (e.getSource() == jbtn[0]) {
	   jl[0].setText("0�� + ��ư");
	   jpmain[0].remove((JPanel)temp[0]);   
	   jpmain[0].repaint();
	   
	  } 
	 else if (e.getSource() == jbtn[1]) {
	  jl[1].setText("1�� + ��ư");
	  jpmain[0].remove((JPanel)temp[1]); 
	  jpmain[0].repaint();
	  } else if (e.getSource() == jbtn[2]) {
	   jl[2].setText("2�� + ��ư");
	   jpmain[0].remove((JPanel)temp[2]); 
	   jpmain[0].repaint();
	  } else if (e.getSource() == jbtn[3]) {
	   jl[3].setText("3�� + ��ư");
	   jpmain[0].remove((JPanel)temp[3]); 
	   jpmain[0].repaint();
	  } else if (e.getSource() == jbtn[4]) {
	   jl[4].setText("4�� + ��ư");
	   jpmain[0].remove((JPanel)temp[4]); 
	   jpmain[0].repaint();
	  }
	 }

	 public static void main(String[] args) {
	  new Paneltest();
	 }
	}

