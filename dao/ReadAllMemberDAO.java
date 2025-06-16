package academyboard.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import academyboard.dto.MemberDTO;

public class ReadAllMemberDAO {
	// �ʵ� ����
		public MemberDTO memberDTO = new MemberDTO();
		public Connection connection = null;
		public Statement statement = null;
		public PreparedStatement preparedStatement = null;
		public ResultSet resultSet = null;
		public int result = 0;

		// �⺻ ������
		public ReadAllMemberDAO() {
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

		public void readAll() {
			try {
				String sql = "select mno, mname, id, pw, email, regidate from member order by mno desc";
				
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);
				
				System.out.println("ȸ�� ��ȣ\t �̸�\t ID\t PW\t Email\t ������");
				while(resultSet.next()) {
					System.out.print(resultSet.getInt("mno") + "\t");
					System.out.print(resultSet.getString("mname") + "\t");
					System.out.print(resultSet.getString("id") + "\t");
					System.out.print(resultSet.getString("pw") + "\t");
					System.out.print(resultSet.getString("email") + "\t");
					System.out.println(resultSet.getDate("regidate") + "\t");
				}
				System.out.println("������~~~~~");
				
			} catch (SQLException e) {
				System.out.println("�����߻� readAll()�޼��带 Ȯ�����ּ���.");
				e.printStackTrace();
			} finally {
				close();
			}
			
			
		}
		
		

}
