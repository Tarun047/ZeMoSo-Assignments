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

    componentWillMount()
    {
      auth.onAuthStateChanged((user) => { this.setState({user}) });

    }
    async componentDidMount()
    {
        const response = await fetch('/api/interns/')
        const body = await response.json()
        this.setState({interns:body,isLoading:false,addUI:false});
        console.log(this.state.interns);
         auth.onAuthStateChanged((user) => { this.setState({user}) });
         if(!this.state.user)
               {
                      startFirebaseUI("#firebaseui-auth-container");
               }

    }

    constructor(props)
    {
        super(props);
        this.onDismiss = this.onDismiss.bind(this);
        this.addIntern = this.addIntern.bind(this);
        this.refreshUI = this.refreshUI.bind(this);
        this.onDelete = this.onDelete.bind(this);
        this.addTask = this.addTask.bind(this);
        this.login =  this.login.bind(this);
        this.logout = () => { auth.signOut().then(this.forceUpdate())}
    }

    login(event,values)
    {
        console.log(values)
        auth.signInWithEmailAndPassword(values[0], values[1]).catch(function(error) {
          var errorCode = error.code;
          var errorMessage = error.message;
          alert(errorMessage);
        });

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




        const {user,interns,isLoading,addUI,addTaskUI,isLoggedIn}=this.state;
        if(isLoading)
        {
            return <p>Loading...</p>;
        }


        if(user){
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
                 <div> {user.email} </div>
                 <Button onClick={this.logout}>Logout</Button>
                 <div className="table-header">
                 <span style={largeColumn}>
                 <Button onClick={this.addIntern}>Add Interns</Button>
                 </span>
                 <span>
                 <Button onClick={this.addTask}> Add a Task </Button>
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
