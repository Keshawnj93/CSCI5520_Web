<%-- 
* Keshawn Jones
* January 17, 2018
* CSCI 5520
* Exercise 38.01 Write a java server page to display a table that contains factorials 
* for the numbers from 0 to 10.
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            table {margin-left: auto; margin-right: auto;}
            table, th, td {border: 1px solid black;}
            h2, h3 {font: Georgia, sans-serif;}
            p {font: 16px Georgia, sans-serif;}
        </style>  
        <title>Exercise 38.01</title>        
    </head>
    
    <body>
        <h2 align ="center">Exercise 38_01</h2>
        
        <p align ="center">
            Write a Java Server Page to display a table that contains
            factorials for the numbers from 0 to 10.
        </p>
        
        <p align ="center">By: Keshawn Jones</p>
        
        <table>
            <tr>
                <th>Number</th>
                <th>Factorial</th>
            </tr>
        <%      int i = 1;
                while (i < 10){ %>
        <tr> 
            <td> <%= i %> </td>
            <td> <%= factorial(i) %> </td>
        </tr>
        <%      i++;
            }
        %>
        </table>
        
        <%! private int factorial(int n){
                if (n == 0) return 1;
                int ret = n;
                for (int i = 1; i < n; i++){
                    ret *= n;
                }
                return ret;
            }
        %>
        
    </body>
</html>

