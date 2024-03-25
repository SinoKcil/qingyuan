export default {
  path: "/carmap",
  redirect: "/carmap/index",
  meta: {
    icon: "informationLine",
    title: "工厂地图",
    showLink: true,
    rank: 10
  },
  children: [
    {
      path: "/carmap/index",
      name: "Carmap",
      component: () => import("@/views/carmap/index.vue"),
      meta: {
        title: "工厂地图",
        showLink: false,
      }
    },
    {
      path: "/carmap/faultDetail",
      name: "FaultDetail",
      component: () => import("@/views/carmap/detail.vue"),
      meta: {
        title: "故障详情",
        showLink:true,
      }
    }
  ]
} as RouteConfigsTable;
