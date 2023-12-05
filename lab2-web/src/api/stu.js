import axios from "../utils/http";
import httpCode from "../utils/httpCode";
import { ElMessage, ElLoading } from "element-plus";

const base = "/student";

/* 可以传的参数
{
  id
  studentName,
  grade,
  classId, // schoolClass: {id, className},
  logoUrl,
  aGrade,
  bGrade,
  cGrade,
  zGrade,
  pageNum
}
*/

const sendRequest = async (url, method, params) => {
  // 遮罩层
  ElLoading.service(); // 服务的方式调用的全屏 Loading 是单例的

  const options = {
    url: `${base}${url}`,
    method: method,
    headers: { "content-type": "application/x-www-form-urlencoded" },
    data: params,
  };
  // 转换method为大写，便于处理
  method = method.toUpperCase();
  if (method === "GET" || method === "DELETE") {
    options.params = options.data;
    delete options.data;
  }
  try {
    const response = await axios(options);

    if (Number(response.data.code) !== httpCode.SUCCESS) {
      return false;
    }

    return response.data;
  } catch (error) {
    console.error("Error occurred while sending request:", error);
    ElMessage.error({
      message: "发送请求时出错：" + error,
    });
    throw error;
  } finally {
    // 遮罩层
    ElLoading.service().close(); // 服务的方式调用的全屏 Loading 是单例的
  }
};

export const stuApi = {
  getAll: async (params) => {
    return await sendRequest("/getAll", "GET", params);
  },
  getById: async (id) => {
    return await sendRequest(`/getById/${id}`, "GET", null);
  },
  insert: async (params) => {
    return await sendRequest("/insert", "POST", params);
  },
  update: async (params) => {
    return await sendRequest("/update", "POST", params);
  },
  delete: async (id) => {
    return await sendRequest(`/delete/${id}`, "GET", null);
  },
};
