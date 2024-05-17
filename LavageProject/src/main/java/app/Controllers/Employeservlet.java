package app.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import app.Dao.BookingDb;
import app.Dao.ClientDb;
import app.Dao.EmployeDb;
import app.Dao.servicesDAO;
import app.models.Booking;
import app.models.Employe;
import app.models.Client;
import app.models.services;



@WebServlet("/")
public class Employeservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EmployeDb employeDAO;
    private BookingDb bookingDAO;
    private ClientDb clientDB;
    private servicesDAO serviceDAO;


	public void init() {
		employeDAO = new EmployeDb();
		bookingDAO = new BookingDb();
		clientDB = new ClientDb();
		 serviceDAO = new servicesDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/logout":
				logout(request, response);
				break;
			case "/dashboard":
				dashboard(request, response);
				break;
			case "/loginn":
				login(request, response);
				break;
			case "/login":
				showlogin(request, response);
				break;
			case "/newEmploye":
				showNewForm(request, response);
				break;
			case "/insertEmploye":
				insertEmploye(request, response);
				break;
			case "/deleteEmploye":
				deleteEmploye(request, response);
				break;
			case "/editEmploye":
				showEditForm(request, response);
				break;
			case "/updateEmploye":
				updateEmploye(request, response);
				break;
			case "/newBooking":
				showNewFormbooking(request, response);
				break;
			case "/insertBooking":
				insertBooking(request, response);
				break;
			case "/deleteBooking":
				deleteBooking(request, response);
				break;
			case "/editBooking":
				showEditFormbooking(request, response);
				break;
			case "/updateBooking":
				updateBooking(request, response);
				break;
			case "/listBooking":
				listBooking(request, response);
				break;	
			case "/newClient":
				showNewFormClient(request, response);
				break;
			case "/insertClient":
				insertclient(request, response);
				break;
			case "/deleteClient":
				deleteclient(request, response);
				break;
			case "/editClient":
				showEditFormClient(request, response);
				break;
			case "/updateClient":
				updateclient(request, response);
				break;
			case "/listClient":
				listclient(request, response);
				break;
			case "/newService":
                showNewFormService(request, response);
                break;
            case "/insertService":
                insertService(request, response);
                break;
            case "/deleteService":
                deleteService(request, response);
                break;
            case "/editService":
                showEditFormService(request, response);
                break;
            case "/updateService":
                updateService(request, response);
                break;
            case "/listService":
                listService(request, response);
                break;	
	
			default:
				listEmploye(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	 private void dashboard(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        
	        request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
	    }

	private void listEmploye(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Employe> listemploye = employeDAO.selectAllEmployes();
		request.setAttribute("listEmploye", listemploye);
		RequestDispatcher dispatcher = request.getRequestDispatcher("listEmploye.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("addEmploye.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Employe existingEmploye = employeDAO.selectEmploye(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addEmploye.jsp");
		request.setAttribute("employe", existingEmploye);
		dispatcher.forward(request, response);

	}

	private void insertEmploye(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String fname = request.getParameter("fname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		double salary = Double.parseDouble(request.getParameter("salary"));
		Employe newEmploye = new Employe(fname, username, password,salary);
		employeDAO.insertEmploye(newEmploye);
		response.sendRedirect("listEmploye");
	}

	private void updateEmploye(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String fname = request.getParameter("fname");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		double salary = Double.parseDouble(request.getParameter("salary"));
		Employe newEmploye = new Employe(id,fname, username, password,salary);
		employeDAO.updateEmploye(newEmploye);
		response.sendRedirect("listEmploye");
	}

	private void deleteEmploye(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		employeDAO.deleteEmploye(id);
		response.sendRedirect("listEmploye");

	}
	
	
	/////////////////////////////////////////booking/////////////////////////////////////////////////////
	
	
	private void listBooking(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Booking> listbooking = bookingDAO.selectAllBookings();
		
		request.setAttribute("listBooking", listbooking);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("listBooking.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewFormbooking(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<services> listservices = serviceDAO.selectAllServices();
		request.setAttribute("listService", listservices);
		List<Employe> listemploye = employeDAO.selectAllEmployes();
		request.setAttribute("listEmploye", listemploye);
		List<Client> listClient = clientDB.selectAllclients();
		request.setAttribute("listclient", listClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addBooking.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditFormbooking(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<services> listservices = serviceDAO.selectAllServices();
		request.setAttribute("listService", listservices);
		List<Employe> listemploye = employeDAO.selectAllEmployes();
		request.setAttribute("listEmploye", listemploye);
		List<Client> listClient = clientDB.selectAllclients();
		request.setAttribute("listclient", listClient);
		int id = Integer.parseInt(request.getParameter("id"));
		Booking existingBooking = bookingDAO.selectBooking(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addBooking.jsp");
		request.setAttribute("booking", existingBooking);
		dispatcher.forward(request, response);

	}

	private void insertBooking(HttpServletRequest request, HttpServletResponse response) 
	        throws SQLException, IOException {

	    int clientId = Integer.parseInt(request.getParameter("clientId"));
	    int employeeId = Integer.parseInt(request.getParameter("employeeId"));
	    int serviceId = Integer.parseInt(request.getParameter("serviceId"));
	    String bookingDateString = request.getParameter("bookingDate");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
	    java.sql.Date bookingDate;
	    try {
	      bookingDate = new java.sql.Date(dateFormat.parse(bookingDateString).getTime());
	      // Use bookingDate for database interactions
	    } catch (ParseException e) {
	      e.printStackTrace();  
	      throw new IOException("Invalid date format. Please use YYYY-MM-DD format."); 
	    }

	    String description = request.getParameter("description");
	    double price = 1.1;

	    Booking newBooking = new Booking(clientId, employeeId, bookingDate, description, price,serviceId);
	    bookingDAO.insertBooking(newBooking);
	    response.sendRedirect("listBooking");
	}


	private void updateBooking(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int clientId = Integer.parseInt(request.getParameter("clientId"));
	    int employeeId = Integer.parseInt(request.getParameter("employeeId"));
	    int serviceId = Integer.parseInt(request.getParameter("serviceId"));
	    String bookingDateString = request.getParameter("bookingDate");
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
	    java.sql.Date bookingDate;
	    try {
	      bookingDate = new java.sql.Date(dateFormat.parse(bookingDateString).getTime());
	      // Use bookingDate for database interactions
	    } catch (ParseException e) {
	      e.printStackTrace();  
	      throw new IOException("Invalid date format. Please use YYYY-MM-DD format."); 
	    }
	    
		    String description = request.getParameter("description");
		    double price = 1.1;
		    String statut = request.getParameter("statut");

		    Booking newBooking = new Booking(id,clientId, employeeId, bookingDate, description, price,statut,serviceId,"","","");
		bookingDAO.updateBooking(newBooking);
		response.sendRedirect("listBooking");
	}

	private void deleteBooking(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		bookingDAO.deleteBooking(id);
		response.sendRedirect("listBooking");

	}

	/////////////////////////////////////////Clients/////////////////////////////////////////////////////

	private void listclient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Client> listClient = clientDB.selectAllclients();
		request.setAttribute("listClient", listClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("client-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewFormClient(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("addClient.jsp");
		
		dispatcher.forward(request, response);
	}

	private void showEditFormClient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Client> listClient = clientDB.selectAllclients();
		int id = Integer.parseInt(request.getParameter("id"));
		Client existingUser = clientDB.selectClient(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("addClient.jsp");
		request.setAttribute("client", existingUser);
		request.setAttribute("listclient", listClient);
		dispatcher.forward(request, response);

	}

	private void insertclient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String prenom = request.getParameter("prenom");
		String name = request.getParameter("nom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String description = request.getParameter("description");
		Client newClient = new Client(prenom,name, email,telephone,description );
		clientDB.insertClient(newClient);
		response.sendRedirect("listClient");
	}

	private void updateclient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String prenom = request.getParameter("prenom");
		String name = request.getParameter("nom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String description = request.getParameter("description");

		Client newclient = new Client(id, prenom,name,email, telephone,description);
		clientDB.updateclient(newclient);
		response.sendRedirect("listClient");
	}

	private void deleteclient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		clientDB.deleteclient(id);
		response.sendRedirect("listClient");

	}
	
	
	/////////////////////////////////////////Services/////////////////////////////////////////////////////
	
	
    private void listService(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<services> listService = serviceDAO.selectAllServices();
        request.setAttribute("listService", listService);
        request.getRequestDispatcher("service-liste.jsp").forward(request, response);
    }

    private void showNewFormService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("service", new services());
        request.getRequestDispatcher("services.jsp").forward(request, response);
    }

    private void showEditFormService(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        services existingService = serviceDAO.selectService(id);
        request.setAttribute("service", existingService);
        request.getRequestDispatcher("services.jsp").forward(request, response);
    }

    private void insertService(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        services newService = new services(0, name, description, price);
        serviceDAO.insertService(newService);
        response.sendRedirect("listService");
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        services service = new services(id, name, description, price);
        serviceDAO.updateService(service);
        response.sendRedirect("listService");
    }

    private void deleteService(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        serviceDAO.deleteService(id);
        response.sendRedirect("listService");
    }
	
   ////////////////////////////////////////////////////////////////////////////////////////////
    
    private void showlogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	HttpSession sessions = request.getSession(false);
    	sessions.invalidate(); 
    	response.sendRedirect("login");
    }
    
    private void login(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("login?emptyFields=1");
            return;
        }

        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/jaava_db", "root", "");

            // Prepare SQL statement to check user credentials
            String sql = "SELECT * FROM user WHERE full_name = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            // Check if user exists
            if (rs.next()) {
                // Authentication successful, create session and redirect to dashboard.jsp
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                response.sendRedirect("dashboard");
            } else {
                // Authentication failed, redirect back to login page with an error message
                response.sendRedirect("login?error=1");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle database connection errors or SQL exceptions
            response.sendRedirect("error.jsp");
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
}
