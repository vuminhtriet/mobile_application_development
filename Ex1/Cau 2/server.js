var express = require('express');
var app = express();
var fs = require("fs");
var lat1,long1,lat2,long2 = 0;
var status = "OK";
var distance = null;

function getDistanceFromLatLonInKm(lat1,lon1,lat2,lon2) {
    var R = 6371; // Radius of the earth in km
    var dLat = deg2rad(lat2-lat1);  // deg2rad below
    var dLon = deg2rad(lon2-lon1); 
    var a = 
      Math.sin(dLat/2) * Math.sin(dLat/2) +
      Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
      Math.sin(dLon/2) * Math.sin(dLon/2)
      ; 
    var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
    var d = R * c; // Distance in km
    return d;
  }
  
  function deg2rad(deg) {
    return deg * (Math.PI/180)
  }

app.get('/lat1='+':lat1'+'&long1='+':long1'+'&lat2='+':lat2'+'&long2='+':long2', function (req, res) {
    lat1 = Number(req.params.lat1);
    long1 = Number(req.params.long1);
    lat2 = Number(req.params.lat2);
    long2 = Number(req.params.long2);
    distance = getDistanceFromLatLonInKm(lat1,long1,lat2,long2)
    if (distance){
        if (Math.abs(lat1) <= 90 && Math.abs(lat2) <= 90 && Math.abs(long1) <= 180 && Math.abs(long2) <= 180){
            status = "OK";
        }
        else{
            distance = null;
            status = "Wrong latlng";
        }
    }
    else{
        if (distance == 0){
            status = "OK";
        }
        else{
            status = "Wrong latlng";
        }
    }
    
    fs.readFile( __dirname + "/" + "distance.json", 'utf8', function (err, data) {
        var user = {
            "lat1" : lat1,
            "long1": long1,
            "lat2" : lat2,
            "long2": long2,
            "distance": distance,
            "status": status
         }
        data = JSON.parse( data );
        data["lat1"] = user["lat1"];
        data["long1"] = user["long1"];
        data["lat2"] = user["lat2"];
        data["long2"] = user["long2"];
        data["distance"] = user["distance"];
        data["status"] = user["status"];
        console.log( data );
        res.end( JSON.stringify(data));
    });
})

var server = app.listen(process.env.PORT || 8081, function () {

  var host = server.address().address
  var port = server.address().port

  console.log("Example app listening at http://%s:%s", host, port)

})