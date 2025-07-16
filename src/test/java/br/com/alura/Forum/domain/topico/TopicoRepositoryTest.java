package br.com.alura.Forum.domain.topico;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("_test")
class TopicoRepositoryTest {

    @Test
    void existsByTituloAndMensagemIgnoreCaseTrim() {
    }

    @Test
    void findAllByAtivoTrue() {
    }
}