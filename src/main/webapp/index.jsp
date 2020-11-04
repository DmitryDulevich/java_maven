<%@ page import="entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.UserDao" %>
<%@ page import="dao.UserDaoFromDBImpl" %>

<%--
  Created by IntelliJ IDEA.
  User: Дмитрий
  Date: 01.11.2020
  Time: 13:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table border="1px">
    <tr>
        <th>First name</th>
        <th>Last name</th>
    </tr>
    <%
        UserDao userDao = new UserDaoFromDBImpl();
        List<User> users = userDao.findAll();
        for (User user : users) {
    %>
    <tr>
        <td><%=user.getFirstName()%>
        </td>
        <td><%=user.getLastName()%>
        </td>

        <td>
            <form action="/deleteuser" method="post">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <button type="submit">Delete</button>
            </form>
        </td>

        <td>
            <form action="/updateuser" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>">
                <button type="submit">Update</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<p>
<form action="/createuser.html">
    <button type="submit">Create user</button>
</form>
</p>
<p>
<form action="/finduser.html">
    <button type="submit">Find user</button>
</form>
</p>

</body>
</html>
