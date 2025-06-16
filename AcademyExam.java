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
			System.out.println("MBC��ī���� ȸ���� �Խ����Դϴ�.");
			System.out.println("1. �α��� | 2. ȸ������ | 3. �Խ��� | 4. ������ | 5. ���α׷� ����");
			System.out.print("�޴� ���� : ");
			String select = inputStr.next();
			switch (select) {
			case "1":
				System.out.println("�α��� ȭ������ �̵��մϴ�.");
				LoginService loginService = new LoginService();
				session = loginService.menu(inputStr);
				break;
			case "2":
				System.out.println("ȸ������ ȭ������ �̵��մϴ�.");
				CreateMemberService createMemberService = new CreateMemberService();
				createMemberService.createMenu(inputStr);
				break;
			case "3":
				System.out.println("�Խ��� ȭ������ �̵��մϴ�.");
				if (session != null) {
					System.out.println("���� ���� : " + session.getMname() + "��");
				}else {
					System.out.println("�α��� ���°� �ƴմϴ�. �α��� �Ǵ� ȸ�������� �ϰ� �̿��� �ּ���.");
				}
				break;
			case "4":
				System.out.println("������ ȭ������ �̵��մϴ�.");
				if (session != null) {
					System.out.println("���� ���� : " + session.getMname() + "��");
					MemberService memberService = new MemberService();
					session = memberService.memberMenu(inputStr, session);
				}else {
					System.out.println("�α��� ���°� �ƴմϴ�. �α��� �Ǵ� ȸ�������� �ϰ� �̿��� �ּ���.");
				}
				break;
			case "5":
				System.out.println("���α׷��� �����մϴ�.");
				run = false;
				break;
			case "99" :
				System.out.println("���� �޴� �Դϴ�. ��ü ȸ���� �����帮�ڽ��ϴ�.");
				ReadAllMemberDAO readAllMemberDAO = new ReadAllMemberDAO();
				readAllMemberDAO.readAll();
			default:
				System.out.println("1~4������ ���ڸ� �Է����ּ���");
				break;
			}
		}

	} // main �޼��� ����

} // class ����
