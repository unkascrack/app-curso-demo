App.Store = DS.Store.extend({
	revision: 12,
	adapter: DS.RESTAdapter.extend({
		url: getURL(),
		namespace: 'rest',
		bulkCommit: false,
		serializer: DS.RESTSerializer.extend({
			init: function() {
				this._super();
		        this.map("App.Curso", {tutor: { embedded: "load" }});
			}
		})
	})
});

DS.RESTAdapter.configure('plurals', {
	tutor: 'tutores',
	curso: 'cursos'
});

function getURL() {
	var protocol = location.protocol + '//';
	var hostname = location.hostname + ':' + location.port + '/';
	var context = location.pathname.substr(1, location.pathname.substr(1).indexOf('/')) + '/';
	return protocol + hostname + context;
}