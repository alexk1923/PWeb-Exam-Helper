package pweb.examhelper.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@Valid
public class QuestionController {
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<Page<QuestionDTO>> getAllQuestions(Pageable pageable) {
        return ResponseEntity.ok(questionService.getAllQuestions(pageable));
    }

    @PostMapping()
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionCreationDTO questionCreationDTO) {
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
                                                      @Valid @RequestBody QuestionUpdateDTO updateQuestionDTO) {
        questionService.updateQuestion(id, updateQuestionDTO);
        QuestionDTO afterUpdateQuestionDTO = questionService.getQuestion(id);
        return new ResponseEntity<>(afterUpdateQuestionDTO, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
