$(document).ready(function() {

        	var datas = {
        		left_lists: [],  // 左边功能目录
        		right_lists: [],  // 暂未使用
        		admin_info: {}, // 不存放密码
        		worker_cols: [], //工作人员列表列名
        		workers: [], //工作人员信息
        		admin_cols: [],
        		admins: [],
        		examine_cols: [],
        		examines: [],
        		test: 'make a test',
        		show_workers: false,
        		show_admins: true,
        		show_examine: false
        	}

        	var vm = new Vue({
        		el: '#app',
        		data: datas,
        		methods: {

        			//设置cookie
        			setCookie: function(key, value, exdays) {
        				var d = new Date();
        				d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        				var expires = "expires=" + d.toUTCString();
        				document.cookie = key + "=" + value + "; " + expires + "; path=/";
        			},
        			//获取cookie
        			getCookie: function(key) {
        				var name = key + "=";
        				var ca = document.cookie.split(';');
        				console.log("获取cookie,现在循环")
        				for (var i = 0; i < ca.length; i++) {
        					var c = ca[i];
        					console.log(c)
        					while (c.charAt(0) == ' ') c = c.substring(1);
        					if (c.indexOf(name) != -1) {
        						return c.substring(name.length, c.length);
        					}
        				}
        				return "";
        			},
        			//清除cookie
        			clearCookie: function(key) {
        				this.setCookie(key, "", -1);
        			},
        			/* 检查cookie */
        			checkCookie: function(key) {
        				var user = this.getCookie(key);
        				if (user != "") {
        					alert("Welcome again " + user);
        				} else {
        					user = prompt("Please enter your name:", "");
        					if (user != "" && user != null) {
        						this.setCookie("username", user, 365);
        					}
        				}
        			},

        			/* 将登录信息保存 */
        			get_admin_info: function() {

        				return {
        					name: '测试名',
        					account: 'admin1',
        					passwd: '123',
        					email: '123312@qq.com'
        				}
        			},

        			/* 获取左侧的功能列表信息 */
        			get_left_lists: function() {
        				var alist = []
        				alist.push({
        					fun_name: '管理工作人员',
        					request: 'manage_workers',
        					verification: 'true'
        				})
        				alist.push({
        					fun_name: '管理员变更',
        					request: 'manage_admins',
        					verification: 'false'
        				})
        				alist.push({
        					fun_name: '工作人员审核',
        					request: 'manage_examine',
        					verification: 'false'
        				})
        				return alist
        			},

        			/* 切换显示右侧内容 */
        			show_workers_btn: function() {
        				this.show_examine = false
        				this.show_admins = false
        				if (this.worker_cols == []) {
        					/* 执行工作人员信息获取 同步获取，有过渡动画*/
        					this.worker_cols = this.get_worker_cols()
        					this.workers = this.get_all_workers()
        					this.show_workers = true
        				} else {
        					/* 异步方式获取信息，无过渡动画 */
        					this.show_workers = true
        					this.worker_cols = this.get_worker_cols()
        					this.workers = this.get_all_workers()
        				}
        			},

        			/* 切换显示右侧内容 */
        			show_admins_btn: function() {
        				this.show_examine = false
        				this.show_workers = false
        				if (this.admin_cols == []) {
        					/* 执行工作人员信息获取 同步获取，有过渡动画*/
        					this.admin_cols = this.get_admin_cols()()
        					this.admins = this.get_all_admins()()
        					this.show_admins = true
        				} else {
        					/* 异步方式获取信息，无过渡动画 */
        					this.show_admins = true
        					this.admin_cols = this.get_admin_cols()()
        					this.admins = this.get_all_admins()()
        				}
        			},

        			/* 切换显示右侧内容 */
        			show_examine_btn: function() {
        				this.show_admins = false
        				this.show_workers = false
        				if (this.examine_cols == []) {
        					/* 执行信息获取 同步获取，有过渡动画*/
        					this.examine_cols = this.get_examine_cols()
        					this.workers = this.get_all_examines()
        					this.show_examine = true
        				} else {
        					/* 异步方式获取信息，无过渡动画 */
        					this.show_examine = true
        					this.examine_cols = this.get_examine_cols()
        					this.workers = this.get_all_examines()
        				}
        			},

        			/* 通过指定列表功能的标识符和本次登录的管理员信息来获取相关页面数据 */
        			get_right_lists: function(request, admin_info) {
        				alert(request.toString())
        				return []
        			},

        			left_click: function(index) {
        				var req = this.left_lists[index].request
        				// var info = this.deepClone(this.admin_info)
        				// this.right_lists = this.get_right_lists(req, info)
        				if (req == 'manage_workers') {
        					this.show_workers_btn()
        				} else if (req == 'manage_admins') {
        					this.show_admins_btn()
        				} else if (req == 'manage_examine') {
        					this.show_examine_btn()
        				}
        			},
        			/* 深拷贝-工具 */
        			deepClone: function(item) {
        				 target = item.constructor === Array ? [] : {}; // 判断复制的目标是数组还是对象
        				for ( keys in item) { // 遍历目标
        					if (item.hasOwnProperty(keys)) {
        						if (item[keys] && typeof item[keys] === 'object') { // 如果值是对象，就递归一下
        							target[keys] = item[keys].constructor === Array ? [] : {};
        							target[keys] = deepClone(item[keys]);
        						} else { // 如果不是，就直接赋值
        							target[keys] = item[keys];
        						}
        					}
        				}
        				return target;
        			},
        			/* 退出登录 */
        			logout_click: function() {
        				alert('logout')

        			},

        			/*                      管理工作人员                            */

        			/* 更新工作人员信息名称 */
        			get_worker_cols: function() {
        				var tmp = ['id', '姓名', '身份', '电话', '登录名', '编辑']
        				return tmp
        			},

        			/* 更新工作人员信息 */
        			get_all_workers: function() {
        				var tmp = []
						$.ajax({
							type:"post",
							url:"",
							dataType:"json",
							success:function(data){
								if(data.errorCode==0){
									$("#nickname").val(mylogin.nickname);
								}else{
									$("#nickname").val("用户");
								}
							},
							error:function(jqXHR){
								console.log("Error: "+jqXHR.status);
							}
						});
        				tmp.push({
        					'worker_id': 3,
        					'name': '测试员',
        					'identity': '借书员',
        					'tel': '15871577000',
        					'login_name': 'yy'
        				})
        				return tmp
        			},

        			/* 修改工作人员信息 */
        			update_worker_btn: function(id) {
        				alert("worker id :" + id)
        			},

        			/*                    管理人员变更                             */

        			/* 更新管理人员信息名称 */
        			get_admin_cols: function() {
        				var tmp = ['id', '账号', '姓名', '修改']
        				return tmp
        			},

        			/* 更新管理人员信息 */
        			get_all_admins: function() {
        				var tmp = []
        				tmp.push({
        					'admin_id': 0,
        					'account': '321123',
        					'name': '测试1'
        				})
        				tmp.push({
        					'admin_id': 2,
        					'account': '321121',
        					'name': '测试2'
        				})
        				tmp.push({
        					'admin_id': 3,
        					'account': '32112311',
        					'name': '测试3'
        				})
        				return tmp
        			},

        			/* 修改管理人员信息 */
        			update_admin_btn: function(id) {
        				alert("admin_id: " + id)
        			},

        			/* 管理工作人员审核 */

        			/* 更新管理人员信息名称 */
        			get_examine_cols: function() {
        				var tmp = ['id', '工作人员', '当前身份', '申请内容', '描述', '审核状态', '修改']
        				return tmp
        			},

        			/* 更新管理人员信息 */
        			get_all_examines: function() {
        				var tmp = []
        				tmp.push({
        					'review_id': 0,
        					'worker_id': '测试1',
        					'current': '找书员',
        					'want': '借书员',
        					'descript': '工作调整',
        					'state': '待处理'
        				})
        				tmp.push({
        					'review_id': 2,
        					'worker_id': '测试2',
        					'current': '找书员',
        					'want': '借书员',
        					'descript': '工作调整',
        					'state': '待处理'
        				})
        				tmp.push({
        					'review_id': 3,
        					'worker_id': '测试3',
        					'current': '找书员',
        					'want': '借书员',
        					'descript': '工作调整',
        					'state': '待处理'
        				})
        				return tmp
        			},

        			/* 修改管理人员信息 */
        			update_examine_btn: function(id, sure) {
        				alert("admin_id: " + id + sure)
        			}


        		},
        		created:function(){
        			/* cookie */
        			var tmp = this.setCookie('admin_id', '10010', 1)
        			// alert(this.getCookie('admin_id'))

        			/* 初始化左侧的功能列表 */
        			this.left_lists = this.get_left_lists()
        			/* 初始化登录信息 */
        			this.admin_info = this.get_admin_info()
        			/* 初始化工作人员管理信息 */
        			this.worker_cols = this.get_worker_cols()
        			this.workers = this.get_all_workers()
        			/* 初始化管理人员信息 */
        			this.admin_cols = this.get_admin_cols()
        			this.admins = this.get_all_admins()
        			/* 初始化工作人员审核信息 */
        			this.examine_cols = this.get_examine_cols()
        			this.examines = this.get_all_examines()
        		}
        	})


        });
