package dao;

import interfaces.CriticaDao;

import java.util.ArrayList;
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
import entidades.FormaPagamento;
import entidades.relatorios.Resumo;
import entidades.relatorios.ResumoFinanceiro;

public class CriticaHibernate implements CriticaDao{

	private final Session session;

	
	public CriticaHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public Critica buscarPorId(long id) {
		return (Critica) session.load(Critica.class, id);
	}

	@Override
	public List<Critica> buscar() {
		return session.createCriteria(Critica.class).addOrder(Order.asc("id")).list();
	}

	@Override
	public void exclui(Critica c) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(c);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public void salva(Critica c) {
		Transaction t = session.beginTransaction();
		try {
			session.merge(c);
			t.commit();
		
		} catch (Exception e) {
			t.rollback();
		}
		
	}
	
	
	@Override
	public void limpa() {
		Transaction t = session.beginTransaction();
		try {
			session.createQuery("delete from Critica").executeUpdate();
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}
	
		
	@Override
	public void criticar() {
		Transaction t = session.beginTransaction();
		try {
			System.out.println("aguarde processando criticas");
			session.connection().prepareCall("{call spc_criticarDescontos}").execute();
			//st.execute();
			System.out.println("criticas processadas");
			//session.createQuery("exec spc_criticarDescontos").executeUpdate();
			t.commit();
		} catch (Exception e) {
			System.out.println("erro");
			e.printStackTrace();
			t.rollback();
		}
		
	}

	@Override
	public List<Critica> buscarPorParametros(String linha, String idBeneficiario, String idEmpresa, 
			String matricula, Date referencia, String nome,Date dataCompra, ClassificacaoCritica classificacaoCritica,
			String numeroNF, String serie, String idControle, FormaPagamento formaPagamento){
		
		Criteria c = session.createCriteria(Critica.class);
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
	public List<Critica> buscarPorDesconto(long id) {
		Criteria c = session.createCriteria(Critica.class);
		Criteria d = c.createCriteria("desconto");
		d.add(Restrictions.eq("id", id));
		return c.list();
	}
	/*
	@Override
	public List<Resumo> buscaResumoCritica() {
		List<Resumo> resumos = new ArrayList<Resumo>();
		String sql = "select c.classificacaoCritica_id, cc.descricao, f.id, f.descricao," +
				" sum(d.vlInformado) as vlInformado, sum(d.vlPago) as vlPago, sum(d.vlInformado / d.nparcela) as valorCobrar," +
				" count(c.desconto_id) as total from Critica as c inner join Desconto  as d on c.desconto_id= d.id" +
				" inner join FormaPagamento as f on d.formaPagamento_id = f.id" +
				" inner join ClassificacaoCritica cc on c.classificacaoCritica_id = cc.id" +
				" group by c.classificacaoCritica_id, cc.descricao, f.id, f.descricao" +
				" order by c.classificacaoCritica_id asc, f.id asc";
		
		for (Object	element : session.createSQLQuery(sql).list() ) {
			Object[] row = (Object[]) element;
			Resumo resumo = new Resumo();
			ClassificacaoCritica classificacaoCritica = new ClassificacaoCritica();
			classificacaoCritica.setId(Long.parseLong(row[0].toString()));
			resumo.setClassificacaoCritica(classificacaoCritica);
			resumo.setDescFormaPagamento(row[1].toString());
			resumo.setFormaPagamento(Long.parseLong(row[2].toString()));
			resumo.setDescricao(row[3].toString());
			resumo.setSomaValorInformado(Double.parseDouble(row[4].toString()));
			resumo.setSomaValorPago(Double.parseDouble(row[5].toString()));
			resumo.setSomaValorCobrar(Double.parseDouble(row[6].toString()));
			resumo.setQtde(Integer.parseInt(row[7].toString()));			
			resumos.add(resumo);
			
		}
		
		return resumos;	
	
	}
	*/
	

	@Override
	public List<Resumo> buscaResumoCritica(Date referencia) {
		Criteria c = session.createCriteria(Critica.class);
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
		Criteria c = session.createCriteria(Critica.class);
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

}
