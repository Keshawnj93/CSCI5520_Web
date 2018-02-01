<%-- 
* Keshawn Jones
* February 5, 2018
* CSCI 5520
* Exercise 38.20(Guess number) Write a JSP page that generates a random number 
* between 1 and 1000 and let the user enter a guess. When the user enters a guess, 
* the program should tell the user whether the guess is correct, too high, or too low.
--%>

<!-- Exercise38.20.jsp -->

<%@page import= "Exercise38_20.RandNumBean" contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id= "RandNumBeanId" class= "Exercise38_20.RandNumBean" scope= "session">
</jsp:useBean>
<jsp:setProperty name= "RandNumBeanId" property= "*"/>
<html>
    <head>
        <style>
            form {border: 1px solid;}
        </style>
        <title>Exercise38.18</title>
    </head>
    <body>
        <h1>Guess an Integer between 1 and 1000</h1>
        
        <form method="post">
        <br />
        Guess a number: <input name="guess"/>
        <br /> <br />
        <input type="submit" name="Submit" value="Guess" />
        <input type="reset" value="Reset" />
        <br /> <br />
        <jsp:getProperty name = "RandNumBeanId" property="guess" /> is
        <%= RandNumBeanId.check() %>
        <br /> <br />
        </form>
    </body>
</html>