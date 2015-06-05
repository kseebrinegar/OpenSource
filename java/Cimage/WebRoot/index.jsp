<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <img alt="" src="/Cimage/img?bw=26&border_w=150&dst_w=800&dst_h=800&bordersrc=http://images.88art.com/data/uploads/scene/54f90c02285f8.jpg&picsrc=http://imgsrc.baidu.com/forum/w%3D580/sign=21558e33d21373f0f53f6f97940e4b8b/3369a2d3fd1f4134671c1336261f95cad0c85ec2.jpg">
    This is my JSP page. <br>
  </body>
</html>
