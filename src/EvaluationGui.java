import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DAODTO.AnorderDAO;
import DAODTO.AnorderDTO;
import DAODTO.EvaluationDAO;
import DAODTO.EvaluationDTO;
import DAODTO.MenuDAO;
import DAODTO.MenuDTO;

public class EvaluationGui extends JPanel implements ActionListener {

	JPanel panelevalluation, panelservice, paneltasteall, panelborder,
			paneltaste, panelclean;
	JPanel[] ptaste;
	JPanel ptasteleft, ptasteright, ptasterrightnorth, ptasterrightcenter;

	ScrollPane scrollpane;
	CardLayout cardappraisal;

	JLabel lbservice, lbtaste, lbclean;

	JLabel lbqservice, lbqtaste, lbqclean;

	JLabel lbterms;

	CheckboxGroup checkgroupservice, checkgroupclean;
	CheckboxGroup[] checkgrouptaste;

	Checkbox checkservice1, checkservice2, checkservice3, checkservice4,
			checkservice5;
	Checkbox[] checktaste1, checktaste2, checktaste3, checktaste4, checktaste5;
	Checkbox checkclean1, checkclean2, checkclean3, checkclean4, checkclean5;

	JButton btnsave, btnbackmain;
	static int service, service2;
	static int clean, clean2;
	static int[] taste = new int[30];
	static int[] taste2 = new int[30];

	boolean[] inspect = new boolean[30];

	int c;

	int n;
	int i;

	ImageIcon img;
	JLabel[] lbimg;
	JLabel[] lbname;
	int[] checkscores;
	String[] name;
	String kind;

	String names;

	int checknum;
	int checkscore;

	JOptionPane optionpane;
	int identifyorder;

	String query;

	EvaluationDAO evaluationdao = new EvaluationDAO();

	public EvaluationGui(String query, int tablecode) {

		panelevalluation = new JPanel(null);

		cardappraisal = new CardLayout();

		panelservice = new JPanel(null);
		panelservice.setBackground(Color.orange);
		paneltasteall = new JPanel(null);
		paneltasteall.setBackground(Color.orange);
		panelborder = new JPanel(new BorderLayout());
		paneltaste = new JPanel(new GridLayout(0, 1));
		paneltaste.setBackground(Color.YELLOW);
		panelclean = new JPanel(null);
		panelclean.setBackground(Color.orange);

		lbterms = new JLabel("                         평가를 위해 모든 항목을 체크해주세요.");

		btnsave = new JButton("저장하기");
		btnsave.addActionListener(this);
		btnbackmain = new JButton("메인으로");

		panelevalluation.add(panelservice);
		panelservice.setBounds(0, 0, 260, 410);
		panelevalluation.add(paneltasteall);
		paneltasteall.setBounds(263, 0, 359, 410);
		panelevalluation.add(panelclean);
		panelclean.setBounds(625, 0, 260, 410);

		paneltasteall.add(panelborder);
		panelborder.setBounds(0, 80, 359, 330);

		scrollpane = new ScrollPane();
		// scrollpane.setSize(359, 200);
		// scrollpane.setBounds(0, 0, 300, 700);

		panelborder.add(scrollpane, "Center");

		scrollpane.add(paneltaste);
		// paneltaste.setSize(359, 200);

		panelevalluation.add(btnsave);
		btnsave.setBounds(670, 422, 100, 30);

		panelevalluation.add(btnbackmain);
		btnbackmain.setBounds(770, 422, 100, 30);

		lbservice = new JLabel("서비스");
		lbtaste = new JLabel("음식");
		lbclean = new JLabel("청결");

		panelservice.add(lbservice);
		lbservice.setBounds(112, 15, 100, 20);
		paneltasteall.add(lbtaste);
		lbtaste.setBounds(170, 15, 100, 20);
		panelclean.add(lbclean);
		lbclean.setBounds(120, 15, 100, 20);

		lbqservice = new JLabel("1. 서비스 만족도를 평가해 주세요.");
		lbqtaste = new JLabel("2. 맛에 대한 평가를 해주세요.");
		lbqclean = new JLabel("3. 청결 상태에 대한 평가를 해주세요.");

		panelservice.add(lbqservice);
		lbqservice.setBounds(20, 50, 200, 20);
		paneltasteall.add(lbqtaste);
		lbqtaste.setBounds(20, 50, 200, 20);
		panelclean.add(lbqclean);
		lbqclean.setBounds(20, 50, 200, 20);

		// ------------------Service;

		checkgroupservice = new CheckboxGroup();
		checkservice1 = new Checkbox("매우 친절하다", checkgroupservice, false);
		checkservice2 = new Checkbox("그저 그렇다", checkgroupservice, false);
		checkservice3 = new Checkbox("보통이다", checkgroupservice, false);
		checkservice4 = new Checkbox("좀더 노력했으면 좋겠다", checkgroupservice, false);
		checkservice5 = new Checkbox("반드시 개선해야 한다", checkgroupservice, false);

		if (service == 5) {
			checkservice1.setState(true);
		}
		if (service == 4) {
			checkservice2.setState(true);
		}
		if (service == 3) {
			checkservice3.setState(true);
		}
		if (service == 2) {
			checkservice4.setState(true);
		}
		if (service == 1) {
			checkservice5.setState(true);
		}

		panelservice.add(checkservice1);
		checkservice1.setBounds(40, 130, 150, 20);
		panelservice.add(checkservice2);
		checkservice2.setBounds(40, 170, 150, 20);
		panelservice.add(checkservice3);
		checkservice3.setBounds(40, 210, 150, 20);
		panelservice.add(checkservice4);
		checkservice4.setBounds(40, 250, 150, 20);
		panelservice.add(checkservice5);
		checkservice5.setBounds(40, 290, 150, 20);

		// ------------------Taste;

		AnorderDAO anorderdao = new AnorderDAO();
		ArrayList<AnorderDTO> list = new ArrayList<AnorderDTO>();
		list = anorderdao.selectevaluation(query, tablecode);

		n = anorderdao.n;

		if (n == 0) {
			paneltaste.add(lbterms);
			System.out.println("정보를 저장했습니다");
		}

		ptaste = new JPanel[n];
		lbimg = new JLabel[n];
		lbname = new JLabel[n];
		checkscores = new int[n];
		// taste = new int[n];

		checkgrouptaste = new CheckboxGroup[n];
		checktaste1 = new Checkbox[n];
		checktaste2 = new Checkbox[n];
		checktaste3 = new Checkbox[n];
		checktaste4 = new Checkbox[n];
		checktaste5 = new Checkbox[n];

		name = new String[n];

		for (AnorderDTO dto : list) {

			ptasteleft = new JPanel(new BorderLayout());
			// ptasteleft.setBackground(Color.red);
			ptasteright = new JPanel(new BorderLayout());
			ptasterrightnorth = new JPanel();

			ptasterrightcenter = new JPanel(new GridLayout(0, 5));
			ptasterrightcenter.setBackground(Color.pink);

			ptasteright.add(ptasterrightnorth, "North");
			ptasteright.add(ptasterrightcenter, "Center");

			ptaste[i] = new JPanel(new BorderLayout());
			ptaste[i].add(ptasteleft, "West");
			ptaste[i].add(ptasteright, "Center");

			name[i] = dto.getName();

			lbimg[i] = new JLabel();

			kind = dto.getKind();

			img = new ImageIcon("image/"
					+ kind + "/" + name[i] + ".jpg");
			img = new ImageIcon(img.getImage().getScaledInstance(80, 80,
					Image.SCALE_REPLICATE));

			lbimg[i].setIcon(img);

			ptasteleft.add(lbimg[i], "Center");
			lbname[i] = new JLabel(dto.getName());

			ptasterrightnorth.add(lbname[i]);

			checkgrouptaste[i] = new CheckboxGroup();

			checktaste1[i] = new Checkbox("A", checkgrouptaste[i], false);
			checktaste2[i] = new Checkbox("B", checkgrouptaste[i], false);
			checktaste3[i] = new Checkbox("C", checkgrouptaste[i], false);
			checktaste4[i] = new Checkbox("D", checkgrouptaste[i], false);
			checktaste5[i] = new Checkbox("E", checkgrouptaste[i], false);

			if (taste[i] == 5) {
				checktaste1[i].setState(true);
			}
			if (taste[i] == 4) {
				checktaste2[i].setState(true);
			}
			if (taste[i] == 3) {
				checktaste3[i].setState(true);
			}
			if (taste[i] == 2) {
				checktaste4[i].setState(true);
			}
			if (taste[i] == 1) {
				checktaste5[i].setState(true);
			}

			ptasterrightcenter.add(checktaste1[i]);
			ptasterrightcenter.add(checktaste2[i]);
			ptasterrightcenter.add(checktaste3[i]);
			ptasterrightcenter.add(checktaste4[i]);
			ptasterrightcenter.add(checktaste5[i]);

			paneltaste.add(ptaste[i]);

			i++;
			img = null;

		}

		// --------------------------------------------Clean


		checkgroupclean = new CheckboxGroup();
		checkclean1 = new Checkbox("매우 청결하다", checkgroupclean, false);
		checkclean2 = new Checkbox("그저 그렇다", checkgroupclean, false);
		checkclean3 = new Checkbox("보통이다", checkgroupclean, false);
		checkclean4 = new Checkbox("좀더 노력했으면 좋겠다", checkgroupclean, false);
		checkclean5 = new Checkbox("반드시 개선해야 한다", checkgroupclean, false);
		if (clean == 5) {
			checkclean1.setState(true);
		}
		if (clean == 4) {
			checkclean2.setState(true);
		}
		if (clean == 3) {
			checkclean3.setState(true);
		}
		if (clean == 2) {
			checkclean4.setState(true);
		}
		if (clean == 1) {
			checkclean5.setState(true);
		}

		panelclean.add(checkclean1);
		checkclean1.setBounds(40, 130, 150, 20);
		panelclean.add(checkclean2);
		checkclean2.setBounds(40, 170, 150, 20);
		panelclean.add(checkclean3);
		checkclean3.setBounds(40, 210, 150, 20);
		panelclean.add(checkclean4);
		checkclean4.setBounds(40, 250, 150, 20);
		panelclean.add(checkclean5);
		checkclean5.setBounds(40, 290, 150, 20);

		// =========================================================================

		// ------------------------------------------------------------
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnsave) {

			if (checkservice1.getState() == true) {
				service = 5;
			} else if (checkservice2.getState() == true) {
				service = 4;
			} else if (checkservice3.getState() == true) {
				service = 3;
			} else if (checkservice4.getState() == true) {
				service = 2;
			} else if (checkservice5.getState() == true) {
				service = 1;
			}

			if (checkclean1.getState() == true) {
				clean = 5;
			} else if (checkclean2.getState() == true) {
				clean = 4;
			} else if (checkclean3.getState() == true) {
				clean = 3;
			} else if (checkclean4.getState() == true) {
				clean = 2;
			} else if (checkclean5.getState() == true) {
				clean = 1;
			}

			for (int i = 0; i < n; i++) {
				if (checktaste1[i].getState() == true) {
					taste[i] = 5;
				} else if (checktaste2[i].getState() == true) {
					taste[i] = 4;
				} else if (checktaste3[i].getState() == true) {
					taste[i] = 3;
				} else if (checktaste4[i].getState() == true) {
					taste[i] = 2;
				} else if (checktaste5[i].getState() == true) {
					taste[i] = 1;
				}

			}
			// =========================================================================== 함수
			// 평가 사항 확인
			if (n == 0) {
				if (service == 0) {
					JOptionPane.showMessageDialog(this, "서비스 항목을 선택해 주세요.");
					return;
				}
				if (clean == 0) {
					JOptionPane.showMessageDialog(this, "청소 항목을 선택해 주세요.");
					return;
				}

			}
			System.out.println(clean);
			for (int i = 0; i < n; i++) {

				if (service == 0) {
					JOptionPane.showMessageDialog(this, "서비스 항목을 선택해 주세요.");
					return;
				}
				if (clean == 0) {
					JOptionPane.showMessageDialog(this, "청소 항목을 선택해 주세요.");
					return;
				}
				if (taste[i] == 0) {
					JOptionPane.showMessageDialog(this, "맛 항목을 선택해 주세요.");
					return;
				}
			}
			// ============================================================================= 함수
			// 평가 사항 적용
			// --------------------------------------------------------------------------------------------------------------0 비교
			// 서비스
			//if (service2 == 0) {
			names = "service";

			evaluationdao.select(names);
			ArrayList<EvaluationDTO> list = new ArrayList<EvaluationDTO>();
			list = evaluationdao.select(names);
//				for (EvaluationDTO dto : list) {
//
//					checknum = dto.getChecknum() + 1;
//					checkscore = service;
//					System.out.println(checknum + "개수");
//					System.out.println(checkscore + "점수");
//				}

			evaluationdao.select(names);
			if (service2 == 0) {

				String sql = "INSERT INTO evaluation (checkscore, checknum, checkname) VALUES (?, ?, ?)";
				evaluationdao.update(sql, names, service, 1);

			} else {

				//TODO: UPDATE DATA WITH PRIMARY KEY
			}
			//String sql = "INSERT INTO evaluation (checkscore, checknum, checkname) VALUES (?, ?, ?)";
			//String sql = "UPDATE evaluation SET checkscore=?,checknum=? WHERE checkname=?";
			c = 1;
		}
		//if (clean2 == 0) {

		names = "clean";

		evaluationdao.select(names);
		ArrayList<EvaluationDTO> list = new ArrayList<EvaluationDTO>();
		list = evaluationdao.select(names);
//				for (EvaluationDTO dto : list) {
//
//					checknum = dto.getChecknum() + 1;
//					checkscore = clean;
//					System.out.println(checknum + "개수");
//					System.out.println(checkscore + "점수");
//				}

		evaluationdao.select(names);

		if (clean2 == 0) {

			checkscore = clean;
			String sql = "INSERT INTO evaluation (checkscore, checknum, checkname) VALUES (?, ?, ?)";
			evaluationdao.update(sql, names, checkscore, 1);

		} else {

			//TODO: UPDATE DATA WITH PRIMARY KEY
		}

//				String sql = "UPDATE evaluation SET checkscore=?,checknum=? WHERE checkname=?";
		c = 1;
		//}

		for (int i = 0; i < n; i++) {
			if (taste2[i] == 0) {

				names = name[i];
				evaluationdao.select(names);
				//ArrayList<EvaluationDTO> list = new ArrayList<EvaluationDTO>();
				list = evaluationdao.select(names);
				// System.out.println(checknum+"개수");System.out.println(checkscore+"점수");
//					for (EvaluationDTO dto : list) {
//
//						checknum = dto.getChecknum() + 1;
//						checkscore = dto.getCheckscore() + clean;
//						System.out.print(taste[i] + "맛점수");
//						System.out.println(taste2[i] + "기존점수");
//					}

				evaluationdao.select(names);
				if (taste2[i] == 0) {
					String sql = "INSERT INTO evaluation (checkscore, checknum, checkname) VALUES (?, ?, ?)";

//					String sql = "UPDATE evaluation SET checkscore=?,checknum=? WHERE checkname=?";
					evaluationdao.update(sql, names, taste[i], 1);
				}

				c = 1;
			}

		}
		//}
		// ------------------------------------------------------------------------------------------------0 비교
		// 확인
		// ======================================================================================================== 확인
		// 최종 확인
		System.out.println("------------service 점수 " + service + " 기존 service 점수"
				+ service2 + "------------");
		System.out.println("------------clean 점수 " + clean + " 기존 clean 점수"
				+ clean2 + "------------");
		if (n == 0) {

			if (service2 != 0 && service2 != service || clean2 != 0
					&& clean2 != clean) {

				identifyorder = optionpane.showConfirmDialog(null,
						"상기 내용이 맞습니까? 변경하시겠습니까?", "확인",
						JOptionPane.YES_NO_OPTION);
			}


			// ========================================================================================================= 확인
			// 최종 확인
			// ------------------------------------------------------------------------------------------------------ 확인
			// 2 = 기존 1 변경 내용

			service2 = service;
			clean2 = clean;

			for (int i = 0; i < n; i++) {

				taste2[i] = taste[i];

			}

			/*
			for (int i = 0; i < n; i++) {

				if (taste2[i] != 0 && taste2[i] != taste[i]) {
					identifyorder = optionpane.showConfirmDialog(null,
							"상기 내용이 맞습니까? 변경하시겠습니까?", "확인",
							JOptionPane.YES_NO_OPTION);

				} else {

					if (service2 != 0 && service2 != service || clean2 != 0
							&& clean2 != clean) {

						identifyorder = optionpane.showConfirmDialog(null,
								"상기 내용이 맞습니까? 변경하시겠습니까?", "확인",
								JOptionPane.YES_NO_OPTION);

					}
				}
				*/
		}

		JOptionPane.showMessageDialog(this, "평가가 완료되었습니다.");
	}

}

/*
 * for (int i = 0; i < n; i++) { if (taste2[i] != 0) {
 *
 * if (taste2[i] != taste[i]) { inspect[i] = false; c = 1; System.out.println(c
 * + i + "ggg"); }
 *
 * } }
 *
 * for (int i = 0; i < n; i++) { if (taste2[i] == 0) { c = 2; }
 *
 * }
 *
 * if (service != 0 && clean != 0) { if (n == 0) { service2 = service; clean2 =
 * clean; } else if (c != 2) { for (int i = 0; i < n; i++) {
 *
 * if (taste[i] != 0) {
 *
 * System.out .println("********************************************���;� �ϴµ�");
 * service2 = service; clean2 = clean; for (int v = 0; v < n; v++) { taste2[v] =
 * taste[v];
 *
 * }
 *
 * }
 *
 * }
 *
 * }
 *
 * } for (int i = 0; i < n; i++) { if (taste[i] != taste2[i]) { c = 1; }
 *
 * }
 *
 * }
 */

// ------------------------------------------------------------------------------------------------------결과
// 2 = 완료 1 = 미완료

