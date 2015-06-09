package cn.itcast.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.itcast.dao.StudentDao;
import cn.itcast.domain.Student;

public class Main {

	public static void main(String[] args) throws IOException {
		
		System.out.println("���ѧ�� (a)  ����ѧ��(b)  ɾ��ѧ��(c)");
		System.out.print("������������ͣ�");
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String type = br.readLine();
		
		if(type.equalsIgnoreCase("a")){
			//���ѧ��
			
			try{
				System.out.print("������ѧ��������");
				String name = br.readLine();
				
				System.out.print("������ѧ��׼��֤�ţ�");
				String examid = br.readLine();
				
				System.out.print("������ѧ�����֤�ţ�");
				String idcard = br.readLine();
				
				System.out.print("������ѧ�����ڵأ�");
				String location = br.readLine();
				
				System.out.print("������ѧ���ɼ���");
				String grade = br.readLine();
				
				
				Student student = new Student();
				student.setExamid(examid);
				student.setGrade(Double.parseDouble(grade));
				student.setIdcard(idcard);
				student.setLocation(location);
				student.setName(name);
				
				StudentDao dao = new StudentDao();
				dao.add(student);
				System.out.println("��ϲ��¼��ɹ�������");
			}catch (Exception e) {
				System.out.println("�Բ���¼��ʧ�ܣ���");
			}
			
			
		}else if(type.equalsIgnoreCase("b")){
			//����ѧ��
		}else if(type.equalsIgnoreCase("c")){
			//ɾ��ѧ��
		}else{
			System.out.println("��֧�ִ������������");
		}
		
	}

}
