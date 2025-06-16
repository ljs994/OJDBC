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
			System.out.println(loginOk.getMname() + "님 메뉴를 보여드리겠습니다.");
			System.out.println("1. 내 정보보기 | 2. 회원정보 수정 | 3. 회원탈퇴 | 4. 뒤로가기");
			System.out.print("선택 : ");
			String select = inputStr.next();
			switch (select) {
			case "1":
				System.out.println(loginOk.getMname() + "님의 정보를 확인 하기 위해  ID, PW를 입력 해주세요.");
				System.out.print("ID : ");
				String id = inputStr.next();
				System.out.print("PW : ");
				String pw = inputStr.next();
				if (id.equals(loginOk.getId()) && pw.equals(loginOk.getPw())) {
					System.out.println("확인되었습니다." + loginOk.getMname() + "님의 정보를 보여드리겠습니다.");
					readOneMemberDAO.readOne(loginOk);
				} else {
					System.out.println("ID, PW를 다시 확인해주세요.");
				}
				break;
			case "2":
				System.out.println(loginOk.getMname() + "님의 정보를 수정 하기 위해  ID, PW를 입력 해주세요.");
				System.out.print("ID : ");
				String id1 = inputStr.next();
				System.out.print("PW : ");
				String pw1 = inputStr.next();
				if (id1.equals(loginOk.getId()) && pw1.equals(loginOk.getPw())) {
					System.out.println("확인되었습니다.");
					System.out.println("=============== 수정을 진행 하겠습니다. ===============");
					updateMemberDAO.update(inputStr, loginOk);
				} else {
					System.out.println("ID, PW를 다시 확인해주세요.");
				}
				break;
			case "3":
				System.out.println(loginOk.getMname() + "님의 정보를 확인 하기 위해  ID, PW를 입력 해주세요.");
				System.out.print("ID : ");
				String id2 = inputStr.next();
				System.out.print("PW : ");
				String pw2 = inputStr.next();
				if (id2.equals(loginOk.getId()) && pw2.equals(loginOk.getPw())) {
					System.out.println("확인되었습니다." + loginOk.getMname() + "님의 정보를 삭제하겠습니다.");
					if (deleteMemberDAO.delete(loginOk)) {
						System.out.println("회원 탈퇴가 완료되었습니다. 처음 화면으로 돌아갑니다.");
						loginOk = null;
						run = false;
					} else {
						System.out.println("회원 탈퇴가 되지 않았습니다.");
					}

				} else {
					System.out.println("ID, PW를 다시 확인해주세요.");
				}
				break;
			case "4":
				System.out.println("========== 이전 메뉴로 돌아갑니다. ==========");
				run = false;
				break;
			default:
				System.out.println("1~4까지의 숫자만 입력해주세요.");
			}
		}
		return loginOk;

	} // memberMenu()메서드 종료

}
