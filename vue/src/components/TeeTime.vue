<template>
    <table class="tee-time-table">
        <thead>
            <tr>
                <th>Tee Time</th>
                <th>Club</th>
                <th>Course</th>
                <th>Players</th>
                <th>League</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="teeTime in teeTimes" :key="teeTime.teeTimeId">
                <td>{{ new Date(teeTime.tee_time).toLocaleDateString() }} {{ new Date(teeTime.tee_time).toLocaleTimeString() }}</td>
                <td>{{ toTitleCase(teeTime.club_name) }}</td>
                <td>{{ teeTime.course_name }}</td>
                <td>{{ teeTime.num_players }}</td>
                <td>{{ teeTime.league_name }}</td>
            </tr>
        </tbody>
    </table>
</template>

<script>

export default {
    props: {
        teeTimes: {
            type: Array,
            required: true
        }
    },
    methods: {
        toTitleCase(text) {
            if (!text) return '';
            return text.replace(/\w\S*/g, (txt) => {
                return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();
            });
        },
    }
};
</script>

<style scoped>
.tee-time-table {
    width: 80%;
    max-width: 1000px;
    margin: 20px auto;
    border-collapse: collapse;
    background-color: rgba(255, 255, 255, 0.8);
}

.tee-time-table th,
.tee-time-table td {
    border: 1px solid #005e23;
    padding: 10px;
    text-align: center;
    font-family: 'Sriracha', serif;
}

.tee-time-table th {
    background-color: #fcf400;
    color: #005e23;
}

.tee-time-table td {
    background-color: #ffffff;
    color: #005e23;
}

.tee-time-table tr:nth-child(even) td {
    background-color: #f2f2f2;
}
</style>