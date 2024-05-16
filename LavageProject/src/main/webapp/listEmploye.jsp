<jsp:include page="sidebar1.jsp"/>
<%@ page import="app.models.Employe" %>
<%@ page import="java.util.List" %>

<div class="container mt-3">
    <h1 class="display-4 text-center">List Employees</h1>
    <br><br><br><br>
    
    <div class="d-flex justify-content-between mb-3">
      <a href="<%=request.getContextPath()%>/new" class="btn btn-primary" id="addEmployeeButton">Create Employee</a>
    </div>

    <table class="table table-striped table-hover">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Full Name</th>
          <th scope="col">Username</th>
          <th scope="col">Password</th>
          <th scope="col">Salary</th>
          <th scope="col"></th>
          <th scope="col"></th>
        </tr>
      </thead>
      <tbody id="employeeTableBody">
        <% 
        List<Employe> listEmployee = (List<Employe>) request.getAttribute("listEmploye");
        if(listEmployee != null) {
            for(Employe employee : listEmployee) {
        %>
        <tr>
          <td><%= employee.getId() %></td>
          <td><%= employee.getFullName() %></td>
          <td><%= employee.getUsername() %></td>
          <td><%= employee.getPassword() %></td>
          <td><%= employee.getSalary() %></td>
          <td>
            <a href="<%=request.getContextPath()%>/edit?id=<%= employee.getId() %>" class="btn btn-warning" id="updateEmployeeButton">Update Employee</a>
          </td>
          <td>
            <a href="<%=request.getContextPath()%>/delete?id=<%= employee.getId() %>" class="btn btn-danger" id="deleteEmployeeButton">Delete Employee</a>
          </td>
        </tr>
        <% 
            }
        }
        %>
      </tbody>
    </table>
</div>

<script>
document.addEventListener("DOMContentLoaded", function() {
    const sidebarLinks = document.querySelectorAll(".second-text");
    const addbtn = document.querySelectorAll(".second-text")[4];
  
    sidebarLinks.forEach(otherLink => otherLink.classList.remove("active"));
    addbtn.classList.add("active");
});
</script>

<jsp:include page="sidebar2.jsp"/>
