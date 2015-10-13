(function() {
    'use strict';
    var app = angular.module("homebudgetApp", ['ngRoute', 'ngMessages']);
    app.config(['$routeProvider',
        function($routeProvider) {
            // $locationProvider.html5Mode(true);
            $routeProvider.
            when("/", {
                templateUrl: "templates/index/index.tpl.html",
                controller: "IndexPageController"
            }).
            when("/login", {
                templateUrl: "templates/login/login.tpl.html",
                controller: "LoginController"
            }).
            when("/register", {
                templateUrl: "templates/register/register.tpl.html",
                controller: "RegisterController"
            }).
            when("/reset", {
                templateUrl: "templates/reset/reset.tpl.html",
                controller: "ResetController"
            }).
            when("/transaction", {
                templateUrl: "templates/transaction/transaction.tpl.html",
                controller: "TransactionController"
            }).
            otherwise({
                redirectTo: "/"
            });
        }
    ]);
})();