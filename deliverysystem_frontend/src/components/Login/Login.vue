<template>
  <div class="container">
  <div class="login-form-container">
    <div class="login-form">
      <h2>校园众包递送系统登录</h2>
      <form @submit.prevent="login">
        <div class="form-group">
          <label for="username">用户名:</label>
          <input type="text" class="form-control" id="username" placeholder="请输入用户名" v-model="formData.username">
          <p v-if="errors.username" class="error">{{ errors.username }}</p>
        </div>
        <div class="form-group">
          <label for="password">密码:</label>
          <input type="password" class="form-control" id="password" placeholder="请输入密码" v-model="formData.password">
          <p v-if="errors.password" class="error">{{ errors.password }}</p>
        </div>
        <div class="form-group form-check">
          <label class="form-check-label">
            <input class="form-check-input" type="checkbox" v-model="remember"> 记住我
          </label>
        </div>
        <button type="submit" class="btn btn-primary">登录</button>
      </form>
      <p v-if="error" class="error">{{ error }}</p>
      <div class="signup-link">
        没有账户？<router-link to="/register">点击这里注册</router-link>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      remember: false,
      formData:{
        username: '',
        password: '',
      },
      errors: {},
      error: '',
    }

  },
  methods: {
    async login() {
      this.errors = {};
      if(!this.formData.username) {
        this.errors.username = '请输入用户名';
      }
      if(this.formData.password.length < 6) {
        this.errors.password = '密码长度应该不少于6位';
      }
      if(Object.keys(this.errors).length > 0) {
        return;
      }
      // 在这里实现登录逻辑，可以使用axios发送POST请求到后端
      // 可以使用this.username和this.password获取输入的用户名和密码
      // 如果勾选了“记住我”可以将状态保存到localStorage或cookie中
      // 登录成功可以跳转到另一个页面，例如：this.$router.push('/home')
      try {
        const response = await axios.post("http://localhost:8080/studentlogin",this.formData);
        console.log(this.formData)
        if (response.status === 200) {
          // 登录成功
          this.error = '';
          localStorage.setItem('username',this.formData.username);
          const response_id = await axios.post("http://localhost:8080/studentfindId",this.formData);
          localStorage.setItem('student_id',response_id.data);
          console.log(response.data);
          console.log(localStorage.getItem('student_id'));
          this.$router.replace("/release");
        } else {
          // 登录失败
          this.error = response.data.message;
        }
      } catch (e) {
        this.error = '登录失败';
      }
    }
  }
};

</script>

<style scoped>
body {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
  height: 100vh;
  display: flex;  /* 新增代码 */
  justify-content: center;  /* 新增代码 */
  align-items: center;  /* 新增代码 */
}

/* Set background color */
body {
  background-color: #eee;
}


.container{
  background-image: url("Images/login_back.jpg");
  background-size:1500px,800px,cover;
  width: 1500px;
  height:800px;
  margin-left:-10rem;
  margin-top: -20rem;
}
/* Style login form */
.login-form-container {  /* 新增代码 */
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  margin-left: 50rem;
  margin-top: 15rem;
}

.login-form {
  background-color: #fff;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  padding: 30px;
  width: 500px; /* 修改代码 */
}

/* Style header */
.login-form h2 {
  margin-top: 0;
  font-size: 1.5em;
  text-align: center;
  color: #555;
}

/* Style form inputs */
.login-form input[type="text"],
.login-form input[type="password"] {
  width: 100%;
  padding: 10px;
  margin: 8px 0;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  outline: none;
}

.login-form input[type="text"]:hover,
.login-form input[type="password"]:hover {
  border: 1px solid #333;
}

.login-form input[type="text"]:focus,
.login-form input[type="password"]:focus {
  border: 1px solid #1b6dc1;
  box-shadow: 0 0 3px #1b6dc1;
}

/* Style form submit button */
.login-form button[type="submit"] {
  background-color: #1b6dc1;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 1.2em;
  padding: 10px 20px;
  margin-top: 10px;
  width: 100%;
}

.login-form button[type="submit"]:hover {
  background-color: #0e52a1;
}

/* Style form checkbox */
.login-form input[type="checkbox"] {
  margin-top: 10px;
}

/* Style error message */
p.error {
  margin-top: 10px;
  color: #f00;
  font-size: 16px;
}

.signup-link {
  margin-top: 20px;
  text-align: center;
}

.signup-link a {
  color: #1b6dc1;
  text-decoration: none;
}

.signup-link a:hover {
  text-decoration: underline;
}
</style>
