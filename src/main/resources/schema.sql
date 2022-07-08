CREATE SEQUENCE sprint_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE user_story_id_seq START WITH 1 INCREMENT BY 1;
CREATE TYPE sprint_status AS ENUM ('Pending', 'In progress', 'Finished', 'Canceled');
CREATE TABLE sprint (
    id INTEGER NOT NULL AUTO_INCREMENT,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    description TEXT,
    sprint_status sprint_status,
    PRIMARY KEY(id)
);
CREATE TYPE user_story_status AS ENUM ('To do', 'In progress', 'Review', 'Done');
CREATE TABLE user_story (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    attachments BYTEA,
    number_of_story_points INTEGER,
    user_story_status user_story_status,
    PRIMARY KEY (id)
);
CREATE TABLE sprint_user_story (
    sprint_id INTEGER NOT NULL,
    user_story_id INTEGER NOT NULL,
    PRIMARY KEY (sprint_id, user_story_id),
    CONSTRAINT fk_sprint FOREIGN KEY (sprint_id) REFERENCES sprint(id),
    CONSTRAINT fk_user_story FOREIGN KEY (user_story_id) REFERENCES user_story(id)
);