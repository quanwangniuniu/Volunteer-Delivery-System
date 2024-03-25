<template>
  <div class="container">
    <Navi></Navi>
    <form class="personal-info-form">
      <h1>个人信息管理</h1>
      <label for="name">姓名：</label>
      <input type="text" name="name" id="name" v-model="formData.username" required @blur="validateName">
      <div class="error-message" v-if="nameError">{{ nameError }}</div>
      <label for="gender">性别：</label>
      <input type="radio" name="gender" id="male" value="male" v-model="formData.gender" required>男&nbsp;&nbsp;&nbsp;
      <input type="radio" name="gender" id="female" value="female" v-model="formData.gender">女
      <label for="birthday">生日：</label>
      <input type="date" name="birthday" id="birthday" v-model="formData.birthday" required @blur="validateBirthday">
      <div class="error-message" v-if="birthdayError">{{ birthdayError }}</div>
      <label for="email">邮箱：</label>
      <input type="email" name="email" id="email" v-model="formData.email" required @blur="validateEmail">
      <div class="error-message" v-if="emailError">{{ emailError }}</div>
      <label for="password">密码：</label>
      <input type="password" name="password" id="password" minlength="6" v-model="formData.password" required @blur="validatePassword">
      <div class="error-message" v-if="passwordError">{{ passwordError }}</div>
      <label for="confirm-password">确认密码：</label>
      <input type="password" name="confirm-password" id="confirm-password" minlength="6" v-model="confirmPassword" required @blur="validateConfirmPassword">
      <div class="error-message" v-if="confirmPasswordError">{{ confirmPasswordError }}</div>
      <div class="error-message" v-if="passwordMatchError">两次输入的密码不匹配</div>
      <label for="phone-number">手机号：</label>
      <input type="tel" name="phone-number" id="phone-number" v-model="phoneNumber" required @blur="validatePhoneNumber">
      <div class="error-message" v-if="phoneNumberError">{{ phoneNumberError }}</div>
      <button type="submit" @click.prevent="submitForm()" :disabled="passwordMatchError">保存</button>
      <p class="back"><router-link to="/release">放弃修改，返回主界面</router-link></p>
    </form>
  </div>
</template>

<script>
import axios from "axios";
import Navi from "@/components/Navi/Navi.vue";
export default {
  components: {Navi},
  data() {
    return {
      confirmPassword: '',
      nameError: '',
      birthdayError: '',
      emailError: '',
      passwordError: '',
      confirmPasswordError: '',
      phoneNumberError: '',
      passwordMatchError: false,
      formData:{
        userId:localStorage.getItem("student_id"),
        username: localStorage.getItem("username"),
        gender: '',
        birthday: '',
        email: '',
        password: '',
        phoneNumber: '',
        credit:'',
        points:'',
      },
    };
  },
  methods: {
    validateName()
    {
      if (!this.formData.username) {
        this.nameError = '请输入姓名';
      } else {
        this.nameError = '';
      }
    },
    validateBirthday() {
      if (!this.formData.birthday) {
        this.birthdayError = '请选择生日';
      } else {
        this.birthdayError = '';
      }
    },
    validateEmail() {
      if (!this.formData.email) {
        this.emailError = '请输入邮箱';
      } else if (!/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/.test(this.email)) {
        this.emailError = '请输入正确的邮箱格式';
      } else {
        this.emailError = '';
      }
    },
    validatePassword() {
      if (!this.formData.password) {
        this.passwordError = '请输入密码';
      } else if (this.password.length < 6) {
        this.passwordError = '密码长度至少为6位';
      } else {
        this.passwordError = '';
      }
    },
    validateConfirmPassword() {
      if (!this.confirmPassword) {
        this.confirmPasswordError = '请再次输入密码';
      } else if (this.confirmPassword.length < 6) {
        this.confirmPasswordError = '密码长度至少为6位';
      } else if (this.confirmPassword !== this.formData.password) {
        this.confirmPasswordError = '两次输入的密码不匹配';
      } else {
        this.confirmPasswordError = '';
      }
    },
    validatePhoneNumber() {
      if (!this.formData.phoneNumber) {
        this.phoneNumberError = '请输入手机号';
      } else if (!/^1[0-9]{10}$/.test(this.formData.phoneNumber)) {
        this.phoneNumberError = '请输入正确的手机号格式';
      } else {
        this.phoneNumberError = '';
      }
    },
    checkPasswords() {
      this.passwordMatchError = this.password !== this.confirmPassword;
      return !this.passwordMatchError;
    },
    async submitForm() {
      if(this.formData.password===this.confirmPassword)
      // 在这里提交表单
      await axios.post("http://localhost:8080/updateInfo", this.formData)
          .then(response => {
        console.log(response);
        alert("修改成功!");
        //处理返回的数据
      }).catch(error => {
        console.log(error);
        alert("修改失败");
      });
    }
  },
  hideErrors() {
    this.nameError = '';
    this.birthdayError = '';
    this.emailError = '';
    this.passwordError = '';
    this.confirmPasswordError = '';
    this.phoneNumberError = '';
    this.passwordMatchError = false;
  },
  mounted() {
    window.addEventListener('click', this.hideErrors);
    //从后端获取当前用户的所有信息
    axios.get(`http://localhost:8080/findStudent/${this.userId}`)
        .then(response => {
          this.formData = response.data;
          console.log(this.formData)
        })
        .catch(error => {
          console.error(error);
        });
  },
  beforeDestroy() {
    window.removeEventListener('click', this.hideErrors);
  },
};
</script>

<style scoped>
.container {
  margin-top: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  width: 1200px;
}
.back{
  float: right;
  padding-top: 1rem;
}
.personal-info-form {
  max-width: 300%;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ddd;
  background-color: #fff;
  border-radius: 5px;
  width: 30rem;
}

h1 {
  text-align: center;
  margin: 0 0 30px;
  color: #5c63e7;
}

label {
  display: block;
  margin-bottom: 4px;
  margin-top: 18px;
  color: #222;
  font-size: 16px;
  font-weight: 600;
}

input[type="text"],
input[type="email"],
input[type="tel"],
input[type="password"],
input[type="date"],
input[type="radio"] {
  display: block;
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 5px;
  margin-bottom: 20px;
  box-sizing: border-box;
  font-size: 15px;
  color: #222;
}

input[type="radio"] {
  display: inline-block;
  width: auto;
  margin-right: 20px;
}

button[type="submit"] {
  display: block;
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  border: none;
  color: #fff;
  font-size: 16px;
  font-weight: 600;
  border-radius: 5px;
  cursor: pointer;
}

button[type="submit"]:hover {
  background-color: #0069d9;
}

.error-message {
  color: red;
  font-size: 12px;
  margin-top: 4px;
}
</style>