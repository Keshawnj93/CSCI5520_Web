/*
* Keshawn Jones
* January 17, 2018
* CSCI 5520
* Exercise 37.01 Write a servlet to display a table that contains factorials 
* for the numbers from 0 to 10, as shown in Figure 37.25.
*/

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise37_01 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<style>");
            out.println("table {margin-left: auto; margin-right: auto;}");
            out.println("table, th, td {border: 1px solid black;}");
            out.println("h2, h3 {font: Georgia, sans-serif;}");
            out.println("p {font: 16px Georgia, sans-serif;}");
            out.println("</style>");
            out.println("<title>Exercise37_01</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h2 align =\"center\">Exercise 37_01</h2>");
            out.println("<p align =\"center\">Write a servlet to display a table "
                    + "that contains factorials for the numbers from 0 to 10, "
                    + "as shown in Figure 37.25.</p>");
            out.println("<p align =\"center\">By: Keshawn Jones</p>");
            printTable(out);
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private int factorial(int n){
        if (n == 0)
            return 1;
        
        int ret = n;
        
        for (int i = 1; i < n; i++){
            ret *= n;
        }
        
        return ret;
    }
    
    private void printTable(PrintWriter out){
        out.println("<table>");
        out.println("<tr>\n<th>Number</th>\n<th>Factorial</th>\n</tr>");
        int i = 1;
        while (i <= 10){
            out.printf("<tr>\n<td>%d</td> \n<td>%d</td>\n</tr>\n", i, factorial(i));
            i++;
        }
        out.println("</table>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
