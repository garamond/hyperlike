import React from 'react/addons';
import {Navigation} from 'react-router'

import CollectorMap from './CollectorMap';
import NumericInput from './NumericInput';
import {ApiMixin} from '../api';
import {Row, Alert, Header, GeoMixin} from '../common';

export default React.createClass({
	mixins: [ApiMixin],
	getInitialState: function() {
		return {
			config: null 
		};
	},
	componentDidMount: function() {
		this.loadConfig();
	},
	render: function() {
		return (this.state.config ? <CollectorImpl config={this.state.config} /> : <Alert message='Initializing...' /> );
	}
})

export var CollectorImpl = React.createClass({
	mixins: [ApiMixin, GeoMixin, Navigation],
	propTypes: {
		config: React.PropTypes.object
	},
	getInitialState: function() {
		return {
			pos: {lat: 0, lon: 0},
			values: {}
		};
	},
	componentDidMount: function() {
		this.findLocation();
	},
	handleLocationChange: function(lat, lon) {
		this.setState({pos: {lat: lat, lon: lon}});
	},
	handleInput: function(id, e) {
		var v = {};
		v[id] = parseFloat(e.target.value);
		let nextState = React.addons.update(this.state, {values: {$merge: v}, modified: {$set: true}});
		this.setState(nextState);
	},
	handleSubmit: function() {
		this.postValue({values: this.state.values, pos: this.state.pos, tstamp: new Date()}, () => {
			this.transitionTo('report')
		})
	},
	validate: function() {
		var res = this.state.modified;
		this.props.config.input.forEach((x) => {
			if (!this.state.values[x.id]) {
				res = false;				
			}
		})
		return res;
	},
	render: function() {
		var alert = this.state.pos.lat != 0 ? null : <Alert message='Warten auf Position...' />
		var inputs = this.props.config.input.map((o, i) => {
			return (
				<Row key={o.id}>
					<NumericInput config={o} value={this.state.values[o.id]} onChange={this.handleInput.bind(this, o.id)} />
				</Row>
			);
		})
		return (
			<div>
				<Header title={this.props.config.title} />
				<div className='row'>
					<CollectorMap config={this.props.config} pos={this.state.pos} onChange={this.handleLocationChange} />
				</div>
				{inputs}
				<Row>
					<div style={{marginTop: 10, marginBottom: 100, padding: 8}}>
						<button 
							type="button" 
							onClick={this.handleSubmit} 
							className="btn btn-success btn-lg btn-block" 
							disabled={!this.validate()}>
								Abschicken
						</button>
					</div>
				</Row>
			</div>
		);
	}
})