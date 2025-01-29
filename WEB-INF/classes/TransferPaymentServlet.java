import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class TransferPaymentServlet extends HttpServlet {

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session == null) {
            out.println("<html><body><h3>No active session found. Please log in again.</h3></body></html>");
            return;
        }

        String sender = (String) session.getAttribute("username");
        out.println("<html><body><h3>Welcome Mr." + sender + "</h3></body></html>");

        String recipent = request.getParameter("receiver");
        String amountstr = request.getParameter("amount");
        String pwd = request.getParameter("password");
        if (recipent.isEmpty() || amountstr.isEmpty() || pwd.isEmpty()) {
            out.println("<html><body><h3>Invalid input. Please fill out all fields</h3></body></html>");
            return;
        }
        if (sender.equals(recipent)){
            out.println("<html><body><h3>You cannot send money to yourself</h3></body></html>");
        return ;
        }
        double amount;
        try {
            amount = Double.parseDouble(amountstr);
        } catch (NumberFormatException e) {
            out.println("<html><body><h3>Please enter numbers only for the amount</h3></body></html>");
            return;
        }
        if(amount<=0){
        out.println("<html><body><h3>Error amount should be greater than 0</h3></body></html>");
        return;}
        PersonDAO personDAO = new PersonDAO();
        PersonInfo person = personDAO.TransferPayment(sender, recipent, amount, pwd);
        out.println("<h1>Transfer Info:</h1>");

        if (person != null) {
            out.println("<h3>" + person.toString() + "</h3>");
            out.println("<h3>Transfer Successfull</h3>");
        } else {
            out.println("<h3>Error doing Transfer</h3>");
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