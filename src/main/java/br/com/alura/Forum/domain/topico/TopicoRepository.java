package br.com.alura.Forum.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {


    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Topico t WHERE LOWER(TRIM(t.titulo)) = LOWER(TRIM(:titulo)) AND LOWER(TRIM(t.mensagem)) = LOWER(TRIM(:mensagem))")
    boolean existsByTituloAndMensagemIgnoreCaseTrim(@Param("titulo") String titulo, @Param("mensagem") String mensagem);


    Page<Topico> findAllByAtivoTrue(Pageable pagincao);

}
