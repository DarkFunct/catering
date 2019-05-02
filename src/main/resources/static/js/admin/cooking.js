var cookingVM = new Vue({
	el : '#cooking',
	data : {
		styleOfCookings : [],
		styleOfCookingId : '',
		cookingName : '',

		addOrUpdateFlag : false,
		actionTitle : '',
		editObj : {},

	},
	computed : {},
	created : function() {
	},
	mounted : function() {
		var that = this;
		that.loadAllStyleOfCooking();
		that.initTable();

		$('.pic').on('fileuploaded', function(event, data, previewId, index) {
			that.editObj.storageId = data.response.data;
			that.addOrUpdateInner();
		});
	},
	methods : {
		loadAllStyleOfCooking : function() {
			var that = this;
			that.$http.get('/cooking/findAllStyleOfCooking').then(function(res) {
				that.styleOfCookings = res.body.data;
			});
		},

		initTable : function() {
			var that = this;
			$('.common-table').bootstrapTable({
				classes : 'table table-hover',
				height : 490,
				url : '/cooking/findCookingByPage',
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
						styleOfCookingId : that.styleOfCookingId,
						cookingName : that.cookingName
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
					field : 'styleOfCookingName',
					title : '所属菜系'
				}, {
					field : 'cookingName',
					title : '菜名'
				}, {
					field : 'price',
					title : '价格'
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
			this.actionTitle = '新增菜品';
			this.editObj = {
				styleOfCookingId : '',
				cookingName : '',
				price : '',
				introduction : ''
			};
			this.initFileUploadWidget();
		},

		openEditModal : function(id) {
			var that = this;
			that.$http.get('/cooking/findCookingById', {
				params : {
					id : id
				}
			}).then(function(res) {
				that.editObj = res.body.data;
				that.addOrUpdateFlag = true;
				that.actionTitle = '编辑菜品';
				that.initFileUploadWidget(that.editObj.storageId);
			});
		},

		initFileUploadWidget : function(storageId) {
			var initialPreview = [];
			var initialPreviewConfig = [];
			if (storageId != null) {
				initialPreview.push('/storage/fetch/' + storageId);
				initialPreviewConfig.push({
					downloadUrl : '/storage/fetch/' + storageId
				});
			}
			$('.pic').fileinput('destroy').fileinput({
				browseOnZoneClick : true,
				showBrowse : false,
				showCaption : false,
				showClose : true,
				showRemove : false,
				showUpload : false,
				dropZoneTitle : '点击选择图片',
				dropZoneClickTitle : '',
				layoutTemplates : {
					footer : ''
				},
				maxFileCount : 1,
				uploadUrl : '/storage/uploadPic',
				enctype : 'multipart/form-data',
				allowedFileExtensions : [ 'jpg', 'png', 'bmp', 'jpeg' ],
				initialPreview : initialPreview,
				initialPreviewAsData : true,
				initialPreviewConfig : initialPreviewConfig
			});
		},

		addOrUpdate : function() {
			var that = this;
			var editObj = that.editObj;
			if (editObj.styleOfCookingId == null || editObj.styleOfCookingId == '') {
				layer.alert('请选择所属菜系', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (editObj.cookingName == null || editObj.cookingName == '') {
				layer.alert('请输入菜名', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (editObj.price == null || editObj.price == '') {
				layer.alert('请输入价格', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (editObj.introduction == null || editObj.introduction == '') {
				layer.alert('请输入简介', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if ($('.pic').fileinput('getPreview').content.length != 0) {
				that.addOrUpdateInner();
			} else {
				var filesCount = $('.pic').fileinput('getFilesCount');
				if (filesCount == 0) {
					layer.alert('请选择要上传的图片', {
						title : '提示',
						icon : 7,
						time : 3000
					});
					return;
				}
				$('.pic').fileinput('upload');
			}
		},

		addOrUpdateInner : function() {
			var that = this;
			that.$http.post('/cooking/addOrUpdateCooking', that.editObj).then(function(res) {
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
				that.$http.get('/cooking/delCookingById', {
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