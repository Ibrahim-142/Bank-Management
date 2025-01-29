import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate(); 
        }
        response.sendRedirect("login.html");
    }
}
