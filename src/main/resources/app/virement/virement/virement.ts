import './virement.css';

// @ts-ignore
const template = require("./virement.html");

import controller from "./virement.controller";

const component={
  template,
  controller,
  controllerAs: 'ctrl',
}

const   VirementComponent = {
  name: 'virement',
  component
};

export default VirementComponent;
