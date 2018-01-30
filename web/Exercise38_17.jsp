<%-- 
* Keshawn Jones
* January 31, 2018
* CSCI 5520
* Exercise 38.17(Guess capitals) Write a JSP that prompts the user to enter a 
* capital for a state, as shown in Figure 38.16a. 
--%>

<!-- Exercise 38_17.jsp -->
<jsp:useBean id= "states" scope= "application" class= "Exercise38_17.States" ></jsp:useBean>
<html>
    <head>
        <title>Exercise 38.17</title>
    </head>
    
    <body>
        <% if (request.getParameter("submit") == null || request.getParameter("submit") == request.getParameter("next")){ %> 
            <% int n = (int)((Math.random() * 50)); %>
            <% states = new Exercise38_17.States(n); %>
        
        
            <form method="post">
                What is the capital of <%= states.getState() %>?<input type="text" name="answer"/> <input type="submit" name="submit" value="Submit"/>
                <input type="hidden" name="storedState" value="<%= states.getState() %>"/>
                <input type="hidden" name="storedCapital" value="<%= states.getCapital() %>"/>
            </form>
        
        <% } else { %>
        <% if (request.getParameter("answer").toUpperCase().equals(request.getParameter("storedCapital").toUpperCase())) { %>
                <form>
                    Correct! The capital of <%= request.getParameter("storedState") %> is <%= request.getParameter("storedCapital") %>! <input type="submit" name="next" value="Next"/>
                </form>
            <% } else { %>
                <form>
                    Incorrect. The capital of <%= request.getParameter("storedState") %> is <%= request.getParameter("storedCapital") %>! <input type="submit" name="next" value="Next"/>
                </form>
            <% } %>
        <% } %>
    </body>
</html>