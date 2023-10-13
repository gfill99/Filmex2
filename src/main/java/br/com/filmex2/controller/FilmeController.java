package br.com.filmex2.controller;

import br.com.filmex2.domain.filme.DadosCadastroFilmes;
import br.com.filmex2.domain.filme.DadosEditaFilmes;
import br.com.filmex2.domain.filme.Filme;
import br.com.filmex2.domain.filme.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeRepository filmeRepository;

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model){
        if(id != null){
            Filme filme = filmeRepository.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
    }

    @GetMapping
    public String carregaPaginaListagem(Model model){
        model.addAttribute("lista", filmeRepository.findAll());
        return "filmes/listagem";
    }

    @PostMapping
    @Transactional
    public String cadastraFilme(DadosCadastroFilmes dados) {
        Filme filme = new Filme(dados);
        filmeRepository.save(filme);
        return "redirect:/filmes";
    }

    @PutMapping
    @Transactional
    public String editaFilme(DadosEditaFilmes dados) {
        Filme filme = filmeRepository.getReferenceById(dados.id());
        filme.atualizaDados(dados);
        return "redirect:/filmes";
    }

    @DeleteMapping
    @Transactional
    public String excluiFilme(Long id) {
        filmeRepository.deleteById(id);
        return "redirect:/filmes";
    }
}
