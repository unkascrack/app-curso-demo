App.IndexController = Ember.ArrayController.extend();

App.CursosController = Ember.ArrayController.extend();

App.CursosIndexController = Ember.ArrayController.extend();

App.CursosNewController = Ember.ObjectController.extend({
	content: null,
	temario: null,
	tutores: [],
	save: function() {
	    this.content.set('attachment', this.temario);
		this.get('store.defaultTransaction').commit();
	    this.transitionToRoute('cursos');
	},
	cancel: function() {
		this.content.deleteRecord();
		this.get('store.defaultTransaction').commit();
		this.transitionToRoute('cursos');
	}
});

App.CursosEditController = Ember.ObjectController.extend({
	content: null,
	temario: null,
	tutores: [],
	save: function() {
		this.content.set('attachment', this.temario);
		this.get('store.defaultTransaction').commit();
		this.transitionToRoute('cursos');
	},
	cancel: function() {
		this.get('store.defaultTransaction').rollback();
		this.transitionToRoute('cursos');
	}
});