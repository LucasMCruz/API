package com.example.demo.handler;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.domain.DetalheErro;
import com.example.demo.exceptions.AutorNao;
import com.example.demo.exceptions.LivroNao;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(LivroNao.class)
	public ResponseEntity<DetalheErro> handleiLivroNao(LivroNao e, HttpServletRequest request){
		
		DetalheErro erro = new DetalheErro();
		erro.setStatus(404l);
		erro.setTitulo("O livro nao pode ser encontrado");
		erro.setMensagemDesenvolvedor("ERROORR");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	@ExceptionHandler(AutorNao.class)
	public ResponseEntity<DetalheErro> handleiAutoroNao(AutorNao e, HttpServletRequest request){
		
		DetalheErro erro = new DetalheErro();
		erro.setStatus(409l);
		erro.setTitulo("O Autor ja existente");
		erro.setMensagemDesenvolvedor("Error 409");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<DetalheErro> handleiConstraintViolationException(ConstraintViolationException e, HttpServletRequest request){
		
		DetalheErro erro = new DetalheErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição invalida");
		erro.setMensagemDesenvolvedor("Error 409");
		erro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	
}
