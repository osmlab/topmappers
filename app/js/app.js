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

map.ui.zoomer.add();
map.ui.zoombox.add();
map.ui.hash.add();

mm_user(listUser);

//mm_file_user('user722137.json', mapData);


function listUser(f) {
    var list_usser = f;
    //console.log(list_usser);
    var o = '';
    for (var i = 0; i < list_usser.length; i++) {
        o += '<li  id="' + list_usser[i].user_id + '"><a class="users" href="#' + list_usser[i].osm_user + '">' + list_usser[i].osm_user + '</a></li>';
    };
    //console.log(o);
    $('#userlayers').append(o);
}


function listEdit(f) {
    features = f;
    console.log(features);

}



function mapData(f) {

    stadistis(f.editions);
    if (map.getLayers().length == 2) {
        map.removeLayerAt(1);
    }



    map.interaction.refresh()
    var features_edit = [];
    features_edit = f.features;

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



    interaction.formatter(function(feature) {
        var o = '<div class="well-toltip">' +
            '<p> num changes :' + feature.properties.num_changes + '</p>' +
            '</div>';
        return o;
    });



    $('#map').removeClass('loading');
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
        small: [10, 10],
        medium: [30, 70],
        large: [35, 90]
    };
    var fp = feature.properties || {};
    var size = fp['marker-size'] || 'medium';
    var symbol = fp['marker-symbol'];
    var color = fp['marker-color'] || '7e7e7e';
    color = color.replace('#', '');
    var d = document.createElement('img');
    d.className = 'simplestyle-marker';
    d.alt = fp.title || '';
    d.src = 'http://dl.dropbox.com/u/43116811/json_user/icon.png';
    var ds = d.style;
    ds.position = 'absolute';
    ds.cursor = 'pointer';
    ds.pointerEvents = 'all';
    return d;
};



google.load("visualization", "1", {
    packages: ["corechart"]
});


function stadistis(f) {

    var rowArray = [];
    for (var i = 0; i < f.length; i++) {
        rowArray.push([f[i].date, f[i].num_edicion]);
    };

    drawChart();

    function drawChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Date');
        data.addColumn('number', 'Num Editions');
        data.addRows(rowArray);
        var chart = new google.visualization.AreaChart(document.getElementById('draw_area'));

        chart.draw(data, {
            width: 600,
            height: 180,
            title: 'Editions by Month',
            hAxis: {
                title: 'Date',
                titleTextStyle: {
                    color: '#404040'
                }
            },
            vAxis: {
                title: 'Num Editions',
                titleTextStyle: {
                    color: '#404040'
                }
            }
        });
    }


}


$(document).ready(function() {
    $('#map').removeClass('loading');

    $('#userlayers').on('click', 'li', function(e) {

        var file_user = 'user' + $(this).attr('id') + '.json';
        $('#map').addClass('loading');
        mm_file_user(file_user, mapData);

    });



    $('#userlayers2').on('click', 'li', function(e) {

        var mbtiles_id = 'user' + $(this).attr('id');
        alert(mbtiles_id);
        $('#map').addClass('loading');

        if (map.getLayers().length == 2) {
            map.removeLayerAt(1);
        }


        map.addLayer(mapbox.layer().id('ruben.' + mbtiles_id, function() {

            map.interaction.auto();
        }));
        map.interaction.refresh()

        $('#map').removeClass('loading');

    });

});