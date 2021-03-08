package csc1035.project2.timetable;
import javax.persistence.*;
@Entity(name = "module_requirements")
    public class ModuleRequirements {



            @Id
            @Column(updatable = false, nullable = false)
            private String id;

            @Column
            private String weekCommencing;

            @Column
            private int LecturesPerWeek;

            @Column
            private int LectureLength;

            @Column
            private int PracticalsPerWeek;

            @Column
            private int PracticalLength;



            public ModuleRequirements(String weekCommencing, int LecturesPerWeek, int LectureLength, int PracticalsPerWeek, int PracticalLength) {
                this.weekCommencing = weekCommencing;
                this.LectureLength = LectureLength;
                this.LecturesPerWeek = LecturesPerWeek;
                this.PracticalsPerWeek = PracticalsPerWeek;
                this.PracticalLength = PracticalLength;
            }

            public ModuleRequirements() {
            }

            public String getId() {
                return id;
            }

            public String getWeekCommencing() {
                return weekCommencing;
            }

            public int getLectureLength() {
                return LectureLength;
            }

            public int getLecturesPerWeek() {
                return LecturesPerWeek;
            }

            public int getPracticalsPerWeek() {
                return PracticalsPerWeek;
            }

            public int getPracticalLength() {
                return PracticalLength;
            }

            public void setLectureLength(int lectureLength) {
                LectureLength = lectureLength;
            }

            public void setLecturesPerWeek(int lecturesPerWeek) {
                LecturesPerWeek = lecturesPerWeek;
            }

            public void setPracticalLength(int practicalLength) {
                PracticalLength = practicalLength;
            }

            public void setPracticalsPerWeek(int practicalsPerWeek) {
                PracticalsPerWeek = practicalsPerWeek;
            }

            public void setWeekCommencing(String weekCommencing) {
                this.weekCommencing = weekCommencing;
            }
        }

