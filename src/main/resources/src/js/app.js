(function() {
    'use strict';
    var app = angular.module("homebudgetApp", ['ngRoute', 'ngMessages', 'pascalprecht.translate']);
    
    app.config(['$routeProvider',
        function($routeProvider) {
            // $locationProvider.html5Mode(true);
            $routeProvider.
            when("/", {
                title: 'Homebudget'
            }).
            when("/login", {
                title: 'Login',
                templateUrl: "templates/login/login.tpl.html",
                controller: "LoginController"
            }).
            when("/register", {
                title: 'Register',
                templateUrl: "templates/register/register.tpl.html",
                controller: "RegisterController"
            }).
            when("/reset/change-password/:token", {
                title: 'Change Password',
                templateUrl: "templates/reset/changePassword/changePassword.tpl.html",
                controller: "ChangePasswordController"
            }).
            when("/reset/recovery-error", {
                title: 'Password Recovery Error',
                templateUrl: "templates/reset/recoveryError/recoveryError.tpl.html"
            }).
            when("/reset/confirm-reset", {
                title: 'Forgotten Password Request',
                templateUrl: "templates/reset/confirmPassword/confirmPassword.tpl.html"
            }).
            when("/reset", {
                title: 'Password Reset',
                templateUrl: "templates/reset/request/reset.tpl.html",
                controller: "ResetController"
            }).
            when("/transaction", {
                title: 'Transaction',
                templateUrl: "templates/transaction/transaction.tpl.html",
                controller: "TransactionController"
            }).
            otherwise({
                redirectTo: "/"
            });
        }
    ]);

    app.config(['$translateProvider', function ($translateProvider) {
        $translateProvider.useStaticFilesLoader({
            prefix: 'l10n/',
            suffix: '.json'
        });

        $translateProvider.use('en_US');
    }]);


app.run(['$rootScope', function($rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;
    });
}]);
})();



