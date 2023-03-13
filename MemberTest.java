package member;

import java.util.Scanner;

public class MemberTest {
	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		Scanner sc = new Scanner(System.in);
		
		//��� ȸ������ ���
		for (MemberVO m : dao.getAllMembers()) {
			System.out.println(m);
		}
		
		// id�� �Է¹޾�, �� ȸ���� ���
		System.out.print("\nid�� �Է��ϼ���: ");
		int id = sc.nextInt();
		MemberVO member = dao.getMemberById(id);
		System.out.println(member);
		
		// ���ο� ȸ�� ������ �Է¹޾�, DB�� ����
		System.out.println("\n���ο� ȸ�� ����(id, �̸�, �̸���, ��ȣ, �����) �Է�:");
		member = new MemberVO();
		member.setId(sc.nextInt());
		member.setName(sc.next());
		member.setEmail(sc.next());
		member.setPassword(sc.next());
		member.setRegdate(sc.next());
		if (dao.insert(member) == 1)
			System.out.println("�Է� ����!!");
		else
			System.out.println("�Է� ����!!");
		
		// ������ ȸ�� ������ �Է¹޾�, DB ����
		System.out.println("\n������ ȸ�� ����(id, �̸���, ��ȣ) �Է�: ");
		id = sc.nextInt();
		String email = sc.next();
		String password = sc.next();
		if (dao.update(id, email, password) == 1)
			System.out.println("���� ����!!");
		else
			System.out.println("���� ����!!");
		
		// ������ ȸ�� ������ �Է¹޾�, DB���� ����
		System.out.print("\n������ ȸ�� id �Է�: ");
		id = sc.nextInt();
		if (dao.delete(id) == 1)
			System.out.println("���� ����!!");
		else
			System.out.println("���� ����!!");
		
		//��� ȸ������ ���
		System.out.println("\n���� ȸ�� ����");
		for (MemberVO m : dao.getAllMembers()) {
			System.out.println(m);
		}
		sc.close();
	}
}
