-- 1. Criar uma tabela temporária com estrutura correta
CREATE TABLE topico_v2 (
  id BIGINT NOT NULL AUTO_INCREMENT,
  autor VARCHAR(100) NOT NULL,
  titulo VARCHAR(100) NOT NULL,
  mensagem VARCHAR(300) NOT NULL,
  data_criacao VARCHAR(50) NOT NULL,
  curso VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

-- 2. Copiar e ajustar os dados da antiga tabela para a nova
INSERT INTO topico_v2 (id, autor, titulo, mensagem, data_criacao, curso)
SELECT
  id,
  LEFT(COALESCE(autor, ''), 100),
  LEFT(COALESCE(titulo, ''), 100),
  LEFT(COALESCE(mensagem, ''), 300),
  DATE_FORMAT(data_criacao, '%Y-%m-%d %H:%i:%s'),
  LEFT(COALESCE(curso, ''), 100)
FROM topico;

-- 3. Apagar a tabela antiga
DROP TABLE topico;

-- 4. Renomear a tabela nova para `topico`
RENAME TABLE topico_v2 TO topico;
