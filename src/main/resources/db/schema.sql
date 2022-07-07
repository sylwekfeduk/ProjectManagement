CREATE TYPE sprintStatus AS ENUM ('Pending', 'In progress', 'Finished', 'Canceled')
CREATE TABLE sprint (
    id INTEGER NOT NULL AUTO_INCREMENT,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    description TEXT NOT NULL,
    status sprintStatus NOT NULL,
    PRIMARY KEY(id)
)
CREATE TYPE userStoryStatus AS ENUM ('To do', 'In progress', 'Review', 'Done')
CREATE TABLE userStory (
    id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    attachments BYTEA NOT NULL,
    storyPoints INTEGER NOT NULL,
    status userStoryStatus NOT NULL,
    PRIMARY KEY(id)
)
CREATE TABLE sprint_userStory (
    sprint_id INTEGER NOT NULL,
    userStory_id INTEGER NOT NULL,
    PRIMARY KEY (sprint_id, userStory_id),
    CONSTRAINTS fk_sprint FOREIGN KEY (sprint_id) REFERENCES sprint(id),
    CONSTRAINTS fk_userStory FOREIGN KEY (userStory_id) REFERENCES userStory(id)
)