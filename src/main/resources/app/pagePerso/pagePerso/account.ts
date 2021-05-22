import './account.css';

// @ts-ignore
const template = require("./account.html");

import controller from "./account.controller";

const component={
  template,
  controller,
  controllerAs: 'ctrl',
}

const   AccountComponent = {
  name: 'account',
  component
};

export default AccountComponent;
