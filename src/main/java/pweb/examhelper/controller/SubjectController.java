package pweb.examhelper.controller;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pweb.examhelper.dto.quiz.QuizAddQuestionDTO;
import pweb.examhelper.dto.quiz.QuizCreationDTO;
import pweb.examhelper.dto.quiz.QuizDTO;
import pweb.examhelper.dto.subject.SubjectAddQuizDTO;
import pweb.examhelper.dto.subject.SubjectCreationDTO;
import pweb.examhelper.dto.subject.SubjectDTO;
import pweb.examhelper.logger.LoggingController;
import pweb.examhelper.service.QuizService;
import pweb.examhelper.service.SubjectService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    private SubjectService subjectService;
    private QuizService quizService;

    @PostMapping
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectCreationDTO subjectCreationDTO) {
        SubjectDTO createdSubject = subjectService.createSubject(subjectCreationDTO);
        return ResponseEntity.ok(createdSubject);
    }

    @GetMapping("{id}")
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable Long id) {
        SubjectDTO subjectDTO = subjectService.getSubject(id);
        LoggingController.getLogger().info(subjectDTO.getName());
        return ResponseEntity.ok(subjectDTO);
    }

    @GetMapping("{subjectId}/quizzes/{quizId}")
    public ResponseEntity<QuizDTO> getQuiz(@PathVariable Long subjectId, @PathVariable Long quizId) {
        QuizDTO quizDTO = quizService.getQuiz(subjectId, quizId);
        return ResponseEntity.ok(quizDTO);
    }

    @PostMapping("{subjectId}/quizzes")
    public ResponseEntity<QuizDTO> createQuiz(@PathVariable Long subjectId, @RequestBody QuizCreationDTO quizCreationDTO) {
        QuizDTO quizDTO = quizService.createQuiz(quizCreationDTO, subjectId);
        return ResponseEntity.ok(quizDTO);
    }

    @PutMapping("{subjectId}/quizzes/{quizId}")
    public ResponseEntity<QuizDTO> addQuestionToQuiz(@PathVariable Long subjectId,
                                                     @PathVariable Long quizId,
                                                     @RequestBody QuizAddQuestionDTO quizAddQuestionDTO) {
        QuizDTO quizDTO = quizService.addQuestionToQuiz(subjectId, quizId, quizAddQuestionDTO.getQuestionId());
        return ResponseEntity.ok(quizDTO);
    }

    @DeleteMapping("{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }
}
