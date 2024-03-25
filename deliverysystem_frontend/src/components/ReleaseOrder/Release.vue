<template>
  <div class="container">
  <div class="main-content">
    <Navi></Navi>
    <form @submit.prevent="submitOrder">
      <label for="delivery-from">物品:</label>
      <input type="text" v-model="order.productName" id="delivery-name" name="delivery-name">
      <label for="delivery-from">取货地址:</label>
      <input type="text" v-model="order.purchaseLocation" id="delivery-from" name="delivery-from">
      <label for="delivery-to">送往地址：</label>
      <input type="text" v-model="order.deliveryLocation" id="delivery-to" name="delivery-to">
      <label for="delivery-date">截止送达日期：</label>
      <input type="date" v-model="order.deadline" id="delivery-date" name="delivery-date">
      <label for="delivery-price" style="color:#1b6dc1">报酬(积分数)：</label>
      <input type="text" v-model="order.payment" id="delivery-price" name="delivery-price">
      <label for="delivery-description">备注:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red">(填写快递取件码等)</span></label>
      <textarea v-model="order.note" id="delivery-description" name="delivery-description"></textarea>
      <button type="submit" class="wow-button" style="align-self: center">发布订单</button>
    </form>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
import Navi from "@/components/Navi/Navi.vue";
export default {
  name: 'OrderForm',
  components: {Navi},
  data() {
    return {
      order: {
        productName:'',
        purchaseLocation: '',
        deliveryLocation: '',
        deadline: '',
        payment: '',
        note: '',
        deliverId:localStorage.getItem("student_id"),
        pickupCode:'',
      }
    }
  },
  methods: {
    async submitOrder() {
      //生成取件码
      this.order.pickupCode = Math.floor(Math.random() * (9999 - 1000 + 1)) + 1000;
      console.log("生成的取件码:",this.order.pickupCode)
      await axios.post("http://localhost:8080/releaseorder", this.order)
          .then(response => {
            console.log(response.data)
            alert("发单成功，马上有人来处理了！")
            this.order.note=""
            this.order.payment=""
            this.order.deadline=""
            this.order.purchaseLocation=""
            this.order.productName=""
            this.order.deliveryLocation=""
          })
          .catch(error => {
            console.log(error)
          })
    }
  }
}
</script>


<style scoped>
.main-content {
  flex: 1;
  padding: 20px;
  width:960px;
  margin-left: 15rem;

}

.container{
  background-image: url("Images/release.jpg");
  background-size: 1400px,800px;
  width:1300px;
  height:800px;
  margin-left: 4rem;
}

h1 {
  margin-bottom: 20px;
  font-size: 32px;
  color: #eeff00;
  background-color: #019293;
  width:15%;
  border: crimson 1px solid;
}

form {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  width: 80%;
}

label {
  display: block;
  margin-bottom: 10px;
  font-size: 20px;
  color: #bdde12;
  font-family:sans-serif;
}

input[type="text"],
input[type="date"],
input[type="time"],
textarea {
  display: block;
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  margin-bottom: 20px;
}

input[type="submit"] {
  background-color: blue;
  color: #fff;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  font-size: 18px;
  cursor: pointer;
}
input[type="submit"]:hover {
  background-color: #0066cc;
}
.wow-button {
  display: inline-block;
  padding: 15px 30px;
  margin: 20px;
  background-color: #a0a0a0;
  background-image: linear-gradient(to bottom, #a0a0a0, #7f7f7f);
  border-top: 1px solid #e5e5e5;
  border-left: 1px solid #e5e5e5;
  border-bottom: 1px solid #555;
  border-right: 1px solid #555;
  border-radius: 20px;
  color: #fff;
  font-size: 20px;
  font-weight: bold;
  text-shadow: 1px 1px #555;
  cursor: pointer;
  transition: all 0.2s ease-in-out;
}

.wow-button:hover {
  background-color: #7f7f7f;
  background-image: linear-gradient(to bottom, #7f7f7f, #a0a0a0);
  border-top: 1px solid #555;
  border-left: 1px solid #555;
  border-bottom: 1px solid #e5e5e5;
  border-right: 1px solid #e5e5e5;
  text-shadow: 2px 2px #555;
}

</style>