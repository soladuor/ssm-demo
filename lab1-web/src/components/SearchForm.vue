<script setup>
import { reactive } from "vue";

const searchForm = reactive({
  id: "",
  studentName: "",
  grade: "",
  classId: "",
  aGrade: "",
  bGrade: "",
  cGrade: "",
  zGrade: "",
});

const emit = defineEmits(["handleSearch"]);

const search = () => {
  emit("handleSearch");
};
console.log(emit);
defineExpose({
  searchForm,
});
</script>

<template>
  <div class="search-form-container">
    <el-form
      :inline="true"
      label-width="60px"
      :model="searchForm"
      style="max-width: 900px"
    >
      <el-form-item label="学号">
        <el-input v-model="searchForm.id" @keyup.enter="emit('handleSearch')" />
      </el-form-item>
      <el-form-item label="姓名">
        <el-input
          v-model="searchForm.studentName"
          @keyup.enter="emit('handleSearch')"
        />
      </el-form-item>
      <el-form-item label="年级">
        <el-input
          v-model="searchForm.grade"
          @keyup.enter="emit('handleSearch')"
        />
      </el-form-item>
      <el-form-item label="班级号">
        <el-input
          v-model="searchForm.classId"
          @keyup.enter="emit('handleSearch')"
        />
      </el-form-item>
      <el-form-item label="A成绩">
        <el-input
          v-model="searchForm.aGrade"
          @keyup.enter="emit('handleSearch')"
        />
      </el-form-item>
      <el-form-item label="B成绩">
        <el-input
          v-model="searchForm.bGrade"
          @keyup.enter="emit('handleSearch')"
        />
      </el-form-item>
      <el-form-item label="C成绩">
        <el-input
          v-model="searchForm.cGrade"
          @keyup.enter="emit('handleSearch')"
        />
      </el-form-item>
      <el-form-item label="Z成绩">
        <el-input
          v-model="searchForm.zGrade"
          @keyup.enter="emit('handleSearch')"
        />
      </el-form-item>
    </el-form>
  </div>
</template>

<style lang="scss" scoped></style>
