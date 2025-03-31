package controle.negocio;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;

import dao.ProcessamentoDao;
import dao.ProcessamentoHibernate;

public class GeradorArquivoFaturamento extends Gerenciador{
	
	private String titulo = "Gera��o de Arquivo CSV para Faturamento da Greenline";
	//private StringBuffer resumoProcessamento = new StringBuffer("");
	private String resumoProcessamento = "";
	private String kaptchafield = "";

	private ProcessamentoDao processamentoDao = new ProcessamentoHibernate(session);

	
	private GerenciadorEMail gerenciadorEMail;
	

	public String confirmarGeracao(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);  
		String c = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		
		System.out.println(c);
		System.out.println(kaptchafield);
		if (kaptchafield.equals(c)){
			menssagemErro = "";
			DateTime dt = new DateTime();
			dt = new DateTime(dt.getYear(), dt.getMonthOfYear(), 1, 0, 0, 0, 0);
			String nomeArquivo = "Teste.csv";
			gerar();
			return "geracaoArquivoFaturamento";
		} else {
			menssagemErro = "A Senha digita est� incorreta. Digite novamente a senha da imagem acima.";
			kaptchafield = "";
			return "geracaoArquivoFaturamento";
		}
		
		
	}
	
	public void gerar(){ 

		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		//resumoProcessamento = "Resumo da listagem de benefici�rios inscritos no Plano Geral Familiar B�sico - PFGB, tendo como base o n�mero de inscri��es no dia "+s.format(new Date())+" \n\n\n";
		resumoProcessamento += "ATEN��O SOMENTE TESTE - ATEN��O SOMENTE TESTE - ATEN��O SOMENTE TESTE - ATEN��O SOMENTE TESTE - ATEN��O SOMENTE TESTE \n\n";
		resumoProcessamento += "Quantidade de Vidas por Plano e Tipo de Dependencia \n\n";
	//	resumoProcessamento += "PFGB Padr�o - Titulares: " + tolalTitularPFGBPadrao + "\n";
	//	resumoProcessamento += "PFGB Padr�o - Dependentes: " + tolalDependentePFGBPadrao + "\n";
	//	resumoProcessamento += "PFGB Agregados Enfermaria: " + tolalAgregadoPFGBPadrao + "\n";
	//	resumoProcessamento += "PFGB Executivo - Titulares: " + tolalTitularPFGBExecutivo + "\n";
	//	resumoProcessamento += "PFGB Executivo - Dependentes: " + tolalDependentePFGBExecutivo + "\n";
	//	resumoProcessamento += "PFGB Agregados Apartamento: " + tolalAgregadoPFGBApartamento + "\n\n";
	//	resumoProcessamento += "Tolal Geral de Vidas: " + (tolalTitularPFGBPadrao + tolalDependentePFGBPadrao + tolalAgregadoPFGBPadrao + tolalTitularPFGBExecutivo + tolalDependentePFGBExecutivo + tolalAgregadoPFGBApartamento+"\n\n");
		resumoProcessamento += "ATEN��O SOMENTE TESTE - ATEN��O SOMENTE TESTE - ATEN��O SOMENTE TESTE - ATEN��O SOMENTE TESTE - ATEN��O SOMENTE TESTE \n\n";
		String[] listaEmails = {"bruno@horizontesistemas.com","monalisa@imasf.com.br"};
		try {
			gerenciadorEMail.sendEmail( listaEmails, "Listagem de Benefici�rios Inscritos", resumoProcessamento);
		} catch (Exception e) {
			logger.error("Erro ao enviar e-mail");
			e.printStackTrace();
		}

	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumoProcessamento() {
		return resumoProcessamento;
	}

	public void setResumoProcessamento(String resumoProcessamento) {
		this.resumoProcessamento = resumoProcessamento;
	}

	public String getKaptchafield() {
		return kaptchafield;
	}

	public void setKaptchafield(String kaptchafield) {
		this.kaptchafield = kaptchafield;
	}


	
	

}
