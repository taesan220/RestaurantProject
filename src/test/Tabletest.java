package test;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Tabletest extends JFrame{
 JTable table;
 JScrollPane scroll; // ���̺� ���� �� ���� �־�����~ scroll
 String[] [] data; // 3���� ������ ���� 2���� �迭�� �����Ѵ�.
 String[] title = {"��ȣ","�̸�","����ó","����"}; //�÷��� ���� ������ ǥ���� 1���� �迭
 public Tabletest(){
  // table = new JTable(3,4); //�÷��� �����Ҽ� ����, �����͸� ������ ����.
  //3��������� ������.
  data = new String[3][20];
 /* data[0][0]="1";
  data[0][1]="�迬��";
  data[0][2]="010-2888-0077";
  data[0][3]="yuna@naver.com";
  
  data[1][0]="2";
  data[1][1]="����ȯ";
  data[1][2]="011-748-5236";
  data[1][3]="park@hanmail.net";
  
  data[2][0]="3";
  data[2][1]="����ȣ";
  data[2][2]="010-5685-5258";
  data[2][3]="chanho@nate.com";*/
  
        //title = new String[4]; �ٸ��� �غ���. ����� ���ÿ� �����غ���.
  
  table=new JTable(data,title); // table=new JTable(������-2�����迭, �÷��迭);
  scroll = new JScrollPane(table);
  add(scroll);
  
  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  pack();
  //setSize(400,150);
  setVisible(true);
 }

 public static void main(String[] args) {
  new Tabletest();

 }

}



