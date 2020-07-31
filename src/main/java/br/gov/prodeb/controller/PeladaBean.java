package br.gov.prodeb.controller;

import java.io.Serializable;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.prodeb.model.Pelada;
import br.gov.prodeb.repository.PeladaRepository;
import br.gov.prodeb.service.PeladaService;
import br.gov.prodeb.util.FacesMessages;

@Named
@ViewScoped
public class PeladaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PeladaRepository usuarioRepository;

	@Inject
	private PeladaService peladaService;

	@Inject
	private FacesMessages messages;

	private List<Pelada> listaPelada;

	private String nome;
	private Date data;
	private String hora;
	private String local;
	

	public void finAll() {
		listaPelada = usuarioRepository.finAll();
	}

	public Time convertToTime(String hora) {
		try {

			SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
			Date data = formatador.parse(hora);
			Time time = new Time(data.getTime());

			return time;

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void cadastrarPelada() {
		peladaService.salvar(this.montarObjeto());
		messages.info("Pelada salva com sucesso!");

	}

	private Pelada montarObjeto() {
		Pelada pelada = new Pelada();
		pelada.setNomeEvento(this.nome);
		pelada.setData(data);
		pelada.setHora(this.convertToTime(hora));
		pelada.setLocal(local);

		return pelada;
	}

	public FacesMessages getMessages() {
		return messages;
	}

	public void setMessages(FacesMessages messages) {
		this.messages = messages;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Pelada> getListaPelada() {
		return listaPelada;
	}

	public void setListaPelada(List<Pelada> listaPelada) {
		this.listaPelada = listaPelada;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

}