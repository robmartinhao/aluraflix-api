package br.com.alura.aluraflixapi.repository;

import br.com.alura.aluraflixapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
