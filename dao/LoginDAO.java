package academyboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import academyboard.dto.MemberDTO;

public class LoginDAO {
	// �ʵ� ����
	public MemberDTO memberDTO = new MemberDTO();
	public Connection connection = null;
	public Statement statement = null;
	public PreparedStatement preparedStatement = null;
	public ResultSet resultSet = null;
	public int result = 0;

	// �⺻ ������
	public LoginDAO() {
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

	// dao�� ����ϴ� �޼���� �������� ����Ͽ� ResultSet, PreparedStatement, Statement �� �ݴ� �뵵�� �޼���
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

	public MemberDTO login(String id, String pw) {
		try {
			String sql = "select * from member where id = ? and pw = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, pw);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMno(resultSet.getInt("mno"));
				memberDTO.setMname(resultSet.getString("mname"));
				memberDTO.setId(resultSet.getString("id"));
				memberDTO.setPw(resultSet.getString("pw"));
				memberDTO.setEmail(resultSet.getNString("email"));
				memberDTO.setRegidate(resultSet.getDate("regidate"));
				return memberDTO;
			}

		} catch (SQLException e) {
			System.out.println("�������� login()�޼��带 Ȯ�����ּ���.");
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

}
