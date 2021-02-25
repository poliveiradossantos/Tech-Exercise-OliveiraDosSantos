

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteStickyNotes
 */
@WebServlet("/DeleteStickyNotes")
public class DeleteStickyNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStickyNotes() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteTitle = request.getParameter("delete");
		
		Connection connection = null;
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	
	    try 
	    {
	    	  DBConnectionOliveiraDosSantos.getDBConnection(getServletContext());
		      connection = DBConnectionOliveiraDosSantos.connection;
		      String deleteSQL = "DELETE FROM TechExerciseTable WHERE TITLE = ?";
		      PreparedStatement preparedDeleteStmt = connection.prepareStatement(deleteSQL);
		    
		      preparedDeleteStmt.setString(1, deleteTitle);
		      preparedDeleteStmt.executeUpdate();
		      
		      out.println("<a href=/Tech-Exercise-OliveiraDosSantos/TechExerciseStickyNotes.html>Back To My Sticky Notes</a> <br>");
		      connection.close();
		      out.close();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
