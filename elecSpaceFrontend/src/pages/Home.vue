<script setup>
import MyNavBar from '../components/MyNavBar.vue';
import { ref } from 'vue';
import ProfileContent from './ProfileContent.vue';
import CategoriesContent from './CategoriesContent.vue';
import AssetsContent from './AssetsContent.vue';
import DashboardContent from './DashboardContent.vue';
import UsersContent from './UsersContent.vue';
import NewAssetFormContent from './NewAssetFormContent.vue';

const displays = ref('dashboard');
</script>

<template>
    <!-- listens for events and updates parent state -->
    <MyNavBar
        @displayProfile="displays = 'profile'"
        @displayDashboard="displays = 'dashboard'"
        @displayCategories="displays = 'categories'"
        @displayAssets="displays = 'assets'"
        @displayUsers="displays = 'users'"
        @displayForm="displays = 'form'"
    />
    <ProfileContent v-if="displays === 'profile'"/>
    <CategoriesContent v-if="displays === 'categories'" />
    <AssetsContent v-if="displays === 'assets'"/>
    <DashboardContent v-if="displays === 'dashboard'" />
    <UsersContent v-if="displays === 'users'"/>
    <NewAssetFormContent v-if="displays === 'form'"/>
</template>

<style scoped></style>