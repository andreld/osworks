package com.andrepires.osworks.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.andrepires.osworks.domain.model.StatusOrdemServico;

public class OrdemServicoDTO {
	private Long id;

	ClienteResumoDTO cliente;

	private String descricao;

	private BigDecimal preco;

	private StatusOrdemServico status;

	private OffsetDateTime dataAbertura;

	private OffsetDateTime dataFinalizacao;

	public OrdemServicoDTO(Long id, Long idCliente, String nomeCliente, String descricao, BigDecimal preco, StatusOrdemServico status,
			OffsetDateTime dataAbertura, OffsetDateTime dataFinalizacao) {
		super();
		this.setId(id);
		this.cliente = new ClienteResumoDTO(idCliente, nomeCliente);
		this.descricao = descricao;
		this.preco = preco;
		this.status = status;
		this.dataAbertura = dataAbertura;
		this.dataFinalizacao = dataFinalizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteResumoDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteResumoDTO cliente) {
		this.cliente = cliente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public StatusOrdemServico getStatus() {
		return status;
	}

	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}

	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(OffsetDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}

	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}

}
