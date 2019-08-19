import React,{Component} from 'react';
import {Table,Button} from '../Layout/BaseLayout.js'
class MyForm extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
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

     await fetch(this.props.submiturl, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(this.state)
    });
    if(this.props.callBack)
        this.props.callBack(event);
  }

  render() {
    return (
      <form onSubmit={this.postchanges}>{
      this.props.fields.map(
        field=>
        <div>
          <p>Enter {field} </p>
          <input
            type='text'
            name={field}
            onChange={this.myChangeHandler}
          /><br />
        </div>
      )
      }
      <br />
      <Button onClick={this.postchanges}>Submit</Button>
      </form>
    );
  }
}

export default MyForm;