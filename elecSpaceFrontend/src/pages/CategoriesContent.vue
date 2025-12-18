<script setup>
import { ref, onMounted, computed } from "vue";
const categories = ref([]);
const assets = ref([]);
onMounted(async () => {
    const res = await fetch("http://localhost:8080/api/categories", {
        credentials: "include"
    })
    const res1 = await fetch("http://localhost:8080/api/assets", {
        credentials: "include"
    });

    if (!res.ok) {
        console.error("Not authorized");
        return;
    }
    if (res.ok) {
        categories.value = await res.json();
    }
    if (!res1.ok) {
        console.error("Not authorized");
        return;
    }
    if (res1.ok) {
        assets.value = await res1.json();
    }
});

const categoryCount = computed(() => {
  const counts = {};
  assets.value.forEach(asset => {
    const id = asset.category?.id ?? 'Unassigned'; 
    if (!counts[id]) counts[id] = 0;
    counts[id]++;
  });
  return counts;
});



</script>

<template>
    <div class="main has-navbar-fixed-top mt-3">
        <div class="section pt-6">
            <h1 class="title">Categories</h1>
        </div>
        <div class="section">
            <div class="table-holder">
                <table class="table is-bordered is-striped is-hoverable is-fullwidth">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Category Name</th>
                            <th>Item Count</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="oneCategory in categories" :key="oneCategory.id">
                            <th>{{ oneCategory.id }}</th>
                            <td>{{ oneCategory.name }}</td>
                            <td>{{ categoryCount[oneCategory.id] }}</td>
                        </tr>
                    </tbody>
                </table>
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
.table-holder {
    border-radius: 12px;
    overflow: hidden;
    border: 1px solid black;
}
</style>