<template>
    <div class="create-league-container">
        
    
        <div>
        <h1>Create League</h1>
        <CreateLeague />
        <form id="createLeague">
            <div class="form-input-group">
                <div class="admin">
                    <label for="host">League Administrator</label>
                    <input type="text" id="host" v-model="league.host" required autofocus />
                </div>
                <div class="league">
                    <label for="leagueName">League Name</label>
                    <input type="text" id="leagueName" v-model="league.leagueName" required autofocus />
                </div>
                <div class="course">
                    <label for="course">Course</label>
                    <input type="select" id="course" v-model="league.course" required autofocus />
                </div>
            </div>
            <div class="submit">
                <button type="submit" v-bind:to="{name: 'League'}">Create League</button>
            </div>
            <div class="link">
                <label for="generated-link">League Link</label>
                <input type="text" id="generated-link" v-model="league.link" required autofocus />
            </div>
        </form>
        </div>
    </div>
</template>