<template>
    <div class="user-profile">
        <div v-if="user"></div>
            <h1> Welcome, {{ user.username }}</h1>
                <section>My Stats</section>
                    <p>Handicap: </p>
                <section>My Leagues</section>
                <section>My Favorite Courses</section>
        
            
        </div>
</template>