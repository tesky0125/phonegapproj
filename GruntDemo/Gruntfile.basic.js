module.exports = function(grunt) {
    // 项目配置
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

    // 默认任务
    grunt.registerTask('default', ['concat', 'uglify']);
}