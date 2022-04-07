package se.iths.service;

import se.iths.entity.Subject;
import se.iths.exception.IdNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager entityManager;

    public void createSubject(Subject subject) {
        entityManager.persist(subject);
    }

    public void updateSubject(Subject subject) {
        entityManager.merge(subject);
    }

    public void patchSubject(Subject subjectUpdate) throws IdNotFoundException {
        Subject subject = entityManager.find(Subject.class, subjectUpdate.getId());

        if (subject == null) {
            throw new IdNotFoundException("Subject with id " + subjectUpdate.getId() + " not found.");
        }
        if (notNull(subjectUpdate.getName())) {
            subject.setName(subjectUpdate.getName());
        }
        /*
        if (notNull(subjectUpdate.getStudents())) {
            subject.setStudents(subjectUpdate.getStudents());
        }
        if (notNull(subjectUpdate.getTeacher())) {
            subject.setTeacher(subjectUpdate.getTeacher());
        }
         */
        entityManager.merge(subject);
    }

    public List<Subject> getAllSubjects() {
        return entityManager.createQuery("SELECT s from Subject s", Subject.class).getResultList();
    }

    public Subject getSubjectsById(Long id) throws IdNotFoundException {
        Subject subject = entityManager.find(Subject.class, id);
        if (subject == null) {
            throw new IdNotFoundException("Subject with id " + id + " not found.");
        }
        return subject;
    }

    public void deleteSubject(Long id) throws IdNotFoundException {
        Subject subject = entityManager.find(Subject.class, id);
        if (subject == null) {
            throw new IdNotFoundException("Subject with id " + id + " not found.");
        }

        entityManager.remove(subject);
    }

    private <T> boolean notNull(T object) {
        return object != null;
    }
}
