import * as React from 'react';
import {Link} from "react-router-dom";

export class FooterComponent extends React.Component {
  public render() {
    return(
      <nav className="navbar navbar-inverse navbar-fixed-bottom">
        <div className="container">
          <div className="collapse navbar-collapse">
            <ul className="nav navbar-nav navbar-left">
              <li>
                <Link to="/">Copyright© мПИНЖ-11 2020</Link>
              </li>
            </ul>
            <ul className="nav navbar-nav navbar-right">
              <li>
                <Link to="/feedback">Написать нам</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    );
  }
}

export default FooterComponent;