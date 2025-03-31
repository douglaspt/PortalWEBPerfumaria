package dao;

import interfaces.CriticaHistoricoDao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import entidades.ClassificacaoCritica;
import entidades.Critica;
import entidades.CriticaHistorico;
import entidades.FormaPagamento;
import entidades.relatorios.Resumo;
import entidades.relatorios.ResumoFinanceiro;

public class CriticaHistoricoHibernate implements CriticaHistoricoDao{

	private final Session session;	
	
	public CriticaHistoricoHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public CriticaHistorico buscarPorId(long id) {
		return (CriticaHistorico) session.load(CriticaHistorico.class, id);
	}


	@Override
	public void exclui(CriticaHistorico c) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(c);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public void salva(CriticaHistorico c) {
		Transaction t = session.beginTransaction();
		try {
			session.merge(c);
			t.commit();
		
		} catch (Exception e) {
			t.rollback();
		}
		
	}


	public List<Critica> buscarPorParametros(String linha, String idBeneficiario, String idEmpresa, 
			String matricula, Date referencia, String nome,Date dataCompra, ClassificacaoCritica classificacaoCritica,
			String numeroNF, String serie, String idControle, FormaPagamento formaPagamento) {
		
		Criteria c = session.createCriteria(CriticaHistorico.class);
		Criteria d = c.createCriteria("desconto");
		
	    if (linha != null & linha != "") {
	    	d.add(Restrictions.eq("linha", Integer.parseInt(linha))) ;
	    }
	    if (idBeneficiario != null & idBeneficiario != "" ){
	    	d.add(Restrictions.eq("idBeneficiario", Integer.parseInt(idBeneficiario))) ;	
	    }
	    if (nome != null & nome != ""){
	    	d.add(Restrictions.like("nome", "%"+nome+"%"));
	    }
	    if (idEmpresa != null & idEmpresa != ""){
	    	d.add(Restrictions.eq("idEmpresa", Integer.parseInt(idEmpresa)));
	    }
	    if (matricula != null & matricula != ""){
	    	d.add(Restrictions.eq("matricula", Integer.parseInt(matricula)));
	    }
	    if (numeroNF != null & numeroNF != ""){
	    	d.add(Restrictions.eq("numeroNF", Integer.parseInt(numeroNF)));
	    }
	    if (serie != null & serie != ""){
	    	d.add(Restrictions.eq("serie", Integer.parseInt(serie)));
	    }
	    if (idControle != null & idControle != ""){
	    	d.add(Restrictions.eq("idControle", Integer.parseInt(idControle)));
	    }
	    if (referencia != null ){
	    	d.add(Restrictions.eq("referencia", referencia));
	    }
	    if (dataCompra != null & dataCompra != null) {
	    	d.add(Restrictions.between("dataCompra", dataCompra, dataCompra));	
	    }
	    if (classificacaoCritica != null && classificacaoCritica.getId()> 0){
	    	c.add(Restrictions.eq("classificacaoCritica", classificacaoCritica));
	    }
	    if (formaPagamento != null && formaPagamento.getId()> 0){
	    	d.add(Restrictions.eq("formaPagamento", formaPagamento));
	    }
	    d.addOrder(Order.desc("referencia"));
	    d.addOrder(Order.asc("linha"));
		return c.list();

	}
	

	@Override
	public List<Resumo> buscaResumoCritica(Date referencia) {
		Criteria c = session.createCriteria(CriticaHistorico.class);
		c.createAlias("desconto", "d");
		c.createAlias("classificacaoCritica", "cc");
		c.createAlias("desconto.formaPagamento", "f");
		c.add(Restrictions.eq("d.referencia", referencia));
  		ProjectionList proList = Projections.projectionList();
  		proList.add(Projections.groupProperty("classificacaoCritica").as("classificacaoCritica"));
  		proList.add(Projections.groupProperty("cc.descricao").as("descricao"));
  		proList.add(Projections.groupProperty("f.id").as("formaPagamento"));
  		proList.add(Projections.groupProperty("f.descricao").as("descFormaPagamento"));
  		proList.add(Projections.sum("d.vlInformado").as("somaValorInformado"));
  		proList.add(Projections.sum("d.vlPago").as("somaValorPago"));
  		proList.add(Projections.count("desconto").as("qtde"));
  		c.addOrder(Order.asc("classificacaoCritica.id"));
  		c.addOrder(Order.asc("f.id"));
  		c.setProjection(proList);
  		c.setResultTransformer(Transformers.aliasToBean(Resumo.class));
		return c.list();	
	}

	
	@Override
	public List<ResumoFinanceiro> buscaResumoFinanceiro(Date referencia) {
		Criteria c = session.createCriteria(CriticaHistorico.class);
		c.createAlias("desconto", "d");
		c.createAlias("desconto.formaPagamento", "f");
		c.add(Restrictions.eq("d.referencia", referencia));
  		ProjectionList proList = Projections.projectionList();
  		proList.add(Projections.groupProperty("f.id").as("formaPagamento"));  
  		proList.add(Projections.groupProperty("f.descricao").as("descFormaPagamento"));
  		proList.add(Projections.sum("d.vlInformado").as("somaValorInformado"));
  		proList.add(Projections.sum("d.vlPago").as("somaValorPago"));
  		proList.add(Projections.count("desconto").as("qtde"));
  		c.addOrder(Order.asc("f.id"));
  		c.setProjection(proList);
  		c.setResultTransformer(Transformers.aliasToBean(ResumoFinanceiro.class));
		return c.list();	
	}

	@Override
	public List<CriticaHistorico> buscarHistorico(Date referencia) {
		Criteria c = session.createCriteria(CriticaHistorico.class);
		Criteria d = c.createCriteria("desconto");
		
	    if (referencia != null ){
	    	d.add(Restrictions.eq("referencia", referencia));
	    }

	    d.addOrder(Order.desc("referencia"));
	    d.addOrder(Order.asc("linha"));
		return c.list();
	}
}
