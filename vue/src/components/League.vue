<template>
    <div class="filters">
        
        <div class="league-label">
            <h3>League Name</h3>
        </div>
        <div class="league-label">
            <h3>Course</h3>
        </div>
        <div class="league-label">
            <h3>Members</h3>
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
    }
};
</script>

<style scoped>

.filters {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
    gap: 25px;
    width: 100%;
    box-sizing: border-box;
    align-items: flex-end;
}

.filter-box {
    display: flex;
    flex-direction: column;
    justify-content: flex-end;
}

.filters label {
    margin-bottom: 5px;
    font-family: 'Sriracha', serif;
    font-weight: 400;
}

.filters input {
    width: 100%;
    box-sizing: border-box;
    margin-bottom: 7px;
}

.league-label {
    font-family: 'Sriracha', serif;
    font-size: 40px;
    margin-top: 20%;
    display: flex;
    align-items: flex-end;
}

</style>