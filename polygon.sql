----------------------------------------GENERAR POLIGONOS DE PUNTOS
select  id, 
ST_PolygonFromText('POLYGON(('||max_lon||' '||min_lat ||','||max_lon||' '||max_lat||','||min_lon||' '||max_lat ||','||min_lon||' '||min_lat||','||max_lon||' '||min_lat||'))',4326) 
from osm_changeset limit 10

----------------------------------------add column in table osm_change_set
ALTER TABLE osm_changeset ADD COLUMN geom_polygon GEOMETRY;
--CREATE INDEX geom_polygon_osm_changeset_index  ON osm_changeset using gist(geom_polygon);
  
---geometry
  UPDATE osm_changeset
   SET geom_polygon=(ST_PolygonFromText('POLYGON(('||max_lon||' '||min_lat ||','||max_lon||' '||max_lat||','||min_lon||' '||max_lat ||','||min_lon||' '||min_lat||','||max_lon||' '||min_lat||'))',4326))

