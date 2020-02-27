package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Autor;
import com.example.demo.service.AutorService;

@RestController
@RequestMapping("/autores")
public class AutorCon {
	
	
	@Autowired
	private AutorService autorSe;

	
	@GetMapping
	public ResponseEntity<List<Autor>> listar(){
		List<Autor> autores = autorSe.listar();
		
		return ResponseEntity.status(HttpStatus.OK).body(autores);
	}
	
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Autor autor) {
		System.out.println("Salvei");
		autor = autorSe.salvar(autor);
		
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(autor.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Autor> bucas(@PathVariable("id")Long id){
		Autor autor = autorSe.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(autor);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
