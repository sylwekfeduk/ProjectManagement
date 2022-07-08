INSERT INTO sprint (id, start_date, end_date, description, sprint_status)
VALUES (1, '2022-06-15', '2022-06-30', 'The new feature of web application', 1);

INSERT INTO user_story (id, name, description, number_of_story_points, user_story_status)
VALUES (1, 'New tab', 'Create new tab in web application',  7, 1);

INSERT INTO user_story (id, name, description, number_of_story_points, user_story_status)
VALUES (2, 'Basket feature', 'Create basket feature',  7, 2);

INSERT INTO user_story (id, name, description, number_of_story_points, user_story_status)
VALUES (3, 'Logging feature', 'Create logging feature',  7, 3);

INSERT INTO user_story (id, name, description, number_of_story_points, user_story_status)
VALUES (4, 'Adding attachments feature', 'Create adding attachments function',  7, 4);

INSERT INTO sprint_user_story (sprint_id, user_story_id)
VALUES (1, 2);

INSERT INTO sprint_user_story (sprint_id, user_story_id)
VALUES (1, 3);

INSERT INTO sprint_user_story (sprint_id, user_story_id)
VALUES (1, 4);