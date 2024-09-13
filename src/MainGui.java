import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

// 03년 6월 최종버전 작업본
public class MainGui extends JFrame {
	JPanel mainjpanelall;
	JPanel mainbtnjpanel;
	JPanel mainposterjpanel;
	Button menu, showorder, playgame, evaluation;
	Image imgposter;

	public MainGui() {

		mainjpanelall = new JPanel(null);
		mainposterjpanel = new JPanel() {
			public void paint(Graphics g) {
				imgposter = Toolkit.getDefaultToolkit().getImage(
						"image/eventposter/4월1일.jpg");

				g.drawImage(imgposter, 0, 0, 500, 470, mainposterjpanel);
			}
		};
		mainjpanelall.add(mainposterjpanel);
		mainposterjpanel.setBackground(Color.RED);
		mainposterjpanel.setBounds(0, 0, 500, 500);
		mainbtnjpanel = new JPanel(null);
		mainjpanelall.add(mainbtnjpanel);
		mainbtnjpanel.setBounds(500, 0, 400, 500);

		// 버튼 설정
		menu = new Button("메뉴");
		menu.setBounds(100, 70, 200, 50);
		showorder = new Button("주문 보기");
		showorder.setBounds(100, 160, 200, 50);
		evaluation = new Button("평가하기");
		evaluation.setBounds(100, 250, 200, 50);
		playgame = new Button("게임하기");
		playgame.setBounds(100, 340, 200, 50);

		mainbtnjpanel.add(menu);
		mainbtnjpanel.add(showorder);
		mainbtnjpanel.add(evaluation);
		mainbtnjpanel.add(playgame);
	}
}