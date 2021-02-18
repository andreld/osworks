package com.andrepires.osworks.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.andrepires.osworks.api.model.ComentarioDTO;
import com.andrepires.osworks.api.model.ComentarioInput;
import com.andrepires.osworks.domain.model.Comentario;
import com.andrepires.osworks.domain.service.GestaoOrdemServicoService;

@RequestMapping("/ordens-servico/{ordemServicoId}/comentario")
@RestController
public class ComentarioController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServicoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioDTO criar(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput) {

		return toDto(gestaoOrdemServicoService.adicionarComentario(ordemServicoId, comentarioInput.getDescricao()));

	}

	private ComentarioDTO toDto(Comentario comentario) {

		return new ComentarioDTO(comentario.getId(), comentario.getDescricao(), comentario.getDataEnvio());

	}
	
}
