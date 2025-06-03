package org.example;

import org.example.model.*;
import org.example.repository.base.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.lang.module.Configuration;
import java.util.*;

public class ConsoleMenuApp {

    private static SessionFactory sessionFactory;

    private static UserRepositoryImpl userRepository;
    private static StudentRepositoryImpl studentRepository;
    private static ExamRepositoryImpl examRepository;
    private static StudentExamRepositoryImpl studentExamRepository;
    private static GradingRepositoryImpl gradingRepository;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize Hibernate SessionFactory from hibernate.cfg.xml (adjust config as needed)
        sessionFactory = new Configuration().configure().buildSessionFactory();

        // Initialize repositories
        userRepository = new UserRepositoryImpl(sessionFactory);
        studentRepository = new StudentRepositoryImpl(sessionFactory);
        examRepository = new ExamRepositoryImpl(sessionFactory);
        studentExamRepository = new StudentExamRepositoryImpl(sessionFactory);
        gradingRepository = new GradingRepositoryImpl(sessionFactory);

        // Main menu loop
        while (true) {
            System.out.println("1. login");
            System.out.println("2. Exit");
            String option = scanner.nextLine();

            if (option.equals("1")) {
                loginMenu();
            } else if (option.equals("2")) {
                System.out.println("Exiting...");
                scanner.close();
                sessionFactory.close();
                break;
            } else {
                System.out.println("Invalid option");
            }
        }
    }

    private static void loginMenu() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();

        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            System.out.println("User not found");
            return;
        }

        User user = userOpt.get();

        if (user.getRoles().stream().anyMatch(r -> r.getName().equalsIgnoreCase("ADMIN"))) {
            adminMenu(user);
        } else if (user.getRoles().stream().anyMatch(r -> r.getName().equalsIgnoreCase("STUDENT"))) {
            studentMenu(user);
        } else if (user.getRoles().stream().anyMatch(r -> r.getName().equalsIgnoreCase("MANAGER"))) {
            managerMenu(user);
        } else {
            System.out.println("User role not recognized");
        }
    }

    private static void adminMenu(User admin) {
        System.out.println("admin panel");
        while (true) {
            System.out.println("1. list all users");
            System.out.println("2. search users");
            System.out.println("3. logout");
            String option = scanner.nextLine();
            switch (option) {
                case "1" -> {
                    List<User> users = userRepository.findAll();
                    System.out.println("users");
                    users.forEach(u -> System.out.println("- " + u.getUsername() + " | " + u.getFullName()));
                }
                case "2" -> {
                    System.out.println("Enter keyword: ");
                    String kw = scanner.nextLine();
                    List<User> users = userRepository.searchUsers(kw);
                    if (users.isEmpty()) {
                        System.out.println("No user found");
                    } else {
                        users.forEach(u -> System.out.println("- " + u.getUsername() + " | " + u.getFullName()));
                    }
                }
                case "3" -> {
                    System.out.println("logout");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void studentMenu(User user) {
        System.out.println("student panel");
        Optional<Student> studentOpt = studentRepository.findByUsername(user.getUsername());
        if (studentOpt.isEmpty()) {
            System.out.println("student not found");
            return;
        }
        Student student = studentOpt.get();

        while (true) {
            System.out.println("1. list exams");
            System.out.println("2. completed exams");
            System.out.println("3. take exam");  // NEW option
            System.out.println("4. logout");
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> {
                    List<Exam> exams = examRepository.findAll();
                    exams.forEach(e -> System.out.println("- " + e.getId() + " | " + e.getName()));
                }
                case "2" -> {
                    List<Exam> completedExams = studentExamRepository.findCompletedExamsByStudentId(student.getId());
                    if (completedExams.isEmpty()) {
                        System.out.println("No completed exams");
                    } else {
                        for (Exam exam : completedExams) {
                            Double score = gradingRepository.getStudentScoreInExam(student.getId(), exam.getId());
                            System.out.println("exam: " + exam.getName() + ", score: " + (score == null ? 0 : score));
                        }
                    }
                }
                case "3" -> {
                    takeExam(student);
                }
                case "4" -> {
                    System.out.println("logout");
                    return;
                }
                default -> System.out.println("invalid option");
            }
        }
    }

    private static void takeExam(Student student) {
        List<Exam> exams = examRepository.findAll();
        System.out.println("Available exams:");
        for (Exam exam : exams) {
            System.out.println(exam.getId() + ": " + exam.getName());
        }
        System.out.print("Enter exam ID to take: ");
        String examIdStr = scanner.nextLine();

        Long examId;
        try {
            examId = Long.parseLong(examIdStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid exam ID.");
            return;
        }

        Optional<Exam> examOpt = examRepository.findById(examId);
        if (examOpt.isEmpty()) {
            System.out.println("Exam not found.");
            return;
        }
        Exam exam = examOpt.get();


        Optional<StudentExam> existing = studentExamRepository.findByStudentAndExam(student.getId(), examId);
        if (existing.isPresent() && existing.get().isSubmitted()) {
            System.out.println("You have already completed this exam.");
            return;
        }


        List<Question> questions = questionRepository.findByExamId(examId);
        if (questions.isEmpty()) {
            System.out.println("No questions found for this exam.");
            return;
        }

        Map<Long, String> studentAnswers = new HashMap<>();
        for (Question q : questions) {
            System.out.println("Q" + q.getId() + ": " + q.getText());

            List<String> options = q.getOptions();
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + ") " + options.get(i));
            }
            System.out.print("Your answer (number): ");
            String ans = scanner.nextLine();
            studentAnswers.put(q.getId(), ans);
        }


        StudentExam studentExam = existing.orElseGet(() -> new StudentExam(student, exam));
        studentExam.setSubmitted(true);
        studentExamRepository.save(studentExam);


        double score = gradeExam(questions, studentAnswers);


        Grading grading = new Grading();
        grading.setStudent(student);
        grading.setExam(exam);
        grading.setScore(score);
        gradingRepository.save(grading);

        System.out.println("Exam completed! Your score: " + score);
    }
    private static void managerMenu(User user) {
        System.out.println("manager panel");
        while (true) {
            System.out.println("1. show profile");
            System.out.println("2. logout");
            String option = scanner.nextLine();

            switch (option) {
                case "1" -> System.out.println("manager: " + user.getUsername() + " | " + user.getFullName());
                case "2" -> {
                    System.out.println("logout");
                    return;
                }
                default -> System.out.println("invalid option");
            }
        }
    }

    // ----------------------
    // Repository Implementations
    // ----------------------

    public static class UserRepositoryImpl {

        private final SessionFactory sessionFactory;

        public UserRepositoryImpl(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

        public void save(User user) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.persist(user);
                session.getTransaction().commit();
            }
        }

        public void update(User user) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.merge(user);
                session.getTransaction().commit();
            }
        }

        public Optional<User> findById(Long id) {
            try (Session session = sessionFactory.openSession()) {
                User user = session.get(User.class, id);
                return Optional.ofNullable(user);
            }
        }

        public Optional<User> findByUsername(String username) {
            try (Session session = sessionFactory.openSession()) {
                String hql = "FROM User u WHERE u.username = :username";
                Query<User> query = session.createQuery(hql, User.class);
                query.setParameter("username", username);
                User user = query.uniqueResult();
                return Optional.ofNullable(user);
            }
        }

        public List<User> findAll() {
            try (Session session = sessionFactory.openSession()) {
                String hql = "FROM User";
                return session.createQuery(hql, User.class).list();
            }
        }

        public List<User> searchUsers(String keyword) {
            try (Session session = sessionFactory.openSession()) {
                String hql = "FROM User u WHERE lower(u.username) LIKE :kw OR lower(u.fullName) LIKE :kw";
                Query<User> query = session.createQuery(hql, User.class);
                query.setParameter("kw", "%" + keyword.toLowerCase() + "%");
                return query.list();
            }
        }
    }

    public static class StudentRepositoryImpl {

        private final SessionFactory sessionFactory;

        public StudentRepositoryImpl(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

        public void save(Student student) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.persist(student);
                session.getTransaction().commit();
            }
        }

        public void update(Student student) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.merge(student);
                session.getTransaction().commit();
            }
        }

        public Optional<Student> findById(Long id) {
            try (Session session = sessionFactory.openSession()) {
                Student student = session.get(Student.class, id);
                return Optional.ofNullable(student);
            }
        }

        public Optional<Student> findByUsername(String username) {
            try (Session session = sessionFactory.openSession()) {
                String hql = "FROM Student s WHERE s.username = :username";
                Query<Student> query = session.createQuery(hql, Student.class);
                query.setParameter("username", username);
                Student student = query.uniqueResult();
                return Optional.ofNullable(student);
            }
        }

        public List<Student> findAll() {
            try (Session session = sessionFactory.openSession()) {
                String hql = "FROM Student";
                return session.createQuery(hql, Student.class).list();
            }
        }
    }

    public static class ExamRepositoryImpl {

        private final SessionFactory sessionFactory;

        public ExamRepositoryImpl(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

        public List<Exam> findAll() {
            try (Session session = sessionFactory.openSession()) {
                String hql = "FROM Exam";
                return session.createQuery(hql, Exam.class).list();
            }
        }
    }

    public static class StudentExamRepositoryImpl {

        private final SessionFactory sessionFactory;

        public StudentExamRepositoryImpl(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

        public void save(StudentExam studentExam) {
            try (Session session = sessionFactory.openSession()) {
                session.beginTransaction();
                session.persist(studentExam);
                session.getTransaction().commit();
            }
        }

        public Optional<StudentExam> findByStudentAndExam(Long studentId, Long examId) {
            try (Session session = sessionFactory.openSession()) {
                String hql = "SELECT se FROM StudentExam se WHERE se.student.id = :studentId AND se.exam.id = :examId";
                Query<StudentExam> query = session.createQuery(hql, StudentExam.class);
                query.setParameter("studentId", studentId);
                query.setParameter("examId", examId);
                List<StudentExam> results = query.list();
                if (results.isEmpty()) return Optional.empty();
                else return Optional.of(results.get(0));
            }
        }

        public List<Exam> findCompletedExamsByStudentId(Long studentId) {
            try (Session session = sessionFactory.openSession()) {
                String hql = "SELECT se.exam FROM StudentExam se WHERE se.student.id = :studentId AND se.submitted = true";
                Query<Exam> query = session.createQuery(hql, Exam.class);
                query.setParameter("studentId", studentId);
                return query.list();
            }
        }

    }

    public static class GradingRepositoryImpl {

        private final SessionFactory sessionFactory;

        public GradingRepositoryImpl(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
        }

        // Dummy implementation, replace with actual logic to get score from DB
        public Double getStudentScoreInExam(Long studentId, Long examId) {
            try (Session session = sessionFactory.openSession()) {
                String hql = "SELECT AVG(g.score) FROM Grading g WHERE g.student.id = :studentId AND g.exam.id = :examId";
                Query<Double> query = session.createQuery(hql, Double.class);
                query.setParameter("studentId", studentId);
                query.setParameter("examId", examId);
                Double score = query.uniqueResult();
                return score != null ? score : 0.0;
            } catch (Exception e) {
                e.printStackTrace();
                return 0.0;
            }
        }
    }

}