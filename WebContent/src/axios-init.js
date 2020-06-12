import axios from 'axios'
import router from './router'

export default()=>{

   axios.defaults.baseURL = 'http://47.93.213.196:8080//qujing'; // 配置axios请求的地址

   axios.defaults.crossDomain = true;  //设置cross跨域 并设置访问权限 
  
 
  

   return axios;
    }






   
	



    
    

/**
 * 
 */