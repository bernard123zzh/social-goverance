<script>
import bgImgUrl from '../../assets/bg.jpg';
import { EntityRelationGraph } from '../../components/kg.js';
import axios from "axios";
export default {
  name: "App",
  components: { EntityRelationGraph },
  data() {
    return {
      bgImgUrl,
      inputText: ``
    }
  },
  methods: {
    async parseEntityData() {
      const input_data = JSON.stringify({ text: this.inputText });
      try {
        const response = await axios.post('http://10.162.147.19:5000/process_text', input_data, {
          headers: {
            'Content-Type': 'application/json'
          }
        });
        console.log("Full response:", response);
        const ner_result = response.data;
        if (ner_result) {
          console.log("ner_result:", ner_result); // 打印ner_result
          this.$refs.entityRelationGraph.setEntityData(ner_result, '实体抽取', ['#ee0080', '#ee8c00', '#23c4b4']);
        } else {
          console.error("ner_result is undefined");
        }
      } catch (error) {
        console.error("Error:", error);
      }
    }

  }
};
</script>

<template>
  <main>
    <div>
            <textarea
                ref="input"
                v-model="inputText"
                placeholder="请输入JSON格式的实体识别结果"
                style="width: 100%; height: 100px; padding: 10px; font-size: 16px; box-sizing: border-box;"></textarea>

      <button @click="parseEntityData">显示</button>

      <EntityRelationGraph
          class="graph"
          ref="entityRelationGraph"
          :backgroundImageUrl="bgImgUrl"/>
    </div>
  </main>
</template>

<style scoped>
main {
  position: absolute;
  width: 100%;
  height: 100%;
  display: flex;
}

div {
  position: relative;
  margin: 10px;
  display: flex;
  flex-direction: column;
  flex-grow: 1;
}

textarea {
  margin-bottom: 10px;
}

button {
  margin-bottom: 10px;
}

.graph {
  margin: 0;
  flex-grow: 1;
}

</style>
