<template>
	<div class="login-wrap">
		<div class="ms-login">
			<div class="ms-title">Library Management System</div>
			<el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
				<el-form-item prop="username">
					<el-input v-model="param.username" placeholder="username">
						<el-button slot="prepend" icon="el-icon-lx-people"></el-button>
					</el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input type="password" placeholder="password" v-model="param.password"
						@keyup.enter.native="submitForm()">
						<el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
					</el-input>
				</el-form-item>
				<div class="login-btn">
					<el-button type="primary" @click="submitForm()">Login</el-button>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import {
		login
	} from '../../api/index';
	import {
		sha256
	} from 'js-sha256';
	export default {
		data: function() {
			return {
				param: {
					username: '',
					password: '',
				},
				rules: {
					username: [{
						required: true,
						message: 'Please input username',
						trigger: 'blur'
					}],
					password: [{
						required: true,
						message: 'Please input password',
						trigger: 'blur'
					}],
				},
			};
		},
		methods: {
			submitForm() {
				this.$refs.login.validate(valid => {
					if (valid) {
						this.param.password = sha256('this.param.password');
						login(this.param).then((res) => {
							if (res.code === 200) {
								this.$message.success('success');
								localStorage.setItem('ms_username', this.param.username);
								localStorage.setItem("token", res.data.token);
								console.log(res.data.token)
								this.$router.push('/');
								return true;
							} else {
								this.$message.error(res.msg);
								return false;
							}
						}).catch(err => {
							this.$message.error(err.msg);
							return false;
						})
					} else {
						this.$message.error('Please input your username and password');
						return false;
					}
				});
			}
		}
	};
</script>

<style scoped lang="scss">
	.login-wrap {
		position: relative;
		width: 100%;
		height: 100%;
		background-image: url(../../assets/img/login-bg.jpg);
		background-size: 100%;
	}

	.ms-title {
		width: 100%;
		line-height: 50px;
		text-align: center;
		font-size: 20px;
		color: #fff;
		border-bottom: 1px solid #ddd;
	}

	.ms-login {
		position: absolute;
		left: 50%;
		top: 50%;
		width: 350px;
		margin: -190px 0 0 -175px;
		border-radius: 5px;
		background: rgba(255, 255, 255, 0.3);
		overflow: hidden;
	}

	.ms-content {
		padding: 30px 30px;
	}

	.login-btn {
		text-align: center;
	}

	.login-btn button {
		width: 100%;
		height: 36px;
		margin-bottom: 10px;
	}

	.login-tips {
		font-size: 12px;
		line-height: 30px;
		color: #fff;
	}
</style>
