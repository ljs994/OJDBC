package academyboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import academyboard.dto.MemberDTO;

public class DeleteMemberDAO {
	// �ʵ� ����
		public MemberDTO memberDTO = new MemberDTO();
		public Connection connection = null;
		public Statement statement = null;
		public PreparedStatement preparedStatement = null;
		public ResultSet resultSet = null;
		public int result = 0;

		// �⺻ ������
		public DeleteMemberDAO() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.177:1521:xe", "actest", "actest");
			} catch (ClassNotFoundException e) {
				System.out.println("�����߻� ����̹� �̸��̳�, ojdbc6.jar ������ �߸� �Ǿ����ϴ�. MemberDAO�� �⺻ �����ڸ� Ȯ���ϼ���.");
				System.exit(0); // ��������
			} catch (SQLException e) {
				System.out.println("URL, ID, PW�� �߸��Ǿ����ϴ�. MemberDAO�� �⺻ �����ڸ� Ȯ���ϼ���.");
				System.exit(0); // ��������;
			}
		} // �⺻ ������ ����

		public void close() {
			try {
				if (resultSet != null)
					resultSet.close();
				System.out.println("resultSet.close(); ����");
				if (preparedStatement != null)
					preparedStatement.close();
				System.out.println("preparedStatement.close(); ����");
				if (statement != null)
					statement.close();
				System.out.println("statement.close(); ����");
				if (connection != null)
					connection.close();
				System.out.println("connection.close(); ����");
			} catch (SQLException e) {
				System.out.println("�����߻� close()�޼��带 Ȯ�����ּ���.");
				e.printStackTrace();
			}
		} // close �޼��� ����

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
				System.out.println("���� �߻� delete()�޼��带 Ȯ���ϼ���.");
				e.printStackTrace();
			}finally {
				close();
			}
			return false;
			
			
		}

}
