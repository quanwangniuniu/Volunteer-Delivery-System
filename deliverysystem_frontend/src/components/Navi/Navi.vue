<template>
  <aside class="sidebar">
    <ul class="sidebar-menu">
      <li><p class="loginInfo">你好!{{username}} 用户id:{{userId}}</p></li>
      <li><router-link to="/personalInfo">个人信息修改</router-link></li>
      <li><router-link to="/release">订单发布</router-link></li>
      <li><router-link to="/grab">抢单管理</router-link></li>
      <li><router-link to="/myorders">我的订单</router-link></li>
      <li><router-link to="/books">众包周边</router-link></li>
      <br><br><br>
      <li><p class="points">积分:{{points}}&nbsp;&nbsp;&nbsp;&nbsp;信誉分:{{credits}}</p></li>
      <br>
      <li @click="logout"><button class="custom-btn">退出登录</button></li>
    </ul>
  </aside>
</template>

<script>
import axios from "axios";

export default {
  name: "Navi",
  data(){
    return{
      username:localStorage.getItem("username"),
      userId:localStorage.getItem("student_id"),
      points:'',
      credits:"",
    }
  },
  methods:{
    logout() {
      localStorage.removeItem('username')
      this.$router.push('/')
    }
  },
  mounted() {
    //从后端获取当前用户的积分信息
    const username = localStorage.getItem("username")
    axios.get(`http://localhost:8080/studentfindPoints/${username}`)
        .then(response => {
          this.points = response.data;
        })
        .catch(error => {
          console.error(error);
        });

    //从后端获取当前用户的信誉信息
    axios.get(`http://localhost:8080/studentfindCredits/${username}`)
        .then(response => {
          this.credits = response.data;
        })
        .catch(error => {
          console.error(error);
        });
  }
}
</script>

<style scoped>
aside{
  font-size: 20px;
}
.sidebar {
  background-color: #343a40;
  color: #fff;
  padding: 20px;
  width: 240px; /* increased sidebar width */
  height: 100%;
  position: fixed; /* keep the sidebar fixed on the left */
  top: 0;
  left: 0;
  z-index: 1; /* ensure the sidebar is on top of other elements */
}

.sidebar-menu {
  list-style: none;
  padding: 0;
  margin: 0;
}

.sidebar-menu li {
  margin-top: 30px;
}

.sidebar-menu a {
  color: #fff;
  text-decoration: none;
  transition: all 0.3s ease;
}

.sidebar-menu a:hover {
  color: #007bff;
}

.custom-btn {
  display: inline-block;
  background-color: #00bcd4;
  border-radius: 4px;
  border: none;
  color: #fff;
  font-size: 18px;
  padding: 10px 20px;
  transition: background-color 0.3s ease;
}

.custom-btn:hover {
  background-color: #019293;
  cursor: pointer;
}
.loginInfo {
  font-family: Arial, Helvetica, sans-serif;
  font-size: 10px;
  letter-spacing: 1px;
  background-color: rgba(35, 215, 44, 0.07);
  border: 2px solid #2D3540;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  color: #FFF;
  text-align: center;
  padding: 5px;
}
.points {
  font-family: Arial, sans-serif;
  font-size: 0.5em;
  font-weight: bold;
  color: #ffffff;
  background-color: rgba(0, 0, 0, 0.84);
  border: 2px solid #e75c5c;
  padding: 10px;
  border-radius: 10px;
}
</style>
