var styleOfCookingVM = new Vue({
	el : '#style-of-cooking',
	data : {
		styleOfCookingName : '',

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
				url : '/cooking/findStyleOfCookingByPage',
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
						styleOfCookingName : that.styleOfCookingName
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
					field : 'styleOfCookingName',
					title : '菜系名称'
				}, {
					field : 'createTime',
					title : '创建时间'
				}, {
					title : '操作',
					formatter : function(value, row, index) {
						return [ '<button type="button" class="edit-btn btn btn-outline-success btn-sm" style="margin-right: 4px;">编辑</button>', '<button type="button" class="del-btn btn btn-outline-danger btn-sm">删除</button>' ].join('');
					},
					events : {
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
			this.actionTitle = '新增菜系';
			this.editObj = {
				orderNo : '',
				styleOfCookingName : ''
			};
		},

		openEditModal : function(id) {
			var that = this;
			that.$http.get('/cooking/findStyleOfCookingById', {
				params : {
					id : id
				}
			}).then(function(res) {
				that.editObj = res.body.data;
				that.addOrUpdateFlag = true;
				that.actionTitle = '编辑菜系';
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
			if (editObj.styleOfCookingName == null || editObj.styleOfCookingName == '') {
				layer.alert('请输入菜系名称', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/cooking/addOrUpdateStyleOfCooking', editObj).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.addOrUpdateFlag = false;
				that.refreshTable();
			});
		},

		del : function(id) {
			var that = this;
			layer.confirm('确定要删除吗?', {
				icon : 7,
				title : '提示'
			}, function(index) {
				layer.close(index);
				that.$http.get('/cooking/delStyleOfCookingById', {
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