import axios from "axios";

const INSTRUCTOR_API_URL = `http://localhost:8080/instructors/`;

class CourseDataService {
    retrieveAllCourses(instructor) {
        let restUrl = `${INSTRUCTOR_API_URL}${instructor}/courses`;
        return axios.get(restUrl);
    }

    deleteCourse(instructor, id) {
        return axios.delete(`${INSTRUCTOR_API_URL}${instructor}/courses/${id}`);
    }
}

export default new CourseDataService();