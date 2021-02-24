
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
 * Servlet implementation class TechExercise
 */
@WebServlet("/TechExercise")
public class TechExercise extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TechExercise() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
	      String date = request.getParameter("date");
	      String description = request.getParameter("description");
	      String complete = request.getParameter("complete");
	      
	      Connection connection = null;
	      String insertSql = " INSERT INTO TechExerciseTable (id, TITLE, DATE, DESCRIPTION, COMPLETE) values (default, ?, ?, ?, ?)";

	      try {
	         DBConnectionOliveiraDosSantos.getDBConnection(getServletContext());
	         connection = DBConnectionOliveiraDosSantos.connection;
	         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
	         preparedStmt.setString(1, title);
	         preparedStmt.setString(2, date);
	         preparedStmt.setString(3, description);
	         preparedStmt.setString(4, complete);
	         preparedStmt.execute();
	         connection.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      
	      String Title = "Sticky Notes";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + Title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h2 align=\"center\">" + Title + "</h2>\n" + //
	            "<ul>\n" + //

	            "  <li><b>Title</b>: " + title + "\n" + //
	            "  <li><b>Date</b>: " + date + "\n" + //
	            "  <li><b>Description</b>: " + description + "\n" + //
	            "  <li><b>Status</b>: " + complete + "\n" + //
	           
	    		  "</ul>\n");

	      out.println("<a href=/Tech-Exercise-OliveiraDosSantos/TechExercise.html>New Sticky Note</a> <br>");
	      out.println("</body></html>");
	      out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
