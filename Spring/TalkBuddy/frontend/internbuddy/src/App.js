import React,{Component} from 'react';
import logo from './logo.svg';
import './App.css';

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
        fetch('/api/interns/removeintern/'+id,{method:'DELETE'}).
        then(this.onDismiss(id));
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

                    {
                      <Table list={interns} onDismiss={this.onDismiss} onDelete={this.onDelete} callback={this.refreshUI}/>

                    }

                 </div>

            );
    }
}




class MyForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      name: '',
      rating: null,
    };

  }
  myChangeHandler = (event) => {
    let nam = event.target.name;
    let val = event.target.value;
    this.setState({[nam]: val});
    this.postchanges = this.postchanges.bind(this);
  }

  async postchanges(event)
  {

     await fetch('/api/interns/createintern', {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name: this.state.name,
        rating: this.state.rating,
      })
    });
    this.props.callBack(event);
  }

  render() {
    return (
      <form onSubmit={this.postchanges}>
      <p>Enter name:</p>
      <input
        type='text'
        name='name'
        onChange={this.myChangeHandler}
      />
      <p>Enter rating:</p>
      <input
        type='text'
        name='rating'
        onChange={this.myChangeHandler}
      />
      <br />
      <input type="submit" value="Submit" />
      </form>
    );
  }
}

class Intern extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = {
            id:props.id,
            name:props.name,
            rating:props.rating,
            tasks:props.tasks,
            isExpanded:false,
        };
        this.onExpand=this.onExpand.bind(this);
        this.onCollapse=this.onCollapse.bind(this);
        this.onRemoveTask=this.onRemoveTask.bind(this);
    }

    onExpand(event)
    {
        event.preventDefault();
        this.setState({isExpanded:true});
    }

    onCollapse(event)
    {
        event.preventDefault();
        this.setState({isExpanded:false});
    }

    onRemoveTask(event,internId,taskId)
    {
        event.preventDefault();
        fetch('/api/interns/'+internId+"/remove_task"+'?taskIds='+taskId,{method:'DELETE'});
        const taskFilter = id=>id!=taskId;
        const newTasks = this.state.tasks.filter(taskFilter);
        this.setState({tasks:newTasks})
    }

    render()
    {
        if(this.state.isExpanded)
        {

            return(
                <div key={this.state.id} className="table-row">
                          <span style={largeColumn}>
                              <a href={this.state.name}>{this.state.name}</a>
                          </span><br/>
                          <span style={midColumn}>{this.state.rating}</span>
                          <span style={largeColumn}>
                          <Button className="button-inline" onClick={this.onCollapse}>
                               Collapse
                           </Button><br />
                           <ol>
                           {
                            this.state.tasks.map(
                                task=>
                                <li>
                                    <b>{task.taskName}</b>
                                    <br/>
                                    {task.description}
                                    <br/>
                                    <Button className="button-inline" onClick={(event)=>this.onRemoveTask(event,this.state.id,task.id)}>Remove Task</Button>

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

            return(

                <div key={this.state.id} className="table-row">
                          <span style={largeColumn}>
                              <a href={this.state.name}>{this.state.name}</a>
                          </span><br/>
                          <span style={midColumn}>{this.state.rating}</span><br />
                          <span style={smallColumn}>
                          <Button className="button-inline" onClick={this.onExpand}>
                               Expand
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
          <Intern id={item.id} name={item.name} rating={item.rating} tasks={item.tasks} onDelete={onDelete} callBack={callBack}/>
        )}
      </div>

const Search = ({ value, onChange, children })=>
      <form>
        {children}
        <input
          type="text"
          value={value}
          onChange={onChange}
        />
      </form>

const Button = ({onClick,className="",children})=>
      <button
        onClick={onClick}
        className={className}
        type="button"
      >
        {children}
      </button>

export default App;
