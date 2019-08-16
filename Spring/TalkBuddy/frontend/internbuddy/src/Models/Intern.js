
import React,{Component} from 'react';
import Select from 'react-select';
import {Button} from'../Layout/BaseLayout.js'

const largeColumn = {width:'40%',};
const midColumn = {width:'30%',};
const smallColumn = {width:'10%'};


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




export default Intern;