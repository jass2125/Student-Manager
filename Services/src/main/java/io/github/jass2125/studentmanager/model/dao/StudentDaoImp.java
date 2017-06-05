/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.jass2125.studentmanager.model.dao;

import io.github.jass2125.studentmanager.beans.StudentDao;
import io.github.jass2125.studentmanager.model.entity.Student;
import io.github.jass2125.studentmanager.model.exceptions.PersistenceException;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Anderson Souza
 */
@Stateless
@Remote(StudentDao.class)
public class StudentDaoImp implements StudentDao {

    @PersistenceContext
    private EntityManager em;

    public StudentDaoImp() {
    }

    @Override
    public Student save(Student student) throws PersistenceException {
        try {
            em.persist(student);
            return student;
        } catch (Exception e) {
            throw new PersistenceException(e, "Verifique os dados e tente novamente.");
        }
    }

    @Override
    public Student getById(Long id) {
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = em.createQuery("SELECT S FROM Student S", Student.class);
        List<Student> resultList = query.getResultList();
        resultList.forEach(t -> {
            System.out.println(t);
        });
        return resultList;
    }

    @Override
    public Student delete(Long id) {
        Student student = getById(id);
        try {
            em.getTransaction().begin();
            em.remove(em.find(Student.class, id));
            em.getTransaction().commit();
            return student;
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
        return null;
    }

    @Override
    public Student update(Student student) {
        try {
            em.merge(student);
            return student;
        } catch (Exception e) {
            return null;
        }
    }
}
