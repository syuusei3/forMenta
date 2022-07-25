<template>
    <div>
        <h1>Login Page</h1>
        <div class="login-form">
            <form>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="email">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" v-model="password">
                </div>
                <button type="button" class="btn btn-primary" v-on:click="login()">Submit</button>
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
            userCredential: '',
            msg: '',
        }
    },
    methods: {
        login() {
            // ログインに使用するユーザーの認証情報をjsonで作成
            const userCredential = {
                email: this.email,
                password: this.password
            }
            this.userCredential = userCredential

            // ログインを行うHTTPリクエスト
            const loginEndpoint ='http://localhost:8000/login';
            axios.post(loginEndpoint, userCredential)
            .then((res) => {
                console.log(res)
                this.$router.push('/board')
                //this.$router.push('/board', JSON.stringify(res))
                // this.msg = res.data.msg
                // localStorage.setItem('admin_token',JSON.stringify(res.data.admin_token))
                // if(res.data.authorized) {
                //     this.$router.push('/board')
                // }
            })
            .catch((err) => {
                console.log(err)
            })
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