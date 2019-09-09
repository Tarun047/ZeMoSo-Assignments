import React,{useState,useEffect} from 'react';
import { makeStyles } from '@material-ui/core/styles';
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
  let [stock,setStock] = useState({});
  const classes = useStyles();

  useEffect(()=>
  {
    async function fetchData()
    {
      const response = await fetch('https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=S9C3XDXUHXF2Q4BB');
      const data = await response.json();
      setStock(data['Time Series (5min)']);
    }
    fetchData();
  },[setStock])
  console.log(stock)
  return (

    <div className={classes.root}>
      {
        stock ? Object.keys(stock).map(
          time=>
          <div key={time}>
            {time}:
            {
              Object.keys(stock[time]).map(interval=>
                <div key={interval}>
                  {interval}:{stock[time][interval]}
                </div>
            )}
          </div>
        ):null
      }
    </div>
  );
}

export default App;
