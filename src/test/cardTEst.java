package test;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;

import javax.swing.JPanel;


public class cardTEst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	final Frame f = new Frame("�������");
	final CardLayout card = new CardLayout(10,10);
	f.setLayout(card);
	
	
	JPanel card1 = new JPanel();
	card1.setBackground(Color.lightGray);
	card1.add(new Label("card 1"));
	JPanel card2 = new JPanel();
	card2.setBackground(Color.orange);
	card2.add(new Label("card 2"));
	JPanel card3 = new JPanel();
	card3.setBackground(Color.cyan);
	card3.add(new Label("card 3"));
	
	
	
	
	f.add(card1,"1");
	f.add(card2,"2");
	f.add(card3,"3");
	
	f.setSize(200, 200);
	f.setVisible(true);
	
	card.show(f, "1");

	
	
	}

}
