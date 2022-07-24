<template>
    <div>
        <h1>Login Page</h1>
        <div class="login-form">
            <form>
                <div class="mb-3">
                    <label for="exampleInputName" class="form-label">User name：</label>
                    <input type="email" class="form-control" id="exampleInputName" aria-describedby="emailHelp" v-model="name">
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email address：</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="email">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password：   </label>
                    <input type="password" class="form-control" id="exampleInputPassword1" v-model="password">
                </div>
                <button type="button" class="btn btn-primary" v-on:click="makeAccount()">Submit</button>
            </form>
        </div>
        <p class="msg">{{msg}}</p>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    data: function() {
        return {
            email: '',
            password: '',
            name:'',
            userCredential: '',
            msg: '',
        }
    },
    methods: {
        makeAccount() {
            // ログインに使用するユーザーの認証情報をjsonで作成
            const AccountInfo = {
                name:this.name,
                email: this.email,
                password: this.password
            }
            console.log(AccountInfo);

            // ログインを行うHTTPリクエスト
            const makeEndpoint ='http://localhost:8000/addaccount';
            axios.post(makeEndpoint, AccountInfo)
            // .then((res) => {
            //     console.log(res)
            // })
            // .catch((err) => {
            //     console.log(err)
            // })
        },

    }
}
</script>

<style scoped>
.login-form {
    width: 50%;
    margin: auto;
}
.msg {
    color: blue;
    font-weight: bold;
}
</style>