'use strict';

ticketingControllers.controller('TicketsCtrl', ['$rootScope', '$scope', '$routeParams', '$http',
    function($rootScope, $scope, $routeParams, $http) {
        $scope.createTicket = function() {

        	$scope.sessionId = $routeParams.sessionId;
            $http.post('ticket', {
            		"id": null,
                    "sessionId": $scope.sessionId,
                    "type": $scope.type,
                    "place": $scope.place
            })
            .success(function(data, status, headers, config) {
                console.log('data = ' , data);
                $scope.id = '';
                $scope.sessionId = '';
                $scope.type = '';
                $scope.newTicketId = data.id;
                if(data.error !== undefined) {
                	$scope.newTicketId = undefined;
                	$scope.errorMessage = data.cause;
                }
            })
            .error(function(data, status, headers, config) {
            	$scope.newTicketId = undefined;
            	$scope.errorMessage = "Internal server error. Please check that all fields in valid state.";
                console.log('error: data = ' , data);
            });
        };
        
        $scope.searchTicket = function() {
            $http.get('ticket/' + $scope.searchTicketId)
            .success(function(data, status, headers, config) {
                console.log('data = ' , data);
                $scope.ticket = ticet;
            })
            .error(function(data, status, headers, config) {
                console.log('error: data = ' , data);
            });
        };
    }
]);