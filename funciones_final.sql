ALTER TABLE osm_changeset ADD COLUMN lat double precision;
ALTER TABLE osm_changeset ADD COLUMN lon double precision;
ALTER TABLE osm_changeset ADD COLUMN geom GEOMETRY;
CREATE INDEX geom_osm_changeset_index  ON osm_changeset using gist(geom);

--ALTER TABLE osm_changeset DROP  COLUMN lon RESTRICT;
--ALTER TABLE osm_changeset DROP  COLUMN lat RESTRICT;
--ALTER TABLE osm_changeset DROP COLUMN geom RESTRICT;

  
--Update Data --
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


----------------------------funcion que conprueba si un punto pertenece a US
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
select check_contained(ST_PointFromText('POINT(-98.711 39.31513)', 4326));
select check_contained(ST_PointFromText('POINT(-12 -74)', 4326));

-------------------------------------------Funccion para eliminar Filas que no estan en US
CREATE OR REPLACE FUNCTION remove_changes(init INTEGER,final INTEGER) 
RETURNS INT
AS $$
DECLARE
	_geom GEOMETRY;
	_bandera boolean;
      
BEGIN		        
        FOR _i IN init..final
        
		LOOP 	
		    --RAISE  NOTICE '====================ID=%', _i;
			_geom=(select geom from osm_changeset where ogc_fid=_i);
			_bandera=check_contained(_geom);
			--RAISE  NOTICE '===========================%', _bandera ;
			IF (_bandera=false) THEN
			--RAISE  NOTICE '===========================%', 'Elimina' ;			
				DELETE FROM osm_changeset
				WHERE ogc_fid=_i;					 				    
			END IF;				
 
		END LOOP;		
	       RETURN final;
END;
$$ LANGUAGE plpgsql;


--Ejecutar Funcion para ajecutar mmas rapido el procesamiento y aprovechar al maxino La Maquina
select remove_changes(1,100000);
select remove_changes(100001,200000);
select remove_changes(200001,400000);
select remove_changes(400001,600000);
select remove_changes(600001,800000);
select remove_changes(800001,1000000);
select remove_changes(1000001,1200000);
select remove_changes(1200001,1400000);
select remove_changes(1400001,1600000);
select remove_changes(1600001,1800000);
select remove_changes(1800001,2000000);
select remove_changes(2000001,2200000);
select remove_changes(2200001,2400000);
select remove_changes(2400001,2600000);
select remove_changes(2600001,2800000);
select remove_changes(2800001,3000000);
select remove_changes(3000001,3200000);
select remove_changes(3200001,3400000);
select remove_changes(3400001,3600000);
select remove_changes(3600001,3800000);
select remove_changes(3800001,4000000);
select remove_changes(4000001,4200000);
select remove_changes(4200001,4400000);
select remove_changes(4400001,4600000);
select remove_changes(4600001,4800000);
select remove_changes(4800001,5000000);
select remove_changes(5000001,5141885);

select count(*) from osm_changeset

-------Finalmente Seleccionar de manera decentente los usuario y sus ediciones
SELECT osm_user , count(*) AS nun_edits FROM osm_changeset GROUP BY osm_user ORDER BY nun_edits DESC;

