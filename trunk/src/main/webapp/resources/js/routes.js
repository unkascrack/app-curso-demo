App.Router.map(function() {
	this.resource('cursos', { path: '/cursos' }, function() {
		this.route('new', { path: 'new' }),
	    this.route('edit', { path: ':curso_id/edit' });
	});
});

App.IndexRoute = Ember.Route.extend({
	redirect: function() {
		this.transitionTo('cursos');
	}
});

App.CursosRoute = Ember.Route.extend({
	model: function(params) {
		//this.get('store.defaultTransaction').rollback();
        return App.Curso.find({ activo: true });
    }
/*
	setupController: function(controller, model) {
		//this.get('store.defaultTransaction').rollback();
		controller.set('content', App.Curso.find({ activo: true }));
	}
*/	
});

App.CursosIndexRoute = Ember.Route.extend({
/*	
	model: function(params) {
		//this.get('store.defaultTransaction').rollback();
		return App.Curso.find({ activo: true });
    },
*/
	setupController: function(controller, model) {
		//this.get('store.defaultTransaction').rollback();
		controller.set('content', App.Curso.find({ activo: true }));
	},
	renderTemplate: function(){
		this.render('cursos', {into:'application'});
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