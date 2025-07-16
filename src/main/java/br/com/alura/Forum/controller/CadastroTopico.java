package br.com.alura.Forum.controller;

import br.com.alura.Forum.domain.topico.DadosCadastroNovoTopico;
import br.com.alura.Forum.domain.topico.Topico;
import br.com.alura.Forum.domain.topico.TopicoRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cadastroTopico")
@SecurityRequirement(name = "bearer-key")
public class CadastroTopico {

    private final TopicoRepository repository;

    public CadastroTopico(TopicoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroNovoTopico dados) {
        repository.save(new Topico(dados));
        System.out.println(dados);
    }
}