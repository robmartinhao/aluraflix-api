package br.com.alura.aluraflixapi.resource;

import br.com.alura.aluraflixapi.model.Video;
import br.com.alura.aluraflixapi.repository.VideoRepository;
import br.com.alura.aluraflixapi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoResource {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoService videoService;

    @GetMapping
    public List<Video> listar() {
        return videoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> buscarPeloId(@PathVariable Long id) throws ResponseStatusException {
        return videoRepository.findById(id)
                .map(video -> ResponseEntity.ok(video))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "recurso.nao-encontrado"));
    }

    @PostMapping
    public ResponseEntity<Video> criar(@Valid @RequestBody Video video) {
        Video videoSalvo = videoRepository.save(video);
        return ResponseEntity.status(HttpStatus.CREATED).body(videoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Video> atualizar(@PathVariable Long id, @Valid @RequestBody Video video) {
            Video videoSalvo = videoService.atualizar(id, video);
            return ResponseEntity.ok(videoSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        videoRepository.deleteById(id);
    }
}
