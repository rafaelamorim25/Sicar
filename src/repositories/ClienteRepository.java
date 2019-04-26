package repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Cliente;

public class ClienteRepository {
	
	LancamentoRepository lancamentoRepository = new LancamentoRepository();
	
	public Integer save(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO cliente (nome_cliente, cpf_cliente, email_cliente) VALUES (?, ?, ?)";
		Integer ret = 0;

		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);

			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setLong(2, cliente.getCpf());
			preparedStatement.setString(3, cliente.getEmail());
			
			ret = preparedStatement.executeUpdate();
			preparedStatement.close();
		
		return ret;
	}
	
	public Cliente findBy(Integer id) {
		String sql = "SELECT id_cliente, nome_cliente, cpf_cliente, email_cliente FROM cliente WHERE id_cliente = ?";
		Cliente cliente = null;
		
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		
		try {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				cliente = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getString(4));
				lancamentoRepository.findLancamentosFrom(cliente);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao lista cliente");
			e.printStackTrace();
		}
		
		return cliente;	
	}
	
	public Cliente findBy(Long cpf) {
		String sql = "SELECT id_cliente, nome_cliente, cpf_cliente, email_cliente FROM cliente WHERE cpf_cliente = ?";
		Cliente cliente = null;
		
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		
		try {
			preparedStatement.setLong(1, cpf);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				cliente = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getString(4));
				lancamentoRepository.findLancamentosFrom(cliente);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar cliente");
			e.printStackTrace();
		}
		
		return cliente;	
	}
	
	public List<Cliente> findAll(){
		String sql = "SELECT id_cliente, nome_cliente, cpf_cliente, email_cliente FROM cliente";
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		
		try {
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Cliente cliente = new Cliente(resultSet.getInt(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getString(4));
				lancamentoRepository.findLancamentosFrom(cliente);
				clientes.add(cliente);
			}
			
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao listar clientes");
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	public int delete(Cliente cliente) {
		String sql = "DELETE FROM cliente WHERE id_cliente = ?";
		Integer ret = 0;
		
		lancamentoRepository.deleteLancamentosFrom(cliente);

		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		
		try {
			preparedStatement.setInt(1, cliente.getId());
			
			ret = preparedStatement.executeUpdate();
			
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("erro ao deletar cliente");
			e.printStackTrace();
		}
		
		return ret;
	}
	
	public int update(Cliente cliente) {
		String sql = "UPDATE cliente SET nome_cliente = ?, cpf_cliente = ?, email_cliente = ? WHERE id_cliente = ?";
		int ret = 0;
		
		PreparedStatement preparedStatement = ConnectionManager.getConnectionManager().getPrepareStatement(sql);
		
		try {
			preparedStatement.setString(1, cliente.getNome());
			preparedStatement.setLong(2, cliente.getCpf());
			preparedStatement.setString(3, cliente.getEmail());
			preparedStatement.setInt(4, cliente.getId());
			ret = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar cliente");
			e.printStackTrace();
		}
		
		return ret;		
	}
}
