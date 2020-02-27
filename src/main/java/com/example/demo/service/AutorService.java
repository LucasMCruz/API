package com.example.demo.service;

import java.util.List;

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
		if(autor.getId() !=null) {
			Autor a = autorRe.findById(autor.getId()).get();
			
			if(a != null) {
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
