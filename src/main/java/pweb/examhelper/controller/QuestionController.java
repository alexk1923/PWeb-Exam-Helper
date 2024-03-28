package pweb.examhelper.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pweb.examhelper.dto.question.QuestionCreationDTO;
import pweb.examhelper.dto.question.QuestionDTO;
import pweb.examhelper.dto.question.QuestionUpdateDTO;
import pweb.examhelper.response.JsonResponse;
import pweb.examhelper.service.QuestionService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private QuestionService questionService;

    @GetMapping
    public List<QuestionDTO> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @PostMapping()
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionCreationDTO questionCreationDTO) {
        QuestionDTO savedQuestion = questionService.createQuestion(questionCreationDTO);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable("id") Long id) {
        QuestionDTO questionDTO = questionService.getQuestion(id);
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable("id") Long id,
                                                    @RequestBody QuestionUpdateDTO updateQuestionDTO) {
        questionService.updateQuestion(id, updateQuestionDTO);
        QuestionDTO afterUpdateQuestionDTO = questionService.getQuestion(id);
        return new ResponseEntity<>(afterUpdateQuestionDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<JsonResponse> deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
        JsonResponse response = new JsonResponse("Question with id " + id + " deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
