(function() {
    'use strict';
    var app = angular.module("homebudgetApp", ['ngRoute', 'ngMessages']);
    app.config(['$routeProvider',
        function($routeProvider) {
            // $locationProvider.html5Mode(true);
            $routeProvider.
            when("/", {
                title: 'Homebudget',
                templateUrl: "templates/index/index.tpl.html",
                controller: "IndexPageController"
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
                templateUrl: "templates/changePassword/changePassword.tpl.html",
                controller: "ChangePasswordController"
            }).
            when("/reset", {
                title: 'Password Reset',
                templateUrl: "templates/reset/reset.tpl.html",
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

app.run(['$rootScope', function($rootScope) {
    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        $rootScope.title = current.$$route.title;
    });
}]);
})();



