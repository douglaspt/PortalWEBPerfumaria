package dao;

import interfaces.LoteDao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import entidades.Lote;

public class LoteHibernate implements LoteDao{

	private final Session session;	
	
	public LoteHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public Lote buscarPorId(long id) {
		return (Lote) session.load(Lote.class, id);
	}

	@Override
	public List<Lote> buscarTodos() {
		return session.createCriteria(Lote.class).addOrder(Order.asc("id")).list();
	}

	@Override
	public void exclui(Lote c) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(c);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public void salva(Lote c) {
		Transaction t = session.beginTransaction();
		try {
			session.merge(c);
			t.commit();
		
		} catch (Exception e) {
			t.rollback();
		}
		
	}

}
