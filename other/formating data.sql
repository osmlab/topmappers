SELECT osm_user , count(*) AS nun_edits FROM osm_changeset GROUP BY osm_user ORDER BY nun_edits DESC limit 100;
SELECT user_id , count(*) AS nun_edits FROM osm_changeset GROUP BY user_id ORDER BY nun_edits DESC limit 100;


select * from osm_changeset limit 100

SELECT user_id , count(*) AS nun_edits FROM osm_changeset GROUP BY user_id ORDER BY nun_edits DESC limit 100;


SELECT osm_user from osm_changeset where  user_id=510836 limit 1;

select substring((CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| ''),1,7) as date , 
count(num_changes) as num_edition, sum(num_changes) as num_changes from  osm_changeset  where user_id=510836
 GROUP BY substring((CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| ''),1,7) 
 ORDER BY substring((CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date)|| ''),1,7)


SELECT substring(start_time,1,8) AS date, count(*) as total FROM cdrs GROUP BY substring(start_time,1,8) ORDER BY substring(start_time,1,8);

--select CAST(((TIMESTAMP WITH TIME ZONE 'epoch' +  INTERVAL '1 second' * closed_at)|| ' ') AS date) as edit , count(num_changes) from  osm_changeset  where user_id=451693 GROUP BY edit DESC;