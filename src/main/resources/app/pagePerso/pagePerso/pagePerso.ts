import './pagePerso.css';

// @ts-ignore
const template = require("./pagePerso.html");

import controller from "./pagePerso.controller";

const component={
  template,
  controller,
  controllerAs: 'ctrl',
}

const   LoginComponent = {
  name: 'pagePerso',
  component
};

export default LoginComponent;
