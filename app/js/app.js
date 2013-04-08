var map_id = 'ruben.map-5164bfio',
    features = [],
    interaction,
    map = mapbox.map('map');
map.addLayer(mapbox.layer().id(map_id));
map.centerzoom({
    lat: 38.163,
    lon: -104.907
}, 5);


map.setZoomRange(0, 12);
map.ui.zoomer.add();
map.ui.zoombox.add();
map.ui.hash.add();
mm_user(listUser);

function listUser(f) {
    var list_usser = f;
    var o = '';
    for (var i = 0; i < list_usser.length; i++) {
        o += '<li  id="' + list_usser[i].user_id + '"><a class="users" href="#' + list_usser[i].osm_user + '">' + list_usser[i].osm_user + '</a></li>';
    };
    $('#userlayers').append(o);
    map.addLayer(mapbox.layer().id('ruben.user590362', function() {
        map.interaction.auto();
    }));
    mm_file_user('user590362', stadistis);
};

google.load("visualization", "1", {
    packages: ["corechart"]
});

function stadistis(f) {

    var rowArray = [];
    for (var i = 0; i < f.editions.length; i++) {
        rowArray.push([f.editions[i].d, f.editions[i].ne]);
    };

    drawChart();

    function drawChart() {

        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Date');
        data.addColumn('number', 'Num Editions');
        data.addRows(rowArray);
        var chart = new google.visualization.AreaChart(document.getElementById('draw_area'));

        var options = {
            width: 600,
            height: 180,
            title: 'Editions by Month from user : ' + f.osm_user,
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
            },
            legend: 'none',
            chartArea: {
                left: 25,
                top: 20,
                width: "95%",
                height: "70%"
            },
            backgroundColor: 'transparent',
            colors:['#B75C30']
        };

        chart.draw(data, options);
    }



    /*var data = new google.visualization.DataTable();
        data.addColumn('string', 'Date');
        data.addColumn('number', 'Num Editions');
        data.addRows(rowArray);
        var chart = new google.visualization.AreaChart(document.getElementById('draw_area'));
        chart.draw(data, {
            width: 600,
            height: 180,
            title: 'Editions by Month from '+ osm_user ,
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
    }*/
}


$(document).ready(function() {
    $('#map').removeClass('loading');
    /*
    $('#userlayers').on('click', 'li', function(e) {
        var file_user = 'user' + $(this).attr('id') + '.json';
        $('#map').addClass('loading');
        mm_file_user(file_user, mapData);
    });
*/
    $('#userlayers').on('click', 'li', function(e) {
        var mbtiles_id = 'user' + $(this).attr('id');
        $('#map').addClass('loading');
        if (map.getLayers().length == 2) {
            map.removeLayerAt(1);
        }
        map.addLayer(mapbox.layer().id('ruben.' + mbtiles_id, function() {
            map.interaction.auto();
        }));
        map.interaction.refresh();
        //alert(mbtiles_id);
        mm_file_user(mbtiles_id, stadistis);

        $('#map').removeClass('loading');
    });
});