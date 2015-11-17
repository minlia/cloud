/*
 * Copyright (C) 2004-2015 http://oss.minlia.com/license/infrastructure/2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
