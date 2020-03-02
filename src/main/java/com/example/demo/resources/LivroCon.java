package com.example.demo.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(tags = "Livro")
@RestController
@RequestMapping("/livros")
public class LivroCon {
	
	
	@Autowired
	private LivrosService livroSe;
	
	@Autowired
	private ComentarioRe comentarioRe;
	
	@ApiOperation("Lista os livros")
	@GetMapping()
	public ResponseEntity<List<Livro>>listar() {
		System.out.println("Os livros");
		
		
		return ResponseEntity.status(HttpStatus.OK).body(livroSe.listar());
	}
	
	@ApiOperation("Salva os livros")
	@PostMapping()
	public ResponseEntity<Void> salvar(@ApiParam(name="corpo", value = "Nome de um novo livro")@RequestBody Livro livro) {
		System.out.println("Salvei");
		livroSe.salvar(livro);
		
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{codigo}").buildAndExpand(livro.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation("Lista o livro do Id")
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscar(@ApiParam(value = "Id de um livro", example = "1")@PathVariable("codigo") Long codigo) {
		Livro livro = livroSe.buscar(codigo);

		CacheControl cachControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cachControl).body(livro);
	}	
	
	@ApiOperation("DEleta os livros")
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
	
	@ApiOperation("Altera os livros")
	@PutMapping("/{codigo}")
	public ResponseEntity<Void> altualizar(@RequestBody Livro livro, @PathVariable("codigo") Long codigo) {
		livro.setCodigo(codigo);
		
		livroSe.atualizar(livro);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PostMapping("/{codigo}/comentarios")
	public ResponseEntity<Void> adicionarComentario(@PathVariable ("codigo") Long livroId, @RequestBody Comentario  comentario) {
		
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		//comentario.setUsuario(auth.getName());
		
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