package xyz.tinyorb.mail;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class initGmail
 */
@WebServlet("/initGmail")
public class initGmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public initGmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		new gmailApi().mail("baksh12shad@gmail.com", "Testing", "Hi Shad, <br><br> How you are doing? Hope everything is fine over there");
		/*
		String x1 = request.getParameter("xyz");
		String x2 = request.getParameter("xyz1");
		response.getWriter().append(x1 + " is " + x2).append(request.getContextPath());
		doPost(request, response);*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		new gmailApi().mail("baksh12shad@gmail.com", "Testing", "Hi Shad, <br><br> How you are doing? Hope everything is fine over there");
	}

}
