var hallVM = new Vue({
	el : '#hall',
	data : {
		freeDiningTables : [],
	},
	computed : {

	},
	created : function() {
	},
	mounted : function() {
		var that = this;
		that.loadAllAllFreeDiningTable();
	},
	methods : {
		loadAllAllFreeDiningTable : function() {
			var that = this;
			that.$http.get('/diningTable/findAllFreeDiningTable').then(function(res) {
				that.freeDiningTables = res.body.data;
				console.log(that.freeDiningTables);
			});
		},

		toOrderDishesPage : function(diningTableId) {
			window.location.href = '/front/order-dishes?diningTableId=' + diningTableId;
		},
	}
});