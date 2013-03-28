_poit_string='POINT(' || lon || ' ' || lat ||')';

		--RAISE  NOTICE '====================ID=%', _poit_string;
		_name=(SELECT name_0 FROM us_admin WHERE ST_Within(ST_PointFromText(_poit_string, 4326), us_admin.geom));

select o.user_id from osm_changeset  as  o 
left join   us_admin as u 
on ST_Within(ST_PointFromText('POINT(' || ((min_lon+max_lon)/2) || ' ' || ((min_lat+max_lat)/2) ||')', 4326), u.geom)
limit 100


select user_id, max(num_changes) from osm_changeset GROUP BY user_id limit 10


select * from osm_changeset where user_id=510836 


