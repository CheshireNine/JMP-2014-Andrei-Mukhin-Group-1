'use strict';

ticketingControllers.controller('FilmCtrl', ['$rootScope', '$scope', '$routeParams', '$http',
	function($rootScope, $scope, $routeParams, $http) {        
	    $scope.getFilm = function() {
	        $http.get('film/' + $routeParams.filmId)
	        .success(function(data, status, headers, config) {
	        	if (data != undefined) {
	                $scope.film = data;
	            } else {
	                $scope.film = {};
	            }
	        })
	        .error(function(data, status, headers, config) {
	            console.log('error: data = ' , data);
	        });
	    };
	
	    $scope.orderProp = 'time';
	    $scope.getFilm();
	}
]);