<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="model.PeopleDAO" %>
<%@ page import="model.domain.PeopleVO" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<%
	if(request.getAttribute("joincheck").equals(1)){
		PrintWriter script = response.getWriter();
		script.println("<script>");		
		script.println("alert('�̹� �����ϴ� ���̵� �Դϴ�. �ٽ� �õ����ּ���.')");
		script.println("location.href ='main.html'");
		script.println("</script>");
	}else if(request.getAttribute("joincheck").equals(0)){
		PrintWriter script = response.getWriter();
		PeopleDAO.insert(new PeopleVO(request.getParameter("id"), request.getParameter("pw"), request.getParameter("name"), request.getParameter("gender"), request.getParameter("birthday")));
		script.println("<script>");		
		script.println("alert('ȸ�����Կ� �����߽��ϴ�.')");
		script.println("location.href ='main.html'");
		script.println("</script>");
	}else if(request.getAttribute("joincheck").equals(-1)){
		PrintWriter script = response.getWriter();
		script.println("<script>");		
		script.println("alert('�����ͺ��̽� �����Դϴ�.')");
		script.println("location.href ='main.html'");
		script.println("</script>");
	}
	%>
</body>
</html>