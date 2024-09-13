import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Scrollbar;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAODTO.GameDAO;
import DAODTO.GameDTO;

public class Game extends JPanel implements ActionListener {

	JPanel panelgameall, panelgamemain;

	JPanel panelRPS, panelupdown, panelsonbyoungho;

	JButton btnRPS, btnupdown, btnsonbyoungho, btnbackmain;
	ImageIcon imgRPS, imgupdown, imgsonbyoungho;

	CardLayout cardgame;

	// ======================================================================1단계
// 버튼 및 레이블 선언

	JButton btnstartRPS, btnrock, btnpaper, btnscissors, btnbackgamemain1;
	JLabel lbyouname, lbpcname, lbyousayimg;
	JLabel lbyousay;
	JLabel lbpcsayimg;
	JLabel lbpcsay;
	JLabel lbyouimg;
	JLabel lbpcimg;
	ImageIcon imgyousay, imgpcsay;

// ===================================================2단계 이미지 아이콘 및 변수

	ImageIcon imgyou, imgyouscissors, imgyourock, imgyoupaper;
	ImageIcon imgpc, imgpcscissors, imgpcrock, imgpcpaper;
	int pcnum, younum;

	Random random;
	Boolean start1;

	String answer = "X";

	JLabel lbimg, lbshownum, lbhint, lbresult;

	JButton btnstart2, btnidentify, btnplus, btnminus, btnbigplus, btnbigminus,
			btnbackgamemain2;

	// ===================================================3단계 이미지 및 패널 설정
	ImageIcon imgbefore, imgup, imgdown;
	boolean start2 = false;
	int randomnum, questionnum, n, shownum, hintup, hintdown;

	CardLayout cardson;
	CardLayout cardgamein;
	JPanel panelsonbyounghoall, panelsonmain, panelsonplay, panelsonstart,
			panelgamein, panelsonquestion;

	JLabel lbson, lbsay, lbcomputersay;

	JButton btnstart1, btnbackgamemain3, btnup, btndown;

	JButton btnstart3;
	JButton btnbackgamemain4, btnnextquestion;
	TextArea txtarea;
	TextField txtfield;
	String sonquestion;

	ImageIcon[] imgson, imgfive, imgfour, imgthree, imgtwo, imgwon;

	ImageIcon imgsay;

	int personnum = 1;
	JButton[] btnhand;
	JLabel[] lbhand, lbname;
	JLabel[] lbsonname;

	int[] sonnum, sonfinger;
	String[] sonquestions, word;

	int[] handfinger;

// ===================================================

	public Game() {

		cardgame = new CardLayout();

		panelgameall = new JPanel(cardgame);
		panelgamemain = new JPanel(null);
		panelgameall.add(panelgamemain, "1");

		panelRPS = new JPanel(null);
		panelupdown = new JPanel(null);
		panelsonbyoungho = new JPanel(new BorderLayout());

		panelgameall.add(panelRPS, "2");
		panelgameall.add(panelupdown, "3");
		panelgameall.add(panelsonbyoungho, "4");

		imgRPS = new ImageIcon(
				"image/game/mainRPS.jpg");
		imgRPS = new ImageIcon(imgRPS.getImage().getScaledInstance(150, 250,
				Image.SCALE_REPLICATE));
		imgupdown = new ImageIcon(
				"image/game/mainup.jpg");
		imgupdown = new ImageIcon(imgupdown.getImage().getScaledInstance(150,
				250, Image.SCALE_REPLICATE));
		imgsonbyoungho = new ImageIcon(
				"image/game/mainson.jpg");
		imgsonbyoungho = new ImageIcon(imgsonbyoungho.getImage()
				.getScaledInstance(150, 250, Image.SCALE_REPLICATE));

		btnRPS = new JButton(imgRPS);
		btnupdown = new JButton(imgupdown);
		btnsonbyoungho = new JButton(imgsonbyoungho);
		btnbackmain = new JButton("뒤로 가기");

		panelgamemain.add(btnRPS);
		btnRPS.setBounds(145, 120, 150, 250);
		panelgamemain.add(btnupdown);
		btnupdown.setBounds(370, 120, 150, 250);
		panelgamemain.add(btnsonbyoungho);
		btnsonbyoungho.setBounds(595, 120, 150, 250);

		panelgamemain.add(btnbackmain);
		btnbackmain.setBounds(760, 428, 120, 30);

		btnRPS.addActionListener(this);
		btnupdown.addActionListener(this);
		btnsonbyoungho.addActionListener(this);

		cardgame.show(panelgameall, "1");

		// ========================================================================================게임 설정
		random = new Random();

		start1 = false;

		btnstartRPS = new JButton("게임 시작");

		btnscissors = new JButton("가위");
		btnrock = new JButton("바위");
		btnpaper = new JButton("보");
		btnbackgamemain1 = new JButton("뒤로 가기");

		imgyou = new ImageIcon(
				"image/game/youbefore.jpg");
		imgyou = new ImageIcon(imgyou.getImage().getScaledInstance(200, 200,
				Image.SCALE_REPLICATE));
		imgpc = new ImageIcon(
				"image/game/pcbefore.jpg");
		imgpc = new ImageIcon(imgpc.getImage().getScaledInstance(200, 200,
				Image.SCALE_REPLICATE));

		imgyouscissors = new ImageIcon(
				"image/game/you가위.jpg");
		imgyouscissors = new ImageIcon(imgyouscissors.getImage()
				.getScaledInstance(200, 200, Image.SCALE_REPLICATE));
		imgyourock = new ImageIcon(
				"image/game/you주먹.jpg");
		imgyourock = new ImageIcon(imgyourock.getImage().getScaledInstance(200,
				200, Image.SCALE_REPLICATE));
		imgyoupaper = new ImageIcon(
				"image/game/you보자기.jpg");
		imgyoupaper = new ImageIcon(imgyoupaper.getImage().getScaledInstance(
				200, 200, Image.SCALE_REPLICATE));

		imgpcscissors = new ImageIcon(
				"image/game/pc가위.jpg");
		imgpcscissors = new ImageIcon(imgpcscissors.getImage()
				.getScaledInstance(200, 200, Image.SCALE_REPLICATE));
		imgpcrock = new ImageIcon(
				"image/game/pc주먹.jpg");
		imgpcrock = new ImageIcon(imgpcrock.getImage().getScaledInstance(200,
				200, Image.SCALE_REPLICATE));
		imgpcpaper = new ImageIcon(
				"image/game/pc보자기.jpg");
		imgpcpaper = new ImageIcon(imgpcpaper.getImage().getScaledInstance(200,
				200, Image.SCALE_REPLICATE));

		imgyousay = new ImageIcon(
				"image/game/you말풍선.PNG");
		imgyousay = new ImageIcon(imgyousay.getImage().getScaledInstance(180,
				70, Image.SCALE_REPLICATE));
		imgpcsay = new ImageIcon(
				"image/game/pc말풍선.PNG");
		imgpcsay = new ImageIcon(imgpcsay.getImage().getScaledInstance(180, 70,
				Image.SCALE_REPLICATE));

		lbyouname = new JLabel("YOU");
		lbyouname.setFont(new Font("YOU", Font.BOLD, 25));
		lbpcname = new JLabel("PC");
		lbpcname.setFont(new Font("PC", Font.BOLD, 25));
		lbyousayimg = new JLabel(imgyousay);
		lbpcsayimg = new JLabel(imgpcsay);
		lbyousay = new JLabel("너 뭐 낼꺼야?");
		lbpcsay = new JLabel("안가르쳐 주지~~");
		lbyouimg = new JLabel(imgyou);
		lbpcimg = new JLabel(imgpc);

		panelRPS.add(lbyousay);
		lbyousay.setBounds(375, 115, 200, 20);
		panelRPS.add(lbpcsay);
		lbpcsay.setBounds(400, 255, 200, 20);

		panelRPS.add(lbyouname);
		lbyouname.setBounds(220, 50, 100, 50);
		panelRPS.add(lbpcname);
		lbpcname.setBounds(630, 50, 100, 50);

		panelRPS.add(lbyousayimg);
		lbyousayimg.setBounds(345, 90, 180, 70);
		panelRPS.add(lbpcsayimg);
		lbpcsayimg.setBounds(372, 230, 180, 70);

		panelRPS.add(lbyouimg);
		lbyouimg.setBounds(150, 100, 200, 200);
		panelRPS.add(lbpcimg);
		lbpcimg.setBounds(550, 100, 200, 200);

		panelRPS.add(btnstartRPS);
		btnstartRPS.setBounds(390, 390, 120, 40);
		panelRPS.add(btnscissors);
		btnscissors.setBounds(350, 335, 60, 40);
		panelRPS.add(btnrock);
		btnrock.setBounds(420, 335, 60, 40);
		panelRPS.add(btnpaper);
		btnpaper.setBounds(490, 335, 60, 40);
		panelRPS.add(btnbackgamemain1);
		btnbackgamemain1.setBounds(760, 420, 120, 30);

		btnbackgamemain1.addActionListener(this);
		btnstartRPS.addActionListener(this);
		btnrock.addActionListener(this);
		btnscissors.addActionListener(this);

		btnpaper.addActionListener(this);

		// =============================================================================================2단계
		// 버튼 관련

		random = new Random();

		start2 = false;

		shownum = 50;
		hintup = 0;
		hintdown = 100;

		imgbefore = new ImageIcon(
				"image/game/test.jpg");
		imgbefore = new ImageIcon(imgbefore.getImage().getScaledInstance(75,
				90, Image.SCALE_REPLICATE));
		imgup = new ImageIcon(
				"image/game/up.jpg");
		imgup = new ImageIcon(imgup.getImage().getScaledInstance(75, 90,
				Image.SCALE_REPLICATE));
		imgdown = new ImageIcon(
				"image/game/down.jpg");
		imgdown = new ImageIcon(imgdown.getImage().getScaledInstance(75, 90,
				Image.SCALE_REPLICATE));

		lbresult = new JLabel("?");
		lbresult.setFont(new Font("맑은 고딕", Font.BOLD, 150));

		lbimg = new JLabel(imgbefore);

		btnstart2 = new JButton("시작하기");

		lbhint = new JLabel("0 < X < 100");
		lbhint.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lbshownum = new JLabel("50");

		btnplus = new JButton("+1");
		btnbigplus = new JButton("+10");
		btnminus = new JButton("-1");
		btnbigminus = new JButton("-10");
		btnidentify = new JButton("확인하기");
		btnbackgamemain2 = new JButton("뒤로가기");

		panelupdown.add(lbresult);
		lbresult.setBounds(100, 115, 300, 200);
		panelupdown.add(lbimg);
		lbimg.setBounds(330, 185, 75, 90);

		panelupdown.add(btnstart2);
		btnstart2.setBounds(205, 335, 100, 40);

		panelupdown.add(lbhint);
		lbhint.setBounds(605, 160, 150, 20);

		panelupdown.add(lbshownum);
		lbshownum.setBounds(608, 220, 80, 50);
		lbshownum.setForeground(Color.RED);
		lbshownum.setFont(new Font(Integer.toString(shownum), Font.BOLD, 50));

		panelupdown.add(btnbigplus);
		btnbigplus.setBounds(480, 300, 70, 20);
		panelupdown.add(btnplus);
		btnplus.setBounds(560, 300, 70, 20);
		panelupdown.add(btnminus);
		btnminus.setBounds(640, 300, 70, 20);
		panelupdown.add(btnbigminus);
		btnbigminus.setBounds(720, 300, 70, 20);
		panelupdown.add(btnbackgamemain2);

		panelupdown.add(btnidentify);
		btnidentify.setBounds(590, 335, 100, 40);

		btnbackgamemain2.setBounds(760, 428, 120, 30);

		btnbackgamemain2.addActionListener(this);
		btnstart2.addActionListener(this);
		btnplus.addActionListener(this);
		btnbigplus.addActionListener(this);
		btnminus.addActionListener(this);

		btnbigminus.addActionListener(this);
		btnidentify.addActionListener(this);

		// ========================================================================================패널 생성
		// 패널 및 레이아웃 설정

		cardson = new CardLayout();
		cardgamein = new CardLayout();
		panelsonbyounghoall = new JPanel(cardson);
		panelsonmain = new JPanel(null);
		panelsonplay = new JPanel(null);
		panelsonplay.setBackground(Color.LIGHT_GRAY);
		panelgamein = new JPanel(cardgamein);
		panelsonstart = new JPanel(null);
		panelsonstart.setBackground(Color.LIGHT_GRAY);
		panelsonquestion = new JPanel(null);
		panelsonquestion.setBackground(Color.LIGHT_GRAY);

		txtfield = new TextField();
		btnstart1 = new JButton("시작하기");
		btnbackgamemain3 = new JButton("뒤로가기");
		btnup = new JButton("+");
		btndown = new JButton("-");

		btnstart3 = new JButton("게임 시작하기");
		btnbackgamemain4 = new JButton("뒤로가기");
		txtarea = new TextArea(sonquestion, 5, 10, Scrollbar.VERTICAL);
		txtarea.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		txtarea.setEditable(false);
		txtarea.setText("질문에 해당되는 사람은 손가락을 접는 게임 입니다");
		btnnextquestion = new JButton("질문 보기");

		panelsonmain.add(btnup);
		btnup.setBounds(488, 180, 50, 30);
		panelsonmain.add(btndown);
		btndown.setBounds(488, 220, 50, 30);
		panelsonmain.add(btnstart1);
		btnstart1.setBounds(360, 260, 180, 30);
		panelsonmain.add(btnbackgamemain3);
		btnbackgamemain3.setBounds(760, 428, 120, 30);

		// ------------------추가 코드

		panelsonbyoungho.add(panelsonbyounghoall, "Center");

		panelsonbyounghoall.add(panelsonmain, "1");
		panelsonbyounghoall.add(panelsonplay, "2");

		panelsonplay.add(panelgamein);
		panelgamein.setBounds(350, 155, 200, 140);

		panelgamein.add(panelsonstart, "1");
		panelgamein.add(panelsonquestion, "2");

		panelsonplay.add(btnbackgamemain4);
		btnbackgamemain4.setBounds(760, 428, 120, 30);

		panelsonstart.add(btnstart3);
		btnstart3.setBounds(39, 50, 120, 40);

		panelsonquestion.add(txtarea);
		txtarea.setBounds(0, 0, 220, 80);

		panelsonquestion.add(btnnextquestion);
		btnnextquestion.setBounds(39, 89, 120, 40);

		// ------------------------------
		panelsonmain.add(txtfield);
		txtfield.setBounds(360, 180, 120, 70);
		txtfield.setText("  " + Integer.toString(personnum));
		txtfield.setFont(new Font("맑은 고딕", Font.BOLD, 65)); // "맑은 고딕" is a commonly used Korean font.
		txtfield.setEditable(false);
		cardson.show(panelsonbyounghoall, "1");

		System.out.println(4);
		btnhand = new JButton[4];
		lbhand = new JLabel[4];
		lbsonname = new JLabel[4];
		imgfive = new ImageIcon[4];
		imgfour = new ImageIcon[4];
		imgthree = new ImageIcon[4];
		imgtwo = new ImageIcon[4];
		imgwon = new ImageIcon[4];
		imgson = new ImageIcon[4];
		handfinger = new int[4];

		for (int i = 0; i < 4; i++) {

			imgfive[i] = new ImageIcon(
					"image/game/5.jpg");
			imgfive[i] = new ImageIcon(imgfive[i].getImage().getScaledInstance(
					100, 100, Image.SCALE_REPLICATE));
			imgfour[i] = new ImageIcon(
					"image/game/4.jpg");
			imgfour[i] = new ImageIcon(imgfour[i].getImage().getScaledInstance(
					100, 100, Image.SCALE_REPLICATE));
			imgthree[i] = new ImageIcon(
					"image/game/3.jpg");
			imgthree[i] = new ImageIcon(imgthree[i].getImage()
					.getScaledInstance(100, 100, Image.SCALE_REPLICATE));
			imgtwo[i] = new ImageIcon(
					"image/game/2.jpg");
			imgtwo[i] = new ImageIcon(imgtwo[i].getImage().getScaledInstance(
					100, 100, Image.SCALE_REPLICATE));
			imgwon[i] = new ImageIcon(
					"image/game/1.jpg");
			imgwon[i] = new ImageIcon(imgwon[i].getImage().getScaledInstance(
					100, 100, Image.SCALE_REPLICATE));
			imgson[i] = new ImageIcon(
					"image/game/0.jpg");
			imgson[i] = new ImageIcon(imgson[i].getImage().getScaledInstance(
					100, 100, Image.SCALE_REPLICATE));

			btnhand[i] = new JButton("버튼"); // "버튼" means "Button" in Korean.
			lbhand[i] = new JLabel(imgfive[i]);
			lbsonname[i] = new JLabel();
			lbsonname[i].setFont(new Font("맑은 고딕", Font.BOLD, 18)); // "맑은 고딕" is a commonly used Korean font.

			handfinger[i] = 5;
		}

		imgsay = new ImageIcon(
				"image/game/pc_message.png");
		imgsay = new ImageIcon(imgsay.getImage().getScaledInstance(210, 70,
				Image.SCALE_REPLICATE));
		lbsay = new JLabel(imgsay);
		lbcomputersay = new JLabel("안녕하세요. 무엇을 도와드릴까요?");

		panelsonplay.add(lbcomputersay);
		lbcomputersay.setBounds(605, 95, 200, 20);
		panelsonplay.add(lbsay);
		lbsay.setBounds(575, 70, 210, 70);

		lbsonname[0].setText("Player1");
		lbsonname[1].setText("Player2");
		lbsonname[2].setText("Player3");
		lbsonname[3].setText("Player4");

		panelsonplay.add(lbsonname[0]);
		lbsonname[0].setBounds(50, 125, 120, 40);
		panelsonplay.add(lbhand[0]);
		lbhand[0].setBounds(40, 170, 100, 100);
		panelsonplay.add(btnhand[0]);
		btnhand[0].setBounds(30, 285, 120, 40);

		lbcomputersay.setVisible(false);
		lbsay.setVisible(false);

		lbsonname[0].setVisible(false);
		lbhand[0].setVisible(false);
		btnhand[0].setVisible(false);

		panelsonplay.add(lbsonname[1]);
		lbsonname[1].setBounds(470, 10, 120, 40);
		panelsonplay.add(lbhand[1]);
		lbhand[1].setBounds(335, 5, 100, 100);
		panelsonplay.add(btnhand[1]);
		btnhand[1].setBounds(445, 45, 120, 40);

		lbsonname[1].setVisible(false);
		lbhand[1].setVisible(false);
		btnhand[1].setVisible(false);

		panelsonplay.add(lbsonname[2]);
		lbsonname[2].setBounds(720, 125, 120, 40);
		panelsonplay.add(lbhand[2]);
		lbhand[2].setBounds(710, 170, 100, 100);
		panelsonplay.add(btnhand[2]);
		btnhand[2].setBounds(700, 285, 120, 40);

		lbsonname[2].setVisible(false);
		lbhand[2].setVisible(false);
		btnhand[2].setVisible(false);

		panelsonplay.add(lbsonname[3]);
		lbsonname[3].setBounds(470, 360, 120, 40);
		panelsonplay.add(lbhand[3]);
		lbhand[3].setBounds(335, 360, 100, 100);
		panelsonplay.add(btnhand[3]);
		btnhand[3].setBounds(445, 400, 120, 40);

		lbsonname[3].setVisible(false);
		lbhand[3].setVisible(false);
		btnhand[3].setVisible(false);

		GameDAO gamedao = new GameDAO();
		ArrayList<GameDTO> list = new ArrayList<GameDTO>();
		// gamedao.selectgame();
		list = gamedao.selectgame();

		if (list != null && list.size() > 0) {
			questionnum = gamedao.n;

			randomnum = random.nextInt(questionnum) + 1;

			sonnum = new int[questionnum];
			sonfinger = new int[questionnum];
			sonquestions = new String[questionnum];
			word = new String[questionnum];

			for (GameDTO dto : list) {
				sonnum[n] = dto.getNum();
				sonfinger[n] = dto.getFinger();
				sonquestions[n] = dto.getQuestion();
				word[n] = dto.getWord();

				n++;
			}

			btnbackgamemain3.addActionListener(this);
			btnup.addActionListener(this);
			btndown.addActionListener(this);
			btnstart1.addActionListener(this);
			btnbackgamemain4.addActionListener(this);
			btnstart3.addActionListener(this);
			btnnextquestion.addActionListener(this);
			for (int i = 0; i < 4; i++) {
				btnhand[i].addActionListener(this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRPS) {
			cardgame.show(panelgameall, "2");
			younum = 0;
			pcnum = 0;

		} else if (e.getSource() == btnupdown) {
			cardgame.show(panelgameall, "3");
			// updown();

		} else if (e.getSource() == btnsonbyoungho) {
			cardgame.show(panelgameall, "4");
			// sonbyoungho();
		}
		// =============================================================== 게임 진행

		if (e.getSource() == btnbackgamemain1) {
			lbyouimg.setIcon(imgyou);
			lbpcimg.setIcon(imgpc);

			lbyousay.setText("가볍게 이겨주지");
			lbpcsay.setText("쉽게 이기긴 어려울겁니다");
			cardgame.show(panelgameall, "1");
		}

		if (e.getSource() == btnstartRPS) {
			if (start1 == false) {
				if (younum == 0) {
					lbyouimg.setIcon(imgyou);
				}

				if (pcnum == 0) {
					lbpcimg.setIcon(imgpc);
				}

				pcnum = random.nextInt(3) + 1;
				System.out.println(pcnum);
				start1 = true;
				lbyousay.setText("가위 바위 보!");
				lbpcsay.setText("가위 바위 보!");
			} else {
				JOptionPane.showMessageDialog(Main.frame, "게임이 이미 시작되었습니다.");
			}
		}

		if (e.getSource() == btnrock) {
			if (start1 == true) {
				younum = 2;
				lbyouimg.setIcon(imgyourock);
				if (pcnum == 1) {
					lbpcimg.setIcon(imgpcscissors);
					lbyousay.setText("더 배우고 오너라!");
					lbpcsay.setText("흑.. 내가 졌다!");
					JOptionPane.showMessageDialog(Main.frame, "당신이 이겼습니다.");
					pcnum = 0;
					younum = 0;
					start1 = false;
					return;
				} else if (pcnum == 2) {
					lbpcimg.setIcon(imgpcrock);
					lbyousay.setText("운이 좋구만!");
					lbpcsay.setText("기회를 더 드리죠!");
					pcnum = random.nextInt(3) + 1;
				} else if (pcnum == 3) {
					lbpcimg.setIcon(imgpcpaper);
					lbyousay.setText("이렇게 지다니..");
					lbpcsay.setText("한판 더?");
					JOptionPane.showMessageDialog(Main.frame, "당신이 졌습니다.");
					pcnum = 0;
					younum = 0;
					start1 = false;
					return;
				}
			} else {
				JOptionPane.showMessageDialog(Main.frame, "게임을 시작해 주세요");
			}
		}

		if (e.getSource() == btnscissors) {
			if (start1 == true) {
				younum = 1;
				lbyouimg.setIcon(imgyouscissors);
				if (pcnum == 1) {
					lbpcimg.setIcon(imgpcscissors);
					lbyousay.setText("비겼습니다!");
					lbpcsay.setText("비겼습니다!");
					pcnum = random.nextInt(3) + 1;
				} else if (pcnum == 2) {
					lbpcimg.setIcon(imgpcrock);
					lbyousay.setText("졌습니다...");
					lbpcsay.setText("축하합니다!");
					JOptionPane.showMessageDialog(Main.frame, "당신이 졌습니다.");
					pcnum = 0;
					younum = 0;
					start1 = false;
					return;
				} else if (pcnum == 3) {
					lbpcimg.setIcon(imgpcpaper);
					lbyousay.setText("참 쉽죠?");
					lbpcsay.setText("네~ 참 잘했어요");
					JOptionPane.showMessageDialog(Main.frame, "당신이 이겼습니다.");
					pcnum = 0;
					younum = 0;
					start1 = false;
					return;
				}
			} else {
				JOptionPane.showMessageDialog(Main.frame, "게임을 시작해 주세요");
			}
		}

		if (e.getSource() == btnpaper) {
			if (start1 == true) {
				younum = 3;
				lbyouimg.setIcon(imgyoupaper);
				if (pcnum == 1) {
					lbpcimg.setIcon(imgpcscissors);
					lbyousay.setText("졌습니다!");
					lbpcsay.setText("승리한 컴퓨터");
					JOptionPane.showMessageDialog(Main.frame, "당신이 졌습니다.");
					pcnum = 0;
					younum = 0;
					start1 = false;
					return;
				} else if (pcnum == 2) {
					lbpcimg.setIcon(imgpcrock);
					lbyousay.setText("이겼습니다!");
					lbpcsay.setText("컴퓨터는 졌습니다.");
					JOptionPane.showMessageDialog(Main.frame, "당신이 이겼습니다.");
					pcnum = 0;
					younum = 0;
					start1 = false;
					return;
				} else if (pcnum == 3) {
					lbpcimg.setIcon(imgpcpaper);
					lbyousay.setText("비겼습니다!");
					lbpcsay.setText("비겼습니다!");
					pcnum = random.nextInt(3) + 1;
				}
			} else {
				JOptionPane.showMessageDialog(Main.frame, "게임을 시작해 주세요");
			}
		}

		// =======================================================================게임 시작
		// 초기화
		if (e.getSource() == btnbackgamemain2) {
			cardgame.show(panelgameall, "1");

			// cardgame.show(panelgamemain,"1" );

			randomnum = 0;
			lbresult.setText("?");
			lbhint.setText("0 < x < 100");
			lbshownum.setText("50");
			lbimg.setIcon(imgbefore);
			start2 = false;
		}

		if (e.getSource() == btnstart2) {
			if (randomnum == 0) {
				randomnum = random.nextInt(100) + 1;
				System.out.println(randomnum);
				start2 = true;
				hintup = 0;
				hintdown = 100;
				shownum = 50;
				lbresult.setText("?");
				lbhint.setText(hintup + " < " + answer + " < " + hintdown);
				lbshownum.setText(Integer.toString(shownum));
				lbimg.setIcon(imgbefore);
			} else {
				JOptionPane optionpane = new JOptionPane();
				int identifyorder = optionpane.showConfirmDialog(null,
						"현재 게임을 초기화 하시겠습니까? 새로운 게임을 시작합니다.", "게임 초기화?",
						JOptionPane.YES_NO_OPTION);
				if (identifyorder == optionpane.YES_OPTION) {
					randomnum = random.nextInt(100) + 1;
					System.out.println("새로운 정답: " + randomnum);
					hintup = 0;
					hintdown = 100;
					shownum = 50;
					lbhint.setText(hintup + " < " + answer + " < " + hintdown);
					lbshownum.setText(Integer.toString(shownum));
					lbresult.setText("?");
					lbimg.setIcon(imgbefore);
					start2 = true;
				}
			}
		}

		if (e.getSource() == btnplus) {
			if (start2 == true) {
				if (shownum < hintdown - 1) {
					shownum = shownum + 1;
					lbshownum.setText(Integer.toString(shownum));
				}
			} else {
				JOptionPane.showMessageDialog(Main.frame, "게임을 시작해 주세요");
			}
		}

		if (e.getSource() == btnbigplus) {
			if (start2 == true) {
				if (shownum < hintdown - 9) {
					shownum = shownum + 10;
					lbshownum.setText(Integer.toString(shownum));
				}
			} else {
				JOptionPane.showMessageDialog(Main.frame, "게임을 시작해 주세요");
			}
		}

		if (e.getSource() == btnminus) {
			if (start2 == true) {
				if (shownum > hintup + 1) {
					shownum = shownum - 1;
					lbshownum.setText(Integer.toString(shownum));
				} else {
					JOptionPane.showMessageDialog(Main.frame, "게임을 시작해 주세요");
				}
			}
		}
		if (e.getSource() == btnbigminus) {

			if (start2 == true) {
				if (shownum > hintup + 9) {
					shownum = shownum - 10;
					lbshownum.setText(Integer.toString(shownum));
				}
			} else {
				JOptionPane.showMessageDialog(Main.frame, "게임을 시작해 주세요");
			}
		}
		if (e.getSource() == btnidentify) {
			if (start2 == true) {
				if (randomnum == shownum) {
					lbresult.setText(Integer.toString(randomnum));
					JOptionPane.showMessageDialog(Main.frame, "정답은 " + randomnum + "입니다~!!^^");
					randomnum = 0;
					start2 = false;
				} else if (randomnum > shownum) {
					hintup = shownum;
					lbhint.setText(hintup + " < " + answer + " < " + hintdown);
					lbimg.setIcon(imgup);
				} else if (randomnum < shownum) {
					hintdown = shownum;
					lbhint.setText(hintup + " < " + answer + " < " + hintdown);
					lbimg.setIcon(imgdown);
				}
			} else {
				JOptionPane.showMessageDialog(Main.frame, "게임을 시작해 주세요");
			}
		}

// =====================================================================
// 버튼 클릭 이벤트 처리
// =====================================================================
		if (e.getSource() == btnbackgamemain3) {
			cardgame.show(panelgameall, "1");
			// panelsonbyounghoall.remove(txtarea);
		}

		if (e.getSource() == btnup) {
			if (personnum < 4) {
				personnum = personnum + 1;
				txtfield.setText("  " + Integer.toString(personnum));
			} else {
				JOptionPane.showMessageDialog(Main.frame, "최대 4명까지 인원이 가능합니다.");
			}
		}

		if (e.getSource() == btndown) {
			if (personnum > 1) {
				personnum = personnum - 1;
				txtfield.setText("  " + Integer.toString(personnum));
			} else {
				JOptionPane.showMessageDialog(Main.frame, "플레이어는 1명 이하로 설정할 수 없습니다.");
			}
		}

		if (e.getSource() == btnstart1) {

			System.out.println("sdfasd");
			JOptionPane optionpane = new JOptionPane();
			int identifygamestart1 = optionpane.showConfirmDialog(null,
					personnum + "명 인원으로 게임을 시작해도 괜찮습니까?", "게임 시작 확인",
					JOptionPane.YES_NO_OPTION);
			if (identifygamestart1 == optionpane.YES_OPTION) {

				cardson.show(panelsonbyounghoall, "2");
				cardgamein.show(panelgamein, "1");

				if (personnum == 1) {

					lbcomputersay.setVisible(true);
					lbsay.setVisible(true);

					lbsonname[0].setText("Player1");
					lbsonname[2].setText("Computer");

					lbsonname[0].setVisible(true);
					lbhand[0].setVisible(true);
					btnhand[0].setVisible(true);

					lbsonname[2].setVisible(true);
					lbhand[2].setVisible(true);

				} else if (personnum == 2) {

					lbsonname[0].setText("Player1");
					lbsonname[2].setText("Player2");

					lbsonname[0].setVisible(true);
					lbhand[0].setVisible(true);
					btnhand[0].setVisible(true);

					lbsonname[2].setVisible(true);
					lbhand[2].setVisible(true);
					btnhand[2].setVisible(true);

				} else if (personnum == 3) {

					lbsonname[0].setText("Player1");
					lbsonname[1].setText("Player2");
					lbsonname[2].setText("Player3");

					lbsonname[0].setVisible(true);
					lbhand[0].setVisible(true);
					btnhand[0].setVisible(true);

					lbsonname[1].setVisible(true);
					lbhand[1].setVisible(true);
					btnhand[1].setVisible(true);

					lbsonname[2].setVisible(true);
					lbhand[2].setVisible(true);
					btnhand[2].setVisible(true);

				} else if (personnum == 4) {

					lbsonname[0].setText("Player1");
					lbsonname[1].setText("Player2");
					lbsonname[2].setText("Player3");
					lbsonname[3].setText("Player4");

					lbsonname[0].setVisible(true);
					lbhand[0].setVisible(true);
					btnhand[0].setVisible(true);

					lbsonname[1].setVisible(true);
					lbhand[1].setVisible(true);
					btnhand[1].setVisible(true);

					lbsonname[2].setVisible(true);
					lbhand[2].setVisible(true);
					btnhand[2].setVisible(true);

					lbsonname[3].setVisible(true);
					lbhand[3].setVisible(true);
					btnhand[3].setVisible(true);

				}

			}

		}
		if (e.getSource() == btnbackgamemain4) {

			JOptionPane optionpane2 = new JOptionPane();
			int identifygamestart2 = optionpane2.showConfirmDialog(null,
					personnum + "명이 참가하였습니다. 다시 확인하시겠습니까?", "확인",
					JOptionPane.YES_NO_OPTION);
			if (identifygamestart2 == optionpane2.YES_OPTION) {
				cardson.show(panelsonbyounghoall, "1");
				cardgamein.show(panelgamein, "1");
				txtarea.setText("게임이 시작되었습니다. 준비하세요.");

				personnum = 1;
				txtfield.setText("  " + Integer.toString(personnum));

				lbcomputersay.setText("준비가 완료되었나요?");


				for (int i = 0; i < 4; i++) {
					handfinger[i] = 5;
					lbhand[i].setIcon(imgfive[i]);
				}

				// ----------------
				lbcomputersay.setVisible(false);
				lbsay.setVisible(false);

				lbsonname[0].setVisible(false);
				lbhand[0].setVisible(false);
				btnhand[0].setVisible(false);

				lbsonname[1].setVisible(false);
				lbhand[1].setVisible(false);
				btnhand[1].setVisible(false);

				lbsonname[2].setVisible(false);
				lbhand[2].setVisible(false);
				btnhand[2].setVisible(false);

				lbsonname[3].setVisible(false);
				lbhand[3].setVisible(false);
				btnhand[3].setVisible(false);

				start2 = false;

			}

		}
		if (e.getSource() == btnstart3) {
			cardgamein.show(panelgamein, "2");

			start2 = true;

		}
		if (e.getSource() == btnnextquestion) {

			btnnextquestion.setText("다음 질문");

			randomnum = random.nextInt(questionnum);

			txtarea.setText(sonquestions[randomnum]);

			lbcomputersay.setText(word[randomnum]);

			if (personnum == 1 && sonfinger[randomnum] == -1
					&& handfinger[2] > 0) {

				handfinger[2] = handfinger[2] - 1;

				if (handfinger[2] == 5) {
					lbhand[2].setIcon(imgfive[2]);
				} else if (handfinger[2] == 4) {
					lbhand[2].setIcon(imgfour[2]);
				} else if (handfinger[2] == 3) {
					lbhand[2].setIcon(imgthree[2]);
				} else if (handfinger[2] == 2) {
					lbhand[2].setIcon(imgtwo[3]);
				} else if (handfinger[2] == 1) {
					lbhand[2].setIcon(imgwon[2]);
				} else if (handfinger[2] == 0) {
					lbhand[2].setIcon(imgson[2]);
					// false
					lbcomputersay.setText("게임이 진행중입니다...");

					JOptionPane.showMessageDialog(Main.frame, lbsonname[2]
							.getText().toString() + "님은 게임에 패배 하였습니다.");

				}
			}
			if (personnum < 2 && (handfinger[2] < 0 || handfinger[0] < 0 || handfinger[1] < 0)) {
				JOptionPane.showMessageDialog(Main.frame, "입력값이 잘못되었습니다.");
			}
		}

		for (int i = 0; i < 4; i++) {
			if (e.getSource() == btnhand[i]) {
				if (start2 == true) {

					if (handfinger[i] > 0) {
						handfinger[i] = handfinger[i] - 1;

						System.out.println("play1: " + handfinger[0]);
						System.out.println("play2: " + handfinger[1]);
						System.out.println("play3: " + handfinger[2]);
						System.out.println("play4: " + handfinger[3]);
						if (handfinger[i] == 5) {
							lbhand[i].setIcon(imgfive[i]);
						} else if (handfinger[i] == 4) {
							lbhand[i].setIcon(imgfour[i]);
						} else if (handfinger[i] == 3) {
							lbhand[i].setIcon(imgthree[i]);
						} else if (handfinger[i] == 2) {
							lbhand[i].setIcon(imgtwo[i]);
						} else if (handfinger[i] == 1) {
							lbhand[i].setIcon(imgwon[i]);
						} else if (handfinger[i] == 0) {
							lbhand[i].setIcon(imgson[i]);
							// false

							JOptionPane.showMessageDialog(Main.frame,
									lbsonname[i].getText().toString() + "은(는) 손가락을 모두 접었습니다.");
						}
					} else {
						JOptionPane.showMessageDialog(Main.frame,
								lbsonname[i].getText().toString() + "는 선택할 수 없습니다.");
					}

				} else if (start2 == false) {
					JOptionPane.showMessageDialog(Main.frame, "게임을 먼저 시작하세요.");
				}
			}

		}
	}

}

