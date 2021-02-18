package com.andrepires.osworks.api.execeptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.andrepires.osworks.domain.exception.NegocioException;

//componente do spring onde são tratadas as exceções dos controladores
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	//anotação para bind caso uma exceção seja lançada por algum controlador chamar essa método
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setTituloString(ex.getMessage());
		problema.setDataHora(OffsetDateTime.now());
		return super.handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Problema.Campo> campos = new ArrayList<Problema.Campo>();
		
		//pesquisar diferença entre a ex.getAllErrors() e ex.getBindingResult().getAllErrors()
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			campos.add(new Problema.Campo(((FieldError) error).getField(), error.getDefaultMessage()));
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(OffsetDateTime.now());
		problema.setTituloString("Um ou mais campos estão inválidos. "
				+ "Faça o preenchimento correto e tente novamente.");
		problema.setCampos(campos);
		
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}
}
