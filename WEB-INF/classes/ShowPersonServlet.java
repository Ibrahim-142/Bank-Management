import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ShowPersonServlet extends HttpServlet { 

public void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { 
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter(); 

	String name = request.getParameter("uname"); 
	
	PersonDAO personDAO = new PersonDAO(); 
	PersonInfo person = personDAO.searchPerson(name); 

	out.println("<html>"); 
	out.println("<body>"); 
	out.println("<h1>Search Results</h1>");

	if (person != null){ 
		out.println("<h3>"+ person.toString() +"</h3>" ); 
			} 
	else{ 
		out.println("<h3>Sorry! No records found</h3>" ); 
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