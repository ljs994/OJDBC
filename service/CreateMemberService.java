package academyboard.service;

import java.util.Scanner;

import academyboard.dao.CreateMemberDAO;

public class CreateMemberService { // 회원가입 메뉴

	public CreateMemberDAO createMemberDAO = new CreateMemberDAO();

	public void createMenu(Scanner inputStr) {

		System.out.println("=========== 회원가입을 진행하겠습니다. ===========");
		createMemberDAO.createMenu(inputStr);

	}

}
