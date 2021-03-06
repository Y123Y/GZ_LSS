var datas = {
    left_lists: ['管理工作人员', '处理审核'],  // 左边功能目录
    worker_cols: ['id', '姓名', '身份', '电话', '登录名', '编辑'], //工作人员列表列名
    workers: [], //工作人员信息
    examine_cols: ['id', '工作人员', '当前身份', '申请内容', '描述', '审核状态', '修改'],
    examines: [],
    states: {},
    identities: {},
    show_workers: true,
    show_workers_tip: true, // 标识工作人员列表是否为空
    show_examine: false, //标识审核信息是否为空
    show_examines_tip: true,
    url_pre: '../',
    admin_info: {name: '', oldPassword: '', newPassword: '', sure_password: ''}
};

function error_tip() {
    alert("网络出现问题，请检查网络连接！")
}

$(document).ready(function () {

    var vm = new Vue({
        el: '#app',
        data: window.datas,
        created: function () {
            if(window.document.location.pathname === '/admin/login'){
                window.location.href = '/admin/main'
            }
            this.url_pre = '../';
            /* 初始化登录名*/
            this.admin_info.name = $('#info_name').text();
            /* 初始化标识符 */
            this.inti_identities_states(false);
            /* 初始化工作人员管理信息 */
            this.workers = this.get_all_workers(true);
            /* 初始化工作人员审核信息 */
            this.examines = this.get_all_examines(true);
        },
        methods: {
            get_preurl: function () {
                var curWwwPath = window.document.location.href;
                var pathName = window.document.location.pathname;
                var pos = curWwwPath.indexOf(pathName);
                var localhostPaht = curWwwPath.substring(0, pos);
                var realPath = localhostPaht;//+projectName;
                return realPath;
            },


            /* 获取身份标识和名称的键值对*/
            inti_identities_states: function (style) {
                $.ajax({
                    type: "post",
                    url: window.datas.url_pre + "adminOperation/getStringAndCode",
                    async: style,
                    dataType: "json",
                    success: function (res) {
                        if (res.code === 200) {
                            res.data['states'].map(function (item, index) {
                                window.datas.states[item.state_id] = item.name
                            });
                            res.data['identities'].map(function (item, index) {
                                window.datas.identities[item.identity] = item.name
                            })
                        } else {
                            alert(res.message)
                        }
                    },
                    error: window.error_tip
                });
            },

            /**
             * 左侧功能按钮点击
             * @param index
             */
            left_click: function (index) {
                if (index === 0) {
                    this.show_examine = false;
                    this.show_workers = true;
                    this.workers = this.get_all_workers(true);
                } else if (index === 1) {
                    this.show_workers = false;
                    this.show_examine = true;
                    this.examines = this.get_all_examines(true);
                }
            },

            /**
             * 更新管理员信息
             * @param style
             * @returns {boolean}
             */
            update_admin_info: function (style) {
                if (datas.admin_info.newPassword !== datas.admin_info.sure_password) {
                    alert("两次输入的密码不一致！");
                    return false
                }
                var tmp_data = {
                    name: datas.admin_info.name,
                    oldPassword: datas.admin_info.oldPassword,
                    newPassword: datas.admin_info.newPassword
                };
                $.ajax({
                    type: "post",
                    url: window.datas.url_pre + "admin/updateAdminInfo",
                    async: style,
                    data: tmp_data,
                    dataType: "json",
                    success: function (res) {
                        m_tip = res.data;
                        if (m_tip.update_name === "true") {
                            $('#info_name').text(tmp_data.name);
                        }
                        alert(m_tip.update_name_msg + " " + m_tip.update_pwd_msg)
                    },
                    error: window.error_tip
                });
                this.admin_info.newPassword = '';
                this.admin_info.oldPassword = '';
                this.admin_info.sure_password = '';
                return true
            },

            /* 退出登录 */
            logout_click: function () {
                location.href = "logout"
            },


            /*                      管理工作人员                            */

            /* 更新工作人员信息 */
            get_all_workers: function (style) {
                var tmp = [];
                $.ajax({
                    type: "post",
                    url: window.datas.url_pre + "adminOperation/getWorkers",
                    async: style,
                    dataType: "json",
                    success: function (res) {
                        if (res.code === 200) {
                            res.data.map(function (item, index) {
                                item.identity = window.datas.identities[item.identity]
                                tmp.push(item)
                            });
                        }
                        window.datas.show_workers_tip = (tmp.length === 0);
                    },
                    error: window.error_tip
                });
                return tmp
            },

            /* 删除工作人员信息 */
            update_worker_btn: function (id) {
                $.ajax({
                    type: "post",
                    url: window.datas.url_pre + "adminOperation/deleteWorker",
                    data: {'worker_id': id},
                    async: true,
                    dataType: "json",
                    success: function (res) {
                        if (res.code === 200) {
                            window.datas.workers.map(function (value, index) {
                                if (value.worker_id === id) {
                                    window.datas.workers.splice(index, 1)
                                }
                            })
                            alert(res.data)
                        } else {
                            alert(res.message)
                        }
                        window.datas.show_workers_tip = (window.datas.workers.length === 0);
                    },
                    error: window.error_tip
                });
            },

            /* 重置工作人员密码*/
            update2_worker_btn: function (id) {
                $.ajax({
                    type: "post",
                    url: window.datas.url_pre + "adminOperation/resetWorker",
                    data: {'worker_id': id},
                    async: true,
                    dataType: "json",
                    success: function (res) {
                        if (res.code === 200) {
                            alert(res.data)
                        } else {
                            alert(res.message)
                        }
                    },
                    error: window.error_tip
                });
            },


            /* 管理工作人员审核 */

            /* 更新管理人员审核信息 */
            get_all_examines: function (style) {
                var tmp = [];
                $.ajax({
                    type: "post",
                    url: window.datas.url_pre + "adminOperation/getExamieOfNeed",
                    async: style,
                    dataType: "json",
                    success: function (res) {
                        if (res.code === 200) {
                            res.data.map(function (item, index) {
                                tmp.push(item)
                            });
                        }
                        window.datas.show_examines_tip = (tmp.length === 0);
                    },
                    error: window.error_tip
                });
                return tmp
            },

            /* 修改审核信息 */
            update_examine_btn: function (id, sure, style) {
                $.ajax({
                    type: "post",
                    url: window.datas.url_pre + "adminOperation/handleExamine",
                    async: style,
                    data: {review_id: id, suggestion: sure},
                    dataType: "json",
                    success: function (res) {
                        if (res.code === 200) {
                            window.datas.examines.map(function (value, index) {
                                if (value.review_id === id) {
                                    if (sure) {
                                        value.state = '已通过'
                                    } else {
                                        value.state = '已驳回'
                                    }
                                }
                            })
                        }
                        alert(res.message)
                    },
                    error: window.error_tip
                });
            }
        }

    })


});
