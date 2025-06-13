package model;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class GarbageRule {
    private String type;         // ごみの種類（例：燃えるごみ）
    private String rule;         // 曜日（例：Monday, Friday）
    private int intervalMonths;  // 間隔（例：1 = 毎月）
    private int startMonth;      // 開始月（例：1 = 1月）
    private String region;       // 地域

    // ゲッター・セッター
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getIntervalMonths() {
        return intervalMonths;
    }

    public void setIntervalMonths(int intervalMonths) {
        this.intervalMonths = intervalMonths;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    // ★ 判定ロジック（この日が収集対象日かどうか）
    public boolean appliesTo(LocalDate date) {
        // ルールの曜日と一致しているか
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        boolean matchesDay = rule.contains(dayOfWeek.toString());

        // 月の間隔に合っているか（例：2か月ごと、開始月1月 → 1,3,5,...）
        boolean matchesInterval = ((date.getMonthValue() - startMonth) % intervalMonths == 0);

        return matchesDay && matchesInterval;
    }
}
