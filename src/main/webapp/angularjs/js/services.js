var services = angular.module('myApp.services', ['ngResource']);

services.config(['$httpProvider', function ($httpProvider) {
	$httpProvider.responseInterceptors.push('errorHttpInterceptor');
}]);

services.factory('errorHttpInterceptor', ['$q', '$location', '$rootScope', 
	function ($q, $location, $rootScope) {
		return function (promise) {
			return promise.then(function (response) {
				return response;
			}, function (response) {
				if (response.status === 401) {
					$rootScope.$broadcast('event:loginRequired');
				} else if (response.status >= 400 && response.status < 500) {
					$rootScope.$broadcast('event:resourceNotFound');
				} else if (response.status >= 500) {
					$rootScope.$broadcast('event:internalServerError');
				}
				return $q.reject(response);
			});
		};
}]);

services.factory('CursoResource', ['$resource', function($resource) {
	return $resource(getURLEscaped() + 'rest/jackson/secured/cursos/:id', 
			{id: '@id'},
			{total: {method:'GET', params:{total:true}, isArray:false}});
}]);

services.factory('TutorResource', ['$resource', function($resource) {
	return $resource(getURLEscaped() + 'rest/jackson/tutores/:id', {id: '@id'});
}]);