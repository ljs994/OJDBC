package academyboard.service;

import java.util.Scanner;

import academyboard.dao.CreateMemberDAO;

public class CreateMemberService { // ȸ������ �޴�

	public CreateMemberDAO createMemberDAO = new CreateMemberDAO();

	public void createMenu(Scanner inputStr) {

		System.out.println("=========== ȸ�������� �����ϰڽ��ϴ�. ===========");
		createMemberDAO.createMenu(inputStr);

	}

}
