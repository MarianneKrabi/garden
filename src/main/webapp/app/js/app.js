'use strict';


// Declare app level module which depends on filters, services and directives
angular.module('Garden', [ 'Garden.filters', 'Garden.services', 'Garden.directives' ])
        .config([ '$routeProvider', function($routeProvider) {
            $routeProvider.when('/garden', {
                templateUrl : 'app/partials/garden.html',
                controller : PlantDetailCtrl
            });
            $routeProvider.when('/plant-list', {
                templateUrl : 'app/partials/plant-list.html',
                controller : PlantListCtrl
            });
            $routeProvider.otherwise({
                redirectTo : '/garden'
            });
        } ]);
