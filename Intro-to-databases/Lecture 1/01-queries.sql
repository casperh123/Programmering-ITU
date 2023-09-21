-- How many players have an email address with yahoo.dk?

select *
from Player 
where email like '%@yahoo.dk';

select count(*)
from Player 
where email like '%@yahoo.dk';

-- How many entries in the Score relation are lower than the average score
-- of all entries in the relation? Is it a reasonable number?

select *
from Score; 

select count(*) 
from Score;

select avg(score) 
from Score;

select count(*) 
from Score
where score < (select avg(score) from Score);

-- Which players have an achievement that does not exist? 

-- One version: NOT IN
select *
from Player P 
    join PlayerAchievement PA on P.id = PA.playerId
where PA.achievementId not in (
    select id 
    from Achievement
);

-- Alternative version: NOT EXISTS
-- Note: There are more potential ways to write this query!
select *
from player P
	join playerachievement PA on P.id = PA.playerid
where not exists (
	select * 
	from achievement A 
	where id = PA.achievementid
);

-- Which players have both a registered score and a registered achievement 
-- in some game produced by Crytek?
select distinct P.id, P.name
from player P
	join score S on P.id = S.playerid
	join game G on S.gameid = G.id
	join playerachievement PA on P.id = PA.playerid 
	join achievement A on A.id = PA.achievementid
where G.producer = 'Crytek'
	-- Need to remember this condition!
	and S.gameid = A.gameid;
