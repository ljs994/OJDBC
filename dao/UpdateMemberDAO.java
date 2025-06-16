package academyboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import academyboard.dto.MemberDTO;

public class UpdateMemberDAO {

	// 필드 생성
	public MemberDTO memberDTO = new MemberDTO();
	public Connection connection = null;
	public Statement statement = null;
	public PreparedStatement preparedStatement = null;
	public ResultSet resultSet = null;
	public int result = 0;

	// 기본 생성자
	public UpdateMemberDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.177:1521:xe", "actest", "actest");
		} catch (ClassNotFoundException e) {
			System.out.println("에러발생 드라이버 이름이나, ojdbc6.jar 파일이 잘못 되었습니다. MemberDAO에 기본 생성자를 확인하세요.");
			System.exit(0); // 강제종료
		} catch (SQLException e) {
			System.out.println("URL, ID, PW가 잘못되었습니다. MemberDAO에 기본 생성자를 확인하세요.");
			System.exit(0); // 강제종료;
		}
	} // 기본 생성자 종료

	// dao를 사용하는 메서드ㅡ 마지막에 사용하여 ResultSet, PreparedStatement, Statement 을 닫는 용도의 메서드
	public void close() {
		try {
			if (resultSet != null)
				resultSet.close();
			System.out.println("resultSet.close(); 성공");
			if (preparedStatement != null)
				preparedStatement.close();
			System.out.println("preparedStatement.close(); 성공");
			if (statement != null)
				statement.close();
			System.out.println("statement.close(); 성공");
			if (connection != null)
				connection.close();
			System.out.println("connection.close(); 성공");
		} catch (SQLException e) {
			System.out.println("에러발생 close()메서드를 확인해주세요.");
			e.printStackTrace();
		}
	} // close 메서드 종료

	public void update(Scanner inputStr, MemberDTO loginOk) {
		try {
			System.out.print("새로 사용할 ID: ");
			String newId = inputStr.next();

			// ID 중복 검사
			String sql = "select count(*) from member where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next() && resultSet.getInt(1) > 0) {
				System.out.println("이미 존재하는 ID입니다.");
				return;
			}
			resultSet.close();
			preparedStatement.close();

			System.out.print("새로운 PW: ");
			String newPw = inputStr.next();

			System.out.println("기존 ID: " + loginOk.getId());
			System.out.println("신규 ID: " + newId);
			System.out.println("신규 PW: " + newPw);

			String usql = "update member set id = ?, pw = ? where id = ?";
			preparedStatement = connection.prepareStatement(usql);
			preparedStatement.setString(1, newId);
			preparedStatement.setString(2, newPw);
			preparedStatement.setString(3, loginOk.getId());

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println("수정완료");
				connection.commit();
				loginOk.setId(newId);
				loginOk.setPw(newPw);
			} else {
				System.out.println("수정실패");
				connection.rollback();
			}

		} catch (SQLException e) {
			System.out.println("에러발생 update()메서드를 확인하세요.");
			e.printStackTrace();
		} finally {
			close();
		}
	}

}
