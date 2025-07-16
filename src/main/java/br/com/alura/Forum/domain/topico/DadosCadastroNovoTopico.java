package br.com.alura.Forum.domain.topico;

import br.com.alura.Forum.domain.curso.NomeCurso;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroNovoTopico(
        @NotBlank
        String autor,
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataCriacao,
        @NotNull
        NomeCurso curso
        ) {
}
