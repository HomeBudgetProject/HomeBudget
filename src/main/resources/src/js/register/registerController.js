angular.module('homebudgetApp').controller('RegisterController', ['$scope', '$http',
    function($scope, $http) {
        var that = this;
        $scope.submit = function() {
            var resp = $http({
                url: '/api/users/register',
                method: "POST",
                data: {
                    "email": $scope.registerform.email.$modelValue,
                    "password": $scope.registerform.password.$modelValue
                },
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            resp.success(function(data) {
                that.error = false;
                that.message = "Вы успешно зарегистрированы";
            }).error(function(data) {
                that.error = true;
                that.message = data.message;
            });
        };
    }
]);