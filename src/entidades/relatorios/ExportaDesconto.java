package entidades.relatorios;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import entidades.Critica;
import entidades.CriticaHistorico;


public class ExportaDesconto {
	
	private int linha;	

	private String referencia;

	private String idBeneficiario;

	private String idEmpresa;

	private String matricula;

	private String digito;	

	private String nome;

	private String documento;

	private String numeroNF;

	private String dataCompra;

	private String vlInformado;
	
	private String vlPago;	

	private String descricaoStatus;

	public ExportaDesconto() {
		
	}
	
	public ExportaDesconto(Critica critica) {
		DecimalFormat df12  = new DecimalFormat("000000000000");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		linha = critica.getDesconto().getLinha();
		referencia = sdf.format(critica.getDesconto().getReferencia());
		idBeneficiario = Integer.toString(critica.getDesconto().getIdBeneficiario());
		idEmpresa = Integer.toString(critica.getDesconto().getIdEmpresa());
		matricula = Integer.toString(critica.getDesconto().getMatricula());
		digito = Integer.toString(critica.getDesconto().getDigito());
		nome = critica.getDesconto().getNome();
		documento = critica.getDesconto().getDocumento();
		numeroNF = Integer.toString(critica.getDesconto().getNumeroNF());
		dataCompra = sdf.format(critica.getDesconto().getDataCompra());
		vlInformado = df12.format(critica.getDesconto().getVlInformado()*100);
		vlPago = df12.format(critica.getDesconto().getVlPago());
		descricaoStatus = critica.getClassificacaoCritica().getDescricao();
	}
	
	public ExportaDesconto(CriticaHistorico critica) {
		DecimalFormat df12  = new DecimalFormat("000000000000");
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
		linha = critica.getDesconto().getLinha();
		referencia = sdf.format(critica.getDesconto().getReferencia());
		idBeneficiario = Integer.toString(critica.getDesconto().getIdBeneficiario());
		idEmpresa = Integer.toString(critica.getDesconto().getIdEmpresa());
		matricula = Integer.toString(critica.getDesconto().getMatricula());
		digito = Integer.toString(critica.getDesconto().getDigito());
		nome = critica.getDesconto().getNome();
		documento = critica.getDesconto().getDocumento();
		numeroNF = Integer.toString(critica.getDesconto().getNumeroNF());
		dataCompra = sdf.format(critica.getDesconto().getDataCompra());
		vlInformado = df12.format(critica.getDesconto().getVlInformado()*100);
		vlPago = df12.format(critica.getDesconto().getVlPago());
		descricaoStatus = critica.getClassificacaoCritica().getDescricao();
	}



	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getIdBeneficiario() {
		return idBeneficiario;
	}

	public void setIdBeneficiario(String idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getDigito() {
		return digito;
	}

	public void setDigito(String digito) {
		this.digito = digito;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNumeroNF() {
		return numeroNF;
	}

	public void setNumeroNF(String numeroNF) {
		this.numeroNF = numeroNF;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getVlInformado() {
		return vlInformado;
	}

	public void setVlInformado(String vlInformado) {
		this.vlInformado = vlInformado;
	}

	public String getVlPago() {
		return vlPago;
	}

	public void setVlPago(String vlPago) {
		this.vlPago = vlPago;
	}

	public String getDescricaoStatus() {
		return descricaoStatus;
	}

	public void setDescricaoStatus(String descricaoStatus) {
		this.descricaoStatus = descricaoStatus;
	}

	
	
	
	
}
