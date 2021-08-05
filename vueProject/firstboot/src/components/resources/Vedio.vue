<template>
  <!-- <div>视频专区</div> -->
  <div id="app">
    <el-upload
      class="upload-demo"
      ref="upload"
      :action="uploadurl"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :file-list="fileList"
      :on-success="handleOnSuccess"
      :auto-upload="false"
      accept=".mp4"
      :limit="1"
      :data="fileMsg"
    >
      <el-button slot="trigger" size="small" type="primary">选取视频</el-button>
      <div slot="tip" class="el-upload__tip">
        只能上传.mp4视频文件，且不超过100MB
      </div>

      <!-- 添加视频信息对话框 -->
      <el-dialog
        title="添加视频信息"
        width="50%"
        :visible.sync="uploadDialogVisible"
      >
        <el-form ref="modifyFormRef" label-width="70px">
          <el-form-item label="文件名称" prop="name">
            <el-input v-model="fileMsg.name"></el-input>
          </el-form-item>

          <el-form-item label="描   述" prop="description">
            <el-input v-model="fileMsg.description"></el-input>
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="uploadDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="upload">确定</el-button>
        </span>
      </el-dialog>
      <!--  -->
      <el-button
        style="margin-left: 10px"
        size="small"
        type="success"
        @click="submitUpload"
        >上传</el-button
      >
    </el-upload>
  </div>
</template>

<script>
export default {
  data() {
    return {
      uploadurl: "http://localhost:9000/uploadfile",
      fileList: [],
      fileMsg: {
        name: "",
        uid: "",
        description: "",
      },
      uploadDialogVisible: false,
    };
  },
  methods: {
    async upload() {
      // if(this.fileList.length<=0){
      //   return this.$message.error("请先选择一个视频！");
      // }
      this.uploadDialogVisible = false;
      // const uname = window.sessionStorage.getItem("user");
      // console.log("[" + uname + "]");
      // window.sessionStorage.setItem("user", res.user);

      // this.fileMsg.uid = user.id;
      this.fileMsg.uid = "10";
      // console.log(this.fileMsg.uid + "===");
      // const { data: res } = await this.$http.post("/getUid", uname);

      // if (res.flag == "error") {
      //   return this.$message.error("用户id获取失败！请重新上传...");
      // }

      // this.fileMsg.uid = res.id; //user.username;

      this.$refs.upload.submit();
      // console.log(val);
    },
    submitUpload() {
      //  if(this.fileList.length<=0){
      //   return this.$message.error("请先选择一个视频！");
      // }
      // this.$refs.upload.submit();
      this.uploadDialogVisible = true;
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
      return this.$message.success("移除成功！");
    },
    handlePreview(file) {
      console.log(file);
    },
    handleOnSuccess() {
      return this.$message.success("上传成功！");
    },
  },
};
</script>

<style>
</style>