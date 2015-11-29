require('../dist/hyperlike.css')

import React from 'react/addons';
import ReactRouter, {
	Route,
    DefaultRoute,
    RouteHandler,
    Link,
    NotFoundRoute,
    Redirect
} from 'react-router';

import Collector from './collect/Collector';
import Reporter from './report/Reporter';

var HyperlikeClient = React.createClass({
    render: function() {
        return (
            <div className='container'>
                <RouteHandler />
            </div>
        );
    }
});

var routes = (
    <Route handler={HyperlikeClient}>
    	<DefaultRoute handler={Collector} />
		<Route name='collect' handler={Collector} />
		<Route name='report' handler={Reporter} />
    </Route>
);

ReactRouter.run(routes, (Handler) => {
    React.render(<Handler />, document.body);
});	

