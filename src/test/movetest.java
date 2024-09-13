package test;

import java.util.ArrayList;

import DAODTO.EvaluationDAO;
import DAODTO.MenuDAO;
import DAODTO.MenuDTO;

public class movetest {

	String names;

	int checkscore;
	int checknum;
	int lastcheckscore;

	String query, kind, name;

	public movetest() {

		for (int i = 0; i < 2; i++) {
			String sql = "INSERT INTO evaluation (checkname,checkscore,checknum) VALUES(?,?,?)";

			EvaluationDAO evdao = new EvaluationDAO();
			if (i == 0) {
				name = "service";
			}
			if (i == 1) {
				name = "clean";
			}
			evdao.update(sql, name, checkscore, checknum);
		}
		name = null;
		System.out.print("������");
		
		query = "SELECT * FROM menu";
		MenuDAO dao = new MenuDAO();
		dao.contentselect(query, kind, name);

		ArrayList<MenuDTO> list = new ArrayList<MenuDTO>();

		list = dao.contentselect(query, kind, name);

		for (MenuDTO dto : list) {

			names = dto.getName();

			String sql = "INSERT INTO evaluation (checkname,checkscore,checknum) VALUES(?,?,?)";
			name = names;
			EvaluationDAO evdao = new EvaluationDAO();

			evdao.update(sql, name, checkscore, checknum);

			System.out.print(".");

		}

	}

	public static void main(String[] args) {

		//new movetest();
		// System.out.println("���� �Ϸ�");
	}

}
