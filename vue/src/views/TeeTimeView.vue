<template>
    <div class="tee-time-container">
        <div class="tee-time-image"></div>

        <div class="content">
            <div class="tee-time-header">
                <h1>Upcoming Tee Times</h1>

                <router-link to="/teetimes/add" class="create-tee-time-button">Create Tee Time</router-link>
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
    methods: {
        async fetchTeeTimes() {
            try {
                const response = await APIService.getTeeTimeByUserId(this.$store.state.user.id);
                console.log('Fetched tee times', response.data);
                if (Array.isArray(response.data)) {
                    this.teeTimes = response.data;
                }
            } catch (error) {
                console.error('Error fetching tee times', error);
            }
        },
        
    },
    created() {
        this.fetchTeeTimes();
    }
};
</script>

<style scoped>
.tee-time-container {
    position: relative;
    width: 100%;
    height: 100%;
}

.tee-time-image {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: -1;
    background: url('@/assets/GolfHole3.png') no-repeat center center;
    background-size: cover;
    
}

.content {
    position: relative;
    z-index: 1;
}

h1 {
    font-family: 'Fugaz One', serif;
    text-align: center;
    font-size: 40px;
    color: #fcf400;
    border: 3px solid #005e23;
    padding: 5px;
    margin-top: 5%;
    width: 25%;
}

.tee-time-header {
    display: flex;
    justify-content: space-between;
    align-items: baseline;
}

.create-tee-time-button {
    font-family: 'Fugaz One', serif;
    font-size: 22px;
    text-align: center;
    background-color: #fcf400;
    color: #005e23;
    padding: 10px;
    text-decoration: none;
    border-radius: 10px;
    width: 12%;
}

.create-tee-time-button:hover {
    background-color: #005e23;
    color: #fcf400;
}

</style>