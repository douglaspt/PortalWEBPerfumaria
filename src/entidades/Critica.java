package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Critica {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column	
	private Date dataCritica;
	@ManyToOne
	private Desconto desconto;
	@OneToOne
	private ClassificacaoCritica classificacaoCritica;
	@OneToOne
	private Usuario usuario;	
	@Column
	private String obsCritica;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDataCritica() {
		return dataCritica;
	}
	public void setDataCritica(Date dataCritica) {
		this.dataCritica = dataCritica;
	}

	public Desconto getDesconto() {
		return desconto;
	}
	public void setDesconto(Desconto desconto) {
		this.desconto = desconto;
	}
	public ClassificacaoCritica getClassificacaoCritica() {
		return classificacaoCritica;
	}
	public void setClassificacaoCritica(ClassificacaoCritica classificacaoCritica) {
		this.classificacaoCritica = classificacaoCritica;
	}
	public String getObsCritica() {
		return obsCritica;
	}
	public void setObsCritica(String obsCritica) {
		this.obsCritica = obsCritica;
	}
	
	
	
}
