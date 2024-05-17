<jsp:include page="sidebar1.jsp"/>
<%@ page import="app.models.Client" %>
<%@page import="jakarta.servlet.http.HttpSession"%>
<%
    HttpSession sessionss = request.getSession(false);
    if (sessionss == null || sessionss.getAttribute("username") == null) {
        response.sendRedirect("login");
        return;
}
%>
<div class="container mt-5">
    <% 
        Client client = (Client) request.getAttribute("client");
        if(client != null) {
    %>
    <h1 class="display-4 text-center">modify client :</h1>
    <hr>
    <form action="updateClient?id=<%= client.getId() %>" method="POST">   
        <div class="form-group">
            <label for="prenom" class="form-label">First Name</label>
            <input type="text" class="form-control" id="prenom" name="prenom" maxlength="50" value="<%= client.getPrenom() %>">
        </div>
        <div class="form-group">
            <label for="nom" class="form-label">Last Name</label>
            <input type="text" class="form-control" id="nom" name="nom" maxlength="50" value="<%= client.getNom() %>">
        </div>
        <div class="form-group">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" maxlength="100" value="<%= client.getEmail() %>">
        </div>
        <div class="form-group">
            <label for="telephone" class="form-label">Phone Number</label>
            <input type="tel" class="form-control" id="telephone" name="telephone" maxlength="15" value="<%= client.getTelephone() %>">
        </div>
        <div class="form-group">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3"><%= client.getDescription() %></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Modify</button>    
    </form>
    <% } else { %>
    <h1 class="display-4 text-center">Add Client :</h1>
    <hr>
    <br>
    <form action="insertClient" method="post">
        <div class="mb-3">
            <label for="prenom" class="form-label">First Name</label>
            <input type="text" class="form-control" id="prenom" name="prenom" maxlength="50" required>
        </div>
        <div class="mb-3">
            <label for="nom" class="form-label">Last Name</label>
            <input type="text" class="form-control" id="nom" name="nom" maxlength="50" required>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" maxlength="100" required>
        </div>
        <div class="mb-3">
            <label for="telephone" class="form-label">Phone Number</label>
            <input type="tel" class="form-control" id="telephone" name="telephone" maxlength="15" required>
        </div>
        <div class="mb-3">
            <label for="description" class="form-label">Description</label>
            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
        </div>
        <button type="submit" class="btn btn-primary">Add Client</button>
    </form>
    <% } %> 
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    const sidebarLinks = document.querySelectorAll(".second-text");
    const addbtn = document.querySelectorAll(".second-text")[1];
    
    sidebarLinks.forEach(otherLink => otherLink.classList.remove("active"));
    addbtn.classList.add("active");
});
</script>

<jsp:include page="sidebar2.jsp"/>
