<template>
    <div class="tee-time-container">
        <div class="tee-time-image"></div>

        <div class="content">
            <div class="tee-time-header">
                <h1>Upcoming Tee Times</h1>
            </div>
            <TeeTime :teeTimes="teeTimes" />
        </div>
    </div>
</template>

<script>
import TeeTime from '@/components/TeeTime.vue';
import APIService from '@/services/APIService.js';

export default {
    components: {
        TeeTime
    },
    data() {
        return {
            teeTimes: []
        };
    },
    mounted() {
        this.teeTimes = [
            {
                tee_time: '02-28-2025 10:00:00 AM',
                club_name: 'Pebble Beach Golf Club',
                course_name: 'Pebble Beach',
                total_players: 4,
                league_name: 'Pebble Beach League'
            },
            {
                tee_time: '04-14-2025 1:00:00 PM',
                club_name: 'Grand Traverse Resort',
                course_name: 'The Bear',
                total_players: 4,
                league_name: 'Monday Night League'
            },
        ];
    }
};
</script>

<style scoped>
.tee-time-container {
    position: relative;
    width: 100%;
    height: 100vh;
    overflow: hidden;
}

.tee-time-image {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
    background-image: url('@/assets/GolfHole3.png');
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
}

.content {
    position: relative;
    z-index: 1;
}

h1 {
    font-family: 'Fugaz One', serif;
    font-weight: 400;
    font-style: normal;
    color: #fcf400;
    margin-bottom: 4px;
    border: none;
}
</style>