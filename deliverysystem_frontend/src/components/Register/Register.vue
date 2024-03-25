<template>
  <head>
    <meta charset="utf-8">
    <title>校园众包递送系统 - 注册</title>
  </head>
  <div class="container">
    <section class="register-box">
      <h2>注册</h2>
      <form @submit.prevent="register">
        <label for="username">用户名</label>
        <input type="text" id="username" v-model="username" maxlength="20" required>
        <span v-if="usernameError" class="error">{{ usernameError }}</span>

        <label for="email">邮箱</label>
        <input type="email" id="email" v-model="email" maxlength="50" required>
        <span v-if="emailError" class="error">{{ emailError }}</span>

        <label for="password">密码</label>
        <input type="password" id="password" v-model="password" minlength="6" maxlength="20" required>
        <span v-if="passwordError" class="error">{{ passwordError }}</span>

        <label for="confirm-password">确认密码</label>
        <input type="password" id="confirm-password" v-model="confirmPassword" minlength="6" maxlength="20" required>
        <span v-if="confirmPasswordError" class="error">{{ confirmPasswordError }}</span>

        <label for="phone-number">电话号码（选填）</label>
        <input type="tel" id="phone-number" v-model="phoneNumber" placeholder="如：18888888888" pattern="[0-9]{11}" maxlength="11">
        <span v-if="phoneNumberError" class="error">{{ phoneNumberError }}</span>

        <button type="submit" class="register-btn">注册</button>
      </form>
      <p class="to-login-text">已有账户？<router-link to="/">登录</router-link>></p>
    </section>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Register",
    data() {
  return {
    username: '',
    email: '',
    password: '',
    confirmPassword: '',
    phoneNumber: '',
    usernameError: '',
    emailError: '',
    passwordError: '',
    confirmPasswordError: '',
    phoneNumberError: ''
  }
    },
    methods: {
       register() {
          // 清空所有错误提示信息
          this.usernameError = ''
          this.emailError = ''
          this.passwordError = ''
          this.confirmPasswordError = ''
          this.phoneNumberError = ''

    // 判断密码是否一致
    if (this.password !== this.confirmPassword) {
      this.confirmPasswordError = '两次输入的密码不匹配'
      return
    }

    const userData = {
           username: this.username,
           email: this.email,
           password: this.password
    };
    axios.post('http://localhost:8080/userregister', userData)
             .then(response => {
               alert("注册成功!");
               this.$router.replace('/login');
               console.log('User registered:', response.data);
             })
             .catch(error => {
               console.log('Error registering user:', error.response.data);
             });
    // TODO: 发送注册请求
      }
   }
}
</script>

<style scoped>
/* 全局样式 */
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}

.error{
  color:red;
}
body {
  background-color: #f4f4f4;
  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

/* Registration box styles */
.register-box {
  background-color: #fff;
  padding: 40px;
  border-radius: 10px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  max-width: 450px;
  width: 100%;
}

@media(min-width: 992px) { /* to apply the following styles only on screens larger than 992px */
  .container {
    width: 200%;
    max-width: none;
  }

  .register-box {
    max-width: 900px;
    margin: 0 auto;
  }
}

h2 {
  margin-bottom: 30px;
  text-align: center;
  color: #1abc9c;
  font-size: 55px;
}

form {
  display: flex;
  flex-direction: column;
}

label {
  font-size: 14px;
  margin-bottom: 8px;
  color: #333;
  font-weight: bold;
}

input {
  padding: 12px;
  margin-bottom: 20px;
  border-radius: 5px;
  border: 1px solid #ccc;
  font-size: 16px;
  color: #555;
  background-color: #f0f0f0;
  transition: all 0.3s ease;
}

input:focus {
  outline: none;
  border-color: #1abc9c;
  box-shadow: 0 0 5px #1abc9c;
}

input:invalid {
  border-color: #f44336;
}

input[type="submit"],
button {
  background-color: #1abc9c;
  color: #fff;
  padding: 12px;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
}

input[type="submit"]:hover,
button:hover {
  background-color: #148f77;
}

.to-login-text {
  margin-top: 20px;
  text-align: center;
  font-size: 14px;
  color: #555;
}

.to-login-text a {
  color: #1abc9c;
  text-decoration: none;
  transition: all 0.3s ease;
}

.to-login-text a:hover {
  color: #148f77;
}
</style>