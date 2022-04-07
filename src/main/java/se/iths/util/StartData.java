package se.iths.util;

import se.iths.entity.Student;
import se.iths.entity.Subject;
import se.iths.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class StartData {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void generateData() {
        Student student1 = new Student("Bullen", "Bullenson", "bullen@mail.com", "123");
        Student student2 = new Student("Dennis", "Gustafsson", "dennis@mail.com", "123");
        entityManager.persist(student1);
        entityManager.persist(student2);

        Subject subject = new Subject("Java");
        subject.setTeacher(new Teacher("Teacher"));
        entityManager.persist(subject);

        student1.addSubject(subject);
        student2.addSubject(subject);
    }
}
