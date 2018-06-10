package xyz.tinyorb.Auth;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tinyorb.encryptography40character.EncapCode;

import xyz.tinyorb.hibernate.App.CreateUser;
import xyz.tinyorb.hibernate.App.PasswordRequestApp;
import xyz.tinyorb.hibernate.App.CurrentCryptKey.CurrentCryptKey;

/**
 * Servlet implementation class Set_new_password
 */
@WebServlet("/Set_new_password")
public class Set_new_password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Set_new_password() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("<h1>Error! Unknown request</h1>").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pass = request.getParameter("pass");
		String request_code = request.getParameter("request_code");
		List<String> key = new CurrentCryptKey().GetKey();
		String username = null;
		for(String k : key)
		{
			EncapCode encapcode = new EncapCode();
			String str = encapcode.DeCryp(request_code,k);
			System.out.println(str);
			try{
				username = new PasswordRequestApp().GetUser(str.substring(0, 21),str.substring(21));
			}
			catch(Exception e)
			{
				
			}
			
		}

		if(username != null)
		{
			boolean status = new CreateUser().setpass(username, pass);
			if(status)
				response.getWriter().write("success");
			else
				response.getWriter().write("fail");
		}
		else
		{
			response.getWriter().write("Request is not valid");
		}
	}

}
