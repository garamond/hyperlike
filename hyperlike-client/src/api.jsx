export var ApiMixin = {
	loadConfig: function() {
		$.getJSON('/api/config').done(	
			(config) => { this.setState({config: config}) }
		).fail(
			(e) => { console.error(e) }
		)
	},
	loadResult: function() {
		$.getJSON('/api/report').done(
			(result) => { this.setState({result: result}) }
		).fail(
			(e) => { console.error(e) }
		)
	},
	postValue: function(payload, callback) {
      $.ajax({
          url: '/api/collect',
          method: 'POST',
          data: JSON.stringify(payload),
          contentType: 'application/json',
          success: callback,
          error: function (xhr, status, err) {
              console.error(xhr, status, err);
          }.bind(this)
      });
	}
}