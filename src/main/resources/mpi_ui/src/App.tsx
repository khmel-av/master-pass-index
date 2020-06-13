import React from 'react';
import './App.css';
import { Provider } from 'react-redux';
import './include/bootstrap';
import { store } from './Store';
import NavigationPanel from './bisness-components/navigation_panel/component/nav.component';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import  LoginComponent  from './bisness-components/auth/component/login.component';
import ProfileComponent from "./bisness-components/profile/component/profile.component";
import FooterComponent from "./bisness-components/footer/component/footer.component";
import {HomeContainer} from "./bisness-components/home/component/home.container";
import PassCardListComponent from "./bisness-components/card/all_card/component/card.container";
import FeedbackComponent from "./bisness-components/feedback/component/feedback.container";
import {NewsComponent} from "./bisness-components/news/component/news.component";
import {SettingComponent} from "./bisness-components/setting/component/setting.component";
import {AdminComponent} from "./bisness-components/admin/component/admin.component";
import RegistrationComponent from "./bisness-components/registration/component/registration.component";
import AddCardComponent from "./bisness-components/card/add_card/component/add.card.component";
import CheckCardComponent from "./bisness-components/card/check_card/component/check.card.container";
import ProtectedRoute from "./bisness-components/protected_route/protected.route";


class App extends React.Component {
  public render() {
    return (
      <Provider store={store as any}>
        <BrowserRouter>
          <div>
            <NavigationPanel />
            <div id="main-content-container">
              <Switch>
                <Route path="/login" component={LoginComponent} />
                <Route path="/register" component={RegistrationComponent} />
                <Route path="/news" component={NewsComponent} />
                <Route path="/feedback" component={FeedbackComponent} />
                <Route path="/check/card" component={CheckCardComponent} />
                <ProtectedRoute allowedRoles={['admin', 'user']} path="/profile" component={ProfileComponent} />
                <ProtectedRoute allowedRoles={['admin', 'user']} path="/create/card" component={AddCardComponent} />
                <ProtectedRoute allowedRoles={['admin', 'user']} path="/all/card" component={PassCardListComponent} />
                <ProtectedRoute allowedRoles={['admin', 'user']} path="/setting" component={SettingComponent} />
                <ProtectedRoute allowedRoles={['admin']} path="/admin" component={AdminComponent} />
                <Route component={HomeContainer} />
              </Switch>
              <br />
            </div>
            <FooterComponent />
          </div>
        </BrowserRouter>
      </Provider>
    );
  }
}

export default App;
