'use strict';

/* App Module */

var ticketingApp = angular.module('ticketingApp', [
	'ngRoute',
	'ticketingControllers'
]);

ticketingApp.config(['$routeProvider',
	function($routeProvider) {
		$routeProvider.
			when('/home', {
				templateUrl: 'partials/home.html',
				controller: 'FilmsCtrl'
			}).
			when('/film', {
				templateUrl: 'partials/film.html',
				controller: 'FilmCtrl'
			}).
			when('/film/:filmId', {
				templateUrl: 'partials/film.html',
				controller: 'FilmCtrl'
			}).
			when('/ticket/:sessionId', {
				templateUrl: 'partials/ticket.html',
				controller: 'TicketsCtrl'
			}).
			otherwise({
				redirectTo: '/home'
			});
	}
]);