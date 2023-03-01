
import  Vuex from 'vuex'
export  default  new Vuex.Store({
    state:{
        routes:[]
    },
    mutations:{
        initRoutes(state,data){
            state.routes=data;
        }
    },
    actions:{}
})