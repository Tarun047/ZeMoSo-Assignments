import React,{Component} from 'react';

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

export default MyForm;