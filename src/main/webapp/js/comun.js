function getURL() {
	var protocol = location.protocol + '//';
	var hostname = location.hostname + ':' + location.port + '/';
	var context = location.pathname.substr(1, location.pathname.substr(1).indexOf('/')) + '/';
	return protocol + hostname + context;
}

function getURLEscaped() {
	var protocol = location.protocol + '//';
	var hostname = location.hostname + '\\:' + location.port + '/';
	var context = location.pathname.substr(1, location.pathname.substr(1).indexOf('/')) + '/';
	return protocol + hostname + context;
}