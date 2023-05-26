package com.egg.biblioteca.services;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.repositorys.AutorRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorService {
    
    @Autowired
    AutorRepository autorRepository;
    
    @Transactional
    public void crearAutor(String nombre) {
        
      Autor autor = new Autor();
      
      autor.setNombre(nombre);
      
      autorRepository.save(autor);        
    }
    
    public List<Autor> listarAutores() {
        
        List <Autor> autores = new ArrayList();
        
        autores = autorRepository.findAll();
        
        return autores;
    }
    
    public void modificarAutor(String nombre, String id) {
        
        Optional<Autor> respuesta = autorRepository.findById(id);
        
        if(respuesta.isPresent()) {
            Autor autor = respuesta.get();
            autor.setNombre(nombre);
            
            autorRepository.save(autor);
        }
    }
}