<!--
 * @Description: file content
 * @Author: tc
 * @Date: 2022-01-01 12:53:00
 * @LastEditors: your name
 * @LastEditTime: 2022-01-01 14:06:07
-->
<template>
  <div id="follow-us" class="guanzhu" style="padding: 20px;">
          <h2 class="hometitle">使用指南</h2>
          <ul>
              <a href="https://github.com/MiracleTanC/Neo4j-KGBuilder">
                    可以参考该链接
              </a>
          </ul>
          <div style="margin-top:3vh; line-height: 1.7;font-size: 13px; font-family: 'Microsoft YaHei';">在本平台，用户可通过输入文本内容至右上角的实体抽取框，自动执行实体识别与关系抽取任务，进而生成知识图谱。此外，我们还支持导入三元组文件或单元格树文件，以便快速构建知识图谱。用户亦可在平台上直接创建新的知识图谱。

在上方的导航栏，可根据不同的主题定义所需展示的知识图谱。通过点击相应主题，用户可在右侧的知识图谱展示区域进行查看。</div>
        </div>
</template>

<script>
export default {
  props: {
    data: Object
  },
  data() {
    return {

    };
  },
  mounted() {},
  components: {},
  methods: {

  }
};
</script>
<style>
.guanzhu ul li {
  font-size: 12px;
  margin-bottom: 10px;
  background: #fff;
  color: #525252;
  line-height: 40px;
  border: 1px solid #ddd;
  border-radius: 2px;
  position: relative;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
}
.guanzhu .wx img {
  width: 100%;
}
</style>
