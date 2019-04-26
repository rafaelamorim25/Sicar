package domain;

import java.util.Date;

public class Lancamento {

	private Integer idLancamento;
	private Date data;
	private Float valor;
	private TipoLancamento tipoLancamento;

	private Cliente cliente;

	public Lancamento(Integer idLancamento, Date data, Float valor, TipoLancamento tipoLancamento, Cliente cliente) {
		super();
		this.idLancamento = idLancamento;
		this.data = data;
		this.valor = valor;
		this.tipoLancamento = tipoLancamento;
		this.cliente = cliente;
	}

	public Lancamento(Date data, Float valor, TipoLancamento tipoLancamento, Cliente cliente) {
		this.data = data;
		this.valor = valor;
		this.tipoLancamento = tipoLancamento;
		this.cliente = cliente;
	}
	
	public Lancamento(Integer idLancamento, Date data, Float valor, TipoLancamento tipoLancamento) {
		this.idLancamento = idLancamento;
		this.data = data;
		this.valor = valor;
		this.tipoLancamento = tipoLancamento;
	}

	public Integer getIdLancamento() {
		return idLancamento;
	}

	public void setIdLancamento(Integer id_lancamento) {
		this.idLancamento = id_lancamento;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	@Override
	public String toString() {
		return "Lancamento [id_lancamento=" + idLancamento + ", data=" + data + ", valor=" + valor + ", id_cliente="
				+ cliente + "]";
	}
}