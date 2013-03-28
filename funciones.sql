--create index  for osm_changeset

CREATE INDEX osm_changeset_index ON osm_changeset(ogc_fid);

--------------------------------CREATE TABLE osm_changeset_US

CREATE TABLE osm_changeset_us
(
  ogc_fid serial NOT NULL,
  user_osm varchar(100),
  user_id integer,
  min_lon double precision,
  min_lat double precision,
  max_lon double precision,
  max_lat double precision,
  closed_at integer,
  num_changes integer
)

CREATE INDEX osm_changeset_us_index ON osm_changeset_us(ogc_fid);


---------------------------------FUNCION QUE CONPRUEBA SI PERTENE A U.S

CREATE OR REPLACE FUNCTION contained_node(lat decimal ,lon decimal)
RETURNS  boolean
AS $$
DECLARE
	_poit_string VARCHAR(200);
	_name VARCHAR(200);
	_bandera boolean;
	BEGIN
		_bandera=false;
		_poit_string='POINT(' || lon || ' ' || lat ||')';

		RAISE  NOTICE '====================ID=%', _poit_string;
		_name=(SELECT name_0 FROM us_admin WHERE ST_Within(ST_PointFromText(_poit_string, 4326), us_admin.geom));
			--RAISE  NOTICE '============Nulo=%',_name;
			IF (_name IS NULL) THEN	
			_bandera=false;
			ELSE
			_bandera=true;											
			--RAISE  NOTICE '=============%',_name;					    
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
CREATE OR REPLACE FUNCTION fill_data(init INTEGER,final INTEGER) 
RETURNS INT
AS $$
DECLARE
	--_init  BIGINT;	
	--_final BIGINT;
	_ogc_fid BIGINT;
	_user_osm varchar(100);
	_user_id INTEGER;
	_closed_at INTEGER;
	_num_changes INTEGER;	
	_lat DECIMAL;
	_lon DECIMAL;
	_bandera BOOLEAN;
      
BEGIN	
	--_final=(select count(*) from osm_changeset);		        
        FOR _i IN init..final

		LOOP 	RAISE  NOTICE '====================ID=%', _i;
			_lat=(select (min_lat + max_lat)/2 from osm_changeset where ogc_fid=_i);
			_lon=(select (min_lon + max_lon)/2 from osm_changeset where ogc_fid=_i);	
			_bandera=contained_node(_lat,_lon);			
			IF (_bandera) THEN
			RAISE  NOTICE '===============================INSERT=%', _i;	
			_user_id=(select user_id from osm_changeset where ogc_fid=_i);
			_closed_at=(select closed_at from osm_changeset where ogc_fid=_i);
			_num_changes=(select num_changes from osm_changeset where ogc_fid=_i);			
			INSERT INTO osm_changeset_us(ogc_fid, user_osm, user_id, closed_at, num_changes)
			 VALUES (_i, 'null', _user_id, _closed_at, _num_changes);
			 				    
			END IF;	
						
 
		END LOOP;
		
	       RETURN final;
END;
$$ LANGUAGE plpgsql;

--TEST
select fill_data(56666,100600);









/*
select ST_SRID(geom) from us_admin;
select ST_Extent(geom) from us_admin;
select *from us_admin
select count(*) from osm_changeset ;
select * from osm_changeset limit 2;

--punto central de las base de datos

select POINT(49.1874282,6.8995722) as lon from osm_changeset where ogc_fid=1 ; 

select ST_PointFromText('POINT(-122.161445617676 37.444938659668)', 4326)

select *from us_admin limit 10

select id_1  from  us_admin where  ST_Within(tiger_version.geom,the_geom));

select st_within(ST_PointFromText('POINT(-122.161445617676 37.444938659668)', 4326),ST_PointFromText('POINT(-122.161445617676 37.444938659668)', 4326));

SELECT name_1   FROM us_admin WHERE ST_Within(ST_PointFromText('POINT(37.444938659668 -122.161445617676)', 4326), us_admin.geom)

select *from osm_changeset where ((min_lat+max_lat)/2) =-1.21129430000000000000
select *from osm_changeset limit 10

select (-1.2112943 + -1.2112943)/2

--SELECT ST_Within(ST_PointFromText('POINT(-122.161445617676 37.444938659668)', 4326), us_admin.geom)  FROM us_admin --
*/







