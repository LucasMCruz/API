package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.domain.Livro;
import com.example.demo.exceptions.LivroNao;
import com.example.demo.repository.LivroRe;
import com.example.demo.service.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivroCon {
	
	
	@Autowired
	private LivrosService livroSe;
	
	@GetMapping()
	public ResponseEntity<List<Livro>>listar() {
		System.out.println("Os livros");
		
		
		return ResponseEntity.status(HttpStatus.OK).body(livroSe.listar());
	}
	
	@PostMapping()
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		System.out.println("OLA API");
		livroRe.save(livro);
		
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{codigo}").buildAndExpand(livro.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@PathVariable("codigo") Long codigo) {
		Livro livro = null;
		try {
			livro = livroSe.buscar(codigo);
		} catch (Exception e) {
			System.out.println("Livro nao encontrado");
			return ResponseEntity.notFound().build();
		}
		 
		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}	
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable("codigo") Long codigo) {
		livroRe.deleteById(codigo);
		/*try {
			livroRe.deleteById(codigo);
		} catch (EmptyResultaDataAccessException e) {
			return ResponseEntity.notFound().build();
		}*/
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{codigo}")
	public void alterar(@RequestBody Livro livro, @PathVariable("codigo") Long codigo) {
		livro.setCodigo(codigo);
		livroRe.save(livro);
	}
}