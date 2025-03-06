<template>
    <div>
        <div>
            <AddCourse/>
        </div>
        <div class="nav-back">
            <router-link id="back" to="/courses">Back</router-link>
        </div>
    </div>
</template>

<script>

import AddCourse from '@/components/AddCourse.vue';

export default {
    components: {
        AddCourse
    }
};
</script>

<style scoped>
.nav-back {
    position: absolute;
    bottom: 80px;
}

#back {
    font-size: 55px;
    font-family: 'Fugaz One', serif;
    color: #fcf400;
    text-decoration: none; /* Ensure no underline */
}

#back:hover {
    color: #Ed0030; /* Maintain yellow color on hover */
}
</style>