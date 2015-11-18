var express = require('express');
var https = require('https');
var http = require('http');
var path = require('path');

var app = express();
app.set('port', process.env.PROT || 3000);
app.use('/jquery', express.static(path.join(__dirname, 'jquery')));

console.log('dirname:' + __dirname);

/*app.use('/admin', function(req, res, next) {
    // GET 'http://localhost:3000/admin/new'
    console.log(req.originalUrl); // '/admin/new'
    console.log(req.baseUrl); // '/admin'
    console.log(req.path); // '/new'
    next();
});*/

app.use(function(req, res, next) {
    console.log('* filter');
    next();
})

/*var router = express.Router();
router.all('*', function() {
    console.log('* filter');
});
router.get('/', function(req, res, next) {
    next();
})
app.use(router);*/

app.all('/', function(req, res, next) {
    console.log('Accessing the section ...');
    next(); // pass control to the next handler
});
app.get('/', function(req, res) {
    res.send('hello world!');
});
app.post('/', function(req, res) {
    res.send('POST request to homepage');
});
app.put('/', function(req, res) {
    res.send('PUT request to homepage');
});

/*app.param('id', function(req, res, next, id) {
    console.log('CALLED ONLY ONCE');
    next();
});
app.get('/:id', function(req, res, next) {
    console.log('although this matches');
    next();
});
app.get('/:id', function(req, res) {
    console.log('and this matches too');
    res.end();
});*/

/*app.get('/data', function(req, res) {
    res.send({
        'key': 'value'
    });
});
app.get('/data2', function(req, res) {
    res.send({
        'key2': 'value2'
    });
});*/

/*app.get('/file/:name', function(req, res, next) {
    var options = {
        root: __dirname + '/file/',
        dotfiles: 'deny',
        headers: {
            'x-timestamp': Date.now(),
            'x-sent': true
        }
    };
    var fileName = req.params.name;
    res.sendFile(fileName, options, function(err) {
        if (err) {
            console.log(err);
            res.status(err.status).end();
        } else {
            console.log('Sent:', fileName);
        }
    });
})*/

app.route('/book')
    .get(function(req, res) {
        res.send('Get a random book');
    })
    .post(function(req, res) {
        res.send('Add a book');
    })
    .put(function(req, res) {
        res.send('Update the book');
    });


var server = app.listen(app.get('port'), function(){
    var host = server.address().address;
    var port = server.address().port;

    console.log('Example app listening at http://%s:%s', host, port);
});

// var serverSocket = http.createServer(app).listen(app.get('port'), function() {
//     console.log('listening on *:3000');
// });

// var serverSocket = https.createServer(options, app).listen(app.get('port'), function(){
//   console.log('listening on *:3000');
// });
