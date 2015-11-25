module.exports = function (grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        requirejs: {
            main: {
                options: {
                    baseUrl: "src/",
                    //输出目录
                    dir: "dest/",
                    //配置所有的path，类似requirejs.config({paths:{})
                    paths: {
                        $: "libs/jquery",
                        _: "libs/underscore",
                        B: "libs/backbone",
                        test1: "test1",
                        test2: "test2",
                        test: "test"
                    },
                    modules:[
                        {
                            name:'text',//直接指向文件
                            include:'text'//直接指向文件，根据文件搜索
                        }，
                        {
                            name:'libs',//直接指向文件
                            include:['$','_','B']//指向paths，根据paths搜索
                        },
                        {
                            name:'test',//直接指向文件
                            include:['test1','test2','test']//define in paths or modules
                        }
                    ],
                    //移除合并后的文件，配合modules使用
                    removeCombined: true,
                    // include在最外层时，将包含的文件打包成单一文件，需指定out目录
                    // include: [//define in paths
                    //     // "$",
                    //     // "_",
                    //     // "B",
                    //     "test1",
                    //     "test2",
                    //     "test"
                    // ],
                    // out: "dest/libs_r.js"
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-contrib-requirejs');

    grunt.registerTask('default', ['requirejs']);
}