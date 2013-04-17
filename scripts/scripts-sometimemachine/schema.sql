CREATE TABLE osm_changeset (
  user_id INTEGER,
  osm_user VARCHAR(100),
  min_lon REAL,
  min_lat REAL,
  max_lon REAL,
  max_lat REAL,
  closed_at INTEGER,
  num_changes INTEGER
);
