package academyboard.service;

import java.util.Scanner;

import academyboard.dao.LoginDAO;
import academyboard.dto.MemberDTO;

public class LoginService {
	
	public LoginDAO loginDAO = new LoginDAO();

	public MemberDTO menu(Scanner inputStr) {
		// �α��� ���� �޴�
		MemberDTO loginOk = null;
		

			System.out.println("=========== �α����� ���� �ϰڽ��ϴ�. ===========");
			System.out.print("ID : ");
			String id = inputStr.next();
			System.out.print("PW : ");
			String pw = inputStr.next();
			
			loginOk = loginDAO.login(id, pw);
			if(loginOk != null) {
				System.out.println(loginOk.getMname() + "�� ȯ���մϴ�.");
				return loginOk;
			}else {
				System.out.println("�α��� ����");
				return null;
			}
		
		
	}

}
