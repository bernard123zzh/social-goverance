<!--
 * @Description: entity_indentify
 * @Author: zehuazeng
 * @Date: 2024-8-17 16:44:07
 * @LastEditors: Please set LastEditors
 * @LastEditTime:
-->
<template>
  <div class="box">
    <div class="layer1">
      <h1 class="logo3">人物实体匹配展示平台</h1>
    </div>
<!-- 上边框-->
    <div class="layer2">
      <div class="c1">
        <el-select v-model="value" placeholder="请选择">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>

        <el-input v-model="text_input1" placeholder="请输入内容"></el-input>
      </div>

      <div class="c2">
        <el-select v-model="value" placeholder="请选择">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>

        <el-input v-model="text_input2" placeholder="请输入内容"></el-input>
      </div>

      <div class="c3">
        <el-button type="primary">分析匹配</el-button>
      </div>

    </div>
<!-- 下边框   -->
    <div class="layer3">
      <div class="lt">
<!--        <div class="centre">-->
<!--          匹配相似度图表-->
<!--        </div>-->
        <div class="ct">
          <v-chart class="chart" :option="option" />
        </div>

      </div>
      <div class="rt">
        <div class="centre">
          匹配逻辑解析
        </div>
        <el-input v-model="textContent"
                  placeholder="待生成..."
                  type="textarea"
                  size="medium"
                  maxlength=500
                  rows="14"
                  class="in-el"
        ></el-input>
      </div>
    </div>


  </div>



</template>
<script>


import KgFocus from "@/components/KGFocus.vue";
import * as echarts from 'echarts';
import axios from 'axios';
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { PieChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
} from 'echarts/components';
import VChart, { THEME_KEY } from 'vue-echarts';
import { ref, defineComponent } from 'vue';

use([
  CanvasRenderer,
  PieChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
]);




export default defineComponent({
  components: {
    KgFocus,
    VChart,
  },
  provide: {
    [THEME_KEY]: 'white',
  },

  data() {
    return {
      options: [{
        value: '选项1',
        label: '人物1'
      }, {
        value: '选项2',
        label: '人物2'
      }, {
        value: '选项3',
        label: '人物3'
      }, {
        value: '选项4',
        label: '人物4'
      }, {
        value: '选项5',
        label: '人物5'
      }],
      text_input1: '',
      text_input2: '',
      textContent: '',

    }
  },

  setup() {
    const option = ref({
      title: {
        text: '匹配相似度图表',
        left: 'center',
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'shadow',
        },
      },

      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true,
      },
      xAxis: {
        type: 'category',
        data: ['人物1', '人物2', '人物3', '人物4', '人物5'],
      },
      yAxis: {
        type: 'value',
      },
      series: [
        {
          name: '匹配相似度',
          type: 'bar',
          barWidth: '50%',
          data: [335, 310, 234, 135, 1548],
          itemStyle: {
            normal: {
              color: new echarts.graphic.LinearGradient(
                0, 0, 0, 1,
                [
                  { offset: 0, color: '#83bff6' },
                  { offset: 0.5, color: '#1890ff' },
                  { offset: 1, color: '#1890ff' },
                ]
              ),
            },
          },
        },
      ],
    });

    return { option };
  },



  // mounted() {
  //   this.initCharts();
  // },
  // methods: {
  //   initCharts() {
  //     let charts = this.$echarts.init(document.getElementById("charts"));
  //     let option = {
  //       title: {
  //         text: "逐月消费趋势", // 标题
  //         subtext: "柱状图", // 副标题
  //       },
  //       xAxis: {
  //         type: "category",
  //       },
  //       yAxis: {
  //         type: "value",
  //       },
  //       color: ["#1890ff", "#52c41a", " #faad14"], // 柱状图颜色
  //       dataset: {
  //         source: [
  //           // 数据源
  //           ["1月", 1330, 666, 560],
  //           ["2月", 820, 760, 660],
  //           ["3月", 1290, 1230, 780],
  //           ["4月", 832, 450, 890],
  //           ["5月", 901, 880, 360],
  //           ["6月", 934, 600, 700],
  //         ],
  //       },
  //       series: [
  //         // 图标列设置
  //         { type: "bar", stack: "total", name: "苹果" },
  //         { type: "bar", stack: "total", name: "梨子" },
  //         { type: "bar", stack: "total", name: "桃子" },
  //       ],
  //       tooltip: {
  //         // 提示框组件
  //       },
  //     };
  //     charts.setOption(option);
  //   },
  // },
});

</script>

<style>
.logo3 { float: left;margin-left: 10px;width: 400px;font-size: 26px;padding: 8px;}

.box {
  height: 100vh;
  width: 100%;
  display: -webkit-flex;
  display: flex;
  flex-direction: column;
  //justify-content: center;
}

.layer1{
  //display: flex;
  height: 10vh;
  //width: 100%;
  align-items: center;
  justify-content: center;
  margin-left: 10vh;
  margin-right: 10vh;
}

.layer2{
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  height: 30vh;
  //width: 100%;
  border-left: 5px solid #DCDFE6;
  border-top: 5px solid #DCDFE6;
  border-right: 5px solid #DCDFE6;
  border-bottom: 5px solid #DCDFE6;
  padding-top: 2vh;
  padding-bottom: 2vh;
  padding-left: 5vh;
  padding-right: 5vh;
  margin-left: 10vh;
  margin-right: 10vh;
  margin-bottom: 2vh;
}

.c1{
  width: 90%;
  display: flex;
  padding-top: 2vh;
  padding-bottom: 2vh;
  padding-left: 5vh;
  padding-right: 5vh;
  border-left: 5px solid #E4E7ED;
  border-top: 5px solid #E4E7ED;
  border-right: 5px solid #E4E7ED;
  border-bottom: 5px solid #E4E7ED;
}

.c2{
  display: flex;
  width: 90%;
  padding-top: 2vh;
  padding-bottom: 2vh;
  padding-left: 5vh;
  padding-right: 5vh;
  border-left: 5px solid #E4E7ED;
  border-top: 5px solid #E4E7ED;
  border-right: 5px solid #E4E7ED;
  border-bottom: 5px solid #E4E7ED;
  margin-top: 2vh;
}

.c3{
  display: flex;
  //justify-content: flex-end; /* 按钮放在右边 */
  justify-content: center;
  align-items: center;
  margin-top: 4vh;
  //padding-left: 5vh;
  //padding-right: 5vh;
}
.layer3{
  display: flex;
  height:40vh;
//width: 100%;
  border-left: 5px solid #DCDFE6;
  border-top: 5px solid #DCDFE6;
  border-right: 5px solid #DCDFE6;
  border-bottom: 5px solid #DCDFE6;
  padding-top: 2vh;
  padding-bottom: 2vh;
  //padding-left: 5vh;
  //padding-right: 5vh;
  margin-top: 2vh;
  margin-left: 10vh;
  margin-right: 10vh;
}


.lt{
  height: 100%;
  width: 60%;
  border-left: 5px solid #E4E7ED;
  border-top: 5px solid #E4E7ED;
  border-right: 5px solid #E4E7ED;
  border-bottom: 5px solid #E4E7ED;
  //padding-left: 2vh;
  //padding-right: 2vh;
  margin-left:3vh;
  margin-right:2vh;
}

.rt{
  height: 100%;
  width: 40%;
  border-left: 5px solid #E4E7ED;
  border-top: 5px solid #E4E7ED;
  border-right: 5px solid #E4E7ED;
  border-bottom: 5px solid #E4E7ED;
  //padding-left: 2vh;
  //padding-right: 2vh;
  margin-left: 3vh;
  margin-right: 3vh;
}

.ct{
  display: flex;
  justify-content: center;
  height:100%;
  width:100%
}

.centre{
  display: flex;
  justify-content: center;
  align-items: center;
  padding-top: 1vh;
}

.in-el{
    justify-content: center;
    margin-top: 3vh;
    //padding-left: 1vh;
    //padding-right: 1vh;
}

</style>
