import React,{useState,useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {VictoryChart,VictoryLine} from 'victory'
import './App.css';
import useDebounce from './debounce'
import { spacing } from '@material-ui/system';

const useStyles = makeStyles(theme => 
  ({
    root: {
      display: 'flex',
      flexWrap: 'wrap',
      flexDirection: 'column',
      alignItems:'center',
      overflow: 'hidden',
      backgroundColor: theme.palette.background.paper,
      padding: '16'
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
        if('Time Series (5min)' in data)
          setStock(data);
    }
    if(debouncedSearchTerm)
    {
      setIsSearching(true);
      fetchData().then(()=>setIsSearching(false))
    }

    return function cleanup()
    {
      setStock(null);
    }
  },[debouncedSearchTerm])


  function transformData(column)
  {
      return Object.keys(stock['Time Series (5min)']).map(key=>{return {x: new Date(key),y:parseFloat(stock['Time Series (5min)'][key][column]) };});
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
      stock && !isSearching ? <VictoryChart data={transformData('1. open')} height={250}>
      <VictoryLine 
        interpolation="linear"
        data={transformData('1. open')}  
        style={{data: {stroke: "#c43a31", strokeWidth: 1}}}
      />
    </VictoryChart> : <div> Enter a valid term to Search</div>
      }
    </div>
  );
}

export default App;
