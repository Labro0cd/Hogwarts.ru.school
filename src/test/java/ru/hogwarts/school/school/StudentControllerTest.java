package ru.hogwarts.school.school;


import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwarts.school.school.Controller.StudentController;
import ru.hogwarts.school.school.Service.StudentService;
import ru.hogwarts.school.school.entity.Student;

import ru.hogwarts.school.school.repository.StudentRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private StudentService studentService;


    @Test
    void testControllerMethod() throws Exception {
        // Настройка поведения мока репозитория
        when(studentRepository.findById(1L)).thenReturn(Optional.of(new Student()));
    }

    @Test
    public void saveStudentTest() throws Exception {

        // Подготовка тестовых данных
        long id = 1L; // Уникальный идентификатор студента
        String name = "Bob"; // Имя студента
        int age = 11; // Возраст студента

        // Создание JSON-объекта, который будет отправлен в запросе
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name); // Добавляем имя в JSON
        studentObject.put("age", age); // Добавляем возраст в JSON


        // Создание объекта Student, который будет возвращен репозиторием
        Student student = new Student();
        student.setId(id); // Устанавливаем идентификатор
        student.setName(name); // Устанавливаем имя
        student.setAge(age); // Устанавливаем возраст

        // Настройка mock-репозитория
        when(studentRepository.save(any(Student.class))).thenReturn(student); // Когда вызывается метод save, возвращаем созданного студента
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student)); // Когда вызывается метод findById, возвращаем созданного студента
        // Выполнение HTTP-запроса и проверка результата
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/students") // Отправляем POST-запрос на эндпоинт /students
                        .content(studentObject.toString()) // Устанавливаем тело запроса (JSON)
                        .contentType(MediaType.APPLICATION_JSON) // Указываем тип содержимого (JSON)
                        .accept(MediaType.APPLICATION_JSON)) // Указываем, что ожидаем JSON в ответе
                .andExpect(status().isOk()) // Проверяем, что статус ответа 200 OK
                .andExpect(jsonPath("$.id").value(id)) // Проверяем, что в ответе поле "id" равно ожидаемому значению
                .andExpect(jsonPath("$.name").value(name)) // Проверяем, что в ответе поле "name" равно ожидаемому значению
                .andExpect(jsonPath("$.age").value(age)); // Проверяем, что в ответе поле "age" равно ожидаемому значению

    }

    @Test
    public void findStudentTest() throws Exception {

        // Подготовка тестовых данных
        long id = 1L; // Уникальный идентификатор студента
        String name = "Bob"; // Имя студента
        int age = 11; // Возраст студента


        // Создание объекта Student, который будет возвращен репозиторием
        Student student = new Student();
        student.setId(id); // Устанавливаем идентификатор
        student.setName(name); // Устанавливаем имя
        student.setAge(age); // Устанавливаем возраст

        // Настройка mock-репозитория
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));// Когда вызывается метод findById, возвращаем созданного студента
        // Выполнение HTTP-запроса и проверка результата
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/" + id) // Отправляем GET-запрос на эндпоинт /students/{id}
                        .contentType(MediaType.APPLICATION_JSON) // Указываем тип содержимого (JSON)
                        .accept(MediaType.APPLICATION_JSON)) // Указываем, что ожидаем JSON в ответе
                .andExpect(status().isOk()) // Проверяем, что статус ответа 200 OK
                .andExpect(jsonPath("$.id").value(id)) // Проверяем, что в ответе поле "id" равно ожидаемому значению
                .andExpect(jsonPath("$.name").value(name)) // Проверяем, что в ответе поле "name" равно ожидаемому значению
                .andExpect(jsonPath("$.age").value(age));// Проверяем, что в ответе поле "age" равно ожидаемому значению
    }

    @Test
    public void NotFoundStudent() throws Exception{
        long id = 999L;

        when(studentRepository.findById(eq(id))).thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/students/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteStudentTest() throws Exception {

        // Подготовка тестовых данных
        long id = 1L; // Уникальный идентификатор студента
        String name = "Bob"; // Имя студента
        int age = 11; // Возраст студента


        // Создание объекта Student, который будет возвращен репозиторием
        Student student = new Student();
        student.setId(id); // Устанавливаем идентификатор
        student.setName(name); // Устанавливаем имя
        student.setAge(age); // Устанавливаем возраст

        // Настройка mock-репозитория
        when(studentRepository.save(any(Student.class))).thenReturn(student); // Когда вызывается метод save, возвращаем созданного студента
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));// Когда вызывается метод findById, возвращаем созданного студента
        // Выполнение HTTP-запроса и проверка результата
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/students/" + id) // Отправляем GET-запрос на эндпоинт /students
                        .accept(MediaType.APPLICATION_JSON)) // Указываем, что ожидаем JSON в ответе
                .andExpect(status().isOk()); // Проверяем, что статус ответа 200 OK
    }

}
