var map_id = 'examples.map-4l7djmvo',
    features = [],
    interaction,
    map = mapbox.map('map');



map.addLayer(mapbox.layer().id(map_id));


mm_data(mapData);

map.centerzoom({
    lat: 37.504,
    lon: -94.668
}, 5);

map.setZoomRange(0, 18);

function mapData(f) {
    features = f[2].edicion;
    //features = f;    
    console.log(features);

    markerLayer = mapbox.markers.layer().features(features);




    interaction = mapbox.markers.interaction(markerLayer);
    map.addLayer(markerLayer);
    map.ui.zoomer.add();
    map.ui.zoombox.add();
    map.ui.hash.add();

}


function newMarker() {
    if (window.location.hash == '#new') {
        $('#new').fadeIn('slow');
        window.location.hash = '';
        window.setTimeout(function() {
            $('#new').fadeOut('slow');
        }, 4000)
    }
}