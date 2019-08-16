import React,{Component} from 'react';
import MyForm from './BaseUI/BasicForms.js'
import logo from './logo.svg';
import './App.css';
import Select from 'react-select';



//Styling area
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






class Intern extends React.Component
{


     async componentDidMount()
        {
            const response = await fetch('/api/tasks/');
            const body = await response.json();
            const lis = []
            body.forEach(task=>lis.push({value:task.id,label:task.taskName}))
            this.setState({allTasks:lis});
        }


    constructor(props)
    {
        super(props);
        this.state = {
            id:props.id,
            name:props.name,
            rating:props.rating,
            assignments:props.assignments,
            isExpanded:false,
            isAssignView:false,
            selections:null
        };
        this.onExpand=this.onExpand.bind(this);
        this.onCollapse=this.onCollapse.bind(this);
        this.onRemoveTask=this.onRemoveTask.bind(this);
        this.setAssignView=this.setAssignView.bind(this);
        this.onSelectTask=this.onSelectTask.bind(this);
        this.postAssignments=this.postAssignments.bind(this);
        console.log(this.state.assignments);
    }

    onExpand(event)
    {

        this.setState({isExpanded:true});
    }

    onCollapse(event)
    {
        this.setState({isExpanded:false});
    }

   onSelectTask(choices)
   {
        if(choices)
            this.setState({selections:choices.map(choice=>choice.value)});
        else
            this.setState({selections:[]});
        console.log(this.state.selections);
   }

   setAssignView()
   {
    this.setState({isAssignView:true,selections:null});
    console.log(this.state.selections)
   }


   onRemoveTask(event,internId,assignmentId)
    {

        fetch('/api/interns/'+internId+"/remove_task"+'?taskIds='+assignmentId,{method:'DELETE'});
        const assignmentFilter = assignment=>assignment.id!==assignmentId;
        const newAssignments = this.state.assignments.filter(assignmentFilter);
        this.setState({assignments:newAssignments});
    }


    async postAssignments(event,internId)
    {
        if(this.state.selections){
             const response = await fetch('/api/interns/'+internId+"/assign_task"+'?taskIds='+this.state.selections.join(),{method:'POST'});
             const body = await response.json();
             this.setState({isAssignView:false,assignments:body.assignments});
        }
        this.setState({isAssignView:false});
    }

    render()
    {
        if(this.state.isExpanded)
        {

            return(
                <div key={this.state.id} className="table-row">
                          <span style={midColumn}>
                              <a href={this.state.name}>{this.state.name}</a>
                          </span>
                          <span style={midColumn}>{this.state.rating}</span>
                          <span style={largeColumn}>
                          <Button className="button-inline" onClick={this.onCollapse}>
                               Collapse
                           </Button><br />
                           <ol>
                           {
                            this.state.assignments.map(
                                assignment=>
                                <li key={assignment.task.id}>
                                    <b>{assignment.task.taskName}</b>
                                    <br/>
                                    {assignment.task.description}
                                    <br/>
                                    <Button className="button-inline" onClick={(event)=>this.onRemoveTask(event,this.state.id,assignment.id)}>Remove Task</Button>
                               </li>
                            )
                           }
                           </ol>
                           </span>
                           <span style={smallColumn}>
                           <Button className="button-inline" onClick={()=>this.props.onDelete(this.state.id)}>
                               Delete
                           </Button>
                           </span>
                 </div>

            );
        }
        if(this.state.isAssignView)
        {
            return(
                <div>
                    <Select
                        isMulti
                        options={this.state.allTasks}
                        onChange={this.onSelectTask}
                    />
                    <br />
                    <Button className="button-inline" onClick={(event)=>this.postAssignments(event,this.state.id)}> Post Assignments </Button>
                </div>
            )
        }
        return(
                <div key={this.state.id} className="table-row">
                          <span style={midColumn}>
                             {this.state.name}
                          </span><br/>
                          <span style={midColumn}>{this.state.rating}</span><br />
                          <span style={smallColumn}>
                          <Button className="button-inline" onClick={this.onExpand}>
                               Expand
                           </Button>
                           </span>
                           <span style={smallColumn}>
                                <Button className="button-inline" onClick={this.setAssignView}>
                                    Assign Task
                                </Button>
                           </span>
                           <span style={smallColumn}>
                           <Button className="button-inline" onClick={()=>this.props.onDelete(this.state.id)}>
                               Delete
                           </Button>
                           </span>
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
