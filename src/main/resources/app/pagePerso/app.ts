// @ts-ignore
import angular from 'angular';
import User from "./user/user";
import pagePerso from "./pagePerso/pagePerso";
import "../style/app.css";
import "jquery";


import UserComponent from './user/user';
import PagePersoComponent from './pagePerso/pagePerso'
import uirouter from '@uirouter/angularjs';

import "@fortawesome/fontawesome-free/css/all.min.css";

import {default as userServiceName, UserService} from "../service/UserService";


angular.module('app', [uirouter])
    .component(pagePerso.name, pagePerso.component)
    .component(User.name, User.component)
    .service(userServiceName, UserService)
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {


        const espacePerso = {
            name: "espacePerso",
            state: {
                url: "/espacePerso",
                views: {
                    'main@': {
                        component: PagePersoComponent.name
                    }
                }
            }
        };


        $urlRouterProvider.otherwise("/espacePerso");

        $stateProvider
            .state(espacePerso.name, espacePerso.state);
    }])


angular.bootstrap(document.body, ['app']);
