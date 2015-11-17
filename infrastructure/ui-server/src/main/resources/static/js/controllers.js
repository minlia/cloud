'use strict'

var myAppControllers = angular.module('myApp.controllers', []);

myAppControllers.controller('HeaderLinksController', ["$scope", function($scope) {
    $scope.links = [
        {url:"view1", name: "View 1"},
        {url:"view2", name: "View 2"}
    ]
}]);

myAppControllers.controller('Controller1', function($scope, $http) {
    $scope.message = "Hello from view 1";
	 $http({
	        url: '/zhubajie-app-apiprovider/countries/197',
	        dataType: 'json',
	        method: 'GET',
	        data: '',
	        headers: {
	            "Content-Type": "application/json"
	        }
	    }).success(function(data) {
	        console.log('CountryName: ' + data.name);
	        $scope.response = data;
	    }).error(function(error) {
	        console.log('Error: ' + error);
	        $scope.error = error;
	    });	
});

myAppControllers.controller('Controller2', function($scope, $http) {
    $scope.message = "Hello from view 2";
    $scope.now = new Date();
    $http({
        url: '/zhubajie-app-apiprovider/countries',
        dataType: 'json',
        method: 'GET',
        data: '',
        headers: {
            "Content-Type": "application/json"
        }
    }).success(function(data) {
        $scope.countries = data;
    }).error(function(error) {
        $scope.error = error;
    });    
});

