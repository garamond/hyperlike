import React from 'react/addons';

export var Row = React.createClass({
	render: function() {
		return (
			<div className='row'>
				{this.props.children}
			</div>
		);
	}
})

export var Alert = React.createClass({
	propTypes: {
		message: React.PropTypes.string.isRequired
	},
	render: function() {
		var ok = this.props.onOk ? <div><button className='btn btn-default' onClick={this.props.onOk}>OK</button></div> : null
		return (
			<div className='overlay'>
				<div style={{padding:8, width: 200, height: 100, 
						margin: '200px auto auto auto', textAlign: 'center', backgroundColor: '#fff'}}>
					<p>{this.props.message}</p>
					{ok}
				</div>
			</div>
		);
	}	
})

export var Header = React.createClass({
	render: function() {
		return (
			<Row>
				<div style={{padding: 10}}>
					<h2 style={{margin: 0}}>{this.props.title}</h2>
				</div>
			</Row>		
		);
	}
})

export var GeoMixin = {
	findLocation: function() {
	    navigator.geolocation.getCurrentPosition((position) => {
	    	const {latitude: lat, longitude: lon} = position.coords;
	    	this.setState({pos: {lat: lat, lon: lon}});
	    });
	}
}