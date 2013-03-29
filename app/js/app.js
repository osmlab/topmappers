var map_id = 'examples.map-4l7djmvo',
    features = [],
    interaction,
    map = mapbox.map('map');
var recursos_search = [];


map.addLayer(mapbox.layer().id(map_id));

mm_data(mapData); 


map.centerzoom({
    lat: -13.16048,
    lon: -74.22565
}, 15);
map.setZoomRange(0, 18);

function mapData(f) {

    features = f;
    console.log('ultimopor aqui' + features);/*
    
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
    interaction.formatter(function(feature) {
        var o = '<h5 class="popover-geo-title">' + feature.nombre + '</h5>' +
            '<p>' + feature.descripcion.substring(0, 100) + '...</p>' +
            '<div class="well-toltip">' +
            '<img style="height: 120px; width:120px;   margin-right: 3px;" src="' + feature.imagenes[0].url + '">' +
            '<img style="height: 120px; width:120px;" src="' + feature.imagenes[1].url + '">' +
            '</div>';
        var a_button = '';
        var a = feature.clase.replace(/\s/g, "");

        if (a == 'RecursoTurístico') {
            a_button = '<a  role="button" class="btn"  href="#detail" onclick="call_detaill_recurso(\'' + feature.idproducto + '\')"> Más Detalle</a>';

        } else if (a == 'Hotel') {
            a_button = '<a  role="button" class="btn"  href="#detail" onclick="call_detail_hotel(\'' + feature.idproducto + '\')"> Más Detalle</a>';

        }
        return o + a_button;
    });
    $('#map').removeClass('loading');


    fill_search(features);
    grid_images(features);*/
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
