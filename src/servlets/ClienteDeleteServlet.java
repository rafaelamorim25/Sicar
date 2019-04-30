package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Cliente;
import services.ClienteService;
import utils.FileToString;
import utils.RelativePath;

public class ClienteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ClienteService clienteService = new ClienteService();

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		Cliente cliente = clienteService.findBy(Integer.parseInt(request.getParameter("id")));
		
		if(cliente != null) {
			
			List<String> fields = new ArrayList<String>();
			
			fields.add(cliente.getNome());
			fields.add(cliente.getCpf().toString());
			fields.add(cliente.getEmail());
			fields.add(cliente.saldo().toString());
			fields.add(request.getRequestURI());
			fields.add(request.getParameter("id"));
			
			String HTML = FileToString.convert(RelativePath.fileName(this, "clienteDelete.html"), fields);

			out.println(HTML);
			
		}else {
			response.sendRedirect("clienteSearch");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ClienteService clienteService = new ClienteService();
	
		clienteService.delete(Integer.parseInt(req.getParameter("id")));
		
		resp.sendRedirect("clienteSearch");
	}

}
