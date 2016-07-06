angular.module('homebudgetApp').controller('ResetController', ['$scope', '$http', '$location', '$route',
    function($scope, $http, $location, $route){
        //console.log(ResetController);	    
        $scope.submit = function() {
            var that = this;
            var payload = { email: $scope.resetform.email.$modelValue };
            console.log(payload);
            var resp = $http({
                url: '/api/users/resetPassword',
                method: "POST",
                data: angular.toJson(payload),
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            resp.success(function(data) {
                console.log(data);
                that.error = false;
                that.message = "An email with instructions for password recovery has been sent";
                $location.path("/reset/confirm-reset");
            }).error(function(data) {
                console.log(data);
                that.error = true;
                that.message = data.message;
            });
        };    
    }
]);