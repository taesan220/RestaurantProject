package test;

import java.awt.GridLayout;
import java.awt.ScrollPane;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jscrollpanetest extends JFrame
{
JFrame f = new JFrame("Jscrollpanetest");

ScrollPane scroll = new ScrollPane();

JPanel p = new JPanel(new GridLayout(0,1));

JButton[] btn = new JButton[10];



public Jscrollpanetest(){
	
	f.setSize(100, 100);
	scroll.setSize(100, 100);
	
	f.add(scroll);
	scroll.add(p);
	p.setSize(100,100);
	f.setVisible(true);
	for(int i = 0 ; i <10 ; i++){
	btn[i]= new JButton("Ȯ��");
	p.add(btn[i]);
	
		
	}
	
	
	
}
 public static void main(String[] args) {
	new Jscrollpanetest();
}
};



