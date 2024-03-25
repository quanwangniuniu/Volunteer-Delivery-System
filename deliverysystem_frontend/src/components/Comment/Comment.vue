<template>
  <div class="main-content">
    <Navi></Navi>
    <h1>订单评价</h1>
    <form @submit.prevent="submit(order.orderNumber)">
      <div>
        <label for="order-id">订单编号：</label>
        <input type="text" v-model="order.orderNumber" id="order-id" name="order-id" required>
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
      <button type="submit">提交评价</button>
    </form>
  </div>
</template>

<script>
import Navi from "@/components/Navi/Navi.vue";
import axios from "axios";
export default {
  name: 'OrderEval',
  components: {
    Navi,
  },
  data() {
    return {
      order: {
        orderNumber: '',
        levels: '',
        comments: '',
        payment:'',
        deadline:'',
        studentId:'',
        deliverId:'',
        isPaid:'',
        pickupCode:'',
      },
    };
  },
  methods: {
    submit(num) {
      axios.get(`http://localhost:8080/findOrderById/${num}`)
          .then(response => {
            console.log("从后端返回的数据:",response.data)
            this.order.deliverId = response.data.deliverId;
            this.order.payment = response.data.payment;
            this.order.pickupCode = response.data.pickupCode;
            this.order.studentId = response.data.studentId;
            this.order.isPaid = response.data.isPaid;
          })
          .catch(error => {
            console.error(error);
          });
      console.log("评价状态订单信息:",this.order)
      axios.post('http://localhost:8080/comment', this.order)
          .then(response => {
              alert("评价成功");
              this.order.levels=''
              this.order.comments=''
              this.order.orderNumber=''
          })
          .catch(error => {
            console.log(error);
            alert('评价失败，请重新尝试！');
          });
    },
  },
};

</script>

<style scoped>
/* 给评价表单的顶级元素main-content添加样式 */
.main-content {
  margin-left: 25rem;
  max-width: 900px;
  padding: 20px;
  width: 500px;
}

/* 给输入框和标签添加样式 */
label {
  display: inline-block;
  margin-bottom: 5px;
  font-weight: bold;
  color: #333;
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

</style>