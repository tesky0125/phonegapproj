module.exports = function(grunt) {
    // ��Ŀ����
    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        concat: {
            options: {
                separator: ';'
            },
            dist: {
                src: ['src/jquery.js', 'src/backbone.js', 'src/underscore.js'],
                dest: 'dest/<%= pkg.file %>.js'
            }
        },
        uglify: {
            options: {
                banner: '/*! <%= pkg.file %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
            },
            build: {
                src: 'dest/<%= pkg.file %>.js',
                dest: 'dest/<%= pkg.file %>.min.js'
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-concat');
    grunt.loadNpmTasks('grunt-contrib-uglify');

    // Ĭ������
    grunt.registerTask('default', ['concat', 'uglify']);
}