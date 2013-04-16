function mm_user(callback) {
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for mm_recurso';
    }
    var url = 'http://rub21.github.com/report_top_us/app/listtop50user.json?callback=callback';
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

function mm_file_user(file_user,callback) {
    //alert('mm_edit');
    if (typeof reqwest === 'undefined') {
        throw 'CSV: reqwest required for mm_edit';
    }
    var url = 'http://rub21.github.com/report_top_us/json_app/'+file_user+'.json?callback=callback';
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