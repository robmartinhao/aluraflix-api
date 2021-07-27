package br.com.alura.aluraflixapi.service;

import br.com.alura.aluraflixapi.model.Categoria;
import br.com.alura.aluraflixapi.repository.CategoriaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria atualizar(Long id, Categoria categoria) {
        Categoria categoriaSalva = buscarCategoriaPeloId(id);
        BeanUtils.copyProperties(categoria, categoriaSalva, "id");
        return categoriaRepository.save(categoriaSalva);
    }

    public Categoria buscarCategoriaPeloId(Long id) {
        Categoria categoriaEncontrada = categoriaRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        return categoriaEncontrada;
    }
}
