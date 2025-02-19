package com.ruoyi.filingsystem.mapper;

import com.ruoyi.filingsystem.domain.FilingFileLoanReturn;
import com.ruoyi.filingsystem.dto.*;
import com.ruoyi.filingsystem.vo.FilingBorrowRecordVO;
import com.ruoyi.filingsystem.vo.FilingFetchLoanFileVO;
import com.ruoyi.filingsystem.vo.FilingFetchReturnFileVO;

import java.util.List;

public interface FilingBorrowRecordMapper {

    List<FilingBorrowRecordVO> getBorrowRecordsByStaffID(GetBorrowRecordDTO dto);

    FilingFetchLoanFileVO fetchBorrowFile(FetchBorrowFileDTO dto);

    int borrowConfirm(List<InsertBorrowConfirmDTO> list);

    void updateBorrower(BorrowConfirmDTO dto);

    void recoverBorrower(BorrowConfirmDTO dto);

    FilingFetchReturnFileVO fetchReturnFile(FetchReturnFileDTO dto);

    int returnConfirm(String[] fileIDs);

    //发送邮件需要使用查询的接口
    List<FilingFileLoanReturn> selectByFileIDs(String[] fileIDs);


    String getSystemDate();
}
