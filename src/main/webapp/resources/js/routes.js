App.Router.map(function() {
	this.route('cursos', { path: '/cursos' });
	this.route('cursos.new', { path: '/cursos/new' });
    this.route('cursos.edit', { path: '/cursos/:curso_id/edit' });
});

App.IndexRoute = Ember.Route.extend({
	redirect: function() {
		this.transitionTo('cursos');
	}
});

App.CursosRoute = Ember.Route.extend({
	model: function(params) {
		this.get('store.defaultTransaction').rollback();
        return App.Curso.find({ activo: true });
    }
});

App.CursosNewRoute = Ember.Route.extend({
	content: null,
	temario: null,
	renderTemplate: function(){
		this.render('cursos/new', {into:'application'});
	},
	setupController: function(controller, model) {
		controller.set('content', App.Curso.createRecord());
		controller.set('tutores', App.Tutor.find());
	}
});

App.CursosEditRoute = Ember.Route.extend({
	content: null,
	temario: null,
	renderTemplate: function(){
		this.render('cursos/edit', {into:'application'});
	},
	setupController: function(controller, model) {
		controller.set('content', App.Curso.find(model.id));
		controller.set('tutores', App.Tutor.find());
	}
});