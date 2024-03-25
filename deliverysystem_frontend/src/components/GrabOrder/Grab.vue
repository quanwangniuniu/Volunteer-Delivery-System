<template>
  <div class="container">
  <div class="main-content">
    <Navi></Navi>
    <h1>抢单管理</h1>
    <table>
      <thead>
      <tr>
        <th>订单编号</th>
        <th>商品名称</th>
        <th>购买地点</th>
        <th>收货地点</th>
        <th>截止日期</th>
        <th>报酬</th>
        <th>操作</th>
        <th>抢单</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(order, index) in orders" :key="index">
        <td>{{ order.orderNumber }}</td>
        <td>{{ order.productName }}</td>
        <td>{{ order.purchaseLocation }}</td>
        <td>{{ order.deliveryLocation }}</td>
        <td>{{ order.deadline }}</td>
        <td>{{order.payment}}</td>
        <td>
          <button v-if="order.orderStatus === 0" class="btn" @click="grabOrder(order)">立即接单</button>
          <button v-else class="btn disabled">此单被接</button>
        </td>
        <td>
          <button v-if="order.orderStatus === 1" style="color:orange" @click="steal(order)">试试抢单</button>
          <button v-else class="btn disabled">此单完成</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
  </div>
</template>

<script>
import Navi from "@/components/Navi/Navi.vue";
import axios from "axios";

export default {
  components: {Navi},
  data() {
    return {
      orders: [
        {
          orderNumber: '001',
          productName: 'iPhone 12',
          purchaseLocation: '北京',
          deliveryLocation: '上海',
          orderStatus: 0,
          payment:'',
          deadline:'',
          studentId:'',
          deliverId:'',
        },
      ]
    };
  },
  methods: {
    async grabOrder(order) {
      try {
        order.studentId=localStorage.getItem("student_id")
        order.orderStatus=1;
        const response = await axios.post('http://localhost:8080/graborders', order);
        if (response.status === 200) {
           alert("接单成功");
           order.orderStatus=1;
        } else {
          // 接单失败
          this.error = response.data.message;
        }
      } catch (e) {
        this.error = '接单失败';
      }
    },
     async steal(order) {
      const nowUserId = localStorage.getItem("student_id")
       const existedUserId = order.studentId;
       axios.get(`http://localhost:8080/findStudent/${nowUserId}`)
           .then(response => {
             // 从后端获取当前用户的信誉分
             const nowCredit = response.data.credit;
             axios.get(`http://localhost:8080/findStudent/${existedUserId}`)
                 .then(response=>{
                   {
                     // 从后端获取已接单用户的信誉分
                     const existedCredit = response.data.credit;
                     console.log(nowCredit)
                     console.log(existedCredit)
                     if(nowCredit>existedCredit)
                     {
                       order.studentId=localStorage.getItem("student_id")
                       order.orderStatus=1;
                       axios.post('http://localhost:8080/graborders', order);
                       alert("抢单成功!")
                     }
                     else{
                       alert("抢单失败!您的信誉分不够！")
                     }
                   }
                 })

           })
           .catch(error => {
             console.error(error);
           });

     }
  },
  mounted() {
    const API_URL='http://localhost:8080/getallorders'
    axios.get(API_URL, {withCredentials: true})
        .then(response=>{
          this.orders = response.data
          console.log(this.orders)
        }).catch(error=>{
      console.log(error)
    })
  }
};
</script>

<style scoped>

.main-content {
  margin-left: 15rem;
  flex: 1;
  padding: 16px;
  margin-top: 1rem;
  width: 1000px;
}

h1 {
  font-size: 28px;
  text-align: center;
  margin-bottom: 24px;
}

table {
  border-collapse: collapse;
  width: 100%;
  font-size: 16px;
  border: 2px solid #ccc;
  margin-top: 16px;
}

th,
td {
  text-align: left;
  padding: 8px;
  border: 1px solid #ccc;
  width: 40px;
}

tr:nth-child(even) {
  background-color: #f2f2f2
}

th {
  background-color: #4CAF50;
  color: white;
}

.btn {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 8px 16px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
  border-radius: 4px;
  transition: background-color 0.3s ease;
}

.btn:hover {
  background-color: #3e8e41;
}

.disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

</style>
