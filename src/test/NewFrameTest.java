package test;
import java.awt.*;
import java.awt.event.*;

public class NewFrameTest extends Frame implements ActionListener
{
Frame f2 = new Frame("�θ���");
Frame f3 = new Frame("�κθ���");//�������� �̸� ����
Button b1;
Button b2;
Button b3;

int createButtonCount = 0;

NewFrameTest()
{
setLocation(300, 300);
f2.setLocation(500, 500);
f3.setLocation(700, 700);//�������� ��ġ
setSize(300, 300);
setLayout(null);
f2.setSize(300, 300);
f2.setLayout(null);
f3.setSize(300, 300);
f3.setLayout(null);

b1 = new Button("Ȯ��1");
b1.addActionListener(this);
b1.setSize(50, 50);
b1.setLocation(100, 100);
add(b1);

b2 = new Button("Ȯ��2");
b2.addActionListener(this);
b2.setSize(50, 50);
b2.setLocation(100, 50);
f2.add(b2);

b3 = new Button("Ȯ��3");
b3.addActionListener(this);
b3.setSize(50, 50);
b3.setLocation(100, 100);
f3.add(b3);

f2.setVisible(false);
f3.setVisible(false);
f2.addWindowListener(new EventHandler());
f3.addWindowListener(new EventHandler());
addWindowListener(new EventHandler());

setVisible(true);
}

public static void main(String[] args)
{
	NewFrameTest test = new NewFrameTest();
}

public void actionPerformed(ActionEvent e)
{
Button source = (Button) e.getSource();

if (source.getActionCommand().equals("Ȯ��1"))
{
f2.setVisible(true);
}
if (source.getActionCommand().equals("Ȯ��2"))
{
f3.setVisible(true);
}
if (source.getActionCommand().equals("Ȯ��3"))
{
if(createButtonCount > 0) return;//��ư�� ���� �ƴٸ� ���̻� ���� ����
      
      b2 = new Button("����");// ��ư ����
b2.addActionListener(this);

b2.setSize(50, 50);
b2.setLocation(100, 150);
f2.add(b2);//�������ӿ� �ļ��� ��ư �ֱ�    
      createButtonCount++;
}


if (source.getActionCommand().equals("����"))
{
f2.remove(source);//������ ��ư�� ����� ��ư ����
      createButtonCount--;
}

}
}

class EventHandler extends WindowAdapter
{
public void windowClosing(WindowEvent e)
{
e.getWindow().setVisible(false);
e.getWindow().dispose();
}
}

