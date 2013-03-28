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
   WHERE ogc_fid<100;








CREATE OR REPLACE FUNCTION contained(_geom Geometry)
RETURNS  boolean
AS $$
DECLARE
	_name VARCHAR(200);
	_bandera boolean;
	BEGIN
		_bandera=false;

		_name=(SELECT name_0 FROM us_admin WHERE st_contains(us_admin.geom, _geom);
			IF (_name IS NULL) THEN	
			_bandera=true;
			ELSE
			_bandera=false;	
			END IF;	
		RETURN _bandera;
	END;
$$ LANGUAGE plpgsql;


--TEST
select contained_node(49.1874282,6.8995722);
select contained_node(37.444938659668,-122.161445617676);
select contained_node(-12,-74);
select contained_node(32.91332,-82.88819);


-----------------------------------------------------------------
CREATE OR REPLACE FUNCTION remove_changes(init INTEGER,final INTEGER) 
RETURNS INT
AS $$
DECLARE
	_geom GEOMETRY;
      
BEGIN		        
        FOR _i IN init..final
        
		LOOP 	RAISE  NOTICE '====================ID=%', _i;
			_geom=(select geom from osm_changeset where ogc_fid=_i);

			IF (_bandera) THEN			
				DELETE FROM Table
				WHERE ogc_fid=_i;					 				    
			END IF;				
 
		END LOOP;		
	       RETURN final;
END;
$$ LANGUAGE plpgsql;















/*
select o.user_id , count(o.num_changes)from osm_changeset  as  o 
left join   us_admin as u 
on st_contains(u.geom, o.geom)
GROUP BY o.user_id limit 2

510836 
153669;2695
81339;26

select user_id , count(num_changes) from  osm_changeset   where user_id=81339 GROUP BY user_id*/



