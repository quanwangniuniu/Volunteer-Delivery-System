<template>
  <v-dialog v-model="dialogVisible">
    <slot></slot>
    <v-card>
      <v-card-title>
        <h3>订单详情</h3>
      </v-card-title>
      <v-card-text>
        <p>订单编号：{{ order.orderNumber }}</p>
        <p>订单来源：{{ order.source }}</p>
        <p>订单去向：{{ order.destination }}</p>
        <p>订单报酬：{{ order.reward }}</p>
        <p>订单备注：{{ order.notes }}</p>
        <p>截止日期：{{ order.deadline }}</p>
      </v-card-text>
      <v-card-actions>
        <v-btn class="btn" @click="executeOrder">操作订单</v-btn>
        <v-btn class="btn" @click="dialogVisible = false">关闭</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: {
    order: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      order:{
        source: '',
        destination:'',
        notes:'',
        reward:'',
      },
      dialogVisible: true,
    }
  },
  methods: {
    executeOrder() {
      // 这里可以编写操作订单的代码
    }
  }
}
</script>
