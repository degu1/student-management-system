package se.iths.service;

import se.iths.entity.Subject;
import se.iths.exception.IdNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService implements Service<Subject> {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void create(Subject subject) {
        entityManager.persist(subject);
    }

    @Override
    public void updateWithPUT(Subject subject) {
        entityManager.merge(subject);
    }

    @Override
    public void updateWithPATCH(Subject subject) throws IdNotFoundException {
        Subject oldSubject = entityManager.find(Subject.class, subject.getId());

        if (oldSubject == null) {
            throw new IdNotFoundException("Subject with id " + subject.getId() + " not found.");
        }
        if (notNull(subject.getName())) {
            oldSubject.setName(subject.getName());
        }
        entityManager.merge(oldSubject);
    }

    @Override
    public List<Subject> getAll() {
        return entityManager.createQuery("SELECT s from Subject s", Subject.class).getResultList();
    }

    @Override
    public Subject getById(Long id) throws IdNotFoundException {
        Subject subject = entityManager.find(Subject.class, id);
        if (subject == null) {
            throw new IdNotFoundException("Subject with id " + id + " not found.");
        }
        return subject;
    }

    @Override
    public void remove(Long id) throws IdNotFoundException {
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
