<template>
    <div>
        <button>{{name}}</button>
    </div>
</template>

<script lang="ts">
    import { Component, Vue } from 'vue-property-decorator';

    @Component
    export default class MainButton extends Vue{
        name: string = 'JIHONG';
    }
</script>
