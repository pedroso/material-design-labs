'use strict';

/**
 * @ngdoc function
 * @name materialDesignApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the materialDesignApp
 */
angular.module('materialDesignApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
