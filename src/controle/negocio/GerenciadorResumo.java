package controle.negocio;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.context.FacesContext;

import dao.CriticaHibernate;
import dao.CriticaHistoricoHibernate;
import entidades.FormaPagamento;
import entidades.relatorios.Resumo;

public class GerenciadorResumo extends Gerenciador{
	
	private String titulo = "Resumo do Processamento de Criticas";
	private Calendar referencia = new GregorianCalendar();
	private String mesAno = "";
	private Resumo resumo = new Resumo();
	private FormaPagamento formaPagamento = new FormaPagamento();
	private List<Resumo> resumos = new ArrayList<Resumo>();
	private CriticaHibernate cH = new CriticaHibernate(session);
	private CriticaHistoricoHibernate chH = new CriticaHistoricoHibernate(session);
	private FacesContext facesContext = FacesContext.getCurrentInstance();  
	private GerenciadorCriticas gerenciadorCriticas = (GerenciadorCriticas) facesContext.getApplication().createValueBinding("#{gerenciadorCriticas}").getValue(facesContext); 
	private boolean processamentoCritica = false;
	
	public String verCriticas(){
		resumo = (Resumo) objDataTable.getRowData();
		formaPagamento.setId(resumo.getFormaPagamento());
		return  gerenciadorCriticas.pesquisaPorParametro(mesAno, resumo.getClassificacaoCritica(), formaPagamento);
	}

	public String abrir(){
		resumos.clear();
		processamentoCritica = false;
		return "resumoCriticas";
	}
	
	public String abrirProcessamento(){
		resumos.clear();
		processamentoCritica = true;
		return "resumoCriticas";
	}
	
	public String pesquisar() {
		//Calendar d = new GregorianCalendar();
		resumos.clear();
		referencia.set(Integer.parseInt(mesAno.substring(3,7)),Integer.parseInt(mesAno.substring(0,2)) - 1,1);
		resumos = cH.buscaResumoCritica(referencia.getTime());
		System.out.println("Resumo Critica Atual");
		if(resumos.size()==0||resumos==null) {
			resumos = chH.buscaResumoCritica(referencia.getTime());	
			System.out.println("HistoricoMovimentacao");
		}
		/*
		if ((referencia.get(Calendar.MONTH) == d.get(Calendar.MONTH)) && (referencia.get(Calendar.YEAR) == d.get(Calendar.YEAR))){
			resumos = cH.buscaResumoCritica();	
		}
		else {
			resumos = chH.buscaResumoCritica(referencia.getTime());		
		}
		*/		
	
		return null;
	}

	public String processarCriticas(){
		cH.criticar();
		resumos.clear();
		referencia.set(Integer.parseInt(mesAno.substring(3,7)),Integer.parseInt(mesAno.substring(0,2)) - 1,1);
		resumos = cH.buscaResumoCritica(referencia.getTime());	
		return null;
	}
	
	public String goHome(){
		return "index";
	}
	

	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Calendar getReferencia() {
		return referencia;
	}


	public void setReferencia(Calendar referencia) {
		this.referencia = referencia;
	}


	public Resumo getResumo() {
		return resumo;
	}


	public void setResumo(Resumo resumo) {
		this.resumo = resumo;
	}


	public List<Resumo> getResumos() {
		return resumos;
	}


	public void setResumos(List<Resumo> resumos) {
		this.resumos = resumos;
	}


	public CriticaHibernate getcH() {
		return cH;
	}


	public void setcH(CriticaHibernate cH) {
		this.cH = cH;
	}


	public CriticaHistoricoHibernate getChH() {
		return chH;
	}


	public void setChH(CriticaHistoricoHibernate chH) {
		this.chH = chH;
	}



	public String getMesAno() {
		return mesAno;
	}



	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

	public boolean isProcessamentoCritica() {
		return processamentoCritica;
	}

	public void setProcessamentoCritica(boolean processamentoCritica) {
		this.processamentoCritica = processamentoCritica;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	
	
		
	
	
	
	
	
}
