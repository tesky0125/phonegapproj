var express = require('express');
var http = require('http');
var path = require('path');
// var routes = require('./routes');
// var ejs = require('ejs');

var app = express();
app.set('port', process.env.PROT || 3000);
// app.set('views', path.join(__dirname, 'views'));
// app.engine('.html', ejs.__express);
// app.set('view engine', 'html');
app.use(express.static(path.join(__dirname, 'jquery')));

app.get('/data2', function(req, res){
  res.send({'key2':'value2'});
});
app.get('/data', function(req, res){
  res.send({'key':'value'});
});

var serverSocket = http.createServer(app).listen(app.get('port'), function(){
  console.log('listening on *:3000');
});