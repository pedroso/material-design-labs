'use strict';

/**
 * @ngdoc function
 * @name materialDesignApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the materialDesignApp
 */
angular.module('materialDesignApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
