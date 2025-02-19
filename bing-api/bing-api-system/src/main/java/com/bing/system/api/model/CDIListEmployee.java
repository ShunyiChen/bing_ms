package com.bing.system.api.model;

/**
 * CDI接口取得员工信息DTO
 *
 * @author Simeon
 */
public class CDIListEmployee extends CDIResponse {
    private Response[] response;

    public static class Response {
        private String LPN;
        private String GPN;
        private String sel_EY_Rank;
        private String First_Name_1;
        private String Last_Name_1;
        private String sel_Service_Line;
        private String Desc_Management_Unit;
        private String Internet_Addr;

        public String getLPN() {
            return LPN;
        }

        public void setLPN(String LPN) {
            this.LPN = LPN;
        }

        public String getGPN() {
            return GPN;
        }

        public void setGPN(String GPN) {
            this.GPN = GPN;
        }

        public String getSel_EY_Rank() {
            return sel_EY_Rank;
        }

        public void setSel_EY_Rank(String sel_EY_Rank) {
            this.sel_EY_Rank = sel_EY_Rank;
        }

        public String getFirst_Name_1() {
            return First_Name_1;
        }

        public void setFirst_Name_1(String first_Name_1) {
            First_Name_1 = first_Name_1;
        }

        public String getLast_Name_1() {
            return Last_Name_1;
        }

        public void setLast_Name_1(String last_Name_1) {
            Last_Name_1 = last_Name_1;
        }

        public String getSel_Service_Line() {
            return sel_Service_Line;
        }

        public void setSel_Service_Line(String sel_Service_Line) {
            this.sel_Service_Line = sel_Service_Line;
        }

        public String getDesc_Management_Unit() {
            return Desc_Management_Unit;
        }

        public void setDesc_Management_Unit(String desc_Management_Unit) {
            Desc_Management_Unit = desc_Management_Unit;
        }

        public String getInternet_Addr() {
            return Internet_Addr;
        }

        public void setInternet_Addr(String internet_Addr) {
            Internet_Addr = internet_Addr;
        }

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

    public Response[] getResponse() {
        return response;
    }

    public void setResponse(Response[] response) {
        this.response = response;
    }
}
