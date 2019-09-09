import React,{useState,useEffect} from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const [count,setCount] = useState(0);
  const [title,setTitle] = useState(0);
  function increment()
  {
    setCount(count+1);
  }

 

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <div>
          <button onClick={increment}>Click Me</button>
          <br/>Current Count is:{count}<br/>
          <input type="text" value={title} />
        </div>
      </header>
    </div>
  );
}

export default App;
