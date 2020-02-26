package com.example.demo.service;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Livro;
import com.example.demo.exceptions.LivroNao;
import com.example.demo.repository.LivroRe;

@Service
public class LivrosService {
	@Autowired
	private LivroRe livroRe;
	
	public List<Livro> listar(){
		return livroRe.findAll();
	}
	
	public Livro buscar(Long codigo){
		Livro livro = livroRe.findById(codigo).get();
		
		if(livro == null) {
			throw new LivroNao("O livro nao foi encontrado.");
		}
		return livro;
	}
	public Livro salvar(Livro livro) {
		livro.setCodigo(codigo);

	}
}
