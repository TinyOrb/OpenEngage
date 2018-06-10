package xyz.tinyorb.Auth;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tinyorb.encryptography40character.EncapCode;

import xyz.tinyorb.hibernate.App.AuthenticUser;
import xyz.tinyorb.hibernate.App.PasswordRequestApp;
import xyz.tinyorb.hibernate.App.CurrentCryptKey.CurrentCryptKey;
import xyz.tinyorb.hibernate.entity.PasswordRecoveryRequest;

/**
 * Servlet implementation class init_password_recovery
 */
@WebServlet("/init_password_recovery")
public class init_password_recovery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public init_password_recovery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.getWriter().write("success");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try{
			HttpSession session = request.getSession(true);
			String email = request.getParameter("email");
			System.out.print(email);
			String username = new AuthenticUser().CheckEmail(email);
			if(username != null)
			{
				PasswordRecoveryRequest requestedCode = new PasswordRequestApp().GetCode(username);
				List<String> key = new CurrentCryptKey().GetKey();
				String str = requestedCode.getRequestedDate().toString() +requestedCode.getCode();
				for(String k : key)
				{
					System.out.println(str + " " + k);
					EncapCode encapcode = new EncapCode();
					System.out.println(encapcode.Encryp(str, k));				
				}
				response.getWriter().write("success");
			}
			else
			{
				response.getWriter().write("fail");
			}
		}
		catch(Exception e){
			e.printStackTrace();
			response.getWriter().write("exception");
		}
		
	}

}
