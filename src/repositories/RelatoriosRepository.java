package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class RelatoriosRepository {
	
	public float amountReceivedBetween(Date ini, Date fim) {
		String sql = "SELECT sum(valor_lancamento) FROM lancamento WHERE tipo_lancamento = 2 AND data_lancamento BETWEEN ? AND ?";
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		Float total = 0.0F;
		
		try {
			preparedStatement.setDate(1, new java.sql.Date(ini.getTime()));
			preparedStatement.setDate(2, new java.sql.Date(fim.getTime()));
			
			ResultSet resultSet =  preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				total = resultSet.getFloat(1);
			}
		
		}catch (Exception e){
			System.out.println("Erro ao gerar relatório");
		}
		
		return total;
	}
	
	public float amountToReceiveBetween(Date ini, Date fim) {
		String sql = "SELECT sum(valor_lancamento) FROM lancamento WHERE tipo_lancamento = 1 AND data_lancamento BETWEEN ? AND ?";
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		Float total = 0.0F;
		
		try {
			preparedStatement.setDate(1, new java.sql.Date(ini.getTime()));
			preparedStatement.setDate(2, new java.sql.Date(fim.getTime()));
			
			ResultSet resultSet =  preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				total = resultSet.getFloat(1);
			}
		
		}catch (Exception e){
			System.out.println("Erro ao gerar relatório");
		}
		
		return total;
	}
	
	public float amountReceived() {
		String sql = "SELECT sum(valor_lancamento) FROM lancamento WHERE tipo_lancamento = 2";
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		Float total = 0.0F;
		
		try {
			
			ResultSet resultSet =  preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				total = resultSet.getFloat(1);
			}
		
		}catch (Exception e){
			System.out.println("Erro ao gerar relatório");
		}
		
		return total;
	}
	
	public float amountToReceive() {
		String sql = "SELECT sum(valor_lancamento) FROM lancamento WHERE tipo_lancamento = 1";
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		Float total = 0.0F;
		
		try {
			
			ResultSet resultSet =  preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				total = resultSet.getFloat(1);
			}
		
		}catch (Exception e){
			System.out.println("Erro ao gerar relatório");
		}
		
		return total;
	}
}
