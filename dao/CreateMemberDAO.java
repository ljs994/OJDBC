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
	// �ʵ� ����
	public MemberDTO memberDTO = new MemberDTO();
	public Connection connection = null;
	public Statement statement = null;
	public PreparedStatement preparedStatement = null;
	public ResultSet resultSet = null;
	public int result = 0;

	// �⺻ ������
	public CreateMemberDAO() {
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

	public void createMenu(Scanner inputStr) {
		boolean run = true;
		
		try {
			System.out.print("�̸� : ");
			memberDTO.setMname(inputStr.next());
			while (run) {
				System.out.print("����� ID : ");
				String id = inputStr.next();
				String sql = "select count (*) from member where id = ?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, id);
				resultSet = preparedStatement.executeQuery();
				
				if(resultSet.next() && resultSet.getInt(1) > 0) {
					System.out.println("�̹� �����ϴ� ID�Դϴ�. �ٽ� �Է����ּ���.");
				} else {
					memberDTO.setId(id);
					run = false;
				}
				resultSet.close();
				preparedStatement.close();
			}
			System.out.print("����� PW : ");
			memberDTO.setPw(inputStr.next());
			
			System.out.print("����� Email : ");
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
				System.out.println(result + "���� ȸ���� ���������� ��ϵǾ����ϴ�.");
				connection.commit();
			} else {
				System.out.println("ȸ�� ��Ͽ� �����߽��ϴ�.");
				connection.rollback();
			}
		} catch (SQLException e) {
			System.out.println("�����߻� createMenu()�޼��带 Ȯ�����ּ���.");
			e.printStackTrace();
		} finally {
			close();
		}

	}

}
