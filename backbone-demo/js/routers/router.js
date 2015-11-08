var app=app||{};
(function(){
	app.TodoRouter = Backbone.Router.extend({
		routes:{
			'*filter': 'setFilter'
		},
		setFilter:function(param){
			app.TodoFilter = param || '';//Global object
			app.todos.trigger('filter');//TODO
		}
	});
	app.todoRouter = new app.TodoRouter();
	Backbone.history.start();
}());