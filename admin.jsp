<%@ page language="java" import="javax.servlet.*, javax.servlet.http.*, java.sql.*" %>
    <%@ page contentType="text/html" pageEncoding="UTF-8" session="false" %>

        <% HttpSession session=request.getSession(false); if (session==null) { response.sendRedirect("login.html");
            return; } String role=(String) session.getAttribute("role"); if (role==null || !"admin".equals(role)) {
            response.sendRedirect("login.html"); return; } %>
<style>
    body {
    font-family: sans-serif;
    margin: 20px;
}

.container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color:lightblue;
}

h1 {
    text-align: center;
}

.div1 {
    margin-bottom: 20px;
}

form {
    margin-bottom: 15px;
}

input[type="text"],
input[type="password"],
input[type="number"] {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 3px;
}

input[type="submit"] {
    margin-top: 2px;
    border-style: solid;
    background-color: grey;
    padding: 7px;
    color: white;
    font-weight: bold;
    font-size: medium;
    border-width: 2px;
    border-color: grey;
    border-radius: 20px;
    cursor: pointer;

}

p{
    font-family: 'Gill Sans', 'Gill Sans MT', Calibri, 'Trebuchet MS', sans-serif;
}
</style>
            <html>

            <head>
            </head>

            <body>
                <div class="container">
                    <div class="div1">
                        <h1>Welcome Admin</h1>
                        <hr>
                        <h2>1. Add Bank User</h2>
                        <form method="post" action="InsertPersonServlet">
                            <p>Enter the username of the BankUser you want to insert:</p><input type="text" name="uname"
                                required><br>
                            <p>Enter the password of the BankUser you want to insert:</p><input type="password"
                                name="password" required><br>
                            <p>Enter the AccountBalance of the BankUser you want to insert:</p><input type="number"
                                name="amount" required><br>
                            <input type="submit" value="SUBMIT"><br>
                        </form>
                    </div>
                    <hr>
                    <div>
                        <h2>2. Search Bank User</h2>
                        <form method="post" action="ShowPersonServlet">
                            <p> Enter the username of the BankUser you want to search:</p><input type="text"
                                name="uname" required>
                            <input type="submit" value="SUBMIT"><br>
                        </form>
                    </div>
                    <hr>
                    <div>
                        <h2>3. Update Current Data</h2>
                        <form method="post" action="UpdatePersonServlet">
                            <p> Enter the id of the BankUser whose data you want to update:</p><input type="text"
                                name="id" required><br>
                            <p> Enter new username if you wish to change username:</p> <input type="text"
                                name="newuname"><br>
                            Enter new password if you wish to change password: <input type="password"
                                name="password"><br>
                            <p> Enter new Account Balance if you wish to change Account Balance:</p> <input
                                type="number" name="amount"><br>
                            <input type="submit" value="SUBMIT">
                        </form>
                    </div>
                    <hr>
                    <div>
                        <h2>4. Delete Bank User</h2>
                        <form method="post" action="DeletePersonServlet">
                            <p>Enter the id of the BankUser you want to delete:</p><input type="text" name="id"
                                required>
                            <input type="submit" value="SUBMIT">
                        </form>
                    </div>
                    <hr>
                    <div class="h5">
                        <h2>5. Check Payments History:</h2>
                        <form action="CheckPaymentServlet" method="get">
                            <input type="submit" value="CHECK PAYMENTS">
                        </form>
                        <form method="get" action="LogoutServlet">
                            <input type="submit" value="LOGOUT">
                        </form>
                    </div>
                </div>
            </body>

            </html>