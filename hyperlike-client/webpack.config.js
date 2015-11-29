module.exports = {
	entry: './src/app.jsx',
	output: {
		path: '../hyperlike-server/target/classes/static/',
		publicPath: '/assets/',
		filename: 'bundle.js'       
	},
	resolve: {
		extensions: ["", ".web.js", ".js", ".jsx"]
	},
	module: {
		loaders: [
			{ test: /\.jsx$/, exclude: /node_modules/, loader: "babel-loader" },
	    	{ test: /\.css$/, loader: 'style-loader!css-loader' },
	    	{ test: /\.(png|jpg|svg|ttf|woff|woff2|eot)$/, loader: 'url-loader?limit=8192' }
		]
	}
};