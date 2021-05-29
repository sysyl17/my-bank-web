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
    private name: string;
    private balance: string;
    private id: string;

    constructor(private userService: UserService, private $sce, private accountService: AccountService, private $state) {
    }


    $onInit() {
        this.userService.getCurrentUser()
            .then((response) => {
                this.id = response.id;
                this.loadAccount();
            })
            .catch((e) => {
                this.userService.logout();
                alert("Session invalide, expulsé pour inactivité");
            });
    }

    loadAccount() {
        return this.accountService.loadUserAccounts(this.id)
            .then((response) => {
                this.accounts = response.data;
                return response;
            });
    }

    async creeCompte() {

        if (this.name && this.balance) {
            let tokenExp = await this.userService.getCurrentUser();
            if (!tokenExp) {
                this.userService.logout();
                alert("Session invalide, expulsé pour inactivité");
            }

            this.userService.refreshTokenExpiration();
            let response = await this.accountService.addAccount(this.name, this.balance);
            if (response.status === 200) {
                this.closeNewAccount();
            }
        } else {
            alert("Erreur de saisie.")
        }
    }

    //redirige vers la page virement en faisant en rafraichissant la date d'expiration du token
    async virement() {
        this.userService.refreshTokenExpiration();
        document.location.href = "/virement";
    }

    closeNewAccount() {
        this.name = undefined;
        this.balance = undefined;
        this.loadAccount();
    }


}
