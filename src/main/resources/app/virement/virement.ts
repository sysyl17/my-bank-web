// @ts-ignore
import angular from 'angular';
import virement from "./virement/virement";
import "../style/app.css";
import "jquery";



import VirementComponent from './virement/virement'
import uirouter from '@uirouter/angularjs';

import "@fortawesome/fontawesome-free/css/all.min.css";

import {default as userServiceName, UserService} from "../service/UserService";
import {default as accountServiceName, AccountService} from "../service/AccountService";
import {default as virementServiceName, VirementService} from "../service/VirementService";


angular.module('virement', [uirouter])
    .component(virement.name, virement.component)
    .service(userServiceName, UserService)
    .service(accountServiceName, AccountService)
    .service(virementServiceName, VirementService)
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {


        const virement = {
            name: "virement",
            state: {
                url: "/virement",
                views: {
                    'main@': {
                        component: VirementComponent.name
                    }
                }
            }
        };


        $urlRouterProvider.otherwise("/virement");

        $stateProvider
            .state(virement.name, virement.state);
    }])


angular.bootstrap(document.body, ['virement']);
