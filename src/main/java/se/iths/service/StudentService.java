package se.iths.service;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.exception.ConnectedException;
import se.iths.exception.IdNotFoundException;
import se.iths.exception.NoConnectionWithEntityException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService implements Service<Student> {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void create(Student student) {
        entityManager.persist(student);
    }

    @Override
    public void updateWithPUT(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void updateWithPATCH(Student studentUpdate) throws IdNotFoundException {
        Student student = entityManager.find(Student.class, studentUpdate.getId());

        if (student == null) {
            throw new IdNotFoundException("Student with id " + studentUpdate.getId() + " not found.");
        }

        if (notNull(studentUpdate.getFirstName())) {
            student.setFirstName(studentUpdate.getFirstName());
        }
        if (notNull(studentUpdate.getLastName())) {
            student.setLastName(studentUpdate.getLastName());
        }
        if (notNull(studentUpdate.getEmail())) {
            student.setEmail(studentUpdate.getEmail());
        }
        if (notNull(studentUpdate.getPhoneNumber())) {
            student.setPhoneNumber(studentUpdate.getPhoneNumber());
        }
        entityManager.merge(student);
    }

    @Override
    public List<Student> getAll() {
        return entityManager.createQuery("SELECT s from Student s", Student.class).getResultList();
    }

    @Override
    public Student getById(Long id) throws IdNotFoundException {
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            throw new IdNotFoundException("Student with id " + id + " not found.");
        }
        return student;
    }

    @Override
    public void remove(Long id) throws IdNotFoundException {
        Student student = entityManager.find(Student.class, id);
        if (student == null) {
            throw new IdNotFoundException("Student with id " + id + " not found.");
        }

        student.removeAllSubjects();
        entityManager.remove(student);
    }


    public List<Student> getStudentsByLastName(String lastName) {
        return entityManager.createNamedQuery("student.getAllByLastName", Student.class).setParameter("lastName", lastName).getResultList();
    }

    public void addSubject(Long studentId, Long subjectId) throws IdNotFoundException, ConnectedException {
        Student student = entityManager.find(Student.class, studentId);
        Subject subject = entityManager.find(Subject.class, subjectId);
        if (student == null) {
            throw new IdNotFoundException("Student with id " + studentId + " not found.");
        }
        if (subject == null) {
            throw new IdNotFoundException("Subject with id " + subjectId + " not found.");
        }
        if (student.subjects().contains(subject)) {
            throw new ConnectedException("Student is already connected with subject with id " + subjectId);
        }
        student.addSubject(subject);
    }

    public void removeSubject(Long studentId, Long subjectId) throws IdNotFoundException, NoConnectionWithEntityException {
        Student student = entityManager.find(Student.class, studentId);
        Subject subject = entityManager.find(Subject.class, subjectId);
        if (student == null) {
            throw new IdNotFoundException("Student with id " + studentId + " not found.");
        }
        if (!student.subjects().contains(subject)) {
            throw new NoConnectionWithEntityException("Student don't contain subject with id " + subjectId);
        }
        if (subject == null) {
            throw new IdNotFoundException("Subject with id " + subjectId + " not found.");
        }
        student.removeSubject(subject);
    }


    private <T> boolean notNull(T object) {
        return object != null;
    }


}
