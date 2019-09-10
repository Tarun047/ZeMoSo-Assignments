import React,{useState,useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {Container, TextField} from '@material-ui/core';
import {VictoryChart,VictoryLine} from 'victory';
import './App.css';
import useDebounce from './debounce'


const useStyles = makeStyles(theme => 
  ({
    root: {
      display: 'flex',
      flexWrap: 'wrap',
      flexDirection: 'column',
      alignItems:'center',
      overflow: 'hidden',
      margin: 16
    },
    gridList: {
      width: 500,
      height: 450,
    },
    searchLabel:
    {
      margin:8,
    },
    textField: {
      marginLeft: theme.spacing(1),
      marginRight: theme.spacing(1),
    }
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
    setIntrest(event.target.value.toUpperCase());
  }
  
  return (
    <Container className={classes.root}>
       <TextField
        required
        id="outlined-required"
        label="Stock Code"
        className={classes.textField}
        margin="normal"
        variant="outlined"
        value={intrest}
        onChange={handleIntrestChange}
      />
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
    </VictoryChart> : <div className={classes.searchLabel}> Enter a valid term to Search</div>
      }
    </Container>
  );
}

export default App;
