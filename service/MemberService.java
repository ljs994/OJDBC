package academyboard.service;

import java.util.Scanner;

import academyboard.dao.DeleteMemberDAO;
import academyboard.dao.ReadOneMemberDAO;
import academyboard.dao.UpdateMemberDAO;
import academyboard.dto.MemberDTO;

public class MemberService {

	public ReadOneMemberDAO readOneMemberDAO = new ReadOneMemberDAO();
	public UpdateMemberDAO updateMemberDAO = new UpdateMemberDAO();
	public DeleteMemberDAO deleteMemberDAO = new DeleteMemberDAO();

	public MemberDTO memberMenu(Scanner inputStr, MemberDTO loginOk) {
		boolean run = true;

		while (run) {
			System.out.println(loginOk.getMname() + "�� �޴��� �����帮�ڽ��ϴ�.");
			System.out.println("1. �� �������� | 2. ȸ������ ���� | 3. ȸ��Ż�� | 4. �ڷΰ���");
			System.out.print("���� : ");
			String select = inputStr.next();
			switch (select) {
			case "1":
				System.out.println(loginOk.getMname() + "���� ������ Ȯ�� �ϱ� ����  ID, PW�� �Է� ���ּ���.");
				System.out.print("ID : ");
				String id = inputStr.next();
				System.out.print("PW : ");
				String pw = inputStr.next();
				if (id.equals(loginOk.getId()) && pw.equals(loginOk.getPw())) {
					System.out.println("Ȯ�εǾ����ϴ�." + loginOk.getMname() + "���� ������ �����帮�ڽ��ϴ�.");
					readOneMemberDAO.readOne(loginOk);
				} else {
					System.out.println("ID, PW�� �ٽ� Ȯ�����ּ���.");
				}
				break;
			case "2":
				System.out.println(loginOk.getMname() + "���� ������ ���� �ϱ� ����  ID, PW�� �Է� ���ּ���.");
				System.out.print("ID : ");
				String id1 = inputStr.next();
				System.out.print("PW : ");
				String pw1 = inputStr.next();
				if (id1.equals(loginOk.getId()) && pw1.equals(loginOk.getPw())) {
					System.out.println("Ȯ�εǾ����ϴ�.");
					System.out.println("=============== ������ ���� �ϰڽ��ϴ�. ===============");
					updateMemberDAO.update(inputStr, loginOk);
				} else {
					System.out.println("ID, PW�� �ٽ� Ȯ�����ּ���.");
				}
				break;
			case "3":
				System.out.println(loginOk.getMname() + "���� ������ Ȯ�� �ϱ� ����  ID, PW�� �Է� ���ּ���.");
				System.out.print("ID : ");
				String id2 = inputStr.next();
				System.out.print("PW : ");
				String pw2 = inputStr.next();
				if (id2.equals(loginOk.getId()) && pw2.equals(loginOk.getPw())) {
					System.out.println("Ȯ�εǾ����ϴ�." + loginOk.getMname() + "���� ������ �����ϰڽ��ϴ�.");
					if (deleteMemberDAO.delete(loginOk)) {
						System.out.println("ȸ�� Ż�� �Ϸ�Ǿ����ϴ�. ó�� ȭ������ ���ư��ϴ�.");
						loginOk = null;
						run = false;
					} else {
						System.out.println("ȸ�� Ż�� ���� �ʾҽ��ϴ�.");
					}

				} else {
					System.out.println("ID, PW�� �ٽ� Ȯ�����ּ���.");
				}
				break;
			case "4":
				System.out.println("========== ���� �޴��� ���ư��ϴ�. ==========");
				run = false;
				break;
			default:
				System.out.println("1~4������ ���ڸ� �Է����ּ���.");
			}
		}
		return loginOk;

	} // memberMenu()�޼��� ����

}
