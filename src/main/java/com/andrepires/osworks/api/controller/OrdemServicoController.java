package com.andrepires.osworks.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.andrepires.osworks.api.model.OrdemServicoDTO;
import com.andrepires.osworks.api.model.OrdemServicoInput;
import com.andrepires.osworks.domain.model.Cliente;
import com.andrepires.osworks.domain.model.OrdemServico;
import com.andrepires.osworks.domain.repository.OrdemServicoRepository;
import com.andrepires.osworks.domain.service.GestaoOrdemServicoService;

@RequestMapping("/ordens-servico")
@RestController
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoDTO criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {
		
		return toDto(gestaoOrdemServico.criar(toEntity(ordemServicoInput)));

	}

	@GetMapping
	public List<OrdemServicoDTO> listar() {

		return toDtoCollection(ordemServicoRepository.findAll());

	}

	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoDTO> buscar(@PathVariable Long ordemServicoId) {
		Optional<OrdemServico> os = ordemServicoRepository.findById(ordemServicoId);

		if (os.isPresent()) {
			OrdemServicoDTO ordemServicoModel = toDto(os.get());
			return ResponseEntity.ok(ordemServicoModel);
		}

		return ResponseEntity.notFound().build();
		
	}
	
	private OrdemServicoDTO toDto(OrdemServico os) {
		return new OrdemServicoDTO(os.getId(), os.getCliente().getId(), os.getCliente().getNome(),
				os.getDescricao(), os.getPreco(), os.getStatus(), os.getDataAbertura(),
				os.getDataFinalizacao());
	}
	
	private List<OrdemServicoDTO> toDtoCollection(List<OrdemServico> listaOs){
		return listaOs.stream().map(os -> toDto(os)).collect(Collectors.toList());
	}

	private OrdemServico toEntity(OrdemServicoInput os) {
		Cliente cliente = new Cliente();
		cliente.setId(os.getCliente().getId());
		
		return new OrdemServico(cliente, os.getDescricao(), os.getValor());

	}
}
