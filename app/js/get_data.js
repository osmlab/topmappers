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
            features.push(x[j]);
        };
        //console.log(features);
        return callback(features);
    }
};





/*

function mm_edit(callback) {
    //alert('mm_edit');
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for mm_edit';
    }
    var url = 'http://rub21.github.com/report_top_us/json/user103107.json?callback=callback';
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });

    function response(x) {
     console.log(x)
        var features = [];

        for (var j = 0; j < x.length-75; j++) {

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

            features.push(x[j]);

        };
 

        return callback(features);
    }
}
*/

function mm_file_user(file_user,callback) {
    //alert('mm_edit');
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for mm_edit';
    }
    var url = 'http://rub21.github.com/report_top_us/json_app/'+file_user+'?callback=callback';
    console.log(url);
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });

    function response(x) {
    
  console.log(x);
        return callback(x);
    }
}