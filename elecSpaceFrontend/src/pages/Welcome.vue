<script setup lang="ts">
import { onMounted } from 'vue';
import { useRouter } from 'vue-router'
const router = useRouter();
onMounted(() => {
    setTimeout(() => {
        router.replace("/login");
    }, 2500);
});
</script>

<template>
    <div class="main-pg container is-align-content-center">
        <div class="center-content content p-6 box container is-flex is-flex-direction-column">
            <figure class="web-icon image is-128x128">
                <img src="../assets/simple.png" />
            </figure>
            <h1 class="is-inline-block">Welcome to [OurTechSpace]</h1>
            <button class="button is-static is-loading">Loading</button>
        </div>
    </div>
</template>

<style scoped>
.main-pg {
    background-image: url("https://wallpapercave.com/wp/wp7410874.jpg");
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
    min-height: 100vh;
    min-width: 100vw;
    margin: 0;
}
.center-content {
    width: fit-content;
}
.web-icon {
    margin: auto;
}
</style>
