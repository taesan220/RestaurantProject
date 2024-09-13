package test;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

class tableselecttest extends JPanel implements MouseListener {
  JTable jtable;

  public tableselecttest() {
    setLayout(new BorderLayout());
    final String[] columns = { "����", "�а�", "����" };
    final String[][] data = { { "ȫ�浿", "�����", "Java" },
        { "����", "���ڰ�", "XML" }, { "������", "����", "EJB" } };

    DefaultTableModel model = new DefaultTableModel(data, columns);

    jtable = new JTable(model);

    jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// ���ϼ���
    jtable.addMouseListener(this);
    JScrollPane spane = new JScrollPane(jtable);
    add(spane, BorderLayout.CENTER);
  }// ������

  public static void main(String[] args) {
    JFrame f = new JFrame("JTable Test");
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    tableselecttest jt = new tableselecttest();
    f.add(jt);
    f.setSize(300, 200);
    f.setVisible(true);
  }// main

  public void mouseClicked(MouseEvent me) {
    int row = jtable.getSelectedRow();
    int column = jtable.getSelectedColumn();
    System.out.println(row + "��, " + column + "���� �� : "
        + jtable.getValueAt(row, column) + " ��������");
  }// mouseClicked

  // �Ʒ��� ���콺 �̺�Ʈ�� ���� ó������
  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }
}// end


