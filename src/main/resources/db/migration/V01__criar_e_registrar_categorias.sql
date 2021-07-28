CREATE TABLE categoria (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(30) NOT NULL,
    cor VARCHAR(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (titulo, cor) VALUES ('LIVRE', '#000000');
INSERT INTO categoria (titulo, cor) VALUES ('Back-end', '#FF0000');
INSERT INTO categoria (titulo, cor) VALUES ('Front-end', '#0000FF');
INSERT INTO categoria (titulo, cor) VALUES ('Devops', '#008000');
INSERT INTO categoria (titulo, cor) VALUES ('Data Science', '#FFA500');
