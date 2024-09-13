package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.text.*;
import java.util.Date;
import java.util.Calendar;

// ���� �� �ұԸ� �系���� ����� �� �ִ� ��������

class Gui1 extends JFrame implements ActionListener, ItemListener
{
	// jdbc ���� ���� ����
	String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
	String url = "jdbc:odbc:jdbc";
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	String[] myYear, myMonth, myDate;	// choice�� ���� ����� ��ġ
	Choice chYear, chMonth, chDate;		// choice�� ����� ���
	int Year_num, Month_num, Date_num;	

	String eYear, eMonth, eDate;
	String eDay = null;			// choice �� ���ǵ� ��¥�� yyyy-MM-dd �������� ����

	// ��¥ ���� 
	Calendar cal;
	Date date;
	SimpleDateFormat formatter;
	String pattern ;
	
	// ������ �����ִ� JTextArea
	JTextArea txt_work = null;		// ���޻���
	JTextArea txt_tang = null;		// ����
	JTextArea txt_workdelay = null;		// ������
	
	// ���� �̺�Ʈ�� ������ JButton
	JButton btn_find = null;		// ��¥ ��ȸ
	JButton btn_today = null;		// ��� ��¥������ ���÷� �ٷΰ���
	JButton btn_modify = null;		// ����
	JButton btn_save = null;		// ����
	JButton btn_exit = null;		// ����
	JButton btn_workdelayModify = null;	// ������ ������ư
	JButton btn_workdelaySave = null;	// ������ �����ư


	// ������
	public Gui1(String title){
		super(title);
		
		// ���� ��¥�� ����Ͽ� �����Ѵ�.
		cal = Calendar.getInstance();
		date = cal.getTime();
		formatter = new SimpleDateFormat("yyyy");
		eYear = formatter.format(date);

		formatter = new SimpleDateFormat("MM");
		eMonth = formatter.format(date);
	
		formatter = new SimpleDateFormat("dd");
		eDate = formatter.format(date);

		myYear = new String[20];		// �⵵�� ���� 20�Ⱓ ����� �� �ְ� ����
		chYear = new Choice();

		for (int i=0; i<myYear.length ;i++ )	// chYear �� 2008����� 2027�� ����
		{
			myYear[i] = i + 2008 + "";
			chYear.add(myYear[i]);
		}
	
		myMonth = new String[12];		// ���� �׻� 12����			
		chMonth = new Choice();

		for (int i=0; i<myMonth.length ;i++ )	// chMonth�� 12������ �� ����
		{	
			if (i < 9)
			{
				myMonth[i] = "0" + (i + 1) + "";
				chMonth.add(myMonth[i]);
			}
			else {
				myMonth[i] = (i + 1) + "";
				chMonth.add(myMonth[i]);
			}
		}
		
		// ������ �ش� ����� �ڵ������Ѵ�.
		chYear.select(eYear);
		chMonth.select(eMonth);

		// ������ ���� ���� ������ ã�� ���� ����� �����Ѵ�.
		int Year_num = Integer.parseInt(chYear.getSelectedItem());
		int Month_num = Integer.parseInt(chMonth.getSelectedItem());		
		
		//  ���ǵ� ����� �ش��ϴ� ���ڸ� �޾� chDate�� ���ڸ� �����Ѵ�.
		chDate = new Choice();
		SET_chDate(Year_num, Month_num);
	
		chDate.select(eDate);	// ������ ���ڸ� �ڵ�����

		eDay = chYear.getSelectedItem() + "-" + chMonth.getSelectedItem() + "-" + chDate.getSelectedItem();

		txt_work = new JTextArea(19,66);
		txt_work.setEnabled(false);			// ó�� ȭ����½� ���� ���� �����Ұ�
		txt_tang = new JTextArea(9,29);
		txt_tang.setEnabled(false);			// ó�� ȭ����½� ���� ���� �����Ұ�
		txt_workdelay = new JTextArea(9,30);
		txt_workdelay.setEnabled(false);
		
		// ��ư ����
		btn_find = new JButton(" �� ȸ ");
		btn_today = new JButton(" �� �� ");
		btn_modify = new JButton(" �� �� ");
		btn_save = new JButton(" �� �� ");
		btn_exit = new JButton(" �� �� ");

		btn_workdelayModify = new JButton("����");
		btn_workdelaySave = new JButton("����");


		// ���� ��ư ��Ȱ��ȭ ������ư Ŭ���� �Ŀ��� Ȱ��ȭ
		btn_save.setEnabled(false);
		btn_workdelaySave.setEnabled(false);

		// �̺�Ʈ ���
		chMonth.addItemListener(this);
		chDate.addItemListener(this);

		btn_find.addActionListener(this);
		btn_today.addActionListener(this);
		btn_modify.addActionListener(this);
		btn_save.addActionListener(this);
		btn_exit.addActionListener(this);

		btn_workdelayModify.addActionListener(this);
		btn_workdelaySave.addActionListener(this);

		// ���� ��� â �����

		// panel_c�� ��¥�� ��ư ����
		JPanel panel_n = new JPanel();

		panel_n.add(chYear);
		panel_n.add(chMonth);
		panel_n.add(chDate);
	
		panel_n.add(btn_find);
		panel_n.add(btn_modify);
		panel_n.add(btn_save);
		panel_n.add(btn_today);
		panel_n.add(btn_exit);

		// panel_c�� �󺧰� �ؽ�Ʈ����� ����
		JPanel panel_c = new JPanel();
		panel_c.add(new JLabel("����"));
		JScrollPane scroll_work = new JScrollPane(txt_work);
		panel_c.add(scroll_work);
		
		// panel_s�� ���� �󺧰� �ؽ�Ʈ����� ����
		JPanel panel_s = new JPanel(new FlowLayout());
		panel_s.add(new JLabel("����"));
		JScrollPane scroll_tang = new JScrollPane(txt_tang);
		panel_s.add(scroll_tang);
	
		// panel_s �ȿ� ���ο� �г� panel_s_in �� ����� �󺧰� ��ư�� ����
		JPanel panel_s_in = new JPanel(new GridLayout(3,1));
		panel_s_in.add(new JLabel("������"));
		panel_s_in.add(btn_workdelayModify);
		panel_s_in.add(btn_workdelaySave);

		panel_s.add(panel_s_in);

		// panel_s�� ������ �ؽ�Ʈ����� ����
		JScrollPane scroll_workdelay = new JScrollPane(txt_workdelay);
		panel_s.add(scroll_workdelay);
		
		// ������� ������ ���� panel�� JFrame�� ����
		add("North", panel_n);	
		add("Center", panel_c);
		add("South", panel_s);
		
		// DB �����Ͽ� �ش� ��¥�� �����͸� �ҷ��´�.
		DB_conn();

		String sql = "Select * from Haedock_table where ��¥ like '" + eDay +"'";
		DB_search(sql);

		DB_close();

		// DB �����Ͽ� �������� �ҷ��´�.
		DB_workdelaySearch();

		setSize(800, 600);
		setResizable(false);
		setVisible(true);
	} // ������
		
		// ���õ� ����� �ش��ϴ� ���� �Ҵ� �޼ҵ�
		public void SET_chDate(int Year_num, int Month_num){

			// ���õ� ��� �޿� �Ϸ縦 ���� ���� ���� ������ ��¥�� �ȴ�.
			// �޿� 9�� ������ 10�� 1�Ϸμ����� �ȴ� 
			cal.set(Year_num, Month_num , 1, 0, 0, 0);	

			// ���� ������ ����� ù������ �Ϸ縦 ���� chMonth���� ������ ���� ������ ��¥���ȴ�.		
			cal.add(Calendar.DATE, -1);				
		
			Date_num = cal.get(Calendar.DATE);		// ���õ� ��� ���� ������ ��¥

			// ���õ� ��� ���� ��¥�� chDate�� ��´�.
			myDate = new String[Date_num];

			for (int i=0; i<myDate.length ;i++ )
			{	
				if (i < 9)
				{
					myDate[i] = "0" + (i + 1) + "";
					chDate.add(myDate[i]);
				}
				else {
					myDate[i] = (i + 1) + "";
					chDate.add(myDate[i]);
				}
			}

		} // SET_chDate()

		// ItemEvent ó��
		public void itemStateChanged(ItemEvent e){
			Object obj = e.getSource();
			eDay = chYear.getSelectedItem() + "-" + chMonth.getSelectedItem() + "-" + chDate.getSelectedItem();

			if (obj == chMonth)			// ���� �����ϸ� ���õ� ����� �´� ���ڷ� ����
			{
				chDate.removeAll();		

				int Year_num = Integer.parseInt(chYear.getSelectedItem());
				int Month_num = Integer.parseInt(chMonth.getSelectedItem());

				SET_chDate(Year_num, Month_num);
			}

			if (obj == chDate)	// ���ڸ� �����ϸ� �ش� �����͸� �ҷ� ȭ�鿡 ���
			{
				DB_conn();

				String sql = "Select * from Haedock_table where ��¥ like '" + eDay +"'";

				DB_search(sql);

			}
		}// ItemEvent

		// ActionEvent ó��
		public void actionPerformed(ActionEvent ae){
			Object obj = ae.getSource();
			eDay = chYear.getSelectedItem() + "-" + chMonth.getSelectedItem() + "-" + chDate.getSelectedItem();

			if ( obj == btn_find)			// ��ȸ ��ư Ŭ���Ҷ�(���ڸ� �����Ҷ��� ���ϱ��
			{					// ȭ���� ��� ���� ������ �ٸ� �Ŀ��� ���������� �����Ƿ�
				DB_conn();			// �������� ����������� ���ŵ� �����ִ��� ��ȸ�Ҷ�

				String sql = "Select * from Haedock_table where ��¥ like '" + eDay +"'";

				DB_search(sql);
			}

			if (obj == btn_modify)			// ���� ��ư Ŭ���Ҷ�
			{
				DB_conn();	

				String sql = "Select * from Haedock_table where ��¥ like '" + eDay +"'";				
				DB_search(sql);

				DB_close();

				// ������ �Է� Ȱ��ȭ
				txt_work.setEnabled(true);
				txt_tang.setEnabled(true);

				// ���� ��ư Ȱ��ȭ ������ ��ư ��� ��Ȱ��ȭ
				btn_save.setEnabled(true);
				btn_modify.setEnabled(false);
				btn_find.setEnabled(false);
				btn_today.setEnabled(false);
				btn_exit.setEnabled(false);

			}
			else if (obj == btn_save)		// ���� ��ư Ŭ���Ҷ�
			{
					DB_conn();
					
					String sql = "Update Haedock_table set ����='" + txt_work.getText();
					sql += "', ����='" + txt_tang.getText();
					sql += "' where ��¥ like '" + eDay + "'";

					DB_modify(sql);

					DB_close(); 

					// ������ �Է� ��Ȱ��ȭ
					txt_work.setEnabled(false);
					txt_tang.setEnabled(false);
					
					// ���� ��ư ��Ȱ��ȭ ������ ��ư ��� Ȱ��ȭ
					btn_save.setEnabled(false);
					btn_find.setEnabled(true);
					btn_modify.setEnabled(true);
					btn_today.setEnabled(true);
					btn_exit.setEnabled(true);
			}
			else if (obj == btn_today)			// ���� ��ư Ŭ���Ҷ� �����ϱ�
			{
				DB_conn();

				formatter = new SimpleDateFormat("yyyy-MM-dd");
				eDay = formatter.format(date);		// ���� ��¥��..

				String sql = "Select * from Haedock_table where ��¥ like '" + eDay +"'";

				DB_search(sql);

				DB_close();
				
				chYear.select(eYear);
				chMonth.select(eMonth);
				chDate.select(eDate);

			}
			else if (obj == btn_exit)		// ���� ��ư Ŭ���Ҷ�
			{
				System.exit(0);
			}
			else if (obj == btn_workdelayModify)	// ������ ������ư��
			{
				// ������ ������ �ҷ�����  �����Ϸ��� ���� �ٸ� ����� ���������� �����Ƿ�
				DB_workdelaySearch();			

				btn_workdelaySave.setEnabled(true);
				txt_workdelay.setEnabled(true);
				btn_workdelayModify.setEnabled(false);
			}
			else if (obj == btn_workdelaySave)		// ������ �����ư��
			{
				DB_workdelaySave();

				btn_workdelaySave.setEnabled(false);
				txt_workdelay.setEnabled(false);
				btn_workdelayModify.setEnabled(true);
			}

		} // ActionEvent


		// �����ͺ��̽� ����
		public void DB_conn(){
			try
			{
				Class.forName(driver);
				con = DriverManager.getConnection(url, "master", "java");
				stmt = con.createStatement();				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		} // DB_conn

		// �˻�ó��
		public void DB_search(String sql){
			try
			{
				rs = stmt.executeQuery(sql);

				// �ʵ尪�����ֱ�
				if (rs.next())		// ������ ���ڵ� ���� �ϳ� �˻��ǹǷ� while�� �ƴ� if�� ���
				{	
					txt_work.setText(rs.getObject(2) + "");	// ù��° �ʵ�� ��¥�̹Ƿ� �ι�°����
					txt_tang.setText(rs.getObject(3) + "");
					if(rs != null) rs.close();

					DB_close();
				}
				
				else 	// ��ȸ�� ��¥�� ������ �����Ͱ� ���� ������ �����ְ� �����ʵ忡 "Ȯ�� :" �������
				{	// ���� ������ ������ ����� "Ȯ��:"�� ���� �̸� ����
					try
					{
						rs.close();

						// ��ȸ ��¥ ���̺� �Է��ϱ�
						String insert = "Insert into Haedock_table (��¥, ����, ����) values ";
						insert += "('" +eDay+ "' , 'Ȯ�� : ' , '')";

						stmt.executeUpdate(insert);

						DB_close();

						txt_work.setText("����� �����Ͱ� �����ϴ�. ������ư Ŭ�� �� �Է��ϼ���");
						txt_tang.setText("����� �����Ͱ� �����ϴ�. ������ư Ŭ�� �� �Է��ϼ���");
					}
					catch (Exception e)
					{
						System.out.println(e.getMessage());
					}

				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}					
		} // DB_search

		// �����ͺ��̽� �ʵ� ���� ���� ����
		public void DB_modify(String sql){
			try
			{	
				stmt.executeUpdate(sql);				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}			
		} // DB_modify

		// �����ͺ��̽� ���� ����
		public void DB_close(){
			try
			{
				if(stmt != null) stmt.close();
				if(con !=null) con.close();
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		} // DB_close

		// ������ ������ �ҷ�����
		public void DB_workdelaySearch(){
			try
			{
				Class.forName(driver);
				con = DriverManager.getConnection(url, "master", "java");
				stmt = con.createStatement();
				
				String sql_delay = "Select * from data";
				rs = stmt.executeQuery(sql_delay);
				
				while (rs.next())		
				{	
					txt_workdelay.setText(rs.getObject(2) + "");
				}

				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con !=null) con.close();				
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		} // DB_workdelaySearch

		// ������ ������ �����ϱ�
		public void DB_workdelaySave(){
			try
			{
				Class.forName(driver);
				con = DriverManager.getConnection(url,"master", "java");
				stmt = con.createStatement();

				String sql_workdelay = "Update data set ����='" + txt_workdelay.getText();
				sql_workdelay += "' where ���='���'";

				stmt.executeUpdate(sql_workdelay);
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
			finally
			{
				try
				{
					if(stmt != null) stmt.close();
					if(con != null) con.close();
				}
				catch (Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}

	// ���� �޼ҵ�
	public static void main(String[] args){
		Gui1 g = new Gui1("�������� ver 1.0");
		g.setDefaultCloseOperation(EXIT_ON_CLOSE);
	} // main
} // Gui1
