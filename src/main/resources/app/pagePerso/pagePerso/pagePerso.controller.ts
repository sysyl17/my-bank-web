import {UserService, default as userServiceName} from "../../service/UserService";

export default class CommentCtrl {

    private static readonly $inject=[
        userServiceName,
        "$sce",
        "$state"
    ]
    private comments: Array<any>;
    private newCommentText: string;

    constructor(private userService:UserService, private $sce, private $state) {
    }

    $onInit() {
        this.userService.getCurrentUser()
            .then(() => {
            });
    }

    showUser(user){
        this.$state.go("user", {id:user.id})
    }


}
