
<jsp:include page="sidebar1.jsp"/>
<%@ page import="app.models.Employe" %>

<div class="container mt-5">
<% 
        Employe employe = (Employe) request.getAttribute("employe");
        if(employe != null) {
         
       %>
        <h2>modify Employe :  </h2>
    
    <hr>
	
        <form action="update?id=<%= employe.getId() %>" method="POST">   
        <div class="form-group">
            <label for="fname">Full Name:</label>
            <input type="text" class="form-control" id="fname" name="fname" value="<%= employe.getFullName() %>" required>
        </div>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" value="<%= employe.getUsername() %>" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" value="<%=employe.getPassword() %>" required>
        </div>
        <div class="form-group">
            <label for="salary">Salary:</label>
            <input type="text" class="form-control" id="salary" name="salary" value="<%=employe.getSalary()%>" >
        </div>
           
         <button type="submit" class="btn btn-primary">modify</button>    
    </form>
    <%}else{%>  
            <h2>Add Employe :  </h2>
    
    <hr>
	
        <form action="insert" method="POST">   
        <div class="form-group">
            <label for="fname">Full Name:</label>
            <input type="text" class="form-control" id="fname" name="fname" required>
        </div>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" class="form-control" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="salary">Salary:</label>
            <input type="text" class="form-control" id="salary" name="salary" >
        </div>
           
         <button type="submit" class="btn btn-primary">Add</button>    
    </form>
    <%}%> 
</div>

  <script>
  document.addEventListener("DOMContentLoaded", function() {
	  const sidebarLinks = document.querySelectorAll(".second-text");
	  const addbtn = document.querySelectorAll(".second-text")[3];
	  
	  
	  
	  sidebarLinks.forEach(otherLink => otherLink.classList.remove("active"));
	  addbtn.classList.add("active");
	  
	});
  </script>

<jsp:include page="sidebar2.jsp"/>
