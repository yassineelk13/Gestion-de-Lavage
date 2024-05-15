
<jsp:include page="sidebar1.jsp"/>
 
<div class="container mt-5">
    <h2>Add Employee</h2>
    <hr>
    <form action="" method="POST">
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
            <input type="text" class="form-control" id="salary" name="salary">
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>
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
