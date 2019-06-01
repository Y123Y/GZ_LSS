window.onload = function() {
	var datas = {
		loginBtnName: '登录',
		test: 'test2',
		account:'',
		password:''
	}

	var vm = new Vue({
		el: '#app',
		data: datas,
		
		created:{

		},
		methods:{
			login:function(){
				if(this.account == '' || this.password == ''){
					alert('账号或密码不能为空！')
				}
				return true
			}
		}
	})
}
