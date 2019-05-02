var placeOrderRecordVM = new Vue({
	el : '#place-order-record',
	data : {
		state : '',
		stateDictItems : [ {
			dictItemCode : '1',
			dictItemName : '未点菜'
		}, {
			dictItemCode : '2',
			dictItemName : '已点菜'
		}, {
			dictItemCode : '3',
			dictItemName : '通知结账'
		}, {
			dictItemCode : '4',
			dictItemName : '已结账'
		} ],
		showRecordFlag : true,
		showDetailsFlag : false,
		placeOrderRecordId : '',
		placeOrderRecord : {},
		orderDishesRecords : []
	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		this.initTable();
	},
	methods : {

		initTable : function() {
			var that = this;
			$('.common-table').bootstrapTable({
				classes : 'table table-hover',
				height : 490,
				url : '/diningTable/findPlaceOrderRecordByPage',
				pagination : true,
				sidePagination : 'server',
				pageNumber : 1,
				pageSize : 10,
				pageList : [ 10, 25, 50, 100 ],
				queryParamsType : '',
				queryParams : function(params) {
					var condParam = {
						pageSize : params.pageSize,
						pageNum : params.pageNumber,
						state : that.state
					};
					return condParam;
				},
				responseHandler : function(res) {
					return {
						total : res.data.total,
						rows : res.data.content
					};
				},
				columns : [ {
					field : 'orderNo',
					title : '订单号'
				}, {
					field : 'diningTableName',
					title : '餐桌'
				}, {
					field : 'bookTime',
					title : '订桌时间'
				}, {
					field : 'stateName',
					title : '状态'
				}, {
					field : 'settleAccountTime',
					title : '结账时间'
				}, {
					field : 'consume',
					title : '消费'
				}, {
					title : '操作',
					formatter : function(value, row, index) {
						var btns = [];
						if (row.state == '2' || row.state == '3' || row.state == '4') {
							btns.push('<button type="button" class="view-details-btn btn btn-outline-info btn-sm" style="margin-right: 4px;">查看详情</button>');
						}
						return btns.join('');
					},
					events : {
						'click .view-details-btn' : function(event, value, row, index) {
							that.showDetailsPage(row.id);
						}
					}
				} ]
			});
		},

		refreshTable : function() {
			$('.common-table').bootstrapTable('refreshOptions', {
				pageNumber : 1
			});
		},

		showRecordPage : function() {
			this.showRecordFlag = true;
			this.showDetailsFlag = false;
			this.refreshTable();
		},

		showDetailsPage : function(id) {
			var that = this;
			that.placeOrderRecordId = id;
			that.$http.get('/diningTable/findPlaceOrderRecordById', {
				params : {
					id : that.placeOrderRecordId
				}
			}).then(function(res) {
				that.placeOrderRecord = res.body.data;
				that.showRecordFlag = false;
				that.showDetailsFlag = true;
			});
			that.loadOrderDishesRecord();
		},

		loadOrderDishesRecord : function() {
			var that = this;
			that.$http.get('/diningTable/findOrderDishesRecordByBookingId', {
				params : {
					bookingId : that.placeOrderRecordId
				}
			}).then(function(res) {
				that.orderDishesRecords = res.body.data;
			});
		},

		settleAccount : function(id) {
			var that = this;
			that.$http.get('/diningTable/settleAccount', {
				params : {
					id : id
				}
			}).then(function(res) {
				that.showDetailsPage(that.placeOrderRecordId);
			});
		},

		updateOrderDishesRecordState : function(id, state) {
			var that = this;
			that.$http.get('/diningTable/updateOrderDishesRecordState', {
				params : {
					id : id,
					state : state
				}
			}).then(function(res) {
				that.loadOrderDishesRecord();
			});
		},

		withdrawTheTable : function(id) {
			var that = this;
			layer.confirm('确定要退桌吗?', {
				icon : 7,
				title : '提示'
			}, function(index) {
				layer.close(index);
				that.$http.get('/diningTable/withdrawTheTable', {
					params : {
						tableId : id
					}
				}).then(function(res) {
					layer.alert('操作成功!', {
						icon : 1,
						time : 3000,
						shade : false
					});
					that.refreshTable();
				});
			});
		}
	}
});