package ru.hogwarts.school.service;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import java.util.Collection;
import java.util.HashMap;
@Service
public class FacultyService {
    private final HashMap<Long, Faculty> faculties = new HashMap<>();
    private long count = 0;
    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++count);
        return faculties.put(count, faculty);

    }
    public Faculty getFaculty(long id) {
        return faculties.get(id);
    }
    public Faculty updateFaculty(Faculty faculty) {
        if (faculties.containsKey(faculty.getId())) {
            faculties.put(faculty.getId(), faculty);
            return faculty;
        }
        return null;
    }

    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);

    }
    public Collection<Faculty> getAllFaculties(){
        return faculties.values();
    }

    @Override
    public String toString() {
        return "FacultyService{" +
                "faculties=" + faculties +
                ", count=" + count +
                '}';
    }


}
