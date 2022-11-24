package platform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Entity
@Table(name = "code")
public class Code {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(name = "code")
    private String code;
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "views")
    private int views;

    @Column(name = "restricted")
    @JsonIgnore
    private boolean restricted;

    @Column(name = "time_restriction")
    @JsonIgnore
    private boolean timeRestricted;

    @Column(name = "views_restricted")
    @JsonIgnore
    private boolean viewsRestricted;

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    public void setTimeRestricted(boolean timeRestricted) {
        this.timeRestricted = timeRestricted;
    }

    public boolean isViewsRestricted() {
        return viewsRestricted;
    }

    public void setViewsRestricted(boolean viewsRestricted) {
        this.viewsRestricted = viewsRestricted;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public long getTime() {
        return ChronoUnit.SECONDS.between(LocalTime.now(), time);
//        return time;
    }

    public void setTime(int time) {
        this.time = LocalTime.now().plusSeconds(time);
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String  code) {
        this.code = code;
    }

    public String getDate() {
        return String.format("%s/%s/%s " + date.toLocalTime().minusNanos(date.getNano()),
                date.getYear(), date.getMonthValue(), date.getDayOfMonth());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Code{" +
                "code='" + code + '\'' +
                ", date=" + getDate() +
                '}';
    }

    public Code(String code, int time, int views) {
        this.code = code;
        this.date = LocalDateTime.now();
        this.time = LocalTime.now().plusSeconds(time);
        this.views = views;
    }

    Code() {
        this.date = LocalDateTime.now();
    }
}
