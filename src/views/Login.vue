<template>
  <div class="login">
    <div class="card">
      <div class="front" :class="isTop ? 'contain-Before' : ''" id="1">
        <v-container>
          <v-form ref="form" lazy-validation>
            <img src="../assets/realMo.png" id="logo" /><br />
            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="10">
                  <v-text-field
                    v-model="id"
                    :rules="idRules"
                    label="学号/教工号"
                    required
                  ></v-text-field>

                  <v-text-field
                    v-model="password"
                    :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
                    :rules="passwordRules"
                    :type="show1 ? 'text' : 'password'"
                    label="密码"
                    required
                    @click:append="show1 = !show1"
                  ></v-text-field>

                  <v-btn :disabled="!valid" class="button" @click="Login" large>
                    <p class="login_">登录</p>
                  </v-btn>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </v-container>
        <v-container>
          <v-col justify="center" cols="12">
            <v-btn text color="primary" class="register" @click="toRegister"
              >注册</v-btn
            >
            <v-btn text color="primary" class="forget" :to="'/ForgetPassword'"
              >忘记密码</v-btn
            >
          </v-col>
        </v-container>
      </div>
      <div class="back" :class="isTop ? 'contain-After' : ''" id="2">
        <v-container>
          <v-form ref="form" lazy-validation>
            <img src="../assets/moyunlogo.png" id="logo" /><br />
            <v-container>
              <v-row justify="center">
                <v-col cols="12" sm="10">
                  <v-text-field
                    v-model="id"
                    :rules="idRules"
                    label="学号/教工号"
                    required
                  ></v-text-field>

                  <v-text-field
                    v-model="password"
                    :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
                    :rules="passwordRules"
                    :type="show2 ? 'text' : 'password'"
                    label="密码"
                    required
                    @click:append="show2 = !show2"
                  ></v-text-field>

                  <v-text-field
                    v-model="rePassword"
                    :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
                    :type="show3 ? 'text' : 'password'"
                    :rules="[affirmPass]"
                    label="确认密码"
                    required
                    @click:append="show3 = !show3"
                  ></v-text-field>

                  <v-text-field
                    v-model="Email"
                    :rules="emailRules"
                    label="E-mail"
                    required
                  ></v-text-field>

                  <v-btn :disabled="!valid" class="button" @click="Register">
                    <p class="register_">注册</p>
                  </v-btn>
                </v-col>
              </v-row>
            </v-container>
          </v-form>
        </v-container>
        <v-container>
          <v-row>
            <v-col justify="center" cols="12">
              <v-btn text color="primary" class="return" @click="toLogin"
                >登录</v-btn
              >
            </v-col>
          </v-row>
        </v-container>
      </div>
      <v-snackbar
        v-model="snackbar"
        :timeout="3000"
        color="blue-grey"
        absolute
        rounded="pill"
        top
      >
        {{ message }}
      </v-snackbar>
    </div>
  </div>
</template>

<script>
export default {
  inject: ["reload"],
  data() {
    return {
      valid: true,
      show1: false,
      show2: false,
      show3: false,
      isTop: false,
      timer: null,
      snackbar: false,
      id: "",
      idRules: [(v) => !!v || "请填写账号"],
      pass: "",
      password: "",
      passwordRules: [(v) => !!v || "请填写密码"],
      rePassword: "",
      Email: "",
      emailRules: [
        (v) => !!v || "请填写邮箱",
        (v) => /.+@.+\..+/.test(v) || "邮箱格式不合法",
      ],
      checkbox: false,
      message: "error",
    };
  },
  methods: {
    toRegister() {
      if (!this.isTop) {
        this.isTop = true;
        this.clear();
      }
    },
    toLogin() {
      if (this.isTop) {
        this.isTop = false;
        this.clear();
      }
    },
    Login() {
      this.validate();
      this.$http({
        method: "post",
        url: "http://localhost:5000/api/login",
        data: {
          username: this.id,
          password: this.password,
        },
        withCredentials: true
      })
        .then((res) => {
          this.message = res.data.message;

          if (res.data.message === '登录成功') {
            this.$store.commit("setLogin");
            this.$store.commit("setUserID", this.id);
            
            if (res.data.user && res.data.user.isAdmin) {
              this.$store.commit("setAdmin");
              this.$router.push({ path: "/Admin" });
            } else {
              this.$store.commit("setIsTeacher", res.data.user ? res.data.user.isTeacher : false);
              this.$router.push({ path: "/" });
            }
          }
        })
        .catch((err) => {
          console.error(err);
          this.message = "登录失败，请检查用户名和密码";
        });
      this.snackbar = true;
    },
    Register() {
      if (!this.validate()) {
        return;
      }
      this.$http({
        method: "post",
        url: "http://localhost:5000/api/register",
        data: {
          username: this.id,
          password: this.password,
          email: this.Email,
        },
        withCredentials: true
      })
      .then(response => {
        this.message = "注册成功，请登录";
        this.snackbar = true;
        setTimeout(() => {
          this.toLogin();
        }, 1500);
      })
      .catch(error => {
        console.error('注册失败:', error);
        this.message = "注册失败，请稍后重试";
        this.snackbar = true;
      });
    },
    validate() {
      return this.$refs.form.validate();
    },
    clear() {
      this.id = "";
      this.password = "";
      this.rePassword = "";
      this.Email = "";
      this.$refs.form.resetValidation();
    },
    affirmPass() {
      return this.rePassword === this.password || "两次密码不一致";
    },
  },
};
</script>

<style>
.login {
  background-color: #efeeee;
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}
#logo {
  position: relative;
  bottom: -20px;
}

.button {
  color: #596275;
  box-shadow: 9px 9px 18px rgba(0, 0, 0, 0.1),
    -9px -9px 18px rgba(255, 255, 255, 1);
  border-radius: 3rem;
  transition: box-shadow 0.2s ease-out;
  background-color: #efeeee;
  position: relative;
  top: 30px;
  margin-top: 20px;
  width: 130px;
  height: 55px;
  outline: none;
  border: none;
}
.button:hover {
  font-size: 19px;
  box-shadow: 1px 1px 2px rgba(0, 0, 0, 0.2),
    -1px -1px 2px rgba(255, 255, 255, 0.8);
  border-radius: 3rem;
  transition: box-shadow 0.2s ease-out;
  transition: font-size 0.2s ease-out;
}
.login_ {
  font-size: 20px;
  margin-top: 15px;
}
.register_ {
  font-size: 20px;
  margin-top: 15px;
}
.card {
  perspective: 150rem;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  height: 700px;
  width: 550px;
  z-index: 1;
}
.card .front,
.card .back {
  position: absolute;
  text-align: center;
  box-shadow: 12px 12px 24px rgba(0, 0, 0, 0.1),
    -12px -12px 24px rgba(255, 255, 255, 1);
  border-radius: 3rem;
  background-color: #efeeee;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  backface-visibility: hidden;
  transition: 1s cubic-bezier(0.68, -0.55, 0.265, 1.55);
}
.front,
.back {
  position: absolute;
  transition: 0.3s linear;
  backface-visibility: hidden;
}
.register {
  position: relative;
  left: 100px;
  top: 40px;
}
.forget {
  position: relative;
  right: 100px;
  top: 40px;
}
.turn {
  text-align: right;
}

.back {
  transform: rotateY(-180deg);
}
.front {
  transform: rotateY(0deg);
}
.return {
  top: 40px;
}
.card .contain-Before {
  transform: rotateY(-180deg);
}
.card .contain-After {
  transform: rotateY(0deg);
}
</style>
