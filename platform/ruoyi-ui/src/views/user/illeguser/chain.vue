<template>
  <div class="home_container">
    <div class="center-content">
      <el-card class="table_content">
        <div class="main">
          <template>
            <div
              class="echarts"
              style="width: 100%; height: 600px; margin: 0 auto"
              id="echarts-pie-chart"
            ></div>
          </template>
        </div>
      </el-card>
    </div>
  </div>
</template>

<script>
import {
  BuyerListBySeller,
  ActivityListBySeller,
  SellerListByBuyer,
} from "@/api/nft/activity";
import { getillUser } from "@/api/nft/user";
const echarts = require("echarts");
export default {
  components: {},
  data() {
    return {
      data1: [],
      links1: [],
     
    };
  },
  mounted() {
    this.initMap();
  },
  created() {},
  methods: {
    initMap() {
      var map = new Map();
      var pieChart = echarts.init(document.getElementById("echarts-pie-chart"));
      pieChart.showLoading({
        text: "数据加载中......",
        color: "#B2312D",
        textColor: "#B2312D",
        fontSize: 20,
        showSpinner: true,
        spinnerRadius: 20,
        lineWidth: 5,
        zlevel: 0,
      });

      getillUser(this.$route.params.userId).then((res) => {
        var item = res.data;
        map.set(item.userId, item.userName);
          this.data1.push({
            name: item.userName,
            des: '',
            category: item.isFraud
          });
        
        SellerListByBuyer({ buyer: this.$route.params.userId }).then(
          (response) => {
            response.rows.forEach((item) => {
              if (this.data1.indexOf(item) == -1) {
                map.set(item.userId, item.userName);
                this.data1.push({
                        name: item.userName,
                        des: '',
                        category: item.isFraud
                      });
              }
            });
            BuyerListBySeller({ seller: this.$route.params.userId }).then(
              (response1) => {
                response1.rows.forEach((item) => {
                  map.set(item.userId, item.userName);
                  if (this.data1.indexOf(item) == -1) {
                    this.data1.push({
                        name: item.userName,
                        des: '',
                        category: item.isFraud
                      });
                  }
                });
                ActivityListBySeller({
                  userId: this.$route.params.userId,
                }).then((response) => {
                  response.rows.forEach((item) => {
                    var linkItem = { source: map.get(item.seller),
                      target: map.get(item.buyer),
                      name: item.held,
                      des: "交易金额："+item.priceUsd + "美元 / " + item.priceEth +"以太币"+"<br/>交易时间："+item.held,}
                      if(linkItem.source == this.data1[0].name){
                        linkItem.lineStyle = {
                        width: 5,
                        curveness: 0.3,
                        color: "red"
                      }
                    }
                    this.links1.push(linkItem);
                  });
                  var data11 = [];
                  var da = [];
                  this.data1.forEach((it) => {
                    if (data11.indexOf(it.name) == -1) {
                      data11.push(it.name);
                      if (it.category == 0) {
                      it.symbolSize = 70
                      it.itemStyle = {
                        normal:{color:'#C1EBDD'}
                      }
                    } else{
                      it.symbolSize = 85
                      it.itemStyle = {
                        normal:{color:'#FF9393'}
                      }
                    }
                      da.push(it);
                    }
                  });
                  const data = da;
                  const links = this.links1;
                  var categories = [
                    { name: "正常"},
                    { name: "有洗钱嫌疑"},
                  ];

                  var pieoption = {
                    title: {
                      text: "用户资金流动链条图",
                    },
                    tooltip: {
                      formatter: function (x) {
                        return x.data.des;
                      },
                    },
                    legend: [
                      {
                        // selectedMode: 'single',
                        data: categories.map(function (a) {
                          return a.name;
                        }),
                      },
                    ],
                      color:["#C1EBDD","#FF9393"],
                    series: [
                      {
                        type: "graph",
                        layout: "force", //图的布局，类型为力导图
                        symbolSize: 60, // 调整节点的大小
                        roam: true, // 是否开启鼠标缩放和平移漫游。默认不开启。如果只想要开启缩放或者平移,可以设置成 'scale' 或者 'move'。设置成 true 为都开启
                        edgeSymbol: ["circle", "arrow"],
                        focusNodeAdjacency: true,
                        edgeSymbolSize: [2, 10],
                        force: {
                          //力引导图基本配置
                          layoutAnimation: true,
                          // xAxisIndex : 0, //x轴坐标 有多种坐标系轴坐标选项
                          // yAxisIndex : 0, //y轴坐标
                          gravity: 0.02, //节点受到的向中心的引力因子。该值越大节点越往中心点靠拢。
                          edgeLength: 180, //边的两个节点之间的距离，这个距离也会受 repulsion。[10, 50] 。值越小则长度越长
                          repulsion: 2500, //节点之间的斥力因子。支持数组表达斥力范围，值越大斥力越大。
                          // repulsion: 3000, //节点之间的斥力因子。支持数组表达斥力范围，值越大斥力越大。
                          // edgeLength: 80 //边的两个节点之间的距离，这个距离也会受 repulsion。[10, 50] 。值越小则长度越长
                        },
                        draggable: true,
                        lineStyle: {
                          normal: {
                            curveness: 0.3,
                            width: 2,
                            color: "#4b565b",
                          },
                        },
                        label: {
                          normal: {
                            show: true,
                            textStyle: {
                                fontSize:20
                            },
                          },
                        },
                        edgeLabel: {
                          normal: {
                            show: true,
                            formatter: function (x) {
                              return x.data.name;
                            },
                          },
                        },

                        data: data,
                        links: links,
                        categories: categories,
                      },
                    ],
                  };
                  pieChart.setOption(pieoption);
                  pieChart.hideLoading();
                });
              }
            );
          }
        );
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.home_container {
  padding: 5px 5px;
  line-height: 1;
  height: 100%;
}
.center-content {
  margin-top: 20px;
  display: flex;
  width: 100%;
  background-color: transparent !important;
}
.table_content {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  margin: 5px 10px;
}
</style>
