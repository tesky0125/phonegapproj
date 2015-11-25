requirejs.config({
    baseUrl: 'src/',
    paths: {
        $: "libs/jquery",
        _: "libs/underscore",
        B: "libs/backbone",
        text:"libs/text",
        test1: "test1",
        test2: "test2",
        test: "test",
        text1:'text1.txt'
    }
});

requirejs(['$', 'test', 'text!text1'], function   ($, test, text) {
    console.log(test.test1+','+test.test2);
    console.log(text);
});