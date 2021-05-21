import './user.css';

// @ts-ignore
const template = require("./user.html");

import controller from "./user.controller";

const component={
  template,
  controller,
  controllerAs: 'ctrl',
}

const CommentUserComponent = {
  name: 'commentUser',
  component
};


export default CommentUserComponent;
