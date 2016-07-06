angular.module('homebudgetApp').controller('ChangePasswordController', ['$scope', '$http', '$location', '$route', '$routeParams', 
	function($scope, $http, $location, $route, $routeParams){
	    $scope.submit = function() {
	        var that = this;
	        var payload = {newPassword: $scope.resetform.password.$modelValue, tokenHash: $routeParams.token};
	        console.log(payload);
	        var resp = $http({
	            url: '/api/users/changePassword',
	            method: "POST",
	            data: angular.toJson(payload),
	            headers: {
	                'Content-Type': 'application/json'
	            }
	        });
	
	        resp.success(function(data) {
	            console.log(data);
	            that.error = false;
	            that.message = "Your password has been successfuly changed";
	            $location.url("/login");
	        }).error(function(data) {
	            console.log(data);
	            that.error = true;
	            that.message = data.message;
	            $location.url("/reset/recovery-error");
	        });
	    };    
	}
]);