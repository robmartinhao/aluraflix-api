package br.com.alura.aluraflixapi.service;

import br.com.alura.aluraflixapi.model.Categoria;
import br.com.alura.aluraflixapi.model.Video;
import br.com.alura.aluraflixapi.repository.CategoriaRepository;
import br.com.alura.aluraflixapi.repository.VideoRepository;
import br.com.alura.aluraflixapi.service.exception.CategoriaInexistenteException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Video atualizar(Long id, Video video) {
        Video videoSalvo = buscarVideoPeloId(id);
        BeanUtils.copyProperties(video, videoSalvo, "id");
        return videoRepository.save(videoSalvo);
    }

    public Video buscarVideoPeloId(Long id) {
        Video videoEncontrado = videoRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return videoEncontrado;
    }

    public Video salvar(Video video) {

        Categoria livre = new Categoria();

        if (video.getCategoria() == null) {
            livre.setId(1L);
            livre.setTitulo("LIVRE");
            livre.setCor("#000000");
            video.setCategoria(livre);
        }

        categoriaRepository.findById(video.getCategoria().getId())
                .orElseThrow(() -> new CategoriaInexistenteException());

        return videoRepository.save(video);
    }
}
