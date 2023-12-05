<script setup>
import {onMounted, reactive, ref} from "vue";
import {stuApi} from "@/api/stu";
import StuForm from "./StuForm.vue";
import {ElMessage, ElMessageBox} from "element-plus";
import SearchForm from "./SearchForm.vue";
import {Search} from "@element-plus/icons-vue";

const formType = {
  INSERT: "insert",
  UPDATE: "update",
};

// 表单弹出框
const dialogForm = ref(false);
// 弹出框标题（标题+‘学生信息’）
const dialogFormTitle = ref("");
// 弹出框类型（增加、修改）
const dialogFormType = ref("");
// 传入弹出框表单的数据
const stuFormData = ref({});

// stuForm组件
const stuFormRef = ref(null);
// searchForm组件
const searchFormRef = ref(null);

// 本页数据
const pageData = reactive({
  // 列表分页数据
  tableData: {
    pageNum: 1, // 当前页
    pageSize: 10, // 每页的数量
    // size: 10, // 当前页的数量
    // startRow: 1, // 当前页面第一个元素在数据库中的行号
    // endRow: 10, // 当前页面最后一个元素在数据库中的行号
    total: 0, // 总记录数
    // pages: 0, // 总页数
    list: [], // 当前页结果集
    // prePage: 0, // 上一页页码
    // nextPage: 2, // 下一页页码
    // isFirstPage: true, // 是否为第一页
    // isLastPage: false, // 是否为最后一页
    // hasPreviousPage: false, // 是否有上一页
    // hasNextPage: true, // 是否有下一页
    // navigatePages: 10, // 导航页码数
    // navigatepageNums: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], // 所有导航页号
    // navigateFirstPage: 1, // 导航条上的第一页
    // navigateLastPage: 10, // 导航条上的最后一页
  },
});

// 获取列表
const getAllList = async (num) => {
  const res = await stuApi.getAll({pageNum: num});
  console.log(res.data);
  pageData.tableData = res.data;
  // 解决刷新就回到第一页的问题
  // localStorage 存储的数据会一直存在，直到清除浏览器缓存或手动移除它
  // sessionStorage 仅在当前会话期间有效，关闭浏览器标签或窗口数据会被清除（和服务器的会话稍有不同）
  sessionStorage.setItem("currentPage", num ? num : 1); // 存放页码到本地存储（sessionStorage只能存储字符串）
};

// 初始化获取当前页数据
const init = () => {
  const currentPage = sessionStorage.getItem("currentPage"); // 从本地存储拿到页码
  getAllList(currentPage);
};

// 挂载初始化
onMounted(() => {
  init();
});

// 弹出新增学生信息框
const handleInsert = () => {
  stuFormData.value = {};
  // 设置属性为修改框
  stuFormData.value.type = formType.INSERT;
  dialogFormTitle.value = "新增学生信息";
  dialogForm.value = true;
};

// 弹出修改框
const handleEdit = (index) => {
  // 复制原始对象的副本，以防止修改原始对象
  stuFormData.value = {...pageData.tableData.list[index]};
  // 将schoolClass.id的值赋给classId属性
  stuFormData.value.classId = stuFormData.value.schoolClass.id;
  // 删除schoolClass属性
  delete stuFormData.value.schoolClass;
  // 设置属性为修改框
  stuFormData.value.type = formType.UPDATE;
  dialogFormTitle.value = `修改学生信息【${stuFormData.value.id}】`;
  dialogForm.value = true;
};

// 新增学生信息
const stuInsert = async (stuFormData) => {
  console.log(stuFormData);
  const res = await stuApi.insert(stuFormData);
  if (res.code === 200) {
    ElMessage({
      type: "success",
      message: `添加成功【${stuFormData.id} ${stuFormData.studentName}】`,
    });
    init();
  }
};

// 修改学生信息
const stuEdit = async (stuFormData) => {
  console.log(stuFormData);
  const res = await stuApi.update(stuFormData);
  if (res.code === 200) {
    ElMessage({
      type: "success",
      message: `修改成功【${stuFormData.id} ${stuFormData.studentName}】`,
    });
    init();
  }
};

// 点击提交
const formSubmit = () => {
  const stuFormData = stuFormRef.value.stuFormData;
  console.log(stuFormData.type);
  switch (stuFormData.type) {
    case formType.UPDATE:
      delete stuFormData.type;
      stuEdit(stuFormData);
      break;
    case formType.INSERT:
      delete stuFormData.type;
      stuInsert(stuFormData);
      break;
    default:
      ElMessage({type: "error", message: "操作失败，弹出框类型错误"});
      break;
  }
  dialogForm.value = false;
};

// 点击删除
const handleDelete = (row) => {
  ElMessageBox.confirm(
      `将会删除学生【${row.id} ${row.studentName}】，是否确认删除？`, // 提示信息
      "Warning", // 标题
      {
        confirmButtonText: "删除",
        cancelButtonText: "取消",
        type: "warning",
      }
  )
      .then(async () => {
        const res = await stuApi.delete(row.id);
        if (res.code === 200) {
          ElMessage({
            type: "success",
            message: `删除成功【${row.id} ${row.studentName}】`,
          });
          init();
        } else {
          ElMessage({type: "error", message: "删除失败"});
        }
      })
      .catch(() => {
        // ElMessage({ type: "info", message: "取消删除" });
      });
};

// 点击搜索
const handleSearch = async () => {
  const option = searchFormRef.value.searchForm;
  option.pageNum = 1;
  const res = await stuApi.getAll(option);
  console.log(res.data);
  pageData.tableData = res.data;
  // 回到第一页
  sessionStorage.setItem("currentPage", 1);
};
</script>

<template>
  <div class="student-container">
    <div class="search">
      <SearchForm ref="searchFormRef" @handleSearch="handleSearch"></SearchForm>
      <el-button type="primary" :icon="Search" @click="handleSearch"/>
    </div>
    <div class="insert">
      <el-button type="primary" @click="handleInsert">添加</el-button>
    </div>
    <el-table :data="pageData.tableData.list" border style="width: 100%">
      <el-table-column fixed prop="id" label="学号"/>
      <el-table-column prop="studentName" label="姓名"/>
      <el-table-column prop="grade" label="年级"/>
      <el-table-column prop="schoolClass.className" label="班级"/>
      <el-table-column prop="aGrade" label="A成绩"/>
      <el-table-column prop="bGrade" label="B成绩"/>
      <el-table-column prop="cGrade" label="C成绩"/>
      <el-table-column prop="zGrade" label="Z成绩"/>
      <el-table-column fixed="right" label="修改" width="90">
        <template #default="scope">
          <el-button type="primary" @click="handleEdit(scope.$index)">
            修改
          </el-button>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="删除" width="90">
        <template #default="scope">
          <el-button type="danger" @click="handleDelete(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination
          background
          layout="total, prev, pager, next, jumper"
          :total="pageData.tableData.total"
          :page-size="pageData.tableData.pageSize"
          :current-page="pageData.tableData.pageNum"
          @current-change="getAllList"
      />
    </div>
    <!-- 弹框 -->
    <el-dialog v-model="dialogForm" :title="dialogFormTitle">
      <StuForm ref="stuFormRef" :form-data="stuFormData"></StuForm>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogForm = false">取消</el-button>
          <el-button type="primary" @click="formSubmit">提交</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<style lang="scss" scoped>
@mixin goDirection($direction) {
  display: flex;
  justify-content: $direction; // 主轴的对齐方式
  align-items: flex-start; // 交叉轴方向上的对齐方式
}

.student-container {
  .search {
    @include goDirection(left);
  }

  .insert {
    margin: 15px;
    @include goDirection(right);
  }

  .pagination {
    margin: 15px 0;
    @include goDirection(right);
  }
}
</style>
