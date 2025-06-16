package academyboard.service;

import java.util.Scanner;

import academyboard.dao.LoginDAO;
import academyboard.dto.MemberDTO;

public class LoginService {
	
	public LoginDAO loginDAO = new LoginDAO();

	public MemberDTO menu(Scanner inputStr) {
		// 로그인 관리 메뉴
		MemberDTO loginOk = null;
		

			System.out.println("=========== 로그인을 진행 하겠습니다. ===========");
			System.out.print("ID : ");
			String id = inputStr.next();
			System.out.print("PW : ");
			String pw = inputStr.next();
			
			loginOk = loginDAO.login(id, pw);
			if(loginOk != null) {
				System.out.println(loginOk.getMname() + "님 환영합니다.");
				return loginOk;
			}else {
				System.out.println("로그인 실패");
				return null;
			}
		
		
	}

}
