package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Usuario;
import services.LoginService;
import utils.FileToString;
import utils.RelativePath;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		sendLoginForm(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LoginService loginService = new LoginService();

		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		if (loginService.login(new Usuario(userName, password))) {
			HttpSession session = request.getSession(true);
			session.setAttribute("loggedIn", new String("true"));
			response.sendRedirect("clienteSearch");
			request.getSession().setAttribute("erro", "");
		} else {
			HttpSession session = request.getSession(true);
			session.setAttribute("loggedIn", new String("false"));
			request.getSession().setAttribute("erro", "Senha incorreta");
			sendLoginForm(request, response);
		}
	}

	private void sendLoginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String HTML;

		if (request.getSession().getAttribute("erro") != null) {
			HTML = FileToString.convert(RelativePath.fileName(this, "login.html"), "Senha incorreta");
		} else {
			HTML = FileToString.convert(RelativePath.fileName(this, "login.html"), "");
		}

		out.println(HTML);

		out.flush();
	}
}
