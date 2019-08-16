import React,{Component} from 'react';
import MyForm from './BaseUI/BasicForms.js'
import Intern from './Models/Intern.js'
import logo from './logo.svg';
import './App.css';


const largeColumn = {width:'40%',};
const midColumn = {width:'30%',};
const smallColumn = {width:'10%'};



class App extends Component
{
    state={
        isLoading: true,
        interns:[],
        addUI:false,
    };

    async componentDidMount()
    {
        const response = await fetch('/api/interns/')
        const body = await response.json()
        this.setState({interns:body,isLoading:false,addUI:false});
        console.log(this.state.interns);
    }

    constructor(props)
    {
        super(props);
        this.onDismiss = this.onDismiss.bind(this);
        this.addIntern = this.addIntern.bind(this);
        this.refreshUI = this.refreshUI.bind(this);
        this.onDelete = this.onDelete.bind(this);
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
       this.setState({interns:body,isLoading:false,addUI:false});

    }

    addIntern()
    {
       this.setState({addUI:true})
    }

    onDelete(id)
    {
        fetch('/api/interns/removeintern/'+id,{method:'DELETE'}).then(this.onDismiss(id));
    }

    render()
    {
        const {interns,isLoading,addUI}=this.state;
        if(isLoading)
        {
            return <p>Loading...</p>;
        }

        if(addUI)
        {
            return (
                <MyForm callBack={this.refreshUI} />
                )
        }
        return (

                 <div className="page">
                 <Button onClick={this.addIntern}>Add Interns</Button>
                    <h2>Interns List</h2>
                    <div className="table">
                        <div className="table-header">
                            <span style={largeColumn}> Name </span>
                            <span style={largeColumn}> Rating </span>
                            <span style={largeColumn}> Tasks </span>
                        </div>
                        {
                        <Table list={interns} onDismiss={this.onDismiss} onDelete={this.onDelete} callBack={this.refreshUI}/>

                        }
                    </div>
                 </div>

            );
    }
}


const Table = ({list,onDismiss,onDelete,callBack})=>
      <div className="table">
        {list.map(item=>
          <Intern id={item.id} name={item.name} rating={item.rating} assignments={item.assignments} onDelete={onDelete} callBack={callBack}/>
        )}
      </div>

const Button = ({onClick,className="",children})=>
      <button
        onClick={onClick}
        className={className}
        type="button"
      >
        {children}
      </button>

export default App;
