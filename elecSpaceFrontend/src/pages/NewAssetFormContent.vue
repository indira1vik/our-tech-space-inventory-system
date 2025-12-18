<script setup>
import { ref, onMounted, computed } from "vue";
const categories = ref([]);
onMounted(async () => {
    const res = await fetch("http://localhost:8080/api/categories", {
        credentials: "include"
    })
    if (!res.ok) {
        console.error("Not authorized");
        return;
    }
    if (res.ok) {
        categories.value = await res.json();
    }
});
const name = ref("");
const description = ref("");
const tagNumber = ref("");
const categoryId = ref("");
const status = ref("");

const submit = async () => {
    const payload = {
        name: name.value,
        description: description.value,
        tagNumber: tagNumber.value,
        category: { id: categoryId.value },
        status: status.value
    };

    const res = await fetch("http://localhost:8080/api/assets", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        credentials: "include",
        body: JSON.stringify(payload)
    });

    if (res.ok) {
        alert("Asset added!");
        name.value = "";
        description.value = "";
        tagNumber.value = "";
        categoryId.value = "";
        status.value = "";
    } else {
        alert("Failed to add asset");
    }
};


</script>

<template>
    <div class="main has-navbar-fixed-top mt-3">
        <div class="section pt-6">
            <div class="card">
                <div class="card-header">
                    <p class="card-header-title">
                        <span class="icon mr-2"><i class="mdi mdi-pen mdi-24px"></i></span>
                        <span>Add Details</span>
                    </p>
                </div>
                <div class="card-content">
                    <div>
                        <div class="mb-2">Name</div>
                        <input v-model="name" type="text" class="input" placeholder="Name" required>
                    </div>
                    <hr>
                    <div>
                        <div class="mb-2">Description</div>
                        <input v-model="description" type="text" class="input" placeholder="Description" required>
                    </div>
                    <hr>
                    <div>
                        <div class="mb-2">Tag Number</div>
                        <input v-model="tagNumber" type="number" class="input" placeholder="Tag Number" required>
                    </div>
                    <hr>
                    <div>
                        <div class="mb-2">Category</div>
                        <div class="control">
                            <div class="select">
                                <select v-model="categoryId" required>
                                    <option value="">Select</option>
                                    <option v-for="cat in categories" :key="cat.id" :value="cat.id">{{ cat.name }}
                                    </option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div>
                        <div class="mb-2">Status</div>
                        <div class="control">
                            <div class="select">
                                <select v-model="status">
                                    <option value="">Select</option>
                                    <option>ACTIVE</option>
                                    <option>INACTIVE</option>
                                    <option>REPAIR</option>
                                    <option>DAMAGED</option>
                                    <option>LOST</option>
                                    <option>SCRAPPED</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <button @click.prevent="submit" class="button is-primary">Submit</button>
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
</style>