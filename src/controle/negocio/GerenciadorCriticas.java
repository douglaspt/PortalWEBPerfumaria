package controle.negocio;


import interfaces.ClassificacaoCriticaDao;
import interfaces.CriticaDao;
import interfaces.CriticaHistoricoDao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import dao.ClassificacaoCriticaHibernate;
import dao.CriticaHibernate;
import dao.CriticaHistoricoHibernate;
import dao.FormaPagamentoHibernate;
import entidades.ClassificacaoCritica;
import entidades.Critica;
import entidades.CriticaHistorico;
import entidades.FormaPagamento;

/**
 * @author bruno.teixeira
 *
 */
public class GerenciadorCriticas extends Gerenciador{
		
	private String titulo = "Visualizar Criticas do Arquivo (Motivos de N�o Pagamento)";

	
	private String linha = "";
	private String idBeneficiario = "";
	private String idEmpresa = "";
	private String matricula = "";
	private String numeroNF = "";
	private String serie = "";
	private String idControle = "";
	private String extratoIdEmpresa = "";
	private String extratoMatricula = "";	
	private String nome = "";
	private String mesAno = "";
	private String obsCritica = "";
	private Calendar referencia = new GregorianCalendar();
	private Date dataCompra = null;
	//private Date adesaoFinal = null;
	private Critica critica = new Critica();
	private ClassificacaoCritica classificacaoCritica = new ClassificacaoCritica();
	private FormaPagamento formaPagamento = new FormaPagamento();
	private ClassificacaoCritica classificacaoCriticaEdicao = new ClassificacaoCritica();
	private List<Critica> criticas = new ArrayList<Critica>();
	private List<SelectItem> classificacoes = getListaClassificacaoCritica();
	private List<SelectItem> pagamentos = getListaFormaPagamento();
	private CriticaDao criticaDao = new CriticaHibernate(session);
	private CriticaHistoricoDao criticaHistoricoDao = new CriticaHistoricoHibernate(session);
	private ClassificacaoCriticaDao classificacaoCriticaDao = new ClassificacaoCriticaHibernate(session);

	public List<SelectItem> getListaClassificacaoCritica() {
		List<ClassificacaoCritica> cl = new ClassificacaoCriticaHibernate(session).buscarTodos();
		List<SelectItem> listaSelect = new ArrayList<SelectItem>(cl.size());  
		SelectItem aux;
        aux = new SelectItem();
        aux.setLabel("");  
        aux.setValue("0");  
        listaSelect.add(aux); 
		for(ClassificacaoCritica c : cl){  
		           aux = new SelectItem();  
		           aux.setLabel(c.getDescricao());  
		           aux.setValue(c.getId());
		           aux.setDescription(c.getDescricao());
		           listaSelect.add(aux);  
		 }  
        aux = new SelectItem();  
        aux.setLabel("Todos");  
        aux.setValue("0");  
        listaSelect.add(aux); 
		return listaSelect;  	
	}
	
	public List<SelectItem> getListaFormaPagamento() {
		List<FormaPagamento> cl = new FormaPagamentoHibernate(session).buscarTodos();
		List<SelectItem> listaSelect = new ArrayList<SelectItem>(cl.size());  
		SelectItem aux;
        aux = new SelectItem();
        aux.setLabel("");  
        aux.setValue("0");  
        listaSelect.add(aux); 
		for(FormaPagamento c : cl){  
		           aux = new SelectItem();  
		           aux.setLabel(c.getDescricao());  
		           aux.setValue(c.getId());
		           aux.setDescription(c.getDescricao());
		           listaSelect.add(aux);  
		 }  

		return listaSelect;  	
	}
	
	public String download() throws IOException{ 
		System.out.println("Excel");
		ServletContext serveletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    	String destino = serveletContext.getRealPath("exportFiles/output1.csv");
    	
    	FileWriter fileWriter = new FileWriter( destino );
    	PrintWriter writer = new PrintWriter(fileWriter);
		for(Critica c : criticas){  
			writer.println(c.getDesconto().getLinha()+";"+c.getDesconto().getReferencia()+";"+c.getDesconto().getIdEmpresa()+";"+c.getDesconto().getMatricula()+
			";"+c.getDesconto().getIdBeneficiario()+";"+c.getDesconto().getNome()+";"+c.getDesconto().getDocumento()+";"+c.getDesconto().getDataCompra()+
			";"+c.getDesconto().getVlInformado()+";"+c.getDesconto().getVlPago()+";"+c.getClassificacaoCritica().getDescricao());
		 }  
    	writer.close();  
    	fileWriter.close();
		
	     OperacoesArquivos.downloadFile("output1.csv", "/exportFiles/", "csv", FacesContext.getCurrentInstance());  
	    // deve ser passado o nome do arquivo+extens�o  teste.txt   
	    return "";  
	}
	
	public String limpar(){
		critica = new Critica();
		return null;
	}
	
	public String abrir(){
		criticas.clear();
		return "criticas";
		}
	
	public String editar(){
		System.out.println("Editar");
        critica = (Critica) objDataTable.getRowData();
		return null;
	}
	
	public String salvarCritica(){
		classificacaoCriticaEdicao = classificacaoCriticaDao.buscarPorId(103l);
		critica.setDataCritica(data.getTime());
	//	critica.setVlPagar(0);
	//	critica.setVlPago(0);
		critica.setUsuario(getUsuarioLogado());
	//	critica.setDescricaoCritica(classificacaoCriticaEdicao.getDescricao());
		critica.setClassificacaoCritica(classificacaoCriticaEdicao);
		critica.getDesconto().setVlPago(0);
		criticaDao.salva(critica);
		pesquisaPorParametro();
		return "criticas";
	}
	
	
	public String salvarPagamentoIncondicional(){
		classificacaoCriticaEdicao = classificacaoCriticaDao.buscarPorId(900l);
		critica.setDataCritica(data.getTime());
	//	critica.setObsCritica(obsCritica);
		critica.setUsuario(getUsuarioLogado());
	//	critica.setDescricaoCritica(classificacaoCriticaEdicao.getDescricao());
		critica.setClassificacaoCritica(classificacaoCriticaEdicao);
		//critica.getDesconto().setVlPago(critica.getVlPago);
		criticaDao.salva(critica);
		pesquisaPorParametro();
		return "criticas";
	}
	
	public String cancelar(){
		limpar();
		return "criticas";
	}
	
	public String pesquisaPorParametro(String mesAno, ClassificacaoCritica classificacaoCritica, FormaPagamento formaPagamento) {
		criticas.clear(); 
		this.mesAno = mesAno;
		this.classificacaoCritica = classificacaoCritica;
		this.formaPagamento = formaPagamento;
		pesquisaPorParametro();
		return "criticas";
	}
	
	public String pesquisaPorParametro() {
		criticas.clear();
		limpar();
		/*if (adesaoInicial != null){
			adesaoInicial.setHours(00);
		}
		if (adesaoFinal != null){
			adesaoFinal.setHours(23);
			adesaoFinal.setMinutes(55);
		}*/
		try {
			Integer.parseInt(linha);
		}catch (Exception e){
		  linha = ""; 
		}
		try{
			Integer.parseInt(idEmpresa);	
		}catch (Exception e){
			idEmpresa = "";
		}
		try{
			Integer.parseInt(matricula);
		}catch (Exception e){
			 matricula = "";	
		}
		
		try{
			Integer.parseInt(numeroNF);
		}catch (Exception e){
			 numeroNF = "";	
		}
		try{
			Integer.parseInt(serie);
		}catch (Exception e){
			 serie = "";	
		}
		
		try{
			Integer.parseInt(idControle);
		}catch (Exception e){
			 idControle = "";	
		}
		
		referencia.set(Integer.parseInt(mesAno.substring(3,7)),Integer.parseInt(mesAno.substring(0,2)) - 1,1);
		//adesaoInicial.set(adesaoInicial.getYear(),adesaoInicial.getMonth(), adesaoInicial.getDay(),0,0,0);
		//System.out.println(adesaoInicial+" inicial");
		//System.out.println(adesaoFinal+" final");
		//adesaoFinal.set(adesaoFinal.get(adesaoFinal.YEAR), adesaoFinal.get(adesaoFinal.MONTH), adesaoFinal.get(adesaoFinal.DAY_OF_MONTH), 23, 55);
		//if ((referencia.get(referencia.MONTH) == d.get(d.MONTH)) & (referencia.get(referencia.YEAR) == d.get(d.YEAR))){
		criticas = criticaDao.buscarPorParametros(linha, idBeneficiario, idEmpresa, matricula, referencia.getTime(), 
				nome, dataCompra, classificacaoCritica, numeroNF, serie, idControle, formaPagamento);	
		System.out.println("Atual");
		if(criticas.size()==0||criticas==null) {
			criticas = criticaHistoricoDao.buscarPorParametros(linha, idBeneficiario, idEmpresa, matricula, referencia.getTime(), 
					nome, dataCompra, classificacaoCritica, numeroNF, serie, idControle, formaPagamento);	
			System.out.println("HistoricoMovimentacao");
		}
		extratoIdEmpresa = "";
		extratoMatricula = "";
		return null;
	}
	

//	public String pesquisar() {
//		criticas = criticaDao.buscar();
//		return null;
//	}
	
	public void gerarExtratoPorMatricula(){
		filtrarPorMatricula();
		gerarExtrato();
	}
	
	public String filtrarPorMatricula(){
		criticas = criticaDao.buscarPorParametros(null, null, extratoIdEmpresa, extratoMatricula, null, null, null, null, null, null, null, null);	
		criticas.addAll(criticaHistoricoDao.buscarPorParametros(null, null,  extratoIdEmpresa, extratoMatricula, null, null, null, null, null, null, null, null));	
		return null;
	}
	
	public String filtrarPorMatriculaSelecionada(){
		try {
			critica = (Critica) objDataTable.getRowData();	
			extratoIdEmpresa = Integer.toString(critica.getDesconto().getIdEmpresa());
			extratoMatricula = Integer.toString(critica.getDesconto().getMatricula());	
			System.out.println(" teste normal");
		} catch (java.lang.ClassCastException e) {
			CriticaHistorico critica = (CriticaHistorico) objDataTable.getRowData();
			extratoIdEmpresa = Integer.toString(critica.getDesconto().getIdEmpresa());
			extratoMatricula = Integer.toString(critica.getDesconto().getMatricula());
			System.out.println(" teste historico");
		}
		filtrarPorMatricula();
		return null;
	}	
	
	public String gerarExtrato(){
		verExtrato(criticas);
		return null;
	}
	
	public void verExtrato(List criticas){
		System.out.println("iniciando relatorio");
		ServletContext serveletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		String fileNamePath = serveletContext.getRealPath("reports/reportRelacaoDescontos.jasper");
		String pdfFileName = "reports/extratoPerfumariaPorMatricula"+extratoMatricula+".pdf";
		String pdfFileNamePath = serveletContext.getRealPath(pdfFileName);
		HashMap hm = new HashMap();
		System.out.println(criticas.size());
				 		
		try {
			JRBeanArrayDataSource jrdts = new JRBeanArrayDataSource(criticas.toArray());
			System.out.println("DataSource criado");
			JasperPrint print = JasperFillManager.fillReport(fileNamePath, hm, jrdts);
			//JRExporter exporter = new JRPdfExporter();
            //exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, pdfFileNamePath);
            //exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            
            // Export the PDF file
            //exporter.exportReport();        
            
            //byte[] bytes = 
            	JasperExportManager.exportReportToPdfFile(print, pdfFileNamePath);  
            
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
                      response.setContentType("application/pdf");  
                      //response.setContentLength(bytes.length);
                      response.setHeader("Expires", "0");  
                      response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");  
                      response.setHeader("Pragma", "public");  
                      System.out.println("fazendo redirect");  
                      response.sendRedirect(pdfFileName);
            

            //JasperViewer.viewReport(print,false);
        } catch (JRException e) {
            e.printStackTrace();
            System.out.println("Erro JRException");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro Exception");
        }
	}

	
	// getters e setters automaticos
	

	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getLinha() {
		return linha;
	}


	public void setLinha(String linha) {
		this.linha = linha;
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


	public String getExtratoIdEmpresa() {
		return extratoIdEmpresa;
	}


	public void setExtratoIdEmpresa(String extratoIdEmpresa) {
		this.extratoIdEmpresa = extratoIdEmpresa;
	}


	public String getExtratoMatricula() {
		return extratoMatricula;
	}


	public void setExtratoMatricula(String extratoMatricula) {
		this.extratoMatricula = extratoMatricula;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getMesAno() {
		return mesAno;
	}


	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}


	public String getObsCritica() {
		return obsCritica;
	}


	public void setObsCritica(String obsCritica) {
		this.obsCritica = obsCritica;
	}


	public Calendar getReferencia() {
		return referencia;
	}


	public void setReferencia(Calendar referencia) {
		this.referencia = referencia;
	}


	public Date getDataCompra() {
		return dataCompra;
	}


	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}


	public Critica getCritica() {
		return critica;
	}


	public void setCritica(Critica critica) {
		this.critica = critica;
	}


	public ClassificacaoCritica getClassificacaoCritica() {
		return classificacaoCritica;
	}


	public void setClassificacaoCritica(ClassificacaoCritica classificacaoCritica) {
		this.classificacaoCritica = classificacaoCritica;
	}


	public ClassificacaoCritica getClassificacaoCriticaEdicao() {
		return classificacaoCriticaEdicao;
	}


	public void setClassificacaoCriticaEdicao(
			ClassificacaoCritica classificacaoCriticaEdicao) {
		this.classificacaoCriticaEdicao = classificacaoCriticaEdicao;
	}


	public List<Critica> getCriticas() {
		return criticas;
	}


	public void setCriticas(List<Critica> criticas) {
		this.criticas = criticas;
	}


	public List<SelectItem> getClassificacoes() {
		return classificacoes;
	}


	public void setClassificacoes(List<SelectItem> classificacoes) {
		this.classificacoes = classificacoes;
	}


	public String getNumeroNF() {
		return numeroNF;
	}


	public void setNumeroNF(String numeroNF) {
		this.numeroNF = numeroNF;
	}


	public String getSerie() {
		return serie;
	}


	public void setSerie(String serie) {
		this.serie = serie;
	}


	public String getIdControle() {
		return idControle;
	}

	public void setIdControle(String idControle) {
		this.idControle = idControle;
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public List<SelectItem> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<SelectItem> pagamentos) {
		this.pagamentos = pagamentos;
	}
	
	


	
}