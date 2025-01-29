import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DepositServlet extends HttpServlet { 

public void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { 
response.setContentType("text/html");
PrintWriter out = response.getWriter();
String pwd = request.getParameter("password");
String amountstr = request.getParameter("amount");
HttpSession session = request.getSession(false);
if (session == null) {
    out.println("<html><body><h3>No active session found. Please log in again.</h3></body></html>");
    return;
}
String sessionusername = (String) session.getAttribute("username");
out.println("Welcome Mr." + sessionusername);
out.println("Session ID: " + session.getId() + "<br>");
out.println("Is New: " + session.isNew() + "<br>");
out.println("Created: " + new java.util.Date(session.getCreationTime()) + "<br>");
out.println("Last accessed: " + new java.util.Date(session.getLastAccessedTime()) + "<br>");
double amount = 0.0;
try {
    amount = Double.parseDouble(amountstr);
} catch (NumberFormatException e) {
    out.println("<html><body><h3>Invalid amount format!</h3></body></html>");
    return;
}
if (amount <= 0) {
    out.println("<html><body><h3>Please Enter a valid amount greater than 0</h3></body></html>");
    return;
}
out.println("Amount to be deposited: " + amount);
if (pwd.isEmpty()) {
    out.println("<html><body><h3>Please enter your password to confirm deposit</h3></body></html>");
    return;
}
PersonDAO personDAO = new PersonDAO(); 
PersonInfo person = personDAO.Deposit(pwd,amount,sessionusername); 
out.println("<h1>Deposit Info:</h1>");

	if (person != null){ 
		out.println("<h3>"+ person.toString() +"</h3>" ); 
		out.println("<h3>Deposit Successfull</h3>" ); 
			} 
	else{ 
		out.println("<h3>Error doing Deposit</h3>" ); 
	} 
	out.println("</body>"); 
	out.println("</html>");
	out.close(); 

}
public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException { 
    processRequest(request, response);} 

public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { 
    processRequest(request, response); 
} 
}