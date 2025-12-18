<script setup>
import { ref, onMounted } from "vue";

const users = ref([]);
onMounted(async () => {
    const res = await fetch("http://localhost:8080/api/users", {
        credentials: "include"
    })


    if (!res.ok) {
        console.error("Not authorized");
        return;
    }
    if (res.ok) {
        users.value = await res.json();
    }
});

</script>

<template>
    <div class="main has-navbar-fixed-top mt-3">
        <div class="section pt-6">
            <h1 class="title">Users</h1>
        </div>
        <div class="section">
            <div class="table-holder">
                <table class="table is-bordered is-striped is-hoverable is-fullwidth">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Employee Name</th>
                            <th>Email</th>
                            <th>ID Card No.</th>
                            <th>Username</th>
                            <th>Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="user in users" :key="user.id">
                            <th>{{ user.id }}</th>
                            <td>{{ user.name }}</td>
                            <td>{{ user.email }}</td>
                            <td>{{ user.idCardNumber }}</td>
                            <td>{{ user.username }}</td>
                            <td class="is-capitalized">{{ user.role }}</td>
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