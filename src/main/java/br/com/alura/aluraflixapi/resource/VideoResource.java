package br.com.alura.aluraflixapi.resource;

import br.com.alura.aluraflixapi.exceptionhandler.AluraflixExceptionHandler;
import br.com.alura.aluraflixapi.model.Video;
import br.com.alura.aluraflixapi.repository.VideoRepository;
import br.com.alura.aluraflixapi.service.VideoService;
import br.com.alura.aluraflixapi.service.exception.CategoriaInexistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/videos")
public class VideoResource {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoService videoService;

    @Autowired
    private MessageSource messageSource;

    private Locale locale = new Locale("pt","BR");

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

    @GetMapping("/categoria/{id}")
    public ResponseEntity<List<Video>> buscarVideosPelaCategoria(@PathVariable Long id) throws ResponseStatusException {
        List<Video> videos = videoService.buscarVideosPelaCategoria(id);
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Video>> buscarVideosPeloTitulo(@PathVariable String titulo) throws ResponseStatusException {
        List<Video> videos = videoService.buscarVideosPeloTitulo(titulo);
        return ResponseEntity.ok(videos);
    }

    @PostMapping
    public ResponseEntity<Video> criar(@Valid @RequestBody Video video) {
        Video videoSalvo = videoService.salvar(video);
        return ResponseEntity.status(HttpStatus.CREATED).body(videoSalvo);
    }

    @ExceptionHandler({CategoriaInexistenteException.class})
    public ResponseEntity<Object> handleCategoriaInexistenteException(CategoriaInexistenteException ex, WebRequest request){
        String mensagemUsuario = messageSource.getMessage("categoria.inexistente", null, locale);
        String mensagemDesenvolvedor = ex.toString();
        List<AluraflixExceptionHandler.Erro> erros = Arrays.asList(new AluraflixExceptionHandler.Erro(mensagemUsuario, mensagemDesenvolvedor));

        return ResponseEntity.badRequest().body(erros);
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
