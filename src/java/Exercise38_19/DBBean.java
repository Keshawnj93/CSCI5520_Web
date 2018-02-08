//DBBean.java (Used to perform database actions in Exercise 38.19.jsp

package Exercise38_19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBBean {
    String id, lastName, firstName, mi, address, city, state, telephone, email, mode, message;
    PreparedStatement psmt;
    Connection conn;
    
    public DBBean(){
        id = lastName = firstName = mi = address = city = state = telephone = email = mode = message = "";
        psmt = null;
        conn = null;
        initDB();
    }
    
    public void act(){
        switch (mode){
            case "View": {
                if (viewValidate()){
                    view();
                    break;
                }
            }
            
            case "Insert": {
                if (inUpValidate()){
                    insert();
                    break;
                }
            }
            
            case "Update": {
                if (inUpValidate()){
                    update();
                    break;
                }
            }
            
            case "Clear": {
                    clear(); break;
            }
            
            default: break;
        }
    }
    
    public void initDB(){        
        //Load Driver
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e){
            System.out.println ("Error loading jdbc driver");
            System.exit(0);
        }
        System.out.println("Driver Loaded Successfully");
        
        //Connect to Database
        conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "root", "Flylow13");
        } catch (Exception e){
            System.out.println ("Error connecting to server");
            System.exit(0);
        }
        message = "Database Connected Successfully";
        
    }
    
    public boolean viewValidate(){
        //Is the ID valid
        if (!(id.length() < 10 && id.length() > 0)){
            message = "ID " +id+ " is invalid. ID must contain at least one character and may be no longer than 9 characters";
            return false;
        }
        
        return true;
    }
    
    public boolean inUpValidate(){
        boolean ret;
        
        //Is the ID valid
        ret = (id.length() < 10 && id.length() > 0);
        if (!ret){
            message = "ID " +id+ " is invalid. ID must contain at least one character and may be no longer than 9 characters";
            return ret;
        }
        
        //Is last name valid
        ret = (lastName.length() < 16);
        if (!ret){
            message = "Last Name " +lastName+ " is invalid. Last Name must be 15 characters or less";
            return ret;
        }
        
        //Is first name valid
        ret = (firstName.length() < 16);
        if (!ret){
            message = "First Name " +firstName+ " is invalid. First Name must be 15 characters or less";
            return ret;
        }
        
        //Is middle initial valid
        ret = (mi.length() < 2);
        if (!ret){
            message = "Middle Initial " +mi+ " is invalid. Middle Initial must be 1 characters or less";
            return ret;
        }
        
        //Is address valid
        ret = (address.length() < 21);
        if (!ret){
            message = "Address " +address+ " is invalid. Address must be 20 characters or less";
            return ret;
        }
        
        //Is the city valid
        ret = (city.length() < 21);
        if (!ret){
            message = "City " +city+ " is invalid. City must be 20 characters or less";
            return ret;
        }
        
        //Is state valid
        ret = (state.length() < 3);
        if (!ret){
            message = "State " +state+ " is invalid. State must be 2 characters or less";
            return ret;
        }
        
        //Is telephone valid
        ret = (telephone.length() < 11);
        if (!ret){
            message = "Telephone " +telephone+ " is invalid. Telephone must be 10 characters or less";
            return ret;
        }
        
        //Is email valid
        ret = (email.length() < 41);
        if (!ret){
            message = "Email " +email+ " is invalid. Email must be 40 characters or less";
            return ret;
        }
        
        return ret;
    }
    
    public void view(){
        String st = "Select * from staff where id = ?";
        
        try{
            psmt = conn.prepareCall(st);
        } catch(Exception e){
            message = "Error in creating prepared statement";
        }
        
        ResultSet res = null;
        try{
            psmt.setString(1, id);
            res = psmt.executeQuery();
            
            if (!res.isBeforeFirst()){
                message = "The requested ID " +id+ " could not be found";
            } else {
                res.next();
                id = res.getString(1);
                lastName = res.getString(2);
                firstName = res.getString(3);
                mi = res.getString(4);
                address = res.getString(5);
                city = res.getString(6);
                state = res.getString(7);
                telephone = res.getString(8);
                email = res.getString(9);
                
                message = "<p>ID: " +id + " was found</p>";
            }
        } catch(Exception e){
            message = "Error in retrieval";
        }
    }
    
    public void insert(){
        String st = "Insert into staff (id, lastName, firstName, mi, address, city, state, telephone, email)"
                + " values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        Statement stmt = null;
        ResultSet res = null;
        
        try{
            psmt = conn.prepareCall(st);
        } catch (Exception e){
            message = "Error in preparing statement";
        }
        
        try {
            //Test whether the ID entered is already in use
            stmt = conn.createStatement();
            res = stmt.executeQuery("Select * from Staff where id = \"" + id + "\";");
            if (res.isBeforeFirst()){
                message = "Error: The ID " +id+" is already in use";
                return;
            }
            
            //Insert new row into table
            psmt.setString(1, id);
            psmt.setString(2, lastName);
            psmt.setString(3, firstName);
            psmt.setString(4, mi);
            psmt.setString(5, address);
            psmt.setString(6, city);
            psmt.setString(7, state);
            psmt.setString(8, telephone);
            psmt.setString(9, email);
            psmt.execute();
            message = "ID: " + id + " added successfully";
        } catch (Exception e){
            message = "Sorry, an error has occured in execution. Please try again  " + id;
        }
    }
    
    public void update(){
        String st = "Update staff "
                + "set lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ? "
                + "where id = ?;";
        Statement stmt = null;
        ResultSet res = null;
        
        try{
            psmt = conn.prepareCall(st);
        } catch (Exception e){
            message = "Error in preparing statement";
        }
        
        try {
            //Test whether the ID entered can be updated
            stmt = conn.createStatement();
            res = stmt.executeQuery("Select * from Staff where id = \"" + id + "\";");

            if (!res.isBeforeFirst()){
                message = "Error: The ID " +id+" could not be found";
                return;
            }
            
            //Update the table
            psmt.setString(1, lastName);
            psmt.setString(2, firstName);
            psmt.setString(3, mi);
            psmt.setString(4, address);
            psmt.setString(5, city);
            psmt.setString(6, state);
            psmt.setString(7, telephone);
            psmt.setString(8, email);
            psmt.setString(9, id);
            
            psmt.execute();
            
            message = "ID: " + id + " updated successfully";
        } catch (Exception e){
            message = "Sorry, an error has occured in execution. Please try again";
        }
    }
    
    public void clear(){
        id = firstName = lastName = mi = address = city = state = telephone = email = "";
        message = "Data fields cleared";
    }
    
    
    /* Getters and setters for bean */
    public String getId(){
        return id;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    
    public String getMi(){
        return mi;
    }
    
    public String getAddress(){
        return address;
    }
    
    public String getCity(){
        return city;
    }
    
    public String getState(){
        return state;
    }
    
    public String getTelephone(){
        return telephone;
    }
    
    public String getEmail(){
        return email;
    }
    
    public String getMode(){
        return mode;
    }
    
    public String getMessage(){
        return message;
    }
    
    public void setId(String s){
        id = s;
    }
    
    public void setlastName(String s){
        lastName = s;
    }
    
    public void setFirstName(String s){
        firstName = s;
    }
    
    public void setMi(String s){
        mi = s;
    }
    
    public void setAddress(String s){
        address = s;
    }
    
    public void setCity(String s){
        city = s;
    }
    
    public void setState(String s){
        state = s;
    }
    
    public void setTelephone(String s){
        telephone = s;
    }
    
    public void setEmail(String s){
        email = s;
    }
    
    public void setMode(String s){
        mode = s;
    }
    
    public void setMessage(String s){
        message = s;
    }
}
