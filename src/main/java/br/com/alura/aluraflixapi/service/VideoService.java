package br.com.alura.aluraflixapi.service;

import br.com.alura.aluraflixapi.model.Video;
import br.com.alura.aluraflixapi.repository.VideoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

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
}
