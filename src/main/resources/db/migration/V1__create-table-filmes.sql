CREATE TABLE filmes (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        ano INT NOT NULL,
                        diretor VARCHAR(255),
                        soma_das_avaliacoes DOUBLE,
                        total_de_avaliacoes INT,
                        duracao_em_minutos INT
);
