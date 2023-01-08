insert into member (id, nickname, token) values (1, '익명1', '1');
insert into member (id, nickname, token) values (2, '익명2', '2');
insert into member (id, nickname, token) values (3, '익명3', '3');

insert into team (id, is_alarm_active, meal_time, team_code, team_name) values (4, false, '12:00:00', '1111', 'team1');

insert into team_member (id, member_id, team_id) values (5, 1, 4);
insert into team_member (id, member_id, team_id) values (6, 2, 4);
insert into team_member (id, member_id, team_id) values (7, 3, 4);
