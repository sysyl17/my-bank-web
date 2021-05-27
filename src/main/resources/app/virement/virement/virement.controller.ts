import {UserService, default as userServiceName} from "../../service/UserService";
import {VirementService, default as virementServiceName} from "../../service/VirementService";
import virement from "./virement";

export default class AccountCtrl {

    private static readonly $inject = [
        userServiceName,
        "$sce",
        virementServiceName,
        "$state"
    ]
    private virementsRecu: Array<any>;
    private virementsEffectue: Array<any>;
    private id: string;

    constructor(private userService: UserService, private $sce, private virementServiceName: VirementService, private $state) {
    }

    $onInit() {
        this.userService.getCurrentUser()
            .then((response) => {
                this.id = response.id;
                this.loadVirementRecu();
                this.loadVirementEffectue();
            })
            .catch((e) => {
                this.userService.deconnecter();
                alert("Session invalide, expulsé pour inactivité");
            });
    }

    loadVirementRecu() {
        return this.virementServiceName.loadVirementRecu(this.id)
            .then((response) => {
                this.virementsRecu = response.data;
                console.log(this.virementsRecu);
                return response;
            });

    }

    loadVirementEffectue() {
        return this.virementServiceName.loadVirementEffectue(this.id)
            .then((response) => {
                this.virementsEffectue = response.data;
                return response;
            });
    }

    async accounts() {
        document.location.href = "/espacePerso";
    }

}
