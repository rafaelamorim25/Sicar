package domain;

public enum TipoLancamento {
	
	COMPRA(1), PAGAMENTO(2);
	
	private int cod;
	
	TipoLancamento(int cod) {
		this.cod = cod;
	}
	
	public Integer getCod() {
		return this.cod;
	}

	public static TipoLancamento toEnum(Integer cod) {
		
		if(cod==null) {
			return null;
		}
		
		for(TipoLancamento tipoLancamento : TipoLancamento.values()) {
			if(cod.equals(tipoLancamento.getCod())) {
				return tipoLancamento;
			}
		}
		
		throw new IllegalArgumentException("Id inv�lido: " + cod);
	}
	
	public TipoLancamento inverse() {
		if(this.equals(COMPRA)) {
			return PAGAMENTO;
		}else{
			return COMPRA;
		}
	}
}
