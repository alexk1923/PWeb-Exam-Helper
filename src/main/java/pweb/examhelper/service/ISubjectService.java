package pweb.examhelper.service;

import pweb.examhelper.dto.subject.SubjectAddQuizDTO;
import pweb.examhelper.dto.subject.SubjectCreationDTO;
import pweb.examhelper.dto.subject.SubjectDTO;

import java.util.List;

public interface ISubjectService {
    List<SubjectDTO> getAllSubjects();
    SubjectDTO getSubject(Long id);
    SubjectDTO createSubject(SubjectCreationDTO subjectCreationDTO);
    SubjectDTO updateSubject();
    void deleteSubject(Long id);

}
