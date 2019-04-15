package com.likeminds.EBSAuth;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EBSAuthServlet
 */
@WebServlet("/loginAction")
public class EBSAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EBSAuthServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String name = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		
		if(name.equalsIgnoreCase("Thani") && userpass.equalsIgnoreCase("Thani")){
			
			System.out.println("Session creation ::::::: Likeminds");
			
			//EBSAdapter.reset_session(490283146);
			
			int sessionID = EBSAdapter.create_session(1015104);
			System.out.println("sessionID ===== " + sessionID );
			
			String xSid= EBSAdapter.getxid(sessionID);
		//	logger.debug("XSID is :: " + xSid);
			System.out.println("XID =" + xSid );
			Cookie cookie = new Cookie("VIS", xSid);
			System.out.println("Cookie value VIS ::" + cookie.getValue());
			//cookie.setDomain(".likemindscloud.com");
			System.out.println("Domain is ::" + cookie.getDomain());
			cookie.setPath("/");
			System.out.println("Path ::" + cookie.getPath());
			cookie.setHttpOnly(true);
			response.addCookie(cookie);
			response.sendRedirect("http://ebs.likemindscloud.com:8000/OA_HTML/OA.jsp?OAFunc=OAHOMEPAGE");
				
			//response.sendRedirect("success.jsp");
		}else {
			response.sendRedirect("error.jsp");
		}
	}
//project test for sindhuja m 
}
