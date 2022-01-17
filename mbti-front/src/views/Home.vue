<template>
  <div class="home">
    <mainbutton></mainbutton>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import mainbutton from '../components/MainButton.vue';

@Component({
  components: {
    mainbutton,
  },
})
export default class Home extends Vue {}
</script>
