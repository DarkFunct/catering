var accountManageVM = new Vue({
	el : '#account-manage',
	data : {
		accountTypeDictItems : [ {
			dictItemCode : 'admin',
			dictItemName : '管理员'
		}, {
			dictItemCode : 'waiter',
			dictItemName : '服务员'
		} ],
		accountStateDictItems : [ {
			dictItemCode : '1',
			dictItemName : '启用'
		}, {
			dictItemCode : '0',
			dictItemName : '禁用'
		} ],
		userName : '',
		realName : '',

		addUserAccountFlag : false,
		selectedAccount : {},
		modifyLoginPwdFlag : false,
		newLoginPwd : '',
		accountEditFlag : false,
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
			$('.account-manage-table').bootstrapTable({
				classes : 'table table-hover',
				height : 490,
				url : '/userAccount/findUserAccountDetailsInfoByPage',
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
						userName : that.userName,
						realName : that.realName
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
					field : 'userName',
					title : '用户名'
				}, {
					field : 'realName',
					title : '真实姓名'
				}, {
					field : 'accountTypeName',
					title : '账号类型'
				}, {
					field : 'stateName',
					title : '账号状态'
				}, {
					field : 'registeredTime',
					title : '注册时间'
				}, {
					field : 'latelyLoginTime',
					title : '最近登录时间',
				}, {
					title : '操作',
					formatter : function(value, row, index) {
						return [ '<button type="button" class="account-edit-btn btn btn-outline-primary btn-sm" style="margin-right: 4px;">编辑</button>', '<button type="button" class="modify-login-pwd-btn btn btn-outline-secondary btn-sm" style="margin-right: 4px;">修改登录密码</button>', '<button type="button" class="del-account-btn btn btn-outline-danger btn-sm">删除</button>' ].join('');
					},
					events : {
						'click .account-edit-btn' : function(event, value, row, index) {
							that.showAccountEditModal(row);
						},
						'click .modify-login-pwd-btn' : function(event, value, row, index) {
							that.showModifyLoginPwdModal(row);
						},
						'click .del-account-btn' : function(event, value, row, index) {
							that.delAccount(row);
						}
					}
				} ]
			});
		},
		refreshTable : function() {
			$('.account-manage-table').bootstrapTable('refreshOptions', {
				pageNumber : 1
			});
		},

		openAddAccountModal : function() {
			this.addUserAccountFlag = true;
			this.selectedAccount = {
				userName : '',
				realName : '',
				loginPwd : '',
				accountType : '',
				state : ''
			}
		},

		addUserAccount : function() {
			var that = this;
			var selectedAccount = that.selectedAccount;
			if (selectedAccount.userName == null || selectedAccount.userName == '') {
				layer.alert('请输入用户名', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (selectedAccount.realName == null || selectedAccount.realName == '') {
				layer.alert('请输入真实姓名', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (selectedAccount.loginPwd == null || selectedAccount.loginPwd == '') {
				layer.alert('请输入登录密码', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (selectedAccount.accountType == null || selectedAccount.accountType == '') {
				layer.alert('请选择账号类型', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (selectedAccount.state == null || selectedAccount.state == '') {
				layer.alert('请选择状态', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/userAccount/addUserAccount', selectedAccount, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.addUserAccountFlag = false;
				that.refreshTable();
			});
		},

		delAccount : function(row) {
			var that = this;
			layer.confirm('确定要删除该账号吗?', {
				icon : 7,
				title : '提示'
			}, function(index) {
				layer.close(index);
				that.$http.get('/userAccount/delUserAccount', {
					params : {
						userAccountId : row.id
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

		showAccountEditModal : function(row) {
			var that = this;
			that.$http.get('/userAccount/findUserAccountDetailsInfoById', {
				params : {
					userAccountId : row.id
				}
			}).then(function(res) {
				that.selectedAccount = res.body.data;
				that.accountEditFlag = true;
			});
		},

		updateUserAccount : function() {
			var that = this;
			var selectedAccount = that.selectedAccount
			if (selectedAccount.userName == null || selectedAccount.userName == '') {
				layer.alert('请输入用户名', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (selectedAccount.realName == null || selectedAccount.realName == '') {
				layer.alert('请输入真实姓名', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (selectedAccount.accountType == null || selectedAccount.accountType == '') {
				layer.alert('请选择账号类型', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			if (selectedAccount.state == null || selectedAccount.state == '') {
				layer.alert('请选择状态', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/userAccount/updateUserAccount', {
				userAccountId : selectedAccount.id,
				userName : selectedAccount.userName,
				realName : selectedAccount.realName,
				accountType : selectedAccount.accountType,
				state : selectedAccount.state
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.accountEditFlag = false;
				that.refreshTable();
			});
		},

		showModifyLoginPwdModal : function(row) {
			this.selectedAccount = row;
			this.newLoginPwd = '';
			this.modifyLoginPwdFlag = true;
		},

		modifyLoginPwd : function() {
			var that = this;
			if (that.newLoginPwd == null || that.newLoginPwd == '') {
				layer.alert('请输入登录密码', {
					title : '提示',
					icon : 7,
					time : 3000
				});
				return;
			}
			that.$http.post('/userAccount/modifyLoginPwd', {
				userAccountId : that.selectedAccount.id,
				newLoginPwd : that.newLoginPwd
			}, {
				emulateJSON : true
			}).then(function(res) {
				layer.alert('操作成功!', {
					icon : 1,
					time : 3000,
					shade : false
				});
				that.modifyLoginPwdFlag = false;
				that.refreshTable();
			});
		}
	}
});