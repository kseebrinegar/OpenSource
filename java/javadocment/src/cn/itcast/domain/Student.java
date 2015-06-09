package cn.itcast.domain;

/*
 * 
 * <student idcard="111" examid="222">
		<name>’≈»˝</name>
		<location>…Ú—Ù</location>
		<grade>89</grade>
	</student>
 */


public class Student {
	
	private String idcard;
	private String examid;
	private String name;
	private String location;
	private double grade;
	
	//alt+shift+s
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	
	
	
	
	
	
}
