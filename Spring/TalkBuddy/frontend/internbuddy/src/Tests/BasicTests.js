import React from 'react'
import expect from 'expect'
import { shallow, mount } from 'enzyme'


import InternApp from '../InternApp'
import Task from '../Models/Task'
const data = fetch('localhost:8080/api/interns/273')
const formatted_result = data.json()


describe('Intern Component', () => {
    it('calls componentDidMount', () => {
      const wrapper = mount(<InternApp intern={formatted_result}/>);
      expect(Foo.prototype.componentDidMount).to.have.property('callCount', 1);
    });
  });