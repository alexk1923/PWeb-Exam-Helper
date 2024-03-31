package pweb.examhelper.mapper;

import pweb.examhelper.dto.quiz.QuizDTO;
import pweb.examhelper.dto.subject.SubjectCreationDTO;
import pweb.examhelper.dto.subject.SubjectDTO;
import pweb.examhelper.entity.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectMapper {
    public static Subject toSubject(SubjectCreationDTO subjectCreationDTO) {
        return new Subject(subjectCreationDTO.getTitle());
   }

    public static SubjectDTO toSubjectDTO(Subject subject) {

        List<QuizDTO> quizzes;
        if(subject.getQuizList() != null && subject.getQuizList().size() > 0) {
            quizzes = subject.getQuizList().stream().map(QuizMapper::mapToQuizDTO).collect(Collectors.toList());
        } else {
            quizzes = new ArrayList<>();
        }

        return new SubjectDTO(subject.getId(), subject.getTitle(), quizzes);
    }
}
