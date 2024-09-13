package test;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class FrameTest {

	
	int i=0;
	JPanel[] panel;
	
	JFrame frame = new JFrame();
	
	JLabel label = new JLabel("�ݰ����ϴ�.");
	
	Button btn = new Button("��ư�� Ŭ���ϼ���");
	Button bt2 = new Button("���ƿ�");
	
	
	public FrameTest(){

	//	frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setSize(400, 400);		
		
		
		
		panel = new JPanel[3];
		
		panel[0] = new JPanel();
		panel[1] = new JPanel();
		
		panel[0].add(btn);
		panel[0].setBackground(Color.BLUE);
		panel[0].setSize(400, 400);
		
		
	frame.add(panel[0]);	
	
	
	
	btn.addActionListener(new ActionListener() {
	
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			
			System.out.println(22);
			
			if(command.equals("��ư�� Ŭ���ϼ���")){
				System.out.println(44);
				
				
				frame.remove(panel[0]);
				
				panel[1].setSize(400,400);
				panel[1].add(label);
				//panel[1].setBackground(Color.RED);
				panel[1].add(bt2);
				frame.add(panel[1]);
				frame.setVisible(true);
			}
		}
	});
	
	
		
		
	
		
	}
	
	
	
	public static void main(String[] args) {
		
		
		FrameTest f = new FrameTest();
		

	}

}
