package br.gov.prodeb.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.gov.prodeb.model.Pelada;
import br.gov.prodeb.model.Usuario;

public class PeladaRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;


	public List<Pelada> finAll() {
		return manager.createQuery("from Pelada", Pelada.class).getResultList();
	}

	public Pelada salvar(Pelada pelada) {
		return manager.merge(pelada);
	}
	
	
	public Pelada findById(Long id) {
		return manager.find(Pelada.class, id);
	}
	
	
}