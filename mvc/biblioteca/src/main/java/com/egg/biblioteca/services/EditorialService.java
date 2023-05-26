package com.egg.biblioteca.services;

import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.repositorys.EditorialRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialService {
    
    @Autowired
    EditorialRepository editorialRepository;
    
    @Transactional
    public void crearEditorial(String nombre) {
        
        Editorial editorial = new Editorial();
        
        editorial.setNombre(nombre);
        
        editorialRepository.save(editorial);
    }
    
    public List<Editorial> listarEditoriales() {
        
        List <Editorial> editoriales = new ArrayList();
        
        editoriales = editorialRepository.findAll();
        
        return editoriales;
    }
    
    public void modificarEditorial(String id, String nombre) {
        
        Optional<Editorial> respuesta = editorialRepository.findById(id);
        
        if(respuesta.isPresent()) {
            Editorial editorial = respuesta.get();
            editorial.setNombre(nombre);
            
            editorialRepository.save(editorial);
        }
    }
    
}