
<jsp:include page="sidebar1.jsp"/>

<div class="container mt-3">
    <h2>Add Client</h2>
    <form action="insert.php" method="post">
      <div class="mb-3">
        <label for="prenom" class="form-label">First Name</label>
        <input type="text" class="form-control" id="prenom" name="prenom" maxlength="50">
      </div>
      <div class="mb-3">
        <label for="nom" class="form-label">Last Name</label>
        <input type="text" class="form-control" id="nom" name="nom" maxlength="50">
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" maxlength="100">
      </div>
      <div class="mb-3">
        <label for="telephone" class="form-label">Phone Number</label>
        <input type="tel" class="form-control" id="telephone" name="telephone" maxlength="15">
      </div>
      <div class="mb-3">
        <label for="description" class="form-label">Description</label>
        <textarea class="form-control" id="description" name="description" rows="3"></textarea>
      </div>
      <button type="submit" class="btn btn-primary">Add Client</button>
    </form>
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
