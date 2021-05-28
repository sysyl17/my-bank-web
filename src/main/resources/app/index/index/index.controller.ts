import {UserService, default as userServiceName} from "../../service/UserService";

export default class LoginCtrl {
    private id: string;
    private password: string;


    private static readonly $inject = ["$state", userServiceName];

    constructor(private $state, private userService: UserService) {
    }

}
