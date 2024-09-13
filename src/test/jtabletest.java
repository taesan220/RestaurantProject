package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class jtabletest extends JFrame implements ActionListener, MouseListener {
 public Container con_Addr = this.getContentPane();

 private JPanel panel_Left = new JPanel();
 private JPanel panel_Input = new JPanel();
 private JPanel panel_Search = new JPanel();
 private JPanel panel_Sleft = new JPanel();
 private JPanel panel_Sright = new JPanel();
 private JPanel panel_Choose = new JPanel();

 // �Է¸��
 private JLabel lb_Name = new JLabel("  �� ��");
 private JTextField tf_Name = new JTextField(12);
 private JLabel lb_Phone = new JLabel("  �� ȭ");
 private JTextField tf_Phone = new JTextField(12);
 private JLabel lb_Addr = new JLabel("  �� ��");
 private JTextField tf_Addr = new JTextField(100);
 private JLabel lb_Memo = new JLabel("  �� ��");
 private JTextField tf_Memo = new JTextField(100);
 private JButton bt_Add = new JButton("�� ��");
 private JButton bt_Init = new JButton("�ٽ��Է�");

 // �ּҷ� �߰�, ����
 private String[] info_temp = new String[4];
 private String name, phone, addr, memo;
 private int snum;

 // �˻����
 private JLabel lb_Search = new JLabel("�˻����� : ");
 private JLabel lb_Word = new JLabel("�˻��ܾ� : ");
 private JCheckBox cb_Name = new JCheckBox("�� ��");
 private JCheckBox cb_Phone = new JCheckBox("�� ȭ");
 private JTextField tf_Word = new JTextField(10);
 private JButton bt_Search = new JButton("�� ��");
 private JTextArea ta_Result = new JTextArea("�˻����");
 private JScrollPane sp_Result = new JScrollPane(ta_Result);

 // ����Ʈ���
 private String[] str = { "�� ��", "��ȭ��ȣ", "�� ��", "�� ��" };
 private DefaultTableModel dtm = new DefaultTableModel(str, 0); // 30��
 private DefaultTableColumnModel dtcm = new DefaultTableColumnModel(); // ���ʺ�
                   // ��������
 private JTable table = new JTable(dtm, dtcm);
 private JScrollPane jsp = new JScrollPane(table);
 private JPanel panel_Right = new JPanel();
 private TableColumn tc1, tc2, tc3, tc4; // �� �ʺ� ����
 private JButton bt_Modi = new JButton("�����ϱ�");
 private JButton bt_Del = new JButton("�����ϱ�");

 // ���� �����
 private Vector vData = new Vector();
 private String user_Id;
 private File dir;

 public jtabletest() {

  dir = new File("Address//");
  load();
  action();
  init();
  list();
 }

 public void init() {
  // �����Է�

  panel_Input.setBorder(new TitledBorder("�����Է�"));
  panel_Input.setLayout(new BorderLayout());
  JPanel input_lb = new JPanel();
  JPanel input_tf = new JPanel();
  JPanel input_total = new JPanel();

  input_lb.setLayout(new GridLayout(4, 1));
  input_lb.setPreferredSize(new Dimension(40, 100));
  input_tf.setPreferredSize(new Dimension(160, 100));
  input_tf.setLayout(new GridLayout(4, 1));
  input_lb.add(lb_Name);
  input_tf.add(tf_Name);
  input_lb.add(lb_Phone);
  input_tf.add(tf_Phone);
  input_lb.add(lb_Addr);
  input_tf.add(tf_Addr);
  input_lb.add(lb_Memo);
  input_tf.add(tf_Memo);

  input_total.setLayout(new FlowLayout());
  input_total.add(input_lb);
  input_total.add(input_tf);

  JPanel input_bt = new JPanel();
  input_bt.setPreferredSize(new Dimension(0, 20));
  // bt_Init.setPreferredSize(new Dimension(90, 20));
  // bt_Add.setPreferredSize(new Dimension(90, 20));
  input_bt.add(bt_Init);
  input_bt.add(bt_Add);

  panel_Input.add("North", input_total);
  panel_Input.add("Center", input_bt);

  // �˻�ȭ��
  panel_Search.setBorder(new TitledBorder("��  ��"));
  JPanel panel_Top = new JPanel();
  panel_Top.setLayout(new FlowLayout());
  bt_Search.setPreferredSize(new Dimension(70, 25));
  panel_Top.add(tf_Word);
  panel_Top.add(bt_Search);
  panel_Search.add("Center", panel_Top);

  // ��ü ȭ�� ����
  // panel_Left.setLayout(new GridLayout(2,1));

  panel_Left.setLayout(new BorderLayout());
  panel_Left.setPreferredSize(new Dimension(220, 0));
  panel_Left.add("Center", panel_Input);
  panel_Left.add("South", panel_Search);
  con_Addr.add("West", panel_Left);
  con_Addr.add("Center", panel_Right);

 }

 public void list() { // ����Ʈ ����
  JPanel list_Bottom = new JPanel();
  tc1 = new TableColumn(0, 2);
  tc1.setHeaderValue("�� ��");
  tc2 = new TableColumn(1, 40);
  tc2.setHeaderValue("��ȭ��ȣ");
  tc3 = new TableColumn(2, 100);
  tc3.setHeaderValue("��    ��");
  tc4 = new TableColumn(3, 20);
  tc4.setHeaderValue("�� ��");
  dtcm.addColumn(tc1);
  dtcm.addColumn(tc2);
  dtcm.addColumn(tc3);
  dtcm.addColumn(tc4);

  list_Bottom.setLayout(new FlowLayout());
  list_Bottom.add(bt_Modi);
  list_Bottom.add(bt_Del);
  panel_Right.setBorder(new TitledBorder("��� ����"));
  panel_Right.setLayout(new BorderLayout());
  panel_Right.add("Center", jsp);
  panel_Right.add("South", list_Bottom);
 }

 public void action() {
  bt_Del.addActionListener(this);
  bt_Init.addActionListener(this);
  bt_Add.addActionListener(this);
  bt_Modi.addActionListener(this);
  bt_Search.addActionListener(this);
  table.addMouseListener(this);
 }

 public void actionPerformed(ActionEvent e) {
  if (e.getSource() == bt_Del) { // ���� �ּ����� ����
   int line = table.getSelectedRow();
   if (line == -1) {
    JOptionPane.showMessageDialog(this, "�����Ϸ���  ����� �����ϼ���");
    return;
   }
   dtm.removeRow(line); // ���õ� Row ����
   for (int i = 0; i < 4; i++) {
    vData.remove(line * 4);
   }
   save();

  } else if (e.getSource() == bt_Init) {// �ʵ� �ʱ�ȭ
   tf_Addr.setText("");
   tf_Memo.setText("");
   tf_Name.setText("");
   tf_Phone.setText("");

  } else if (e.getSource() == bt_Add) { // �����Է�
   name = tf_Name.getText();
   phone = tf_Phone.getText();
   addr = tf_Addr.getText();
   memo = tf_Memo.getText();
   info_temp[0] = name;
   info_temp[1] = phone;
   info_temp[2] = addr;
   info_temp[3] = memo;
   if (name.equals("") || phone.equals("") || addr.equals("")) {
    JOptionPane.showMessageDialog(this, "�̸�, ��ȭ��ȣ, �ּҸ� �Է��ϼ���!",
      "Ȯ��", JOptionPane.INFORMATION_MESSAGE);
    return;
   }

   if (bt_Add.getText() == "�Ϸ�") { // ����
    for (int i = 0; i < info_temp.length; i++) {
     dtm.setValueAt(info_temp[i], snum, i); // ���̺� ä���
     vData.remove(snum * 4); // 4�� �����
    }

    bt_Add.setText("�߰�");
    save(); // �ڵ� ��� ����
   } else
    dtm.addRow(info_temp);

   // �Է� ���� ���Ϳ� ����
   for (int i = 0; i < info_temp.length; i++) {
    vData.addElement(info_temp[i]);
   }
   save(); // �ڵ� ��� ����
   // �ʵ� �ʱ�ȭ
   tf_Addr.setText("");
   tf_Memo.setText("");
   tf_Name.setText("");
   tf_Phone.setText("");

  } else if (e.getSource() == bt_Modi) {// ���� ����
   snum = table.getSelectedRow();
   if (snum == -1) {
    JOptionPane.showMessageDialog(this, "�����Ϸ��� ����� �����ϼ���");
    return;
   }

   for (int i = 0; i < info_temp.length; i++) {
    info_temp[i] = (String) dtm.getValueAt(snum, i);
   }

   tf_Name.setText(info_temp[0]);
   tf_Phone.setText(info_temp[1]);
   tf_Addr.setText(info_temp[2]);
   tf_Memo.setText(info_temp[3]);

   bt_Add.setText("�Ϸ�");

  } else if (e.getSource() == bt_Search) { // ���� �˻�
   table.clearSelection();
   String[] search = new String[vData.size()];
   for (int i = 0; i < vData.size(); i++) {
    search[i] = (String) vData.elementAt(i);
   }
   String keyword = tf_Word.getText();
   if (tf_Word.getText().equals("")) {
    JOptionPane.showMessageDialog(this, "�˻�� �Է��� �ּ���", "�� ��",
      JOptionPane.INFORMATION_MESSAGE);
    return;
   }

   boolean bool = false;
   int count = 0;
   for (int i = 0; i < search.length; i++) {
    if (keyword.equals("")) {
     ta_Result.setText("�˻�� �Է��ϼ���!");
     return;
    } else if (search[i].indexOf(keyword) != -1) {
     table.addRowSelectionInterval((int) i / 4, (int) i / 4);
     table.setSelectionForeground(Color.red);
     bool = true;
     count++;
    }
   }
   if (bool) {
    JOptionPane.showMessageDialog(this, count + "�� ã�ҽ��ϴ�!", "�˻����",
      JOptionPane.INFORMATION_MESSAGE);
   } else
    JOptionPane.showMessageDialog(this, "�˻� ����� �����ϴ�!", "�˻����",
      JOptionPane.INFORMATION_MESSAGE);
  }

 }

 public void save() {// ������ ������ ���Ϸ� ����
  try {
   File file = new File("AddrData");

   BufferedWriter out = new BufferedWriter(new FileWriter(file));
   String[] str2 = new String[vData.size()];
   String str = "";
   for (int i = 0; i < vData.size(); i++) {
    str2[i] = (String) vData.elementAt(i);
   }
   for (int j = 0; j < str2.length; j++) {
    if ((j + 1) % 4 == 0 && (j + 1) != str2.length) {
     str += str2[j] + "||\n";
    } else {
     str += str2[j] + "||";
    }
   }
   out.write(str, 0, str.length());
   out.newLine();
   out.close();
  } catch (IOException exc) {
   exc.printStackTrace();
  }
 }

 public void load() { // ���Ϸ� ���� �о ���ͷ� ������ ���̺� ���
  File file = new File("AddrData");
  try {
   if (!file.exists()) {
    file.createNewFile();
   }

   BufferedReader in = new BufferedReader(new FileReader(file));
   String str = "";
   vData = new Vector();
   while ((str = in.readLine()) != null) {
    StringTokenizer token = new StringTokenizer(str, "||" + "\n");
    int i = 0;
    String[] str2 = new String[15]; // while�� �ȿ� �־�� �Ѵ�.
    while (token.hasMoreTokens()) {
     str2[i] = token.nextToken();
     vData.addElement(str2[i]);
     i++;
    }

    dtm.addRow(str2);
   }
   in.close();
  } catch (IOException e) {
   e.printStackTrace();
  }
 }

 public void mouseClicked(MouseEvent e) {
  table.setSelectionForeground(Color.black);
 }

 public void mouseEntered(MouseEvent arg0) {
 }

 public void mouseExited(MouseEvent arg0) {
 }

 public void mousePressed(MouseEvent arg0) {
 }

 public void mouseReleased(MouseEvent arg0) {
 }
public static void main(String[] args) {
	new jtabletest();
}
}

