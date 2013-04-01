function mm_user(callback) {
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for mm_recurso';
    }
    var url = 'http://rub21.github.com/report_top_us/app/SListUsers_final.json?callback=callback';
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });

    function response(x) {

        var features = [];
        for (var j = 0; j < x.length; j++) {
            features.push(x[i]);
        };
        console.log(features);
        return callback(features);
    }
}


function mm_data(callback) {
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for mm_recurso';
    }
    var url = 'http://rub21.github.com/report_top_us/app/SList5.json?callback=callback';
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });

    function response(x) {

        var features = [];

        for (var j = 0; j < x.length; j++) {

            for (var i = 0; i < x[j].edicion.length; i++) {
                x[j].edicion[i]['properties'] = {};

                x[j].edicion[i].properties['marker-size'] = 'small';
                x[j].edicion[i].properties['title'] = 'Num changes:' + x[j].edicion[i].num_changes;

                x[j].edicion[i]['geometry'] = {};
                x[j].edicion[i].geometry['type'] = 'Point';
                x[j].edicion[i].geometry['coordinates'] = [];
                x[j].edicion[i].geometry.coordinates[0] = x[j].edicion[i].lon;
                x[j].edicion[i].geometry.coordinates[1] = x[j].edicion[i].lat;

            }

            features.push(x[i]);

        };
        //console.log(features);


        return callback(features);
    }
}