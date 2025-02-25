<template>
    <div class="filters">
        <div class="filter-box">
            <label for="clubName">Club Name:</label>
            <input type="text" id="clubName" v-model="filters.club_name" @input="updateFilters" class="input-box" />
        </div>
        <div class="filter-box">
            <label for="courseName" class="course-label">Course Name:</label>
            <input type="text" id="courseName" v-model="filters.course_name" @input="updateFilters" class="input-box" />
        </div>
        <div class="filter-box">
            <label for="city" class="city-label">City:</label>
            <input type="text" id="city" v-model="filters.city" @input="updateFilters" class="input-box" />
        </div>
        <div class="filter-box state-box">
            <label class="state-label" for="state">State:</label>
            <input type="text" id="state" v-model="filters.state" @input="updateFilters" class="input-box" />
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

.course-label {
    margin-left: 30px;
}

#courseName {
    width: 90%;
    margin-left: 30px;
}

.state-box {
    grid-column: 4 / 5; /* Ensure the state box is in the fourth column */
    grid-row: 1; /* Ensure the state box is in the first row */
}

.state-box .state-label {
    max-width: fit-content;
    margin-left: 35%;
}

.city-label {
    margin-left: 50px;
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
    width: 70%;
    margin-left: 50px;
}

#state {
    width: 25%;
    margin-left: 80px; 
}

/* Media queries for responsive design */
@media (max-width: 768px) {
    .filters {
        grid-template-columns: repeat(2, 1fr);
    }

    .course-label, .city-label, .state-label {
        margin-left: 0;
    }

    #courseName, #city, #state {
        width: 100%;
        margin-left: 0;
    }

    .filters label {
        font-size: 0.8rem;
    }

    .filters input {
        font-size: 0.8rem;
    }
}

@media (max-width: 480px) {
    .filters {
        grid-template-columns: 1fr;
    }

    .filters label {
        font-size: 0.7rem;
    }

    .filters input {
        font-size: 0.7rem;
    }
}
</style>