module.exports = function(grunt) {
	grunt.initConfig({
		rev: {
			options: {
				encoding: 'utf8',
				algorithm: 'md5',
				length: 8
			},
			assets: {
				files: [{
					src: ['dest/text1.txt']
				}]
			}
		}
	});
	grunt.loadNpmTasks('grunt-rev');
	// grunt.registerTask('default', 'rev');

	grunt.registerTask('md5', function() {
		var fs = require('fs');
		var md5 = require('md5');
		console.log(md5('message'));

		var data = fs.readFileSync('index.html');
		console.log(md5(data));
	});

	grunt.registerTask('default', 'md5');
};