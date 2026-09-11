package Panri.Backend.service;

import Panri.Backend.DTOs.ReportCardDetailDTO;
import Panri.Backend.model.ReportCardDetailEntity;
import Panri.Backend.model.ReportCardEntity;
import Panri.Backend.model.SubjectEntity;
import Panri.Backend.repository.ReportCardDetailRepository;
import Panri.Backend.repository.ReportCardRepository;
import Panri.Backend.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportCardDetailService {

    @Autowired
    private ReportCardDetailRepository reportCardDetailRepository;
    @Autowired
    private ReportCardRepository reportCardRepository;
    @Autowired
    private SubjectRepository subjectRepository;


    public ReportCardDetailEntity generateCardDetail(ReportCardDetailDTO reportCardDetailDTO){

        //entidades relacionadas, reportCard, subject, score

        SubjectEntity subjectEntity = subjectRepository.findById(reportCardDetailDTO.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Error: El subject no existe"));

        ReportCardEntity reportCardEntity = reportCardRepository.findById(reportCardDetailDTO.getReportCardId())
                .orElseThrow(() -> new RuntimeException("Error: El term no existe"));

        ReportCardDetailEntity reportCardDetailEntity = new ReportCardDetailEntity();

        reportCardDetailEntity.setScore(reportCardDetailDTO.getScore());
        reportCardDetailEntity.setReportCard(reportCardEntity);
        reportCardDetailEntity.setSubjectEntity(subjectEntity);

        return reportCardDetailRepository.save(reportCardDetailEntity);
    }

}
