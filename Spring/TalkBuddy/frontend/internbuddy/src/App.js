import React,{Component} from 'react';
import MyForm from './Forms/BasicForms.js'
import Intern from './Models/Intern.js'
import {Table,Button} from'./Layout/BaseLayout.js'
import { auth, startFirebaseUI  } from './Login/firebase.js'
import logo from './logo.svg';



const largeColumn = {width:'40%',};
const midColumn = {width:'30%',};
const smallColumn = {width:'10%'};




class App extends Component
{
    state={
        user:null,
        isLoading: true,
        interns:[],
        addUI:false,
        addTaskUI:false,

    };

    async componentDidMount()
    {
        const response = await fetch('/api/interns/')
        const body = await response.json()
        this.setState({interns:body,isLoading:false,addUI:false});

    }


    constructor(props)
    {
        super(props);
        this.onDismiss = this.onDismiss.bind(this);
        this.addIntern = this.addIntern.bind(this);
        this.refreshUI = this.refreshUI.bind(this);
        this.onDelete = this.onDelete.bind(this);
        this.addTask = this.addTask.bind(this);
        this.handleLogin = this.handleLogin.bind(this);
        this.logout = () => { auth.signOut()}
        auth.onAuthStateChanged((user)=>this.handleLogin(user));

    }

    handleLogin(user)
    {
        this.setState({user:user});
        if(user==null)
            startFirebaseUI("#firebaseui-auth-container");
    }


    onDismiss(id)
      {
        const isNotId = item => item.id !== id;
        const updatedInterns = this.state.interns.filter(isNotId);
        this.setState({
                interns:updatedInterns
            }
        );
      }

    async refreshUI(event)
    {
       if(event)
        event.preventDefault();
       const response = await fetch('/api/interns/')
       const body = await response.json()
       this.setState({interns:body,isLoading:false,addUI:false,addTaskUI:false});
    }

    addIntern()
    {
       this.setState({addUI:true})
    }

    addTask()
    {
        this.setState({addTaskUI:true})
    }

    onDelete(id)
    {
        fetch('/api/interns/removeintern/'+id,{method:'DELETE'}).then(this.onDismiss(id));
    }

    render()
    {




        const {interns,isLoading,addUI,addTaskUI,isLoggedIn}=this.state;


        if(this.state.user!==null){
        if(addUI)
        {
            return (
                <MyForm callBack={this.refreshUI} submiturl="/api/interns/createintern" fields={["name","rating"]}/>
                )
        }

        if(addTaskUI)
        {
            return <MyForm callBack={this.refreshUI} submiturl="/api/tasks/add_task" fields={["taskName","description"]} />
        }

        return (

                 <div className="page">
                 <div align="right">
                 <div> {this.state.user.email} </div>
                 <Button className="button-inline" onClick={this.logout}>Logout</Button>
                 </div>
                 <div className="table-header">
                 <span style={largeColumn}>
                 <Button className="button-inline" onClick={this.addIntern}>Add Interns</Button>
                 </span>
                 <span>
                 <Button className="button-inline" onClick={this.addTask}> Add a Task </Button>
                 </span>
                 </div>
                    <h2>Interns List</h2>
                    <div className="table">
                        <div className="table-header">
                            <span style={largeColumn}> Name </span>
                            <span style={largeColumn}> Rating </span>
                            <span style={largeColumn}> Tasks </span>
                        </div>
                        {
                        <Table list={interns} onDelete={this.onDelete} callBack={this.refreshUI} tag={Intern} />
                        }
                    </div>
                 </div>

            );
        }
        else
        {
            return <div id="firebaseui-auth-container"></div>
        }
    }

}




export default App;
