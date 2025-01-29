import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class UpdatePersonServlet extends HttpServlet { 

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        response.setContentType("text/html"); 
        PrintWriter out = response.getWriter(); 

        try {
            String idstr = request.getParameter("id");
            String newname = request.getParameter("newuname");
            String newpwd = request.getParameter("password");  
            String newbalancestr = request.getParameter("amount");  

            if (idstr == null || idstr.isEmpty()) {
                out.println("<h3>Id is required.</h3>");
                return;
            }

            int id = Integer.parseInt(idstr);  

            PersonDAO personDAO = new PersonDAO();
            PersonInfo person = personDAO.updatePerson(id, newname, newpwd, newbalancestr); 

            out.println("<html>"); 
            out.println("<body>"); 
            out.println("<h1>Updating Data:</h1>");

            if (person != null){ 
                out.println("<h3>Updated Person Information:</h3>");
                out.println("<p>" + person.toString() + "</p>"); 
                out.println("<p>Update Successful</p>");
            } else { 
                out.println("<h3>Could not update the records. Please check the provided data.</h3>");
            }
            out.println("</body>"); 
            out.println("</html>");
        } catch (NumberFormatException e) {
            out.println("<h3>Error: Invalid ID format. Please provide a valid numeric ID.</h3>");
        } catch (Exception e) {
            out.println("<h3>An error occurred while processing your request. Please try again later.</h3>");
            e.printStackTrace();
        } finally {
            out.close();
        }
    } 

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        processRequest(request, response);
    } 

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        processRequest(request, response); 
    } 
}
