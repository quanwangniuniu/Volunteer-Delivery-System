<template>
  <Navi></Navi>
  <div class="book-exchange">
    <div class="book-list">
      <div v-for="(book, index) in books" :key="index" class="book-item">
        <img :src="book.coverImgUrl" alt="book-cover" class="book-cover" style="margin-left: 20rem"/>
        <div class="book-info">
          <h3 class="book-title">《{{ book.title }}》</h3>
          <p class="book-author">作者：{{ book.author }}</p>
          <p class="book-publisher">出版社：{{ book.publisher }}</p>
          <p class="book-description">{{ book.description }}</p>
        </div>
        <div class="book-cost">
          <p class="cost-value" style="color: #e75c5c">{{ book.cost }} 积分</p>
        </div>
        <button class="exchange-btn" @click="confirmExchange(book)">兑换</button>
        <div>
          <button v-on:click="download1" v-if="book.isPaid===true&&book.id===1">Download PDF</button>
          <button v-on:click="download2" v-if="book.isPaid===true&&book.id===2">Download PDF</button>
          <button v-on:click="download3" v-if="book.isPaid===true&&book.id===3">Download PDF</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Navi from "@/components/Navi/Navi.vue";
import axios from "axios";
import fileDownload from "js-file-download";
import  jsFileDownload  from 'js-file-download';
import { saveAs } from 'file-saver';

export default {
  components:{Navi},
  data() {
    return {
      books: [
        {
          id:1,
          title: '活着',
          author: '余华',
          publisher: '江苏师范大学出版社',
          coverImgUrl: 'Images/templatemo_image_02.jpg',
          description: '《活着》是中国作家余华所写的一本长篇小说，讲述了一个男人的命运和对生死的思考。小说主人公福贵在一个贫苦农村出生，他一直劳作着，直到他成为一个富翁。但是，在中国的社会变革时期，他失去了他所有的财产和家庭。小说生动描绘了人性的复杂和充满挫折和痛苦的旅程，同时也探讨了生命的本质和存在的意义。这本书在中国以及国际上都备受赞誉，成为了中国当代文学的代表作之一。',
          cost: 400,
          isPaid:false
        },
        {
          id:3,
          title: '百年孤独',
          author: '加夫列尔·加西亚·马尔克斯',
          publisher: '江苏师范大学科文学院出版社',
          coverImgUrl: 'Images/templatemo_image_03.jpg',
          description: '《百年孤独》是哥伦比亚作家加夫列尔·加西亚·马尔克斯所写的一本长篇小说，描绘了一个神秘的布恩迪亚家族七代人的传奇故事。这个家族充满了秘密和神秘的力量，其中有个人也成为了家族的叛徒，引发了连锁反应。小说以超现实主义和魔幻现实主义的风格展开叙述，让读者充分感受到了马尔克斯的文学魅力和思想深度，成为了拉丁美洲文学的代表作之一。',
          cost: 700,
          isPaid:false
        },
        {
          id:2,
          title: '三体-黑暗森林',
          author: '刘慈欣',
          publisher: '江苏师范大学盗版社',
          coverImgUrl: 'Images/templatemo_image_04.jpg',
          description: '《三体》是中国科幻作家刘慈欣所写的一部长篇小说。小说讲述了人类在地球上发现了一种名为“三体”的外星文明，两个文明之间的接触和交流最终导致了一场宏大的宇宙灾难。小说深入探讨了人类文明的发展和进化，以及面对未知和危机时的选择和思考。这本书在国内外科幻界都获得了高度评价和认可，成为了中国科幻文学的代表作之一。',
          cost: 900,
          isPaid:false
        },
        // ...
      ],
      userId : localStorage.getItem("student_id"),
      Student:{
        points:'',
        credit:'',
        studentId: '',
        isPaid:true
      },
    };
  },
  methods: {
    download1() {
        const url = 'http://localhost:8080/download1'; // 后端提供的下载接口 URL
        window.open(url, '活着.pdf');
    },
    download2() {
      const url = 'http://localhost:8080/download2'; // 后端提供的下载接口 URL
      window.open(url, '三体——黑暗森林.pdf');
    },
    download3() {
      const url = 'http://localhost:8080/download3'; // 后端提供的下载接口 URL
      window.open(url, '百年孤独.pdf');
    },
    confirmExchange(book) {
      //从后端获取当前用户的积分信息
      const username = localStorage.getItem("username")
      axios.get(`http://localhost:8080/studentfindPoints/${username}`)
          .then(response => {
            this.Student.points = response.data;
            // 扣积分后的积分
            this.Student.points = this.Student.points-book.cost;
            console.log("扣除积分后的积分:",this.Student.points);
            axios.post('http://localhost:8080/payOrder', this.Student)
            alert("兑换成功！请立刻下载，否则就要失效喽！")
            book.isPaid = true;
          })
          .catch(error => {
            console.error(error);
          });
    },
  },
  mounted() {
    //从后端获取当前用户的所有信息
    axios.get(`http://localhost:8080/findStudent/${this.userId}`)
        .then(response => {
          this.Student = response.data;
        })
        .catch(error => {
          console.error(error);
        });

  },
};
</script>

<style>
/* 游戏布局样式 */
.book-exchange {
  width:800px;
  margin-left: 20rem;
  background-color: rgb(255, 255, 255);
}

.book-list {
  width:800px;
  margin-right: 2rem;
}

.book-item {
  width:800px;
  margin-bottom: 1.5rem;
  padding: 1rem;
  border: 1px solid #ddd;
}

.book-cover {
  flex-basis: 6rem;
  margin-right: 1.5rem;
}

.book-info {
  flex-basis: 50%;
}

.book-title {
  margin-bottom: 0.5rem;
  font-size: 1.2rem;
  font-weight: bold;
  align-items: center;
}

.book-author,
.book-publisher {
  margin-bottom: 0.5rem;
  font-size: 1rem;
}

.book-cost {
  display: flex;
  flex-direction: column;
  flex-basis: 20%;
  align-items: center;
}

.cost-label {
  margin-bottom: 0.5rem;
  font-size: 1rem;
  color: #666;
}

.cost-value {
  font-size: 1.2rem;
  font-weight: bold;
}

.exchange-btn {
  background-color: #e75c5c;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  cursor: pointer;
  transition: all 0.3s ease;
  margin-left: 42rem;
  margin-top: 2rem;
  margin-bottom: 2rem;
}

.exchange-btn:hover {
  background-color: #0069d9;
}

</style>