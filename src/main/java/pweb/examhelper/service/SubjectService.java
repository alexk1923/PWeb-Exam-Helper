package pweb.examhelper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.dto.subject.SubjectAddQuizDTO;
import pweb.examhelper.dto.subject.SubjectCreationDTO;
import pweb.examhelper.dto.subject.SubjectDTO;
import pweb.examhelper.entity.Quiz;
import pweb.examhelper.entity.Subject;
import pweb.examhelper.mapper.QuizMapper;
import pweb.examhelper.mapper.SubjectMapper;
import pweb.examhelper.repository.SubjectRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class SubjectService implements ISubjectService{

    private SubjectRepository subjectRepository;

    @Override
    public List<SubjectDTO> getAllSubjects() {
        return null;
    }

    @Override
    public SubjectDTO getSubject(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(RuntimeException::new);
        return SubjectMapper.toSubjectDTO(subject);

    }

    @Override
    public SubjectDTO createSubject(SubjectCreationDTO subjectCreationDTO) {
        Subject newSubject = SubjectMapper.toSubject(subjectCreationDTO);
        Subject createdSubject = subjectRepository.save(newSubject);
        return SubjectMapper.toSubjectDTO(createdSubject);
    }

    @Override
    public SubjectDTO updateSubject() {
        return null;
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
}
