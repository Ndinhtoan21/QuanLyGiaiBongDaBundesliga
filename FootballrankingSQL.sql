create database bundesligadb


create table team_info (
	team_id int(5) NOT NULL AUTO_INCREMENT,
    team_name varchar(120) NOT NULL,
    founding_date varchar(120) NOT NULL,
    home_stadium varchar(120),
    PRIMARY KEY (team_id)
)


create table matchs (
	match_id int(5) NOT NULL AUTO_INCREMENT,
    home_team_id int(5),
    away_team_id int(5),
    home_team_score int(5),
    away_team_score int(5),
    match_date date,
    primary key (match_id),
    FOREIGN KEY (home_team_id) REFERENCES team_info(team_id),
    FOREIGN KEY (away_team_id) REFERENCES team_info(team_id)
	)