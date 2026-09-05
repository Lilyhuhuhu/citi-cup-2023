<template>
  <div class="app-container">
    <!-- <el-row :gutter="20">
        <el-col :span="4" :xs="24">
          <div class="head-container">
            <el-input
              v-model="deptName"
              placeholder="请输入平台名称"
              clearable
              size="small"
              prefix-icon="el-icon-search"
              style="margin-bottom: 20px"
            />
          </div>
          <div class="head-container">
            <el-tree
              :data="gameList"
              :props="defaultProps"
              :expand-on-click-node="false"
              :filter-node-method="filterNode"
              ref="tree"
              node-key="id"
              default-expand-all
              highlight-current
              @node-click="handleNodeClick"
            />
          </div>
        </el-col>
        <el-col :span="20" :xs="24"> -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户姓名" prop="userName">
        <el-input v-model="queryParams.userName" placeholder="请输入用户姓名" clearable style="width: 240px"
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="手机号码" prop="phonenumber">
              <el-input
                v-model="queryParams.phonenumber"
                placeholder="请输入手机号码"
                clearable
                style="width: 240px"
                @keyup.enter.native="handleQuery"
              />
            </el-form-item> -->
      <el-form-item label="是否洗钱" prop="isFraud">
        <el-select v-model="queryParams.isFraud" placeholder="请选择" clearable style="width: 240px">
          <el-option v-for="item in moneyOptions" :key="item.value" :label="item.label" :value="item.value" />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="违规时间">
              <el-date-picker
                v-model="dateRange"
                style="width: 240px"
                value-format="yyyy-MM-dd"
                type="daterange"
                range-separator="-"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              ></el-date-picker>
            </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
              <el-button
                type="primary"
                plain
                icon="el-icon-plus"
                size="mini"
                @click="handleAdd"
                v-hasPermi="['system:user:add']"
              >新增</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="success"
                plain
                icon="el-icon-edit"
                size="mini"
                :disabled="single"
                @click="handleUpdate"
                v-hasPermi="['system:user:edit']"
              >修改</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="danger"
                plain
                icon="el-icon-delete"
                size="mini"
                :disabled="multiple"
                @click="handleDelete"
                v-hasPermi="['system:user:remove']"
              >删除</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="info"
                plain
                icon="el-icon-upload2"
                size="mini"
                @click="handleImport"
                v-hasPermi="['system:user:import']"
              >导入</el-button>
            </el-col>
            <el-col :span="1.5">
              <el-button
                type="warning"
                plain
                icon="el-icon-download"
                size="mini"
                @click="handleExport"
                v-hasPermi="['system:user:export']"
              >导出</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
          </el-row> -->

    <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
      <el-table-column label="序号" align="center" key="userId" prop="userId" v-if="columns[0].visible" width="80px" />
      <el-table-column label="用户姓名" align="center" key="userName" prop="userName" :show-overflow-tooltip="true"
        v-if="columns[1].visible" />
      <!-- <el-table-column label="用户昵称" align="center" key="nickName" prop="nickName" :show-overflow-tooltip="true" v-if="columns[2].visible"/>
            <el-table-column label="游戏名称" align="center" key="deptName" prop="dept.deptName"  :show-overflow-tooltip="true" v-if="columns[3].visible"/>
            <el-table-column label="手机号码" align="center" key="phonenumber" prop="phonenumber" width="120" v-if="columns[4].visible"/> -->
      <el-table-column label="状态" align="center" key="isFraud" v-if="columns[2].visible" prop="isFraud">
        <template slot-scope="scope">
          <el-tag :type="getState(scope.row).type">{{
            getState(scope.row).text
          }}</el-tag>
        </template>
      </el-table-column>
      <!-- <el-table-column label="创建时间" align="center" prop="createTime" v-if="columns[6].visible" width="160">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.createTime) }}</span>
              </template>
            </el-table-column> -->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!-- <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-edit"
                  @click="handleUpdate(scope.row)"
                >修改</el-button>
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                >删除</el-button> -->
          <!-- <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)"> -->
          <el-button size="mini" type="text" icon="el-icon-d-arrow-right"
            @click="dealDetail(scope.row)">查看资金流动</el-button>
          <!-- <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="handleResetPwd" icon="el-icon-key"
                      v-hasPermi="['system:user:resetPwd']">重置密码</el-dropdown-item>
                    <el-dropdown-item command="handleAuthRole" icon="el-icon-circle-check"
                      v-hasPermi="['system:user:edit']">分配角色</el-dropdown-item>
                  </el-dropdown-menu> -->
          <!-- </el-dropdown> -->
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />
    <!-- </el-col>
      </el-row> -->


  </div>
</template>
  
<script>
import { getToken } from "@/utils/auth";
import { listillUser } from "@/api/nft/user"
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "User",
  dicts: ['sys_normal_disable', 'sys_user_sex'],
  components: { Treeselect },
  data () {
    return {
      // 遮罩层
      loading: true,
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
      // 部门树选项
      deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 部门名称
      deptName: undefined,
      // 默认密码
      initPassword: undefined,
      // 日期范围
      dateRange: [],
      // 岗位选项
      postOptions: [],
      // 角色选项
      roleOptions: [],
      // 表单参数
      form: {},
      defaultProps: {
        label: "name"
      },
      gameList: [{
        name: "NFT1",
        id: "123456"
      },
      {
        name: "NFT2",
        id: "123451"
      },
      ],
      moneyOptions: [{
        value: 1,
        label: "有洗钱嫌疑"
      }, {
        value: 0,
        label: "正常"
      }],
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/user/importData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        isFraud: undefined
      },
      // 列信息
      columns: [
        { key: 0, label: `身份证号`, visible: true },
        { key: 1, label: `用户姓名`, visible: true },
        { key: 2, label: `用户昵称`, visible: true },
        { key: 3, label: `游戏名称`, visible: true },
        { key: 4, label: `手机号码`, visible: true },
        { key: 5, label: `洗钱金额`, visible: true },
        { key: 6, label: `创建时间`, visible: true }
      ],
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "用户密码不能为空", trigger: "blur" },
          { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' }
        ],
        email: [
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        phonenumber: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ]
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName (val) {
      this.$refs.tree.filter(val);
    }
  },
  created () {
    this.getList();

  },
  methods: {
    getState (row) {
      let type = ''
      let text = ''
      switch (row.isFraud) {
        case 1:
          text = '有洗钱嫌疑'
          type = 'danger'
          break
        default:
          type = "'success'"
          text = '正常'
      }

      return {
        type,
        text
      }
    },
    dealDetail (row) {
      console.log(row.userId)
      this.$router.push("/user/chain/id/" + row.userId);
    },
    /** 查询用户列表 */
    getList () {
      this.loading = true;
      listillUser(this.addDateRange(this.queryParams)).then(response => {
        this.userList = response.rows;
        this.total = response.total;
        this.loading = false;
      }
      );
    },

    // 表单重置
    reset () {
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
        roleIds: []
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.dateRange = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.ids = selection.map(item => item.userId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
  }
};
</script>
  