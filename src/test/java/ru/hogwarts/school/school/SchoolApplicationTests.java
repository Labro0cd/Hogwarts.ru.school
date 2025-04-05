package ru.hogwarts.school.school;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import ru.hogwarts.school.school.Controller.FacultyController;
import ru.hogwarts.school.school.Controller.StudentController;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private StudentController studentController;

	@Autowired
	private FacultyController facultyController;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception{
		Assertions.assertThat(studentController).isNotNull();
	}

	@Test
	public void testGetStudent() throws Exception {
		Assertions.assertThat(this.restTemplate
				.getForObject("http://localhost:"+port+"/students/1", String.class)).isNotNull();
	}

	/*@Test
	public void testPostStudent() {
		Student student = new Student(1,"Kirill", 12);

		Assertions.assertThat(this.restTemplate
				.postForObject("http://localhost:"+port+"/students",student, String.class))
				.isNotNull();
	}*/
}
