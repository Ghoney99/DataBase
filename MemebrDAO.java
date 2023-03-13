package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

class MemberDAO {

	// insert - MemberVO ������ �������� ���ο� ȸ�� ���
	public int insert(MemberVO member) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException : " + e.getMessage());
		}

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott", "tiger");

			String sql = "insert into member values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, member.getId());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getPassword());
			pstmt.setString(5, member.getRegdate());
			
			result = pstmt.executeUpdate(); // sql����
		} catch (SQLException sqle) {
			System.err.println("SQLException : " + sqle);
		}
		return result;
	}

	// delete - id�� �ش��ϴ� ȸ�� ����
	public int delete(int id) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException : " + e.getMessage());
		}
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott", "tiger");

			String sql = "delete from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);

			result = pstmt.executeUpdate(); // sql����
		} catch (SQLException sqle) {
			System.err.println("SQLException : " + sqle);
		}
		return result;
	}

	// update - id ȸ���� �̸��ϰ� ��ȣ ����
	public int update(int id, String email, String password) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException : " + e.getMessage());
		}
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott", "tiger");

			String sql = "update member set email = ?, password = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			pstmt.setInt(3, id);
			
			result = pstmt.executeUpdate(); // sql����
		} catch (SQLException sqle) {
			System.err.println("SQLException : " + sqle);
		}
		return result;
	}

	// getMemberById - id�� �ش��ϴ� ȸ���� �˻��Ͽ� MemberVO�� ������ �� return
	MemberVO getMemberById(int id) {
		MemberVO temp = new MemberVO();
		
		Connection conn = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException : " + e.getMessage());
		}
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott", "tiger");

			String sql = "select * from member where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				temp.setId(rset.getInt("id"));
				temp.setName(rset.getString("name"));
				temp.setEmail(rset.getString("email"));
				temp.setPassword(rset.getString("password"));
				temp.setRegdate(rset.getString("regdate"));
			}

		} catch (SQLException sqle) {
			System.err.println("SQLException : " + sqle);
		}
		return temp;
	}

	// getAllMembers() - ���̺� ����� ��� ȸ���� MemberVO ����Ʈ�� ������ �� return
	List<MemberVO> getAllMembers() {
		ArrayList<MemberVO> memberlist = new ArrayList<MemberVO>();

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFoundException : " + e.getMessage());
		}

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xepdb1", "scott", "tiger");
			stmt = conn.createStatement();

			String sql = "select * from member";
			rset = stmt.executeQuery(sql);

			while (rset.next()) {
				MemberVO temp = new MemberVO();
				temp.setId(rset.getInt("id"));
				temp.setName(rset.getString("name"));
				temp.setEmail(rset.getString("email"));
				temp.setPassword(rset.getString("password"));
				temp.setRegdate(rset.getString("regdate"));

				memberlist.add(temp);
			}
		} catch (SQLException sqle) {
			System.err.println("SQLException : " + sqle);
		}
		return memberlist;
	}
}
