<script setup>
import { ref, onMounted } from "vue";

const oneUser = ref([]);
onMounted(async () => {
    const res = await fetch("http://localhost:8080/api/auth/me", {
        credentials: "include"
    })

    if (!res.ok) {
        console.error("Not authorized");
        return;
    }
    if (res.ok) {
        oneUser.value = await res.json();
    }
});
</script>

<template>
    <div class="main has-navbar-fixed-top mt-3">
        <div class="section pt-6">
            <h1 class="title">Profile</h1>
        </div>

        <div class="section">
            <div class="card">
                <div class="card-header">
                    <p class="card-header-title">
                        <span class="icon mr-2"><i class="mdi mdi-account-circle mdi-24px"></i></span>
                        <span>Information</span>
                    </p>
                </div>
                <div class="card-content">
                    <div>
                        <figure class="image is-128x128">
                            <img class="dp" :src="oneUser.imgUrl" />
                        </figure>
                    </div>
                    <hr>
                    <div>
                        <div>Name</div>
                        <div class="subtitle is-5">{{ oneUser.name }}</div>
                    </div>
                    <hr>
                    <div>
                        <div>Email</div>
                        <div class="subtitle is-5">{{ oneUser.email }}</div>
                    </div>
                    <hr>
                    <div>
                        <div>ID Card Number</div>
                        <div class="subtitle is-5">{{ oneUser.idCardNumber }}</div>
                    </div>
                    <hr>
                    <div>
                        <div>Username</div>
                        <div class="subtitle is-5">{{ oneUser.username }}</div>
                    </div>
                    <hr>
                    <div>
                        <div>Role</div>
                        <div class="subtitle is-5 is-capitalized">{{ oneUser.role }}</div>
                    </div>
                </div>
            </div>
        </div>

        <!-- <div class="section">
            <div class="card">
                <div class="card-header">
                    <p class="card-header-title">
                        <span class="icon mr-2"><i class="mdi mdi-lead-pencil mdi-24px"></i></span>
                        <span>Edit Details</span>
                    </p>
                </div>
                <div class="card-content">
                    <div>
                        <div class="mb-2">Name</div>
                        <input type="text" name="new-name" value="" class="input" placeholder="Full Name"></input>
                    </div>
                    <hr>
                    <div>
                        <div class="mb-2">Email</div>
                        <input type="email" name="new-email" value="" class="input" placeholder="Email ID"></input>
                    </div>
                    <hr>
                    <div>
                        <div class="mb-2">Username</div>
                        <input type="text" name="new-username" value="" class="input" placeholder="Username"></input>
                    </div>
                    <hr>
                    <button class="button is-primary">Submit</button>
                </div>
            </div>
        </div>

        <div class="section">
            <div class="card">
                <div class="card-header">
                    <p class="card-header-title">
                        <span class="icon mr-2"><i class="mdi mdi-lock mdi-24px"></i></span>
                        <span>Change Password</span>
                    </p>
                </div>
                <div class="card-content">
                    <div>
                        <div class="mb-2">Current Password</div>
                        <input type="password" name="current-password" value="" class="input" placeholder="Password" required>
                    </div>
                    <hr>
                    <div>
                        <div class="mb-2">New Password</div>
                        <input type="password" name="new-password" value="" class="input" placeholder="Password" required></input>
                    </div>
                    <hr>
                    <div>
                        <div class="mb-2">Confirm Password</div>
                        <input type="password" name="new-confirm-password" value="" class="input" placeholder="Password" required>
                    </div>
                    <hr>
                    <button class="button is-primary">Submit</button>
                </div>
            </div>
        </div> -->
    </div>
</template>

<style scoped>
.dp {
    border-radius: 1em;
    border: 2px solid black;
}
.main > div {
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