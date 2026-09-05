<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-card style="margin-left: 10px;margin-right: 40px;padding-bottom: 20px;" title="数据面板">
        <template #header>
            <div
              class="userlist-header"
              style="font-size: 15px; font-weight: bold"
            >
              <p
                style="
                  text-align: left;
                  margin-top: -2px;
                  display: inline-block;
                  border-left: 5px solid rgb(53, 84, 187);
                  padding-left: 10px;
                "
              >
              数据面板
              </p>
            </div>
          </template>
      <el-col :sm="24" :lg="8">
        <el-card class="all-card" title="今日甄别用户数" style="background:linear-gradient(to right, #4DB3F6,#748CF3);">
          <template #header>
            <div class="event-header">
              <!-- <img src="src\img\images.jpg" style="width: 30px; height: 30px; margin-right: 10px;"> -->
              <p style="text-align:center;margin-top: -1px;letter-spacing:2px">今日甄别用户数</p>
            </div>
          </template>
          <el-row>
            <el-col :span="10">
              <div style="width:72px;height:72px;line-height: 50px;font-size: 50px;margin-top: 2px;
          margin-left: 60px;">
              <el-image :src="require('@/assets/icons/user.png')" fit="contain">
              </el-image>
            </div>
            </el-col>
            <el-col :span="14">
            <div  style="font-size: 50px;font-weight: 800; text-align:center;color: white; ">{{ userCount }}</div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col sm="24" :lg="8">
        <el-card class="all-card" title="处理洗钱事件数" style="background:linear-gradient(to right, #FCC591,#FF7D7A)">
          <template #header>
            <div
              class="event-header"
            >
              <p style="padding-left: 10px; margin-top: -1px;letter-spacing:2px">处理洗钱事件数</p>
            </div>
          </template>
          <el-row>
            <el-col :span="10">
              <div style="width:65px;height:65px;line-height: 50px;font-size: 50px;margin-top: 2px;
          margin-left: 60px;">
              <el-image :src="require('@/assets/icons/eventt.png')" fit="contain">
              </el-image>
            </div>
            </el-col>
            <el-col :span="14">
            <div  style="font-size: 50px;font-weight: 800; text-align:center;color: white; ">{{ eventCount }}</div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col sm="24" :lg="8">
        <el-card class="all-card" title="处理洗钱总金额" style="background:linear-gradient(to right, #8DEFB7,#19B98F)">
          <template #header>
            <div
              class="event-header"
            >
              <p style="text-align: center; margin-top: -1px;letter-spacing:2px">处理洗钱总金额（美元）</p>
            </div>
          </template>
          <el-row>
            <el-col :span="10">
              <div style="width:68px;height:68px;line-height: 50px;font-size: 50px;margin-top: 2px;
          margin-left: 45px;">
              <el-image :src="require('@/assets/icons/money.png')" fit="contain">
              </el-image>
            </div>
            </el-col>
            <el-col :span="14">
            <div  style="font-size: 50px;font-weight: 800; text-align:center;color: white; ">{{ moneyCount }}</div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-card>
    </el-row>

    <el-row style="margin-top: 20px;">
      <el-col sm="24" :lg="12">
        <el-card class="user-list" title="今日洗钱用户列表">
          <template #header>
            <div
              class="userlist-header"
              style="font-size: 15px; font-weight: bold"
            >
              <p
                style="
                  text-align: left;
                  margin-top: -2px;
                  display: inline-block;
                  border-left: 5px solid rgb(53, 84, 187);
                  padding-left: 10px;
                "
              >
                今日洗钱用户列表
              </p>
            </div>
          </template>
          <template>
            <div>
               <el-table :data="userData" style="width: 100%; height: 300px; overflow-y: scroll" header-row-class-name="user-table-header">
                <el-table-column prop="userId" label="用户Id"></el-table-column>
              <el-table-column prop="money1" label="洗钱金额"></el-table-column>
           </el-table>
            </div>
          </template>
        </el-card>
      </el-col>
      <el-col sm="24" :lg="12">
        <el-card class="plat-list" title="今日洗钱平台列表">
          <template #header>
            <div
              class="userlist-header"
              style="font-size: 15px; font-weight: bold"
            >
              <p
                style="
                  text-align: left;
                  margin-top: -2px;
                  display: inline-block;
                  border-left: 5px solid rgb(53, 84, 187);
                  padding-left: 10px;
                "
              >
                今日洗钱平台列表
              </p>
            </div>
          </template>
          <template>
            <div>
              <el-table :data="platData" style="width: 100%; height: 300px; overflow-y: scroll" header-row-class-name="plat-table-header">
                <el-table-column prop="platId" label="平台名称"></el-table-column>
              <el-table-column prop="money2" label="洗钱金额"></el-table-column>
           </el-table>
           </div>
          </template>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from "axios";
import "element-ui/lib/theme-chalk/index.css";
import { Table, TableColumn } from "element-ui";
export default {
  components: {
    "el-table": Table,
    "el-table-column": TableColumn,
  },
  data() {
    return {
      userData: [],
      platData: [],
      userCount:14,
      eventCount: 36,
      moneyCount: "8400.0",
      eventData: [
        {
          label: '待接收',
          prop: 'djs',
          src: require('@/assets/icons/daijieshou.png'),
          color: ['#4DB3F6', '#748CF3']
        },
        {
          label: '待分派',
          prop: 'dfp',
          src: require('@/assets/icons/daifenpei.png'),
          color: ['#FCC591', '#FF7D7A']
        },
        {
          label: '已处置',
          prop: 'dcz',
          src: require('@/assets/icons/yichuzhi.png'),
          color: ['#8DEFB7', '#19B98F']
        }
      ],
      event: {
        djs: 30,
        dfp: 45,
        dcz: 14
      },
    };
  },

  mounted() {
    axios
      .get("/api/user_count")
      .then((response) => {
        this.userCount = response.data.count;
      })
      .catch((error) => {
        console.log(error);
      });

    axios
      .get("/api/event_count")
      .then((response) => {
        this.eventCount = response.data.count;
      })
      .catch((error) => {
        console.log(error);
      });

    axios
      .get("/api/money_count")
      .then((response) => {
        this.moneyCount = response.data.count;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  created() {
    // 异步请求数据
    this.$axios
      .get("/api/getData")
      .then((response) => {
        this.userData = response.userdata;
        this.platData = response.platdata;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  methods: {
    userIdFormatter(row) {
      return `<span style="color: rgb(23, 90, 167)">${row.userId}</span>`;
    },
    platIdFormatter(row) {
      return `<span style="color: rgb(23, 90, 167)">${row.userId}</span>`;
    },
  },
};
</script>

<style scoped lang="scss">
.home {
  .all-card{
    width: 400px;
    height:180px;
    margin-left: 20px;
    border-radius: 30px;
  }
  .user-header {
    height: 25px;
    display: felxbox;
    justify-content: space-between;
    align-items: center;
  }
  .user-header p {
    font-family: Verdana, Geneva, Tahoma, sans-serif;
  }

  .user-card {
    width: 400px;
    left: 2px;
    background-color: rgb(85, 126, 240);
  }
  .event-card {
    width: 400px;
    background-color: rgb(245, 188, 83);
  }
  .event-header {
    text-align:center;
    font-size: 20px; font-weight: bold;color: white;
    height: 25px;
    align-items: center;
  }
  .money-header {
    height: 25px;
    display: felxbox;
    justify-content: space-between;
    align-items: center;
  }

  .money-card {
    width: 400px;
    background-color: rgb(69, 192, 86);
  }
  .el-table__body-wrapper {
  overflow: hidden;
  white-space: nowrap;
  animation: scroll 30s linear infinite;
}
  .el-table__body-wrapper:hover {
  animation-play-state: paused;
}
  @keyframes scroll {
  0% {
    transform: translateX(0);
  }
  100% {
    transform: translateX(-100%);
  }
}
::-webkit-scrollbar {
  display: none;
}

  .user-list {
    width: 700px;
  }
  .userlist-header {
    height: 20px;
  }
  .platlist-header {
    height: 20px;
  }
  .plat-list {
    width: 700px;
  }
}
</style>