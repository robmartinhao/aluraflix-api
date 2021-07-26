CREATE TABLE video (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(30) NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    url VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO video (titulo, descricao, url) VALUES ('Java', 'Maven', 'http://www.java.com.br/maven');
INSERT INTO video (titulo, descricao, url) VALUES ('Scala', 'scala', 'http://www.scala.com.br/scala');
INSERT INTO video (titulo, descricao, url) VALUES ('Elixir', 'elixir', 'http://www.elixir.com.br/elixir');
INSERT INTO video (titulo, descricao, url) VALUES ('Javascript', 'javascript', 'http://www.javascript.com.br/script');
