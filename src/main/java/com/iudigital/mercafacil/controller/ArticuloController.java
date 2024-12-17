package com.iudigital.mercafacil.controller;

import com.iudigital.mercafacil.model.Almacen;
import com.iudigital.mercafacil.model.Articulo;
import com.iudigital.mercafacil.model.Grupo;
import com.iudigital.mercafacil.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private AlmacenRepository almacenRepository;

    @GetMapping
    public String listarArticulos(Model model) {
        List<Articulo> articulos = articuloRepository.findAll();
        model.addAttribute("articulos", articulos);
        return "/lista";
    }

    @GetMapping("/nuevo")
    public String nuevoArticulo(Model model) {
        model.addAttribute("articulo", new Articulo());
        model.addAttribute("grupos", grupoRepository.findAll());
        model.addAttribute("almacenes", almacenRepository.findAll());
        return "/formulario";
    }

    @PostMapping
   public String guardarArticulo(
        @RequestParam(required = false) Long codigoArticulo,
        @RequestParam String nombre,
        @RequestParam String unidadMedida,
        @RequestParam int existencia,
        @RequestParam int cantidadMaxima,
        @RequestParam int cantidadMinima,
        @RequestParam Long grupo,
        @RequestParam Long almacen
) {
    // Buscar el Grupo y el Almacén por sus IDs
    Grupo encontrarGrupo = grupoRepository.findById(grupo)
            .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));
    Almacen encontrarAlmacen = almacenRepository.findById(almacen)
            .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
    System.out.println(codigoArticulo);
    // Crear o actualizar el Artículo
    Articulo articulo;
    if (codigoArticulo != null) {
        articulo = articuloRepository.findById(codigoArticulo)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));
    } else {
        articulo = new Articulo();
    }
    articulo.setNombre(nombre);
    articulo.setUnidadMedida(unidadMedida);
    articulo.setExistencia(existencia);
    articulo.setCantidadMaxima(cantidadMaxima);
    articulo.setCantidadMinima(cantidadMinima);
    articulo.setGrupo(encontrarGrupo);
    articulo.setAlmacen(encontrarAlmacen);

    articuloRepository.save(articulo);
    return "redirect:/articulos";
}

    @GetMapping("/editar/{id}")
    public String editarArticulo(@PathVariable Long id, Model model) {
        System.out.println(id);
        Articulo articulo = articuloRepository.findById(id).orElse(null);
        assert articulo != null;
        System.out.println(articulo.getNombre());
        model.addAttribute("articulo", articuloRepository.findById(id).orElse(null));
        model.addAttribute("grupos", grupoRepository.findAll());
        model.addAttribute("almacenes", almacenRepository.findAll());
        return "/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarArticulo(@PathVariable Long id) {
        articuloRepository.deleteById(id);
        return "redirect:/articulos";
    }
}