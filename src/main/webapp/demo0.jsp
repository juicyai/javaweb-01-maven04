<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="/common/header.jsp"/>

<%--“<%=%>”表示将结果输出到客户端--%>
<%= LocalDateTime.now()%>
<hr>

<%--"<% %>"表示java代码块--%>
<%
    int n=0;
    int sum=0;
    for(int i=0;i<100;i++){
       sum=sum+i;
    }
    out.println("sum:"+sum);
%>

<%--"<%!%>"表示声明，将变量声明至类中，可以在方法之间实现共享--%>
<%!
String username="admin";
String password="123456";
%>
<hr>
<%
    out.println("username:"+username+","+"password"+password);
%>
<jsp:include page="/common/footer.jsp"/>
</body>
</html>
