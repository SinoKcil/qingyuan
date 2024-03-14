export default {
    path: "/carmap",
    redirect: "/user/index",
    meta: {
      icon: "material-symbols:account-circle-outline",
      title: "个人中心",
      //showLink: false,
      rank: 10
    },
    children: [
      {
        path: "/user/index",
        name: "UserCenter",
        component: () => import("@/views/user/index.vue"),
        meta: {
          title: "个人中心"
        }
      },
     
    ]
  } as RouteConfigsTable;
  