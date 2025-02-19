package com.bing.bing.dto;

import lombok.Data;

/**
 * CDI接口取得员工信息DTO
 *
 * @author Simeon
 */
@Data
public class CDIListEmployeeDTO extends CDIResponseDTO {
    private Response[] response;
    @Data
    public static class Response {
        private String LPN;
        private String GPN;
        private String sel_EY_Rank;
        private String First_Name_1;
        private String Last_Name_1;
        private String sel_Service_Line;
        private String Desc_Management_Unit;
        private String Internet_Addr;

//        private Integer Id;
//        private String SetID;
//        private String LPN;
//        private String GUI;
//        private String First_Name;
//        private String Middle_Name;
//        private String Last_Name;
//        private String First_Name_1;
//        private String Last_Name_1;
//        private String Delete_Flag01;
//        private String sel_Per_Status;
//        private String Per_Status;
//        private String Location;
//        private String sel_Location;
//        private String sel_Empl_Status;
//        private String Empl_Status;
//        private String Business_Title;
//        private String DeptID;
//        private String Empl_Class;
//        private String sel_Empl_Class;
//        private String Sal_Admin_Plan_Desc;
//        private String Sal_Admin_Plan_Val;
//        private String Sal_Admin_Plan;
//        private String GPN;
//        private String Notes_Addr;
//        private String Internet_Addr;
//        private String Termination_Dt;
//        private String Orig_Hire_Dt;
//        private String Rehire_Dt;
//        private String Service_Dt;
//        private String Seniority_Pay_Dt;
//        private String Extension;
//        private String Region;
//        private String DeptDesc;
//        private String Service_Line;
//        private String sel_Service_Line;
//        private String Service_Line_Grouping;
//        private String sel_Service_Line_Grouping;
//        private String Business_Unit;
//        private String Operating_Unit;
//        private String Management_Unit;
//        private String SubManagement_Unit;
//        private String Desc_Business_Unit;
//        private String Desc_Operating_Unit;
//        private String Desc_Management_Unit;
//        private String Desc_SubManagement_Unit;
//        private String EY_Rank;
//        private String sel_EY_Rank;
//        private String EY_Grade;
//        private String CS_Support_Ind;
//        private String Assoc_Firm;
//        private String Rehire_Ind;
//        private String ADDomain;
//        private String ADAccount;
//        private String Eff_Dt;
//        private String Probation_Dt;
    }
}
