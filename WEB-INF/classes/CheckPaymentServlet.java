import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

public class CheckPaymentServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);

        if (session == null) {
            out.println("<html><body><h3>No active session found. Please log in again.</h3></body></html>");
            return;
        }

        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        try {
            PersonDAO personDAO = new PersonDAO();
            PersonInfo person = personDAO.GetPersonInfo(username, role);
            List<PaymentInfo> payments = personDAO.GetPayments(username, role);

            out.println("<html><body>");
            out.println("<h2>Welcome Mr." + username + "!</h2>");
            if (person != null) {
                out.println("<h3>" + person.toString() + "</h3>");
            }

            out.println("<h3>Payment History:</h3>");
            if (payments.isEmpty()) {
                out.println("<h3>No payment records found.</h3>");
            } else {
                for (int i = 0; i < payments.size(); i++) {
                    out.println("<div>");
                    PaymentInfo payment = payments.get(i);
                    out.println("Sender Name: " + payment.getsender() + "<br>");
                    out.println("Receiver Name: " + payment.getreciever() + "<br>");
                    out.println("Amount: " + payment.getamount() + "<br>");
                    out.println("Status: " + payment.getstatus() + "<br>");
                    out.println("</div>");
                    out.println("<br>");
                }
            }
            out.println("</body></html>");
        } catch (Exception e) {
            out.println("<html><body><h3>Error: " + e.getMessage() + "</h3></body></html>");
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
