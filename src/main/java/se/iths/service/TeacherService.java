package se.iths.service;

import se.iths.entity.Subject;
import se.iths.entity.Teacher;
import se.iths.exception.ConnectedException;
import se.iths.exception.IdNotFoundException;
import se.iths.exception.NoConnectionWithEntityException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class TeacherService implements Service<Teacher> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Teacher teacher) {
        entityManager.persist(teacher);
    }

    @Override
    public void updateWithPUT(Teacher teacher) {
        entityManager.merge(teacher);
    }

    @Override
    public void updateWithPATCH(Teacher teacher) throws IdNotFoundException {
        Teacher oldTeacher = entityManager.find(Teacher.class, teacher.getId());

        if (oldTeacher == null) {
            throw new IdNotFoundException("Teacher with id " + teacher.getId() + " not found.");
        }

        if (notNull(teacher.getName())) {
            oldTeacher.setName(teacher.getName());
        }
        entityManager.merge(oldTeacher);
    }

    @Override
    public List<Teacher> getAll() {
        return entityManager.createQuery("SELECT s from Teacher s", Teacher.class).getResultList();
    }

    @Override
    public Teacher getById(Long id) throws IdNotFoundException {
        Teacher teacher = entityManager.find(Teacher.class, id);
        if (teacher == null) {
            throw new IdNotFoundException("Teacher with id " + id + " not found.");
        }
        return teacher;
    }

    @Override
    public void remove(Long id) throws IdNotFoundException {
        Teacher teacher = entityManager.find(Teacher.class, id);
        if (teacher == null) {
            throw new IdNotFoundException("Teacher with id " + id + " not found.");
        }
        teacher.subjects()
                .forEach(Subject::removeTeacher);
        entityManager.remove(teacher);
    }

    public void addSubject(Long teacherId, Long subjectId) throws IdNotFoundException, ConnectedException {
        Teacher teacher = entityManager.find(Teacher.class, teacherId);
        Subject subject = entityManager.find(Subject.class, subjectId);
        if (teacher == null) {
            throw new IdNotFoundException("Teacher with id " + teacherId + " not found.");
        }
        if (subject == null) {
            throw new IdNotFoundException("Subject with id " + subjectId + " not found.");
        }
        if (subject.getTeacher() == null) {
            subject.setTeacher(teacher);
        } else {
            throw new ConnectedException("Teacher is already connected");
        }

    }

    public void removeSubject(Long subjectId) throws IdNotFoundException, NoConnectionWithEntityException {
        Subject subject = entityManager.find(Subject.class, subjectId);

        if (subject == null) {
            throw new IdNotFoundException("Subject with id " + subjectId + " not found.");
        }
        if (subject.getTeacher() == null) {
            throw new NoConnectionWithEntityException("Teacher and subject not connected");
        }
        subject.removeTeacher();
    }

    private <T> boolean notNull(T object) {
        return object != null;
    }
}
