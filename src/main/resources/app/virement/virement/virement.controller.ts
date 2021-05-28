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
                this.userService.logout();
                alert("Session invalide, expulsé pour inactivité");
            });
    }

    //obtention des virements recu selon un id d'utilisateur
    loadVirementRecu() {
        return this.virementServiceName.loadVirementRecu(this.id)
            .then((response) => {
                this.virementsRecu = response.data;
                return response;
            });

    }

    //obtention des virements effectué selon un id d'utilisateur
    loadVirementEffectue() {
        return this.virementServiceName.loadVirementEffectue(this.id)
            .then((response) => {
                this.virementsEffectue = response.data;
                return response;
            });
    }

    //redirige vers la page espacePerso en faisant en rafraichissant la date d'expiration du token
    async accounts() {
        this.userService.refreshTokenExpiration();
        document.location.href = "/espacePerso";
    }

}
