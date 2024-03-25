import { createApp } from 'vue'
import App from './App.vue'
import {createRouter,createWebHistory} from "vue-router";
import './assets/main.css'
import Login from "@/components/Login/Login.vue";
import Register from "@/components/Register/Register.vue";
import PersonInfo from "@/components/PersonInfo/PersonInfo.vue";
import Comment from "@/components/Comment/Comment.vue";
import Grab from "@/components/GrabOrder/Grab.vue";
import Release from "@/components/ReleaseOrder/Release.vue";
import MyOrders from "@/components/Myorder/MyOrders.vue";
import myOrders from "@/components/Myorder/MyOrders.vue";
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
import Books from "@/components/Book/Books.vue";
import PersonInfo_test from "@/components/PersonInfo/PersonInfo_test.vue";
const routes=[
    {path:'/',name:Login,component:Login},
    {path:'/register',name:Register,component: Register},
    {path:'/personalInfo',name:PersonInfo,component: PersonInfo,meta:{requiresAuth:true}},
    {path:'/comment',name:Comment,component: Comment,meta:{requiresAuth:true}},
    {path:'/grab',name:Grab,component: Grab,meta:{requiresAuth:true}},
    {path:'/release',name:Release,component: Release,meta:{requiresAuth:true}},
    {path:'/myorders',name:MyOrders,component: myOrders,meta: {requiresAuth:true}},
    {path:'/books',name:Books,component: Books,meta:{requiresAuth: true}},
];



const router = createRouter(
    {
        history:createWebHistory(),
        routes,
    }
)

router.beforeEach((to,from,next)=>{
    const isAuthenticated = localStorage.getItem("username");
    if(to.matched.some(record=>record.meta.requiresAuth)){
        if(!isAuthenticated)
        {
            next({path:'/login'})
        }
        else
        {
            next();
        }
    }else{
        next();
    }
})
createApp(App).use(router,ElementPlus).mount('#app')
