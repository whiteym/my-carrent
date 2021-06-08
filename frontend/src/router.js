
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import ProductManager from "./components/ProductManager"


import MyPage from "./components/MyPage"
import StoreManager from "./components/StoreManager"

import BookingManager from "./components/BookingManager"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/Product',
                name: 'ProductManager',
                component: ProductManager
            },


            {
                path: '/MyPage',
                name: 'MyPage',
                component: MyPage
            },
            {
                path: '/Store',
                name: 'StoreManager',
                component: StoreManager
            },

            {
                path: '/Booking',
                name: 'BookingManager',
                component: BookingManager
            },



    ]
})
