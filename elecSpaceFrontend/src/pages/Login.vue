<script setup lang="ts">

import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();

const signup = ref(false);
const email = ref('');
const password = ref('');

const loginUser = async () => {

    const formData = new URLSearchParams();
    formData.append("username", email.value);
    formData.append("password", password.value);

    const res = await fetch("http://localhost:8080/login", {
        method: "POST",
        credentials: "include",
        body: formData
    });

    if (!res.ok) {
        alert("Invalid Login");
        return;
    }

    router.replace("/home");
}

const newName = ref("");
const newEmail = ref("");
const newUsername = ref("");
const newPassword = ref("");
const newPic = ref("");
const newIDCardNumber = ref();
const newRole = ref("");

const signupUser = async () => {
    // build payload from v-models
    const payload = {
        name: newName.value,
        email: newEmail.value,
        username: newUsername.value,
        password: newPassword.value,
        profilePic: newPic.value,
        idCardNumber: Number(newIDCardNumber.value),
        role: newRole.value || "EMPLOYEE"
    };

    try {
        const res = await fetch("http://localhost:8080/api/auth/signup", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "include",
            body: JSON.stringify(payload)
        });

        if (!res.ok) {
            const errorText = await res.text();
            alert("Signup failed: " + errorText);
            return;
        }

        alert("Signup successful!");
        signup.value = false; // switch to login view

        // optionally reset the form
        newName.value = "";
        newEmail.value = "";
        newUsername.value = "";
        newPassword.value = "";
        newPic.value = "";
        newIDCardNumber.value = "";
        newRole.value = "";
    } catch (err) {
        console.error(err);
        alert("Signup failed: network error");
    }
};
</script>

<template>
    <div v-if="!signup">
        <div class="login-pg container is-align-content-center has-background-primary">
            <div class="login-form content p-6 box container is-flex is-flex-direction-column">
                <h1>Sign-in</h1>
                <div class="field">
                    <p class="control has-icons-left has-icons-right">
                        <input class="input" type="email" placeholder="Email" v-model="email" />
                        <span class="icon is-small is-left">
                            <i class="mdi mdi-email mdi-dark"></i>
                        </span>
                    </p>
                </div>
                <div class="field">
                    <p class="control has-icons-left">
                        <input class="input" type="password" placeholder="Password" v-model="password" />
                        <span class="icon is-small is-left">
                            <i class="mdi mdi-lock mdi-dark"></i>
                        </span>
                    </p>
                </div>
                <div class="field">
                    <p class="control">
                        <button class="btn button is-primary" @click="loginUser">
                            Login
                        </button>
                    </p>
                </div>
                <div>
                    <a @click="signup = !signup" class="has-text-link is-underlined">Create new user account</a>
                </div>
            </div>
        </div>
    </div>
    <div v-else>
        <div class="login-pg container is-align-content-center has-background-primary">
            <div class="login-form content p-6 box container is-flex is-flex-direction-column">
                <h1>Sign-up</h1>
                <div class="field">
                    <p class="control has-icons-left has-icons-right">
                        <input class="input" type="text" placeholder="Enter Name" v-model="newName" />
                        <span class="icon is-small is-left">
                            <i class="mdi mdi-account mdi-dark"></i>
                        </span>
                    </p>
                </div>
                <div class="field">
                    <p class="control has-icons-left has-icons-right">
                        <input class="input" type="email" placeholder="Enter Email" v-model="newEmail" />
                        <span class="icon is-small is-left">
                            <i class="mdi mdi-email mdi-dark"></i>
                        </span>
                    </p>
                </div>
                <div class="field">
                    <p class="control has-icons-left has-icons-right">
                        <input class="input" type="text" placeholder="Enter Username" v-model="newUsername" />
                        <span class="icon is-small is-left">
                            <i class="mdi mdi-account mdi-dark"></i>
                        </span>
                    </p>
                </div>
                <div class="field">
                    <p class="control has-icons-left has-icons-right">
                        <input class="input" type="text" placeholder="Link to new profile pic" v-model="newPic" />
                        <span class="icon is-small is-left">
                            <i class="mdi mdi-image mdi-dark"></i>
                        </span>
                    </p>
                </div>
                <div class="field">
                    <p class="control has-icons-left">
                        <input class="input" type="password" placeholder="Create password" v-model="newPassword" />
                        <span class="icon is-small is-left">
                            <i class="mdi mdi-lock mdi-dark"></i>
                        </span>
                    </p>
                </div>
                <div class="field">
                    <p class="control has-icons-left">
                        <input class="input" type="number" placeholder="Enter ID Card Number"
                            v-model="newIDCardNumber" />
                        <span class="icon is-small is-left">
                            <i class="mdi mdi-account-card mdi-dark"></i>
                        </span>
                    </p>
                </div>
                <div class="control pb-3">
                    <div class="select is-fullwidth">
                        <select placeholder="Select Role" v-model="newRole">
                            <option value="EMPLOYEE">EMPLOYEE</option>
                            <option value="ADMIN">ADMIN</option>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <p class="control">
                        <button class="btn button is-primary" @click="signupUser">
                            Signup
                        </button>
                    </p>
                </div>
                <div>
                    <a @click="signup = !signup" class="has-text-link is-underlined">Already have an account</a>
                </div>
            </div>
        </div>
    </div>

</template>

<style scoped>
.login-pg {
    min-height: 100vh;
    min-width: 100vw;
    margin: 0;
}

.login-form {
    width: fit-content;
    text-align: center;
}

.btn {
    width: 100%;
}
</style>