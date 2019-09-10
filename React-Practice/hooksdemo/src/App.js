import React,{useState,useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {Container} from '@material-ui/core';
import {VictoryChart,VictoryLine} from 'victory';
import './App.css';
import useDebounce from './debounce'

const useStyles = makeStyles(theme => 
  ({
    body:
    {
      height:'100%',
    },
    root: {
      position:"absolute",
      display: 'flex',
      flexWrap: 'wrap',
      bottom:0,
      flexDirection: 'column',
      alignItems:'center',
      overflow: 'hidden',
      padding: '16',
      background:'#08F9DC',
      height: '100%'
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
     
        const response = await fetch(`https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=${debouncedSearchTerm}&interval=1min&apikey=S9C3XDXUHXF2Q4BB`);
        const data = await response.json();
        if('Time Series (1min)' in data)
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
      return Object.keys(stock['Time Series (1min)']).map(key=>{return {x: new Date(key),y:parseFloat(stock['Time Series (1min)'][key][column]) };});
  }

  function handleIntrestChange(event)
  {
    setIntrest(event.target.value);
  }
  
  return (
    <Container class={classes.root} height="100%" fixed>
      <div>
        <input value={intrest} onChange={handleIntrestChange} />
      </div>
      {
      stock && !isSearching ? 
      <VictoryChart data={transformData('1. open')} height={250} scale={{ x: "time" }} animate={{
        duration: 5000,
        onLoad: { duration: 100 }
      }} >
        <VictoryLine 
        interpolation="linear"
        data={transformData('1. open')}  
        style={{data: {stroke: "#c43a31", strokeWidth: 1}}}
        />
        <VictoryLine 
        interpolation="linear"
        data={transformData('2. high')}  
        style={{data: {stroke: "#D6D60C", strokeWidth: 1}}}
        />
        <VictoryLine 
        interpolation="linear"
        data={transformData('3. low')}  
        style={{data: {stroke: "#789394", strokeWidth: 1}}}
        />
    </VictoryChart> : <div> Enter a valid term to Search</div>
      }
    </Container>
  );
}

export default App;
