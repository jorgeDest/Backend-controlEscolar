package Panri.Backend.service;

import Panri.Backend.DTOs.SubjectDTO;
import Panri.Backend.model.SubjectEntity;
import Panri.Backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public SubjectEntity createNewSubject(SubjectDTO subjectDTO) {

        SubjectEntity subject = new SubjectEntity();
        //Guardar los valores del DTO en la entidad
        subject.setName(subjectDTO.getSubjectName());
        subject.setSubjectCode(subjectDTO.getSubjectCode());

        return subjectRepository.save(subject);
    }
}
