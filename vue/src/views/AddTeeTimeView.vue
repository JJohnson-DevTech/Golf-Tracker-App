<template>
    <div>
        <AddTeeTime />
    </div>
    <div class="nav-back">
        <router-link id="back" to="/tee-times">Back</router-link>
    </div>
</template>

<script>
import AddTeeTime from '../components/AddTeeTime.vue';

export default {
    components: {
    AddTeeTime
}
};
</script>

<style scoped>
#back {
    margin-top: 20px;
    font-family: 'Fugaz One', serif;
    font-weight: 400;
    font-style: normal;
    color: #fcf400;
    text-decoration: none; /* Ensure no underline */
}

#back:hover {
    color: #Ed0030; /* Maintain yellow color on hover */
}
</style>