import React from 'react/addons';

export default React.createClass({
    componentDidMount: function() {
        var map = L.map(this.refs.map.getDOMNode(), {
            maxZoom: 17,
            minZoom: 16,
            zoomControl:false,
            layers: [
                L.tileLayer(
                    'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                    {
                    	attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>'
                    })
            ],
        });

        map.on('click', this.onMapClick);
        map.fitWorld();
        this.setState({map: map})
    },
    componentWillReceiveProps: function(nextProps) {
        this.state.map.setMaxBounds(nextProps.config.bounds)
    	this.setLoc(nextProps.pos.lat, nextProps.pos.lon);
    },
    onMapClick: function(e) {
        this.setLoc(e.latlng.lat, e.latlng.lng)
        this.props.onChange(e.latlng.lat, e.latlng.lng);
    },
    setLoc: function(lat, lon) {
        this.state.marker && this.state.map.removeLayer(this.state.marker);
        var marker = L.marker([lat, lon]);
        this.state.map.addLayer(marker)
        this.state.map.setView([lat, lon], this.state.map.getZoom() || 16);
        this.setState({marker: marker})
    },
    render: function() {
        return (
            <div style={{height: 200}}>
                <div ref='map' className='map'></div>
            </div>
        );
    }
})