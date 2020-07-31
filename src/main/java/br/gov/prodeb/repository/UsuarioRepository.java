package br.gov.prodeb.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.gov.prodeb.model.PeladaParticipantes;
import br.gov.prodeb.model.Usuario;

public class UsuarioRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;


	public Usuario obterUsuario(String email,String senha) {
		TypedQuery<Usuario> query = manager.createQuery("from Usuario where email = :email and senha = :senha ",Usuario.class);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		
		try{
            return query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
            return null;
        }
	}
	
	public List<Usuario> finAll() {
         return manager.createQuery("from Usuario", Usuario.class).getResultList();
    }

	public Usuario salvar(Usuario usuario) {
		return manager.merge(usuario);
	}

	public void salvar(PeladaParticipantes peladaParticipantes) {
		manager.merge(peladaParticipantes);
	}
	

}