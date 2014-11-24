# --- Database schema

# --- !Ups

INSERT INTO ARTICLE(ID, TITLE, LINK, DESCRIPTION, DOMAIN, PUBLISHED) VALUES (1, 'First', 'http://some.com/1', 'I am number one', 'some.com', '2001-01-01 01:01:01');
INSERT INTO ARTICLE(ID, TITLE, LINK, DESCRIPTION, DOMAIN, PUBLISHED) VALUES (2, 'Second', 'http://some.com/2', 'I am number two', 'some.com', '2002-02-02 02:02:02');

# --- !Downs

DELETE FROM ARTICLE;


