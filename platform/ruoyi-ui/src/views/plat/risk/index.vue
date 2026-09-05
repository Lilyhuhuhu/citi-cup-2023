<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="平台名称" prop="platName">
        <el-input
          v-model="queryParams.platName"
          placeholder="请输入平台名称"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="tableData"
      :row-key="
        (row) => {
          return row.id;
        }
      "
      :expand-row-keys="expands"
    >
      <el-table-column type="expand" width="1">
        <template slot-scope="scope">
          <div id="radar-chart" style="height: 250px;"></div>
          <div
            class="dialog-lable"
            style="font-size: 13px; text-align: center; color: grey"
          >
            <span style="margin-right: 15px">1-Optional</span>
            <span>2-Mandatory</span>
            <span style="margin-left: 15px; margin-right: 15px">3-Partial</span>
            <span style="margin-right: 15px">4-Not applicable</span>
            <span>5-Exists</span>
            <span style="margin-left: 15px">6-Does not exist</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column
        prop="id"
        label="平台ID"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="name"
        label="平台名称"
        align="center"
      ></el-table-column>
      <el-table-column label="网址" align="center">
        <template slot-scope="scope">
          <a :href="scope.row.url" target="_blank">
            <span style="color: rgb(85, 126, 240)">{{ scope.row.url }}</span>
          </a>
        </template>
      </el-table-column>
      <el-table-column label="评价指标" align="center">
        <template slot-scope="scope">
          <el-button size="mini" type="text" @click="showRadar(scope.row)">
            查看</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      class="dialog-chart"
      :visible.sync="dialogVisible"
      title="评价指标雷达图"
      :draggable="true"
    >
      <div
        class="dialog-lable"
        style="font-size: 13px; text-align: center; color: grey"
      >
        <span style="margin-right: 15px">1-Optional</span>
        <span>2-Mandatory</span>
        <span style="margin-left: 15px; margin-right: 15px">3-Partial</span>
        <span style="margin-right: 15px">4-Not applicable</span>
        <span>5-Exists</span>
        <span style="margin-left: 15px">6-Does not exist</span>
      </div>
    </el-dialog>
  </div>
</template>
  
  <script>
import {} from "@/api/system/user";
import { getToken } from "@/utils/auth";
import { listillUser } from "@/api/nft/user";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
const echarts = require("echarts");

export default {
  name: "plat",

  data() {
    return {
      tableData: [
        {
          id: "1",
          name: "OpenSea",
          url: "https://opensea.io",
          indicators: {
            身份验证: 6,
            双重认证: 4,
            令牌合约的可验证性: 6,
            修改令牌元数据: 3,
            最小权限原则: 5,
            无效的缓存: 5,
            卖方和NFT藏品验证: 1,
            缺乏透明度: 6,
            公平竞标: 6,
          }, 
        },
        {
          id: "2",
          name: "Axie",
          url: "https://axieinfinity.com",
          indicators: {
            身份验证: 6,
            双重认证: 6,
            令牌合约的可验证性: 4,
            修改令牌元数据: 5,
            最小权限原则: 5,
            无效的缓存: 4,
            卖方和NFT藏品验证: 4,
            缺乏透明度: 6,
            公平竞标: 5,
          },
        },
        {
          id: "3",
          name: "CryptoPunks",
          url: "https://www.larvalabs.com/cryptopunk",
          indicators: {
            身份验证: 6,
            双重认证: 4,
            令牌合约的可验证性: 4,
            修改令牌元数据: 6,
            最小权限原则: 5,
            无效的缓存: 4,
            卖方和NFT藏品验证: 4,
            缺乏透明度: 6,
            公平竞标: 5,
          },
        },
        {
          id: "4",
          name: "rarible",
          url: "https://rarible.com/",
          indicators: {
            身份验证: 6,
            双重认证: 4,
            令牌合约的可验证性: 6,
            修改令牌元数据: 6,
            最小权限原则: 5,
            无效的缓存: 5,
            卖方和NFT藏品验证: 1,
            缺乏透明度: 6,
            公平竞标: 6,
          },
        },
        {
          id: "5",
          name: "Super rare",
          url: "https://superrare.com/",
          indicators: {
            身份验证: 6,
            双重认证: 4,
            令牌合约的可验证性: 4,
            修改令牌元数据: 3,
            最小权限原则: 3,
            无效的缓存: 4,
            卖方和NFT藏品验证: 2,
            缺乏透明度: 6,
            公平竞标: 5,
          },
        },
        {
          id: "6",
          name: "Sorare",
          url: "https://sorare.com",
          indicators: {
            身份验证: 6,
            双重认证: 1,
            令牌合约的可验证性: 4,
            修改令牌元数据: 3,
            最小权限原则: 5,
            无效的缓存: 4,
            卖方和NFT藏品验证: 4,
            缺乏透明度: 6,
            公平竞标: 6,
          },
        },
        {
          id: "7",
          name: "Foundation",
          url: "https://foundation.app/@paveci/wanderlust-ai/1",
          indicators: {
            身份验证: 6,
            双重认证: 4,
            令牌合约的可验证性: 4,
            修改令牌元数据: 6,
            最小权限原则: 6,
            无效的缓存: 4,
            卖方和NFT藏品验证: 2,
            缺乏透明度: 6,
            公平竞标: 5,
          },
        },
        {
          id: "8",
          name: "Nifty Gateway",
          url: "https://www.niftygateway.com/",
          indicators: {
            身份验证: 6,
            双重认证: 5,
            令牌合约的可验证性: 6,
            修改令牌元数据: 6,
            最小权限原则: 6,
            无效的缓存: 4,
            卖方和NFT藏品验证: 2,
            缺乏透明度: 5,
            公平竞标: 6,
          },
        },
      ],
      dialogVisible: false,

      // 遮罩层
      // loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户表格数据
      userList: null,
      // 弹出层标题
      title: "",

      // 角色选项
      roleOptions: [],
      // 表单参数
      form: {},
      defaultProps: {
        label: "name",
      },
      expands: [],

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        platName: undefined,
      },
    };
  },

  created() {
    this.getList();
    this.getDeptTree();
    this.getConfigKey("sys.user.initPassword").then((response) => {
      this.initPassword = response.msg;
    });
  },

  methods: {
   
    showRadar(row) {
      const indicatorData = Object.keys(row.indicators);
      const seriesData = [
        {
          type: "radar",
          data: [
            {
              name: row.name,
              value: Object.values(row.indicators),
            },
          ],
        },
      ];
      const option = {
        tooltip: {},
        radar: {
          indicator: indicatorData.map((name) => ({ name })),
        },
        series: seriesData,
      };
      this.expands = [];
      this.expands.push(row.id);
      // this.dialogVisible = true;
      this.$nextTick(() => {
        const chart = echarts.init(document.getElementById("radar-chart"));
        chart.setOption(option);
      });
    },

    dealDetail(row) {
      this.$router.push("/user/chain/id/" + row.userId);
    },
    /** 查询用户列表 */
    // getList() {
    //   this.loading = true;
    //   listillUser(this.addDateRange(this.queryParams)).then((response) => {
    //     this.userList = response.rows;
    //     this.total = response.total;
    //     this.loading = false;
    //   });
    // },

    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },

    // 表单重置
    reset() {
      this.form = {
        userId: undefined,
        deptId: undefined,
        userName: undefined,
        nickName: undefined,
        password: undefined,
        phonenumber: undefined,
        email: undefined,
        sex: undefined,
        status: "0",
        remark: undefined,
        postIds: [],
        roleIds: [],
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      // 发起查询请求
      this.loading = true;
      setTimeout(() => {
        // 模拟查询结果
        const data = this.tableData.filter(
          (item) => item.name.indexOf(this.queryParams.platName) > -1
        );
        this.tableData = data;
        this.loading = false;
      }, 1000);
    },
    resetQuery() {
      // 重置查询条件
      this.$refs.queryForm.resetFields();
      this.queryParams.platName = "";
    },
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.userId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
  },
};
</script> 

<style lang="scss" scoped>
.dialog-label span {
  display: inline-block; /* 设置 span 元素为块级元素 */
}
.dialog-chart {
  z-index: 2000; /* 设置弹窗的层级 */
  position: fixed !important;
  width: 800px;
  top: 152px;
  left: 670px;
}

.dialog-chart {
  overflow: hidden; /* 防止弹窗超出屏幕 */
}

.v-modal {
  background-color: transparent !important; /* 将遮罩层的颜色设置为透明 */
}

::v-deep .el-table__expand-icon {
  display: none !important;
}
</style>