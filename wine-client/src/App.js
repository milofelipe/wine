import React from 'react';
import { BrowserRouter, Switch, Route } from "react-router-dom";
import WineSearch from "./components/winesearch/WineSearch";
import ProductView from "./components/productview/ProductView";

export default class App extends React.Component {

  render() {
    return (
        <div>
          <BrowserRouter>
            <Switch>
              <Route exact path="/" component={WineSearch} />
              <Route exact path="/wine/:lotCode" component={ProductView} />
            </Switch>
          </BrowserRouter>
        </div>
    );
  }

}
