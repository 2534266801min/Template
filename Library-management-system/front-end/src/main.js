import Vue from 'vue';
import App from './App.vue';
import router from './router';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css'; // default theme
// import './assets/css/theme-green/index.css'; // green theme
import './assets/css/icon.css';
import './components/common/directives';
import 'babel-polyfill';

Vue.config.productionTip = false;
Vue.use(ElementUI, {
    size: 'small'
});


router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title} | library-management-system`;
    const role = localStorage.getItem("token");
    if (!role && to.path !== '/login' && to.path !== '/register') {
        next('/login');
    } else if (to.meta.permission) {
        role === 'admin' ? next() : next('/403');
    } else {
        if (navigator.userAgent.indexOf('MSIE') > -1 && to.path === '/editor') {
            Vue.prototype.$alert('Vue quill editor component is not compatible with ie10 and below browsers. Please use a higher version of browser to view', 'Browser incompatibility notification', {
                confirmButtonText: 'Confirm'
            });
        } else {
            next();
        }
    }
});

new Vue({
    router, 
    render: h => h(App)
}).$mount('#app');
