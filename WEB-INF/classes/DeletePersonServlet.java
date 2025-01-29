import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class DeletePersonServlet extends HttpServlet { 

public void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException { 
	response.setContentType("text/html"); 
	PrintWriter out = response.getWriter(); 
	String idstr = request.getParameter("id");
	if (idstr == null || idstr.isEmpty()) {
		out.println("<h3>Id is required.</h3>");
		return;
	}

	int id = Integer.parseInt(idstr);  
	PersonDAO personDAO = new PersonDAO(); 
	PersonInfo person = personDAO.deletePerson(id); 
	
	
	out.println("<html>"); 
	out.println("<body>"); 
	out.println("<h1>Person to be Deleted:</h1>");

	if (person != null){ 
		out.println("<h3>"+ person.toString() +"</h3>" ); 
		out.println("<h3> Deletion Successfull</h3>" ); 		
			} 
	else{ 
		out.println("<h3>Records could not be deleted</h3>" ); 
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