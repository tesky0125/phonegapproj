var app = app || {};
(function(){
	app.AppView = Backbone.View.extend({
		el:"#todoapp",
		statusTemplate:_.template($('#stats-template').html()),
		events:{
			'keypress #new-todo': 'createOnEnter',
			'click #clear-completed': 'clearCompleted',
			'click #toggle-all': 'toggleAllComplete'
		},
		initialize:function(){
			this.allCheckbox = this.$('#toggle-all')[0];
			this.$input = this.$('#new-todo');
			this.$footer = this.$('#footer');
			this.$main = this.$('#main');
			this.$list = $('#todo-list');

			
		},
		render:function(){

		},
		createOnEnter:function(e){
			if (e.which === ENTER_KEY && this.$input.val().trim()) {
				app.todos.create(this.newAttributes());
				this.$input.val('');
			}			
		},
		toggleAllComplete:function(){

		},
		clearCompleted:function(){

		}
	});
}());