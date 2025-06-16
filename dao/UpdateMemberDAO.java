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

	// �ʵ� ����
	public MemberDTO memberDTO = new MemberDTO();
	public Connection connection = null;
	public Statement statement = null;
	public PreparedStatement preparedStatement = null;
	public ResultSet resultSet = null;
	public int result = 0;

	// �⺻ ������
	public UpdateMemberDAO() {
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

	public void update(Scanner inputStr, MemberDTO loginOk) {
		try {
			System.out.print("���� ����� ID: ");
			String newId = inputStr.next();

			// ID �ߺ� �˻�
			String sql = "select count(*) from member where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next() && resultSet.getInt(1) > 0) {
				System.out.println("�̹� �����ϴ� ID�Դϴ�.");
				return;
			}
			resultSet.close();
			preparedStatement.close();

			System.out.print("���ο� PW: ");
			String newPw = inputStr.next();

			System.out.println("���� ID: " + loginOk.getId());
			System.out.println("�ű� ID: " + newId);
			System.out.println("�ű� PW: " + newPw);

			String usql = "update member set id = ?, pw = ? where id = ?";
			preparedStatement = connection.prepareStatement(usql);
			preparedStatement.setString(1, newId);
			preparedStatement.setString(2, newPw);
			preparedStatement.setString(3, loginOk.getId());

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println("�����Ϸ�");
				connection.commit();
				loginOk.setId(newId);
				loginOk.setPw(newPw);
			} else {
				System.out.println("��������");
				connection.rollback();
			}

		} catch (SQLException e) {
			System.out.println("�����߻� update()�޼��带 Ȯ���ϼ���.");
			e.printStackTrace();
		} finally {
			close();
		}
	}

}
