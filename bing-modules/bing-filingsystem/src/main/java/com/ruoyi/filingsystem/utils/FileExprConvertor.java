package com.ruoyi.filingsystem.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileExprConvertor {

    static Map<String, String> COLUMN_MAPPING = new HashMap<>();

    static {
        COLUMN_MAPPING.put("FileID", "fileID");
        COLUMN_MAPPING.put("Location", "areaLocation");
        COLUMN_MAPPING.put("Description", "fileDescription");
        COLUMN_MAPPING.put("Group", "clientGroupName");
        COLUMN_MAPPING.put("ClientCode", "clientNumber");
        COLUMN_MAPPING.put("BoxNo", "boxNumber");
        COLUMN_MAPPING.put("FileType", "fileType");
        COLUMN_MAPPING.put("ClientName", "clientCompanyName");
        COLUMN_MAPPING.put("Remarks", "fileremarks");
        COLUMN_MAPPING.put("MovedTo", "moveTo");
        COLUMN_MAPPING.put("YearEnd", "yearEndDate");
        COLUMN_MAPPING.put("AuditReportDate", "auditReportDate");
        COLUMN_MAPPING.put("RetentionDate", "retentionDate");
        COLUMN_MAPPING.put("CreationDate", "creationDate");
    }

    public String transform(String expression) throws Exception {
        List<String> items = new ArrayList<>();
        List<String> keywords = new ArrayList<>();
        preCheck(expression, items, keywords);
        return done(process(items, keywords));
    }


    private void preCheck(String expression, List<String> items, List<String> keywords) {
        if(!expression.matches("^\\s*(NOT|AND|OR).*")) {
            expression = " AND "+expression;
        }
        String[] parts = expression.split("\\b(NOT|AND|OR)\\b");
        for(String item : parts) {
            if(item.matches("^\\s*\\S+(\\+)*\\s*:\\s*\\S+\\s*")) {
                String[] kv = item.split(":");
                if(COLUMN_MAPPING.containsKey(kv[0].trim())) {
                    items.add(item);
                }
            }
        }
        Pattern pattern = Pattern.compile("\\b(NOT|AND|OR)\\b");
        Matcher matcher = pattern.matcher(expression);
        StringBuilder keywordsBuilder = new StringBuilder();
        while (matcher.find()) {
            keywordsBuilder.append(matcher.group()).append(" ");
        }
        parts = keywordsBuilder.toString().trim().split("\\s+");
        for(String k : parts) {
            keywords.add(k);
        }
    }

    private List<String> process(List<String> items, List<String> keywords) throws Exception {
        List<String> conditions = new ArrayList<>();
        for (int i = 0; i < keywords.size(); i++) {
            String k = keywords.get(i);
            String item = items.get(i);

            if(k.equals("NOT")) {
                String[] kv = item.split(":");
                String column = kv[0].trim();
                String value = kv[1].trim();

                if(column.equals("FileID")
                        || column.equals("BoxNo")) {
                    if(value.matches("^\\d+(\\.\\.)\\d+")) {
                        String[] val = kv[1].split("\\.\\.");
                        String cond = column+" NOT BETWEEN "+val[0]+" AND "+val[1];
                        conditions.add("AND "+cond);
                    } else {
                        String cond = COLUMN_MAPPING.get(column) +" != "+Integer.parseInt(value);
                        conditions.add("AND "+cond);
                    }
                } else if(column.equals("Location")
                        || column.equals("Description")
                        || column.equals("Group")
                        || column.equals("ClientCode")
                        || column.equals("FileType")
                        || column.equals("ClientName")
                        || column.equals("Remarks")
                        || column.equals("MoveTo")){
                    String cond = COLUMN_MAPPING.get(column)+" != '"+value+"'";
                    conditions.add("AND "+cond);
                } else if(column.equals("YearEnd")
                        || column.equals("AuditReportDate")
                        || column.equals("RetentionDate")
                        || column.equals("CreationDate")) {
                    if(value.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])(\\.\\.)\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
                        String[] val = kv[1].split("\\.\\.");
                        String cond = COLUMN_MAPPING.get(column)+" NOT BETWEEN '"+val[0]+"' AND '"+val[1]+"'";
                        conditions.add("AND "+cond);
                    } else {
                        String cond = COLUMN_MAPPING.get(column)+" != '"+value+"'";
                        conditions.add("AND "+cond);
                    }
                }
            } else if(k.equals("AND")) {
                String[] kv = item.split(":");
                String column = kv[0].trim();
                String value = kv[1].trim();

                if(column.equals("FileID")
                        || column.equals("BoxNo")) {
                    if(value.matches("^\\d+(\\.\\.)\\d+")) {
                        String[] val = kv[1].split("\\.\\.");
                        String cond = COLUMN_MAPPING.get(column)+" BETWEEN "+val[0]+" AND "+val[1];
                        conditions.add("AND "+cond);
                    } else {
                        String cond = COLUMN_MAPPING.get(column)+" = "+Integer.parseInt(value);
                        conditions.add("AND "+cond);
                    }
                } else if(column.equals("Location")
                        || column.equals("Description")
                        || column.equals("Group")
                        || column.equals("ClientCode")
                        || column.equals("FileType")
                        || column.equals("ClientName")
                        || column.equals("Remarks")
                        || column.equals("MoveTo")){
                    String cond = COLUMN_MAPPING.get(column)+" = '"+value+"'";
                    conditions.add("AND "+cond);
                } else if(column.equals("YearEnd")
                        || column.equals("AuditReportDate")
                        || column.equals("RetentionDate")
                        || column.equals("CreationDate")) {
                    if(value.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])(\\.\\.)\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
                        String[] val = kv[1].split("\\.\\.");
                        String cond = COLUMN_MAPPING.get(column)+" BETWEEN '"+val[0]+"' AND '"+val[1]+"'";
                        conditions.add("AND "+cond);
                    } else {
                        String cond = COLUMN_MAPPING.get(column)+" = '"+value+"'";
                        conditions.add("AND "+cond);
                    }
                }

            } else if(k.equals("OR")) {
                String[] kv = item.split(":");
                String column = kv[0].trim();
                String value = kv[1].trim();

                if(column.equals("FileID")
                        || column.equals("BoxNo")) {
                    if(value.matches("^\\d+(\\.\\.)\\d+")) {
                        String[] val = kv[1].split("\\.\\.");
                        String cond = COLUMN_MAPPING.get(column)+" BETWEEN "+val[0]+" AND "+val[1];
                        conditions.add("OR "+cond);
                    } else {
                        String cond = COLUMN_MAPPING.get(column)+" = "+Integer.parseInt(value);
                        conditions.add("OR "+cond);
                    }
                } else if(column.equals("Location")
                        || column.equals("Description")
                        || column.equals("Group")
                        || column.equals("ClientCode")
                        || column.equals("FileType")
                        || column.equals("ClientName")
                        || column.equals("Remarks")
                        || column.equals("MoveTo")){
                    String cond = COLUMN_MAPPING.get(column)+" = '"+value+"'";
                    conditions.add("OR "+cond);
                } else if(column.equals("YearEnd")
                        || column.equals("AuditReportDate")
                        || column.equals("RetentionDate")
                        || column.equals("CreationDate")) {
                    if(value.matches("^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])(\\.\\.)\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$")) {
                        String[] val = kv[1].split("\\.\\.");
                        String cond = COLUMN_MAPPING.get(column)+" BETWEEN '"+val[0]+"' AND '"+val[1]+"'";
                        conditions.add("OR "+cond);
                    } else {
                        String cond = COLUMN_MAPPING.get(column)+" = '"+value+"'";
                        conditions.add("OR "+cond);
                    }
                }
            }
        }
        return conditions;
    }

    private String done(List<String> conditions) {
        StringBuilder sqlWhere = new StringBuilder();
        for (String cond: conditions) {
            sqlWhere.append(cond);
            sqlWhere.append(" ");
        }

        // 定义关键字数组
        String[] keywords = {"NOT", "AND", "OR"};
        String trimmedString = "";
        // 遍历关键字数组
        for (String keyword : keywords) {
            // 去除字符串两端的空格并检查是否以当前关键字开始
            if (sqlWhere.toString().trim().startsWith(keyword)) {
                // 去除关键字
                trimmedString = sqlWhere.toString().substring(keyword.length()).trim();
                break; // 已经找到匹配的关键字，不需要继续遍历
            }
        }

        return trimmedString;
    }

//    public static void main(String[] args) {
//        FileExprConvertor fec = new FileExprConvertor();
//        String expression = " AND FileID:1001 AND Location:Dalian AND AuditReportDate:1998-11-10..2998-11-10 NOT AuditReportDate:1998-11-10";
//
////        String expression = "    ANDFileID:1001 OR FileID:1002 AND Location:Dalian NOT AuditReportDate:1998-11-10";
//        String sqlwhere = "";
//        try {
//            sqlwhere = fec.transform(expression);
//        } catch (Exception e) {
//            e.printStackTrace();
////            System.err.println("检索式异常");
//        }
//        System.err.println(sqlwhere);
//    }

}
