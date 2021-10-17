package org.wuzl.test.jd;

import org.wuzl.test.util.StringUtil;

public class CreateSqlByWiki {
    public static void main(String[] args) {
        String input =
            "id##主键##bigint(20)##否##``tenant_id##租户id##bigint(20)##否##``data_type##数据类型##int(4)##否##1项目``source_type##数据来源##int(4)##否##101:行云;201:京智``topic##topic##varchar(256)##否``tags##tags##varchar(256)##是##``content##通知信息##varchar(4000)##否``creator##创建人ERP##varchar(32)##否##``modifier##修改人ERP##varchar(32)##否##``deleted##是否有效##varchar(2)##否##(1:有效,0:无效)``ll_version##版本##int(11)##否##``created_date##创建时间##datetime##否##``modified_date##修改时间##datetime##否##";
        String tableName = "t_ll_mp_notice_log";
        String name = "通知日志表";
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE `").append(tableName).append("` (\n");
        String[] lines = input.split("``");
        for (String line : lines) {
            String[] array = line.split("##");
            sb.append("  `").append(array[0]).append("` ");
            sb.append(array[2]);
            sb.append(array[3].trim().equals("否") ? " NOT NULL " : " DEFAULT NULL ");
            if (array[0].equals("id")) {
                sb.append(" AUTO_INCREMENT ");
            }
            if (array[0].equals("deleted") || array[0].equals("ll_version")) {
                sb.append(" DEFAULT '0' ");
            }
            if (array[0].equals("created_date")) {
                sb.append(" DEFAULT CURRENT_TIMESTAMP ");
            }
            if (array[0].equals("modified_date")) {
                sb.append(" DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ");
            }
            sb.append("COMMENT '");
            sb.append(array[1]);
            if (array.length >= 5 && !StringUtil.isBlank(array[4])) {
                sb.append("-").append(array[4]);
            }
            sb.append("',\n");
        }
        sb.append("  PRIMARY KEY (`id`),\n");
        sb.append(") ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='").append(name)
            .append("';");
        System.out.println(sb);
    }

}
