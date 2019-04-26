package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Cliente;
import domain.Lancamento;
import domain.TipoLancamento;

public class LancamentoRepository {
	
	public Integer save(Lancamento lancamento) {
		String sql = "INSERT INTO lancamento (data_lancamento, valor_lancamento, tipo_lancamento, id_cliente_lancamento) VALUES (?, ?, ?, ?)";
		Integer ret = 0;

		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		
		try {
			preparedStatement.setDate(1, new java.sql.Date(lancamento.getData().getTime()));
			preparedStatement.setFloat(2, lancamento.getValor());
			preparedStatement.setInt(3, lancamento.getTipoLancamento().getCod());
			preparedStatement.setInt(4, lancamento.getCliente().getId());
			
			ret = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("erro ao inserir lancamento");
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public Lancamento findBy(Integer id) {
		String sql = "SELECT id_lancamento, data_lancamento, valor_lancamento, tipo_lancamento,  id_cliente_lancamento FROM lancamento WHERE id_lancamento = ?";
		Lancamento lancamento = null;
		
		ClienteRepository clienteRepository = new ClienteRepository(); 
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		
		try {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				lancamento = new Lancamento(resultSet.getInt(1), 
						resultSet.getDate(2), 
						resultSet.getFloat(3),  
						TipoLancamento.toEnum(resultSet.getInt(4)),
						clienteRepository.findBy(resultSet.getInt(5)));
			}
			
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao buscar lancamento");
			e.printStackTrace();
		}
		
		return lancamento;	
	}
	
	public void findLancamentosFrom(Cliente cliente) {
		String sql = "SELECT id_lancamento, data_lancamento, valor_lancamento, tipo_lancamento FROM lancamento WHERE id_cliente_lancamento = ?";
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);

		try {
			
			preparedStatement.setInt(1, cliente.getId());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				cliente.addLancamento(new Lancamento(resultSet.getInt(1), 
													resultSet.getDate(2),
													resultSet.getFloat(3),
													TipoLancamento.toEnum(resultSet.getInt(4)),
													cliente));
			}
			
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar lancamentos");
		}	
	}
	
	public int delete(Lancamento lancamento) {
		String sql = "DELETE FROM lancamento WHERE id_lancamento = ?";
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		int ret = 0;
		
		try {
			
			preparedStatement.setInt(1, lancamento.getIdLancamento());
			ret = preparedStatement.executeUpdate();
			
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("Erro ao deleta lancamento");
		}	
		
		return ret;	
	}
	
	public int deleteLancamentosFrom(Cliente cliente) {
		String sql = "DELETE FROM lancamento WHERE id_cliente_lancamento = ?";
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		int ret = 0;
		
		try {
			
			preparedStatement.setInt(1, cliente.getId());
			ret = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			System.out.println("Erro ao deletar lancamentos");
		}	
		
		return ret;
	}
	
	public int update(Lancamento lancamento) {
		String sql = "UPDATE lancamento SET data_lancamento = ?, valor_lancamento = ?, tipo_lancamento = ? WHERE id_lancamento = ?";
		int ret = 0;
		
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		
		try {
			preparedStatement.setDate(1, new java.sql.Date(lancamento.getData().getTime()));
			preparedStatement.setFloat(2, lancamento.getValor());
			preparedStatement.setInt(3, lancamento.getTipoLancamento().getCod());
			preparedStatement.setInt(4, lancamento.getIdLancamento());
			ret = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar lancamento");
			e.printStackTrace();
		}
		
		return ret;		
	}

}
