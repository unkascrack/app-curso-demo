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