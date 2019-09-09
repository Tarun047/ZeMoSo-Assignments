import React,{useState,useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {VictoryChart,VictoryLine} from 'victory'
import './App.css';
import useDebounce from './debounce'

const useStyles = makeStyles(theme => 
  ({
    root: {
      display: 'flex',
      flexWrap: 'wrap',
      justifyContent: 'space-around',
      overflow: 'hidden',
      backgroundColor: theme.palette.background.paper,
    },
    gridList: {
      width: 500,
      height: 450,
    },
  }))


function App() {
  let [stock,setStock] = useState(null);
  let [intrest,setIntrest] = useState('')
  const debouncedSearchTerm = useDebounce(intrest, 500);
  const [isSearching, setIsSearching] = useState(false);
  
  const classes = useStyles();

  useEffect(()=>
  {
    async function fetchData()
    {
     
        const response = await fetch(`https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=${debouncedSearchTerm}&interval=5min&apikey=S9C3XDXUHXF2Q4BB`);
        const data = await response.json();
        setStock(data);
    }
    if(debouncedSearchTerm)
    {
      setIsSearching(true);
      fetchData().then(()=>setIsSearching(false))
    }
  },[debouncedSearchTerm])


  function transformData(column)
  {
    
    if(stock)
    {
      return Object.keys(stock['Time Series (5min)']).map(key=>{return {x: new Date(key),y:parseFloat(stock['Time Series (5min)'][key][column]) };});
    }
    return null;
  }

  function handleIntrestChange(event)
  {
    setIntrest(event.target.value);
  }
  
  return (
    <div className={classes.root}>
      <div>
        <input value={intrest} onChange={handleIntrestChange} />
      </div>
      {
      !isSearching ? <VictoryChart data={transformData('1. open')} height={250}>
      <VictoryLine 
        interpolation="linear"
        data={transformData('1. open')}  
        style={{data: {stroke: "#c43a31", strokeWidth: 1}}}
      />
    </VictoryChart>:<div> "Searching ..."</div>
      }
    </div>
  );
}

export default App;
