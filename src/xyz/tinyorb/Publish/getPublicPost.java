package xyz.tinyorb.Publish;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import xyz.tinyorb.converter.ToJsonString;
import xyz.tinyorb.hibernate.App.Article.ListUserPost;
import xyz.tinyorb.hibernate.App.Publisher.GetPublish;

/**
 * Servlet implementation class getPublicPost
 */
@WebServlet("/getPublicPost")
public class getPublicPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getPublicPost() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_NOT_FOUND);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		HttpSession session = request.getSession(false);
		if(session != null)
		{
			String responseStr = new ToJsonString().converter(new GetPublish().getTenRecentPublicPublishList());
			if(responseStr != null)
				response.getWriter().write(responseStr);
			else
				response.getWriter().write("No Data");
		}
		else
		{
			String responseStr = new ToJsonString().converter(new GetPublish().getTenRecentPublicPublishList());
			if(responseStr != null)
				response.getWriter().write(responseStr);
			else
				response.getWriter().write("No Data");
		}
	}

}
