angular.module('homebudgetApp').directive("validateemail", function() {
    return {
        restrict: 'A',
        require: "ngModel",
        link: function(scope, element, attributes, ngModel) {
            ngModel.$validators.required = function(modelValue) {
                return (modelValue !== "") || scope.registerform.email.$pristine;
            };
            ngModel.$validators.regexp = function(modelValue) {
                var regexp = new RegExp("^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
                return regexp.test(modelValue) || scope.registerform.email.$pristine;
            };
            ngModel.$validators.emaillength = function(modelValue) {
                return (modelValue && modelValue.length >= 6 && modelValue.length <= 60) || scope.registerform.email.$pristine;
            };
        }
    };
});
angular.module('homebudgetApp').directive("validatepassword", function() {
    return {
        restrict: 'A',
        require: "ngModel",
        link: function(scope, element, attributes, ngModel) {
            ngModel.$validators.required = function(modelValue) {
                return (modelValue !== "") || scope.registerform.password.$pristine;
            };
            ngModel.$validators.passwordlength = function(modelValue) {
                return (modelValue && modelValue.length >= 6 && modelValue.length <= 100) || scope.registerform.password.$pristine;
            };
            ngModel.$validators.regexp = function(modelValue) {
                var regexp = new RegExp("^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+$");
                return regexp.test(modelValue) || scope.registerform.password.$pristine;
            };
        }
    };
});