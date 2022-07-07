CREATE TYPE sprintStatus AS ENUM ('Pending', 'In progress', 'Finished', 'Canceled')
CREATE TABLE sprint (
    id SERIAL PRIMARY KEY,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    description TEXT NOT NULL,
    status sprintStatus NOT NULL,
)
CREATE TYPE userStoryStatus AS ENUM ('Pending', 'In progress', 'Review', 'Done')
CREATE TABLE userStory (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    attachments BYTEA[] NOT NULL,
    storyPoints INT NOT NULL,
    status userStoryStatus NOT NULL,
)
CREATE TABLE sprint_userStory (
    sprint_id INT NOT NULL,
    userStory_id INT NOT NULL,
    PRIMARY KEY (sprint_id, userStory_id),
    CONSTRAINTS fk_sprint FOREIGN KEY (sprint_id) REFERENCES sprint(id),
    CONSTRAINTS fk_userStory FOREIGN KEY (userStory_id) REFERENCES userStory(id)
)