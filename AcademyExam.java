package academyboard;

import java.util.Scanner;

import academyboard.dao.ReadAllMemberDAO;
import academyboard.dto.MemberDTO;
import academyboard.service.CreateMemberService;
import academyboard.service.LoginService;
import academyboard.service.MemberService;

public class AcademyExam {

	public static Scanner inputStr = new Scanner(System.in);
	public static Scanner inputInt = new Scanner(System.in);
	public static Scanner inputLine = new Scanner(System.in);
	
	public static MemberDTO session = null;

	public static void main(String[] args) {
		boolean run = true;

		while (run) {
			System.out.println("MBC아카데미 회원용 게시판입니다.");
			System.out.println("1. 로그인 | 2. 회원가입 | 3. 게시판 | 4. 내정보 | 5. 프로그램 종료");
			System.out.print("메뉴 선택 : ");
			String select = inputStr.next();
			switch (select) {
			case "1":
				System.out.println("로그인 화면으로 이동합니다.");
				LoginService loginService = new LoginService();
				session = loginService.menu(inputStr);
				break;
			case "2":
				System.out.println("회원가입 화면으로 이동합니다.");
				CreateMemberService createMemberService = new CreateMemberService();
				createMemberService.createMenu(inputStr);
				break;
			case "3":
				System.out.println("게시판 화면으로 이동합니다.");
				if (session != null) {
					System.out.println("접속 유저 : " + session.getMname() + "님");
				}else {
					System.out.println("로그인 상태가 아닙니다. 로그인 또는 회원가입을 하고 이용해 주세요.");
				}
				break;
			case "4":
				System.out.println("내정보 화면으로 이동합니다.");
				if (session != null) {
					System.out.println("접속 유저 : " + session.getMname() + "님");
					MemberService memberService = new MemberService();
					session = memberService.memberMenu(inputStr, session);
				}else {
					System.out.println("로그인 상태가 아닙니다. 로그인 또는 회원가입을 하고 이용해 주세요.");
				}
				break;
			case "5":
				System.out.println("프로그램을 종료합니다.");
				run = false;
				break;
			case "99" :
				System.out.println("히든 메뉴 입니다. 전체 회원을 보여드리겠습니다.");
				ReadAllMemberDAO readAllMemberDAO = new ReadAllMemberDAO();
				readAllMemberDAO.readAll();
			default:
				System.out.println("1~4까지의 숫자만 입력해주세요");
				break;
			}
		}

	} // main 메서드 종료

} // class 종료
