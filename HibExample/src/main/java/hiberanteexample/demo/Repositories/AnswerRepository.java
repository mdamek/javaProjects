package hiberanteexample.demo.Repositories;

import hiberanteexample.demo.Models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
