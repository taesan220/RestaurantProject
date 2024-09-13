import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import DAODTO.AnorderDAO;
import DAODTO.MenuDAO;
import DAODTO.MenuDTO;
public class MenuGui extends JPanel implements ActionListener {
	Main main = new Main();

	public int tablecode = main.tablecode;

	public JPanel menuall; // 전체 패널

	JScrollPane jscrollpane; // ---------------------- 주문 내역 스크롤 패널
	JPanel menubill; // 주문 내역 패널

	JTable tablebill;

	DefaultTableColumnModel columnmodel; // 테이블 열 모델
	TableColumn column1, column2, column3; // 테이블 열 모델 열

	DefaultTableModel model;

	Vector vectordata = new Vector();

	JLabel lbprice, lbsum, lbwon;

	JButton btnorder, btndelete;

	JPanel showpanel; // --------------------------- 메뉴 표시 패널

	CardLayout menucard; // 메뉴 카드 레이아웃

	JPanel showkindofmenu; // 메뉴 종류 패널

	JButton btnchicken, btnhamburger, btndessert, btndrink, btnsetmenu,
			btnbackmain; // 메뉴 버튼

	String kind;

	int showorder; // 주문 추가 처리 상태
	// ===============================================Showmenu

	JPanel contentall, contentshow, contentbackmenu; // 전체 패널, 표시 패널, 돌아가기 메뉴 패널

	ScrollPane scrollcontent;

	JPanel[] content;
	JButton[] btnshowcontent;
	JLabel[] lbcontentname;
	JLabel lbcontentprice;

	JButton btnbackmenu;

	ImageIcon imgcontent;

	String query;
	String name;
	int i;
	int j;
	/*
	 * int n; int total;
	 */

	// ================================================SelectFrame

	JFrame selectframe;

	JPanel selectmenuall, selectmenu, selectorder;

	JLabel lbselectimage, lbselectname, lbselectmeterial, lbselectcalorie, lbselectprice;
	JLabel lbselectwon, lbselectamount, lbselectmultiplication;
	JButton btnselectorder, btnselectcancle, btnselectup, btnselectdown;
	JTextArea txtexplain;
	TextField txtprice, txtamount;
	ImageIcon imgselect;

	int num;
	int amount;
	int number;
	int price;
	int order;
	String selectname;

	//================================================================
	//==================================================================메소드 정의
	public MenuGui() {

		menucard = new CardLayout();

		menuall = new JPanel(null);
		menubill = new JPanel(null);
		showpanel = new JPanel();
		showpanel.setLayout(menucard);
		showkindofmenu = new JPanel(null);
		showpanel.add(showkindofmenu, "1"); // --------------------------------card 1번 패널

		// 버튼들
		btnorder = new JButton("주문");
		btndelete = new JButton("삭제");
		btnchicken = new JButton("치킨");
		btnhamburger = new JButton("햄버거");
		btndessert = new JButton("디저트");
		btndrink = new JButton("음료");
		btnsetmenu = new JButton("세트메뉴");
		btnbackmain = new JButton("메인으로");

		// 레이블들
		lbsum = new JLabel("합계");
		lbprice = new JLabel("      0");
		lbwon = new JLabel("원");

		// 테이블 관련 설정
		String[] title = {"상품명", "수량", "합계"};

		model = new DefaultTableModel(title, 30) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		}; // public boolean isCellEditable(int rowIndex, int mColIndex){return false;}는 셀 편집 불가능 설정입니다.
		columnmodel = new DefaultTableColumnModel(); // 열 모델 설정

		tablebill = new JTable(model, columnmodel); // DefaultTableModel, DefaultTableColumnModel

		//--------------------------------------------------------------------------
		column1 = new TableColumn(0, 110); // 열 너비 설정
		column1.setHeaderValue("상품명"); // 열 헤더 제목 설정
		column2 = new TableColumn(1, 2);
		column2.setHeaderValue("수량");
		column3 = new TableColumn(2, 10);
		column3.setHeaderValue("합계");
		columnmodel.addColumn(column1);
		columnmodel.addColumn(column2);
		columnmodel.addColumn(column3);
		//--------------------------------------------------------------------------

		jscrollpane = new JScrollPane(tablebill);

		menuall.setBackground(Color.red);
		menuall.add(menubill); // 메뉴 패널 추가
		menubill.setBounds(0, 0, 250, 463);
		menubill.setBackground(Color.yellow);
		showpanel.setBounds(250, 0, 635, 463);

		menuall.add(showpanel);

		showkindofmenu.setBounds(250, 0, 635, 463);
		//showkindofmenu.setBackground(Color.YELLOW);
		// -------------------------------------------------주문 패널
		menubill.add(jscrollpane);
		jscrollpane.setBounds(0, 0, 250, 390);
		menubill.add(lbsum); // 합계
		lbsum.setBounds(150, 400, 120, 20);
		menubill.add(lbprice); // 가격
		lbprice.setBounds(180, 400, 50, 20);
		menubill.add(lbwon); // 원
		lbwon.setBounds(230, 400, 30, 20);

		menubill.add(btndelete);
		btndelete.setBounds(0, 432, 125, 30);
		menubill.add(btnorder); // =====================주문 버튼
		btnorder.setBounds(125, 432, 125, 30);
		// --------------------------------------------------주문 패널
		showkindofmenu.add(btnchicken);
		btnchicken.setBounds(360, 70, 110, 110);
		btnchicken.addActionListener(this);
		showkindofmenu.add(btnhamburger);
		btnhamburger.setBounds(160, 70, 110, 110);
		btnhamburger.addActionListener(this);
		showkindofmenu.add(btndessert);
		btndessert.setBounds(130, 250, 90, 90);
		btndessert.addActionListener(this);
		showkindofmenu.add(btndrink);
		btndrink.setBounds(275, 250, 90, 90);
		btndrink.addActionListener(this);
		showkindofmenu.add(btnsetmenu);
		btnsetmenu.setBounds(420, 250, 90, 90);
		btnsetmenu.addActionListener(this);
		showkindofmenu.add(btnbackmain); // ===========메인으로 버튼
		btnbackmain.setBounds(525, 425, 100, 30);
		btnbackmain.addActionListener(this);

		//--------
		btndelete.addActionListener(this);
		btnorder.addActionListener(this);

		menucard.show(showpanel, "1"); // ---------------------1번 카드로 표시
		// showkindofpanel이 기본적으로 표시됩니다.

	}

	// ****************************************************************Showmenu
	public void Showmenu(String query, String kind, int layoutwidth, int layouthight,
						 String imglocation, int imgwidth, int imghight) {

		System.out.println(kind);

		contentall = new JPanel(new BorderLayout());
		showpanel.add(contentall, "2"); // --------------------------------card 2번 패널
		contentshow = new JPanel();
		contentshow.setLayout(new GridLayout(layoutwidth, layouthight));

		contentbackmenu = new JPanel();
		btnbackmenu = new JButton("메인으로");
		contentbackmenu.add(btnbackmenu);
		btnbackmenu.addActionListener(this);

		scrollcontent = new ScrollPane();
		scrollcontent.add(contentshow);

		contentall.add(scrollcontent, "Center");
		contentall.add(contentbackmenu, "South");

		MenuDAO menudao = new MenuDAO();
		menudao.contentselect(query, kind, name); // -------------------데이터베이스 조회

		i = menudao.i;

		content = new JPanel[i]; // 패널 배열
		btnshowcontent = new JButton[i]; // 버튼 배열
		lbcontentname = new JLabel[i]; // 레이블 배열
		lbcontentprice = new JLabel();

		ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();

		list = menudao.contentselect(query, kind, name);

		for (MenuDTO dto : list) {

			if (j < i) {

				content[j] = new JPanel(new BorderLayout());
				content[j].setBackground(Color.YELLOW);

				contentshow.add(content[j]);

				lbcontentname[j] = new JLabel(dto.getName());
				lbcontentprice = new JLabel(Integer.toString(dto.getPrice()));

				imgcontent = new ImageIcon(
						"image/"
								+ imglocation + "/" + dto.getName() + ".jpg");
				imgcontent = new ImageIcon(imgcontent.getImage()
						.getScaledInstance(imgwidth, imghight,
								Image.SCALE_REPLICATE));

				btnshowcontent[j] = new JButton(imgcontent);
				btnshowcontent[j].addActionListener(this);

				content[j].add(btnshowcontent[j], "North");
				content[j].add(lbcontentname[j], "Center");
				content[j].add(lbcontentprice, "South");

				j++;

				/*
				 * System.out.println(dto.getName());
				 * System.out.println(dto.getPrice());
				 */

			}

		}

		j = 0;

	}

	public void SelectFrame(String query, String name, int imagewidth,
							int imageheight, int x, int y) {


		selectframe = new JFrame("주문 선택");
		selectframe.setSize(500, 350);

		selectmenuall = new JPanel(null);
		selectmenu = new JPanel(null);
		selectorder = new JPanel(null);

		selectmenuall.add(selectmenu);
		selectmenu.setBounds(0, 0, 500, 250);
		selectmenuall.add(selectorder);
		selectorder.setBounds(0, 250, 500, 100);

		// -------------------이미지 및 텍스트 설정

		imgselect = new ImageIcon("image/"
				+ kind + "/" + name + ".jpg");
		imgselect = new ImageIcon(imgselect.getImage().getScaledInstance(imagewidth, imageheight, Image.SCALE_REPLICATE));

		lbselectimage = new JLabel(imgselect);
		lbselectname = new JLabel();
		lbselectcalorie = new JLabel();
		txtexplain = new JTextArea(250, 50);
		lbselectmeterial = new JLabel();
		lbselectprice = new JLabel();


//=================================================================database
		query = "SELECT * FROM menu WHERE kind=? AND name=?";
		MenuDAO menudao = new MenuDAO();
		menudao.contentselect(query, kind, name); //----------------------------데이터베이스 1
		ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
		list = menudao.contentselect(query, kind, name); //---------------------데이터베이스 2
//	MenuDTO dto = new MenuDTO();
		for (MenuDTO dto : list) {

			selectname = dto.getName();
			lbselectname.setText(name);
			lbselectmeterial.setText(dto.getMeterial());
			txtexplain.setText(dto.getExplain());
			lbselectcalorie.setText(Integer.toString(dto.getCalorie()));
			lbselectcalorie.setForeground(Color.GREEN);
			price = dto.getPrice();

			lbselectprice.setText(Integer.toString(price) + " 원");
		}
//==========================================================================
//-------------------------------------------------------------주문 패널
		selectmenu.add(lbselectimage);
		lbselectimage.setBounds(x, y, imagewidth, imageheight);
		selectmenu.add(lbselectname);
		lbselectname.setBounds(300, 30, 150, 20);
		selectmenu.add(lbselectmeterial);
		lbselectmeterial.setBounds(250, 170, 200, 20);
		selectmenu.add(txtexplain);
		txtexplain.setLineWrap(true); //----텍스트 입력에 줄 바꿈을 적용합니다. 화면에 맞게 표시됩니다.
		txtexplain.setBounds(250, 70, 200, 90);
		txtexplain.setEditable(false);


		selectmenu.add(lbselectprice);
		lbselectprice.setBounds(400, 210, 80, 20);
//------------------------------------------------------------주문 패널
		lbselectwon = new JLabel("원");
		lbselectmultiplication = new JLabel("x");
		lbselectamount = new JLabel("개");

		txtprice = new TextField();
		txtprice.setText(Integer.toString(price));
		txtprice.setEditable(false);

		amount = 1;
		txtamount = new TextField();
		txtamount.setText(Integer.toString(amount));
		txtamount.setEditable(false);


		btnselectup = new JButton("+");
		btnselectdown = new JButton("-");

		btnselectcancle = new JButton("취소");
		btnselectorder = new JButton("주문 추가");

		selectorder.add(txtprice);//-------------------가격 입력 필드
		txtprice.setBounds(20, 20, 50, 20);

		selectorder.add(lbselectwon);//------------------------원 레이블
		lbselectwon.setBounds(75, 20, 20, 20);
		selectorder.add(lbselectmultiplication);//-----------------곱하기 레이블
		lbselectmultiplication.setBounds(105, 20, 20, 20);
		selectorder.add(txtamount);//------------수량 입력 필드
		txtamount.setBounds(135, 20, 20, 20);
		selectorder.add(lbselectamount);//-------------수량 레이블
		lbselectamount.setBounds(160, 20, 25, 20);
		selectorder.add(btnselectup);
		btnselectup.setBounds(195, 10, 42, 20);//-------증가 버튼

		selectorder.add(btnselectdown);
		btnselectdown.setBounds(195, 30, 42, 20);//-------감소 버튼

		selectorder.add(btnselectorder);
		btnselectorder.setBounds(310, 17, 110, 30);//---------주문 추가 버튼


		selectorder.add(btnselectcancle);
		btnselectcancle.setBounds(420, 17, 60, 30);//--------------취소 버튼
		//=========================================버튼 이벤트

		btnselectcancle.addActionListener(this);
		btnselectorder.addActionListener(this);
		btnselectup.addActionListener(this);
		btnselectdown.addActionListener(this);
		txtamount.getText().toString();

		selectframe.add(selectmenuall);
		selectframe.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnhamburger) {
			kind = "hamburger";
			query = "SELECT * FROM menu where kind=?";
			Showmenu(query, kind, 0, 4, "hamburger", 90, 80);
			menucard.show(showpanel, "2");
		}
		if (e.getSource() == btnchicken) {
			kind = "chicken";
			query = "SELECT * FROM menu WHERE kind=?";
			Showmenu(query, kind, 0, 3, "chicken", 120, 80);
			menucard.show(showpanel, "2");
		}
		if (e.getSource() == btndessert) {
			kind = "dessert";
			query = "SELECT * FROM menu WHERE kind=?";
			Showmenu(query, kind, 0, 4, "dessert", 90, 80);
			menucard.show(showpanel, "2");
		}
		if (e.getSource() == btndrink) {
			kind = "drink";
			query = "SELECT * FROM menu WHERE kind=?";
			Showmenu(query, kind, 0, 4, "drink", 80, 80);
			menucard.show(showpanel, "2");
		}
		if (e.getSource() == btnsetmenu) {
			kind = "hamburgerset";
			query = "SELECT * FROM menu WHERE kind=?";
			Showmenu(query, kind, 0, 3, "hamburgerset", 140, 80);
			menucard.show(showpanel, "2");
		}
		if (e.getSource() == btnbackmenu) {
			menucard.show(showpanel, "1");
			contentall.remove(0);
		}
		for (int v = 0; v < i; v++) {
			if (e.getSource() == btnshowcontent[v]) {
				System.out.println(lbcontentname[v].getText().toString());
				String name = lbcontentname[v].getText().toString();
				if (kind.equals("hamburger")) {
					SelectFrame(query, name, 170, 150, 30, 55);
				} else if (kind.equals("chicken")) {
					SelectFrame(query, name, 190, 150, 35, 55);
				} else if (kind.equals("dessert")) {
					SelectFrame(query, name, 160, 150, 35, 55);
				} else if (kind.equals("drink")) {
					SelectFrame(query, name, 150, 150, 35, 55);
				} else if (kind.equals("hamburgerset")) {
					SelectFrame(query, name, 210, 150, 20, 55);
				}
			}
		}
		if (e.getSource() == btnselectup) {
			amount = Integer.parseInt(txtamount.getText().toString());
			amount = amount + 1;
			txtamount.setText(Integer.toString(amount));
		}
		if (e.getSource() == btnselectdown) {
			if (Integer.parseInt(txtamount.getText().toString()) > 1) {
				amount = Integer.parseInt(txtamount.getText().toString());
				amount = amount - 1;
				txtamount.setText(Integer.toString(amount));
				return;
			}
			if (Integer.parseInt(txtamount.getText().toString()) <= 1) {
				JOptionPane.showMessageDialog(this, "수량은 1보다 작을 수 없습니다.");
				// System.out.println("수량은 1보다 작을 수 없습니다.");
			}
		}
		if (e.getSource() == btnselectcancle) {
			selectframe.dispose();
			System.out.println("취소");
		}
		if (e.getSource() == btnselectorder) {
			order = price * amount;
			model.setValueAt(selectname, number, 0);
			model.setValueAt(amount, number, 1);
			model.setValueAt(order, number, 2);
			number++;
			// int fornumber= number;
			for (int i = 0; i < number; i++) {
				Object totalprice = model.getValueAt(i, 2);
				showorder = showorder + (int) totalprice;
			}
			// showorder = showorder+order;
			lbprice.setText(Integer.toString(showorder));
			showorder = 0;
			model.addColumn(30);
			selectframe.dispose();
		}
		if (e.getSource() == btndelete) {
			int selectline = tablebill.getSelectedRow();
			if (selectline == -1) {
				JOptionPane.showMessageDialog(this, "선택된 행이 없습니다.");
				return;
			}
			int y = tablebill.getSelectedRow();
			int x = tablebill.getSelectedColumn();
			System.out.println(y + "행 " + x + "열");
			// System.out.println(tablebill.getValueAt(y, x));
			Object a = tablebill.getValueAt(y, x);
			// System.out.println(a+"값");
			if (a == null) {
				System.out.println("빈 셀");
			} else {
				number = number - 1;
				System.out.println("빈 셀 제거 완료");
			}
			for (int i = 0; i < number; i++) {
				Object totalprice = model.getValueAt(i, 2);
				showorder = showorder + (int) totalprice;
			}
			lbprice.setText(Integer.toString(showorder));
			showorder = 0;
			// -----------추가
			vectordata.addElement("");
			vectordata.addElement("");
			vectordata.addElement("");
			model.removeRow(selectline);
			model.addRow(vectordata);
		}
		if (e.getSource() == btnorder) {
			String sql = "INSERT INTO Anorder (tablecode, kind, name, amount, anorder, time, `condition`) VALUES (?, ?, ?, ?, ?, SYSDATE(), ?)";
			String[] kindof = new String[number];
			String[] bname = new String[number];
			int[] bamount = new int[number];
			int[] banorder = new int[number];
			int condition = 3; // 상태는 대기
			String kind;
			JOptionPane optionpane = new JOptionPane("주문을 하시겠습니까?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
			int identifyorder = optionpane.showConfirmDialog(null, "주문 하시겠습니까?", "주문 확인", JOptionPane.YES_NO_OPTION);
			if (identifyorder == optionpane.YES_OPTION) {
				System.out.println("주문 확인");
				AnorderDAO anorderdao = new AnorderDAO();
				for (int y = 0; y < number; y++) {
					bname[y] = (String) tablebill.getValueAt(y, 0);
					bamount[y] = (int) tablebill.getValueAt(y, 1);
					banorder[y] = (int) tablebill.getValueAt(y, 2);
					// ------------- 메뉴 종류를 얻어오기 위한 kind의 값을 조회
					name = bname[y];
					MenuDAO menudao = new MenuDAO();
					menudao.fororder(name); // ----------------------------메뉴 1
					ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();
					list = menudao.fororder(name); // ---------------------메뉴 2
					for (MenuDTO dto : list) {
						kindof[y] = dto.getKind();
					}
					name = null;
					// ------------------------
				}
				for (int i = 0; i < number; i++) {
					String name = bname[i];
					int amount = bamount[i];
					int anorder = banorder[i];
					kind = kindof[i];
					anorderdao.insertorder(sql, tablecode, kind, name, amount, anorder, condition);
				}
				if (number == 0) {
					JOptionPane.showMessageDialog(this, "주문할 항목이 없습니다.");
					return;
				} else {
					JOptionPane.showMessageDialog(this, "주문이 완료되었습니다.");
					for (int i = 0; i < number; i++) {
						model.setValueAt("", i, 0);
						model.setValueAt("", i, 1);
						model.setValueAt("", i, 2);
					}
					lbprice.setText(("        0"));
					menucard.show(showpanel, "1");
					number = 0;
					showorder = 0;
				}
			} else if (identifyorder == optionpane.NO_OPTION) {
				System.out.println("주문 취소");
			}
		}
	}
}