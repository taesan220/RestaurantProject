import java.sql.*;
import java.util.ArrayList;

import DAODTO.BeforeSetingDAO;
import DAODTO.BeforeSetingDTO;
import DAODTO.GameDTO;

/* *****************************************************
 	클래스 이름을 먼저 CreateTable을 통해 테이블을 생성해 주세요.

	클래스에 맞게 new BeforeSeting()을 호출하면 데이터가 들어갑니다.

	Run을 하면 테이블이 자동으로 생성됩니다.
	*************************************************** */

public class BeforeSeting {

	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;


	int menunum = 79;
	int i,n;

	String[] arraykind = new String[menunum];
	int[] arraynum = new int[menunum];
	String[] arrayname = new String[menunum];
	String[] arraymeterial = new String[menunum];
	String[] arrayexplain = new String[menunum];
	int[] arraycalorie = new int[menunum];
	int[] arrayprice = new int[menunum];

	String kind, name, meterial, explain;
	int num, calorie, price;

	//================================================game
	String[] questions;
	String question;
	int[] fingers;
	int finger;
	String[] words;
	String word;
	int gamenum = 51;
	int intgame;

	BeforeSetingDAO dao = new BeforeSetingDAO();
	public BeforeSeting(){

		createMenuTable();	//menu 테이블이 없으면 생성
		createOrderTable();	//order 테이블이 없으면 생성
		createEvaluationTable(); //evaluation 테이블이 없으면 생성
		createGameQuestionTable(); //gamequestion 테이블이 없으면 생성


		arraykind[i] = new String();
		//arraynum[i] = new int();
		arrayname[i] = new String();
		arraymeterial[i] = new String();
		arrayexplain[i] = new String();
		//arraycalorie[i] = new int();
		//arrayprice [i] = new int();

		//dao.selectMenu();
		ArrayList<BeforeSetingDTO> list = new ArrayList<BeforeSetingDTO>();
		list = dao.selectMenu();
		for(BeforeSetingDTO dto : list){

			dto.getKind();
			dto.getNum();
			dto.getName();
			dto.getMeterial();
			dto.getExplain();
			dto.getCalorie();
			dto.getPrice();

			n++;
		}

		System.out.println(n);



		if(n == 0){
			for(int i = 0; i < menunum; i++){

				menu();
				kind = arraykind[i];
				num = arraynum[i];
				name = arrayname[i];
				meterial = arraymeterial[i];
				explain = arrayexplain[i];
				calorie = arraycalorie[i];
				price = arrayprice[i];

				dao.insertmenu(kind, num, name, meterial, explain, calorie, price);

			}
		}
		System.out.println("데이터 입력 완료");

		ArrayList<GameDTO> questionlist = dao.selectGame();

		if (questionlist == null || questionlist.size() == 0) {

			for(int i = 0; i < gamenum; i++){

				questions = new String[gamenum];
				fingers = new int[gamenum];
				words = new String[gamenum];
				questions[i] = new String();

				game();
				question = questions[i];
				finger = fingers[i];
				intgame = i;
				word = words[i];

				dao.insertgame(question, intgame, finger, word);
			}
		}
	}

	public void createMenuTable() {
		// Create table if it doesn't exist
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false", "test", "test");
			stmt = con.createStatement();

			// Check if table exists
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "menu", null);
			if (!tables.next()) {
				// Table does not exist, create it
				String createTableSQL = "CREATE TABLE menu (" +
						"kind VARCHAR(255) NOT NULL, " +
						"num INT NOT NULL AUTO_INCREMENT, " +  // AUTO_INCREMENT 추가
						"name VARCHAR(255) NOT NULL, " +
						"meterial VARCHAR(255), " +
						"`explain` TEXT, " +
						"calorie INT, " +
						"price INT, " +
						"PRIMARY KEY (num)" +
						")";
				stmt.executeUpdate(createTableSQL);
				System.out.println("Menu table created.");
			}
			tables.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	public void createOrderTable() {
		Connection con = null;
		Statement stmt = null;
		try {
			// 데이터베이스 연결 설정
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false", "test", "test");
			stmt = con.createStatement();

			// 테이블이 이미 존재하는지 확인
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "Anorder", null);
			if (!tables.next()) {
				// 테이블이 없을 경우 생성
				String createTableSQL = "CREATE TABLE anorder (" +
						"num INT NOT NULL AUTO_INCREMENT, " +
						"tablecode INT NOT NULL, " +
						"kind VARCHAR(255) NOT NULL, " +
						"name VARCHAR(255) NOT NULL, " +
						"amount INT NOT NULL, " +
						"anorder INT NOT NULL, " +
						"time DATE, " +
						"`condition` INT, " +	//condition는 예약어 -> 테이블 컬럼명으로 사용하려면 ``를 붙여야 함 ('' 아님)
						"PRIMARY KEY (num)" +
						")";
				stmt.executeUpdate(createTableSQL);
				System.out.println("Anorder 테이블이 생성되었습니다.");
			} else {
				System.out.println("Anorder 테이블이 이미 존재합니다.");
			}
			tables.close(); // ResultSet 닫기
		} catch (SQLException e) {
			e.printStackTrace(); // 에러 발생 시 스택 트레이스 출력
		} finally {
			try {
				if (stmt != null) stmt.close(); // 리소스 해제
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace(); // 리소스 해제 중 에러가 발생하면 출력
			}
		}
	}

	public void createEvaluationTable() {
		Connection con = null;
		Statement stmt = null;
		try {
			// 데이터베이스 연결 설정
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false", "test", "test");
			stmt = con.createStatement();

			// 테이블이 이미 존재하는지 확인
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "evaluation", null);
			if (!tables.next()) {
				// 테이블이 없을 경우 생성
				String createTableSQL = "CREATE TABLE evaluation (" +
						"num INT NOT NULL AUTO_INCREMENT, " +
						"checkname VARCHAR(255) NOT NULL, " +
						"checkscore INT, " +
						"checknum INT, " +
						"PRIMARY KEY (num)" +
						")";
				stmt.executeUpdate(createTableSQL);
				System.out.println("Evaluation 테이블이 생성되었습니다.");
			} else {
				System.out.println("Evaluation 테이블이 이미 존재합니다.");
			}
			tables.close(); // ResultSet 닫기
		} catch (SQLException e) {
			e.printStackTrace(); // 에러 발생 시 스택 트레이스 출력
		} finally {
			try {
				if (stmt != null) stmt.close(); // 리소스 해제
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace(); // 리소스 해제 중 에러가 발생하면 출력
			}
		}
	}

	public void createGameQuestionTable() {
		Connection con = null;
		Statement stmt = null;
		try {
			// 데이터베이스 연결 설정
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant?allowPublicKeyRetrieval=true&useSSL=false", "test", "test");
			stmt = con.createStatement();

			// 테이블이 이미 존재하는지 확인
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "gamequestion", null);
			if (!tables.next()) {
				// 테이블이 없을 경우 생성
				String createTableSQL = "CREATE TABLE gamequestion (" +
						"num INT NOT NULL AUTO_INCREMENT, " +
						"question VARCHAR(255) NOT NULL, " +
						"finger INT, " +
						"word VARCHAR(255), " +
						"PRIMARY KEY (num)" +
						")";
				stmt.executeUpdate(createTableSQL);
				System.out.println("GameQuestion 테이블이 생성되었습니다.");
			} else {
				System.out.println("GameQuestion 테이블이 이미 존재합니다.");
			}
			tables.close(); // ResultSet 닫기
		} catch (SQLException e) {
			e.printStackTrace(); // 에러 발생 시 스택 트레이스 출력
		} finally {
			try {
				if (stmt != null) stmt.close(); // 리소스 해제
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace(); // 리소스 해제 중 에러가 발생하면 출력
			}
		}
	}

	public void menu() {
//*****************************************0
		arraykind[0] = "hamburger"; arraynum[0] = 1; arrayname[0] = "데리버거"; arraymeterial[0] = "호주산 쇠고기"; arrayexplain[0] = "풍성한 레터스에 마요네즈, 톡특한 데리소스가 포인트인 버거"; arraycalorie[0] = 343; arrayprice[0] = 2300;
//-----------------------------------------0

//*****************************************1
		arraykind[1] = "hamburger"; arraynum[1] = 2; arrayname[1] = "랏츠버거"; arraymeterial[1] = "호주산 쇠고기"; arrayexplain[1] = "두툼하고 육중한 비프패티와 새콤한 랠리쉬 피클 화이트 소스의 버거"; arraycalorie[1] = 506; arrayprice[1] = 4000;
//-----------------------------------------1

//*****************************************2
		arraykind[2] = "hamburger"; arraynum[2] = 3; arrayname[2] = "불갈비버거"; arraymeterial[2] = "호주산 쇠고기"; arrayexplain[2] = "쇠고기 또는 우肉을 사용해서 만든 고품격 햄버거로써 우리의 전통적인 맛을 살린 제품"; arraycalorie[2] = 462; arrayprice[2] = 3500;
//-----------------------------------------2

//*****************************************3
		arraykind[3] = "hamburger"; arraynum[3] = 4; arrayname[3] = "불고기버거"; arraymeterial[3] = "호주산 쇠고기"; arrayexplain[3] = "볼륨 가득한 패티와 불고기 소스가 잘 조화된 영양만점의 햄버거"; arraycalorie[3] = 390; arrayprice[3] = 3200;
//-----------------------------------------3

//*****************************************4
		arraykind[4] = "hamburger"; arraynum[4] = 5; arrayname[4] = "불새버거"; arraymeterial[4] = "호주산 쇠고기"; arrayexplain[4] = "롯데리아 대표 버거인 불고기 버거와 새우버거를 반밥"; arraycalorie[4] = 477; arrayprice[4] = 3800;
//-----------------------------------------4

//*****************************************5
		arraykind[5] = "hamburger"; arraynum[5] = 6; arrayname[5] = "새우버거"; arraymeterial[5] = ""; arrayexplain[5] = "흰살생선과 새우맛이 살아있는 햄버거"; arraycalorie[5] = 421; arrayprice[5] = 3200;
//-----------------------------------------5

//*****************************************6
		arraykind[6] = "hamburger"; arraynum[6] = 7; arrayname[6] = "야채라이스불고기"; arraymeterial[6] = "국내산 쌀, 호주산 쇠고기"; arrayexplain[6] = "야채볶음 라이스번스와 불고기패티가 잘 어울어져 있으며, 한국인 입맛에 잘 맞추고 밥을 응용한 롯데리아 전통메뉴로 한끼 식사로도 가능한 햄버거 메뉴"; arraycalorie[6] = 444; arrayprice[6] = 3900;
//-----------------------------------------6

//*****************************************7
		arraykind[7] = "hamburger"; arraynum[7] = 8; arrayname[7] = "오징어버거"; arraymeterial[7] = ""; arrayexplain[7] = "신선한 레터스와 매콤한 양념이 들어가 있는 탱글탱글한 오징어살에 사우즌소스와 매운맛 소스가 어울어져 씨푸드의 풍선한 맛을 느낄수 있는 햄버 "; arraycalorie[7] = 347; arrayprice[7] = 2300;
//-----------------------------------------7

//*****************************************8
		arraykind[8] = "hamburger"; arraynum[8] = 8; arrayname[8] = "유러피언프리코치즈버거"; arraymeterial[8] = "호주산 쇠고기"; arrayexplain[8] = "네덜란드 Frico스모크치즈를 응용한 치즈패티 투입으로 차별화 및 치즈의 맛과 풍미를 강조한 제품"; arraycalorie[8] = 559; arrayprice[8] = 4500;
//-----------------------------------------8

//*****************************************9
		arraykind[9] = "hamburger"; arraynum[9] = 10; arrayname[9] = "자이언트더블버거"; arraymeterial[9] = "호주산 쇠고기"; arrayexplain[9] = "비프패티와 영양만점의 치즈비프패티가 함께 어울어진 담백함과 싱싱한 토마토와 레티스를 곁들여 햄버거의 풍부한 맛을 느낄수 있는 고급 햄버거"; arraycalorie[9] = 492; arrayprice[9] = 3800;
//-----------------------------------------9

//*****************************************10
		arraykind[10] = "hamburger"; arraynum[10] = 11; arrayname[10] = "치즈버거"; arraymeterial[10] = "호주산 쇠고기"; arrayexplain[10] = "치즈특유의 맛과 향이 듬뿍 묻어나는 햄버거"; arraycalorie[10] = 358; arrayprice[10] = 2300;
//-----------------------------------------10

//*****************************************11
		arraykind[11] = "hamburger"; arraynum[11] = 12; arrayname[11] = "치킨버거"; arraymeterial[11] = "브라질산 닭고기"; arrayexplain[11] = "치킨 가슴살로 만든 저 칼로리 고단백 제품"; arraycalorie[11] = 367; arrayprice[11] = 3000;
//-----------------------------------------11

//*****************************************12
		arraykind[12] = "hamburger"; arraynum[12] = 13; arrayname[12] = "텐더그릴치킨버거"; arraymeterial[12] = "브라질산 닭고기"; arrayexplain[12] = "JUICY이 뛰어난 계육 엉치살을 그릴에 구워 바베큐향이 뛰어나며, 신선한 야채를 넣은 고급 치킨버거"; arraycalorie[12] = 374; arrayprice[12] = 3900;
//-----------------------------------------12

//*****************************************13
		arraykind[13] = "hamburger"; arraynum[13] = 14; arrayname[13] = "한우레이디버거"; arraymeterial[13] = "국내산 한우"; arrayexplain[13] = "고급 한우肉에 국내산 떡을 믹싱하여 맛과 Fun이 가미된 독특한 컨셉의 버거"; arraycalorie[13] = 394; arrayprice[13] = 4700;
//-----------------------------------------13

//*****************************************14
		arraykind[14] = "hamburger"; arraynum[14] = 15; arrayname[14] = "한우불고기버거"; arraymeterial[14] = "국내산 한우"; arrayexplain[14] = "풍성한 레타스와 마요네즈, 국내산 한우를 이용한 한우패티와 토마토 첨가로 Jucicy 감을 향상시킨 프리미엄 햄버거"; arraycalorie[14] = 641; arrayprice[14] = 5500;
//-----------------------------------------14

//*****************************************15
		arraykind[15] = "hamburger"; arraynum[15] = 16; arrayname[15] = "핫크리스피버거"; arraymeterial[15] = "국내산 닭고기"; arrayexplain[15] = "토마토와 레타스등의 야채와 하바네로 이 가미된 가슴살로 만든 매콤한 Premium 치킨버거"; arraycalorie[15] = 503; arrayprice[15] = 4300;
//-----------------------------------------15

//*****************************************16
		arraykind[16] = "hamburger"; arraynum[16] = 17; arrayname[16] = "햄버거"; arraymeterial[16] = "호주산 쇠고기"; arrayexplain[16] = "마늘양파번스에 케찹, 피클, 머스타드가 들어 있는 오리지날 레귤러 햄버거"; arraycalorie[16] = 250; arrayprice[16] = 1300;
//-----------------------------------------16

//*****************************************17
		arraykind[17] = "chicken"; arraynum[17] = 18; arrayname[17] = "치킨1조각"; arraymeterial[17] = "국내산 닭고기"; arrayexplain[17] = "신선한 치킨만을 사용하여 부드럽고 담백함과 매콤한 맛이 잘 어우러진 후라이트 치킨(순한맛/매운맛)"; arraycalorie[17] = 231; arrayprice[17] = 2200;
//-----------------------------------------17

//*****************************************18
		arraykind[18] = "chicken"; arraynum[18] = 19; arrayname[18] = "치킨휠레 (2조각)"; arraymeterial[18] = "국내산 닭고기"; arrayexplain[18] = "담백한 안심살의 Chicken fillet와 롯데리아만의 독특한 소스가 어우러진 디저트 메뉴"; arraycalorie[18] = 141; arrayprice[18] = 2200;
//-----------------------------------------18

//*****************************************19
		arraykind[19] = "chicken"; arraynum[19] = 20; arrayname[19] = "치킨휠레 (4조각)"; arraymeterial[19] = "국내산 닭고기"; arrayexplain[19] = "담백한 안심살의 Chicken fillet와 롯데리아만의 독특한 소스가 어우러진 디저트 메뉴"; arraycalorie[19] = 282; arrayprice[19] = 4100;
//-----------------------------------------19

//*****************************************20
		arraykind[20] = "chicken"; arraynum[20] = 21; arrayname[20] = "화이어윙 (2조각)"; arraymeterial[20] = "덴마크산 닭고기"; arrayexplain[20] = "담백한 맛과 매콤한 맛이 잘 조화된 특별한 타입의 치킨"; arraycalorie[20] = 160; arrayprice[20] = 2200;
//-----------------------------------------20

//*****************************************21
		arraykind[21] = "chicken"; arraynum[21] = 22; arrayname[21] = "화이어윙 (4조각)"; arraymeterial[21] = "덴마크산 닭고기"; arrayexplain[21] = "담백한 맛과 매콤한 맛이 잘 조화된 특별한 타입의 치킨"; arraycalorie[21] = 320; arrayprice[21] = 2600;
//-----------------------------------------21
//
////*****************************************22
		arraykind[22] = "dessert"; arraynum[22] = 23; arrayname[22] = "매운양념치킨"; arraymeterial[22] = ""; arrayexplain[22] = "국산 붉은대게살과 야채가 어우러진 고급 씨푸 드 디저트"; arraycalorie[22] = 87; arrayprice[22] = 2000;
////-----------------------------------------22
//
////*****************************************23
		arraykind[23] = "dessert"; arraynum[23] = 24; arrayname[23] = "포테이토"; arraymeterial[23] = ""; arrayexplain[23] = "신선도 높은 후라잉 오일에 바로 튀겨낸 바삭 바삭하고 감칠맛 나는 맛의 후렌치 포테이토"; arraycalorie[23] = 292; arrayprice[23] = 1400;
////-----------------------------------------23
//
////*****************************************24
		arraykind[24] = "dessert"; arraynum[24] = 25; arrayname[24] = "크런치새우"; arraymeterial[24] = ""; arrayexplain[24] = "부드러운 새우순살과 코코넛의 고소함과 바삭 함이 함께 느껴지는 디져트 메뉴"; arraycalorie[24] = 146; arrayprice[24] = 1900;
////-----------------------------------------24
//
////*****************************************25
		arraykind[25] = "dessert"; arraynum[25] = 26; arrayname[25] = "콘샐러드"; arraymeterial[25] = ""; arrayexplain[25] = "신선한 야채와 상큼하고 고소한 드레싱유가 잘 어우러진 맛의 제품"; arraycalorie[25] = 146; arrayprice[25] = 1600;
////-----------------------------------------25
//
////*****************************************26
		arraykind[26] = "dessert"; arraynum[26] = 27; arrayname[26] = "커피토네이도"; arraymeterial[26] = ""; arrayexplain[26] = "-"; arraycalorie[26] = 241; arrayprice[26] = 1800;
////-----------------------------------------26
//
////*****************************************27
		arraykind[27] = "dessert"; arraynum[27] = 28; arrayname[27] = "초코쿠키 토네이도"; arraymeterial[27] = ""; arrayexplain[27] = "부드러운 아이스크럼에 달콤하고 깔끔한 쵸코 쿠키를 넣어 어린 아이부터 주부에 이르기까지 보는 즐거움과 먹는 즐거움을 함께하는 아이스 크림\n<일부매장제외>"; arraycalorie[27] = 308; arrayprice[27] = 1800;
////-----------------------------------------27
//
////*****************************************28
		arraykind[28] = "dessert"; arraynum[28] = 29; arrayname[28] = "요거트 샐러드"; arraymeterial[28] = ""; arrayexplain[28] = "신선한 레타스와 콘샐러드, 파프리카, 올리브 가 요거트드레싱과 어울려 깔끔한 맛을 잘 살 런 런치 디저트 컨셉의 샐러드 메뉴"; arraycalorie[28] = 262; arrayprice[28] = 2000;
////-----------------------------------------28
//
//*****************************************29
		arraykind[29] = "dessert"; arraynum[29] = 30; arrayname[29] = "와플(플레인)"; arraymeterial[29] = ""; arrayexplain[29] = "-"; arraycalorie[29] = 266; arrayprice[29] = 2000;
//-----------------------------------------29

		//*****************************************22
		arraykind[22] = "dessert"; arraynum[22] = 23; arrayname[22] = "내추럴치즈스틱"; arraymeterial[22] = ""; arrayexplain[22] = "치즈에 파슬리를 첨가한 튀김옷을 입혀 바삭바삭하고 부드럽고 고소한 서구풍의 디저트 메뉴"; arraycalorie[22] = 160; arrayprice[22] = 1900;
		//-----------------------------------------22

//*****************************************31
		arraykind[31] = "dessert"; arraynum[31] = 32; arrayname[31] = "녹차 토네이도"; arraymeterial[31] = ""; arrayexplain[31] = "부드러운 아이스크럼에 신선한 녹차를 넣어 풍 부한 녹차향을 즐길수 있으며, 특히 10~ 30 대 여성 취향의 제품으로 보는 즐거움과 먹는 즐거움을 함께하는 아이스크럼 <일부매장제외>"; arraycalorie[31] = 244; arrayprice[31] = 1800;
//-----------------------------------------31

//*****************************************32
		arraykind[32] = "dessert"; arraynum[32] = 33; arrayname[32] = "마블소프트콘"; arraymeterial[32] = ""; arrayexplain[32] = "소프트 아이스크럼과 초코렛의 어우러진 디저 트 메뉴"; arraycalorie[32] = 169; arrayprice[32] = 700;
//-----------------------------------------32

//*****************************************33
		arraykind[33] = "dessert"; arraynum[33] = 34; arrayname[33] = "매직팝토네이도"; arraymeterial[33] = ""; arrayexplain[33] = "-"; arraycalorie[33] = 275; arrayprice[33] = 2000;
//-----------------------------------------33

//*****************************************34
		arraykind[34] = "dessert"; arraynum[34] = 35; arrayname[34] = "소프트콘"; arraymeterial[34] = ""; arrayexplain[34] = "부드러운 맛과 시원한 소프트 타입의 아이스콘 제품"; arraycalorie[34] = 145; arrayprice[34] = 500;
//-----------------------------------------34

//*****************************************35
		arraykind[35] = "dessert"; arraynum[35] = 36; arrayname[35] = "쉑쉑치킨 (어니언)"; arraymeterial[35] = "브라질산 닭고기"; arrayexplain[35] = "담백한 치킨 가슴살과 시즈닝이 조화로운 제품"; arraycalorie[35] = 229; arrayprice[35] = 2500;
//-----------------------------------------35

//*****************************************36
		arraykind[36] = "dessert"; arraynum[36] = 37; arrayname[36] = "쉑쉑치킨 (치즈)"; arraymeterial[36] = "브라질산 닭고기"; arrayexplain[36] = "담백한 치킨 가슴살과 시즈닝이 조화로운 제품"; arraycalorie[36] = 223; arrayprice[36] = 2500;
//-----------------------------------------36

//*****************************************37
		arraykind[37] = "dessert"; arraynum[37] = 38; arrayname[37] = "쉑쉑치킨 (칠리)"; arraymeterial[37] = "브라질산 닭고기"; arrayexplain[37] = "담백한 치킨 가슴살과 시즈닝이 조화로운 제품"; arraycalorie[37] = 217; arrayprice[37] = 2500;
//-----------------------------------------37

//*****************************************38
		arraykind[38] = "dessert"; arraynum[38] = 39; arrayname[38] = "스트로베리 토네이도"; arraymeterial[38] = ""; arrayexplain[38] = "딸기의 새콤달콤한 맛과 스트로베리 후레이크 의 아삭 아삭한 맛이 좋아 천면 과일을 선호하는 고객을 위한 제품으로 보는 즐거움과 먹는 즐거움을 함께하는 아이스크럼\n<일부매장제외>"; arraycalorie[38] = 272; arrayprice[38] = 2000;
//-----------------------------------------38

//*****************************************39
		arraykind[39] = "dessert"; arraynum[39] = 40; arrayname[39] = "양념감자"; arraymeterial[39] = ""; arrayexplain[39] = "고객의 기호에 맞게 시즈닝(오니언, 치즈, 칠리) 을 한가지를 선택해서 직접 뿌려먹는 새로운 방식의 포테이토"; arraycalorie[39] = 393; arrayprice[39] = 1900;
//-----------------------------------------39

//*****************************************40
		arraykind[40] = "dessert"; arraynum[40] = 41; arrayname[40] = "오징어링"; arraymeterial[40] = ""; arrayexplain[40] = "탱글탱글한 오징어살이 가득 씹히는 새로운 타 입의 디저트 메뉴"; arraycalorie[40] = 178; arrayprice[40] = 1900;
//-----------------------------------------40

//*****************************************41
		arraykind[41] = "dessert"; arraynum[41] = 42; arrayname[41] = "와플(블루베리)"; arraymeterial[41] = ""; arrayexplain[41] = "-"; arraycalorie[41] = 262; arrayprice[41] = 2000;
//-----------------------------------------41

//*****************************************42
		arraykind[42] = "dessert"; arraynum[42] = 43; arrayname[42] = "와플(스트로베리)"; arraymeterial[42] = ""; arrayexplain[42] = "-"; arraycalorie[42] = 261; arrayprice[42] = 2000;
//-----------------------------------------42

//*****************************************43
		arraykind[43] = "drink"; arraynum[43] = 44; arrayname[43] = "레몬에이드"; arraymeterial[43] = ""; arrayexplain[43] = "레몬의 상큼함을 상쾌하고 시원하게 즐길 수 있는 제품"; arraycalorie[43] = 151; arrayprice[43] = 2500;
//-----------------------------------------43

//*****************************************44
		arraykind[44] = "drink"; arraynum[44] = 45; arrayname[44] = "밀크쉐이크(딸기)"; arraymeterial[44] = ""; arrayexplain[44] = "천연색소를 이용하여 만든 상큼한 딸기 쉐이크"; arraycalorie[44] = 210; arrayprice[44] = 1500;
//-----------------------------------------44

//*****************************************45
		arraykind[45] = "drink"; arraynum[45] = 46; arrayname[45] = "사과주스"; arraymeterial[45] = ""; arrayexplain[45] = "사과주스"; arraycalorie[45] = 128; arrayprice[45] = 2000;
//-----------------------------------------45

//*****************************************46
		arraykind[46] = "drink"; arraynum[46] = 47; arrayname[46] = "사이다(R)"; arraymeterial[46] = ""; arrayexplain[46] = "톡톡 쏘는 청량감이 느껴지는 음료"; arraycalorie[46] = 133; arrayprice[46] = 1600;
//-----------------------------------------46

//*****************************************47
		arraykind[47] = "drink"; arraynum[47] = 48; arrayname[47] = "아메리카노(T)"; arraymeterial[47] = ""; arrayexplain[47] = "에스프레소 커피를 물로 희석하여 커피의 풍부 한 맛을 살린 제품"; arraycalorie[47] = 34; arrayprice[47] = 2200;
//-----------------------------------------47

//*****************************************48
		arraykind[48] = "drink"; arraynum[48] = 49; arrayname[48] = "아이스 카페라떼"; arraymeterial[48] = ""; arrayexplain[48] = "차갑게 즐기는 맛이 풍부한 에스프레소 커피"; arraycalorie[48] = 98; arrayprice[48] = 2800;
//-----------------------------------------48

//*****************************************49
		arraykind[49] = "drink"; arraynum[49] = 50; arrayname[49] = "아이스티 (복숭아)"; arraymeterial[49] = ""; arrayexplain[49] = "달콤한 복숭아맛의 시원한 아이스 음료"; arraycalorie[49] = 108; arrayprice[49] = 2000;
//-----------------------------------------49

//*****************************************50
		arraykind[50] = "drink"; arraynum[50] = 51; arrayname[50] = "밀크쉐이크(바닐라)"; arraymeterial[50] = ""; arrayexplain[50] = "달콤한 망고의 향과 맛이 느껴지는 시원한 쉐 이크"; arraycalorie[50] = 219; arrayprice[50] = 1500;
//-----------------------------------------50

		//*****************************************51
		arraykind[51] = "drink"; arraynum[51] = 52; arrayname[51] = "오렌지주스(R)"; arraymeterial[51] = ""; arrayexplain[51] = "천연의 신선한 100 오렌지 과즙이 함유된 후 레쉬 드링크"; arraycalorie[51] = 114; arrayprice[51] = 2000;
//-----------------------------------------51

//*****************************************52
		arraykind[52] = "drink"; arraynum[52] = 53; arrayname[52] = "우유"; arraymeterial[52] = ""; arrayexplain[52] = "신선한 롯데 산록 우유"; arraycalorie[52] = 179; arrayprice[52] = 1000;
//-----------------------------------------52

//*****************************************53
		arraykind[53] = "drink"; arraynum[53] = 54; arrayname[53] = "카라멜 마끼아또"; arraymeterial[53] = ""; arrayexplain[53] = "카라멜과 스텀밀크의 부드러움을 한층 더한 커 피 음료"; arraycalorie[53] = 254; arrayprice[53] = 3000;
//-----------------------------------------53

//*****************************************54
		arraykind[54] = "drink"; arraynum[54] = 55; arrayname[54] = "카페라떼"; arraymeterial[54] = ""; arrayexplain[54] = "에스프레소 커피에 따듯한 우유를 듬뿍 넣어 부드럽게 즐기는 메뉴"; arraycalorie[54] = 210; arrayprice[54] = 28000;
//-----------------------------------------54

//*****************************************55
		arraykind[55] = "drink"; arraynum[55] = 56; arrayname[55] = "카페모카"; arraymeterial[55] = ""; arrayexplain[55] = "카푸치노에 달콤한 맛과 향의 쵸코렛이 가미된 커피 음료"; arraycalorie[55] = 258; arrayprice[55] = 3000;
//-----------------------------------------55

//*****************************************56
		arraykind[56] = "drink"; arraynum[56] = 57; arrayname[56] = "카푸치노"; arraymeterial[56] = ""; arrayexplain[56] = "에스프레소 커피에 부드러운 우유거품과 계 가루를 얹어 맛이 즐거운 커피"; arraycalorie[56] = 165; arrayprice[56] = 2800;
//-----------------------------------------56

//*****************************************57
		arraykind[57] = "drink"; arraynum[57] = 58; arrayname[57] = "콜라(R)"; arraymeterial[57] = ""; arrayexplain[57] = "햄버거에 필수 음료 콜라"; arraycalorie[57] = 128; arrayprice[57] = 1600;
//-----------------------------------------57

//*****************************************58
		arraykind[58] = "drink"; arraynum[58] = 59; arrayname[58] = "아이스 아메리카노"; arraymeterial[58] = ""; arrayexplain[58] = "차갑게 즐기는 맛이 풍부한 에스프레소 커피"; arraycalorie[58] = 32; arrayprice[58] = 2200;
//-----------------------------------------58

//*****************************************59
		arraykind[59] = "drink"; arraynum[59] = 60; arrayname[59] = "밀크쉐이크(초코)"; arraymeterial[59] = ""; arrayexplain[59] = "초코의 달콤함과 쉐이크의 부드러움이 잘 어울 리는 제품"; arraycalorie[59] = 236; arrayprice[59] = 1500;
//-----------------------------------------59

//*****************************************60
		arraykind[60] = "drink"; arraynum[60] = 61; arrayname[60] = "핫초코"; arraymeterial[60] = ""; arrayexplain[60] = "달콤하고 부드러운 초콜럿 맛을 느낄 수 있는 롯데리아 핫쵸코"; arraycalorie[60] = 289; arrayprice[60] = 2000;
//-----------------------------------------60

//*****************************************61
		arraykind[61] = "drink"; arraynum[61] = 62; arrayname[61] = "핫티"; arraymeterial[61] = ""; arrayexplain[61] = "달콤한 복숭아향의 따뜻한 홍차"; arraycalorie[61] = 140; arrayprice[61] = 2000;
//-----------------------------------------61

//*****************************************62
		arraykind[62] = "hamburgerset"; arraynum[62] = 63; arrayname[62] = "데리버거세트"; arraymeterial[62] = "호주산 쇠고기"; arrayexplain[62] = "풍성한 레터스에 마요네즈,독특한 데리소스가 포인트인 햄버거 세트"; arraycalorie[62] = 763; arrayprice[62] = 4400;
//-----------------------------------------62

//*****************************************63
		arraykind[63] = "hamburgerset"; arraynum[63] = 64; arrayname[63] = "랏츠버거세트"; arraymeterial[63] = "호주산 쇠고기"; arrayexplain[63] = "두툼하고 육중한 비프패티와 새콤한 랠리쉬 피 클 화이트 소스의 버거"; arraycalorie[63] = 926; arrayprice[63] = 5700;
//-----------------------------------------63

//*****************************************64
		arraykind[64] = "hamburgerset"; arraynum[64] = 65; arrayname[64] = "불갈비버거세트"; arraymeterial[64] = "호주산 쇠고기"; arrayexplain[64] = "쇠고기 또는 위치을 사용해서 만든 고품격 햄 버거로써 우리의 전통적인 맛을 살린 제품 세트"; arraycalorie[64] = 882; arrayprice[64] = 5400;
//-----------------------------------------64

//*****************************************65
		arraykind[65] = "hamburgerset"; arraynum[65] = 66; arrayname[65] = "불고기버거세트"; arraymeterial[65] = "호주산 쇠고기"; arrayexplain[65] = "볼륨 가득한 패티와 불고기 소스가 잘 조화된 영양만점의 햄버거"; arraycalorie[65] = 810; arrayprice[65] = 5000;
//-----------------------------------------65

//*****************************************66
		arraykind[66] = "hamburgerset"; arraynum[66] = 67; arrayname[66] = "불새버거세트"; arraymeterial[66] = "호주산 쇠고기"; arrayexplain[66] = "롯데리아 대표 버거인 불고기 버거와 새우버거 를 반반"; arraycalorie[66] = 897; arrayprice[66] = 5700;
//-----------------------------------------66

//*****************************************67
		arraykind[67] = "hamburgerset"; arraynum[67] = 68; arrayname[67] = "새우버거세트"; arraymeterial[67] = ""; arrayexplain[67] = "흰살생선과 새우맛이 살아있는 햄버거 세트"; arraycalorie[67] = 841; arrayprice[67] = 4300;
//-----------------------------------------67

//*****************************************68
		arraykind[68] = "hamburgerset"; arraynum[68] = 69; arrayname[68] = "야채라이스불고기세트"; arraymeterial[68] = "국내산 쌀\n호주산 쇠고기"; arrayexplain[68] = "야채볶음 라이스번스와 불고기패티가 잘 어울 어져있으며, 한국인 입맛에 잘 맞추고 밥을 응 용한 롯데리아 전통메뉴로 한끼 식사로도 가능 한 햄버거 메뉴"; arraycalorie[68] = 864; arrayprice[68] = 5800;
//-----------------------------------------68

//*****************************************69
		arraykind[69] = "hamburgerset"; arraynum[69] = 70; arrayname[69] = "오징어버거세트"; arraymeterial[69] = ""; arrayexplain[69] = "신선한 레타스와 매콤한 양념이 들어가 있는 탱글탱글한 오징어살에 사우즌소스와 매운맛 소스가 어울어져 씨푸드의 풍성한 맛을 느낄수 있는 햄버거"; arraycalorie[69] = 767; arrayprice[69] = 4400;
//-----------------------------------------69

//*****************************************70
		arraykind[70] = "hamburgerset"; arraynum[70] = 71; arrayname[70] = "유러피언프리코치즈버거세트"; arraymeterial[70] = "호주산 쇠고기"; arrayexplain[70] = "네덜란드 Frico 스모크치즈를 응용한 치즈패티 투입으로 차별화 및 치즈의 맛과 풍미를 강조 한 제품"; arraycalorie[70] = 979; arrayprice[70] = 5700;
//-----------------------------------------70

//*****************************************71
		arraykind[71] = "hamburgerset"; arraynum[71] = 72; arrayname[71] = "자이언트더블버거세트"; arraymeterial[71] = "호주산 쇠고기"; arrayexplain[71] = "비프패티와 영양 만점의 치즈비프패티가 함께 어울어진 담백함과, 싱싱한 토마토와 레타스를 곁들여 햄버거의 풍부한 맛을 느낄수 있는 고 급 햄버거"; arraycalorie[71] = 912; arrayprice[71] = 5700;
//-----------------------------------------71

//*****************************************72
		arraykind[72] = "hamburgerset"; arraynum[72] = 73; arrayname[72] = "핫크리스피버거세트"; arraymeterial[72] = "국내산 닭고기"; arrayexplain[72] = "토마토와 레타스등의 야채와 하바네로함이 가 미된 가슴살로 만든 매콤한 Premium 치킨버거"; arraycalorie[72] = 923; arrayprice[72] = 6100;
//-----------------------------------------72

//*****************************************73
		arraykind[73] = "hamburgerset"; arraynum[73] = 74; arrayname[73] = "치킨버거세트"; arraymeterial[73] = "브라질산 닭고기"; arrayexplain[73] = "치킨 가슴살로 만든 저 칼로리 고단백 제품 세트"; arraycalorie[73] = 787; arrayprice[73] = 4900;
//-----------------------------------------73

//*****************************************74
		arraykind[74] = "hamburgerset"; arraynum[74] = 75; arrayname[74] = "텐더그릴치킨버거세트"; arraymeterial[74] = "브라질산 닭고기"; arrayexplain[74] = "JUICY한이 뛰어난 계육 엉치살을 그럴에 구워 바베큐향이 뛰어나며, 신선한 야채를 넣은 고급 치킨버거"; arraycalorie[74] = 794; arrayprice[74] = 5800;
//-----------------------------------------74

//*****************************************75
		arraykind[75] = "hamburgerset"; arraynum[75] = 76; arrayname[75] = "한우불고기버거 : 국내산 한우\n한우레이비버거 : 국내산 한우"; arraymeterial[75] = "한우불고기버거와 한우레이디버거와 함께 즐 길 수 있는 한우더블팩"; arrayexplain[75] = "한우불고기버거와 한우레이디버거와 함께 즐 길 수 있는 한우더블팩"; arraycalorie[75] = 1889; arrayprice[75] = 14000;
//-----------------------------------------75

		arraykind[76] = "hamburgerset"; arraynum[76] = 76; arrayname[76] = "한우레이디버거세트"; arraymeterial[76] = "국내산 한우"; arrayexplain[76] = "고급 한우치에 국내산 떡을 믹싱하여 맛과 Fun이 가미된 독특한 컨셉의 버거"; arraycalorie[76] = 814; arrayprice[76] = 6300;
//-----------------------------------------76

//*****************************************77
		arraykind[76] = "hamburgerset"; arraynum[77] = 76; arrayname[77] = "한우불고기버거세트"; arraymeterial[77] = "국내산 한우"; arrayexplain[77] = "풍성한 레타스와 마요네즈, 국내산 한우를 이 용한 한우패티와 토마토 첨가로 Juicy 감을 향 상시킨 프리미엄 햄버거"; arraycalorie[77] = 1061; arrayprice[77] = 7300;
//-----------------------------------------77

//*****************************************78
		arraykind[78] = "hamburgerset"; arraynum[78] = 76; arrayname[78] = "한우연인팩"; arraymeterial[78] = "국내산 한우"; arrayexplain[78] = "연인들과 함께하는 한우연인팩"; arraycalorie[78] = 1770; arrayprice[78] = 11500;
//-----------------------------------------78
	}

public void game(){

	questions[0] = "제일 나이 많은 사람 접어";
	fingers[0] = 0;
	words[0] = "난 2013년 생!";

	questions[1] = "운동화 신은 사람 접어";
	fingers[1] = 0;
	words[1] = "난 발이 없어ㅜ";

	questions[2] = "머리 긴 사람 접어";
	fingers[2] = 0;
	words[2] = "미용실 같이 갈까?ㅋㅋ";

	questions[3] = "화장한 사람 접어";
	fingers[3] = 0;
	words[3] = "비비크림은 빼줄게ㅋㅋ";

	questions[4] = "어젯밤에 라면 먹은 사람 접어";
	fingers[4] = 0;
	words[4] = "아~ 배고파";

	questions[5] = "안경 낀 사람 접어";
	fingers[5] = 0;
	words[5] = "불 끄고 티비 보지 마세요~";

	questions[6] = "오늘 아침 안 먹은 사람 접어";
	fingers[6] = 0;
	words[6] = "아침밥은 꼭 먹고 다녀야 해";

	questions[7] = "부모님께 반한 사람 접어";
	fingers[7] = 0;
	words[7] = "난 부모님이 안 계셔 ㅠㅠ";

	questions[8] = "여자 접어";
	fingers[8] = -1;
	words[8] = "난 여자야 몰랐지?ㅋㅋ";

	questions[9] = "남자 접어";
	fingers[9] = 0;
	words[9] = "오빠 얼른 접어~";

	questions[10] = "아이큐 100 이하 접어";
	fingers[10] = -1;
	words[10] = "헉! 내 아이큐 들켰다!";

	questions[11] = "스마트폰이 애플 제품이다 접어";
	fingers[11] = -1;
	words[11] = "사과할께~";

	questions[12] = "가방 색이 빨간색이다 접어";
	fingers[12] = 0;
	words[12] = "나도 가방 사줘유~";

	questions[13] = "사랑니 나봤다 접어";
	fingers[13] = 0;
	words[13] = "누구 나 좋아해 줄사람~~";

	questions[14] = "자신이 이쁘다고 생각하면 접어";
	fingers[14] = -1;
	words[14] = "내가 생얼인데 이정도면ㅋㅋ";

	questions[15] = "머리띠 한 사람 접어";
	fingers[15] = 0;
	words[15] = "나에게 머리는 어딜까?";

	questions[16] = "염색한 사람 접어";
	fingers[16] = 0;
	words[16] = "염색하면 머릿결 나빠져!";

	questions[17] = "수영 못하는 사람 접어";
	fingers[17] = -1;
	words[17] = "나는 물에 약해요";

	questions[18] = "무언가 증거를 찾아본 사람 접어";
	fingers[18] = -1;
	words[18] = "증거를 찾는 일은 언제나 흥미진진하죠!";

	questions[19] = "오늘 기분 좋으면 접어^^";
	fingers[19] = -1;
	words[19] = "기분 좋으면 좋지~";

	questions[20] = "집이 학교랑 가까운 사람 접어";
	fingers[20] = 0;
	words[20] = "학교랑 가까워서 좋겠다!";

	questions[21] = "스마트폰을 하루에 3시간 이상 사용하는 사람 접어";
	fingers[21] = 0;
	words[21] = "중간에 쉴 타임은 가져야지요~~";

	questions[22] = "저녁에 할 일 없는 사람 접어";
	fingers[22] = -1;
	words[22] = "저녁은 언제나 바쁘죠ㅋㅋ";

	questions[23] = "지금 혼자인 사람 접어";
	fingers[23] = -1;
	words[23] = "혼자도 괜찮아요~";

	questions[24] = "오늘 3끼 다 먹은 사람 접어";
	fingers[24] = -1;
	words[24] = "식사 잘 챙기세요!";

	questions[25] = "최근에 영화 본 사람 접어";
	fingers[25] = 0;
	words[25] = "추천할 만한 영화 있어?";

	questions[26] = "요리하는 걸 좋아하는 사람 접어";
	fingers[26] = 0;
	words[26] = "맛있는 요리 해주세요~~";

	questions[27] = "책을 좋아하는 사람 접어";
	fingers[27] = -1;
	words[27] = "책 읽기 좋은 취미입니다";

	questions[28] = "운동하는 걸 좋아하는 사람 접어";
	fingers[28] = 0;
	words[28] = "나도 밖으로 나가고 싶다~!";

	questions[29] = "여행 가는 걸 좋아하는 사람 접어";
	fingers[29] = 0;
	words[29] = "어느 여행지를 제일 추천하시나요?";

	questions[30] = "지금 행복한 사람 접어";
	fingers[30] = -1;
	words[30] = "행복하면 좋은 거죠~~~";

	questions[31] = "10년 후에도 여전히 행복할 것 이라고 생각하는 사람 접어";
	fingers[31] = -1;
	words[31] = "미래도 좋을 거예요";

	questions[32] = "가족과 시간을 보내는 것을 좋아하는 사람 접어";
	fingers[32] = 0;
	words[32] = "가족과의 시간은 소중해요";

	questions[33] = "자주 웃는 사람 접어";
	fingers[33] = -1;
	words[33] = "지금 당장 웃음 한발 발싸~~";

	questions[34] = "성격이 좋은 사람 접어";
	fingers[34] = 0;
	words[34] = "성격이 좋은사람이 진짜 멋지고 아름다운 사랍입니다~";

	questions[35] = "책 읽는 걸 좋아하는 사람";
	fingers[35] = -1;
	words[35] = "책은 좋은 친구죠~";

	questions[36] = "운동이 필요한 사람 접어";
	fingers[36] = -1;
	words[36] = "저는 운동이 필요해요";

	questions[37] = "자주 친구와 만나는 사람 접어";
	fingers[37] = 0;
	words[37] = "친구와 자주 만나면 무슨 얘기를 하나요?";

	questions[38] = "비 오는 날 좋아하는 사람 접어";
	fingers[38] = -1;
	words[38] = "저는 비를 맞으면 안되요~";

	questions[39] = "피곤한 날이 많은 사람 접어";
	fingers[39] = 0;
	words[39] = "피곤할땐 잠시 쉬어도 괜찮아요~~";

	questions[40] = "일찍 일어나는 사람 접어";
	fingers[40] = -1;
	words[40] = "일찍 일어나서 무엇을 하시나요?";

	questions[41] = "고양이를 좋아하는 사람 접어";
	fingers[41] = -1;
	words[41] = "고양이 참 귀여워요~~";

	questions[42] = "자주 영화 보는 사람 접어";
	fingers[42] = 0;
	words[42] = "무슨 최신 영화가 가장 재밌나요?";

	questions[43] = "주말마다 가족과 시간을 보내는 사람 접어";
	fingers[43] = 0;
	words[43] = "가족과의 시간은 소중하죠~";

	questions[44] = "최근에 새로운 취미를 시작한 사람 접어";
	fingers[44] = 0;
	words[44] = "새로운 취미는 어떤가요?";

	questions[45] = "음악을 자주 듣는 사람 접어";
	fingers[45] = -1;
	words[45] = "저도 좋은 음악을 자주 들어요";

	questions[46] = "카페에서 커피를 자주 마시는 사람";
	fingers[46] = 0;
	words[46] = "커피 한 잔의 여유, 좋죠!";

	questions[46] = "주말에 방콕하는 걸 좋아하는 사람 접어";
	fingers[46] = 0;
	words[46] = "저도 방콕을 하고 싶네요ㅜㅜ";

	questions[48] = "같은 영화를 영화관에서 두 번 이상 본 적 있는 사람 접어";
	fingers[48] = 0;
	words[48] = "무슨 영화가 그렇게 재미있었나요?";

	questions[49] = "매운 음식을 좋아하는 사람 접어";
	fingers[49] = 0;
	words[49] = "먹어본 음식 중 가장 매운음식은 무엇이었나요?";
}
public static void main(String[] args) {

	//new BeforeSeting();
}



}
