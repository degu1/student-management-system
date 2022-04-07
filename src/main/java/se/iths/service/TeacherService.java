package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exception.IdNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService {

    @PersistenceContext
    EntityManager entityManager;

    public void createTeacher(Teacher teacher) {
        entityManager.persist(teacher);
    }

    public void updateTeacher(Teacher teacher) {
        entityManager.merge(teacher);
    }

    public void patchTeacher(Teacher teacherUpdate) throws IdNotFoundException {
        Teacher teacher = entityManager.find(Teacher.class, teacherUpdate.getId());

        if (teacher == null) {
            throw new IdNotFoundException("Teacher with id " + teacherUpdate.getId() + " not found.");
        }

        if (notNull(teacherUpdate.getName())) {
            teacher.setName(teacherUpdate.getName());
        }
        /*
        if (notNull(teacherUpdate.subjects()) && !teacherUpdate.subjects().equals(teacher.subjects())) {
            teacher.removeAllSubjects();
            teacherUpdate.subjects().forEach(s -> teacher.addSubject(s));
        }
         */
        entityManager.merge(teacher);
    }

    public List<Teacher> getAllTeacher() {
        return entityManager.createQuery("SELECT s from Teacher s", Teacher.class).getResultList();
    }

    public Teacher getTeacherById(Long id) throws IdNotFoundException {
        Teacher teacher = entityManager.find(Teacher.class, id);
        if (teacher == null) {
            throw new IdNotFoundException("Teacher with id " + id + " not found.");
        }
        return teacher;
    }

    public void deleteTeacher(Long id) throws IdNotFoundException {
        Teacher teacher = entityManager.find(Teacher.class, id);
        if (teacher == null) {
            throw new IdNotFoundException("Teacher with id " + id + " not found.");
        }

        teacher.subjects()
                .forEach(Subject::removeTeacher);
        entityManager.remove(teacher);
    }

    public void addSubject(Long teacherId, Long subjectId) throws IdNotFoundException {
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        Subject subject = entityManager.find(Subject.class, subjectId);
        if (teacher == null) {
            throw new IdNotFoundException("Teacher with id " + teacherId + " not found.");
        }
        if (subject == null) {
            throw new IdNotFoundException("Subject with id " + subjectId + " not found.");
        }
        subject.setTeacher(teacher);
    }

    public void removeSubject(Long subjectId) throws IdNotFoundException {
        Subject subject = entityManager.find(Subject.class, subjectId);

        if (subject == null) {
            throw new IdNotFoundException("Subject with id " + subjectId + " not found.");
        }
        subject.removeTeacher();
    }


    private <T> boolean notNull(T object) {
        return object != null;
    }
}
