<template>
   <v-app id="inspire">
    <v-app-bar
      app
      color="white"
      flat
    >
      <v-container class="py-0 fill-height">
        <v-avatar
          class="mr-10"
          color="grey darken-1"
          size="32"
        ></v-avatar>

        <v-btn
          href="/shop"
        >
          쇼핑몰
        </v-btn>

        <v-spacer></v-spacer>

        <v-responsive max-width="260">
          <v-text-field
            dense
            flat
            hide-details
            rounded
            solo-inverted
          ></v-text-field>
        </v-responsive>
      </v-container>
    </v-app-bar>

    <v-main class="grey lighten-3">
      <v-container>
        <v-row>
          <v-col cols="2">
            <v-sheet rounded="lg">
              <v-list color="transparent">
                <v-list-item
                  v-for="item in items"
                  :key="item"
                  link
                  @click="addPet(item)"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      {{ item }}
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

                <v-divider class="my-2"></v-divider>

                <v-list-item
                  link
                  color="grey lighten-4"
                >
                  <v-list-item-content>
                    <v-list-item-title>
                      Refresh
                    </v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list>
            </v-sheet>
          </v-col>

          <v-col>
            <v-sheet
              min-height="70vh"
              rounded="lg"
            >

              <Pet @change="updatePet(pet)" :is="pets[index].type" v-model="pets[index]" v-for="(pet, index) in pets" :key="index"></Pet>
                          
              
            </v-sheet>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>

import Cat from './components/Cat'  // import components.Cat;
import Dog from './components/Dog'  // import components.Dog;
import Pet from './components/Pet'  // import components.Dog;
const axios = require('axios').default;

export default {    // public class App{
  name: 'App',

  components: {
    Cat, Dog,
    Pet
  },

  data: () => ({
      
      items: [
        '강아지',
        '고양이'
      ],
      pets: [
        
        
      ]
  }),

  created(){
    this.init();
  },

  methods:{

    async init(){
      const response = await axios.get("/cats");
      
      this.pets = response.data._embedded.cats;
    },

    addPet(item){

      if(item == "강아지"){
        this.pets.push({
          type: 'Dog',
          name: '멍멍이',
          energy: 2, appearance: 5

        });
      }else

      if(item == "고양이"){
        this.pets.push({
          type: 'Cat',
          name: '양옹이',
          energy: 2, appearance: 10

        });
      }


       
        axios.request({ 
          method: 'POST', 
          url: `/cats`, 
          headers: {'Content-Type': 'application/json'}, 
          data: this.pets[this.pets.length - 1]
        });

    },

    updatePet(pet){

        axios.request({ 
          method: 'PATCH', 
          url: new URL(pet._links.self.href).pathname, 
          headers: {'Content-Type': 'application/json'}, 
          data: pet
        });
    }

  }
};
</script>
