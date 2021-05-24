import {UserService, default as userServiceName} from "../../service/UserService";
import {AccountService, default as accountServiceName} from "../../service/AccountService";
import account from "./account";

export default class AccountCtrl {

    private static readonly $inject = [
        userServiceName,
        "$sce",
        accountServiceName,
        "$state"
    ]
    private accounts: Array<any>;
    private name: string;
    private balance: string;
    private id: string;

    constructor(private userServiceName: UserService, private $sce, private accountServiceName: AccountService, private $state) {
    }

    $onInit() {
        this.userServiceName.getCurrentUser()
            .then((response) => {
                this.id = response.id;
                this.loadAccount();
            })
            .catch((e) => {
                document.location.href = "/login";
                alert("Session invalide, expulsé pour inactivité");
            });
    }

    loadAccount() {
        return this.accountServiceName.loadUserAccounts(this.id)
            .then((response) => {
                this.accounts = response.data;
                return response;
            });
    }

    async creeCompte() {
       console.log("appel");

        if (this.name && this.balance) {
            let tokenExp= await this.userServiceName.getCurrentUser();
            if (!tokenExp) {
                document.location.href = "/login";
                alert("Session invalide, expulsé pour inactivité");
            }

            let response = await this.accountServiceName.addAccount(this.name, this.balance);
            if (response.status === 200) {
                this.closeNewAccount();
            }
        } else {
            alert("Erreur de saisie.")
        }
    }

    async virement() {
        document.location.href = "/virement";
    }

    closeNewAccount() {
        this.name = undefined;
        this.balance = undefined;
        this.loadAccount();
    }


    showUser(user) {
        this.$state.go("user", {id: user.id})
    }

}
