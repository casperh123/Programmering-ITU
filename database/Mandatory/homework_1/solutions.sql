1)

SELECT DISTINCT COUNT(*) 
FROM person 
WHERE deathdate 
IS null;

Answer: 52759

2)

SELECT COUNT(*) 
FROM (
    SELECT m.id
        FROM movie m 
    JOIN involved i ON m.id = i.movieid 
    JOIN person p ON i.personid = p.id 
    WHERE m.language = 'French' 
    GROUP BY m.id
    HAVING avg(p.height) > 185
) AS subquery;

Answer: 46

3)

SELECT COUNT(*) FROM (
    SELECT movieid 
        FROM movie_genre 
    WHERE genre = 'Action' 
    GROUP BY movieid 
    HAVING COUNT(movieid) > 1
) AS amount;

Answer: 14

4)

SELECT DISTINCT p.name
FROM involved i 
JOIN movie m ON i.movieid = m.id
JOIN person p ON i.personid = p.id 
WHERE m.id IN (
    SELECT m.id
    FROM movie m
    JOIN involved i ON m.id = i.movieid
    JOIN person p ON i.personid = p.id
    WHERE i.role = 'director' AND p.name = 'Ingmar Bergman'
) AND i.role = 'actor';

Answer: 52

5)

SELECT COUNT(*) AS moviesamount
FROM (
    SELECT COUNT(DISTINCT m.id) FROM movie m
    JOIN involved i ON m.id = i.movieid
    WHERE i.role = 'actor' AND m.year = 2010
    GROUP BY m.id HAVING COUNT(i.personid) = 1
) AS subquery;

Answer: 18

6)


SELECT COUNT(DISTINCT personid) AS movies
FROM (
    SELECT actor.personid
    FROM involved actor
    JOIN involved director ON actor.movieid = director.movieid
    WHERE actor.role = 'actor' and director.role = 'director'
    GROUP BY actor.personid, director.personid
    HAVING COUNT(DISTINCT actor.movieid) > 12
) AS subquery;


Answer: 34


