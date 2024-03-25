import { store } from "@/store";
import { defineStore } from "pinia";

export const  useFormStore = defineStore({
    id:"pure-form",
    state:()=>{
        return{
        warehouseStore:{
            "regionName":"空空如也",
            "regionId":"test 1001",
            "layer":"空空如也",
            "leaderName":"韩梅梅",
            "leaderPhone":"10016",
            "position":"空空如也",
            "label":"空空如也",
        }
        };
    },
    actions:{
        getForm(newForm){
            this.warehouseStore=newForm
        }
    },
})
export function useFormStoreHook() {
    return useFormStore(store);
  }