var map_id = 'ruben.map-5164bfio',
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
    console.log(f)
    features = f[24].edicion;
    //features = f;    
    console.log(features);

    markerLayer = mapbox.markers.layer().features(features);

    markerLayer.factory(function(m) {

        //var elem = mapbox.markers.simplestyle_factory(m);
        var elem = simplestyle_factory_rub(m);
        MM.addEvent(elem, 'click', function(e) {
            map.ease.location({
                lat: m.geometry.coordinates[1],
                lon: m.geometry.coordinates[0]
            }).zoom(map.zoom()).optimal();
        });

        return elem;
    });

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



simplestyle_factory_rub = function(feature) {

    var sizes = {
        small: [15, 18],
        medium: [30, 70],
        large: [35, 90]
    };

    var fp = feature.properties || {};

    var size = fp['marker-size'] || 'medium';
   
    var symbol = fp['marker-symbol'];
    //console.log(symbol);
    var color = fp['marker-color'] || '7e7e7e';
    color = color.replace('#', '');

    var d = document.createElement('img');
    d.width = sizes[size][0];
    d.height = sizes[size][1];
    d.className = 'simplestyle-marker';
    d.alt = fp.title || '';
    d.src = 'http://dl.dropbox.com/u/43116811/json_user/icon.png';
    var ds = d.style;
    ds.position = 'absolute';
    ds.clip = 'rect(auto auto ' + (sizes[size][1] * 0.75) + 'px auto)';
    ds.marginTop = -((sizes[size][1]) / 2) + 'px';
    ds.marginLeft = -(sizes[size][0] / 2) + 'px';
    ds.cursor = 'pointer';
    ds.pointerEvents = 'all';

    return d;
};

