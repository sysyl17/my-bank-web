import * as angular from "angular";
import account from "../pagePerso/pagePerso/account";

export class AccountService {


    constructor(private $http) {
    }

    getUserAccount(id) {
        return this.$http
            .get(`/api/accounts/${id}`, {
                credentials: 'include',
            })
            .then(resp => {
                if (resp.status === 200) {
                    return resp.data;
                }
            })
            .catch((e) => {
            });
    }

    //retourne tous les comptes bancaire d'un utilisateur
    loadUserAccounts(id) {

        return this.$http.get(`/api/accounts/${id}`, {
            credentials: 'include',
        });
    }

    //Ajout d'un compte bancaire à la BD
    addAccount(name, balance) {
        return this.$http.post('/api/accounts', {name: name, balance: balance},
            {
                credentials: 'include',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
            })
    }

}

export default "AccountService";
