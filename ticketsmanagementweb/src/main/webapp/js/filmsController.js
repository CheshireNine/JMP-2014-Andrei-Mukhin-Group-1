'use strict';

ticketingControllers.controller('FilmsCtrl', ['$rootScope', '$scope', '$routeParams', '$http',
    function($rootScope, $scope, $routeParams, $http) {        
        $scope.listFilms = function() {
            $http.get('film')
            .success(function(data, status, headers, config) {
            	if (data != undefined) {
                    $scope.films = data;
                } else {
                    $scope.films = [];
                }
            })
            .error(function(data, status, headers, config) {
                console.log('error: data = ' , data);
            });
        };

        $scope.orderProp = 'name';
        $scope.listFilms();
    }
]);