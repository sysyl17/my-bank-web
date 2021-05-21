import {bootstrap, module} from 'angular';
import Index from "./index/index";
//
import uirouter from '@uirouter/angularjs';
import IndexComponent from './index/index'
import {default as userServiceName, UserService} from "../service/UserService";

module('login', [
    uirouter,
])
    .component(Index.name, Index.component)
    .service(userServiceName, UserService)
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {


        const login = {
            name: "login",
            state: {
                url: "/login",
                views: {
                    'main@': {
                        component: IndexComponent.name
                    }
                }
            }
        };

        $urlRouterProvider.otherwise("/login");

        $stateProvider
            .state(login.name, login.state)


    }])
;
bootstrap(document.body, ['login']);
