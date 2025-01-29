import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String uname = request.getParameter("uname");
        String password = request.getParameter("password");
        if (uname.isEmpty() || password.isEmpty()) {
            out.println("<html><body><h3>Username or Password cannot be empty</h3></body></html>");
            return;
        }
        PersonDAO personDAO = new PersonDAO();
        PersonInfo person = personDAO.Login(uname, password);
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Logging In:</h1>");
        if (person != null) {
            out.println("<h3>" + person.toString() + "</h3>");
            out.println("<h3>Login Successful</h3>");
            HttpSession session = request.getSession();
            session.setAttribute("username", person.getusername());
            session.setAttribute("role", person.getrole());
            String role = person.getrole();
            if ("admin".equals(role)) {
                response.sendRedirect("admin.jsp");
            } else if ("user".equals(role)) {
                response.sendRedirect("bankcustomer.jsp");
            } else {
                out.println("<h3>Unrecognized role: " + role + "</h3>");
            }
        } else {
            out.println("<h3>Invalid username or password</h3>");
        }
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
