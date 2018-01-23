//Exercise37_13.java

package Exercise37_13;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Exercise37_13 extends HttpServlet {

    private PreparedStatement psmnt;
    private Connection connection;
    private String id, firstName, lastName, mi, address, city, state, tele, email;
    
    @Override
    public void init() throws ServletException{
        initDB();
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        id = request.getParameter("id");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        mi = request.getParameter("mi");
        address = request.getParameter("address");
        city = request.getParameter("city");
        state = request.getParameter("state");
        tele = request.getParameter("telephone");
        email = request.getParameter("email");
        
        String b1 = request.getParameter("view");
        String b2 = request.getParameter("insert");
        String b3 = request.getParameter("update");
        String b4 = request.getParameter("showTable");
        String b5 = request.getParameter("clear");
        
        if (b1 != null){
            //View
            if (viewValidate(id, out)){
                view(out);
            }
        }
        
        else if (b2 != null){
            //Insert
            if (inUpValidate(id, lastName, firstName, mi, address, city, state, tele, email, out)){
                insert(id, lastName, firstName, mi, address, city, state, tele, email, out);
            }
        }
        
        else if (b3 != null){
            //Update
            if (inUpValidate(id, lastName, firstName, mi, address, city, state, tele, email, out)){
                update(id, lastName, firstName, mi, address, city, state, tele, email, out);
            }
        }
        
        else if (b4 != null){
            showTable(out);
        }
        
        else if (b5 != null){
            clear(out);
        }
    }

    private void initDB(){        
        //Load Driver
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e){
            System.out.println ("Error loading jdbc driver");
            System.exit(0);
        }
        System.out.println("Driver Loaded Successfully");
        
        //Connect to Database
        connection = null;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
        } catch (Exception e){
            System.out.println ("Error connecting to server");
            System.exit(0);
        }
        System.out.println("Database Connected Successfully");
        
    }
    
    private boolean viewValidate(String id, PrintWriter out){
        //Is the ID valid
        if (!(id.length() < 10 && id.length() > 0)){
            printForm(out);
            out.println("ID " +id+ " is invalid. ID must contain at least one character and may be no longer than 9 characters");
            return false;
        }
        
        return true;
    }
    
    private boolean inUpValidate(String id, String lastName, String firstName,
            String mi, String address, String city, String state, String telephone,
            String email, PrintWriter out){
        boolean ret;
        
        //Is the ID valid
        ret = (id.length() < 10 && id.length() > 0);
        if (!ret){
            printForm(out);
            out.println("ID " +id+ " is invalid. ID must contain at least one character and may be no longer than 9 characters");
            return ret;
        }
        
        //Is last name valid
        ret = (lastName.length() < 16);
        if (!ret){
            printForm(out);
            out.println("Last Name " +lastName+ " is invalid. Last Name must be 15 characters or less");
            return ret;
        }
        
        //Is first name valid
        ret = (firstName.length() < 16);
        if (!ret){
            printForm(out);
            out.println("First Name " +firstName+ " is invalid. First Name must be 15 characters or less");
            return ret;
        }
        
        //Is middle initial valid
        ret = (mi.length() < 2);
        if (!ret){
            printForm(out);
            out.println("Middle Initial " +mi+ " is invalid. Middle Initial must be 1 characters or less");
            return ret;
        }
        
        //Is address valid
        ret = (address.length() < 21);
        if (!ret){
            printForm(out);
            out.println("Address " +address+ " is invalid. Address must be 20 characters or less");
            return ret;
        }
        
        //Is the city valid
        ret = (city.length() < 21);
        if (!ret){
            printForm(out);
            out.println("City " +city+ " is invalid. City must be 20 characters or less");
            return ret;
        }
        
        //Is state valid
        ret = (state.length() < 3);
        if (!ret){
            printForm(out);
            out.println("State " +state+ " is invalid. State must be 2 characters or less");
            return ret;
        }
        
        //Is telephone valid
        ret = (telephone.length() < 11);
        if (!ret){
            printForm(out);
            out.println("Telephone " +telephone+ " is invalid. Telephone must be 10 characters or less");
            return ret;
        }
        
        //Is email valid
        ret = (email.length() < 41);
        if (!ret){
            printForm(out);
            out.println("Email " +email+ " is invalid. Email must be 40 characters or less");
            return ret;
        }
        
        return ret;
    }
    
    private void view(PrintWriter out){
        String st = "Select * from staff where id = ?";
        
        try{
            psmnt = connection.prepareCall(st);
        } catch(Exception e){
            printForm(out);
            out.println("Error in creating prepared statement");
        }
        
        ResultSet res = null;
        try{
            psmnt.setString(1, id);
            res = psmnt.executeQuery();
            
            if (!res.isBeforeFirst()){
                printForm(out);
                out.println("The requested ID " +id+ " could not be found");
            } else {
                res.next();
                id = res.getString(1);
                lastName = res.getString(2);
                firstName = res.getString(3);
                mi = res.getString(4);
                address = res.getString(5);
                city = res.getString(6);
                state = res.getString(7);
                tele = res.getString(8);
                email = res.getString(9);
                
                printForm(out);
                out.println("<p>ID: " +id + " was found</p>");
            }
        } catch(Exception e){
            printForm(out);
            out.println("Error in retrieval");
        }
    }
    
    private void insert(String id, String lastName, String firstName,
            String mi, String address, String city, String state, String telephone,
            String email, PrintWriter out){
        String st = "Insert into staff (id, lastName, firstName, mi, address, city, state, telephone, email)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Statement stmt = null;
        ResultSet res = null;
        
        try{
            psmnt = connection.prepareCall(st);
        } catch (Exception e){
            printForm(out);
            out.println("Error in preparing statement");
        }
        
        try {
            //Test whether the ID entered is already in use
            stmt = connection.createStatement();
            res = stmt.executeQuery("Select * from Staff where id = \"" + id + "\";");
            if (res.isBeforeFirst()){
                printForm(out);
                out.println("Error: The ID " +id+" is already in use");
                return;
            }
            
            //Insert new row into table
            psmnt.setString(1, id);
            psmnt.setString(2, lastName);
            psmnt.setString(3, firstName);
            psmnt.setString(4, mi);
            psmnt.setString(5, address);
            psmnt.setString(6, city);
            psmnt.setString(7, state);
            psmnt.setString(8, telephone);
            psmnt.setString(9, email);
            psmnt.execute();
            printForm(out);
            out.println("ID: " + id + " added successfully");
        } catch (Exception e){
            printForm(out);
            out.println("Sorry, an error has occured in execution. Please try again  " + id);
        }
    }
    
    private void update(String id, String lastName, String firstName,
            String mi, String address, String city, String state, String telephone,
            String email, PrintWriter out){
        String st = "Update staff "
                + "set lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? "
                + "where id = ?;";
        Statement stmt = null;
        ResultSet res = null;
        
        try{
            psmnt = connection.prepareCall(st);
        } catch (Exception e){
            printForm(out);
            out.println("Error in preparing statement");
        }
        
        try {
            //Test whether the ID entered can be updated
            stmt = connection.createStatement();
            res = stmt.executeQuery("Select * from Staff where id = \"" + id + "\";");

            if (!res.isBeforeFirst()){
                printForm(out);
                out.println("Error: The ID " +id+" could not be found");
                return;
            }
            
            //Update the table
            psmnt.setString(1, lastName);
            psmnt.setString(2, firstName);
            psmnt.setString(3, mi);
            psmnt.setString(4, address);
            psmnt.setString(5, city);
            psmnt.setString(6, state);
            psmnt.setString(7, telephone);
            psmnt.setString(8, email);
            psmnt.setString(9, id);
            
            psmnt.execute();
            
            printForm(out);
            out.println("ID: " + id + " updated successfully");
        } catch (Exception e){
            printForm(out);
            out.println("Sorry, an error has occured in execution. Please try again");
        }
    }
    
    private void clear(PrintWriter out){
        id = firstName = lastName = mi = address = city = state = tele = email = "";
        printForm(out);
        out.println("Data fields cleared");
    }
    
    private void showTable(PrintWriter out){
        String state = "Select * from Staff";
        Statement smnt = null;
        ResultSet res = null;
        try {
            smnt = connection.createStatement();
            res = smnt.executeQuery(state);
            
            if (!res.isBeforeFirst()){
                printForm(out);
                out.println("Table Staff is currently empty");
            } else{
                printForm(out);
                out.println("<p>Table successfully retrieved</p>");
                printTable(res, out);
            }
        } catch(SQLException e){
            printForm(out);
            out.println("Error in database retrieval");
        }
    }
    
    private void printTable (ResultSet res, PrintWriter out){
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        out.println("table {margin-left: auto; margin-right: auto;}");
        out.println("table, th, td {border: 1px solid black;}");
        out.println("</style>");
        out.println("<table>");
        out.println("<tr>\n<th>id</th>\n<th>lastName</th>\n<th>firstName</th>\n<th>mi</th>\n"
                + "<th>address</th>\n<th>city</th>\n<th>state</th>\n"
                + "<th>telephone</th>\n<th>email</th>\n"
                + "</tr>");
        try{
            while (res.next()){
                out.printf("<tr>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n"
                    + "<td>%s</td>\n<td>%s</td>\n<td>%s</td>\n"
                    + "<td>%s</td>\n<td>%s</td>\n"
                    + "</tr>", res.getString(1),res.getString(2),res.getString(3),
                    res.getString(4), res.getString(5),res.getString(6),
                    res.getString(7),res.getString(8), res.getString(9));
            }
        } catch (SQLException e){
            printForm(out);
            out.println("An error has occured in printing the table");
        }
        
        
        out.println("</table>");
    }
    
    private void printForm(PrintWriter out){
        out.println("<html>"
                + "<style>form    {border: 1px solid black;"
                + "         width: 35%}"
                + "title   {margin-left: auto;"
                + "         margin-right: auto;"
                + "         font: 24px Georgia, sans-serif}"
                + "</style>"
                + "<title>Exercise37_13</title></head>"
                + "<body>Exercise 37_13"
                + "<form method = \"post\" action = \"Exercise37_13\">"
                + "<p></p>"
                + "<p>ID: <font color = \"#FF0000\">*</font><input type =\"text\" name =\"id\" value =\"" + id + "\"/></p>"
                + "<p>Last Name:  <input type = \"text\" name = \"lastName\" value =\"" + lastName + "\">&nbsp;"
                + "First Name: <input type = \"text\" name = \"firstName\" value =\"" + firstName + "\">&nbsp;"
                + "MI: <input type = \"text\" name = \"mi\" size = \"1\" value =\"" + mi + "\">"
                + "</p>"
                + "<p>Address: <input type=\"text\" name =\"address\" value =\"" + address + "\"/></p>"
                + "<p>City: <input type = \"text\" name = \"city\" size = \"23\" value =\"" + city + "\">&nbsp;"
                + "State: <input type=\"text\" name =\"state\" size=\"2\" value =\"" + state + "\"/>"
                + "</p>"
                + "<p>Telephone: <input type = \"text\" name = \"telephone\" size = \"20\" value =\"" + tele + "\">&nbsp;</p>"
                + "<p>Email: <input type = \"text\" name = \"email\" size = \"20\" value =\"" + email + "\">&nbsp;</p>"
                + "<p><input type =\"submit\" name =\"view\" value =\"View\"/>"
                + "<input type = \"submit\" name = \"insert\" value = \"Insert\"/>"
                + "<input type =\"submit\" name =\"update\" value =\"Update\"/>"
                + "<input type = \"submit\" name =\"clear\" value = \"Clear\"/>"
                + "<input type =\"submit\" name =\"showTable\" value =\"Show Table\"/>"
                + "</p></form></body></html>");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
