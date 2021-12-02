<template>
	<div class="login-wrap">
		<div class="ms-login">
			<div class="ms-title">后台管理系统</div>
			<el-form :model="param" :rules="rules" ref="login" label-width="0px" class="ms-content">
				<el-form-item prop="username">
					<el-input v-model="param.username" placeholder="用户名">
						<el-button slot="prepend" icon="el-icon-lx-people"></el-button>
					</el-input>
				</el-form-item>
				<el-form-item prop="telephone">
					<el-input v-model="param.telephone" placeholder="电话号码">
						<el-button slot="prepend" icon="el-icon-phone"></el-button>
					</el-input>
				</el-form-item>
				<el-form-item prop="email">
					<el-input v-model="param.email" placeholder="邮箱">
						<el-button slot="prepend" icon="el-icon-message"></el-button>
					</el-input>
				</el-form-item>
				<el-form-item prop="password">
					<el-input type="password" placeholder="密码" v-model="param.password"
						@keyup.enter.native="submitForm()">
						<el-button slot="prepend" icon="el-icon-lx-lock"></el-button>
					</el-input>
				</el-form-item>
				<el-form-item prop="captcha" class="captcha-block">
					<el-input v-model="param.captcha" placeholder="验证码" class="captcha-input">
						<el-button slot="prepend" icon="el-icon-key"></el-button>
					</el-input>
					<img :src="captchaImg" class="captcha-img" @click="changeCaptchaImg()" title="点击切换验证码">
				</el-form-item>
				<div class="login-btn">
					<el-button type="primary" @click="submitForm()">注册</el-button>
				</div>
				<div class="login-btn">
					<el-button type="info" @click="toLogin()">登录</el-button>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import {
		register,
		getCaptcha
	} from '../../api/index';
	import {
		sha256
	} from 'js-sha256';
	export default {
		data: function() {
			var checkPhone = (rule, value, callback) => {
				const phoneReg = /^1[0-9]{10}$/
				if (!value) {
					return callback(new Error('电话号码不能为空'))
				}
				setTimeout(() => {
					if (!Number.isInteger(+value)) {
						callback(new Error('请输入数字值'))
					} else {
						if (phoneReg.test(value)) {
							callback()
						} else {
							callback(new Error('电话号码格式不正确'))
						}
					}
				}, 100)
			}
			return {
				captchaImg: '',
				param: {
					ima_id: '',
					username: '',
					password: '',
					telephone: '',
					email: '',
					captcha: '',
				},
				rules: {
					username: [{
						required: true,
						message: '请输入用户名',
						trigger: 'blur'
					}],
					password: [{
						required: true,
						message: '请输入密码',
						trigger: 'blur'
					}],
					telephone: [{
						validator: checkPhone,
						trigger: 'blur'
					}],
					email: [{
							type: "string",
							required: true,
							message: '请输入邮箱地址',
							trigger: 'blur'
						},
						{
							type: 'email',
							message: '请输入正确的邮箱地址',
							trigger: ['blur', 'change']
						}
					],
					captcha: [{
						required: true,
						message: '请输入验证码',
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
						register(this.param).then((res) => {
							if (res.code === 200) {
								this.$message.success('注册成功');
								this.$router.push('/login');
								return true;
							} else {
								this.$message.error(res.msg);
								this.changeCaptchaImg()
								return false;
							}
						}).catch(err => {
							this.$message.error(err.msg);
							this.changeCaptchaImg()
							return false;
						})
					} else {
						this.$message.error('请正确填写完整信息');
						this.changeCaptchaImg()
						return false;
					}
				});
			},
			toLogin() {
				this.$router.push('/login');
			},
			changeCaptchaImg() {
				getCaptcha().then(res => {
					this.captchaImg = 'data:image/jpeg;base64,' + res.image_base,
						this.param.ima_id = res.id
				}).catch(err => {
					this.$message.error(err.msg);
					return false;
				})
			}
		},
		created() {
			getCaptcha().then(res => {
				this.captchaImg = 'data:image/jpeg;base64,' + res.image_base;
				this.param.ima_id = res.id;
			}).catch(err => {
				this.$message.error(err.msg);
				return false;
			})
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
		margin: -226.5px 0 0 -175px;
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

	.captcha-block {
		.captcha-input {
			width: 58%;
			justify-content: flex-start;
		}

		.captcha-img {
			height: 30px;
			width: 24%;
			vertical-align: middle;
			margin-left: 50px;
		}
	}
</style>
