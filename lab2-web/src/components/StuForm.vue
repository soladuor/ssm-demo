<script setup>
import { ref, watch } from "vue";

const props = defineProps({
  formData: {
    type: Object,
    required: true,
    default: () => ({
      id: "",
      studentName: "",
      grade: "",
      classId: "",
      logoUrl: "",
      aGrade: "",
      bGrade: "",
      cGrade: "",
      zGrade: "",
      type: "",
    }),
  },
});

const stuFormData = ref({ ...props.formData });

watch(
  () => props.formData,
  (newVal) => {
    stuFormData.value = { ...newVal };
  },
  { deep: true }
);

// 由于setup默认封闭，所以得定义暴露的数据或函数
defineExpose({
  stuFormData,
});
</script>

<template>
  <div class="stu-form-container">
    <div v-if="formData.type === 'insert'">
      <el-form :inline="true" label-width="100px" :model="formData">
        <el-form-item label="学号">
          <el-input v-model="formData.id" />
        </el-form-item>
      </el-form>
    </div>
    <el-form :inline="true" label-width="100px" :model="formData">
      <el-form-item label="姓名">
        <el-input v-model="formData.studentName" />
      </el-form-item>
      <el-form-item label="年级">
        <el-input v-model="formData.grade" />
      </el-form-item>
      <el-form-item label="班级号">
        <el-input v-model="formData.classId" />
      </el-form-item>
      <el-form-item label="头像url">
        <el-input v-model="formData.logoUrl" />
      </el-form-item>
      <el-form-item label="A成绩">
        <el-input v-model="formData.aGrade" />
      </el-form-item>
      <el-form-item label="B成绩">
        <el-input v-model="formData.bGrade" />
      </el-form-item>
      <el-form-item label="C成绩">
        <el-input v-model="formData.cGrade" />
      </el-form-item>
      <el-form-item label="Z成绩">
        <el-input v-model="formData.zGrade" />
      </el-form-item>
    </el-form>
  </div>
</template>

<style lang="scss" scoped>
.stu-form-container {
  width: 100%;
}
</style>
