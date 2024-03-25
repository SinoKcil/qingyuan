export default {
  path: "/fix",
  redirect: "/fix/index",
  meta: {
    icon: "material-symbols:date-range-outline-sharp",
    title: "故障工单",
    //showLink: false,
    rank: 2
  },
  children: [
    {
      path: "/fix/index",
      name: "AllFixTicket",
      component: () => import("@/views/fix/index.vue"),
      meta: {
        title: "工单汇总",
      }
    },
    {
      path: "/fix/ticket",
      name: "FixDetail",
      component: () => import("@/views/fix/ticket.vue"),
      meta: {
        title: "维修工单",
        showLink:false,
      }
    },
    {
      path: "/fix/form",
      name: "SubmitTicket",
      component: () => import("@/views/fix/form.vue"),
      meta: {
        title: "提交工单",
        showLink:false,
      }
    }
  ]
} as RouteConfigsTable;
