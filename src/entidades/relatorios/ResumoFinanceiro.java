package entidades.relatorios;

public class ResumoFinanceiro {
	private long formaPagamento = 0;
	private String descFormaPagamento;
	private double somaValorInformado = 0;
	private double somaValorPago = 0 ;
	private int qtde = 0;
	


	public double getSomaValorInformado() {
		return somaValorInformado;
	}
	public void setSomaValorInformado(double somaValorInformado) {
		this.somaValorInformado = somaValorInformado;
	}
	public double getSomaValorPago() {
		return somaValorPago;
	}
	public void setSomaValorPago(double somaValorPago) {
		this.somaValorPago = somaValorPago;
	}
	public int getQtde() {
		return qtde;
	}
	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

	public long getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(long formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getDescFormaPagamento() {
		return descFormaPagamento;
	}
	public void setDescFormaPagamento(String descFormaPagamento) {
		this.descFormaPagamento = descFormaPagamento;
	}


	
}
