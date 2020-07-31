package br.gov.prodeb.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import br.gov.prodeb.model.Pelada;
import br.gov.prodeb.model.PeladaParticipantes;
import br.gov.prodeb.model.Usuario;
import br.gov.prodeb.service.PeladaService;
import br.gov.prodeb.service.UsuarioService;
import br.gov.prodeb.util.FacesMessages;

@Named
@javax.enterprise.context.ApplicationScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PeladaService peladaService;

	@Inject
	private UsuarioService usuarioService;

	@Inject
	private FacesMessages messages;

	private List<Usuario> listaUsuario;

	private List<Pelada> listaPelada;

	private String emailLogin;
	private String password;

	private String nome;
	private String apelido;
	private String email;
	private String senha;

//variaveis p tela de vinculaçao
	private String nomeVinc;
	private String apelidoVinc;
	private String emailVinc;
	private String senhaVinc;

	private Usuario usuarioLogado;

	private List<Pelada> selectedListaPeladas;

	public void initialize() {

		listaUsuario = usuarioService.finAll();

	}

	public void finAll() {
		listaUsuario = usuarioService.finAll();
	}

	public Usuario obterUsuarioLogado() {
		usuarioLogado = usuarioService.obterUsuario(this.email, this.password);

		return usuarioLogado;
	}

	public String logar() {
		usuarioLogado = usuarioService.obterUsuario(this.emailLogin, this.password);
		if (usuarioLogado != null) {
			return "menu?faces-redirect=true";
		}
		messages.info("Usuário ou senha Inválido!");
		return "";
	}

	public void cadastraUsuario() {

		try {
			usuarioService.salvar(this.montarObjeto());
			messages.info("Usuario salva com sucesso!");

		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	public void vicularUsuarioAEvento() {
		try {

			if (this.selectedListaPeladas.size() > 0) {
				for (Pelada evento : this.selectedListaPeladas) {
					PeladaParticipantes participantes = new PeladaParticipantes();
					participantes.setUsuario(usuarioLogado);
					participantes.setPelada(evento);
					usuarioService.salvar(participantes);
				}

				messages.info("Vinculação salva com sucesso!");
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

	private Usuario montarObjeto() {
		Usuario usu = new Usuario();
		usu.setNome(this.nome);
		usu.setApelido(this.apelido);
		usu.setEmail(this.email);
		usu.setSenha(this.senha);

		return usu;
	}

	public String redirect() {
		this.carregarDadosUsuLogado();

		return "vincularPelada?faces-redirect=true";
	}

	private void carregarDadosUsuLogado() {

		this.nomeVinc = this.usuarioLogado.getNome();
		this.apelidoVinc = this.usuarioLogado.getApelido();
		this.emailVinc = this.usuarioLogado.getEmail();
		this.senhaVinc = this.usuarioLogado.getSenha();

		this.carregarGridPeladas();
	}

	private void carregarGridPeladas() {
		this.listaPelada = peladaService.finAll();

	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailLogin() {
		return emailLogin;
	}

	public void setEmailLogin(String emailLogin) {
		this.emailLogin = emailLogin;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Pelada> getListaPelada() {
		return listaPelada;
	}

	public void setListaPelada(List<Pelada> listaPelada) {
		this.listaPelada = listaPelada;
	}

	public List<Pelada> getSelectedListaPeladas() {
		return selectedListaPeladas;
	}

	public void setSelectedListaPeladas(List<Pelada> selectedListaPeladas) {
		this.selectedListaPeladas = selectedListaPeladas;
	}

	public String getNomeVinc() {
		return nomeVinc;
	}

	public void setNomeVinc(String nomeVinc) {
		this.nomeVinc = nomeVinc;
	}

	public String getApelidoVinc() {
		return apelidoVinc;
	}

	public void setApelidoVinc(String apelidoVinc) {
		this.apelidoVinc = apelidoVinc;
	}

	public String getEmailVinc() {
		return emailVinc;
	}

	public void setEmailVinc(String emailVinc) {
		this.emailVinc = emailVinc;
	}

	public String getSenhaVinc() {
		return senhaVinc;
	}

	public void setSenhaVinc(String senhaVinc) {
		this.senhaVinc = senhaVinc;
	}

}