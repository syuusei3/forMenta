<template>
    <div>
        <h1>Login Page</h1>
        <div class="login-form">
            <form>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">User name</label>
                    <input type="username" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" v-model="username">
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
            username: '',
            email: '',
            password: '',
            userCredential: '',
            msg: '',
        }
    },
    methods: {
        login() {
            // ログインに使用するユーザーの認証情報をjsonで作成

            // const userCredential = {
            //     username: this.username,
            //     password: this.password
            // }
            // ログインを行うHTTPリクエスト
            const loginEndpoint ='http://localhost:8000/login';

            // console.log("Login",userCredential)
            //chage content-type

            const params = new URLSearchParams();
            params.append('username', this.username); //すみません、ここのusernameがスペルミスしてました...
            params.append("password", this.password);

            // axios.post(loginEndpoint, userCredential)
            axios.post(loginEndpoint, params)

            .then((res) => {
                console.log("Response",res)
                this.$router.push('/board')
                //this.$router.push('/board', JSON.stringify(res))
                // this.msg = res.data.msg
                // localStorage.setItem('admin_token',JSON.stringify(res.data.admin_token))
                // if(res.data.authorized) {
                //     this.$router.push('/board')
                // }
            })
            .catch((err) => {
                alert("Login error")
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