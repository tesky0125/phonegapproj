module.exports = function(grunt) {
    grunt.initConfig({
        replace: {
            dist: {
                options: {
                    patterns: [{
                        match: 'name',
                        replacement: 'rylan:<%= grunt.template.today() %>.'
                    },
                    {
                        match: 'sex',
                        replacement: 'boy:<%= grunt.template.today() %>.'
                    }]
                },
                files: [{
                    expand: true,
                    flatten: true,
                    src: ['src/readme.txt'],
                    dest: 'dest/'
                }]
            }
        }
    });
    grunt.loadNpmTasks('grunt-replace');
    grunt.registerTask('default', 'replace');
};