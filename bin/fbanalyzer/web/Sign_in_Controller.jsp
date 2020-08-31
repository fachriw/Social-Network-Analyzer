<%@page import="com.socialNetworkAnalyzer.fblogin.User_Profile"%>
<%@page import="com.socialNetworkAnalyzer.fblogin.Get_User_Details"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<%
    String access_token=(String)request.getParameter("access_token");
    Get_User_Details obj_Get_User_Details=new Get_User_Details();
    User_Profile obj_User_Profile=obj_Get_User_Details.Get_Profile_info(access_token);
%>
User Name : <%=obj_User_Profile.getUser_name() %>
</body>
</html>