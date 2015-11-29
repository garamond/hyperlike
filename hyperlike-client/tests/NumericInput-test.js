jest.dontMock('../src/collect/NumericInput')
var React = require.requireActual('react/addons');

describe('NumericInput', () => {
	it('accepts configured numeric input', () => {

    	var TestUtils = React.addons.TestUtils;

		var NumericInput = require('../src/collect/NumericInput');

		var val;
		var handleChange = (e) => {
			val = e.target.value;
		}
    	var component = TestUtils.renderIntoDocument(
      		<NumericInput config={{min:0, max:10, step: 1}} value={val} onChange={handleChange}/>
    	);

    	var input = TestUtils.findRenderedDOMComponentWithTag(component, 'input');
		TestUtils.Simulate.change(input, {target: {value: 9}});

		expect(val).toBe(9);

	})
})