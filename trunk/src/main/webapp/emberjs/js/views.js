App.ClickableView = Ember.View.extend({
	content: null,
	click: function(event) {
		var url = getURL() + 'docs/'+this.content.get('id')+'/'+this.content.get('temario');
		window.open(url, '_blank');
	}
});

App.SorterView = Ember.View.extend({
	content: null,
	column: null,
	click: function(event) {
		this.get('controller').set('orderBy', this.column);
		var orderSort = this.get('controller').get('orderSort');
		this.get('controller').set('orderSort', !orderSort);
	}
});