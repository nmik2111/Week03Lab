package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author naeun
 */
public class ArithmeticCalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("answer", "---");
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String first = request.getParameter("first");
        String second = request.getParameter("second");
        
        request.setAttribute("first", first);
        request.setAttribute("second", second);
        
        boolean check = numberOrNot(first, second);
        
        if (first == null || first.equals("") || second == null || second.equals("") || check == false) {
            request.setAttribute("answer", "invalid");
            getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp")
                .forward(request, response);
            return;
        }
        
        int answer = 0; //default ---
        
        if (request.getParameter("+") != null) {
            answer = Integer.parseInt(first) + Integer.parseInt(second);
        } else if (request.getParameter("-") != null) {
            answer = Integer.parseInt(first) - Integer.parseInt(second);
        } else if (request.getParameter("*") != null) {
            answer = Integer.parseInt(first) * Integer.parseInt(second);
        } else if (request.getParameter("%") != null) {
            answer = Integer.parseInt(first) % Integer.parseInt(second);
        }
        
        request.setAttribute("answer", answer);
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp")
                .forward(request, response);
        
    }
    
        boolean numberOrNot(String first, String second) {
            try
            {
                Integer.parseInt(first);
                Integer.parseInt(second);
            }
            catch(NumberFormatException ex)
            {
                return false;
            }
            return true;
        }
}
