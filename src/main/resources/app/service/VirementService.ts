import * as angular from "angular";
import virement from "../virement/virement/virement";

export class VirementService {


    constructor(private $http) {
    }

    loadVirementRecu(id) {
        return this.$http.get(`/api/virement/recu/${id}`, {
            credentials: 'include',
        });
    }

    loadVirementEffectue(id) {
        return this.$http.get(`/api/virement/effectue/${id}`, {
            credentials: 'include',
        });
    }

}

export default "VirementService";
