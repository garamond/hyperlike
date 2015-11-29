import React from 'react';

export default React.createClass({
	propTypes: {
		config: React.PropTypes.object.isRequired,
		onChange: React.PropTypes.func.isRequired,
		value: React.PropTypes.number
	},
	getDefaultProps: function() {
		return {
			config: {min: 0, max: 10, step: 1}
		};
	},
	render: function() {
		return (
			<div>
				<div style={{padding: 10, fontSize: '18px', lineHeight: '20px'}}>{this.props.config.question} <strong>{this.props.value}</strong></div>
					<input name={this.props.config.id} type='range' min={this.props.config.min} max={this.props.config.max} step={this.props.config.step} value={this.props.value || this.props.config.initial} onChange={this.props.onChange}/>
			</div>
		);
	}
})

