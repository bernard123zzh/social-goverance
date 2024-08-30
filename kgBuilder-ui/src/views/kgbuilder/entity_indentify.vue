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
        <el-select v-model="person1" placeholder="请选择">
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
        <el-select v-model="person2" placeholder="请选择">
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
        <el-button type="primary"
                   @click="person_match"
          >分析匹配</el-button>
      </div>

    </div>
<!-- 下边框   -->
    <div class="layer3">
      <div class="lt">
<!--        <div class="centre">-->
<!--          匹配相似度图表-->
<!--        </div>-->
        <div class="ct">
          <v-chart class="chart" :option="option" autoresize />
          <div
            class="echart"
            ref="mychart1"
            style="height:400px"
          ></div>
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
                  rows="18"
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
            value: '健身biubiu',
            label: '健身biubiu'
        }, {
            value: '健身厨男',
            label: '健身厨男'
        }, {
            value: '健身老班长',
            label: '健身老班长'
        }, {
            value: '灵魂健身杨老师',
            label: '灵魂健身杨老师'
        }, {
            value: '医学科普阿源老师',
            label: '医学科普阿源老师'
        }],
        text_input1: '',
        text_input2: '',
        textContent: '',
        person1: '',
        person2: '',
        // person_list: [
        //     '健身biubiu,女,322.7万粉丝,162关注,764.3万转评赞,昨日发博13，阅读数10万+，互动数3323,视频累计播放量2.68亿,运动博主,已实名,IP属地：广东,商务合作QQ：2494997064,1995-04-10 白羊座,中国农业大学,2011-11-24 加入微博,信用极好.',
        //     '健身厨男,男,158.6万粉丝,401关注,339.5万转评赞,健康博主,双鱼座,福建 福州,2011-11-05 加入微博,信用极好.',
        //     '健身老班长,女,135.3万粉丝,105关注,572.5万转评赞,昨日发博21，阅读数10万+，互动数347,视频累计播放量4.06亿,运动博主,已实名,IP属地：安徽,1994-03-18 双鱼座,2015-02-08 加入微博,信用极好.',
        //     '灵魂健身杨老师,男,153.7万粉丝,1154关注,152万转评赞,上海潜能挖掘健康咨询有限公司健康教练,IP属地：江苏,DP健身工作室,南京师范大学,2017-03-17 加入微博,信用极好.',
        //     '医学科普阿源老师,男,32万粉丝,97关注,11.1万转评赞,微博新知博主,IP属地：浙江,这个疗法真神奇？拿出证据来,2013-11-11 天蝎座,第三军医大学,2013-08-04 加入微博,信用极好.'
        // ],
        // chart_ps:["人物1", "人物2", "人物3", "人物4", "人物5"],
        // chart_rate:[0, 0, 0, 0, 0],
        chart_ps:[],
        chart_rate:[],
        // test:this.getRandomData(),
    }
  },

    computed:{
      option(){
        return {
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
            type: "category",
            // data: this.test.map(d=>d.time),
            data: this.chart_ps,
          },

          yAxis: {
            type: "value",
            axisLabel: {
              formatter: '{value}'
            }
          },

          series: [
            {
              name: '匹配相似度',
              type: 'bar',
              barWidth: '50%',
              // data: this.test.map(d=>d.value),
              data: this.chart_rate,
              itemStyle: {
                normal: {
                  color: new echarts.graphic.LinearGradient(
                    0, 0, 0, 1,
                    [
                      {offset: 0, color: '#83bff6'},
                      {offset: 0.5, color: '#1890ff'},
                      {offset: 1, color: '#1890ff'},
                    ]
                  ),
                },
              },
            },
          ],
        }
      },
    },

    methods: {
      // person_match() {
      //   const payload = {
      //     person1_name: this.person1,
      //     person2_name: this.person2,
      //     input_text_1: this.text_input1,
      //     input_text_2: this.text_input2,
      //     // person_list: this.person_list,
      //   };
      //
      //   axios.post('http://10.162.147.19:4000/compare_persons', payload)
      //     .then(response => {
      //       // Handle success.
      //       this.personResult = response.data;
      //       const person2s = response.data.sorted_results.map(item => item.person2);
      //       const similarities = response.data.sorted_results.map(item => item.similarity[0]); // Assuming similarity is an array with one element
      //       this.textContent = this.personResult.reasons;
      //
      //       // Update chart_ps1 to chart_ps5
      //       for (let i = 0; i < person2s.length; i++) {
      //         const numberStr = similarities[i].replace(/[^0-9.]/g, '');
      //         const number = parseFloat(numberStr)/100;
      //         this.$set(this.chart_ps, i, person2s[i]);
      //         this.$set(this.chart_rate, i, number);
      //       }
      //
      //
      //       // console.log('data:', this.xAxisData.value, this.similarity.value);
      //       console.log('data:',this.chart_ps,this.chart_rate)
      //       console.log("personResult:", this.personResult);
      //     })
      //     .catch(error => {
      //       // Handle error.
      //       console.error('There was an error!', error);
      //     });
      // },


        person_match() {
            const payload = {
                person1_name: this.person1,
                person2_name: this.person2,
                input_text_1: this.text_input1,
                input_text_2: this.text_input2,
                // person_list: this.person_list,
            };

            axios.post('http://10.162.147.19:4000/compare_persons', payload)
                .then(response => {
                    // Handle success.
                    const personResult = response.data.results;
                    console.log("personResult",personResult)
                    const compare_response = personResult[3]
                    const person1 = personResult[1]
                    const person2 = personResult[2]
                    const similarity = personResult[0];
                    const similarityNumber = parseInt(similarity, 10)/100
                    console.log("compare_response",compare_response)
                    console.log("person1",person1)
                    console.log("person2",person2)
                    console.log("similarity",similarityNumber)
                    this.textContent = compare_response;

                    // Update chart_ps1 to chart_ps5
                    this.chart_ps.push(person2);
                    this.chart_rate.push(similarityNumber);

                    // console.log('data:', this.xAxisData.value, this.similarity.value);
                    // console.log('data:',this.chart_ps,this.chart_rate)
                    // console.log("personResult:", this.personResult);
                })
                .catch(error => {
                    // Handle error.
                    console.error('There was an error!', error);
                });
        },


      getRandomData(){
          return[
            {
              time:'人物1',
              value:Math.random()
            },
            {
              time:'人物2',
              value:Math.random()
            },
            {
              time:'人物3',
              value:Math.random()
            },
            {
              time:'人物4',
              value:Math.random()
            },
            {
              time:'人物5',
              value:Math.random()
            },
          ]
      }
    },

    // created() {
    //   setInterval(() =>{
    //     this.chart_ps = this.chart_ps;
    //     this.chart_rate = this.chart_rate;
    //     },3000);
    // },

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
