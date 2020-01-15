package com.cc.study.springboot_demo;

import org.junit.Test;

import java.sql.*;
import java.util.UUID;

/**
 * @author chenc
 * @date 2019/12/19
 **/
public class InsertBatchTest {

    private final String url = "jdbc:mysql://rm-uf69i5878l87ctz11mo.mysql.rds.aliyuncs.com\\:3306/smartbox_test?useUnicode=true&characterEncoding=UTF-8";
    private final String user = "dogwang";
    private final String password = "6QtiWedk86WH26V629rxNb85d5Dg4C";

    @Test
    public void jdbcInsert() {
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "INSERT INTO drop_off_info(id, box_id, drop_off_time, is_error, is_over_time, is_pick_up)" +
                    " VALUES(?, ?, ?, 0, 0, 0)";
            String sql2 = "INSERT INTO recharge_history(id, is_pay, pay_time, recharge_type, reality_fee)" +
                    " VALUES(?, 1, ?, 1, ?)";

            long start = System.currentTimeMillis();
            pstm = conn.prepareStatement(sql2);
            System.out.println("开始时间" + start);

            for (int i = 0; i < 150000; i++) {
                pstm.setString(1, getUUID());
                //pstm.setString(2, "0fa60680221842d9b3ff32eb75a13a8c");
                pstm.setString(2, String.valueOf(System.currentTimeMillis()));
                pstm.setDouble(3, 0.9);

                pstm.addBatch();

            }
            pstm.executeBatch();
            long end = System.currentTimeMillis();
            System.out.println("结束时间" + end);

            System.out.println("用时：" + (end - start) / 1000 + "秒");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("开始时间" + start);
    }

    @Test
    public void addTime() {
        Long time = 1578969668000L;
        Long over24H = 24 * 60 * 60 * 1000L;

        System.out.println(time + over24H);
    }
}
