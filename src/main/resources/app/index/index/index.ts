import './index.css';

// @ts-ignore
const template = require("./index.html");

import controller from "./index.controller";

const component={
  template,
  controller,
  controllerAs: 'ctrl'
}

const IndexComponent = {
  name: 'index',
  component
};

export default IndexComponent;
