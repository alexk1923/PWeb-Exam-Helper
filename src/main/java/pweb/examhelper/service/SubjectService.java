package pweb.examhelper.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pweb.examhelper.constants.ErrorMessage;
import pweb.examhelper.dto.student.SubjectUpdateDTO;
import pweb.examhelper.dto.subject.SubjectCreationDTO;
import pweb.examhelper.dto.subject.SubjectDTO;
import pweb.examhelper.entity.Student;
import pweb.examhelper.entity.Subject;
import pweb.examhelper.exception.ResourceNotFoundException;
import pweb.examhelper.mapper.StudentMapper;
import pweb.examhelper.mapper.SubjectMapper;
import pweb.examhelper.repository.SubjectRepository;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class SubjectService implements ISubjectService{

    private SubjectRepository subjectRepository;

    @Override
    public List<SubjectDTO> getAllSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList.stream().map(SubjectMapper::toSubjectDTO).collect(Collectors.toList());
    }

    @Override
    public SubjectDTO getSubject(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SUBJECT_NOT_FOUND));
        return SubjectMapper.toSubjectDTO(subject);

    }

    @Override
    public SubjectDTO createSubject(SubjectCreationDTO subjectCreationDTO) {
        Subject newSubject = SubjectMapper.toSubject(subjectCreationDTO);
        Subject createdSubject = subjectRepository.save(newSubject);
        return SubjectMapper.toSubjectDTO(createdSubject);
    }

    @Override
    public SubjectDTO updateSubject(Long id, SubjectUpdateDTO subjectUpdateDTO) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SUBJECT_NOT_FOUND));
        subject.setTitle(subjectUpdateDTO.getTitle());
        Subject updatedSubject = subjectRepository.save(subject);
        return SubjectMapper.toSubjectDTO(updatedSubject);
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.SUBJECT_NOT_FOUND));
        subjectRepository.deleteById(id);
    }
}
