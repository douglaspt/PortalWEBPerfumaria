package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Pattern;

@Entity
public class Desconto {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column
	@Pattern(regex="[0-9]", message="Este campo deve ser preenchido somente com numeros")
	private int linha;	
	@Column
	@Temporal(TemporalType.DATE)
	private Date referencia;
	@Column
	private int idBeneficiario;
	@Column
	private int idEmpresa;
	@Column
	private int matricula;
	@Column
	private int digito;
	@Column
	private String nome;
	@Column
	private String documento;
	@Column
	private int numeroNF;
	@Column
	@Temporal(TemporalType.DATE)
	private Date dataCompra;
	@Column
	private double vlInformado;
	@Column
	private double vlPago;
	@Column
	@Temporal(TemporalType.DATE)
	private Date dataFaturamento;
	@ManyToOne
	private Lote lote;
	@OneToOne
	private StatusDesconto status;
	@Column
	private String nomeComprador;
	@Column
	private int idInterno;
	@Column
	private int serie;
	@OneToOne
	private FormaPagamento formaPagamento;
	@Column
	private int idControle;
	@Column
	private int parcela;
	@Column
	private int nParcela;
	@Column
	private int idSolicitacao;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getLinha() {
		return linha;
	}
	public void setLinha(int linha) {
		this.linha = linha;
	}
	public Date getReferencia() {
		return referencia;
	}
	public void setReferencia(Date referencia) {
		this.referencia = referencia;
	}
	public int getIdBeneficiario() {
		return idBeneficiario;
	}
	public void setIdBeneficiario(int idBeneficiario) {
		this.idBeneficiario = idBeneficiario;
	}
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public int getMatricula() {
		return matricula;
	}
	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}
	public int getDigito() {
		return digito;
	}
	public void setDigito(int digito) {
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
	public int getNumeroNF() {
		return numeroNF;
	}
	public void setNumeroNF(int numeroNF) {
		this.numeroNF = numeroNF;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public double getVlInformado() {
		return vlInformado;
	}
	public void setVlInformado(double vlInformado) {
		this.vlInformado = vlInformado;
	}
	
	public Date getDataFaturamento() {
		return dataFaturamento;
	}
	public void setDataFaturamento(Date dataFaturamento) {
		this.dataFaturamento = dataFaturamento;
	}
	public Lote getLote() {
		return lote;
	}
	public void setLote(Lote lote) {
		this.lote = lote;
	}
	public StatusDesconto getStatus() {
		return status;
	}
	public void setStatus(StatusDesconto status) {
		this.status = status;
	}

	public String getNomeComprador() {
		return nomeComprador;
	}
	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}
	public double getVlPago() {
		return vlPago;
	}
	public void setVlPago(double vlPago) {
		this.vlPago = vlPago;
	}
	public int getIdInterno() {
		return idInterno;
	}
	public void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}
	public int getSerie() {
		return serie;
	}
	public void setSerie(int serie) {
		this.serie = serie;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public int getIdControle() {
		return idControle;
	}
	public void setIdControle(int idControle) {
		this.idControle = idControle;
	}
	public int getParcela() {
		return parcela;
	}
	public void setParcela(int parcela) {
		this.parcela = parcela;
	}
	public int getnParcela() {
		return nParcela;
	}
	public void setnParcela(int nParcela) {
		this.nParcela = nParcela;
	}
	public int getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(int idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}




		
	
	
}
