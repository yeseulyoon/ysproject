<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="model.PeopleDAO" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>loginAction.jsp</title>
</head>
<body>
	<%
	if(request.getAttribute("login").equals(1)){
	    HttpSession session1 = request.getSession();
	    session1.setAttribute("id", request.getParameter("loginid"));
	    session.setMaxInactiveInterval(30*60);
	    response.sendRedirect("loginSucc.jsp");
	}else if(request.getAttribute("login").equals(0)){
		PrintWriter script = response.getWriter();
		script.println("<script>");		
		script.println("alert('��й�ȣ�� Ʋ���ϴ�.')");
		script.println("location.href ='main.html'");
		script.println("</script>");
	}else if(request.getAttribute("login").equals(-1)){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�������� �ʴ� ���̵��Դϴ�.')");
		script.println("location.href ='main.html'");
		script.println("</script>");
	}else if(request.getAttribute("login").equals(-2)){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('�����ͺ��̽� ������ �߻��߽��ϴ�.')");
		script.println("location.href ='main.html'");
		script.println("</script>");
	}
	%>
</body>
</html>