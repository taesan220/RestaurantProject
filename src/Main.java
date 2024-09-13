import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class Main {

	static Frame frame;
	static CardLayout card;
	static int tablecode = 1;

	public static void main(String[] args) {

		new BeforeSeting();

		// 프로그램의 메인 프레임을 설정합니다.
		frame = new Frame("메뉴를 선택하여 주세요."); // 한글이 깨지지 않도록 복구

		// CardLayout을 사용하여 화면을 전환할 수 있도록 설정합니다.
		card = new CardLayout();

		frame.setLayout(card);

		// 메인 화면과 메뉴 화면을 추가합니다.
		MainGui maingui = new MainGui();
		MenuGui menugui = new MenuGui();

		frame.add(maingui.mainjpanelall, "1"); // 메인 GUI 화면 추가
		frame.add(menugui.menuall, "2"); // 메뉴 GUI 화면 추가

		// 게임 화면을 추가합니다.
		Game game = new Game();
		frame.add(game.panelgameall, "5");

		// 프레임의 크기와 위치를 설정하고 화면에 표시합니다.
		frame.setSize(900, 500);
		frame.setLocation(360, 300);
		frame.setVisible(true);

		// 첫 화면으로 메인 화면을 표시합니다.
		card.show(frame, "1");

		// 메뉴 버튼을 눌렀을 때 메뉴 화면을 표시하도록 설정합니다.
		maingui.menu.addActionListener(new ActionListener() // 메뉴 버튼
		{
			public void actionPerformed(ActionEvent arg0) {
				card.show(frame, "2");
			}
		});

		// 주문 보기 버튼을 눌렀을 때 주문 화면을 표시하도록 설정합니다.
		maingui.showorder.addActionListener(new ActionListener() // 주문 보기 버튼
		{
			public void actionPerformed(ActionEvent e) {
				String query = "SELECT * FROM anorder WHERE `condition` >= ? AND tablecode = " + tablecode + " ORDER BY `condition`";

				//String query = "SELECT * FROM anorder WHERE condition >=? AND tablecode=" + tablecode + " ORDER BY `condition`";
				int condition = 2;
				showorder(query, condition);
				card.show(frame, "3");
			}
		});

		// 메뉴 화면에서 뒤로 가기 버튼을 눌렀을 때 메인 화면으로 돌아가도록 설정합니다.
		menugui.btnbackmain.addActionListener(new ActionListener() // 뒤로 가기 버튼
		{
			public void actionPerformed(ActionEvent arg0) {
				card.show(frame, "1");
			}
		});

		// 평가 버튼을 눌렀을 때 평가 화면을 표시하도록 설정합니다.
		maingui.evaluation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String query = "SELECT DISTINCT name, kind FROM anorder WHERE tablecode =" + tablecode + " and `condition` >=2";
				showevaluation(query, tablecode);
				card.show(frame, "4");
			}
		});

		// 게임 플레이 버튼을 눌렀을 때 게임 화면을 표시하도록 설정합니다.
		maingui.playgame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				card.show(frame, "5");
			}
		});

		// 게임 화면에서 뒤로 가기 버튼을 눌렀을 때 메인 화면으로 돌아가도록 설정합니다.
		game.btnbackmain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(frame, "1");
			}
		});

		// 윈도우 종료 이벤트를 처리합니다.
		frame.addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}

			public void windowOpened(WindowEvent arg0) {
			}

			public void windowIconified(WindowEvent arg0) {
			}

			public void windowDeiconified(WindowEvent arg0) {
			}

			public void windowDeactivated(WindowEvent arg0) {
			}

			public void windowClosed(WindowEvent arg0) {
			}

			public void windowActivated(WindowEvent arg0) {
			}
		});
	}

	// 주문 화면을 표시하는 메서드입니다.
	public static void showorder(String query, int condition) {

		ShoworderGui showordergui = new ShoworderGui(query, condition);
		frame.add(showordergui.showorderall, "3");

		// 뒤로 가기 버튼을 눌렀을 때 메인 화면으로 돌아가도록 설정합니다.
		showordergui.btnbackmain.addActionListener(new ActionListener() // 뒤로 가기 버튼
		{
			public void actionPerformed(ActionEvent e) {
				card.show(frame, "1");
			}
		});

		// 추가 버튼을 눌렀을 때 메뉴 화면을 표시하도록 설정합니다.
		showordergui.btnadd.addActionListener(new ActionListener() // 추가 버튼
		{
			public void actionPerformed(ActionEvent arg0) {
				card.show(frame, "2");
			}
		});
	}

	// 평가 화면을 표시하는 메서드입니다.
	public static void showevaluation(String query, int tablecode) {

		EvaluationGui evaluationgui = new EvaluationGui(query, tablecode);
		frame.add(evaluationgui.panelevalluation, "4");

		// 뒤로 가기 버튼을 눌렀을 때 메인 화면으로 돌아가도록 설정합니다.
		evaluationgui.btnbackmain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(frame, "1");
			}
		});
	}

}