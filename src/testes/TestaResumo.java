package testes;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.internet.AddressException;

import org.hibernate.Session;

import dao.CriticaHibernate;
import dao.CriticaHistoricoHibernate;
import dao.HibernateUtil;
import entidades.CriticaHistorico;
import entidades.relatorios.Resumo;

public class TestaResumo {

	/**
	 * @param args
	 * @throws AddressException 
	 */
	public static void main(String[] args) throws IOException {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		
		Calendar referencia = new GregorianCalendar();
		referencia.set(2016,3,1);
		
		
		/*
		String smtphost = "smtp-web.kinghost.net";
		InternetAddress remetente = new InternetAddress("master@bcubrasil.com"); //Este email deve ser válido e existir como conta de email para o domínio
		InternetAddress destinatario = new InternetAddress("sistema@bcubrasil.com");
		String assunto  = "Assunto do email";
		String conteudo = "Este é um email teste";

		Properties p = new Properties();
		p.put("mail.smtp.host", smtphost);

		Session email = Session.getInstance(p, null);
		MimeMessage msg = new MimeMessage(email);

		msg.setFrom(remetente);
		msg.setRecipient(Message.RecipientType.TO, destinatario);
		msg.setSubject(assunto);
		msg.setContent(conteudo,"text/html");
		msg.saveChanges();

		Transport transport = email.getTransport("smtp");
		transport.connect(smtphost,"");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		*/
	//	CriticaHibernate c = new CriticaHibernate(session);
	//	c.limpa();
		
	//	c.criticar();
		/*
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setId(2);
		System.out.println("ok");
		List criticas = new CriticaHistoricoHibernate(session).buscarPorParametros(null, null,  "5", "61273", null, null, null, null, null, null, null, formaPagamento);
		System.out.println(criticas.size());
		
		
		Calendar referencia = new GregorianCalendar();
		referencia.set(2012,3,1);
        
        */
			List<CriticaHistorico> c = new CriticaHistoricoHibernate(session).buscarHistorico(referencia.getTime());//CriticaHibernate(session).buscaResumoCritica();
			System.out.println(c.size());
		
			for (CriticaHistorico critica : c) {
				System.out.println(critica.getId()+" - "+critica.getObsCritica()+" - "+critica.getClassificacaoCritica().getDescricao());
			}
		
		
	
		
		Float s = 82.2f;
		System.out.println(s);

		List<Resumo> r = new CriticaHibernate(session).buscaResumoCritica(referencia.getTime());
		System.out.println(r.size());
	
		for (Resumo resumo : r) {
			System.out.println(resumo.getClassificacaoCritica().getId() + " - " + resumo.getDescricao() + " - QTDE:"+resumo.getQtde() + " - Pagto:"+resumo.getFormaPagamento() + " - SI:"+resumo.getSomaValorInformado() + " - SP:"+resumo.getSomaValorPago());
		}
		
	/*
		Calendar referencia = new GregorianCalendar();
		referencia.set(2012,3,1);
		
		List<Resumo> rh = new CriticaHistoricoHibernate(session).buscaResumoCritica(referencia.getTime());
		System.out.println(rh.size());
		System.out.println(referencia.getTime());
		for (Resumo resumo : rh) {
			System.out.println(resumo.getClassificacaoCritica().getId() + " - " + resumo.getDescricao() + " - QTDE:"+resumo.getQtde() + " - Pagto:"+resumo.getFormaPagamento() + " - SI:"+resumo.getSomaValorInformado() + " - SP:"+resumo.getSomaValorPago());
		}
		
		
		
		
		List<ResumoFinanceiro> rf = new CriticaHibernate(session).buscaResumoFinanceiro();
		System.out.println(rf.size());
	
		for (ResumoFinanceiro resumo : rf) {
			System.out.println(" - PGTO:"+resumo.getDescFormaPagamento() +" - QTDE:"+resumo.getQtde() + " - Pagto:"+resumo.getFormaPagamento() +  " - VI:"+resumo.getSomaValorInformado() + " - SP:"+resumo.getSomaValorPago());
		}		

    */
		
	}

}
