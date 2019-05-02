var diningTableVM = new Vue({
	el : '#dining-table',
	data : {
		diningTableName : '',

		addOrUpdateFlag : false,
		actionTitle : '',
		editObj : {},

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
				url : '/diningTable/findDiningTableByPage',
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
						diningTableName : that.diningTableName
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
					title : '排序号'
				}, {
					field : 'diningTableName',
					title : '餐桌名'
				}, {
					field : 'createTime',
					title : '创建时间'
				}, {
					field : 'stateName',
					title : '状态'
				}, {
					title : '操作',
					formatter : function(value, row, index) {
						var btns = [];
						if (row.state == '2') {
							btns.push('<button type="button" class="withdraw-the-table-btn btn btn-outline-secondary btn-sm" style="margin-right: 4px;">退桌</button>');
						}
						btns.push('<button type="button" class="edit-btn btn btn-outline-success btn-sm" style="margin-right: 4px;">编辑</button>');
						btns.push('<button type="button" class="del-btn btn btn-outline-danger btn-sm">删除</button>');
						return btns.join('');
					},
					events : {
						'click .withdraw-the-table-btn' : function(event, value, row, index) {
							that.withdrawTheTable(row.id);
						},
						'click .edit-btn' : function(event, value, row, index) {
							that.openEditModal(row.id);
						},
						'click .del-btn' : function(event, value, row, index) {
							that.del(row.id);
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

		openAddModal : function() {
			this.addOrUpdateFlag = true;
			this.actionTitle = '新增餐桌';
			this.editObj = {
				orderNo : '',
				diningTableName : ''
			};
		},

		openEditModal : function(id) {
			var that = this;
			that.$http.get('/diningTable/findById', {
				params : {
					id : id
				}
			}).then(function(res) {
				that.editObj = res.body.data;
				that.addOrUpdateFlag = true;
				that.actionTitle = '编辑餐桌';
			});
		},

		addOrUpdate : function() {
			var that = this;
			var editObj = that.editObj;
			if (editObj.orderNo == null || editObj.orderNo == '') {
				layer.alert('请输入排序号', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (editObj.diningTableName == null || editObj.diningTableName == '') {
				layer.alert('请输入餐桌名', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/diningTable/addOrUpdateDiningTable', editObj).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.addOrUpdateFlag = false;
				that.refreshTable();
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
		},

		del : function(id) {
			var that = this;
			layer.confirm('确定要删除吗?', {
				icon : 7,
				title : '提示'
			}, function(index) {
				layer.close(index);
				that.$http.get('/diningTable/delById', {
					params : {
						id : id
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