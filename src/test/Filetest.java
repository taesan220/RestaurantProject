package test;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

public class Filetest extends Frame implements WindowListener, ActionListener // ��������
// �̺�Ʈ��
{ // Ŭ������ ����
// private Notepad notePadFrame; // ��Ʈ��� ��ü
 private TextArea textArea; // �ؽ�Ʈ �����̷� ����

 private MenuBar menuBar; // �޴���(����ū)

 private Menu fileMenu; // �޴�(�״���)

 private MenuItem newMenuItem; // �޴�(���θ����, �ؽ�Ʈ ���� �ʱ�ȭ�Ҳ�)

 private MenuItem openMenuItem; // �޴��׸�(����)

 private MenuItem saveMenuItem; // �޴��׸�(����)

 private MenuItem saveAsMenuItem; // �޴��׸�(���̸����� ����, ����Ʈ�� �� ���Ϻ���)

 private MenuItem exit; // �޴��׸�(������)

 // �ΰ�
 private FileDialog fileDialog; // ���� ���̾�α�(����, �����ҋ�)

 private String fileName; // ������ �̸��� �Ѿ�� ����

 private Menu menu;

 // //////////////////////////// �⺻ �����ڷ�
 // ui����////////////////////////////////////////////////////////////////////////
 public Filetest() // ������
 { // Notepad �������� ����

  // �׸� ����
  textArea = new TextArea(); // �ؽ�Ʈ ����� ����
  menuBar = new MenuBar(); // �޴��� ����
  menu = new Menu("����"); // �޴�����(�̸��� ���Ϸ�)
  newMenuItem = new MenuItem("���θ����"); // �޴������� ����(���θ����)
  openMenuItem = new MenuItem("����"); // �޴������� ����(����)
  saveMenuItem = new MenuItem("�����ϱ�"); // �޴������� ����(�����ϱ�)
  saveAsMenuItem = new MenuItem("���̸���������"); // �޴������� ����(���̸���������)
  exit = new MenuItem("����"); // �޴������� ����(����)

  // �޴� �����
  menuBar.add(menu); // �޴��ٿ��� �޴��� ���̰�
  menu.add(newMenuItem); // �޴����� �޴��׸���̰�~
  menu.add(openMenuItem);
  menu.add(saveMenuItem);
  menu.add(saveAsMenuItem);
  menu.add(exit);

  setMenuBar(menuBar); // �����ӿ� �޴��ٸ� ����~ ������� ��

  setTitle(fileName); // �Ѿ�� �����̸����� Ÿ��Ʋ ����

  // ������ ����
  newMenuItem.addActionListener(this); // ���� �׸� �ش��ϴ� �����ʸ� ������ ������ �ؿ���
  // �̳���� �Ǽ�׸����ʸ� ���
  openMenuItem.addActionListener(this);
  saveMenuItem.addActionListener(this);
  saveAsMenuItem.addActionListener(this);
  exit.addActionListener(this);

  addWindowListener(this); // ������ xǥ�ô����� ����ǰ� �ϴ� ������ ����
 } // Notepad �������� ��

 // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 // �̺�Ʈ �ڵ鷯 ������
 public void actionPerformed(ActionEvent ae) { // �������� ����
  String getAction = ae.getActionCommand(); // getAction ������ Ŀ�ǵ� �޼ҵ带!!
  String fileName = null; // ���� �̸��� ���� ����
  String tempText = null; // �ؽ�Ʈ ���� �ִ��� ������

  File file; // ���ϻ�������
  FileReader fileReader; // ������ �о�ú���(���ϰ���������ָ� ������) å���ֳ� -_- ���
  // FileInputStream�ۿ�

  FileOutputStream fileOutputStream; // ������ ������(å�� �� ��Ʈ������ �س��� -_- �Ҷ�� ����
  // ����Ʈ������ �ϴ��� --;;)
  PrintStream printStream; // �뵵�� ��Ȯ���� �𸣰����� ���� ����� ���ñ� ����..�����..

  BufferedReader bufferedReader; // �о�� ������ ������ ������ ����

  String temp; // ������ ������ �о�� �ӽ� ������� ^^v

  if (getAction.equals("���θ����")) // ���θ���⸦ �����Ͽ����� equals�� �׸��� �̸��� ����? -_-
  {
   if (tempText != null) // �ؽ�Ʈ���� ������ ������
   {
    textArea.setText(""); // �ؽ�Ʈ���� ��������
   } else {
    // �޼��� �ڽ��� ó���ؾߵǴ��� ����-_- �󺧷� �ؾߵǳ�..
   }
  } else if (getAction.equals("����")) // ���⸦ �����Ͽ�����
  {
   // ���� ���̾�α�
   fileDialog = new FileDialog(this, "���� ������ �����ϼ���", FileDialog.LOAD); // ����
   // ���̾�α׸�
   // �����Ѵ�.
   fileDialog.setVisible(true); // ���̴°�(Ʈ��) ����
   fileName = fileDialog.getDirectory() + fileDialog.getFile(); // ���ϳ���
   // ������
   // ���丮��
   // ���ϸ���
   // ����
   // ����

   try {
    fileReader = new FileReader(fileName); // ���ϸ������� ������ ��θ� �̿���
              // ������
    // �о��
    bufferedReader = new BufferedReader(fileReader); // ���۸�������
                 // ���ϸ�����
    // �ִ� ������ �о��

    textArea.setText(""); // �ϴ� �ؽ�Ʈ ���� �ʱ�ȭ
    while ((temp = bufferedReader.readLine()) != null) // temp�ȿ�
                 // ���۸�����
    // ������ ������ �о
    // ����������
    {
     textArea.append(temp + "\n"); // ������� �ؽ�Ʈ ���� ���
    }
   } catch (Exception e) {
    e.printStackTrace();
   }
   setTitle(fileName);
  } else if (getAction.equals("�����ϱ�")) {

   fileDialog = new FileDialog(this, "����", FileDialog.SAVE); // ����
   // ���̾�α׸�
   // �����Ѵ�.
   fileDialog.setVisible(true); // ���̴°�(Ʈ��) ����
   fileName = fileDialog.getDirectory() + fileDialog.getFile(); // ���ϳ���
   // ������
   // ���丮��
   // ���ϸ���
   // ����
   // ����
   file = new File(fileName); // ������ ���� ����

   try {
    fileOutputStream = new FileOutputStream(file);
    printStream = new PrintStream(fileOutputStream);
    printStream.print(textArea.getText());
   } catch (Exception ex) {
    ex.printStackTrace();
   }

   setTitle(fileName);
  } else if (getAction.equals("���̸�����")) {

  } else if (getAction.equals("����")) {
   dispose();
   System.exit(0);
  }

 }// �������� ��

 // /////////////////////////////////������ ������
 // ������//////////////////////////////////////////////////////////////////////////////

 public void windowClosing(WindowEvent e) {
  setVisible(false);
  System.exit(0);
 }

 public void windowOpened(WindowEvent e) {
 }

 public void windowIconified(WindowEvent e) {
 }

 public void windowDeiconified(WindowEvent e) {
 }

 public void windowClosed(WindowEvent e) {
 }

 public void windowActivated(WindowEvent e) {
 }

 public void windowDeactivated(WindowEvent e) {
 }

 // //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 public static void main(String args[]) {
  Filetest notePadApp = new Filetest();
  notePadApp.setSize(640, 480);
  notePadApp.setVisible(true);
 }
}// Ŭ������ ��
