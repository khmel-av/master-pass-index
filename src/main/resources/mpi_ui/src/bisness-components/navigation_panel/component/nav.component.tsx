import * as React from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { connect } from 'react-redux';
import { IState, IAuthState } from '../../../reducers';
import { withRouter } from "react-router";
import '../../../include/bootstrap';
import {logout, setup} from "../../auth/actions/auth.actions";

interface IProps extends RouteComponentProps<{}> {
    logout: () => void;
    setup: (username: string, password: string) => void;
    auth: IAuthState;
}

class NavigationPanel extends React.PureComponent<IProps, {}, {}> {
    logout = () => {
        this.props.logout();
        this.props.history.push('/login');
    };

    public render() {
        const props = this.props;
        return (
            <nav className="navbar navbar-inverse navbar-fixed-top" role="navigation">
                <div className="container">
                    <div className="navbar-header">
                        <button type="button" className="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                            <span className="sr-only">Toggle navigation</span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                            <span className="icon-bar"></span>
                        </button>
                        <Link className="navbar-brand" to="/">PassCard</Link>
                    </div>
                    <div className="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul className="nav navbar-nav navbar-left">
                            <li>
                                 <Link to="/news">Новости</Link>
                            </li>
                            {
                                this.props.auth.currentUser.roles.some(role => role === 'admin' || role === 'user') &&
                                <>
                                    <li>
                                        <Link to="/create/card">Создать пропуск</Link>
                                    </li>
                                    <li>
                                        <Link to="/all/card">Все пропуска</Link>
                                    </li>
                                    <li>
                                        <Link to="/setting">Настройки</Link>
                                    </li>
                                </>
                            }
                            {
                                this.props.auth.currentUser.roles.some(role => role === 'admin') &&
                                <>
                                    <li>
                                        <Link to="/admin">Администрирование</Link>
                                    </li>
                                </>
                            }
                        </ul>
                        <ul className="nav navbar-nav navbar-right">
                            {
                                props.auth.currentUser.email
                                    ?
                                    <>
                                        <li className="dropdown">
                                            <a href="/#" className="dropdown-toggle" data-toggle="dropdown">
                                                {props.auth.currentUser.shortName}
                                                <b className="caret"></b>
                                            </a>
                                            <ul className="dropdown-menu">
                                                <li>
                                                    <Link to="/profile">Профиль</Link>
                                                </li>
                                                <li>
                                                    <Link to="" onClick={this.logout}>
                                                        Выход
                                                    </Link>
                                                </li>
                                            </ul>
                                        </li>
                                    </>
                                    :
                                    <>
                                        <li className="NotLogin">
                                            Вы не вошли в приложение
                                        </li>
                                        <li>
                                            <Link to="/login" className="unset-anchor nav-link">
                                                <span className="Entry">
                                                    Войти
                                                </span>
                                            </Link>
                                        </li>
                                    </>
                            }
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
}

const mapStateToProps = (state: IState) => ({
    auth: state.auth,
});
const mapDispatchToProps = {
    logout,
    setup
};

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(NavigationPanel)) as any;
