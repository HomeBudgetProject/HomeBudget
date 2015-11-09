angular.module('homebudgetApp').directive("validateemail", function() {
    return {
        restrict: 'A',
        require: ["ngModel", "^form"],
        link: function(scope, element, attributes, requireArray) {
            var ngModel = requireArray[0];
            var form = requireArray[1];
            ngModel.$validators.required = function(modelValue) {
                return (modelValue !== "") || form.email.$pristine;
            };
            ngModel.$validators.regexp = function(modelValue) {
                var regexp = /^(?:[a-zA-Z0-9_'^&\/+-])+(?:\.(?:[a-zA-Z0-9_'^&\/+-])+)*@(?:(?:\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\.){3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\]?)|(?:[a-zA-Z0-9-]+\.)+(?:[a-zA-Z]){2,}\.?)$/;
                return regexp.test(modelValue) || form.email.$pristine;
            };
            ngModel.$validators.emaillength = function(modelValue) {
                return (modelValue && modelValue.length >= 6 && modelValue.length <= 60) || form.email.$pristine;
            };
        }
    };
});
angular.module('homebudgetApp').directive("validatepassword", function() {
    return {
        restrict: 'A',
        require: ["ngModel", "^form"],
        link: function(scope, element, attributes, requireArray) {
            var ngModel = requireArray[0];
            var form = requireArray[1];
            ngModel.$validators.required = function(modelValue) {
                return (modelValue !== "") || form.password.$pristine;
            };
            ngModel.$validators.passwordlength = function(modelValue) {
                return (modelValue && modelValue.length >= 6 && modelValue.length <= 100) || form.password.$pristine;
            };
            ngModel.$validators.regexp = function(modelValue) {
                var regexp = new RegExp("^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+$");
                return regexp.test(modelValue) || form.password.$pristine;
            };
        }
    };
});