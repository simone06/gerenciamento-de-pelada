package br.gov.prodeb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.gov.prodeb.model.PeladaParticipantes;
import br.gov.prodeb.model.Usuario;
import br.gov.prodeb.repository.UsuarioRepository;
import br.gov.prodeb.util.Transacional;

public class UsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioRepository usuarioRepository;

    @Transacional
    public void salvar(Usuario usuario) {
    	usuarioRepository.salvar(usuario);
    }

    
    public Usuario obterUsuario(String email,String senha) {
    	return usuarioRepository.obterUsuario(email, senha);
    }
    
    @Transacional
    public void salvar(PeladaParticipantes peladaParticipantes ) {
    	usuarioRepository.salvar(peladaParticipantes);
    }
    
    public List<Usuario> finAll() {
    	return usuarioRepository.finAll();
    }
    

}