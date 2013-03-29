
SELECT user_id , count(*) AS nun_edits FROM osm_changeset GROUP BY user_id ORDER BY nun_edits DESC limit 100

SELECT user_id , count(*) AS nun_edits FROM osm_changeset GROUP BY user_id ORDER BY nun_edits ASC limit 10




SELECT (TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * 1252606560) AS postgres_timestamp;

SELECT (TIMESTAMP WITH TIME ZONE   1252606560) AS postgres_timestamp;


--------------------------------trabajando con usuarios
select osm_user,min_lon,min_lat, max_lon , max_lat , closed_at , num_changes ,lat , lon from  osm_changeset where user_id=510836

  
select osm_user,min_lon,min_lat, max_lon , max_lat , (CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| '') as closed_at , num_changes ,lat as latitud , lon from  osm_changeset where user_id=590362;

select osm_user,lat  as latitud, lon ,min_lon,min_lat, max_lon , max_lat , (CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| '') as closed_at , num_changes from  osm_changeset where user_id=658192;


---test
select osm_user,min_lon,min_lat, max_lon , max_lat , (CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| '') as closed_at , num_changes ,lat  as latitud, lon from  osm_changeset where user_id=590362;


SELECT * FROM osm_changeset where user_id=207745

SELECT user_id , count(*) AS nun_edits FROM osm_changeset GROUP BY user_id ORDER BY nun_edits DESC 
722137

 select user_id
  from osm_changeset
  where user_id in (SELECT user_id   FROM osm_changeset GROUP BY user_id ORDER BY nun_edits DESC );

  SELECT CAST('2010-01-01 12:12:12' AS date)