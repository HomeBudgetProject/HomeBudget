angular.module('homebudgetApp').controller("LoginController", ['$scope', '$http', '$location', '$route',
    function($scope, $http, $location) {
        var that = this;
        $scope.submit = function() {
            var resp = $http({
                url: '/api/login',
                method: "POST",
                data: "username=" + $scope.loginform.email.$modelValue +
                    "&password=" + $scope.loginform.password.$modelValue,
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