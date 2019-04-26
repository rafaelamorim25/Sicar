package domain;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	
	private Integer id;
	private String nome;
	private Long cpf;
	private String email;
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();

	public Cliente(String nome, Long cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	
	public Cliente(Integer id, String nome, Long cpf, String email) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Integer getId() {
		return id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public void addLancamento(Lancamento l) {
		this.lancamentos.add(l);
	}
	
	public Float saldo() {
		Float saldo = 0.0F;
		for(Lancamento l : this.lancamentos) {
			if(l.getTipoLancamento().equals(TipoLancamento.COMPRA)) {
				saldo += (l.getValor() * -1);
			}
			
			if(l.getTipoLancamento().equals(TipoLancamento.PAGAMENTO)) {
				saldo += l.getValor();
			}
		}
		return saldo;
	}
	
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", saldo=" + saldo() + "]";
	}
}