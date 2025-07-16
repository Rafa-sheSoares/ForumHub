package br.com.alura.Forum.domain.topico;

import br.com.alura.Forum.domain.curso.NomeCurso;
import jakarta.validation.constraints.NotNull;

public record DadosListarTopicoAtualizacaoTopico(
        @NotNull
        Long id,
        String titulo,
        String mensagem,
        NomeCurso curso
) {
}
