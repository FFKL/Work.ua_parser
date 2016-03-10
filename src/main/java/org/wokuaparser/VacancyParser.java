package org.wokuaparser;

import java.io.IOException;

public class VacancyParser {
    //// TODO: 10.03.2016 refactor project

    public static void main(String[] args) throws IOException, InterruptedException {
        Statistic statistic = new Statistic();
        PageNavigator page = statistic.getPage();
        String url = page.setVacancyAndCity("фармацевт", "kyiv");

        System.out.println("SalaryAverage: " + statistic.getSalaryAverage(url));
    }
}