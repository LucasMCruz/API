package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Livro;

public interface LivroRe extends JpaRepository<Livro, Long> {

}
