package com.andrepires.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andrepires.osworks.domain.exception.NegocioException;
import com.andrepires.osworks.domain.model.Cliente;
import com.andrepires.osworks.domain.model.Comentario;
import com.andrepires.osworks.domain.model.OrdemServico;
import com.andrepires.osworks.domain.model.StatusOrdemServico;
import com.andrepires.osworks.domain.repository.ClienteRepository;
import com.andrepires.osworks.domain.repository.ComentarioRepository;
import com.andrepires.osworks.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	OrdemServicoRepository ordemServicoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ComentarioRepository comentarioRepository;

	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado."));

		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());

		return ordemServicoRepository.save(ordemServico);

	}
 
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {

		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new NegocioException("Ordem de serviço não encontrada."));
		
		Comentario comentario = new Comentario(ordemServico, descricao);
		
		return comentarioRepository.save(comentario);
		
	}
}