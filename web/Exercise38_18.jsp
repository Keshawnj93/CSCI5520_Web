<%-- 
* Keshawn Jones
* February 5, 2018
* CSCI 5520
* Exercise 38.18(Large factorial) Rewrite Listing 38.11 to handle a large factorial.
--%>


<!-- Exercise38.18.jsp -->
<%@page import = "Exercise38_18.NewFactorialBean" %>
<jsp:useBean id= "factorialBeanId" class= "Exercise38_18.NewFactorialBean" 
             scope= "page">    
</jsp:useBean>
<jsp:setProperty name = "factorialBeanId" property= "*" />
<html>
    <head>
        <style>
            form {border: 1px solid;}
        </style>
        <title>Exercise38.18</title>
    </head>
    <body>
        <h1>Compute Factorial using a Bean and BigInteger</h1>
        
        <form method="post">
        <br /> <br />
        Enter new value: <input name="number"/>
        <br /> <br />
        <input type="submit" name="Submit" value="Compute Factorial" />
        <input type="reset" value="Reset" />
        <br /> <br />
        Factorial of 
        <jsp:getProperty name = "factorialBeanId" property="number" />
        is
        <%= NewFactorialBean.format(factorialBeanId.getFactorial()) %>
        <br /> <br />
        </form>
    </body>
</html>
