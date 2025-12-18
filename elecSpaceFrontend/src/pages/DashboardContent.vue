<script setup>
import { ref, onMounted, computed } from "vue";
const users = ref([]);
const assets = ref([]);
const oneUser = ref([]);
onMounted(async () => {
    const res1 = await fetch("http://localhost:8080/api/assets", {
        credentials: "include"
    });
    const res2 = await fetch("http://localhost:8080/api/users", {
        credentials: "include"
    })
    const res3 = await fetch("http://localhost:8080/api/auth/me", {
        credentials: "include"
    })

    if (!res3.ok) {
        console.error("Not authorized");
        return;
    }
    if (res3.ok) {
        oneUser.value = await res3.json();
    }

    if (!res1.ok) {
        console.error("Not authorized");
        return;
    }
    if (res1.ok) {
        assets.value = await res1.json();
    }

    if (!res2.ok) {
        console.error("Not authorized");
        return;
    }
    if (res2.ok) {
        users.value = await res2.json();
    }
});

const statusCount = computed(() => {
    const counts = { 'ACTIVE': 0, 'INACTIVE': 0, 'REPAIR': 0, 'LOST': 0, 'DAMAGED': 0, 'SCRAPPED': 0 };
    assets.value.forEach(asset => {
        if (counts[asset.status] !== undefined) counts[asset.status]++;
    });
    return counts;
});

</script>
<template>
    <div class="main has-navbar-fixed-top mt-3">
        <div class="section pt-6">
            <h1 class="title">Hello, {{ oneUser.name }}</h1>
        </div>
        <div class="section">
            <div class="grid is-row-gap-3 is-gap-3">
                <div class="cell has-background-warning-70 p-6">
                    <h1 class="subtitle is-5">Total assets</h1>
                    <h1 class="title is-4">{{ assets.length }} equipments</h1>
                </div>
                <div class="cell has-background-warning-70 p-6">
                    <h1 class="subtitle is-5">Total users</h1>
                    <h1 class="title is-4">{{ users.length }} employees</h1>
                </div>
            </div>
        </div>
        <div class="section">
            <div class="grid is-col-min-15 is-row-gap-3 is-gap-3">
                <div class="cell has-background-primary p-6">
                    <h1 class="subtitle is-5">Total active</h1>
                    <h1 class="title is-4">{{ statusCount['ACTIVE'] }}</h1>
                </div>
                <div class="cell has-background-info p-6">
                    <h1 class="subtitle is-5">Total inactive</h1>
                    <h1 class="title is-4">{{ statusCount['INACTIVE'] }}</h1>
                </div>
                <div class="cell has-background-primary p-6">
                    <h1 class="subtitle is-5">Total in repair</h1>
                    <h1 class="title is-4">{{ statusCount['REPAIR'] }}</h1>
                </div>
                <div class="cell has-background-info p-6">
                    <h1 class="subtitle is-5">Total damaged</h1>
                    <h1 class="title is-4">{{ statusCount['DAMAGED'] }}</h1>
                </div>
                <div class="cell has-background-primary p-6">
                    <h1 class="subtitle is-5">Total scrapped</h1>
                    <h1 class="title is-4">{{ statusCount['SCRAPPED'] }}</h1>
                </div>
                <div class="cell has-background-info p-6">
                    <h1 class="subtitle is-5">Total lost</h1>
                    <h1 class="title is-4">{{ statusCount['LOST'] }}</h1>
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
    border-radius: 1em;
}
</style>