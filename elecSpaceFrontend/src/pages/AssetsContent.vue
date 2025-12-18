<script setup>
import Card from '../components/Card.vue';

import { ref, onMounted } from "vue";

const assets = ref([]);
onMounted(async () => {
    const res = await fetch("http://localhost:8080/api/assets", {
        credentials: "include"
    })

    if (!res.ok) {
        console.error("Not authorized");
        return;
    }
    if (res.ok) {
        assets.value = await res.json();
        assets.value = assets.value.reverse();
    }
});
</script>

<template>
    <div class="main has-navbar-fixed-top mt-3">
        <div class="section pt-6">
            <h1 class="title">Assets</h1>
        </div>
        <div class="section">
            <div class="grid is-col-min-12 is-row-gap-3 is-gap-3">
                <div class="cell" v-for="asset in assets" :key="asset.id">
                    <Card :asset="asset" />
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.main>div {
    padding-top: 0;
    padding-bottom: 0;
}

.main {
    display: flex;
    flex-direction: column;
    gap: 2em;
    padding-top: 2em;
    padding-bottom: 2em;
}

.cell {
    display: flex;
}
</style>