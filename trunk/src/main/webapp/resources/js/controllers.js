App.IndexController = Ember.ArrayController.extend();

App.CursosController = Ember.ArrayController.extend();

App.CursosIndexController = Ember.ArrayController.extend();

App.CursosNewController = Ember.ObjectController.extend({
	content: null,
	temario: null,
	tutores: [],
	save: function() {
	    this.content.set('attachment', this.temario);
	    this.content.on('didCreate', this, function() {
	    	this.transitionToRoute('cursos');
	    });
	    this.content.save();
	    this.get('store').commit();
		//this.get('store.defaultTransaction').commit();
		//this.transitionToRoute('cursos');
	},
	cancel: function() {
		this.content.deleteRecord();
		this.get('store').commit();
		//this.get('store.defaultTransaction').commit();
		this.transitionToRoute('cursos');
	}
});

App.CursosEditController = Ember.ObjectController.extend({
	content: null,
	temario: null,
	tutores: [],
	save: function() {
		this.content.set('attachment', this.temario);
	    this.content.on('didUpdate', this, function() {
	    	this.transitionToRoute('cursos');
	    });
	    this.content.save();
	    this.get('store').commit();
		//this.get('store.defaultTransaction').commit();
		//this.transitionToRoute('cursos');
	},
	cancel: function() {
		//this.get('transaction').rollback();
		this.get('store.defaultTransaction').rollback();
		this.transitionToRoute('cursos');
	}
});