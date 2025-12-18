<script setup>
import { useRouter } from 'vue-router';

const router = useRouter();
const logOut = async () => {
    await fetch("http://localhost:8080/logout", {
        method: "POST",
        credentials: "include"
    });
    localStorage.clear();
    router.replace("/login");
}

// declares that this component can emit an event
const emit = defineEmits(['displayProfile', 'displayDashboard', 'displayCategories', 'displayAssets', 'displayUsers', 'displayForm']);

</script>
<template>
    <nav class="navbar is-fixed-top is-primary p-1" role="navigation" aria-label="main navigation">
        <div class="navbar-brand heading">
            <div class="navbar-item">
                <h1 class="has-text-weight-extrabold">[OurTechSpace]</h1>
            </div>
            <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false"
                data-target="navbarBasicExample">
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
            </a>
        </div>

        <div id="navbarBasicExample" class="navbar-menu">
            <div class="navbar-start">
                <a class="navbar-item is-underlined" @click="emit('displayDashboard')">Dashboard</a>
                <a class="navbar-item is-underlined" @click="emit('displayAssets')">Assets</a>
                <a class="navbar-item is-underlined" @click="emit('displayCategories')">Categories</a>
                <a class="navbar-item is-underlined" @click="emit('displayUsers')">Users</a>
                <a class="navbar-item is-underlined" @click="emit('displayProfile')">Profile</a>
            </div>
            <div class="navbar-end">
                <div class="navbar-item">
                    <div class="buttons">
                        <a class="button is-link" @click="emit('displayForm')">
                            <span class="icon">
                                <i class="mdi mdi-plus"></i>
                            </span>
                            <span>Add new asset</span>
                        </a>
                        <a class="button is-light" @click="logOut">Logout</a>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</template>

<style></style>