package com.shit_code.cloud.lib.springboot.database.sql.process;

import com.shit_code.cloud.lib.springboot.database.sql.SqlScript;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Anthony Chen
 * @date 2020/3/5
 **/
public class DateProcessor extends AbstractProcessor {

    private DecimalFormat format=new DecimalFormat("00");

    @Override
    protected void doProcess(SqlScript sqlScript) {
        LocalDate startDate = sqlScript.getStartDate();
        LocalDate tmp = sqlScript.getEndDate();
        if (tmp == null) {
            tmp = startDate.plusYears(3);
        }
        final LocalDate endDate = tmp;
        sqlScript.setSqlList(
                Stream.generate(new Supplier<LocalDate>() {
                    private int i = 0;
                    @Override
                    public LocalDate get() {
                        return startDate.plusDays(i++);
                    }
                }).limit(endDate.toEpochDay() - startDate.toEpochDay() + 1)
                        .map(date -> resolveSql(sqlScript.getTemplate(), date)).distinct().collect(Collectors.toList()));
    }

    @Override
    protected boolean support(SqlScript sqlScript) {
        return sqlScript.getTemplate().contains("${year}");
    }

    /**
     * @param sql
     * @param localDate
     * @return
     */
    private String resolveSql(String sql, LocalDate localDate) {
        return sql.replaceAll("\\$\\{year}", String.valueOf(localDate.getYear()))
                .replaceAll("\\$\\{month}", format.format(localDate.getMonthValue()))
                .replaceAll("\\$\\{day}", format.format(localDate.getDayOfMonth()));
    }
}
