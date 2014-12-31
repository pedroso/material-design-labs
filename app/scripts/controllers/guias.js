'use strict';

/**
 * @ngdoc function
 * @name materialDesignApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the materialDesignApp
 */
 //"polymer": "Polymer/polymer#~0.5.2",
angular.module('materialDesignApp')
  .controller('GuiaCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
