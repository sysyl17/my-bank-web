// @ts-ignore
import angular from 'angular';
import pagePerso from "./pagePerso/account";

import "../style/app.css";
import "jquery";


import AccountComponent from './pagePerso/account'
import uirouter from '@uirouter/angularjs';

import "@fortawesome/fontawesome-free/css/all.min.css";

import {default as userServiceName, UserService} from "../service/UserService";
import {default as accountServiceName, AccountService} from "../service/AccountService";
import {csrfInterceptor} from "../service/Interceptor";


angular.module('app', [uirouter])
    .component(pagePerso.name, pagePerso.component)
    .service(userServiceName, UserService)
    .service(accountServiceName, AccountService)
    .factory('authInterceptor', csrfInterceptor)
    .config(['$httpProvider', ($httpProvider) => {
        $httpProvider.interceptors.push('authInterceptor');
    }])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {


        const accounts = {
            name: "accounts",
            state: {
                url: "/accounts",
                views: {
                    'main@': {
                        component: AccountComponent.name
                    }
                }
            }
        };


        $urlRouterProvider.otherwise("/accounts");

        $stateProvider
            .state(accounts.name, accounts.state);

    }])


angular.bootstrap(document.body, ['app']);
