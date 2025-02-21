<template>
    <div class="filters">
        <div class="filter-box">
            <label for="clubName">Club Name:</label>
            <input type="text" id="clubName" v-model="filters.club_name" @input="updateFilters" class="input-box" />
        </div>
        <div class="filter-box">
            <label for="courseName">Course Name:</label>
            <input type="text" id="courseName" v-model="filters.course_name" @input="updateFilters" class="input-box" />
        </div>
        <div class="filter-box">
            <label for="city" class="city-label">City:</label>
            <input type="text" id="city" v-model="filters.city" @input="updateFilters" class="input-box small-input" />
        </div>
        <div class="filter-box state-box">
            <label class="state-label" for="state">State:</label>
            <input type="text" id="state" v-model="filters.state" @input="updateFilters" class="input-box small-input" />
        </div>
    </div>
</template>

<script>
export default {
    props: {
        filters: {
            type: Object,
            required: true
        }
    },
    methods: {
        updateFilters() {
            this.$emit('update-filters', { ...this.filters });
        }
    }
};
</script>

<style scoped>
.filters {
    display: grid;
    grid-template-columns: repeat(4, 1fr);
    gap: 25px;
    margin-left: -7px;
    width: 100%;
    box-sizing: border-box;
}

.filter-box {
    display: flex;
    flex-direction: column;
    max-width: fit-content;
}

.state-box .state-label {
    max-width: fit-content;
    margin-left: -30px;
}

.filters label {
    margin-bottom: 5px;
    font-family: 'Sriracha', serif;
    font-weight: 400;
    font-style: normal;
}

.filters input {
    width: 100%;
    box-sizing: border-box;
    margin-bottom: 7px;
}

#city {
    width: 75%;
}

#state {
    width: 25%;
    margin-left: -20px;
}

;
</style>