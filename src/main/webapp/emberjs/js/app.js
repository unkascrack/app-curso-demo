App = Ember.Application.create({
	LOG_TRANSITIONS: true
});

//Ember.LOG_BINDINGS = true;

Ember.onerror = function(error) {
	console.log(error.stack);
	alert(error);
};

Ember.TextField.reopen({
	attributeBindings: ['required','min','max']
});

$(document).ready(function(){
	$(".numeric").numeric({ negative: false, decimal: false });
});

Ember.Select.reopen({
	attributeBindings: ['required']
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