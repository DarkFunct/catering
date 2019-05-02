var orderDishesVM = new Vue({
	el : '#order-dishes',
	data : {
		bookingTableRecord : {},
		styleOfCookings : [],
		selectedStyleOfCookingId : '',
		cookings : [],
		selectedCookingId : '',
		cookingCar : [],
		orderDishesRecords : [],
		showCookingListFlag : true,
		showPlacedOrderFlag : false,
		showCookingCarFlag : false,
	},
	computed : {
		subTotal : {
			get : function() {
				var cookingCar = this.cookingCar;
				if (cookingCar.length == 0) {
					return 0;
				}
				var subTotal = 0;
				for (var i = 0; i < cookingCar.length; i++) {
					subTotal += cookingCar[i].price;
				}
				return subTotal;
			}
		},
		total : {
			get : function() {
				var orderDishesRecords = this.orderDishesRecords;
				if (orderDishesRecords.length == 0) {
					return 0;
				}
				var total = 0;
				for (var i = 0; i < orderDishesRecords.length; i++) {
					total += orderDishesRecords[i].price;
				}
				return total;
			}
		}
	},
	created : function() {
	},
	mounted : function() {
		var that = this;
		that.booking();
	},
	methods : {
		noticeSettleAccount : function() {
			var that = this;
			that.$http.get('/diningTable/noticeSettleAccount', {
				params : {
					id : that.bookingTableRecord.id
				}
			}).then(function(res) {
				layer.alert('已通知服务员前来结账!', {
					icon : 1,
					time : 3000,
					shade : false
				});
			});
		},
		getUrlParam : function(paraName) {
			var url = document.location.toString();
			var arrObj = url.split('?');
			var arrPara = arrObj[1].split('&');
			for (var i = 0; i < arrPara.length; i++) {
		　　　　　　　　var arr = arrPara[i].split('=');
		　　　　　　　　if (arr != null && arr[0] == paraName) {
		　　　　　　　　　　return arr[1];
		　　　　　　　　}
		　　　}
			return '';
		},
		booking : function() {
			var that = this;
			that.$http.get('/diningTable/booking', {
				params : {
					id : that.getUrlParam('diningTableId')
				}
			}).then(function(res) {
				that.bookingTableRecord = res.body.data;
				that.loadAllStyleOfCooking();
			});
		},
		
		orderDishes : function() {
			var that = this;
			var param = {
				bookDiningTableRecordId : that.bookingTableRecord.id,
				cookingIds : []
			};
			for (var i = 0; i < that.cookingCar.length; i++) {
				param.cookingIds.push(that.cookingCar[i].id);
			}
			that.$http.post('/diningTable/orderDishes', param).then(function(res) {
				layer.alert('下单成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.cookingCar = [];
				that.showPlacedOrderListPage();
			});
		},
		showPlacedOrderListPage : function() {
			var that = this;
			that.$http.get('/diningTable/findOrderDishesRecordByBookingId', {
				params : {
					bookingId : that.bookingTableRecord.id
				}
			}).then(function(res) {
				that.orderDishesRecords = res.body.data;
				that.showCookingListFlag = false;
				that.showPlacedOrderFlag = true;
				that.showCookingCarFlag = false;
			});
		},
		showCookingListPage : function() {
			this.showCookingListFlag = true;
			this.showPlacedOrderFlag = false;
			this.showCookingCarFlag = false;
		},
		showCookingCarPage : function() {
			this.showCookingListFlag = false;
			this.showPlacedOrderFlag = false;
			this.showCookingCarFlag = true;
		},
		addToCookingCar : function(cooking) {
			this.cookingCar.push(cooking);
		},
		loadAllStyleOfCooking : function() {
			var that = this;
			that.$http.get('/cooking/findAllStyleOfCooking').then(function(res) {
				that.styleOfCookings = res.body.data;
				if (that.styleOfCookings.length > 0) {
					that.loadCooking(that.styleOfCookings[0].id);
				}
			});
		},

		loadCooking : function(styleOfCookingId) {
			var that = this;
			that.selectedStyleOfCookingId = styleOfCookingId;
			that.$http.get('/cooking/findCookingByStyleOfCookingId', {
				params : {
					styleOfCookingId : that.selectedStyleOfCookingId
				}
			}).then(function(res) {
				that.cookings = res.body.data;
			});
		},
	}
});