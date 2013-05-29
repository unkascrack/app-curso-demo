//TODO: actualizar a la versión ngGrid 2.0.6 (corrige bug totalServerItems)
var app = angular.module('myApp', ['ngGrid', 'myApp.directives', 'myApp.services']);

function empty(data) {
	return data === null || data === '' || typeof data === 'undefined' || (data instanceof Object && Object.keys(data).length == 0);
}

function strcmp(a, b) {
	if (empty(a) && empty(b)) return 0;
    if (!empty(a) && empty(b) ||  a.toString() < b.toString()) return -1;
    if (empty(a) && !empty(b) || a.toString() > b.toString()) return 1;
    return 0;
}

function trim(data) {
	var y;
	for ( var x in data) {
		y = data[x];
		if (empty(y)) {
			delete data[x];
		}
		if (y instanceof Object) {
			y = trim(y);
		}
	}
	return data;
}

function getTimestamp() {
	return new Date().getTime();
}