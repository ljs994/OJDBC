package academyboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import academyboard.dto.MemberDTO;

public class CreateMemberDAO {
	// 필드 생성
	public MemberDTO memberDTO = new MemberDTO();
	public Connection connection = null;
	public Statement statement = null;
	public PreparedStatement preparedStatement = null;
	public ResultSet resultSet = null;
	public int result = 0;

	// 기본 생성자
	public CreateMemberDAO() {
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

	public void createMenu(Scanner inputStr) {
		boolean run = true;
		
		try {
			System.out.print("이름 : ");
			memberDTO.setMname(inputStr.next());
			while (run) {
				System.out.print("사용할 ID : ");
				String id = inputStr.next();
				String sql = "select count (*) from member where id = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, id);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next() && resultSet.getInt(1) > 0) {
					System.out.println("이미 존재하는 ID입니다. 다시 입력해주세요.");
				} else {
					memberDTO.setId(id);
					run = false;
				}
				resultSet.close();
				preparedStatement.close();
			}
			System.out.print("사용할 PW : ");
			memberDTO.setPw(inputStr.next());
			
			System.out.print("사용할 Email : ");
			memberDTO.setEmail(inputStr.next());
			
			String isql = "insert into member (mno, mname, id, pw, email, regidate) "
					+ "values (member_seq.nextval, ?, ?, ?, ?, sysdate)";
			preparedStatement = connection.prepareStatement(isql);
			preparedStatement.setString(1, memberDTO.getMname());
			preparedStatement.setString(2, memberDTO.getId());
			preparedStatement.setString(3, memberDTO.getPw());
			preparedStatement.setString(4, memberDTO.getEmail());

			result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println(result + "명의 회원이 성공적으로 등록되었습니다.");
				connection.commit();
			} else {
				System.out.println("회원 등록에 실패했습니다.");
				connection.rollback();
			}
		} catch (SQLException e) {
			System.out.println("에러발생 createMenu()메서드를 확인해주세요.");
			e.printStackTrace();
		} finally {
			close();
		}

	}

}
