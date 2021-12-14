<template>

  <v-card
    class="mx-auto"
    max-width="344"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="text-overline mb-4">
          {{value.type}}
        </div>
        <v-list-item-title class="text-h5 mb-1" v-if="editMode">
          <v-text-field v-model="value.name"></v-text-field>
        </v-list-item-title>
        <v-list-item-title class="text-h5 mb-1" @click="toggleEditMode" v-else>
          {{value.name}}
        </v-list-item-title>
        <v-list-item-subtitle @click="toggleEditMode">에너지는 {{value.energy}}이고 외모지수는 {{value.appearance}}입니다.</v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar
        tile
        size="80"
        color="grey"
      ></v-list-item-avatar>
    </v-list-item>

    <v-card-actions>
      <v-btn
        outlined
        rounded
        text

        @click="eat"
      >
        먹이주기
      </v-btn>
      <v-btn
        outlined
        rounded
        text

        @click="sleep"
      >
        잠자기
      </v-btn>

      <slot name="buttons"></slot>
      
    </v-card-actions>
  </v-card>


</template>

<script>

  const axios = require('axios').default;

  export default {
    name: 'Pet',

    props: {
      value: Object,
      editMode: Boolean
    },


    methods:{

      async eat(){

        await axios.request({ 
          method: 'PUT', 
          url: new URL(this.value._links.feed.href).pathname, 
          headers: {'Content-Type': 'application/json'}
        });

        this.refresh();



        // if(this.$parent.eat){
        //   this.$parent.eat();
        // }else
        //   this.value.energy += 2; //this.energy = this.energy + 2;

        // this.$emit("change", this.value); 


      },

      async sleep(){

        await axios.request({ 
          method: 'PUT', 
          url: new URL(this.value._links.sleep.href).pathname, 
          headers: {'Content-Type': 'application/json'}
        });

        this.refresh();

        
        // if(this.$parent.sleep){
        //   this.$parent.sleep();
        // }else
        //   this.value.energy += 2;

        // this.$emit("change", this.value); 

      },

      async refresh(){
        var response = await axios.get(new URL(this.value._links.self.href).pathname);

        if(response){
          this.value = response.data;
          this.$emit("input", this.value);
        }
      },

      toggleEditMode(){
        this.editMode = !this.editMode;

        this.$emit("change", this.value);
      }


    }
  }
</script>
