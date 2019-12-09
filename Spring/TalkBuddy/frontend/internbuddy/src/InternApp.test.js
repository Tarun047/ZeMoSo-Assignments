import '@testing-library/jest-dom/extend-expect'
import React from 'react'
import {render, fireEvent, getByLabelText, getByTestId} from '@testing-library/react'
import InternApp from './InternApp'
import {Provider} from 'react-redux'
import Store from './Store'
import expectExport from 'expect'
//To Enable mocking of firebase functions
var jest = require('jest');
  jest.mock('./Login/firebase.js', () => {
    return {};
  });
function renderWithRedux(ui) {
    return {
      ...render(<Provider store={Store}>{ui}</Provider>),
      // adding `store` to the returned utilities to allow us
      // to reference it in our tests (just try to avoid using
      // this to test implementation details).
      Store,
    }
  }
test('Tests if Intern Component mounts without crashing', async () => {
  const mockIntern = {name:'Tarun',assignments:[]}
  renderWithRedux(<InternApp intern={mockIntern}/>)
})
test('Tests if Intern component displays the basic layout',async ()=>{
  const mockIntern = {name:'Tarun',assignments:[]}
  const {getByText,getByTestId} = renderWithRedux(<InternApp intern={mockIntern}/>)
  expect(getByText("Intern Application")).toBeInTheDocument()
  expect(getByText("Logout")).toBeInTheDocument()
  expect(getByTestId('name-placeholder')).toBeInTheDocument()
  expect(getByText("Logout")).toBeInTheDocument()
  expect(getByText("Filter")).toBeInTheDocument()
  expect(getByText("Assignments")).toBeInTheDocument()
})

test('Tests if the Intern component displays the information passed correctly',async ()=>{
  const mockIntern = {name:'Tarun',assignments:[{task:{name:'Spring Boot',description:'Sample description 1',deadline:'28-10-19'},status:'OPEN'}]}
  const {getByText,getByTestId} = renderWithRedux(<InternApp intern={mockIntern} />)
  expect(getByText(mockIntern.name,{exact:false})).toBeInTheDocument()
  const assignmentContainer = getByTestId('assignment-container')
  expect(assignmentContainer.children.length).toBe(mockIntern.assignments.length)
})
