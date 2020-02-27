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

import com.example.demo.domain.Comentario;
import com.example.demo.domain.Livro;
import com.example.demo.repository.ComentarioRe;
import com.example.demo.service.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivroCon {
	
	
	@Autowired
	private LivrosService livroSe;
	
	@Autowired
	private ComentarioRe comentarioRe;
	
	@GetMapping()
	public ResponseEntity<List<Livro>>listar() {
		System.out.println("Os livros");
		
		
		return ResponseEntity.status(HttpStatus.OK).body(livroSe.listar());
	}
	
	@PostMapping()
	public ResponseEntity<Void> salvar(@RequestBody Livro livro) {
		System.out.println("Salvei");
		livroSe.salvar(livro);
		
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{codigo}").buildAndExpand(livro.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@PathVariable("codigo") Long codigo) {
		Livro livro = livroSe.buscar(codigo);

		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}	
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deletar(@PathVariable("codigo") Long codigo) {
		livroSe.deletar(codigo);
		/*try {
			livroRe.deleteById(codigo);
		} catch (EmptyResultaDataAccessException e) {
			return ResponseEntity.notFound().build();
		}*/
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Void> altualizar(@RequestBody Livro livro, @PathVariable("codigo") Long codigo) {
		livro.setCodigo(codigo);
		
		livroSe.atualizar(livro);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/{codigo}/comentarios")
	public ResponseEntity<Void> adicionarComentario(@PathVariable ("codigo") Long livroId, @RequestBody Comentario  comentario) {
		
		livroSe.salvarComentario(livroId, comentario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
		
		
		
	}
	
	@GetMapping("/{codigo}/comentarios")
	public ResponseEntity<List<Comentario>> listarComentario(@PathVariable("codigo") Long livroId){
		
		List<Comentario> comentarios = livroSe.listarComentarios(livroId);
		
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}