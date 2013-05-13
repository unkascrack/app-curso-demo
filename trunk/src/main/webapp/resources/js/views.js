App.ClickableView = Ember.View.extend({
	content: null,
	click: function(event) {
		var url = 'docs/'+this.content.get('id')+'/'+this.content.get('temario');
		window.open(url, '_blank');
	}
});

App.UploadFileView = Ember.TextField.extend({
	type: 'file',
    attributeBindings: ['name'],
    change: function(evt) {
		var self = this;
		var input = evt.target;
		if (input.files && input.files[0]) {
        	var reader = new FileReader();
        	reader.onload = function(e) {
				var fileToUpload = e.srcElement.result;
				var name = self.get('name');
				self.get('controller').set(name, fileToUpload);
				self.get('controller').get('content').set(name, input.files[0].name);
        	};
        	reader.readAsDataURL(input.files[0]);
      	}
	}
});