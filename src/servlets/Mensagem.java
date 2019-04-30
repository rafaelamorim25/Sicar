package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.FileToString;
import utils.RelativePath;

public class Mensagem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Mensagem() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sendMensagemDeAlerta(request,response);
	}

	private void sendMensagemDeAlerta(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		List<String> fields = new ArrayList<String>();
		
		/*fields.add("text-"+request.getParameter("classe"));
		fields.add("");*/
		fields.add(renderMessage(request.getParameter("domain"),request.getParameter("acao"),request.getParameter("classe")));
		
		
		String HTML = FileToString.convert(RelativePath.fileName(this, "alerta.html"), fields);

		out.println(HTML);
	}

	private String renderMessage(String domain,String acao,String classe) {
		String msg = domain;
		switch (acao) {
		
		case "insert":
			if(classe.equals("success")) {
				msg = msg + " inserido com sucesso";
			}else {
				msg = msg + " não inserido. Ocorreu erro";
			}
			break;
		case "update":
			if(classe.equals("success")) {
				msg = msg + " atualizado com sucesso";
			}else {
				msg = msg + " não atualizado. Ocorreu erro";
			}
			break;
		case "delete":
			if(classe.equals("success")) {
				msg = msg + " deletado com sucesso";
			}else {
				if (domain.equals("Cliente")) {
					msg = "Não é possível deletar cliente com divida";
				}else {
					msg = msg + " não deletado. Ocorreu erro";
				}
			}
			break;
		}
		return msg;
	}

}
