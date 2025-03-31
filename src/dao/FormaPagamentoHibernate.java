package dao;

import interfaces.FormaPagamentoDao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import entidades.FormaPagamento;

public class FormaPagamentoHibernate implements FormaPagamentoDao{

	private final Session session;	
	
	public FormaPagamentoHibernate(final Session session) {
		this.session = session;
	}

	@Override
	public FormaPagamento buscarPorId(long id) {
		return (FormaPagamento) session.load(FormaPagamento.class, id);
	}

	@Override
	public List<FormaPagamento> buscarTodos() {
		return session.createCriteria(FormaPagamento.class).addOrder(Order.asc("id")).list();
	}

	@Override
	public void exclui(FormaPagamento c) {
		Transaction t = session.beginTransaction();
		try {
			session.delete(c);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}
		
	}

	@Override
	public void salva(FormaPagamento c) {
		Transaction t = session.beginTransaction();
		try {
			session.merge(c);
			t.commit();
		
		} catch (Exception e) {
			t.rollback();
		}
		
	}

}
