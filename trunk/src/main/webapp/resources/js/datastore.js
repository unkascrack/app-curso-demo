App.Store = DS.Store.extend({
	revision: 12,
	adapter: DS.RESTAdapter.extend({
		url: 'http://localhost:8080/',
		namespace: 'curso-demo/rest',
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