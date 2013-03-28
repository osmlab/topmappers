ALTER TABLE osm_changeset ADD COLUMN lat double precision;
ALTER TABLE osm_changeset ADD COLUMN lon double precision;
ALTER TABLE osm_changeset ADD COLUMN geom GEOMETRY;
CREATE INDEX geom_osm_changeset_index  ON osm_changeset using gist(geom);

--ALTER TABLE osm_changeset DROP  COLUMN lon RESTRICT;
--ALTER TABLE osm_changeset DROP  COLUMN lat RESTRICT;
--ALTER TABLE osm_changeset DROP COLUMN geom RESTRICT;

  
--fill data--
   UPDATE osm_changeset
   SET lat=(SELECT ((min_lat+max_lat)/2))
   --WHERE ogc_fid<100;
   
  UPDATE osm_changeset
   SET lon=(SELECT ((min_lon+max_lon)/2))
   --WHERE ogc_fid<100;

   
---geometry
  UPDATE osm_changeset
   SET geom=(SELECT ST_PointFromText('POINT(' || lon || ' ' || lat ||')', 4326))
   --WHERE ogc_fid<100;


--delet for test soem rows
DELETE FROM osm_changeset
WHERE ogc_fid >100000;

select count(*) from osm_changeset;




CREATE OR REPLACE FUNCTION check_contained(_geom Geometry)
RETURNS  boolean
AS $$
DECLARE
	_name VARCHAR(200);
	_bandera boolean;
	BEGIN
		_bandera=false;

		_name=(SELECT name_0 FROM us_admin WHERE st_contains(us_admin.geom, _geom));
		IF (_name IS NULL) THEN	
			_bandera=false;
		ELSE
			_bandera=true;	
		END IF;	
	RETURN _bandera;
	END;
$$ LANGUAGE plpgsql;


--TEST
select check_contained(49.1874282,6.8995722);
select check_contained(37.444938659668,-122.161445617676);
select check_contained(-12,-74);
select check_contained(ST_PointFromText('POINT(-98.711 39.31513)', 4326));

select check_contained(ST_PointFromText('POINT(-12 -74)', 4326));


-----------------------------------------------------------------
CREATE OR REPLACE FUNCTION remove_changes(init INTEGER,final INTEGER) 
RETURNS INT
AS $$
DECLARE
	_geom GEOMETRY;
	_bandera boolean;
      
BEGIN		        
        FOR _i IN init..final
        
		LOOP 	RAISE  NOTICE '====================ID=%', _i;
			_geom=(select geom from osm_changeset where ogc_fid=_i);
			_bandera=check_contained(_geom);

			RAISE  NOTICE '===========================%', _bandera ;
			IF (_bandera=false) THEN
			RAISE  NOTICE '===========================%', 'Elimina' ;			
				DELETE FROM osm_changeset
				WHERE ogc_fid=_i;					 				    
			END IF;				
 
		END LOOP;		
	       RETURN final;
END;
$$ LANGUAGE plpgsql;




select remove_changes(1,100);

select remove_changes(101,20000);
select remove_changes(20001,40000);
select remove_changes(40001,60000);
select remove_changes(60001,80000);
select remove_changes(80001,100000);

select count(*) from osm_changeset










/*
select o.user_id , count(o.num_changes)from osm_changeset  as  o 
left join   us_admin as u 
on st_contains(u.geom, o.geom)
GROUP BY o.user_id limit 2

510836 
153669;2695
81339;26

select user_id , count(num_changes) from  osm_changeset   where user_id=81339 GROUP BY user_id*/



