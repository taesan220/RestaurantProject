package test;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class TwoFrametest {

	JFrame f = new JFrame("f");
	
	JFrame ff = new JFrame("ff");
	
	JButton bb = new JButton("Ȯ��");
	
	String[] title = {"a","b"};
	
	DefaultTableModel model = new DefaultTableModel(title,4);
	JTable tt = new JTable(model);
	
	public void TwoFrametest(){
		
		
		
		f.setSize(150, 150);
		f.add(tt);
		
		f.setVisible(true);
		
		
		ff.setSize(100, 100);
		
		f.setLayout(new BorderLayout());
		ff.add(bb,"Center");
		ff.setVisible(true);
		
		
		System.out.println("ss");
		
		
	}
	
	public static void main(String[] args) {
		
		
	new TwoFrametest(); 
	}
}
