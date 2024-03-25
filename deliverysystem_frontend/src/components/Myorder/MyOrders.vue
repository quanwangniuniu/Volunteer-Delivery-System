<template>
  <div class="page-container">
    <Navi></Navi>
    <main class="main-content">
      <button class="lbutton" @click="toggleSentOrders">我发出的订单</button><br>
      <div class="orders" v-show="sentOrdersShow">
        <h2></h2>
        <ul>
          <li v-for="order in sentOrders" :key="order.orderNumber">
            <h2 style="margin-left: 20rem;">订单编号：{{ order.orderNumber}}</h2>
            <p>名称：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ order.productName }}</p>
            <p>截止日期：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ order.deadline }}</p>
            <p>来源地:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{order.purchaseLocation}}</p>
            <p>目的地:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{order.deliveryLocation}}</p>
            <p>备注:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{order.note}}</p>
            <p style="font-family: Algerian,serif">确认码:&nbsp;&nbsp;&nbsp;&nbsp;{{order.pickupCode}}</p>
            <button class="btn" v-if="order.orderStatus === 0">订单未处理</button>
            <button class="btn" v-if="order.orderStatus === 1">订单进行中</button>
            <button class="btn" v-if="order.orderStatus === 3 && order.isPaid === 1">订单已完成</button>&nbsp;&nbsp;
            <button class="btn" v-if="order.isPaid === 0 && order.orderStatus !==0" @click="payOrder(order)">付款</button>&nbsp;&nbsp;
            <button class="btn" v-if="order.orderStatus ===2 " @click="confirmFinish(order)">确认订单完成</button>
            <button class="btn" v-if="order.orderStatus === 3"  @click="showDialogII">订单评价</button>
            <el-dialog :title="dialogTitleII" :append-to-body="true" width="50%" height="50%" v-if="dialogVisibleII" v-model="dialogVisibleII">
              <div slot="append-to-body" class="comment">
                <form @submit.prevent="submit(order)">
                  <div>
                    <label for="order-id" >订单编号：</label>
                    <p>{{order.orderNumber}}</p>
                  </div>
                  <div>
                    <label for="rate">评价等级：</label>
                    <select v-model="order.levels" id="rate" name="rate" required>
                      <option value="">请选择评价等级</option>
                      <option value="好">好</option>
                      <option value="较好">较好</option>
                      <option value="一般">一般</option>
                      <option value="较差">较差</option>
                      <option value="差">差</option>
                    </select>
                  </div>
                  <div>
                    <label for="comment">评价内容：</label>
                    <textarea v-model="order.comments" id="comment" name="comment" rows="5" required></textarea>
                  </div>
                  <button type="submit" style="align-items: center;margin-left: 18rem">提交评价</button>
                </form>
              </div>
            </el-dialog>
          </li>
        </ul>
      </div>
      <button class="lbutton" @click="toggleGetOrders">我接的订单</button><br>
      <div class="orders" v-show="getOrdersShow">
        <ul>
          <li v-for="order in receivedOrders" :key="order.orderNumber">
            <h3>订单编号：{{ order.orderNumber}}</h3>
            <p>商品名称：{{ order.productName }}</p>
            <p>截止日期：{{ order.deadline }}</p>
            <p>来源:{{order.purchaseLocation}}</p>
            <p>目的地:{{order.deliveryLocation}}</p>
            <p>备注(填写快递码等重要信息):{{order.note}}</p>
            <input type="text" id="username" placeholder="请输入四位数确认码" v-model="checkedCode" v-if="order.orderStatus!==3">
            <button class="btn" v-if="order.orderStatus === 1">订单进行中</button>&nbsp;
            <button class="btn" v-if="order.orderStatus === 1" @click="confirmSendFinish(order)">货物已送达</button>&nbsp;
            <button class="btn" v-if="order.orderStatus === 3">订单已完成</button>&nbsp;&nbsp;&nbsp;
            <button class="btn" v-if="order.orderStatus === 2">订单待对方确认完成</button>&nbsp;&nbsp;&nbsp;
            <button class="btn" v-if="order.orderStatus === 3"  @click="showDialog">查看该订单评价</button>
            <el-dialog :title="dialogTitle" :append-to-body="true" width="50%" height="50%" v-if="dialogVisible" v-model="dialogVisible">
              <div slot="append-to-body" class="comment">
                <h3>订单号:</h3>
                <p>{{ order.orderNumber }}</p>
                <h3>订单等级:</h3>
                <p>{{ order.levels }}</p>
                <h3>订单评价:</h3>
                <p>{{ order.comments }}</p>

              </div>
              <div slot="footer" class="dialog-footer">
                <button @click="dialogVisible=false">确定</button>
              </div>
            </el-dialog>
            <!--            <my-dialog v-if="selectedOrder":order=this.order></my-dialog>-->
          </li>
        </ul>

      </div>
    </main>
  </div>
</template>

<script>
import Navi from "@/components/Navi/Navi.vue";
import axios from "axios";
import { ElButton } from 'element-plus'
import { ElDialog } from "element-plus";
import 'element-plus/theme-chalk/el-button.css';
import 'element-plus/theme-chalk/base.css';

export default {
  name: "MyOrders",
  components: {Navi,ElButton,ElDialog},

  data() {
    return {
      dialogTitle: "当前订单的评价",
      dialogTitleII:'评价当前订单',
      dialogContent: "当前订单号:123456，订单评级:A，订单评价：好评",
      dialogContent2: {
        order_id:'',
        order_level:'',
        order_note:'',
      },
      dialogVisible: false,
      dialogVisibleII: false,
      checkedCode:'',
      userId : localStorage.getItem("student_id"),
      selectedOrder:null,
      sentOrdersShow:true,
      getOrdersShow:true,
      historyOrdersShow:false,
      sentOrders: [
        {
          orderNumber: "20200701001",
          productName: "衣服",
          purchaseLocation:"",
          deliveryLocation:"",
          deadline: "2021-04-30",
          note:"",
          orderStatus:"",
          payment:"",
          deliverId:"",
          isPaid:"",
          pickupCode: "",
          comments:"",
          levels:"",
        },
      ],
      receivedOrders: [
        {
          orderNumber: "20200701001",
          productName: "衣服",
          purchaseLocation:"",
          deliveryLocation:"",
          deadline: "2021-04-30",
          note:"",
          orderStatus:"",
          pickupCode: "",
          comments:'',
        },
      ],
      historicalOrders: [
        {
          orderNumber: "20200701001",
          productName: "衣服",
          purchaseLocation:"",
          deliveryLocation:"",
          deadline: "2021-04-30",
          note:"",
          orderStatus:""
        },
      ],
      Student:{
        points:'',
        credit:'',
        studentId: '',
      },
      Deliver:{
        points:'',
        credit:'',
        studentId:'',
      }
    }
  },
  mounted() {
    //从后端获取所有当前用户发送的订单
    axios.get(`http://localhost:8080/findOrdersById/${this.userId}`)
        .then(response => {
          this.sentOrders = response.data;
          console.log(this.sentOrders)
        })
        .catch(error => {
          console.error(error);
        });

    //从后端获取所有用户当前接收的订单
    axios.get(`http://localhost:8080/findMyOrdersById/${this.userId}`)
        .then(response => {
          this.receivedOrders = response.data;
          console.log(this.receivedOrders)
        })
        .catch(error => {
          console.error(error);
        });

    //从后端获取当前用户的所有信息
    axios.get(`http://localhost:8080/findStudent/${this.userId}`)
        .then(response => {
          this.Student = response.data;
          console.log(this.Student)
        })
        .catch(error => {
          console.error(error);
        });

  },

  methods: {
    showDialog() {
      this.dialogVisible = true;
    },
    showDialogII(){
      this.dialogVisibleII = true;
    },
    submit(order) {
      axios.post('http://localhost:8080/comment',order)
          .then(response => {
            console.log("评价等级:",order.levels)
            if(order.levels==='较差'||order.levels==='差')
            {
              //从后端获被评价用户的所有信息
              const getPaidId = order.studentId;
              axios.get(`http://localhost:8080/findStudent/${getPaidId}`)
                  .then(async response => {
                    this.Deliver = response.data;
                    try {
                      this.Deliver.credit = this.Deliver.credit - 1
                      const response = await axios.post('http://localhost:8080/payOrder', this.Deliver);
                      if (response.status === 200) {
                        // console.log('被评价方的信誉:', this.Deliver)
                      } else {
                        this.error = response.data.message;
                      }
                    } catch (e) {
                      this.error = '信誉分扣分失败';
                    }
                    //收款结束
                  })
                  .catch(error => {
                    console.error(error);
                  });
            }
            if(order.levels==='好'||order.levels==='较好')
            {
              //学生信誉分++
              //从后端获被评价用户的所有信息
              const getPaidId = order.studentId;
              axios.get(`http://localhost:8080/findStudent/${getPaidId}`)
                  .then(async response => {
                    this.Deliver = response.data;
                    try {
                      this.Deliver.credit = this.Deliver.credit + 1
                      const response = await axios.post('http://localhost:8080/payOrder', this.Deliver);
                      if (response.status === 200) {
                        // console.log('被评价方的信誉:', this.Deliver)
                      } else {
                        this.error = response.data.message;
                      }
                    } catch (e) {
                      this.error = '信誉分加分失败';
                    }
                    //收款结束
                  })
                  .catch(error => {
                    console.error(error);
                  });
            }
            alert("评价成功");
          })
          .catch(error => {
            console.log(error);
            alert('评价失败，请重新尝试！');
          });
    },
    async payOrder(order) {
      try {
        this.Student.points = this.Student.points - order.payment
        const response = await axios.post('http://localhost:8080/payOrder', this.Student);
        if (response.status === 200) {
          alert("付款成功");
          // 修改当前订单的状态
          order.isPaid = 1;
          order.orderStatus = 2;
          await axios.post('http://localhost:8080/graborders', order);
        } else {
          this.error = response.data.message;
        }
      } catch (e) {
        this.error = '付款失败,请重试';
      }
      //从后端获收款用户的所有信息
      const getPaidId = order.studentId;
      axios.get(`http://localhost:8080/findStudent/${getPaidId}`)
          .then(async response => {
            this.Deliver = response.data;
            //收款开始
            //收款用户收款
            try {
              this.Deliver.points = this.Deliver.points + order.payment
              const response = await axios.post('http://localhost:8080/payOrder', this.Deliver);
              if (response.status === 200) {
                // console.log('收费方的余额:', this.Deliver)
              } else {
                this.error = response.data.message;
              }
            } catch (e) {
              this.error = '收款失败,请重试';
            }
            //收款结束
          })
          .catch(error => {
            console.error(error);
          });
      this.$forceUpdate();
    },
    async confirmSendFinish(order) {
      // 获得当前订单的取件码
      axios.get(`http://localhost:8080/findOrderById/${order.orderNumber}`)
          .then(response => {
            order.pickupCode = response.data.pickupCode;
          })
          .catch(error => {
            console.error(error);
          });
       if (order.pickupCode == this.checkedCode)
       {
         // 取件码匹配
         try {
           order.orderStatus = 3;
           const response = await axios.post('http://localhost:8080/graborders', order);
           if (response.status === 200) {
             alert("确认此商品已送达,待对方确认");
           } else {
             this.error = response.data.message;
           }
         } catch (e) {
           this.error = '订单未完成,请重新确认';
         }
       }
       else{
         alert("取件码不匹配,请重新输入!");
       }
    },
    async confirmFinish(order) {
      if(order.isPaid === 1)
      {
        try {
          order.orderStatus = 3;
          const response = await axios.post('http://localhost:8080/graborders', order);
          if (response.status === 200) {
            alert("此订单已完成");
            order.orderStatus = 3;
          } else {
            this.error = response.data.message;
            alert("订单完成失败!")
          }
        } catch (e) {
          this.error = '订单未完成,请重新确认';
        }
      }
      else
      {
        alert("请先付款!");
      }
    },
    toggleSentOrders(){
      this.sentOrdersShow = !this.sentOrdersShow
    },
    toggleGetOrders(){
      this.getOrdersShow = !this.getOrdersShow
    },
    toggleHistoryOrders(){
      this.historyOrdersShow = !this.historyOrdersShow
    }
  }
}
</script>

<style scoped>
/* 页面容器样式 */
.page-container {
  display: flex;
  height: 100vh;
  margin-top:-10px;
  justify-content: center;
  align-items: flex-start; /* 修改为flex-start */
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

.main-content {
  flex: 1;
  padding: 16px;
  margin-left: 8rem;
  width: 1200px;
}

h1 {
  text-align: center;
  margin-top: 50px;
}

.orders {
  margin: 50px auto;
  width: 80%;
  max-width: 800px;
  border: 1px solid #ccc;
  padding: 20px;
}

.orders h2 {
  font-size: 20px;
  margin-bottom: 10px;
}

.orders ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.orders li {
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;
  padding-bottom: 20px;
}

.orders li:last-child {
  border-bottom: none;
}

.orders h3 {
  font-size: 18px;
  margin-bottom: 10px;
}

.orders p {
  margin: 0;
}
.btn {
  background-color: green;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
}

.btn:hover {
  background-color: grey;
  cursor: pointer;
}

.lbutton {
  display: inline-block;
  margin: 10px;
  padding: 12px 20px;
  font-size: 18px;
  font-weight: bold;
  color: #fff;
  text-transform: uppercase;
  background-color: #c23745;
  border: none;
  border-radius: 25px;
  box-shadow: 0 0 2px rgba(0,0,0,0.25);
  transition: all 0.3s ease;
}

.lbutton:hover {
  background-color: #000;
  color: #c23745;
  box-shadow: 0px 2px 2px rgba(0,0,0,0.25);
}

@media screen and (max-width: 768px) {
  .lbutton {
    display: block;
    margin: 10px auto;
  }
}

.dialog-footer button {
  background-color: #f0f0f0;
  border: none;
  color: #333;
  padding: 15px 30px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px 4px 19rem;
  cursor: pointer;
  border-radius: 5px;
  box-shadow: 0px 2px 5px #ccc;
}

.dialog-footer button:hover {
  background-color: #ededed;
  box-shadow: 0px 2px 5px #999;
}

.dialog-footer button.active {
  background-color: #007bff;
  color: #fff;
  box-shadow: 0px 2px 5px #0056b3;
}

.comment p {
  font-size: 16px;
  font-weight: bold;
  color: #333;
  text-align: center;
  margin-top: 30px;
  margin-bottom: 20px;
  padding: 10px;
  border-radius: 5px;
  background-color: #f2f2f2;
}

.comment p:before, p:after {
  content: "";
  display: table;
  border-collapse: collapse;
}

.comment p:after {
  clear: both;
}

.comment p.marketing-text {
  font-size: 20px;
  font-weight: bold;
  color: #333;
  text-align: center;
}

.comment p.marketing-text:before, p.marketing-text:after {
  content: "";
  display: table;
  border-collapse: collapse;
}

.comment p.marketing-text:after {
  clear: both;
}

input[type="text"],
select,
textarea {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
}

/* 给提交按钮添加样式 */
button[type="submit"] {
  background-color: #ff8168;
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px 20px;
  font-size: 16px;
  cursor: pointer;
  transition: all 0.3s ease;
}

button[type="submit"]:hover {
  background-color: #e06862;
}
/* 给评价等级下拉框添加样式 */
select#rate {
  background-color: #fff;
  appearance: none;
  -webkit-appearance: none;
  -moz-appearance: none;
  background-repeat: no-repeat;
  background-position: right 10px center;
}

h1{
  margin:2.5rem;
}

/* 给评价等级选项添加不同颜色 */
select#rate option[value="好"] {
  background-color: #4caf50;
  color: #fff;
}

select#rate option[value="较好"] {
  background-color: #8bc34a;
  color: #fff;
}

select#rate option[value="一般"] {
  background-color: #ffeb3b;
  color: #333;
}

select#rate option[value="较差"] {
  background-color: #e68a00;
  color: #fff;
}

select#rate option[value="差"] {
  background-color: #d32f2f;
  color: #fff;
}

/* 给评价内容文本框添加自适应宽度和边框半径 */
textarea#comment {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 16px;
  resize: vertical;
  min-height: 100px;
}

/* 给错误提示添加样式 */
input:invalid,
textarea:invalid {
  border-color: #f44336;
}

input:valid,
textarea:valid {
  border-color: #ccc;
}

.orders {
  margin: 50px auto;
  width: 80%;
  max-width: 800px;
  border: 1px solid #ccc;
  padding: 20px;
  background-color: #f2f2f2;
}

.orders h2 {
  font-size: 24px;
  margin-bottom: 10px;
}

.orders ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.orders li {
  margin-bottom: 5px;
  border-bottom: 1px solid #ccc;
  padding-bottom: 10px;
}

.orders li:last-child {
  border-bottom: none;
}

.orders h3 {
  font-size: 18px;
  margin-bottom: 10px;
}

.orders p {
  font-size: 16px;
  line-height: 1.5;
  margin: 10px 0;
}

.btn {
  margin-top: 4rem;
  background-color: #007bff;
  color: #fff;
  padding: 10px 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 15px;
}

.btn:hover {
  background-color: #0056b3;
}
</style>