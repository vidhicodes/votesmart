CREATE database vote_smart;

USE vote_smart;

CREATE TABLE voters(

     voter_id INT AUTO_INCREMENT NOT NULL,
     name VARCHAR(100) NOT NULL,
     email VARCHAR(100) NOT NULL,
     password VARCHAR(255) NOT NULL,
     age INT NOT NULL,
     CONSTRAINT voters_PK_voter_id PRIMARY KEY (voter_id),
     CONSTRAINT voters_UQ_email UNIQUE (email),
     CONSTRAINT voters_CK_age CHECK (age>=18),
     CONSTRAINT voters_CK_email CHECK (email LIKE '%@%')

);

CREATE TABLE CANDIDATES(

     candidate_id INT AUTO_INCREMENT NOT NULL,
     name VARCHAR(100) NOT NULL,
     party VARCHAR(100) NOT NULL,
     age INT NOT NULL,
     contested_position VARCHAR(100) NOT NULL,
     votes_count INT DEFAULT 0,
     CONSTRAINT candidates_PK_candidate_id PRIMARY KEY (candidate_id),
     CONSTRAINT candidates_CK_age CHECK (age>=25)

);

CREATE TABLE votes (

    voter_id INT NOT NULL,
    candidate_id INT NOT NULL,
    FOREIGN KEY (voter_id) REFERENCES voters(voter_id) ON DELETE CASCADE,
    FOREIGN KEY (candidate_id) REFERENCES candidates(candidate_id) ON DELETE CASCADE,
    CONSTRAINT votes_PK_voter_id_candidate_id PRIMARY KEY(voter_id,candidate_id)

);