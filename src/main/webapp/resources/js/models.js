App.niveles = ['BASICO', 'INTERMEDIO', 'AVANZADO'];

App.Tutor = DS.Model.extend({
	nombre: DS.attr('string'),
	apellidos: DS.attr('string'),
	nombreCompleto: function() {
		return this.get('nombre') + ' ' + this.get('apellidos');
	}.property('nombre', 'apellidos')
});

App.Curso = DS.Model.extend({
	activo: DS.attr('boolean'),
	tutor: DS.belongsTo('App.Tutor'),
	titulo: DS.attr('string'),
	nivel: DS.attr('string'),
	horas: DS.attr('number'),
	temario: DS.attr('string'),
	attachment: DS.attr('string')
});