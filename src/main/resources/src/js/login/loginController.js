angular.module('homebudgetApp').controller("LoginController", ['$scope', '$http', '$location', '$route', '$filter',
    function($scope, $http, $location, $route, $filter) {
        var that = this;
        $scope.submit = function() {
            var resp = $http({
                url: '/api/login',
                method: "POST",
                data: "username=" + encodeURIComponent($scope.loginform.email.$modelValue) +
                    "&password=" + encodeURIComponent($scope.loginform.password.$modelValue),
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            });

            resp.success(function(data) {
                console.log(data);
                that.error = false;
                that.message = "Вы успешно вошли";
                $location.path("/transaction");
            }).error(function(data) {
                console.log(data);
                that.error = true;
                that.message = data.message;
            });
        };
    }
]);