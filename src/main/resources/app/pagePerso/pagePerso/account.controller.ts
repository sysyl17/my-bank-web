import {UserService, default as userServiceName} from "../../service/UserService";
import {AccountService, default as accountServiceName} from "../../service/AccountService";

export default class AccountCtrl {

    private static readonly $inject = [
        userServiceName,
        "$sce",
        accountServiceName,
        "$state"
    ]
    private accounts: Array<any>;


    constructor(private userService: UserService, private $sce, private accountServiceName: AccountService, private $state) {
    }

    $onInit() {
        this.userService.getCurrentUser()
            .then((response) => {
                this.loadAccounts();
            });
        //this.loadAccount( this.userService.getCurrentUser().id);
    }

    loadAccount(id) {
        return this.accountServiceName.getUserAccount(id)
            .then((response) => {
                this.accounts = response.data;
                return response;
            });
    }
    loadAccounts() {
        return this.accountServiceName.loadAccounts()
            .then((response) => {
                this.accounts = response.data;
                return response;
            });
    }

    showUser(user) {
        this.$state.go("user", {id: user.id})
    }


}
