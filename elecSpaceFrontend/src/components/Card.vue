<script setup>
defineProps({
    asset: {
        type: Object,
        required: true
    }
})
</script>

<template>
    <div class="card" v-if="asset">
        <div class="card-content">
            <div class="mb-2">
                <span class="tag is-info mr-2">
                    {{ asset.category ? asset.category.name : 'Unassigned' }}
                </span>
                <span class="tag" :class="asset.status === 'ACTIVE' ? 'is-success' : 'is-warning'">
                    {{ asset.status }}
                </span>
            </div>
            <p class="title is-4">{{ asset.name }}</p>
            <p class="subtitle is-6">Tag Number: {{ asset.tagNumber }}</p>
            <div>
                <div class="is-underlined">Details</div>
                <p>{{ asset.description }}</p>
            </div>
        </div>
    </div>
</template>

<style scoped>
.card {
    height: 100%;
    width: 100%;
}
</style>