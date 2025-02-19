package com.bing.system.api.model;


public class CDIEngagement extends CDIResponse {
    private Response[] response;
    public static class Response {
        private String C_LNM; //Client Name
        private String E_NM; //Engagement Name
        private String EID;
        private String CCID; //Client Code
        private String SUBSL_DESCR; //业务线
        private String NAME_EP;
        private String EMAIL_EP;
        private String NAME_EM;
        private String EMAIL_EM;
        private String E_TYPE_CLS_DESCR; //chargeable

        public String getC_LNM() {
            return C_LNM;
        }

        public void setC_LNM(String c_LNM) {
            C_LNM = c_LNM;
        }

        public String getEID() {
            return EID;
        }

        public void setEID(String EID) {
            this.EID = EID;
        }

        public String getCCID() {
            return CCID;
        }

        public void setCCID(String CCID) {
            this.CCID = CCID;
        }

        public String getSUBSL_DESCR() {
            return SUBSL_DESCR;
        }

        public void setSUBSL_DESCR(String SUBSL_DESCR) {
            this.SUBSL_DESCR = SUBSL_DESCR;
        }

        public String getEMAIL_EP() {
            return EMAIL_EP;
        }

        public void setEMAIL_EP(String EMAIL_EP) {
            this.EMAIL_EP = EMAIL_EP;
        }

        public String getNAME_EP() {
            return NAME_EP;
        }

        public void setNAME_EP(String NAME_EP) {
            this.NAME_EP = NAME_EP;
        }

        public String getNAME_EM() {
            return NAME_EM;
        }

        public void setNAME_EM(String NAME_EM) {
            this.NAME_EM = NAME_EM;
        }

        public String getEMAIL_EM() {
            return EMAIL_EM;
        }

        public void setEMAIL_EM(String EMAIL_EM) {
            this.EMAIL_EM = EMAIL_EM;
        }

        public String getE_NM() {
            return E_NM;
        }

        public void setE_NM(String e_NM) {
            E_NM = e_NM;
        }

        public String getE_TYPE_CLS_DESCR() {
            return E_TYPE_CLS_DESCR;
        }

        public void setE_TYPE_CLS_DESCR(String e_TYPE_CLS_DESCR) {
            E_TYPE_CLS_DESCR = e_TYPE_CLS_DESCR;
        }
    }

    public Response[] getResponse() {
        return response;
    }

    public void setResponse(Response[] response) {
        this.response = response;
    }
}
