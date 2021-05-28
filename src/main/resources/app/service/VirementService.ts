import * as angular from "angular";
import virement from "../virement/virement/virement";

export class VirementService {


    constructor(private $http) {
    }

    //retourne tous les virements recu par un utilisateur
    loadVirementRecu(id) {
        return this.$http.get(`/api/virement/recu/${id}`, {
            credentials: 'include',
        });
    }

    //retourne tous les virements effectué par un utilisateur
    loadVirementEffectue(id) {
        return this.$http.get(`/api/virement/effectue/${id}`, {
            credentials: 'include',
        });
    }

}

export default "VirementService";
