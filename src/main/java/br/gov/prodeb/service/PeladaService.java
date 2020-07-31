package br.gov.prodeb.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.gov.prodeb.model.Pelada;
import br.gov.prodeb.repository.PeladaRepository;
import br.gov.prodeb.util.Transacional;

public class PeladaService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private PeladaRepository peladaRepository;

	@Transacional
	public void salvar(Pelada pelada) {
		peladaRepository.salvar(pelada);
	}

	public List<Pelada> finAll() {
		return peladaRepository.finAll();
	}

}