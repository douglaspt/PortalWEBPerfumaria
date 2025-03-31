package entidades.relatorios;

import entidades.ClassificacaoCritica;
import entidades.FormaPagamento;


public class Resumo {
	private ClassificacaoCritica classificacaoCritica;
	private String descricao;
	private long formaPagamento;
	private String descFormaPagamento;
	private double somaValorInformado = 0;
	private double somaValorPago = 0 ;
	private double somaValorCobrar = 0 ;
	private int qtde = 0;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


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

	public ClassificacaoCritica getClassificacaoCritica() {
		return classificacaoCritica;
	}
	public void setClassificacaoCritica(ClassificacaoCritica classificacaoCritica) {
		this.classificacaoCritica = classificacaoCritica;
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
	public double getSomaValorCobrar() {
		return somaValorCobrar;
	}
	public void setSomaValorCobrar(double somaValorCobrar) {
		this.somaValorCobrar = somaValorCobrar;
	}




	
	
	
}
