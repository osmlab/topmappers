var map_id = 'ruben.map-5164bfio',
    features = [],
    interaction,
    map = mapbox.map('map');
map.addLayer(mapbox.layer().id(map_id));
map.centerzoom({
    lat: 39.188,
    lon: -97.575
}, 5);

var list_usser = [];

//inicialiser
map.addLayer(mapbox.layer().id('ruben.user722137', function() {
    map.interaction.auto();
    map.interaction.off('on');
    map.interaction.off('off');
    map.interaction.on({
        on: function(o) {
            var mydiv = document.getElementById('interactive');
            mydiv.style.display = 'block';
            document.getElementById('osm_user').innerHTML = o.data.osm_user;
            document.getElementById('edit_at').innerHTML = o.data.closed_at;
            document.getElementById('num_changes').innerHTML = o.data.num_changes;
        },
        off: function(o) {
            var mydiv = document.getElementById('interactive');
            mydiv.style.display = 'none';
        }
    });

}));


map.setZoomRange(0, 12);
map.ui.zoomer.add();
map.ui.zoombox.add();
map.ui.hash.add();
mm_user(listUser);

function listUser(f) {

    list_usser = f;
    var o = '';
    for (var i = 0; i < list_usser.length; i++) {
        if (i == 0) {
            o += '<li  class="active" id="' + list_usser[i].user_id + '"><a class="users" href="#' + list_usser[i].osm_user + '">' + list_usser[i].osm_user + '</a></li>';

        } else {
            o += '<li  id="' + list_usser[i].user_id + '"><a class="users" href="#' + list_usser[i].osm_user + '">' + list_usser[i].osm_user + '</a></li>';
        }
    };

    $('#userlayers').append(o);
    $('#map').removeClass('loading');


};

google.load("visualization", "1", {
    packages: ["corechart"]
});

function stadistis(f) {

    var rowArray = [];
    var num_edition = 0;
    for (var i = 0; i < f.editions.length; i++) {
        rowArray.push([f.editions[i].d, f.editions[i].ne]);
        num_edition = num_edition + f.editions[i].ne;
    };
 
    var title = '';
    if (f.osm_user === 'All Users') {
        title = 'Edit by Month from : ' + f.osm_user ;
    } else {
        title = 'Edit by Month from user : ' + f.osm_user ;
    }

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
            title: title,
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
            colors: ['#B75C30']
        };

        chart.draw(data, options);
    }
    $('.draw').addClass('well');
}


$(document).ready(function() {

    mm_file_user('user722137', stadistis);

    $('#userlayers').on('click', 'li', function(e) {

        $('#userlayers li').removeClass('active');
        $('#map').addClass('loading');

        var mbtiles_id = 'user' + $(this).attr('id');

        removelayers();
        map.addLayer(mapbox.layer().id('ruben.' + mbtiles_id, function() {
            map.interaction.auto();
            map.interaction.off('on');
            map.interaction.off('off');
            map.interaction.on({
                on: function(o) {
                    var mydiv = document.getElementById('interactive');
                    mydiv.style.display = 'block';
                    document.getElementById('osm_user').innerHTML = o.data.osm_user;
                    document.getElementById('edit_at').innerHTML = o.data.closed_at;
                    document.getElementById('num_changes').innerHTML = o.data.num_changes;
                },
                off: function(o) {
                    var mydiv = document.getElementById('interactive');
                    mydiv.style.display = 'none';
                }
            });
        }));
        map.interaction.refresh();
        mm_file_user(mbtiles_id, stadistis);
        $('#map').removeClass('loading');
        $(this).addClass('active');
    });



    $("#all").click(function() {
        removelayers();
        //clear stadistic
        $('#draw_area').empty();

        $('.draw').removeClass('well');

        map.addLayer(mapbox.layer().id('ruben.users', function() {
            map.interaction.auto();
            map.interaction.off('on');
            map.interaction.off('off');
            map.interaction.on({
                on: function(o) {
                    var mydiv = document.getElementById('interactive');
                    mydiv.style.display = 'block';
                    document.getElementById('osm_user').innerHTML = o.data.osm_user;
                    document.getElementById('edit_at').innerHTML = o.data.closed_at;
                    document.getElementById('num_changes').innerHTML = o.data.num_changes;
                },
                off: function(o) {
                    var mydiv = document.getElementById('interactive');
                    mydiv.style.display = 'none';
                }
            });

        }));
        map.interaction.refresh();
        mm_file_user('users', stadistis);
    });


    function removelayers() {
        //remove layers
        if (map.getLayers().length == 2) {
            map.removeLayerAt(1);
        }
        if (map.getLayers().length == 11) {
            for (var i = 1; i < 11; i++) {
                map.removeLayerAt(1);
            };
        }
    };

});