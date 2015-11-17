var app = angular.module('myApp', ['myApp.controllers', 'ngRoute']);

app.config(function($routeProvider, $locationProvider) {
    $routeProvider
	    .when('/', {
	        templateUrl: 'partials/main.html'
	    })
        .when('/view1', {
            controller: 'Controller1',
            templateUrl: 'partials/view1.html'
        })
        .when('/view2', {
            controller: 'Controller2',
            templateUrl: 'partials/view2.html'
        })
        .when('/register-user.html', {
        	templateUrl: 'partials/register-user.html'
        });
        $locationProvider.html5Mode(true); //activate HTML5 Mode
});
