App = Ember.Application.create();

App.Router.map(function() {
  // put your routes here
});

App.IndexRoute = Ember.Route.extend({
  model: function() {
    return ['red', 'yellow', 'blue'];
  }
});

App.ApplicationController = Ember.Controller.extend({
  firstName: "Trek",
  lastName: "Glowacki"
});


App.Tutor = Ember.Object.extend({
	id: null,
	nombre: null,
	apellidos: null,

	nombreCompleto: function() {
		return this.get('firstName') + " " + this.get('lastName');
	}.property('nombre', 'apellidos')
});

App.Curso = Ember.Object.extend({
	id: null,
	activo: false,
	tutor: null,
	titulo: null,
	nivel: null,
	horas: null,
	nombreTemario: null,
	contenidoTemario: null
});
