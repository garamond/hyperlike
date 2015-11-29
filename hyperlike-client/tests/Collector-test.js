fs = require('fs');
jest.dontMock('../src/collect/Collector')
jest.dontMock('../src/common')
jest.dontMock('../src/collect/NumericInput')
var React = require.requireActual('react/addons');

navigator.geolocation = {
	getCurrentPosition: jest.genMockFunction().mockImplementation(function(callback) {
    	callback({coords: {latitude: 47.387984, longitude: 8.510121}})
    	return this;
  })
}

describe('Collector', () => {
	it('starts initializing after mount', () => {

    	var TestUtils = React.addons.TestUtils;
		  var Collector = require('../src/collect/Collector').default;

    	var component = TestUtils.renderIntoDocument(
      		<Collector />
    	);

    	var alert = TestUtils.findRenderedDOMComponentWithTag(component, 'p');
    	expect(alert.getDOMNode().textContent).toBe('Initializing...');

	})
})

describe('CollectorImpl', () => {
	it('implements the config correctly', () => {

    	var TestUtils = React.addons.TestUtils;
		var Collector = require('../src/collect/Collector').CollectorImpl;
		var config = JSON.parse(fs.readFileSync('../hyperlike-server/config.json', 'utf8'));

    	var component = TestUtils.renderIntoDocument(
      		<Collector config={config} />
    	);

    	var inputs = TestUtils.scryRenderedDOMComponentsWithTag(component, 'input');
		expect(inputs.length).toBe(3);

		for (var i=0; i<inputs.length; i++) {
			TestUtils.Simulate.change(inputs[i], {target: {value: 5}});			
		}
		expect(component.state.modified).toBe(true);
		expect(component.state.values.temp).toBe(5);
		expect(component.state.modified).toBe(true);

		component.handleLocationChange(1.0, 0.1)
		expect(component.state.pos.lat).toBe(1.0);
		expect(component.state.pos.lon).toBe(0.1);

		expect(component.validate()).toBe(true);

    	var button = TestUtils.findRenderedDOMComponentWithTag(component, 'button');
		TestUtils.Simulate.click(button.getDOMNode());

	})
})