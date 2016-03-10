package org.wokuaparser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PageNavigator {

    private final String FORMAT_STRING = "%s%s%s";

    private String url = "http://www.work.ua/jobs";

    public String getNextPage(Document currentPage) {
        String nextPage = null;
        Elements elements2 = currentPage.getElementsByClass("no-style");
        for (Element e : elements2) {
            if (e.toString().contains("Следующая")) {
                Elements link = e.getElementsByTag("a");
                String linkAttr = link.attr("abs:href");
                if (!linkAttr.isEmpty())
                    nextPage = linkAttr;
            }
        }
        return nextPage;
    }

    public String setVacancyAndCity(String vacancy, String city) {
        return String.format(FORMAT_STRING, url, "-" + city, "-" + vacancy + "/" );
    }
}
