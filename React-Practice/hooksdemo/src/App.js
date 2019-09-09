import React,{useState,useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
import {VictoryChart,VictoryLine} from 'victory'
import './App.css';

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
  const classes = useStyles();

  useEffect(()=>
  {
    async function fetchData()
    {
      const response = await fetch('https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=S9C3XDXUHXF2Q4BB');
      const data = await response.json();
      setStock(data);
    }
    fetchData();
  },[setStock])

  function transformData(column)
  {
    
    if(stock)
    {
      console.log(Object.keys(stock['Time Series (5min)']).map(key=>{return {x: new Date(key),y:parseFloat(stock['Time Series (5min)'][key][column]) };}));
      return Object.keys(stock['Time Series (5min)']).map(key=>{return {x: new Date(key),y:parseFloat(stock['Time Series (5min)'][key][column]) };});
    }
    return null;
  }
  
  return (
    <div className={classes.root}>
      {
      stock ? <VictoryChart data={transformData('1. open')} height={250}>
      <VictoryLine 
        interpolation="linear"
        data={transformData('1. open')}  
        style={{data: {stroke: "#c43a31", strokeWidth: 1}}}
      />
    </VictoryChart>: null
      }
    </div>
  );
}

export default App;
