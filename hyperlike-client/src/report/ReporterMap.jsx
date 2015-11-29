import React from 'react/addons';

export default React.createClass({
    getInitialState: function() {
        return {
            selected: null
        };
    },
	getDefaultProps: function() {
		return {
			result: {}
		};
	},
    componentDidMount: function() {
        var map = L.map(this.refs.map.getDOMNode(), {
            maxZoom: 17,
            zoomControl:false,
            layers: [
                L.tileLayer(
                    'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
                    {
                    	attribution: '&copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>'
                    })
            ],
        });
        map.fitWorld();
        this.setState({map: map})
    },
    componentWillReceiveProps: function(nextProps) {
    	this.state.map.fitBounds(nextProps.config.bounds)
    	this.state.map.setMaxBounds(nextProps.config.bounds)
        var marker = L.marker([nextProps.pos.lat, nextProps.pos.lon]);
        this.state.map.addLayer(marker);
        this.renderGrid();
    },
    handleGridClick: function(data) {
        //this.setState({selected: data})
        console.log(data)
    },
    renderGrid: function() {
    	var res = this.props.result.values;
    	if (res) {
    		var grid = [];
	  		var gridSize = this.props.config.analysis.grid;
	   		var max = this.props.result.meta.max;
    		var idx = 0;
    		for (var i=0; i<gridSize; i++) {
    			for (var j=0; j<gridSize; j++) {
                    var currRes = res[idx];
                    if (currRes) {
                        var southWest = currRes.area.coordinates[0][0]
                        var northEast = currRes.area.coordinates[0][2]
                        var bounds = [[southWest[1], southWest[0]], [northEast[1], northEast[0]]]; // lat lon vertauscht
                        var dz = max / this.props.config.display.colors.length;
                        var metric = this.props.config.display.metric;
                        var val = currRes.computed[metric] || currRes[metric]
                        var colorIdx = null;
                        for (var k=0; k<this.props.config.display.colors.length; k++) {
                            if (val >= k * dz) {
                                colorIdx = k;
                            }
                        }
                        var color = (currRes.count >= this.props.config.display.quorum) && this.props.config.display.colors[colorIdx]
                        console.log(currRes.name+': '+val+' '+color)
                        var rect = L.rectangle(bounds, {weight: 1, color: '#000', fillColor: color, fillOpacity: color ? 0.3 : 0});
                        rect.on('click', this.handleGridClick.bind(this, currRes));
                        grid.push(rect);
                    }

					idx += 1;
    			}
    		}
    		var gridLayer = L.layerGroup(grid);
    		if (this.state.grid) {
    			this.state.map.removeLayer(this.state.grid);
    		}
   			this.state.map.addLayer(gridLayer);
   			this.setState({grid: gridLayer});
    	}
    },
    render: function() {
        return (
        	<div style={{height: 400}}>
            	<div ref='map' className='map'></div>
            </div>
        );
    }
})
