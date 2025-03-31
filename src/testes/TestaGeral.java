package testes;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Session;

import dao.CriticaHibernate;
import dao.HibernateUtil;
import entidades.relatorios.Resumo;

public class TestaGeral {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
    	Calendar data = new GregorianCalendar();
    	data.set(2015,3,1);
    	
    	String sql = "SELECT c.classificacaoCritica_id, cc.descricao, f.id, f.descricao," +
		" sum(d.vlInformado) as vlInformado, sum(d.vlPago) as vlPago, sum(d.vlInformado / d.nparcela) as valorCobrar," +
		" count(c.desconto_id) as total FROM Critica as c inner join Desconto as d on c.desconto_id = d.id" +
		" inner join FormaPagamento as f on d.formaPagamento_id = f.id" +
		" inner join ClassificacaoCritica cc on c.classificacaoCritica_id = cc.id" +
		" group by c.classificacaoCritica_id, cc.descricao, f.id, f.descricao" +
		" order by c.classificacaoCritica_id asc, f.id asc";
    	//Query query = (Query) session.createQuery(sql);
    	
    	List list = session.createSQLQuery(sql).list();

    	System.out.println(list);

		        
    		List<Resumo> r = new CriticaHibernate(session).buscaResumoCritica(data.getTime());
    		System.out.println(r.size());
    	
    		for (Resumo resumo : r) {
    			System.out.println(resumo.getDescFormaPagamento()+"   VL. INF:"+resumo.getSomaValorInformado() + "   VLPG:"+resumo.getSomaValorPago()+
    					"   VLCOB: "+resumo.getSomaValorCobrar());
    		}	
		
		
	}
	
	
	

}
