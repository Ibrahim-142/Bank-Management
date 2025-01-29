<%@ page language="java" import="javax.servlet.*,javax.servlet.http.*" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>

<%
    HttpSession session = request.getSession(false);
    if (session == null) {
        response.sendRedirect("login.html");
        return;
    }
    String role = (String) session.getAttribute("role");
    if (role == null || !"user".equals(role)) {
        response.sendRedirect("login.html");
        return;
    }
%>
<html>
    <head>
        <link rel="stylesheet" href="style2.css">
    </head>
<body>
    <div class="container">
    <h1>Welcome Bank Customer</h1>
   <div class="c1"> <h2>Transfer Payments</h2></div>
    <form action="TransferPaymentServlet" method="post">
       <p> Enter the receiver's name:</p><input type="text" name="receiver" required>
      <p>  Enter the amount you want to send:</p><input type="number" name="amount" required>
       <p> Enter your password:</p><input type="password" name="password" required>
        <input type="submit" value="SUBMIT">
    </form>

    <div class="c1">    <h2>Check Payments History</h2></div>
    <form action="CheckPaymentServlet" method="get">
        <input type="submit" value="CHECK PAYMENTS">
    </form>

    <div class="c1"><h2>Deposit cash</h2></div>
    <form action="DepositServlet" method="post">
       <p> Enter the amount you want to deposit:</p><input type="number" name="amount" required>
       <p> Please Enter your password:</p><input type="password" name="password" required>
        <input type="submit" value="SUBMIT">
    </form>

    <div class="c1"><h2>Withdraw cash</h2></div>
    <form action="WithdrawServlet" method="post">
       <p> Enter the amount you want to Withdraw:</p><input type="number" name="amount" required>
       <p> Please Enter your password:</p><input type="password" name="password" required>
        <input type="submit" value="SUBMIT">
    </form>
    <form method="get" action="LogoutServlet">
        <input type="submit" value="LOGOUT">
    </form>
</div>
</body>
</html>
