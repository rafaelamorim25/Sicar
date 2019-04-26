package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.LancamentosService;

public class LancamentoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LancamentosService lancamentosService = new LancamentosService();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>sicar</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY>");
		out.println("<CENTER>");
		out.println("<P>" + lancamentosService.delete(Integer.parseInt(request.getParameter("id"))) + "</P>");
		out.println("<A HREF=lancamentoSearch?id=" + request.getSession().getAttribute("id_cliente")
				+ ">Retornar</A>  voltar");
	}
}
