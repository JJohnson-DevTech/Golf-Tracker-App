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
    font-family: 'Fugaz One', serif;
    color: #fcf400;
    text-decoration: none; 
}

#back:hover {
    color: #Ed0030;
}
</style>