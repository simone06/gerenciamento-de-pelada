package br.gov.prodeb.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.prodeb.model.Pelada;
import br.gov.prodeb.model.PeladaParticipantes;
import br.gov.prodeb.model.Usuario;
import br.gov.prodeb.service.PeladaService;
import br.gov.prodeb.service.UsuarioService;
import br.gov.prodeb.util.FacesMessages;

@Named
@ViewScoped
public class PeladaParticipantesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6495289608936827742L;
	private List<Pelada> listaPeladaCombo;
	private Pelada peladaSelecionada;
	private List<Usuario> selectedListaUsuarios;
	private List<Usuario> listaUsuario;

	@Inject
	private PeladaService peladaService;

	@Inject
	private FacesMessages messages;

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	@Inject
	private UsuarioService usuarioService;

	@PostConstruct
	public void initialize() {
		this.listaUsuario = usuarioService.finAll();
		this.listaPeladaCombo = peladaService.finAll();
	}

	public void convidarUsuarios() {
		try {

			if (this.selectedListaUsuarios.size() > 0) {
				for (Usuario usuario : this.selectedListaUsuarios) {
					PeladaParticipantes participantes = new PeladaParticipantes();
					participantes.setUsuario(usuario);
					participantes.setPelada(this.peladaSelecionada);
					usuarioService.salvar(participantes);
				}

				messages.info("Convidaddos associados com sucesso!");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Pelada> getListaPeladaCombo() {
		return listaPeladaCombo;
	}

	public void setListaPeladaCombo(List<Pelada> listaPeladaCombo) {
		this.listaPeladaCombo = listaPeladaCombo;
	}

	public Pelada getPeladaSelecionada() {
		return peladaSelecionada;
	}

	public void setPeladaSelecionada(Pelada peladaSelecionada) {
		this.peladaSelecionada = peladaSelecionada;
	}

	public List<Usuario> getSelectedListaUsuarios() {
		return selectedListaUsuarios;
	}

	public void setSelectedListaUsuarios(List<Usuario> selectedListaUsuarios) {
		this.selectedListaUsuarios = selectedListaUsuarios;
	}

}
