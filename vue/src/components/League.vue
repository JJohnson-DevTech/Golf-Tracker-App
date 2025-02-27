<template>
    <div class="filters">
        <div class="filter-box">
            <label class="league-search-label" for="leagueSearch">Search:</label>
            <input type="text" id="leagueSearch" class="league-search-box" />
        </div>
        <div class="league-label">
            <h3>League Name</h3>
        </div>
        <div class="league-label">
            <h3>Course</h3>
        </div>
        <div class="league-label">
            <h3>Members</h3>
        </div>
        <ul v-if="leagues.length > 0"    class="leagues-list">
                <li  v-for="league in leagues" :key="league.id">
                    <RouterLink class="league-box" :to="{ name: 'LeaderBoard' }"  v-on:click="goToLeaderBoardPage" >{{league.leagueName}}</RouterLink>
                    <div class="course-box">{{league.courseName}}</div>
                    <div class="members-box">{{league.minPlayers}}</div>
                </li>
            </ul>
    </div>
</template>

<script>
import { RouterLink } from 'vue-router';

export default {
    props: {
        leagues: {
            type: Array,
            required: true,
        },
        filters: {
            type: Object,
            required: true
        }
    },
    methods: {
        goToLeaderBoardPage() {
            this.$router.push({ name: 'LeaderBoard' });
        }
    },  
};
</script>

<style scoped>

h3 {
    margin: 0;
}
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
    font-weight: 400;
    display: flex;
    align-items: flex-end;

}

#leagueSearch {
    width: 75%;
}
</style>