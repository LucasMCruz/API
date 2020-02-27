package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.domain.Comentario;
import com.example.demo.domain.Livro;
import com.example.demo.exceptions.LivroNao;
import com.example.demo.repository.ComentarioRe;
import com.example.demo.repository.LivroRe;

@Service
public class LivrosService {
	@Autowired
	private LivroRe livroRe;
	@Autowired
	private ComentarioRe comentarioRe;
	
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
		livro.setCodigo(null);
		return livroRe.save(livro);

	}
	public void deletar(Long codigo) {
		try {
			livroRe.deleteById(codigo);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNao("O livro nao pode ser encontrado.");
		}
	}
	public void atualizar(Livro livro) {
		verificarExistencia(livro);
		livroRe.save(livro);
	}
	private void verificarExistencia(Livro livro) {
		buscar(livro.getCodigo());
	}
	
	public Comentario salvarComentario(@PathVariable Long livroId, Comentario comentario) {
		Livro livro = buscar(livroId);
		
		comentario.setLivro(livro);
		comentario.setData(new Date());
		return comentarioRe.save(comentario);
	}
	
	public List<Comentario> listarComentarios(Long livroId){
		Livro livro = buscar(livroId);
		
		return livro.getComentarios();
	}



}
