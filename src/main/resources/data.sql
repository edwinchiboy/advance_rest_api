INSERT INTO user_details (id, birth_date,name)
values (10001, current_date(),'Ranga');

INSERT INTO user_details (id, birth_date,name)
values (10002, current_date(),'Ravi');

INSERT INTO user_details (id, birth_date,name)
values (10003, current_date(),'Chiboy');

INSERT INTO post(id, description, user_id)
values (20001,'I want  to learn Spring', 10001);

INSERT INTO post(id, description, user_id)
values (20002,'I want  to learn AWS', 10001);

INSERT INTO post(id, description, user_id)
values (20003,'I want  to learn Java', 10002);

INSERT INTO post(id, description, user_id)
values (20004,'I want  to learn Flutter', 10002);