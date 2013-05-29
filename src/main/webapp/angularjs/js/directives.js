var directives = angular.module('myApp.directives', []);

directives.directive('focus', function() {
	return {
		link: function(scope, element, attrs, controller) {
			element[0].focus();
		}
	};
});

directives.directive('loading', ['$rootScope', function($rootScope) {
	return {
		link: function(scope, element, attrs) {
			element.addClass('hide');
			$rootScope.$on('$routeChangeStart', function() {
				element.removeClass('hide');
			});
			$rootScope.$on('$routeChangeSuccess', function() {
				element.addClass('hide');
			});
		}
	};
}]);

directives.directive('datepicker', function() {
	return {
		restrict: 'A',
		require: '?ngModel',
		scope: {select: '&'},
		link: function(scope, element, attrs, ngModel) {
			if (!ngModel) return;
			var optionsObj = {};
			optionsObj.dateFormat = 'dd/mm/yy';
			var updateModel = function(dateTxt) {
				scope.$apply(function () {
					ngModel.$setViewValue(dateTxt);
				});
			};
			optionsObj.onSelect = function(dateTxt, picker) {
			updateModel(dateTxt);
			if (scope.select) {
				scope.$apply(function() {
					scope.select({date: dateTxt});
				});
			}
			};
			ngModel.$render = function() {
				element.datepicker('setDate', ngModel.$viewValue || '');
			};
			element.datepicker(optionsObj);
		}
	};
});