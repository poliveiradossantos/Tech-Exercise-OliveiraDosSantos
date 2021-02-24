

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DisplayAllStickyNotes
 */
@WebServlet("/DisplayAllStickyNotes")
public class DisplayAllStickyNotes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayAllStickyNotes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection connection = null;
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	
	    try 
	      {
	    	  DBConnectionOliveiraDosSantos.getDBConnection(getServletContext());
		      connection = DBConnectionOliveiraDosSantos.connection;
		      String selectSQL = "SELECT * FROM TechExerciseTable";
		      PreparedStatement preparedSelectStmt = connection.prepareStatement(selectSQL);
		      
		      ResultSet rs = preparedSelectStmt.executeQuery();
		      
		      while(rs.next())
		      {
		    	  String rsTitle = rs.getString("title");
		    	  String rsDate = rs.getString("date");
		    	  String rsDescription = rs.getString("description");
		    	  String rsStatus = rs.getString("complete");
		      
		    	  out.println(rsTitle + "<br>");
		    	  out.println(rsDate + "<br>");
		    	  out.println(rsDescription + "<br>");
		    	  out.println(rsStatus + "<br> <br>");
		      }
		      connection.close();
		      rs.close();
		      out.println("<a href=/Tech-Exercise-OliveiraDosSantos/TechExercise.html>Add New Sticky Note</a> <br>");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
