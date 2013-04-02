var map_id = 'ruben.map-5164bfio',
    features = [],
    interaction,
    map = mapbox.map('map');
map.addLayer(mapbox.layer().id(map_id));
map.centerzoom({
    lat: 37.504,
    lon: -94.668
}, 5);

map.setZoomRange(0, 18);
/*map.ui.zoomer.add();
map.ui.zoombox.add();
map.ui.hash.add();*/
mm_edit(listEdit);

mm_user(listUser);


function listUser(f) {
    var list_usser = f;
    //console.log(list_usser);
    var o = '';
    for (var i = 0; i < list_usser.length-75; i++) {
        o += '<li  id="' + list_usser[i].user_id + '"><a class="users" href="#' + list_usser[i].osm_user + '">' + list_usser[i].osm_user + '</a></li>';
    };
    //console.log(o);
    $('ul').append(o);
}


function listEdit(f) {
    features = f;
    console.log(features);
    $('#map').removeClass('loading');
}



function mapData(f) {

    if (map.getLayers().length == 2) {
        map.removeLayerAt(1);
    }

    var features_edit = [];
    features_edit = f;

    markerLayer = mapbox.markers.layer().features(features_edit);
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
        small: [15, 20],
        medium: [30, 70],
        large: [35, 90]
    };
    var fp = feature.properties || {};
    var size = fp['marker-size'] || 'medium';
    var symbol = fp['marker-symbol'];
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

$(document).ready(function() {


    $('#userlayers').on('click', 'li', function(e) {
        function findBy(id) {
            var found;
            for (var i = 0; i < features.length; i++) {
                var feature = features[i];
                if (feature.user_id == id) {
                    found = feature;
                    break
                }
            }
            return found
        };

        var user_id_find = $(this).attr('id');
        var user = findBy(user_id_find);
        mapData(user.edicion);
        // console.log(user);

    });

});