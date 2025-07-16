package br.com.alura.Forum.seguranca;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException; // Importar a classe
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class TratadorDeErros {



    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    /**
     * NOVO MÉTODO:
     * Trata a exceção DataIntegrityViolationException, que ocorre quando uma restrição
     * do banco de dados como uma chave única é violada.
     * Retorna o status HTTP 409 Conflict.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity tratarErro409(DataIntegrityViolationException ex) {
        var corpoDaResposta = Map.of("erro", "Recurso duplicado. Já existe um tópico com o mesmo título e/ou mensagem.");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(corpoDaResposta);
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}