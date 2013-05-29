app.config(['$routeProvider', function($routeProvider) {
	$routeProvider
		.when('/login', {templateUrl:'templates/login.html'})
		.when('/400', {templateUrl:'templates/error400.html'})
		.when('/500', {templateUrl:'templates/error500.html'})
		.when('/cursos', {templateUrl:'templates/cursos/list.html'})
		.when('/cursos/new', {controller: 'NewCursoController', templateUrl:'templates/cursos/edit.html'})
		.when('/cursos/:id', {templateUrl:'templates/cursos/show.html'})
		.when('/cursos/:id/edit', {controller: 'EditCursoController', templateUrl:'templates/cursos/edit.html'})
		.otherwise({redirectTo: '/cursos'});
}]);