package com.ruoyi.filingsystem.service;

import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.vo.FilingBorrowRecordVO;
import com.ruoyi.filingsystem.vo.FilingFetchLoanFileVO;
import com.ruoyi.filingsystem.vo.FilingFetchReturnFileVO;
import com.ruoyi.filingsystem.vo.FilingStaffVO;

import java.util.List;

public interface IFilingBorrowRecordService {

    List<FilingBorrowRecordVO> getBorrowRecordsByStaffID(GetBorrowRecordDTO dto);

    FilingFetchLoanFileVO fetchBorrowFile(FetchBorrowFileDTO dto);

    int borrowConfirm(BorrowConfirmDTO dto);

    FilingFetchReturnFileVO fetchReturnFile(FetchReturnFileDTO dto);

    int returnConfirm(String[] fileIDs);
}
