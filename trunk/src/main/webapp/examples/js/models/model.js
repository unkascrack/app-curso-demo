App.Store = DS.Store.extend({
	revision: 12
	//, adapter: 'App.LSAdapter'
});

App.LSAdapter = DS.LSAdapter.extend({
	namespace: 'todos-emberjs'
});

App.Nivel = DS.Model.extend({
	nivel: DS.attr('string')
});

App.Tutor = DS.Model.extend({
	id: DS.attr('number'),
	nombre: DS.attr('string'),
	apellidos: DS.attr('string'),
	nombreCompleto: function() {
		return this.get('nombre') + ' ' + this.get('apellidos');
	}.property('nombre', 'apellidos')
});

App.Curso = DS.Model.extend({
    id: DS.attr('number'),
    activo: DS.attr('boolean'),
    tutor: DS.belongsTo('App.Tutor'),
    titulo: DS.attr('string'),
    nivel: DS.attr('string'),
    horas: DS.attr('number'),
    nombreTemario: DS.attr('string'),
    contenidoTemario: DS.attr('string')
});
