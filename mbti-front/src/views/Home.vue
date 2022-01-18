<template>
  <div class="home">
    <br/>
    <nameButton name = "지혜"></nameButton>
    <br/>
    <nameButton name = "지홍"></nameButton>
    <br/>
    <nameButton name = "기원"></nameButton>
    <br/>
    <nameButton name = "태훈"></nameButton>
    <br/>
    <nameButton name = "소영"></nameButton>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
import nameButton from '../components/NameButton.vue';

@Component({
  components: {
    nameButton,
  },
})
export default class Home extends Vue {}
</script>
