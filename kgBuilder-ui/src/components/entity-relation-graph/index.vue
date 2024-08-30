<template>
    <div class="container" :style="{ backgroundImage: `url(${backgroundImageUrl})` }">
        <RelationGraph
            ref="graphRef"
            :options="graphOptions"/>
    </div>
</template>

<style lang="scss" scoped>
.container {
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}

::v-deep .rel-map {
    background: none !important;
    backdrop-filter: blur(5px) brightness(50%);
    -webkit-backdrop-filter: blur(5px) brightness(50%);
}
</style>

<style>
.rel-toolbar {
    top: 15px !important;
}
</style>

<script>
export default {
    name: "EntityRelationGraph",
    components: {
        RelationGraph: () => import('relation-graph')
    },
    props: {
        backgroundImageUrl: {
            type: String,
            default: ''
        }
    },
    data() {
        return {
            graphOptions: {
                debug: false,
                defaultNodeBorderWidth: 0,
                allowSwitchLineShape: true,
                allowSwitchJunctionPoint: true,
                defaultLineShape: 1,
                layout:
                    {
                        layoutName: 'force',
                        layoutClassName: 'seeks-layout-force'
                    },
                defaultJunctionPoint: 'border'
            }
        };
    },
    methods: {
        // data: [['央视新闻报', 'ORGANIZATION', 1, 3], ['13日', 'DATE', 6, 7], ['下午', 'TIME', 7, 8], ...]
        setEntityData(data, rootText = '实体识别', colors = ['#ee0080', '#ee8c00', '#23c4b4'], zoom = 30) {
            const msraMap = {
                'PERSON': 'PERSON',
                'LOCATION': 'LOCATION',
                'ORGANIZATION': 'ORGANIZATION',
                'DATE': 'TIMEX',
                'DURATION': 'TIMEX',
                'TIME': 'TIMEX',
                'PERCENT': 'NUMEX',
                'MONEY': 'NUMEX',
                'FREQUENCY': 'NUMEX',
                'INTEGER': 'NUMEX',
                'FRACTION': 'NUMEX',
                'DECIMAL': 'NUMEX',
                'ORDINAL': 'NUMEX',
                'RATE': 'NUMEX',
                'AGE': 'MEASUREX',
                'WEIGHT': 'MEASUREX',
                'LENGTH': 'MEASUREX',
                'TEMPERATURE': 'MEASUREX',
                'ANGLE': 'MEASUREX',
                'AREA': 'MEASUREX',
                'CAPACITY': 'MEASUREX',
                'SPEED': 'MEASUREX',
                'ACCELERATION': 'MEASUREX',
                'MEASURE': 'MEASUREX',
                'EMAIL': 'ADDREX',
                'PHONE': 'ADDREX',
                'FAX': 'ADDREX',
                'TELEX': 'ADDREX',
                'WWW': 'ADDREX',
                'POSTALCODE': 'ADDREX',
            };
            const msraTranslate = {
                'PERSON': '人物',
                'LOCATION': '地点',
                'ORGANIZATION': '组织',
                'TIMEX': '时间',
                'NUMEX': '数字',
                'MEASUREX': '度量',
                'ADDREX': '联系方式',
            }; // todo: i18n

            // graphData: {'rootId': 'root', 'nodes': [{'id': 'root', 'text': '实体识别'}, ...], 'lines': [{'from': 'root', 'to': '1'}, ...]}
            // 一个三层的树状结构，根节点为root，第二层为实体识别的类别，第三层为具体的实体
            // 首先将data按照item[1]进行映射msraMap，映射的结果为第二层节点，然后使用msraTranslate进行翻译，作为节点的text
            // 之后每个item[0]作为第三层节点

            const graphData = { rootId: 'root', nodes: [{ id: 'root', text: rootText, color: colors[0] }], lines: [] };
            const nodeMap = {};
            let nodeId = 0;
            for (const item of data) {
                const type = msraMap[item[1]];
                if (!type) {
                    continue;
                }
                const text = msraTranslate[type];
                if (!nodeMap[type]) {
                    nodeMap[type] = { id: type, text: text, color: colors[1] };
                    graphData.nodes.push(nodeMap[type]);
                    graphData.lines.push({ from: 'root', to: type });
                }
                const entity = { id: (nodeId++).toString(), text: item[0], color: colors[2] };
                graphData.nodes.push(entity);
                graphData.lines.push({ from: type, to: entity.id });
            }

            this.$refs.graphRef.setJsonData(graphData, (graphInstance) => {
                graphInstance.setZoom(zoom);
            });
        },
    }
};
</script>
