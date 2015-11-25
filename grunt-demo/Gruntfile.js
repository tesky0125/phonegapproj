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
        },
        md5: {
            compile: {
                files: {
                    'dest/md5/': 'src/**/*'
                },
                options: {
                    encoding: null,
                    keepBasename: true,
                    keepExtension: true,
                    afterEach: function(fileChange, options) {
                    	console.log(fileChange);
                    },
                    after: function(fileChanges, options) {
                        
                    }
                }
            }
        }
    });


    grunt.loadNpmTasks('grunt-rev');
    grunt.loadNpmTasks('grunt-md5');
    grunt.registerTask('default', 'md5');

/*    grunt.registerTask('md5', function() {
        var fs = require('fs');
        var md5 = require('md5');
        console.log(md5('message'));

        var data = fs.readFileSync('index.html');
        console.log(md5(data));
    });

    grunt.registerTask('default', 'md5');*/
};
