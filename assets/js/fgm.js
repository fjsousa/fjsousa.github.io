//MapRags receives 2 sets of arguments
// "el", "map", where map is a 2d array or....
// "el", "map", "n", "m", where map is a 1d array and n and m are the numbers of rows and cols...
var MapRags = function (el, map, n, m, scale){

  if (!map[0].length && (!n || !m)) {
    alert('Inititalization error. Checks the logs.');
    console.log('1D array needs number of cols and rows. Usage:');
    console.log('new MapRags(map, rows, cols)');
    return;
  }

  var canvas = document.getElementById(el);
  if (canvas.getContext) {
    this.ctx = canvas.getContext('2d');

    this.w = canvas.width;
    this.h = canvas.height;

    if (this.h/n < 1 || this.w/m < 1)
      throw 'Cell size < 1. Increase Canvas size'

  } else {
    throw 'Couldn\'t get canvas context';
  }

  //flatten 2d array
  if (!!map[0].length) {
    this.n = map.length;
    this.m = map[0].length;
    this.update2D(map);
    //1d array init
  } else {
    this.n = n;
    this.m = m;
    this.map = map;
  }

  this.c1 = [255, 255, 255];
  this.c2 = [0, 0, 255];//blue
  this.c3 = [0, 255, 0];//green
  this.c4 = [255, 255, 0];//yellow
  this.c5 = [255, 0, 0];//red

  if (scale && scale.max !== undefined && scale.min !== undefined){
    this.max = scale.max;
    this.min = scale.min;
    return;
  }

  //If scale is undefined, get max and min from map
  this.max = Math.max.apply(null, this.map);
  this.min = Math.min.apply(null, this.map);
};

MapRags.prototype.update2D = function (map) {
  var map1d = [];
  this.map =  map1d.concat.apply([], map);//TODO throws errors with big 2d matrix
}

MapRags.prototype.render = function () {

  n = this.n;
  m = this.m;

  for (var i = 0; i < n; i++) {
    for (var j = 0; j < m; j++) {


      if (typeof this.map[i*this.m + j] !== "number") {
        throw 'Array element is not a number'
      }

      if (this.map[i*this.m + j] === Infinity){
        this.ctx.fillStyle = "rgb(0,0,0)";
      } else {

        var color = this.mapColor(this.map[i*this.m + j]);

        var r = color[0];
        var g = color[1];
        var b = color[2];

        this.ctx.fillStyle = "rgb(" + r + "," + g + "," + b + ")";
      }

      var dx = Math.floor(this.w / this.m);
      var dy = Math.floor(this.h / this.n);

      var y = dx * i;
      var x = dy * j;

      this.ctx.fillRect (x, y, dy, dx);

    }
  }
};

MapRags.prototype.mapColor = function(value) {

  var valueMax;
  var valueMin;
  var colorMax;
  var colorMin;

  if (value < this.max/4){
    valueMin = 0;
    valueMax = this.max/4;
    colorMax = this.c2;
    colorMin = this.c1;

  } else if (value < this.max*2/4){
    valueMin = this.max/4;
    valueMax = this.max*2/4;
    colorMax = this.c3;
    colorMin = this.c2;
  } else if (value < this.max*3/4){
    valueMin = this.max*2/4;
    valueMax = this.max*3/4;
    colorMax = this.c4;
    colorMin = this.c3;
  } else {
    valueMin = this.max*3/4;
    valueMax = this.max;
    colorMax = this.c5;
    colorMin = this.c4;
  }

  var mr = (colorMax[0] - colorMin[0])/(valueMax - valueMin);
  var mg = (colorMax[1] - colorMin[1])/(valueMax - valueMin);
  var mb = (colorMax[2] - colorMin[2])/(valueMax - valueMin);

  var br = colorMin[0] - mr * valueMin;
  var bg = colorMin[1] - mg * valueMin;
  var bb = colorMin[2] - mb * valueMin;

  var r = Math.round( mr * value + br);
  var g = Math.round( mg * value + bg);
  var b = Math.round( mb * value + bb);

  return [r, g, b];
};

MapRags.prototype.updateMap = function (map) {
  if (!!map[0].length)
    return this.update2D(map);//TODO update 2d breaks with many rows and cols

  this.map = map;
}

//            //N   NE   E  SE  S  SW   W  NW
var ncols = [ 0,   1,  1,  1, 0, -1, -1, -1];
var nrows = [ -1, -1,  0,  1, 1,  1,  0, -1];

var cols = 20;
var rows = 20;

//populate with zeros
var rosmap = rosmap(cols, rows);
var ndist = calcDist(ncols, nrows);


function initmap(cols, rows, value){

  var array = new Array(cols*rows);

  for (var i = 0;  i < array.length; i++) {
    array[i] = value;
  }

  array[Math.floor(cols/2) + Math.floor(rows/3)*cols] = 0;

  return array
}

function rosmap(cols, rows){

  var array = new Array(cols*rows);
  var r;

  for (var row = 0;  row < rows; row++) {
    for (var col = 0;  col < cols; col++) {

      if ( col > cols/3 && col < cols*2/3){
        if (row < rows*2/3 && row > rows/3){
          r = Math.random()*2;
          array[col + cols*row] = r >= 0.4 ? r : 0.4;
          continue;
        }
      }

      //cap
      r = Math.random();
      array[col + cols*row] = r >= 0.4 ? r : 0.4;

    }
  }

  return array;
}

function calcDist(ncol, nrow){
  var cellwd = cellht = 1;
  var array = [];

  for ( n = 0; n < ncol.length; n++ ){
    array[n] = Math.sqrt ( ncol[n] * cellwd * ncol[n] * cellwd + nrow[n] * cellht * nrow[n] * cellht );
  }

  return array;
}


//initialize stuff
var dumb, smart, dumb2, smart2;
$(document).ready(function () {

  if (document.getElementById('fgm-parallel')){
    var pRags = new MapRags('fgm-parallel', initmap(cols, rows, 5), rows, cols, {max: 20, min: 0});
    pRags.render();
    smart = new Smart('fgm-parallel');
  }

  if (document.getElementById('fgm-serial')){
    var sRags = new MapRags('fgm-serial', initmap(cols, rows, Infinity), rows, cols, {max: 20, min: 0});
    sRags.render();
    dumb = new Dumb('fgm-serial');
  }

  if (document.getElementById('fgm-parallel-twin')){
    var ptwinRags = new MapRags('fgm-parallel-twin', initmap(cols, rows, 5), rows, cols, {max: 20, min: 0});
    ptwinRags.render();
    smart2 = new Smart('fgm-parallel-twin');
  }

  if (document.getElementById('fgm-serial-twin')){
    var stwinRags = new MapRags('fgm-serial-twin', initmap(cols, rows, Infinity), rows, cols, {max: 20, min: 0});
    stwinRags.render();
    dumb2 = new Dumb('fgm-serial-twin');
  }
});
////////////////////
//Serial
////////////////////
var Dumb = function (el){
  this.el = el;
  this.dumbLock = false;
};

Dumb.prototype.run = function() {

  var that = this;

  if (this.dumbLock)
    return;

  this.dumbLock = true;

  var rags;
  var ignitionMap = initmap(cols, rows, Infinity);
  var tn = t = 0;

  var rags = new MapRags(this.el, ignitionMap, rows, cols, {max: 20, min: 0});

  function dumbSpatialLoop(){

    t = tn;
    tn = Infinity;


    //Spatial loop that looks for active cells, ie, cells with
    //ignition time = t
    for ( row = 0; row < rows; row++){
      for ( col = 0; col < cols; col++){
        var idx = col + cols*row;

        //Update tn, so that tn is the minimum ignition time for all cells,
        //in a given iteration
        if ( ignitionMap[idx] > t && tn > ignitionMap[idx] ){

          tn = ignitionMap[idx];
          continue;
        }

        //skips cells that already burned
        if ( ignitionMap[idx] !== t )
          continue;

        //propagate fire for all 8 neighours
        for (var n = 0; n < 8; n++){

          //neighbour index calc
          var ncol = col + ncols[n];
          var nrow = row + nrows[n];
          var nidx = ncol + nrow*cols;

          //Check if neighbour is inbound
          if ( !(nrow >= 0 && nrow < rows && ncol >= 0 && ncol < cols))
            continue;

          // skip if cell has already burned
          if ( ignitionMap[nidx] < t )
            continue;

          //Compute neighbour cell ignition time, based on the propagation speed
          // tcell = current_time + cell distance / flame_speed
          igntime = t + ndist[n] / rosmap[idx];


          //Update ignition time in the map only if the the current time is smaller
          if(igntime < ignitionMap[nidx]){
            ignitionMap[nidx] = igntime;
          }

          //Update tn
          if( igntime < tn )
            tn = igntime;
        }
      }
    }
  }

  var itt = 0;
  (function call(){
    setTimeout(function () {

      dumbSpatialLoop();
      if (itt++ % 1 === 0){

        rags.updateMap(ignitionMap);
        rags.render();
      }

      if (tn === Infinity) {
        that.dumbLock = false;
        return;
      }

      call();

    }, 100);
  })();
}

////////////////////
//Parallel
////////////////////
var Smart = function(el){
  this.el = el;
  this.smartLock = false;
}

Smart.prototype.run = function() {
  var that = this;
  if (this.smartLock)
    return;

  this.smartLock = true;

  var ignitionMap = initmap(cols, rows, 5);
  var monitor;

  var rags = new MapRags(this.el, ignitionMap, rows, cols, {max: 20, min: 0});

  function smartSpatialLoop(){

    for ( row = 0; row < rows; row++){
      for ( col = 0; col < cols; col++){
        var idx = col + cols*row;

        //skip ignition cell
        if (ignitionMap[idx] === 0)
          continue;

        var minArray = [];
        for (var n = 0; n < 8; n++){

          //neighbour index calc
          var ncol = col + ncols[n];
          var nrow = row + nrows[n];
          var nidx = ncol + nrow*cols;

          //Check if neighbour is inbound
          if ( !(nrow >= 0 && nrow < rows && ncol >= 0 && ncol < cols))
            continue;

          // compute igniton time considering that the flame moves from the neighbour to the
          // center cell
          var igntime = ignitionMap[nidx] + ndist[n] / rosmap[nidx];
          minArray.push(igntime);

        }
        //associate the minimum of the propagation times to the ignition time
        // of the center cell
        ignitionMap[idx] = Math.min.apply(null, minArray);;

      }
    }

  }

  var itt=0;
  (function call(){
    setTimeout(function () {

      smartSpatialLoop();
      if (itt++ % 1 === 0){

        rags.updateMap(ignitionMap);
        rags.render();
      }

      if (monitor === ignitionMap[0]){
        that.smartLock = false;
        return;
      }

      monitor = ignitionMap[0];

      call();

    }, 100);
  })();
}
