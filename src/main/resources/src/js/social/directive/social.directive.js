(function() {
    'use strict';

    angular
        .module('homebudgetApp')
        .directive('hbSocial', hbSocial);

    hbSocial.$inject = ['$filter'];

    function hbSocial($filter) {
        var directive = {
            restrict: 'E',
            scope: {
                provider: '@ngProvider'
            },
            templateUrl: 'templates/social/directive/social.tpl.html',
            link: linkFunc
        };

        return directive;

        function linkFunc(scope) {
            scope.label = scope.provider;
            //scope.providerSetting = SocialService.getProviderSetting(scope.provider);
            //scope.providerURL = SocialService.getProviderURL(scope.provider);
            //scope.csrf = SocialService.getCSRF();
        }

    }
})();
