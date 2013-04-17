Top Mappers in US by # of Changesets
====================================

Data processing scripts, map rendering scripts. For web application, see `gh-pages` branch.

source data  http://planet.openstreetmap.org/planet/

### Crear Base de datos SQLite

Source from https://github.com/tmcw/sometimemachine

cambios realizados en stm.py, con respecto a la fecha y usuario osm

	cat schema.sql | sqlite3 changesets.sqlite

	python stm.py changesets-latest.osm changesets.sqlite

 	create index uid_idx on osm_changeset (user_id);

### importar SQLite a Postgres.

	psql -U postgres -c "create database db_planet_changeset;"
	psql -U postgres -d db_planet_changeset -f /usr/share/postgresql/9.1/contrib/postgis-2.0/postgis.sql
	psql -U postgres -d db_planet_changeset -f /usr/share/postgresql/9.1/contrib/postgis-2.0/spatial_ref_sys.sql

to-postgis.sh from https://gist.github.com/ian29/e7e5258bf3406b24e033
(ogr2pgsql dbname src table)

	ruben@ruben-pc:~$ sudo cp -a to-postgis.sh ~/.bashrc
	ruben@ruben-pc:~$ source ~/.bashrc
	ruben@ruben-pc:~$ ogr2pgsql db_planet_changeset ~/changesets_planet.sqlite osm_changeset



### importar SHP de US.

	shp2pgsql -d -I -s 4326 USA_adm1.shp  us_admin | psql -d db_planet_changeset

#### adicionales

for timestamp
http://www.onlineconversion.com/unix_time.htm

