import * as angular from "angular";

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

    loadUserAccounts(id) {

        return this.$http.get(`/api/accounts/${id}`, {
            credentials: 'include',
        });
    }

}

export default "AccountService";
