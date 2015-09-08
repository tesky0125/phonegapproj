var app=app||{};
(function(){
	app.TodoRouter = Backbone.Router.extend({
		routes:{
			'*filter': 'setFilter'
		},
		setFilter:function(param){
			app.TodoFilter = param || '';
			app.todos.trigger('filter');
		}
	});
	app.todoRouter = new app.TodoRouter();
	Backbone.history.start();
}());