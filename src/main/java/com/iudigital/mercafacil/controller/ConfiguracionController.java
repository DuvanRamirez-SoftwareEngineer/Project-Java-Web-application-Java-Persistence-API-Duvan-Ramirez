package com.iudigital.mercafacil.controller;

import com.iudigital.mercafacil.model.Almacen;
import com.iudigital.mercafacil.model.Grupo;
import com.iudigital.mercafacil.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/configuracion")
public class ConfiguracionController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private AlmacenRepository almacenRepository;

    @GetMapping
    public String configuracion(Model model) {
        List<Grupo> grupos = grupoRepository.findAll();
        List<Almacen> almacenes = almacenRepository.findAll();
        System.out.println(grupos.size());
        model.addAttribute("grupos", grupos);
        model.addAttribute("almacenes", almacenes);
        return "/lista";
    }

    @PostMapping("/grupo")
    public String guardarGrupo(Grupo grupo) {
        grupoRepository.save(grupo);
        return "redirect:/configuracion";
    }

    @PostMapping("/almacen")
    public String guardarAlmacen(Almacen almacen) {
        almacenRepository.save(almacen);
        return "redirect:/configuracion";
    }
}
