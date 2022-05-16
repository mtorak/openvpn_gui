<template>
  <div class="container">
    <h3>All Courses</h3>
    <div class="container">
      <table class="table">
        <thead>
          <tr>
            <th>Id</th>
            <th>Description</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="course in courses" v-bind:key="course.id">
            <td>{{course.id}}</td>
            <td>{{course.description}}</td>
            <td>
                <button class="btn btn-warning" v-on:click="deleteCourseClicked(course.id)">
                    Delete
                </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>


<script>
import CourseDataService from '../service/CourseDataService';

export default {
  name: "CoursesList",
  data() {
    return {
      courses: [],
      message: null,
      INSTRUCTOR: "in28minutes"
    };
  },
  methods: {
    refreshCourses() {
      CourseDataService.retrieveAllCourses(this.INSTRUCTOR) 
        .then(response => {
          this.courses = response.data;
        });
    },
    deleteCourseClicked(id) {
        CourseDataService.deleteCourse(this.INSTRUCTOR, id).then(response => {
            this.message = `Delete of course ${id} successful`;
            this.refreshCourses();
            console.log(response);
        });
    }
  },
  created() {
    this.refreshCourses();
  }
};
</script>

<style>
</style>