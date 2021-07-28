package br.com.alura.aluraflixapi.repository;

import br.com.alura.aluraflixapi.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("SELECT v FROM Video v WHERE v.categoria.id = :categoria")
    List<Video> buscarVideosPelaCategoria(Long categoria);

    @Query("SELECT v FROM Video v WHERE v.titulo LIKE %:titulo%")
    List<Video> buscarVideosPeloTitulo(@Param("titulo") String titulo);
}
