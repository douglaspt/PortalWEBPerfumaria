package interfaces;

import java.util.List;

import entidades.FormaPagamento;

public interface FormaPagamentoDao {

	public abstract FormaPagamento buscarPorId(long id);
	
	public abstract List<FormaPagamento> buscarTodos();
		
	public abstract void salva(FormaPagamento c);
	
	public abstract void exclui(FormaPagamento c);	
	
}
