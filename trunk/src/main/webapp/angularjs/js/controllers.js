// TODO: lista de cosas faltan por realizar
//  - actualizar a la versión ngGrid 2.0.6 (corrige bug totalServerItems)
//  - revisar las búsquedas de listados el orden podría ser datos de página y si llegamos a datosPagina.length = pageSize entonces buscar el total
//  - construir una clase javascript comun que gestione todos los controles de los grid
//  - sistema de autenticación

app.controller('RootController', ['$scope', '$location', function($scope, $location) {
	$scope.$on('event:loginRequired', function() {
		console.log('login required');
		$location.path('/login');
	});
	$scope.$on('event:resourceNotFound', function() {
		$location.path('/400');
	});
	$scope.$on('event:internalServerError', function() {
		$location.path('/500');
	});
}]);

app.controller('ListCursoController', ['$scope', 'CursoResource', function($scope, CursoResource) {

	$scope.cursos = CursoResource.query({t:getTimestamp()});
	
    $scope.gridOptions2 = {
        data: 'cursos',
        columnDefs: [{field:'activo', displayName:'Activo', sortable:false}, {field:'titulo', displayName:'Título'}, {field:'nivel', displayName:'Nivel'}, {field:'horas', displayName:'Horas'}, {field:'temario', displayName:'Temario'}],
        i18n: 'sp'
    };
	
    $scope.pagingOptions = {
        pageSizes: [2, 5, 10],
        pageSize: 2,
        currentPage: 1
    };
    $scope.filterOptions = {
        activo: null,
		titulo: null
	};
	$scope.sortInfo = {
		fields: [], 
		columns: [], 
		directions: []
	};
    $scope.gridOptions = {
        data: 'myData',
        columnDefs: [{field:'activo', displayName:'Activo', sortable:false}, {field:'titulo', displayName:'Título'}, {field:'nivel', displayName:'Nivel'}, {field:'horas', displayName:'Horas'}, {field:'temario', displayName:'Temario'}],
        enablePaging: true,
		showFooter: true,
		totalServerItems: 'totalServerItems',
        pagingOptions: $scope.pagingOptions,
        i18n: 'sp',
        useExternalSorting: true,
        sortInfo: $scope.sortInfo
    };
    
    search($scope.filterOptions, $scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage);
	
    function search(filter, pageSize, page, orderBy, orderSort) {
    	searchNuDataAsync(filter, function() {
	    	if ($scope.totalServerItems === 0) {
	    		$scope.myData = [];
	    	}
	    	else {
    			searchDataAsync(filter, pageSize, page, orderBy, orderSort);
	    	}
    	});
    }
    
    function searchNuDataAsync(filter, callback) {
        setTimeout(function () {
        	var params = {};
        	params.t = getTimestamp();
        	params.activo = filter.activo;
        	params.titulo = filter.titulo;
	        CursoResource.total(trim(params), function success(data) {
	        	var total = parseInt(data[0]);
	        	$scope.totalServerItems = total;
	        	if (callback !== null) {
	        		callback();
	        	}
	        });
        }, 100);
    };
    
    function searchDataAsync(filter, pageSize, page, orderBy, orderSort) {
        setTimeout(function () {
        	var params = {};
        	params.t = getTimestamp();
        	params.activo = filter.activo;
        	params.titulo = filter.titulo;
        	params.pageSize = pageSize;
        	params.page = page;
        	params.orderBy = orderBy;
        	params.orderSort = !orderSort ? null : orderSort.toLowerCase() === 'asc' ? true : false;
        	CursoResource.query(trim(params), function success(data) {
        		$scope.myData = data;
        		if (!$scope.$$phase) {
		            $scope.$apply();
		        }
        	});
        }, 100);
    }; 

    var filtering = false;
    
	$scope.filtrar = function(activo, titulo) {
		var oldVal = $scope.filterOptions;
		if (oldVal.activo !== activo || strcmp(titulo, oldVal.titulo) !== 0) {
			filtering = true;
			$scope.filterOptions.activo = activo;
			$scope.filterOptions.titulo = titulo;
			$scope.pagingOptions.currentPage = 1;
			search($scope.filterOptions, $scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.sortInfo.fields[0], $scope.sortInfo.directions[0]);
		}
	};
    
    $scope.$watch('pagingOptions', function (newVal, oldVal) {
        if (!filtering && newVal !== oldVal && (newVal.currentPage !== oldVal.currentPage || newVal.pageSize !== oldVal.pageSize)) {
        	if (newVal.pageSize !== oldVal.pageSize) {
        		$scope.pagingOptions.currentPage = 1;
        	}
        	searchDataAsync($scope.filterOptions, $scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.sortInfo.fields[0], $scope.sortInfo.directions[0]);
        }
        if (filtering) {
        	filtering = false;
        }
         
    }, true);
    
	$scope.$watch('sortInfo', function (newVal, oldVal) {
        if (newVal !== oldVal) {
        	searchDataAsync($scope.filterOptions, $scope.pagingOptions.pageSize, $scope.pagingOptions.currentPage, $scope.sortInfo.fields[0], $scope.sortInfo.directions[0]);
        }
    }, true);
}]);

app.controller('NewCursoController', ['$scope', '$location', 'CursoResource', function($scope, $location, CursoResource) {
	$scope.curso = new CursoResource();
	
	$scope.save = function() {
		$scope.curso.$save(function(curso) {
			$location.path('/cursos/'+curso.id);
		});
	};
	$scope.cancel = function() {
		$location.path('/cursos');
	};
}]);

app.controller('ShowCursoController', ['$scope', '$location', '$routeParams', 'CursoResource', function($scope, $location, $routeParams, CursoResource) {
	$scope.curso = CursoResource.get({id:$routeParams.id});
	
	$scope.edit = function() {
		$location.path('/cursos/'+$routeParams.id+'/edit');
	};
	$scope.remove = function() {
		$scope.curso.$remove(function(curso) {
			$location.path('/cursos');
		});
	};
	$scope.cancel = function() {
		$location.path('/cursos');
	};
}]);

app.controller('EditCursoController', ['$scope', '$location', '$routeParams', 'CursoResource', function($scope, $location, $routeParams, CursoResource) {
	$scope.curso = CursoResource.get({id:$routeParams.id},
			function success(data) {
				console.log(data);
				if (data.activo == null) {
					alert('no data');
					$location.path('/cursos');
				}
			},
			function error(data) {
				alert('error');
			});
	$scope.save = function() {
		$scope.curso.$save(function(curso) {
			$location.path('/cursos');
		});
	};
	$scope.cancel = function() {
		console.log($routeParams.id);
		$location.path('/cursos/'+$routeParams.id);
	};
}]);

app.controller('ListTutorController', ['$scope', 'TutorResource', function($scope, TutorResource) {
	$scope.tutores = TutorResource.query();
}]);

app.controller('ListNivelController', ['$scope', function($scope) {
	$scope.niveles = ['BASICO', 'INTERMEDIO', 'AVANZADO'];
}]);