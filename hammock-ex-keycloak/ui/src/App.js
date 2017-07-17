import React, {Component} from 'react'
import Keycloak from 'keycloak-js'
import './App.css'
import { Button, Modal } from 'react-bootstrap'
import axios from 'axios'

class App extends Component {
    constructor() {
        super()
        this.kc = Keycloak({
            url: '/auth',
            realm: 'master',
            clientId: 'test-client'
        });
        this.state = { showModal: false, msg: '' }
        var kc = this.kc
        kc.init({onLoad: 'login-required'}).success(function(authenticated) {
            console.log(kc.subject)
        }).error(function() {
            alert('failed to initialize');
        });
        this.close = this.close.bind(this);
        this.open = this.open.bind(this);
    }
    close() {
        this.setState({
            showModal: false,
            msg: ''
        });
    }

    open() {
        var that = this;
        var authStr = 'Bearer '+this.kc.token
        axios.get('/api/loggedin', { 'headers': { 'Authorization': authStr } })
            .then(function(response) {
                that.setState({
                    msg: 'Welcome, '+response.data,
                    showModal: true
                })
            })
            .catch(function(e) {
                alert(e)
            })

    }
    render() {
        return (
            <div className='App'>
                <div className='App-header'>
                    <img src="https://raw.githubusercontent.com/hammock-project/hammock-project.github.io/master/static/img/logo/hammock_blue.png" className='App-logo' alt='logo'/>
                    <h2>Welcome Hammock on React</h2>
                </div>
                <p className='App-intro'>
                    This is a simple app that connects to Keycloak running on /auth (via proxy).  It will return who you are logged in as when you click the button in a modal.
                    <br/>
                    <Button bsStyle='primary' onClick={this.open}>Who Am I?</Button>
                </p>
                <Modal show={this.state.showModal} onHide={this.close}>
                    <Modal.Header closeButton>
                        <Modal.Title>The results from the backend</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <h4 id="results">{this.state.msg}</h4>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button onClick={this.close}>Close</Button>
                    </Modal.Footer>
                </Modal>
            </div>

        );
    }
}

export default App;
