<%@ page language="java"
	import="com.likeminds.EBSAuth.*, java.sql.CallableStatement, java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.ResultSet, java.sql.SQLException, javax.servlet.ServletException, javax.servlet.annotation.WebServlet, javax.servlet.http.Cookie, javax.servlet.http.HttpServlet, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<%
int sessionID = EBSAdapter.create_session(1015104);
System.out.println("sessionID ===== " + sessionID );

String xSid= EBSAdapter.getxid(sessionID);
//	logger.debug("XSID is :: " + xSid);
System.out.println("XID =" + xSid );
Cookie cookie = new Cookie("VIS", xSid);
System.out.println("Cookie value VIS ::" + cookie.getValue());
//cookie.setDomain(".likemindscloud.com");
System.out.println("Domain is ::" + cookie.getDomain());
cookie.setPath("/");
System.out.println("Path ::" + cookie.getPath());
cookie.setHttpOnly(true);
response.addCookie(cookie);
response.sendRedirect("http://ebs.likemindscloud.com:8000/OA_HTML/OA.jsp?OAFunc=OAHOMEPAGE");
%>
<meta charset="UTF-8">
<title>EBS Auth Page</title>
</head>
<body>

</body>
</html>