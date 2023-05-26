package com.egg.biblioteca.services;

import com.egg.biblioteca.entidades.Autor;
import com.egg.biblioteca.entidades.Editorial;
import com.egg.biblioteca.entidades.Libro;
import com.egg.biblioteca.exceptions.MiException;
import com.egg.biblioteca.repositorys.AutorRepository;
import com.egg.biblioteca.repositorys.EditorialRepository;
import com.egg.biblioteca.repositorys.LibroRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {
    
    @Autowired
    private LibroRepository libroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private EditorialRepository editorialRepository;
    
    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Autor autor = autorRepository.findById(idAutor).get();
        
        Editorial editorial = editorialRepository.findById(idAutor).get();
        
        Libro libro = new Libro();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        
        libro.setAutor(autor);
        libro.setEditorial(editorial);
        
        libroRepository.save(libro);        
    }
    
    public List<Libro> listarLibros() {
        
        List<Libro> libros = new ArrayList<>();
        
        libros = libroRepository.findAll();
        
        return libros;
    }
    
    public void modificarLibro(Long isbn, String titulo, String idAutor, String idEditorial, Integer ejemplares) throws  MiException{
        
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        
        Optional<Libro> respuesta = libroRepository.findById(isbn);
        Optional<Autor> respuestaAutor = autorRepository.findById(idAutor);
        Optional<Editorial> respuestaEditorial = editorialRepository.findById(idEditorial);
        
        Autor autor = new Autor();
        Editorial editorial = new Editorial();
        
        if (respuestaAutor.isPresent()) {
            autor = respuestaAutor.get();
        }
        
        if (respuestaEditorial.isPresent()) {
            editorial = respuestaEditorial.get();
        }
        
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();
            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            
            libroRepository.save(libro);
        }
    }
    
    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiException{
        
        if (isbn == null) {
            throw new MiException("El isbn no puede ser nulo.");
        }
        
        if (titulo.isEmpty() || titulo == null) {
            throw new MiException("El titulo no puede ser nulo o estar vacio.");
        }
        
        if (ejemplares == null) {
            throw new MiException("Ejemplares no puede ser nulo.");
        }
        
        if (idAutor.isEmpty() || idAutor == null) {
            throw new MiException("El id Autor no puede ser nulo o estar vacio.");
        }
        
        if (idEditorial.isEmpty() || idEditorial == null) {
            throw new MiException("El id Editorial no puede ser nulo o estar vacio.");
        }        
    }
    
}