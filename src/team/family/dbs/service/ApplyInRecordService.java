package team.family.dbs.service;

import team.family.dbs.bean.ApplyInRecord;

import java.util.List;

public interface ApplyInRecordService {
    List<ApplyInRecord>  getAllVisitRecord();
    boolean addVisitRecord(ApplyInRecord applyInRecord);
    boolean delVisitRecord(int applyId);
}
