package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import repositories.RelatoriosRepository;

public class Teste {
	
	public static void main(String[] args) {
		
		RelatoriosRepository r = new  RelatoriosRepository();
		
		LocalDate dataManipulacao = LocalDate.now();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date a = Date.from(dataManipulacao.atStartOfDay(ZoneId.systemDefault()).toInstant());
		System.out.println(format.format(a));
		
	}
}
