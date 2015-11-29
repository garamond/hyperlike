fs = require('fs');
jest.dontMock('../src/report/Reporter')
jest.dontMock('../src/common')
var React = require.requireActual('react/addons');

navigator.geolocation = {
	getCurrentPosition: jest.genMockFunction().mockImplementation(function(callback) {
    	callback({coords: {latitude: 47.387984, longitude: 8.510121}})
    	return this;
  })
}

describe('Reporter', () => {
	it('starts initializing after mount', () => {

    	var TestUtils = React.addons.TestUtils;
		  var Reporter = require('../src/report/Reporter').default;

    	var component = TestUtils.renderIntoDocument(
      		<Reporter />
    	);

      var alert = TestUtils.findRenderedDOMComponentWithTag(component, 'p');
      expect(alert.getDOMNode().textContent).toBe('Initializing...');

	})
})

describe('ReporterImpl', () => {
  it('implements the config correctly', () => {

      var TestUtils = React.addons.TestUtils;
      var Reporter = require('../src/report/Reporter').ReportingClientImpl;
      var config = JSON.parse(fs.readFileSync('../hyperlike-server/config.json', 'utf8'));

      var component = TestUtils.renderIntoDocument(
          <Reporter config={config} />
      );

      var title = TestUtils.findRenderedDOMComponentWithTag(component, 'h2');
      expect(title.getDOMNode().textContent).toBe(config.title);
  })
})