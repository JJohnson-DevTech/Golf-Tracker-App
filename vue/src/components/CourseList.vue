<template>
    <div class="course-search-filter">
        <div class="filter-label-and-box">
            <label for="clubName" class="search-label">Club Name</label>
            <input type="text" id="clubName" class="search-box" v-model="filters.club_name" @input="updateFilters" />
        </div>
        <div class="filter-label-and-box">
            <label for="courseName" class="search-label">Course Name</label>
            <input type="text" id="courseName" class="search-box" v-model="filters.course_name" @input="updateFilters" />
        </div>
        <div class="filter-label-and-box">
            <label for="city" class="search-label">City</label>
            <input type="text" id="city" class="search-box" v-model="filters.city" @input="updateFilters" />
        </div>
        <div class="filter-label-and-box">
            <label id="state-label" for="state" class="search-label">State</label>
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

.search-box {
    background-color: rgba(0, 94, 35, 0.7);
    color: #fcf400;
    border: 1px solid #fcf400; 
    border-radius: 10px;
    padding: 10px;
    font-size: 20px;
    outline: none;
    text-align: center;
}

.search-box:focus {
    border: 2px solid #fcf400;
    outline: 2px solid #fcf400;
    background-color: rgba(0, 94, 35, 0.9);
}

.search-box::placeholder {
    color: #fcf400;
    opacity: 0.7;
}

.course-search-filter {
    display: grid;
    grid-template-columns: 2fr 2fr 2fr 1fr;
    gap: 10px;
}

.search-label {
    font-family: 'Sriracha', serif;
    font-size: 2.5rem;
    font-style: normal;
}

.filter-label-and-box {
    display: flex;
    flex-direction: column;
    width: 60%;
    margin-top: 40px;
    margin-bottom: 20px;
}

.search-box {
    padding: 5px; 
    border-radius: 10px; 
}

#city {
    width: 50%;
}

#state {
    width: 30%;
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