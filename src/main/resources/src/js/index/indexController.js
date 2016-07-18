angular.module('homebudgetApp').controller('IndexPageController', ['$scope', '$translate', function($scope, $translate){
  $scope.lang =   [
    {"code" : "en_US", "name" : "English"},
    {"code" : "ru_RU", "name" : "Russian"}
  ];

  console.log($scope.lang);
  $scope.activeLang = $scope.lang[0];
  
  $scope.useLang = function () {
    $translate.use($scope.activeLang.code);
  };

  $scope.useLang();
}]);