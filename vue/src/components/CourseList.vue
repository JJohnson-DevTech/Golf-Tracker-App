<template>
    <div class="course-search-filter">
        <div class="filter-label-and-box">
            <label for="clubName" class="search-label">Club Name:</label>
            <input type="text" id="clubName" class="search-box" v-model="filters.club_name" @input="updateFilters" />
        </div>
        <div class="filter-label-and-box">
            <label for="courseName" class="search-label">Course Name:</label>
            <input type="text" id="courseName" class="search-box" v-model="filters.course_name" @input="updateFilters" />
        </div>
        <div class="filter-label-and-box">
            <label for="city" class="search-label">City:</label>
            <input type="text" id="city" class="search-box" v-model="filters.city" @input="updateFilters" />
        </div>
        <div class="filter-label-and-box">
            <label id="state-label" for="state" class="search-label">State:</label>
            <input type="text" id="state" class="search-box" v-model="filters.state" @input="updateFilters" />
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
.course-search-filter {
    display: flex;
    justify-content: space-between;
    align-items: center;
    flex-wrap: wrap;
}

.search-label {
    font-family: 'Sriracha', serif;
    font-size: 1.1rem;
    font-weight: 400;
    font-style: normal;
}

.filter-label-and-box {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    flex: 1;
    max-width: 200px; /* Set a maximum width */
    margin: 5px;
}

.search-box {
    padding: 5px;
    border: none;
    border-radius: 4px;
    width: 100%;
    box-sizing: border-box;
}

#state {
    width: 40%;
}

@media (max-width: 768px) {
    .course-search-filter {
        flex-direction: row;
        flex-wrap: wrap;
    }

    .filter-label-and-box {
        width: 100%;
        margin-bottom: 5px;
    }
}
</style>