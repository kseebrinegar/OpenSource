package cn.itcast.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import cn.itcast.domain.Student;
import cn.itcast.utils.XmlUtils;

public class StudentDao {

	/*
	 <student idcard="111" examid="222">
		<name>张三</name>
		<location>沈阳</location>
		<grade>89</grade>
	</student>

	 */
	public void add(Student student){
		try{
			Document document = XmlUtils.getDocument();
			//student_node  //ctrl+1  rename in file
			Element student_node = document.createElement("student");
			student_node.setAttribute("examid", student.getExamid());
			student_node.setAttribute("idcard", student.getIdcard());
			
			Element name = document.createElement("name");
			name.setTextContent(student.getName());
			
			Element location = document.createElement("location");
			location.setTextContent(student.getLocation());
			
			Element grade = document.createElement("grade");
			grade.setTextContent(student.getGrade()+"");
			
			student_node.appendChild(name);
			student_node.appendChild(location);
			student_node.appendChild(grade);
			
			//得到exam结点，并把student挂上去
			document.getElementsByTagName("exam").item(0).appendChild(student_node);
			
			XmlUtils.write2Xml(document);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void delete(String name){
		
	}
	
	
	public Student find(String examid){
		
		
		return null;
	}
}
