CREATE TABLE video (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(30) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    url VARCHAR(50) NOT NULL,
    id_categoria BIGINT(20) NOT NULL DEFAULT 1,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO video (titulo, descricao, url, id_categoria) VALUES ('Java', 'Maven', 'http://www.java.com.br/maven', 1);
INSERT INTO video (titulo, descricao, url, id_categoria) VALUES ('Scala', 'scala', 'http://www.scala.com.br/scala', 2);
INSERT INTO video (titulo, descricao, url, id_categoria) VALUES ('Elixir', 'elixir', 'http://www.elixir.com.br/elixir', 3);
INSERT INTO video (titulo, descricao, url, id_categoria) VALUES ('Javascript', 'javascript', 'http://www.javascript.com.br/script', 4);
