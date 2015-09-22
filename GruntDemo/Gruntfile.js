module.exports = function (grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        requirejs: {
            main: {
                options: {
                    baseUrl: "",
                    paths: {
                        $: "src/jquery",
                        _: "src/underscore",
                        B: "src/backbone",
                        Test: "src/Test"
                    },
                    include: [
                        "$",
                        "_",
                        "B",
                        "Test"
                    ],
                    out: "dest/libs_require.js"
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-requirejs');

    grunt.registerTask('default', ['requirejs']);
}