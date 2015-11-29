jest.dontMock('../src/common')
var React = require.requireActual('react/addons');

navigator.geolocation = {
	getCurrentPosition: jest.genMockFunction().mockImplementation(function(callback) {
    	callback({coords: {latitude: 1.0, longitude: 0.1}})
    	return this;
  })
}


describe('Alert', () => {
	it('behaves correctly', () => {

	    var TestUtils = React.addons.TestUtils;

		var Alert = require('../src/common').Alert;

		var ok = false;
		var onOk = function(e) {
			ok = true;
		}

	    var component = TestUtils.renderIntoDocument(
	      <Alert message='myAlert' onOk={onOk} />
	    );

	   	var p = TestUtils.findRenderedDOMComponentWithTag(component, 'p');
	   	expect(p.getDOMNode().textContent).toBe('myAlert')

	   	var button = TestUtils.findRenderedDOMComponentWithTag(component, 'button');
		TestUtils.Simulate.click(button);
		expect(ok).toBe(true)


	})
})

describe('Header', () => {
	it('displays title', () => {
	    var TestUtils = React.addons.TestUtils;
		var Header = require('../src/common').Header;
	    var component = TestUtils.renderIntoDocument(
	      <Header title='test' />
	    );
	   	var h2 = TestUtils.findRenderedDOMComponentWithTag(component, 'h2');
	   	expect(h2.getDOMNode().textContent).toBe('test')
	})
})

describe('GeoMixin', () => {
	it('finds the user’s location', () => {
	    var TestUtils = React.addons.TestUtils;
		var GeoMixin = require('../src/common').GeoMixin;

		var Component = React.createClass({
			mixins: [GeoMixin],
			componentDidMount: function() {
				this.findLocation();
			},
			render: function() {
				return null;
			}
		})

	    var component = TestUtils.renderIntoDocument(
	      <Component />
	    );

	    expect(component.state.pos.lat).toBe(1.0)
	    expect(component.state.pos.lon).toBe(0.1)

	})
})