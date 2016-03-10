package org.wokuaparser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Statistic {

    final static String REGEXP = "([+-]?\\d+\\.?\\d*)";

    private List<Integer> averageOnPages = new ArrayList<Integer>();

    private PageNavigator page = new PageNavigator();

    public PageNavigator getPage() {
        return page;
    }

    public int getSalaryAverage(String url) throws IOException {
        while (true) {
            Document document = Jsoup.connect(url).timeout(0).get();
            if (url != null) {
                System.out.println(url);
                this.calculateSalaryAverage(document);
            } else
                break;
            url = page.getNextPage(document);
            if (url == null)
                break;
        }
        return this.calculateSalaryAverageOnPages();
    }

    private void calculateSalaryAverage(Document vacancyPage) throws IOException {
        Elements elements = vacancyPage.getElementsByClass("nowrap");
        Pattern pattern = Pattern.compile(REGEXP);

        Matcher matcher;
        List<Integer> cache = new ArrayList<Integer>();
        for (Element e : elements) {
            matcher = pattern.matcher(e.ownText());
            while(matcher.find()){
                cache.add(Integer.parseInt(matcher.group()));
            }

        }

        int sum = 0;
        for (Integer i : cache) {
            sum += i;
        }
        System.out.println(cache);
        Integer average = sum/cache.size();
        System.out.println(average);
        averageOnPages.add(average);
    }

    private Integer calculateSalaryAverageOnPages() {
        int sum = 0;
        for (Integer i : averageOnPages) {
            sum += i;
        }

        return sum/averageOnPages.size();
    }


}
