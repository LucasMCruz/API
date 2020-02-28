package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Autor;
import com.example.demo.exceptions.AutorNao;
import com.example.demo.repository.AutorRe;

@Service
public class AutorService {
	
	
	@Autowired
	private AutorRe autorRe;
	
	public List<Autor> listar(){
		
		return autorRe.findAll();
	}
	
	public Autor salvar(Autor autor) {
		System.out.println("he"+autor.getId());
		if(autor.getId() !=null) {
			Optional<Autor> a = autorRe.findById(autor.getId());
		
		if(a.isPresent()) {
			throw new AutorNao("Autor ja existe");
			}
		}
		
		return autorRe.save(autor);

	}
	
	public Autor buscar(Long id) {
		Autor autor = autorRe.findById(id).get();
		
		if(autor == null) {
			throw new AutorNao("Autor nao encontrado");
		}
		return autor;
	}
}
