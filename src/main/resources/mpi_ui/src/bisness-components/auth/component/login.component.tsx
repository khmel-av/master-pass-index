import * as React from 'react';
import { IState } from '../../../reducers';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router';
import Button from 'reactstrap/lib/Button';
import { Link } from 'react-router-dom';
import {setup} from "../actions/auth.actions";

interface IComponentState {
  username: string,
  password: string,
  confirmationPassword: string,
  newPassword: string,
  passwordNeedsReset: boolean,
  incorrectUserPass: boolean
}

interface IComponentProps extends RouteComponentProps<{}> {
  setup
}

export class LoginComponent extends React.Component<IComponentProps, IComponentState> {
  constructor(props: any) {
    super(props);
    this.state = {
      confirmationPassword: '',
      incorrectUserPass: false,
      newPassword: '',
      password: '',
      passwordNeedsReset: false,
      username: '',
    }
  }

  public updateUsername = (e: any) => {
    const username = e.target.value;
    this.setState({
      ...this.state,
      username

    })
  };

  public updatePassword = (e: any) => {
    const password = e.target.value;
    this.setState({
      ...this.state,
      password
    })
  };
  public updateConfirmationPassword = (e: any) => {
    const password = e.target.value;
    this.setState({
      ...this.state,
      confirmationPassword: password
    })
  };
  public updateNewPassword = (e: any) => {
    const password = e.target.value;
    this.setState({
      ...this.state,
      newPassword: password
    })
  };

  public submitLogin = async (e: any) => {
    e.preventDefault();
    const { username, password } = this.state;
    try {
      // const auth: IAuth = {
      //     login: username,
      //     password: password
      // };
      const user = {
        password,
        username,
      };
      if (user.username === 'NEW_PASSWORD_REQUIRED') {
        this.setState({
          ...this.state,
          passwordNeedsReset: true,

        });
      } else {
        this.props.history.push('/home');
        this.props.setup(username, password);
      }
    } catch (err) {
      console.log(err);
    }
  };

  public submitPasswordReset = async (e: any) => {
    e.preventDefault();
    if (this.state.newPassword === this.state.confirmationPassword) {
      this.props.setup();
      this.props.history.push('/home');
    }
  };

  public handlePassChange(event) {
    this.setState({
      ...this.state,
      password: event.target.value
    })
  }

  public render() {
    return (
      <div className="col-md-12">
        <div className="card card-container">
          {!this.state.passwordNeedsReset &&
          <>
              <h4 id="titleHead">Аутентификация</h4>
              <form id="login-form" onSubmit={this.submitLogin}>
                  <div className="form-group">
                      <input name="username" type="text" className="form-control txt-bx" placeholder="Логин" onChange={this.updateUsername} value={this.state.username} />
                      <br />
                      <input name="password" type="password" className="form-control txt-bx" id="login-pass" placeholder="Пароль" onChange={this.updatePassword} value={this.state.password}/>
                      <br />
                      <Button className="btn btn-primary">Войти</Button>
                  </div>

              </form>

            {this.state.incorrectUserPass &&
            <h6 id="invalidCredHead">Invalid Credentials</h6>
            }
          </>
          }
          {this.state.passwordNeedsReset &&
          <>
              <h4 id="titleHead">Reset Password</h4>
              <form id="login-form">
                  <input  type="text" className="form-control txt-bx" placeholder="New Password" value={this.state.newPassword} />
                  <br />
                  <input id="login-pass" type="password" className="form-control txt-bx" placeholder="Confirm Password" value={this.state.confirmationPassword} />
                  <br />
                  <Button type="submit" className="btn btn-primary">Сброс</Button>
              </form>
          </>
            // <ResetFirstPasswordComponent
            //   code={this.state.password}
            //   setup={this.props.initUser} />
          }
          <div className="row resetDiv">
            <button id="forgot-pass-btn" className="btn btn-info">Забыли логин или пароль</button>
          </div>
          <br />
          <div className="row resetDiv">
            <Link to="/register">
              <button id="register-btn" className="btn btn-success">Зарегистрироваться в системе</button>
            </Link>
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = (state: IState) => (state.auth);
const mapDispatchToProps = {
  setup
};

export default connect(mapStateToProps, mapDispatchToProps)(LoginComponent);
