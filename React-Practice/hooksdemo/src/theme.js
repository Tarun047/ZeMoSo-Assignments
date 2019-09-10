import { createMuiTheme } from 'material-ui/styles';
import blue from '@material-ui/core/colors/blue'
import grey from '@material-ui/core/colors/grey'
export default createMuiTheme({
  palette: {
    primary: grey,
    secondary: blue // Indigo is probably a good match with pink
  }
});