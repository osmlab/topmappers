
function mm_data(callback) {
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for mm_recurso';
    }
    var url = '../app/Slist.json';
    reqwest({
        url: url,
        type: 'jsonp',
        jsonpCallback: 'callback',
        success: response,
        error: response
    });

    function response(x) {
        console.log(x);
           var features = [];
        for (var i = 0; i < x.length; i++) {
            x[i]['properties'] = {};
            x[i].properties['marker-size'] = 'large';
            x[i].properties['marker-symbol'] = type_icon;
            x[i].properties['marker-color'] = '#000000';

            features.push(x[i]); 
            consle.log(features);

        }


        return callback(features);
    }
}