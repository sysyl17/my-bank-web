import * as angular from "angular";

export class AccountService {


    constructor(private $http) {
    }

    getUserAccount(id){
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
    loadAccount(id) {

        return this.$http.get(`/api/accounts/${id}`, {
            credentials: 'include',
        });
    }

    loadAccounts() {

        return this.$http.get('/api/accounts', {
            credentials: 'include',
        });

    }
}

export default "AccountService";
