package academyboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import academyboard.dto.MemberDTO;

public class DeleteMemberDAO {
	// 필드 생성
		public MemberDTO memberDTO = new MemberDTO();
		public Connection connection = null;
		public Statement statement = null;
		public PreparedStatement preparedStatement = null;
		public ResultSet resultSet = null;
		public int result = 0;

		// 기본 생성자
		public DeleteMemberDAO() {
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

		public boolean delete(MemberDTO loginOk) {
			try {
				String sql = "delete from member where id =? and pw =?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, loginOk.getId());
				preparedStatement.setString(2, loginOk.getPw());
				
				int result = preparedStatement.executeUpdate();
				
				if (result > 0) {
					connection.commit();
					return true;
				} else {
					connection.rollback();
				}
			} catch (SQLException e) {
				System.out.println("에러 발생 delete()메서드를 확인하세요.");
				e.printStackTrace();
			}finally {
				close();
			}
			return false;
			
			
		}

}
