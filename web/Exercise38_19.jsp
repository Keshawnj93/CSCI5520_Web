<!-- /*
* Keshawn Jones
* February 5, 2018
* CSCI 5520
* (Access and update a Staff table) Write a JSP for Exercise 33.1, as shown in
* Figure 38.17.
*/-->

<!-- Exercise 38_19.jsp /-->
<%@page import= "Exercise38_19.DBBean" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id= "DBBeanId" class= "Exercise38_19.DBBean" scope= "session">
</jsp:useBean>
<jsp:setProperty name= "DBBeanId" property= "*"/>
<% DBBeanId.act(); %>
<html>
    <head>
        <style>
            form    {border: 1px solid black;
                     width: 35%}
            title   {margin-left: auto;
                     margin-right: auto;
                     font: 24px Georgia, sans-serif}
        </style>
        
        <title>Exercise38.19</title>
    </head>
    <body>
        Exercise 38.19

        <form method = "post">
            
            <p>
                
            </p>
            
            
            <p>
                ID: <font color = "#FF0000">*  </font><input type ="text" name ="id" value="<jsp:getProperty name="DBBeanId" property="id" />" />
            </p>
            
            <p>
                Last Name:  <input type = "text" name = "lastName" value ="<jsp:getProperty name="DBBeanId" property="lastName" />"/>&nbsp;
                First Name: <input type = "text" name = "firstName" value="<jsp:getProperty name="DBBeanId" property="firstName" />"/>&nbsp;
                MI: <input type = "text" name = "mi" size = "1" value="<jsp:getProperty name="DBBeanId" property="mi" />"/>
            </p>
            
            <p>
                Address: <input type="text" name ="address" value="<jsp:getProperty name="DBBeanId" property="address" />"/>
            </p>
            
            <p>
                City: <input type = "text" name = "city" size = "23" value="<jsp:getProperty name="DBBeanId" property="city" />"/>&nbsp;
                State: <input type="text" name ="state" size="2" value="<jsp:getProperty name="DBBeanId" property="state" />"/>
            </p>
            
            <p>
                Telephone: <input type = "text" name = "telephone" size = "20" value = "<jsp:getProperty name="DBBeanId" property="telephone" />"/>&nbsp;
            </p>
            
            <p>
                Email: <input type = "text" name = "email" size = "20" value = "<jsp:getProperty name="DBBeanId" property="email" />"/>&nbsp;
            </p>

            
            <p>
                <input type ="submit" name ="mode" value ="View"/>
                <input type = "submit" name = "mode" value = "Insert"/>
                <input type ="submit" name ="mode" value ="Update"/>
                <input type = "submit" name ="mode" value = "Clear"/>
            </p>
            
            <br>
        </form>
    <jsp:getProperty name= "DBBeanId" property= "message"></jsp:getProperty>
    </body>
</html>
