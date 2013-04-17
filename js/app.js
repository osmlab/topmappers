(function() {

    // Format number string
    function format(nStr) {
        nStr += '';
        x = nStr.split('.');
        x1 = x[0];
        x2 = x.length > 1 ? '.' + x[1] : '';
        var rgx = /(\d+)(\d{3})/;
        while (rgx.test(x1)) {
            x1 = x1.replace(rgx, '$1' + ',' + '$2');
        }
        return x1 + x2;
    }

    // Set up map
    var map = mapbox.map('map');
    map.addLayer(mapbox.layer().id('ruben.map-1pq89o6c,ruben.users50_us'));

    map.setZoomRange(3, 6);
    map.ui.zoomer.add();
    map.ui.zoombox.add();
    map.ui.hash.add();
    map.ui.attribution.add().content('<a href="http://www.openstreetmap.org/copyright">(c) OpenStreetMap contributors</a>');
    map.centerzoom({ lat: 41.474, lon: -101.034 }, 4);

    // Set up drop down
    var suma = 0;
    var o = '';
    for (var i = 0; i < topmappers.length; i++) {
        suma += topmappers[i].num_edit;
        var num_edit = format(topmappers[i].num_edit);
        o += '<li  id="' + topmappers[i].user_id + '">' +
            '<a class="users" href="#' + topmappers[i].osm_user + '">' + topmappers[i].num_post + '. ' + topmappers[i].osm_user + ' (' + num_edit + ' edits)' +
            '</a>' +
            '</li>';
    };
    var o_ = '<li  id="s50" class="active"> <a class="users" href="#"> All mappers  (' + format(suma) + '  edits)</a></li><li class="divider"></li>';
    o = o_ + o;
    $('#userlayers').append(o);
    $('#userlayers').on('click', 'li', function(e) {
        $('#userlayers li').removeClass('active');
        $('.dropdown-toggle').html($(this).text() + '<b class="caret "></b>');
        map.removeLayerAt(0);
        map.addLayer(mapbox.layer().id('ruben.map-1pq89o6c,ruben.user' + $(this).attr('id') + "_us"));
        $(this).addClass('active');
    });

})();
