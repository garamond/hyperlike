import React from 'react/addons';
import {ApiMixin} from '../api';
import {Row, Alert, Header, GeoMixin} from '../common';
import ReporterMap from './ReporterMap';

export default React.createClass({
	mixins: [ApiMixin],
	getInitialState: function() {
		return {
			config: null 
		};
	},
	componentDidMount: function() {
		this.loadConfig();
		this.loadResult();
	},
	render: function() {
		return (this.state.config ? <ReportingClientImpl config={this.state.config} result={this.state.result} /> : <Alert message='Initializing...' /> );
	}
})

export var ReportingClientImpl = React.createClass({
	mixins: [GeoMixin],
	getInitialState: function() {
		return {
			pos: {lat: 0, lon: 0}
		};
	},
	componentDidMount: function() {
		this.findLocation();
	},
	render: function() {
		return (
			<div>
				<Header title={this.props.config.title} />
				<ReporterMap config={this.props.config} pos={this.state.pos} result={this.props.result} />
			</div>
		);
	}
})


