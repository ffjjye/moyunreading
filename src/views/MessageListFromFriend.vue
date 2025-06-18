<template>
  <div class="friMsg">
    <template>
      <bar></bar>
      <v-row justify="center">
        <v-main app>
          <v-container fluid class="pa-4 text-center">
            <v-row justify="center">
              <v-card width="800px" class="mx-auto" style="margin-top: 50px" >
                <v-toolbar dense text-align="center" justify="center">
                  <v-app-bar-nav-icon></v-app-bar-nav-icon>
                  <v-toolbar-title>{{ this.messageBoxTitle }}</v-toolbar-title>
                  <v-spacer></v-spacer>
                  <v-btn icon>
                    <v-icon>mdi-magnify</v-icon>
                  </v-btn>
                  <v-btn icon>
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>
                  <v-btn icon>
                    <v-icon>mdi-dots-vertical</v-icon>
                  </v-btn>
                </v-toolbar>

                <v-list dense rounded>
                  <template v-for="(item, index) in messageTargetList">
                    <v-list-item
                      link
                      @click="toFriend(item.userID)"
                      class="px-2"
                      :key="index"
                      style="margin-left:5px"
                    >
                      <v-list-item-avatar color="grey darken-1">
                        <v-img :src="item.headImage"></v-img>
                      </v-list-item-avatar>
                      <v-list-item-content>
                        <v-list-item-title class="font-italic">{{
                          item.username
                        }}</v-list-item-title>
                        <v-list-item-subtitle class="text-caption">{{
                          item.latestMessage
                        }}</v-list-item-subtitle>
                        <v-list-item-subtitle class="text-caption grey--text">{{
                          item.latestTime
                        }}</v-list-item-subtitle>
                      </v-list-item-content>
                    </v-list-item>
                  </template>
                </v-list>
              </v-card>
            </v-row>
          </v-container>
        </v-main>
      </v-row>
    </template>
  </div>
</template>

<script>
import avatar from "../components/Avatar.vue";
import bar from "../components/Bar.vue";
export default {
  inject: ["reload"],
  data: () => ({
    myInterval: null,
    messageBoxTitle: "对话",
    divider: true,
    inset: true,
    sendUser: {
      userID: 1,
      username: "fjy",
      headImage: require("@/assets/avatar.jpg"),
    },
    getUser: {
      userID: 2,
      username: "张教授",
      headImage: require("@/assets/avatar.jpg"),
    },
    messageTargetList: [
      {
        userID: 2,
        username: "张教授",
        headImage: require("@/assets/avatar.jpg"),
        role: "导师",
        latestMessage: "这是个很有意义的研究方向。",
        latestTime: "2025-05-10 10:15"
      },
      {
        userID: 3,
        username: "李同学",
        headImage: require("@/assets/avatar.jpg"),
        role: "同学",
        latestMessage: "嗨，你上次推荐的书怎么样？",
        latestTime: "2025-05-20 11:30"
      },
      {
        userID: 4,
        username: "王同学",
        headImage: require("@/assets/avatar.jpg"),
        role: "同学",
        latestMessage: "明天有空一起去图书馆吗？",
        latestTime: "2025-06-01 14:00"
      }
    ],
    systemMessageList: [],
  }),

  components: {
    avatar,
    bar,
  },

  props: {
    userID: {
      type: String,
      default: "0",
    },
  },

  created() {
    if (this.userID != 0) {
      let index = parseInt(this.userID);
      if (index > 0 && index <= this.messageTargetList.length) {
         this.messageBoxTitle = this.messageTargetList[index - 1].username;
      }
    }
  },

  mounted() {
    clearInterval(this.myInterval);
  },

  beforeDestroy() {
    clearInterval(this.myInterval);
  },

  methods: {
    toFriend(userID) {
      this.$router.push({ path: "/MessageListFromFriend/" + userID });
      this.reload();
    },
  },

};
</script>

<style scoped>
.RList v-snackbar {
  text-align: "right";
  margin: 10px;
  display: inline-block;
  padding: 3px 10px;
  border: 1px solid aliceblue;
  border-radius: 15px;
}
.LList v-snackbar {
  text-align: "left";
  display: inline-block;
  padding: 3px 10px;
  border: 1px solid aliceblue;
  border-radius: 15px;
  padding: 5px 10px;
}

.friMsg {
	background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a5d57e, #23d5ab);
	background-size: 400% 400%;
	animation: gradient 15s ease infinite;
      width:100%;
    height:100%;
}

@keyframes gradient {
	0% {
		background-position: 0% 50%;
	}
	50% {
		background-position: 100% 50%;
	}
	100% {
		background-position: 0% 50%;
	}
}

</style>
