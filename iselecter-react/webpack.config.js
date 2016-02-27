module.exports = {
    context: __dirname,
    entry: {
    	'iscroll':["./iscroll.js", "./main_iscroll.js"],
    	'iselect':["./iscroll.js", "./iselect.js", "./main_iselect.js"]
    },
    output: {
        path: __dirname + "/dist",
        filename: "[name].bundle.js"
    },
    module: {
        loaders: [{
            test: /\.js[x]?$/,
            exclude: /node_modules/,
            loader: 'babel-loader?presets[]=es2015&presets[]=react'
        }, { 
        	test: /\.css$/, 
        	loader: 'style-loader!css-loader' 
        }, ]
    }
};
